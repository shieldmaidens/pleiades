use std::error::Error;
use std::fmt::Write;
use std::path::Path;
use std::sync::Arc;

use anyhow::Result;
use bytes::{
    Bytes,
    BytesMut
};
use openraft::Entry;
use prost::Message;
use rocksdb::{
    BoundColumnFamily,
    ColumnFamilyDescriptor as rColumnFamilyDescriptor,
    DB as ReadOnlyDB,
    IteratorMode,
    Options,
    ReadOptions,
    TransactionDB as DB,
    TransactionDBOptions
};
use rocksdb::ErrorKind::*;

use nova_api::raft::v1::{
    ColumnFamilies,
    ColumnFamilyDescriptor as nColumnFamilyDescriptor,
    KeyValuePair,
    LogId,
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
use crate::storage::MetaRaftLogStorageError::*;
use crate::storage::StorageError::*;
use crate::typedef::RaftShardConfig;

const VOTE_KEY: &'static str = "vote";
const LOG_ROOT_KEY: &'static str = "logs";
const LOG_LAST_PURGED_KEY: &'static str = "last_purged_id";

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
                    Err(_) => return Err(MetaKeyValueStoreError(GeneralKeyValueStoreError("cannot create column family")))
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
            return Err(MetaRaftLogStorageError(ZeroLengthVote));
        }

        // todo (sienna): figure out why this feels like an unnecessary clone. I shouldn't have to
        // clone a nested struct just to check if there are values. rustism?
        let v = vote.vote.clone().expect("the vote must exist");

        if v.leader_id.is_none() {
            return Err(MetaRaftLogStorageError(ZeroLengthVote));
        }

        let cf_handle = match self.db.cf_handle(&vote.shard_id.to_string()) {
            None => match self.create_cf(vote.shard_id) {
                Ok(v) => v,
                Err(_) => return Err(MetaKeyValueStoreError(GeneralKeyValueStoreError("cannot create column family")))
            },
            Some(v) => v,
        };

        match vote.vote {
            None => Err(MetaRaftLogStorageError(MissingVote(vote.shard_id))),
            Some(_) => Ok((vote.vote.clone().unwrap(), cf_handle))
        }
    }

    fn meta_log_unwinder(&self, meta_log: &MetaLogId) -> Result<(LogId, Arc<BoundColumnFamily>), StorageError> {
        if meta_log.shard_id == 0 {
            return Err(MissingShardId);
        }

        if meta_log.log_id.is_none() {
            return Err(MetaRaftLogStorageError(ZeroLengthLogId));
        }

        let cf_handle = match self.db.cf_handle(&meta_log.shard_id.to_string()) {
            None => match self.create_cf(meta_log.shard_id) {
                Ok(v) => v,
                Err(E) => return Err(MetaRaftLogStorageError(GeneralRaftLogError("cannot create column family for meta_log")))
            },
            Some(v) => v
        };

        match &meta_log.log_id {
            None => Err(MetaRaftLogStorageError(MissingLogId(meta_log.shard_id))),
            Some(v) => Ok((meta_log.log_id.clone().unwrap(), cf_handle))
        }
    }

    /// Attempt to create a column family for the shard, returns an error if it's not possible
    fn create_cf(&self, shard_id: u64) -> Result<Arc<BoundColumnFamily>, StorageError> {
        // todo (sienna): implemented a better error handling pattern
        match self.db.create_cf(shard_id.to_string(), &Options::default()) {
            Ok(_) => {}
            Err(cfe) => match cfe.kind() {
                NotFound => {}
                Corruption => {}
                NotSupported => {}
                InvalidArgument => {}
                IOError => {}
                MergeInProgress => {}
                Incomplete => {}
                ShutdownInProgress => {}
                TimedOut => {}
                Aborted => {}
                Busy => {}
                Expired => {}
                TryAgain => {}
                CompactionTooLarge => {}
                ColumnFamilyDropped => {}
                Unknown => {}
            }
        };

        match self.db.cf_handle(&shard_id.to_string()) {
            None => Err(MetaKeyValueStoreError(GeneralKeyValueStoreError("cannot create column family"))),
            Some(v) => Ok(v),
        }
    }
}

fn vote_key_encoder(node_id: &u64, term_id: &u64) -> Vec<u8> {
    let mut buf = BytesMut::new();
    write!(&mut buf, "{}:{}", node_id, term_id).expect("can't write vote key encoding to buffer");
    buf.to_vec()
}

impl MetaRaftLogStorage for DiskStorage {
    fn get_log_state(&mut self) -> Result<LogState, StorageError> {
        todo!()
    }

    fn save_vote(&self, vote: &MetaVote) -> Result<(), StorageError> {
        let (subvote, cf_handle) = self.metavote_unwinder(&vote)?;

        let mut buf = BytesMut::new();
        match subvote.encode(&mut buf) {
            Ok(_) => {}
            Err(e) => return Err(EncoderError(e.to_string()))
        };

        let tx = self.db.transaction();

        // todo (sienna): add better error handling
        match tx.put_cf(&cf_handle, VOTE_KEY.as_bytes(), buf) {
            Ok(_) => {}
            Err(cfe) => match cfe.kind() {
                NotFound => {}
                Corruption => {}
                NotSupported => {}
                InvalidArgument => {}
                IOError => {}
                MergeInProgress => {}
                Incomplete => {}
                ShutdownInProgress => {}
                TimedOut => {}
                Aborted => {}
                Busy => {}
                Expired => {}
                TryAgain => {}
                CompactionTooLarge => {}
                ColumnFamilyDropped => {}
                Unknown => {}
            }
        };

        match tx.commit() {
            Ok(_) => {}
            Err(e) => return Err(DiskEngineError(e))
        };

        Ok(())
    }

    fn read_vote(&mut self, leader_id: u64) -> Result<Option<Vote>, StorageError> {
        let cf_handle = match self.db.cf_handle(&leader_id.to_string()) {
            None => return Err(MetaRaftLogStorageError(GeneralRaftLogError("missing column family for votes"))),
            Some(v) => v
        };

        // todo (sienna): add better error handling
        match self.db.get_cf(&cf_handle, VOTE_KEY) {
            Err(cfe) => match cfe.kind() {
                NotFound => return Err(DiskEngineError(cfe)),
                Corruption => return Err(DiskEngineError(cfe)),
                NotSupported => return Err(DiskEngineError(cfe)),
                InvalidArgument => return Err(DiskEngineError(cfe)),
                IOError => return Err(DiskEngineError(cfe)),
                MergeInProgress => return Err(DiskEngineError(cfe)),
                Incomplete => return Err(DiskEngineError(cfe)),
                ShutdownInProgress => return Err(DiskEngineError(cfe)),
                TimedOut => return Err(DiskEngineError(cfe)),
                Aborted => return Err(DiskEngineError(cfe)),
                Busy => return Err(DiskEngineError(cfe)),
                Expired => return Err(DiskEngineError(cfe)),
                TryAgain => return Err(DiskEngineError(cfe)),
                CompactionTooLarge => return Err(DiskEngineError(cfe)),
                ColumnFamilyDropped => return Err(DiskEngineError(cfe)),
                Unknown => return Err(DiskEngineError(cfe)),
            },
            Ok(v) => return match v {
                None => Err(MetaKeyValueStoreError(MissingKeyValuePair)),
                Some(buf) => match Vote::decode(Bytes::from(buf)) {
                    Ok(x) => Ok(Some(x)),
                    Err(e) => Err(DecoderError(e.to_string()))
                }
            }
        }
    }

    fn append<I>(&mut self, shard_id: u64, entries: I) -> Result<(), StorageError> where I: IntoIterator<Item=Entry<RaftShardConfig>> + Send {
        let cf_handle = match self.db.cf_handle(&shard_id.to_string()) {
            None => return Err(MetaRaftLogStorageError(GeneralRaftLogError("missing column family for logs"))),
            Some(v) => v,
        };

        let tx = self.db.transaction();
        for entry in entries {
            let mut key_buf = BytesMut::new();
            write!(&mut key_buf, "{}/{:?}", LOG_ROOT_KEY, entry.log_id.index.to_le_bytes());

            let val = match serde_json::to_vec(&entry) {
                Ok(v) => v,
                Err(e) => return Err(EncoderError(e.to_string()))
            };

            // todo (sienna): fix the pattern matching
            match tx.put_cf(&cf_handle, key_buf, val) {
                Ok(_) => {}
                Err(put_err) => match put_err.kind() {
                    NotFound => {}
                    Corruption => {}
                    NotSupported => {}
                    InvalidArgument => {}
                    IOError => {}
                    MergeInProgress => {}
                    Incomplete => {}
                    ShutdownInProgress => {}
                    TimedOut => {}
                    Aborted => {}
                    Busy => {}
                    Expired => {}
                    TryAgain => {}
                    CompactionTooLarge => {}
                    ColumnFamilyDropped => {}
                    Unknown => return Err(DiskEngineError(put_err))
                }
            };
        }
        match tx.commit() {
            Ok(_) => Ok(()),
            Err(ce) => match ce.kind() {
                NotFound => return Err(DiskEngineError(ce)),
                Corruption => return Err(DiskEngineError(ce)),
                NotSupported => return Err(DiskEngineError(ce)),
                InvalidArgument => return Err(DiskEngineError(ce)),
                IOError => return Err(DiskEngineError(ce)),
                MergeInProgress => return Err(DiskEngineError(ce)),
                Incomplete => return Err(DiskEngineError(ce)),
                ShutdownInProgress => return Err(DiskEngineError(ce)),
                TimedOut => return Err(DiskEngineError(ce)),
                Aborted => return Err(DiskEngineError(ce)),
                Busy => return Err(DiskEngineError(ce)),
                Expired => return Err(DiskEngineError(ce)),
                TryAgain => return Err(DiskEngineError(ce)),
                CompactionTooLarge => return Err(DiskEngineError(ce)),
                ColumnFamilyDropped => return Err(DiskEngineError(ce)),
                Unknown => return Err(DiskEngineError(ce)),
            }
        }
    }

    fn truncate(&mut self, meta_log_id: &MetaLogId) -> std::result::Result<(), StorageError> {
        let (log_id, cf_handle) = self.meta_log_unwinder(&meta_log_id)?;

        // nb (sienna): I believe this is a zero allocation copy, but check my math
        // log_root_key + / + u64 = 12 bytes
        let mut start_buf = BytesMut::with_capacity(LOG_ROOT_KEY.len() + 12);
        let mut end_buf = BytesMut::with_capacity(LOG_ROOT_KEY.len() + 12);
        write!(&mut start_buf, "{}/{:?}", LOG_ROOT_KEY, log_id.index.to_le_bytes());
        write!(&mut end_buf, "{}/{:?}", LOG_ROOT_KEY, u64::MAX.to_le_bytes());

        let mut read_opts = ReadOptions::default();
        read_opts.fill_cache(false);
        read_opts.set_iterate_range(start_buf..end_buf);

        let tx = self.db.transaction();
        let mut tx_iter = tx.iterator_cf_opt(&cf_handle, read_opts, IteratorMode::Start);

        for log in tx_iter {
            let (key, val) = log.unwrap();

            // todo (sienna): add better error handling
            match tx.delete_cf(&cf_handle, key) {
                Ok(_) => {}
                Err(cfe) => match cfe.kind() {
                    NotFound => {}
                    Corruption => {}
                    NotSupported => {}
                    InvalidArgument => {}
                    IOError => {}
                    MergeInProgress => {}
                    Incomplete => {}
                    ShutdownInProgress => {}
                    TimedOut => {}
                    Aborted => {}
                    Busy => {}
                    Expired => {}
                    TryAgain => {}
                    CompactionTooLarge => {}
                    ColumnFamilyDropped => {}
                    Unknown => {}
                }
            };
        }

        match tx.commit() {
            Ok(_) => Ok(()),
            Err(ce) => match ce.kind() {
                NotFound => return Err(DiskEngineError(ce)),
                Corruption => return Err(DiskEngineError(ce)),
                NotSupported => return Err(DiskEngineError(ce)),
                InvalidArgument => return Err(DiskEngineError(ce)),
                IOError => return Err(DiskEngineError(ce)),
                MergeInProgress => return Err(DiskEngineError(ce)),
                Incomplete => return Err(DiskEngineError(ce)),
                ShutdownInProgress => return Err(DiskEngineError(ce)),
                TimedOut => return Err(DiskEngineError(ce)),
                Aborted => return Err(DiskEngineError(ce)),
                Busy => return Err(DiskEngineError(ce)),
                Expired => return Err(DiskEngineError(ce)),
                TryAgain => return Err(DiskEngineError(ce)),
                CompactionTooLarge => return Err(DiskEngineError(ce)),
                ColumnFamilyDropped => return Err(DiskEngineError(ce)),
                Unknown => return Err(DiskEngineError(ce)),
            }
        }
    }

    fn purge(&mut self, meta_log_id: &MetaLogId) -> std::result::Result<(), StorageError> {
        let (log_id, cf_handle) = self.meta_log_unwinder(meta_log_id)?;

        let mut start_buf = BytesMut::with_capacity(LOG_ROOT_KEY.len() + 12);
        let mut end_buf = BytesMut::with_capacity(LOG_ROOT_KEY.len() + 12);
        write!(&mut start_buf, "{}/{:?}", LOG_ROOT_KEY, u64::MIN.to_le_bytes());
        write!(&mut end_buf, "{}/{:?}", LOG_ROOT_KEY, log_id.index.to_le_bytes());

        let tx = self.db.transaction();

        match tx.put_cf(&cf_handle, LOG_LAST_PURGED_KEY, log_id.index.to_le_bytes()) {
            Ok(_) => {}
            Err(cfe) => match cfe.kind() {
                NotFound => {}
                Corruption => {}
                NotSupported => {}
                InvalidArgument => {}
                IOError => {}
                MergeInProgress => {}
                Incomplete => {}
                ShutdownInProgress => {}
                TimedOut => {}
                Aborted => {}
                Busy => {}
                Expired => {}
                TryAgain => {}
                CompactionTooLarge => {}
                ColumnFamilyDropped => {}
                Unknown => {}
            }
        }

        let mut read_opts = ReadOptions::default();
        read_opts.fill_cache(false);
        read_opts.set_iterate_range(start_buf..end_buf);
        let mut tx_iter = tx.iterator_cf_opt(&cf_handle, read_opts, IteratorMode::Start);

        for log in tx_iter {
            let (key, val) = log.unwrap();
        };

        Ok(())
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
                        Err(e) => return Err(DecoderError(e.to_string()))
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
            Err(e) => return Err(EncoderError(e.to_string()))
        }

        let tx = self.db.transaction();
        match tx.put_cf(&cf_handle, kvp.key, buf) {
            Ok(_) => {}
            Err(e) => return Err(DiskEngineError(e))
        };

        match tx.commit() {
            Ok(_) => {}
            Err(e) => return Err(DiskEngineError(e))
        };

        Ok(())
    }
    /// Deletes a key from the disk storage
    fn delete(&self, key: &MetaKeyValuePair) -> Result<(), StorageError> {
        let (kvp, cf_handle) = self.key_unwinder(&key, false)?;

        let tx = self.db.transaction();
        match tx.delete_cf(&cf_handle, kvp.key) {
            Ok(_) => {}
            Err(e) => return Err(DiskEngineError(e))
        };

        match tx.commit() {
            Ok(_) => Ok(()),
            Err(e) => Err(DiskEngineError(e))
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

    use crate::storage::{COLUMN_FAMILY_DESCRIPTOR_KEY, ColumnFamilyEncoding, MetaKeyValueStore, StorageError};
    use crate::storage::StorageError::{DiskEngineError, IoError};
    use crate::utils::disk::{clear_tmp_dir, TEST_ROCKSDB_PATH};

    use super::{ColumnFamilyEncoder, DiskStorage};

    #[test]
    fn open_blank_db() -> Result<(), StorageError> {
        let temp_dir = match TempDir::new("open_existing_column") {
            Ok(v) => v,
            Err(e) => return Err(IoError(e))
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
            Err(e) => return Err(IoError(e))
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
                Err(e) => return Err(DiskEngineError(e))
            }
            cfs.column_families.push(cfd);
        }

        // encode and write
        let mut buf = Vec::with_capacity(cfs.encoded_len());
        cfs.encode(&mut buf).unwrap();

        match db.put(COLUMN_FAMILY_DESCRIPTOR_KEY.as_bytes(), buf) {
            Ok(_) => {}
            Err(e) => return Err(DiskEngineError(e))
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
            Err(e) => return Err(IoError(e))
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
            Err(e) => Err(IoError(e))
        }
    }
}
