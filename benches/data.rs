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

use criterion::*;
use mimalloc::MiMalloc;
use nova_api::raft::v1::{
    KeyValuePair,
    MetaKeyValuePair,
};
use pleiades::storage::{
    db::DiskStorage,
    memcache::WriteBackCache,
    MetaKeyValueStore,
};
use rand::{
    Rng,
    RngCore,
};
use tempdir::TempDir;

#[global_allocator]
static GLOBAL: MiMalloc = MiMalloc;

fn gen_meta_key(size: &usize) -> MetaKeyValuePair {
    let mut buf = Vec::with_capacity(*size);
    rand::thread_rng().fill_bytes(&mut buf);

    let mut kvp = KeyValuePair::default();
    kvp.key = bytes::Bytes::from(format!("test-key"));
    kvp.value = bytes::Bytes::from(format!("test-payload"));

    MetaKeyValuePair {
        shard: rand::thread_rng().gen_range(1..100),
        kvp: Option::from(kvp),
        cache_policy: None,
    }
}

pub fn bench_wbc_storage(c: &mut Criterion) {
    let mut group = c.benchmark_group("write back cache storage");

    let temp_dir = match TempDir::new("bench_wbc_storage") {
        | Ok(v) => v,
        | Err(e) => panic!("error making temp directory for data storage, {}", e),
    };
    let db_path = temp_dir.path().to_str().unwrap().to_string();
    let ds = Arc::new(WriteBackCache::new(db_path.clone()));

    for size in [1024, 2048, 4096, 8192, 16384, 32768].iter() {
        group.throughput(Throughput::Bytes(*size as u64));

        group.bench_function(BenchmarkId::new("cache put", *size), |b| {
            b.iter_batched(
                || gen_meta_key(size),
                |meta_key| {
                    match ds.put(&meta_key) {
                        | Ok(_) => {},
                        | Err(e) => panic!("failed to put key value pair, {}", e),
                    };
                },
                BatchSize::LargeInput,
            );
        });

        group.bench_function(BenchmarkId::new("cache get", *size), |b| {
            b.iter_batched(
                || {
                    let meta_key = gen_meta_key(size);

                    match ds.put(&meta_key) {
                        | Ok(_) => {},
                        | Err(e) => panic!("failed to put key value pair, {}", e),
                    };

                    meta_key
                },
                |meta_key| {
                    match ds.get(&meta_key) {
                        | Ok(_) => {},
                        | Err(e) => panic!("failed to get key value pair, {}", e),
                    };
                },
                BatchSize::LargeInput,
            );
        });

        group.bench_function(BenchmarkId::new("cache delete", *size), |b| {
            b.iter_batched(
                || {
                    let meta_key = gen_meta_key(size);

                    match ds.put(&meta_key) {
                        | Ok(_) => {},
                        | Err(e) => panic!("failed to put key value pair, {}", e),
                    };

                    meta_key
                },
                |meta_key| {
                    match ds.delete(&meta_key) {
                        | Ok(_) => {},
                        | Err(e) => panic!("failed to delete key value pair, {}", e),
                    };
                },
                BatchSize::LargeInput,
            );
        });
    }
}

pub fn bench_disk_engine(c: &mut Criterion) {
    let mut group = c.benchmark_group("disk engine");

    let temp_dir = match TempDir::new("bench_disk_engine") {
        | Ok(v) => v,
        | Err(e) => panic!("error making temp directory for data storage, {}", e),
    };
    let db_path = temp_dir.path().to_str().unwrap().to_string();
    let ds = Arc::new(DiskStorage::new(db_path.clone()));

    for size in [1024, 2048, 4096, 8192, 16384, 32768].iter() {
        group.throughput(Throughput::Bytes(*size as u64));

        group.bench_function(BenchmarkId::new("disk engine put", *size), |b| {
            b.iter_batched(
                || gen_meta_key(size),
                |meta_key| {
                    match ds.put(&meta_key) {
                        | Ok(_) => {},
                        | Err(e) => panic!("failed to put key value pair, {}", e),
                    };
                },
                BatchSize::LargeInput,
            );
        });

        group.bench_function(BenchmarkId::new("disk engine get", *size), |b| {
            b.iter_batched(
                || {
                    let meta_key = gen_meta_key(size);

                    match ds.put(&meta_key) {
                        | Ok(_) => {},
                        | Err(e) => panic!("failed to put key value pair, {}", e),
                    };

                    meta_key
                },
                |meta_key| {
                    match ds.get(&meta_key) {
                        | Ok(_) => {},
                        | Err(e) => panic!("failed to get key value pair, {}", e),
                    };
                },
                BatchSize::LargeInput,
            );
        });

        group.bench_function(BenchmarkId::new("disk engine delete", *size), |b| {
            b.iter_batched(
                || {
                    let meta_key = gen_meta_key(size);

                    match ds.put(&meta_key) {
                        | Ok(_) => {},
                        | Err(e) => panic!("failed to put key value pair, {}", e),
                    };

                    meta_key
                },
                |meta_key| {
                    match ds.delete(&meta_key) {
                        | Ok(_) => {},
                        | Err(e) => panic!("failed to delete key value pair, {}", e),
                    };
                },
                BatchSize::LargeInput,
            );
        });
    }
}

criterion_group!(benches, bench_disk_engine, bench_wbc_storage);
criterion_main!(benches);
