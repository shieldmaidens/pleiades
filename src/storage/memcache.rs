/*
 *   Pleiades Source Code
 *   Copyright (C) 2023 Sienna Lloyd, Pleiades Authors
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

use std::sync::Arc;
use std::time::{Duration, Instant};

use ahash::RandomState;
use bytes::Bytes;
use moka::Expiry;
use moka::notification::RemovalCause;
use moka::sync::Cache;

use nova_api::raft::v1::{KeyValuePair, MetaKeyValuePair};

use crate::storage::{MetaKeyValueStore, StorageError};
use crate::storage::db::DiskStorage;
use crate::storage::StorageError::{MissingColumnFamily, MissingKeyValuePair};
use crate::typedef::{SYSTEM_SHARD_RANGE_START, SYSTEM_SHARD_RANGE_STOP};
use crate::utils::math::between;

pub struct WriteBackCache {
    db: Arc<DiskStorage>,
    data_cache: Cache<Bytes, Arc<MetaKeyValuePair>, RandomState>,
}

impl WriteBackCache {
    pub const DEFAULT_SYSTEM_DATA_EXPIRY: Duration = Duration::from_secs(60);

    pub fn new(path: String) -> Self {
        let db = Arc::new(DiskStorage::new(path));

        // this is our eviction listener and will make sure that things are written back to disk
        // when expired/updated/over size, and deleted from disk when explicitly removed.
        let db_clone = db.clone();
        let eviction_listener = move |_key: Arc<Bytes>, value: Arc<MetaKeyValuePair>, cause: RemovalCause| {
            match cause {
                RemovalCause::Expired => {
                    match db_clone.put(&value) {
                        Ok(_) => {}
                        Err(e) => panic!("failed to write back expired metakeyvaluepair to disk, {}", e)
                    }
                }
                RemovalCause::Explicit => {
                    // nb (sienna): if there is a missing key value pair, that means the database
                    // can't find it, so we don't need to delete it.
                    match db_clone.delete(&value) {
                        Ok(_) => {}
                        Err(e) => match e {
                            MissingColumnFamily(_) => {}
                            MissingKeyValuePair => {}
                            _ => panic!("failed to delete explicit metakeyvaluepair from disk, {}", e)
                        }
                    }
                }
                RemovalCause::Replaced => {
                    match db_clone.put(&value) {
                        Ok(_) => {}
                        Err(e) => panic!("failed to write back replaced metakeyvaluepair to disk, {}", e)
                    }
                }
                RemovalCause::Size => {
                    match db_clone.put(&value) {
                        Ok(_) => {}
                        Err(e) => panic!("failed to write back sized metakeyvaluepair to disk, {}", e)
                    }
                }
            }
        };

        let cache = Cache::builder()
            .max_capacity(100_000)
            .weigher(|_key, value: &Arc<MetaKeyValuePair>| -> u32 {
                value.kvp.clone().unwrap().value.len() as u32
            })
            .eviction_listener(eviction_listener)
            .build_with_hasher(RandomState::default());

        Self {
            db,
            data_cache: cache,
        }
    }
}

/// The expiry policy for the write back cache.
// nb (sienna): this is mostly for plumbing, we can optimize this later for more specific caching
// policies.
impl Expiry<Bytes, Arc<MetaKeyValuePair>> for WriteBackCache {
    fn expire_after_create(&self,
                           _key: &Bytes,
                           value: &Arc<MetaKeyValuePair>,
                           _created_at: Instant,
    ) -> Option<Duration> {
        // check for system shards
        if between(value.shard, SYSTEM_SHARD_RANGE_START, SYSTEM_SHARD_RANGE_STOP) {
            Some(Self::DEFAULT_SYSTEM_DATA_EXPIRY)
        } else {
            None
        }
    }

    fn expire_after_read(&self,
                         _key: &Bytes,
                         _value: &Arc<MetaKeyValuePair>,
                         _read_at: Instant,
                         duration_until_expiry: Option<Duration>,
                         _last_modified_at: Instant,
    ) -> Option<Duration> {
        duration_until_expiry
    }

    fn expire_after_update(&self,
                           _key: &Bytes,
                           _value: &Arc<MetaKeyValuePair>,
                           _updated_at: Instant,
                           duration_until_expiry: Option<Duration>,
    ) -> Option<Duration> {
        duration_until_expiry
    }
}

impl MetaKeyValueStore for WriteBackCache {
    fn get(&self, meta_key: &MetaKeyValuePair) -> Result<KeyValuePair, StorageError> {
        let key = &meta_key.kvp.clone().unwrap().key;
        let found_value = match self.data_cache.get(key) {
            Some(value) => match (*value).clone().kvp {
                None => return Err(MissingKeyValuePair),
                Some(v) => v,
            },
            None => {
                let value = self.db.get(meta_key)?;
                self.data_cache.insert(key.clone(), Arc::new(MetaKeyValuePair {
                    shard: meta_key.shard,
                    kvp: Some(value.clone()),
                    cache_policy: None,
                }));
                value
            }
        };

        Ok(found_value)
    }

    fn put(&self, meta_key: &MetaKeyValuePair) -> Result<(), StorageError> {
        self.data_cache.insert(meta_key.kvp.clone().unwrap().key, Arc::new(meta_key.clone()));
        Ok(())
    }

    fn delete(&self, meta_key: &MetaKeyValuePair) -> Result<(), StorageError> {
        Ok(self.data_cache.invalidate(&meta_key.kvp.clone().unwrap().key))
    }
}

#[cfg(test)]
mod tests {
    use std::env;

    use rand::{Rng, RngCore};

    use nova_api::raft::v1::{KeyValuePair, MetaKeyValuePair};

    use crate::storage::{MetaKeyValueStore, StorageError};
    use crate::storage::memcache::WriteBackCache;

    #[test]
    // nb (sienna): this test uses about 200MiB of disk space, but will clean it up afterwards
    fn put_and_get_and_delete() -> Result<(), StorageError> {
        let temp_dir = env::temp_dir();

        let wbc = WriteBackCache::new(temp_dir.to_str().unwrap().to_string());

        const AMOUNT: u64 = 50_000;

        for idx in 1..AMOUNT {
            let mut kvp = KeyValuePair::default();
            kvp.key = bytes::Bytes::from(format!("test-key-{idx}"));

            // generate a random value between 0 and 4096 bytes for the payload
            let range = rand::thread_rng().gen_range(0..4096);
            let mut buf = Vec::with_capacity(range);
            rand::thread_rng().fill_bytes(&mut buf);

            kvp.value = bytes::Bytes::from(buf);

            // evenly distribute the keys across 4 shards to help with test performance
            let shard_id = if idx / 2 == 0 {
                10
            } else if idx / 3 == 0 {
                110
            } else if idx / 5 == 0 {
                120
            } else {
                130
            };

            let meta_key = MetaKeyValuePair {
                shard: shard_id,
                kvp: Option::from(kvp),
                cache_policy: None,
            };

            match wbc.put(&meta_key) {
                Ok(_) => {}
                Err(e) => return Err(e)
            };

            match wbc.get(&meta_key) {
                Ok(_) => {}
                Err(e) => return Err(e)
            };

            match wbc.delete(&meta_key) {
                Ok(_) => {}
                Err(e) => return Err(e)
            };
        };

        Ok(())
    }
}