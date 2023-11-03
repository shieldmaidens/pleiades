use std::fmt::Debug;
use std::io::{Cursor};
use std::ops::RangeBounds;
use std::path::Path;
use std::sync::Arc;

use bytes::{Bytes};
use moka::sync::Cache;
use openraft::{Entry, LogId, LogState, RaftLogReader, RaftSnapshotBuilder, RaftStorage, Snapshot, SnapshotMeta, StorageError, StoredMembership, Vote};
use openraft::async_trait::async_trait;
use prost::Message;
use rocksdb::{
    ColumnFamilyDescriptor as rColumnFamilyDescriptor,
    DB,
    Options,
};
use sysinfo::{
    System,
    SystemExt,
};

use nova_api::raft::v1::{
    ColumnFamilies,
    ColumnFamilyDescriptor as nColumnFamilyDescriptor,
    ColumnFamilyType,
    RaftEntryResponse,
};

use crate::network::HostNode;
use crate::typedef::{NodeId, ShardConfig, StorageResult};

pub const DEFAULT_DB_PATH: &'static str = "/var/lib/pleiades/data";
const COLUMN_FAMILY_DESCRIPTOR_KEY: &'static str = "column_family_descriptor";

pub struct WriteThroughStorage {
    db: Arc<DB>,
    raft_log_cache: Cache<u64, u64>,
    data_cache: Cache<u64, u64>,
}

impl WriteThroughStorage {
    pub fn new(path: String) -> WriteThroughStorage {
        let sys = System::new_all();
        let mem = sys.total_memory();
        let base_amount = mem / 10;

        // the raft log gets 10% of total memory
        let raft_cache = Cache::builder().max_capacity(base_amount).build();

        // the data cache gets 20% of total memory
        let data_cache = Cache::builder().max_capacity(base_amount * 2).build();

        WriteThroughStorage {
            db: Arc::new(bootstrap_rocks(if path.is_empty() {
                DEFAULT_DB_PATH.to_string()
            } else {
                path
            })),
            raft_log_cache: raft_cache,
            data_cache,
        }
    }

    fn save_vote_(&self, vote: &Vote<NodeId>) -> StorageResult<()> {
        todo!()
    }
}

#[async_trait]
impl RaftLogReader<ShardConfig> for WriteThroughStorage {
    async fn get_log_state(&mut self) -> Result<LogState<ShardConfig>, StorageError<NodeId>> {
        todo!()
    }

    async fn try_get_log_entries<RB: RangeBounds<u64> + Clone + Debug + Send + Sync>(&mut self, range: RB) -> Result<Vec<Entry<ShardConfig>>, StorageError<NodeId>> {
        todo!()
    }
}

#[async_trait]
impl RaftSnapshotBuilder<ShardConfig, Cursor<Vec<u8>>> for WriteThroughStorage {
    async fn build_snapshot(&mut self) -> Result<Snapshot<NodeId, HostNode, Cursor<Vec<u8>>>, StorageError<NodeId>> {
        todo!()
    }
}

#[async_trait]
impl RaftStorage<ShardConfig> for WriteThroughStorage {
    type SnapshotData = Cursor<Vec<u8>>;
    type LogReader = Self;
    type SnapshotBuilder = Self;

    async fn save_vote(&mut self, vote: &Vote<NodeId>) -> Result<(), StorageError<NodeId>> {
        todo!()
    }

    async fn read_vote(&mut self) -> Result<Option<Vote<NodeId>>, StorageError<NodeId>> {
        todo!()
    }

    async fn get_log_reader(&mut self) -> Self::LogReader {
        todo!()
    }

    async fn append_to_log(&mut self, entries: &[&Entry<ShardConfig>]) -> Result<(), StorageError<NodeId>> {
        todo!()
    }

    async fn delete_conflict_logs_since(&mut self, log_id: LogId<NodeId>) -> Result<(), StorageError<NodeId>> {
        todo!()
    }

    async fn purge_logs_upto(&mut self, log_id: LogId<NodeId>) -> Result<(), StorageError<NodeId>> {
        todo!()
    }

    async fn last_applied_state(&mut self) -> Result<(Option<LogId<NodeId>>, StoredMembership<NodeId, HostNode>), StorageError<NodeId>> {
        todo!()
    }

    async fn apply_to_state_machine(&mut self, entries: &[&Entry<ShardConfig>]) -> Result<Vec<RaftEntryResponse>, StorageError<NodeId>> {
        todo!()
    }

    async fn get_snapshot_builder(&mut self) -> Self::SnapshotBuilder {
        todo!()
    }

    async fn begin_receiving_snapshot(&mut self) -> Result<Box<Self::SnapshotData>, StorageError<NodeId>> {
        todo!()
    }

    async fn install_snapshot(&mut self, meta: &SnapshotMeta<NodeId, HostNode>, snapshot: Box<Self::SnapshotData>) -> Result<(), StorageError<NodeId>> {
        todo!()
    }

    async fn get_current_snapshot(&mut self) -> Result<Option<Snapshot<NodeId, HostNode, Self::SnapshotData>>, StorageError<NodeId>> {
        todo!()
    }
}

fn bootstrap_rocks(path: String) -> DB {
    let mut opts = Options::default();
    let mut bootstrap = false;

    let tmp_path = Path::new(&path).join("CURRENT");
    if !Path::new(&tmp_path).exists() {
        bootstrap = true;
    }

    set_rocks_opts(&mut opts, bootstrap);

    // first we have to attempt to load the DB to get the column families
    // todo (sienna): this feels... weird.
    let mut cfs: Vec<rColumnFamilyDescriptor> = vec![];
    'a: {
        let db = match DB::open_for_read_only(&opts, &path, true) {
            Ok(db) => db,
            // break because we can't open the DB, so there are no column families to create
            Err(_) => break 'a,
        };
        load_cfs(&db, &mut cfs);
    }

    // now we can open the DB for read/write
    let db = DB::open_cf_descriptors(&opts, &path, cfs).unwrap();
    db
}

fn set_rocks_opts(opts: &mut Options, bootstrap: bool) {
    if bootstrap {
        opts.create_if_missing(true);
    }

    opts.enable_statistics();
    opts.set_allow_mmap_reads(true);
    opts.set_allow_mmap_writes(true);
}

fn load_cfs(db: &DB, target: &mut Vec<rColumnFamilyDescriptor>) {
    let desc = db
        .get_pinned(COLUMN_FAMILY_DESCRIPTOR_KEY.as_bytes())
        .unwrap();

    if let Some(desc) = desc {
        let buf = Bytes::from(desc.to_vec());
        let cfs = ColumnFamilies::decode(buf).unwrap();

        for cf in cfs.column_families {
            let cf_opts = Options::default();
            let cf = rColumnFamilyDescriptor::new(cfd_name_encoder(cf), cf_opts);
            target.push(cf);
        }
    }
}

// nb (sienna): if you change this without a migration plan, you'll fuck running workloads
// as this is how we determine the low level encoding for column family keys.
fn cfd_name_encoder(cfd: nColumnFamilyDescriptor) -> String {
    let s = match cfd.r#type() {
        ColumnFamilyType::Unspecified => "unk",
        ColumnFamilyType::Config => "c",
        ColumnFamilyType::RaftLog => "r",
        ColumnFamilyType::Data => "d",
    };

    format!("{}:{}:{}", cfd.range, cfd.shard, s)
}

#[cfg(test)]
mod test {
    use super::WriteThroughStorage;

    const TEST_ROCKSDB_PATH: &'static str = "/tmp/pleiades";

    fn clear_dir() {
        match std::fs::remove_dir_all(TEST_ROCKSDB_PATH.to_string()) {
            Ok(_) => (),
            Err(e) => panic!("can't delete test directory, error {}", e),
        };

        match std::fs::create_dir_all(TEST_ROCKSDB_PATH.to_string()) {
            Ok(_) => (),
            Err(e) => panic!("can't create temp test directory, error {}", e),
        };
    }

    #[test]
    fn blank_db() {
        clear_dir();
        let _ = WriteThroughStorage::new(TEST_ROCKSDB_PATH.to_string());
    }
}
