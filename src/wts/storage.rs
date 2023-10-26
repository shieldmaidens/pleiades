use moka::sync::Cache;
use sysinfo::{SystemExt, System};

pub struct WriteThroughStorage {
    raft_log_cache: Cache,
    data_cache: Cache
}
impl WriteThroughStorage {
    pub fn new() -> &'static WriteThroughStorage {
        let sys = System::new_all();
        let mem = sys.total_memory();
        let base_amount = mem / 10;

        let mut rlc = Cache.builder()
            .max_capacity();
    }
}