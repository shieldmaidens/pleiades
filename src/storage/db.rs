use std::path::Path;
use std::sync::Arc;

use anyhow::Result;
use bytes::Bytes;
use prost::Message;
use rocksdb::{
    BoundColumnFamily,
    ColumnFamilyDescriptor as rColumnFamilyDescriptor,
    DB as ReadOnlyDB,
    Options,
    TransactionDB as DB,
    TransactionDBOptions,
};

use nova_api::raft::v1::{
    ColumnFamilies,
    ColumnFamilyDescriptor as nColumnFamilyDescriptor,
    KeyValuePair,
    LogState,
    MetaKeyValuePair,
    MetaLogId,
    MetaVote,
    Vote,
};

use crate::storage::{
    COLUMN_FAMILY_DESCRIPTOR_KEY,
    ColumnFamilyEncoding,
    DEFAULT_DB_PATH,
    MetaKeyValueStore,
    MetaRaftLogStorage,
    StorageError,
};
use crate::storage::MetaKeyValueStoreError::*;
use crate::storage::MetaRaftLogStorageError::{
    MissingVote,
    ZeroLengthVote,
};
use crate::storage::StorageError::{MetaKeyValueStoreError, MissingShardId};

/// The default disk storage implementation in Pleiades. The underlying storage is provided by
/// RocksDB.
pub struct DiskStorage {
    db: Arc<DB>,
}

impl DiskStorage {
    /// Generate a new DiskStorage implementation
    pub fn new(path: String) -> DiskStorage {
        DiskStorage {
            db: Arc::new(bootstrap_rocks(if path.is_empty() {
                DEFAULT_DB_PATH.to_string()
            } else {
                path
            })),
        }
    }

    fn key_unwinder(&self, key: &MetaKeyValuePair, create_if_not_exists: bool) -> Result<(KeyValuePair, Arc<BoundColumnFamily>), StorageError> {
        if key.shard == 0 {
            return Err(MissingShardId);
        }

        if key.kvp.is_none() {
            return Err(MetaKeyValueStoreError(MissingKeyValuePair));
        }

        // todo (sienna): figure out why this feels like an unnecessary clone. I shouldn't have to
        // clone a nested struct just to check if there are values. rustism?
        let kvp_bytes = key.kvp.clone().expect("the key value pair must exist").key;

        if kvp_bytes.is_empty() {
            return Err(MetaKeyValueStoreError(ZeroLengthKey));
        }

        let cf_handle = match self.db.cf_handle(&key.shard.to_string()) {
            None => if create_if_not_exists {
                match self.create_cf(key.shard) {
                    Ok(v) => v,
                    Err(_) => return Err(MetaKeyValueStoreError(GeneralError("cannot create column family")))
                }
            } else {
                return Err(MetaKeyValueStoreError(MissingColumnFamily(key.shard)));
            },
            Some(v) => v,
        };

        match key.kvp {
            None => Err(MetaKeyValueStoreError(MissingKeyValuePair)),
            Some(_) => Ok((key.kvp.clone().unwrap(), cf_handle))
        }
    }

    fn metavote_unwinder(&self, vote: &MetaVote) -> Result<(Vote, Arc<BoundColumnFamily>), StorageError> {
        if vote.shard_id == 0 {
            return Err(MissingShardId);
        }

        if vote.vote.is_none() {
            return Err(StorageError::MetaRaftLogStorageError(ZeroLengthVote));
        }

        // todo (sienna): figure out why this feels like an unnecessary clone. I shouldn't have to
        // clone a nested struct just to check if there are values. rustism?
        let v = vote.vote.clone().expect("the key value pair must exist");

        if v.leader_id.is_none() {
            return Err(StorageError::MetaRaftLogStorageError(ZeroLengthVote));
        }

        let cf_handle = match self.db.cf_handle(&vote.shard_id.to_string()) {
            None => match self.create_cf(vote.shard_id) {
                Ok(v) => v,
                Err(_) => return Err(MetaKeyValueStoreError(GeneralError("cannot create column family")))
            },
            Some(v) => v,
        };

        match vote.vote {
            None => Err(StorageError::MetaRaftLogStorageError(MissingVote(vote.shard_id))),
            Some(_) => Ok((vote.vote.clone().unwrap(), cf_handle))
        }
    }

    /// Attempt to create a column family for the shard, returns an error if it's not possible
    fn create_cf(&self, shard_id: u64) -> Result<Arc<BoundColumnFamily>, StorageError> {
        match self.db.create_cf(shard_id.to_string(), &Options::default()) {
            Ok(_) => {}
            Err(e) => return Err(MetaKeyValueStoreError(DiskEngineError(e)))
        };

        match self.db.cf_handle(&shard_id.to_string()) {
            None => Err(MetaKeyValueStoreError(GeneralError("cannot create column family"))),
            Some(v) => Ok(v),
        }
    }
}

impl MetaRaftLogStorage for DiskStorage {
    fn get_log_state(&mut self) -> std::result::Result<LogState, StorageError> {
        todo!()
    }

    fn save_vote(&self, vote: &MetaVote) -> std::result::Result<(), StorageError> {
        todo!()
    }

    fn read_vote(&mut self) -> std::result::Result<Option<Vote>, StorageError> {
        todo!()
    }

    fn append<I>(&mut self, entries: Vec<I>) -> std::result::Result<(), StorageError> {
        todo!()
    }

    fn truncate(&mut self, log_id: &MetaLogId) -> std::result::Result<(), StorageError> {
        todo!()
    }

    fn purge(&mut self, log_id: &MetaLogId) -> std::result::Result<(), StorageError> {
        todo!()
    }
}

impl MetaKeyValueStore for DiskStorage {
    /// Fetches a key from the local disk storage.
    fn get(&self, key: &MetaKeyValuePair) -> Result<KeyValuePair, StorageError> {
        let (kvp, cf_handle) = self.key_unwinder(&key, false)?;

        let found_kvp = match self.db.get_cf(&cf_handle, kvp.key) {
            Ok(kvp_bytes) => match kvp_bytes {
                None => return Err(MetaKeyValueStoreError(MissingKeyValuePair)),
                Some(b) => {
                    let buf = Bytes::from(b);
                    match KeyValuePair::decode(buf) {
                        Ok(v) => v,
                        Err(e) => return Err(MetaKeyValueStoreError(KeyValuePairDecodeError(e)))
                    }
                }
            }
            Err(_) => return Err(MetaKeyValueStoreError(MissingKeyValuePair))
        };

        Ok(found_kvp)
    }
    /// Puts a key into the disk storage
    fn put(&self, key: &MetaKeyValuePair) -> Result<(), StorageError> {
        let (kvp, cf_handle) = self.key_unwinder(&key, true)?;

        let mut buf = vec![];
        match kvp.encode(&mut buf) {
            Ok(_) => {}
            Err(e) => return Err(MetaKeyValueStoreError(KeyValuePairEncodeError(e)))
        }

        let tx = self.db.transaction();
        match tx.put_cf(&cf_handle, kvp.key, buf) {
            Ok(_) => {}
            Err(e) => return Err(MetaKeyValueStoreError(DiskEngineError(e)))
        };

        match tx.commit() {
            Ok(_) => {}
            Err(e) => return Err(MetaKeyValueStoreError(DiskEngineError(e)))
        };

        Ok(())
    }
    /// Deletes a key from the disk storage
    fn delete(&self, key: &MetaKeyValuePair) -> Result<(), StorageError> {
        let (kvp, cf_handle) = self.key_unwinder(&key, false)?;

        let tx = self.db.transaction();
        match tx.delete_cf(&cf_handle, kvp.key) {
            Ok(_) => {}
            Err(e) => return Err(MetaKeyValueStoreError(DiskEngineError(e)))
        };

        match tx.commit() {
            Ok(_) => Ok(()),
            Err(e) => Err(MetaKeyValueStoreError(DiskEngineError(e)))
        }
    }
}

impl Drop for DiskStorage {
    /// Ensure that all background work is safely stopped before drop() is finalized
    fn drop(&mut self) {
        // self.db.cancel_all_background_work(true);
    }
}

fn bootstrap_rocks(path: String) -> DB {
    let mut opts = Options::default();
    let mut tx_opts = TransactionDBOptions::default();
    let mut bootstrap = false;

    let tmp_path = Path::new(&path).join("CURRENT");
    if !Path::new(&tmp_path).exists() {
        bootstrap = true;
    }

    set_rocks_opts(&mut opts, &mut tx_opts, bootstrap);

    // first we have to attempt to load the DB to get the column families
    let mut cfs: Vec<rColumnFamilyDescriptor> = vec![];
    if !bootstrap {
        'a: {
            let db = match ReadOnlyDB::open_for_read_only(&opts, &path, true) {
                Ok(db) => db,
                // break because we can't open the DB, so there are no column families to create
                Err(_) => break 'a,
            };
            load_cfs(&db, &mut cfs);
        }
    }

    // now we can open the DB for read/write
    let db = DB::open_cf_descriptors(&opts, &tx_opts, &path, cfs).unwrap();
    db
}

fn set_rocks_opts(opts: &mut Options, tx_opts: &mut TransactionDBOptions, bootstrap: bool) {
    if bootstrap {
        opts.create_if_missing(true);
    }

    // 1ms should be more than enough given that we have small payloads, super accurate clocks
    // and a bunch of other stuff in our favour that should realistically prevent deadlocks
    tx_opts.set_txn_lock_timeout(1);

    opts.enable_statistics();
    opts.set_allow_mmap_reads(true);
    opts.set_allow_mmap_writes(true);
}

fn load_cfs(db: &ReadOnlyDB, target: &mut Vec<rColumnFamilyDescriptor>) {
    let desc = db
        .get_pinned(COLUMN_FAMILY_DESCRIPTOR_KEY.as_bytes())
        .unwrap();

    if let Some(desc) = desc {
        let buf = Bytes::from(desc.to_vec());
        let cfs = ColumnFamilies::decode(buf).unwrap();

        for cf in cfs.column_families {
            let cf_opts = Options::default();
            let cf = rColumnFamilyDescriptor::new(ColumnFamilyEncoder::default().encode(&cf), cf_opts);
            target.push(cf);
        }
    }
}

#[derive(Default)]
pub struct ColumnFamilyEncoder {}

impl ColumnFamilyEncoding for ColumnFamilyEncoder {
    fn encode(&self, cfd: &nColumnFamilyDescriptor) -> String {
        format!("{}-{}", cfd.range, cfd.shard)
    }

    fn decode(&self, _key: Vec<u8>) -> std::result::Result<nova_api::raft::v1::ColumnFamilyDescriptor, std::fmt::Error> {
        unimplemented!()
    }
}

#[cfg(test)]
mod test {
    use std::ops::Rem;

    use prost::Message;
    use rocksdb::{DB, Options};
    use serial_test::serial;
    use tempdir::TempDir;

    use nova_api::raft::v1::{ColumnFamilies, ColumnFamilyDescriptor, KeyValuePair, MetaKeyValuePair};
    use nova_api::raft::v1::ColumnFamilyType::{Config, Data, RaftLog, Unspecified};

    use crate::storage::{COLUMN_FAMILY_DESCRIPTOR_KEY, ColumnFamilyEncoding, MetaKeyValueStore,StorageError};
    use crate::storage::MetaKeyValueStoreError::{DiskEngineError, IoError};
    use crate::storage::StorageError::MetaKeyValueStoreError;
    use crate::utils::disk::{clear_tmp_dir, TEST_ROCKSDB_PATH};

    use super::{ColumnFamilyEncoder, DiskStorage};

    #[test]
    fn open_blank_db() -> Result<(), StorageError> {
        let temp_dir = match TempDir::new("open_existing_column") {
            Ok(v) => v,
            Err(e) => return Err(MetaKeyValueStoreError(IoError(e)))
        };
        let db_path = temp_dir.path().to_str().unwrap().to_string();

        let db = DiskStorage::new(db_path);
        drop(db);

        Ok(())
    }

    #[test]
    fn open_existing_column() -> Result<(), StorageError> {
        // clear the directory so we can write a new db, then open an existing one
        let temp_dir = match TempDir::new("open_existing_column") {
            Ok(v) => v,
            Err(e) => return Err(MetaKeyValueStoreError(IoError(e)))
        };
        let db_path = temp_dir.path().to_str().unwrap().to_string();

        let mut opts = Options::default();
        opts.create_if_missing(true);

        let db_path_clone = db_path.clone();
        let db = DB::open(&opts, db_path_clone).expect("cannot open rocks db");

        let mut cfs = ColumnFamilies {
            column_families: vec![]
        };

        for i in 1..100 {
            let mut cfd = ColumnFamilyDescriptor::default();
            cfd.range = i;
            cfd.shard = i + 100;

            if i.rem(2) == 0 {
                cfd.r#type = i32::from(Data)
            } else if i.rem(3) == 0 {
                cfd.r#type = i32::from(RaftLog)
            } else if i.rem(5) == 0 {
                cfd.r#type = i32::from(Config)
            } else {
                cfd.r#type = i32::from(Unspecified)
            }

            match db.create_cf(ColumnFamilyEncoder::default().encode(&cfd), &opts) {
                Ok(_) => {}
                Err(e) => return Err(MetaKeyValueStoreError(DiskEngineError(e)))
            }
            cfs.column_families.push(cfd);
        }

        // encode and write
        let mut buf = Vec::with_capacity(cfs.encoded_len());
        cfs.encode(&mut buf).unwrap();

        match db.put(COLUMN_FAMILY_DESCRIPTOR_KEY.as_bytes(), buf) {
            Ok(_) => {}
            Err(e) => return Err(MetaKeyValueStoreError(DiskEngineError(e)))
        }

        // close
        db.cancel_all_background_work(true);
        drop(db);

        // now try to open
        let disk_path_clone = db_path.clone();
        let wts_db = DiskStorage::new(disk_path_clone);
        drop(wts_db);

        Ok(())
    }

    #[test]
    #[serial]
    fn put_and_get_and_delete() -> Result<(), StorageError> {
        const AMOUNT: u64 = 1000;

        match clear_tmp_dir() {
            Ok(_) => {}
            Err(e) => return Err(MetaKeyValueStoreError(IoError(e)))
        }

        let ds = DiskStorage::new(TEST_ROCKSDB_PATH.to_string());

        for idx in 1..AMOUNT {
            let mut kvp = KeyValuePair::default();
            kvp.key = bytes::Bytes::from(format!("test-key-{idx}"));
            kvp.value = bytes::Bytes::from(format!("test-payload-{idx}"));

            let shard_id = if idx / 2 == 0 {
                10
            } else if idx / 3 == 0 {
                11
            } else if idx / 5 == 0 {
                12
            } else {
                13
            };

            // evenly distribute the keys across 4 shards to help with test performance
            let meta_key = MetaKeyValuePair {
                shard: shard_id,
                kvp: Option::from(kvp),
                cache_policy: None,
            };

            match ds.put(&meta_key) {
                Ok(_) => {}
                Err(e) => return Err(e)
            };

            match ds.get(&meta_key) {
                Ok(_) => {}
                Err(e) => return Err(e)
            };

            match ds.delete(&meta_key) {
                Ok(_) => {}
                Err(e) => return Err(e)
            };
        };

        // cleanup
        match clear_tmp_dir() {
            Ok(_) => Ok(()),
            Err(e) => Err(MetaKeyValueStoreError(IoError(e)))
        }
    }
}
