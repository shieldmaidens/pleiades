use moka::sync::Cache;
use sysinfo::{System, SystemExt};

use nova_api::raft::v1::{Entry, Snapshot};
use nova_raft::{Error, GetEntriesContext, RaftState, Storage};

pub struct WriteThroughStorage {
    raft_log_cache: Cache<u64, u64>,
    data_cache: Cache<u64, u64>,
}

impl WriteThroughStorage {
    pub fn new() -> WriteThroughStorage {
        let sys = System::new_all();
        let mem = sys.total_memory();
        let base_amount = mem / 10;

        // the raft log gets 10% of total memory
        let raft_cache = Cache::builder()
            .max_capacity(base_amount)
            .build();

        // the data cache gets 20% of all memory
        let data_cache = Cache::builder()
            .max_capacity(base_amount * 2)
            .build();

        WriteThroughStorage {
            raft_log_cache: raft_cache,
            data_cache,
        }
    }
}

impl Storage for WriteThroughStorage {
    fn initial_state(&self) -> Result<RaftState, Error> { todo!() }

    fn entries(&self, low: u64, high: u64, max_size: impl Into<Option<u64>>, context: GetEntriesContext) -> Result<Vec<Entry>, Error> { todo!() }

    fn term(&self, _: u64) -> Result<u64, Error> { todo!() }

    fn first_index(&self) -> Result<u64, Error> { todo!() }

    fn last_index(&self) -> Result<u64, Error> { todo!() }

    fn snapshot(&self, _: u64, _: u64) -> Result<Snapshot, Error> { todo!() }
}