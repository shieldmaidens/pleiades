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

use std::{
    fmt::Error,
    io,
};

use nova_api::raft::v1::{
    ColumnFamilyDescriptor,
    KeyValuePair,
    LogState,
    MetaKeyValuePair,
    MetaLogId,
    MetaVote,
    Vote,
};
use openraft::Entry;
use thiserror::Error;

use crate::typedef::RaftShardConfig;

pub mod db;
pub mod memcache;
pub mod raft;

pub const DEFAULT_DB_PATH: &'static str = "/var/lib/pleiades/data";
const COLUMN_FAMILY_DESCRIPTOR_KEY: &'static str = "column_family_descriptor";

#[derive(Error, Debug)]
pub enum StorageError {
    #[error("shard id not found")]
    MissingShardId,
    #[error("meta key value store error")]
    MetaKeyValueStoreError(MetaKeyValueStoreError),
    #[error("raft log storage error")]
    MetaRaftLogStorageError(MetaRaftLogStorageError),
    #[error("meta cache error, {0}")]
    MetaCacheError(MetaCacheError),
    #[error("encoder error, {0}")]
    EncoderError(String),
    #[error("decoder error, {0}")]
    DecoderError(String),
    #[error("rocksdb error, {0}")]
    DiskEngineError(rocksdb::Error),
    #[error("io error, {0}")]
    IoError(io::Error),
}

#[derive(Error, Debug)]
pub enum MetaCacheError {
    #[error("cache error, {0}")]
    GeneralCacheError(&'static str),
}

#[derive(Error, Debug)]
pub enum MetaKeyValueStoreError {
    #[error("{0}")]
    GeneralKeyValueStoreError(&'static str),
    #[error("missing the key value pair")]
    MissingKeyValuePair,
    #[error("key length is zero")]
    ZeroLengthKey,
    #[error("missing shard column family {0} in rocks")]
    MissingColumnFamily(u64),
}

// todo (sienna): I think the the results should be boxed, but I'm not sure.
// figure this out later
pub trait MetaKeyValueStore {
    /// Fetches a key from the local disk storage.
    fn get(&self, key: &MetaKeyValuePair) -> Result<KeyValuePair, StorageError>;
    /// Puts a key into the disk storage
    fn put(&self, key: &MetaKeyValuePair) -> Result<(), StorageError>;
    /// Deletes a key from the disk storage
    fn delete(&self, key: &MetaKeyValuePair) -> Result<(), StorageError>;
}

pub trait ColumnFamilyEncoding {
    /// Encodes a key value pair into a byte array
    fn encode(&self, key: &ColumnFamilyDescriptor) -> String;
    /// Decodes a string into a colum family descriptor
    fn decode(&self, key: Vec<u8>) -> Result<ColumnFamilyDescriptor, Error>;
}

/// Used for state machine implementation
pub trait MetaRaftLogStorage {
    fn get_log_state(&mut self, shard_id: u64) -> Result<LogState, StorageError>;

    fn save_vote(&self, vote: &MetaVote) -> Result<(), StorageError>;

    fn read_vote(&mut self, shard_id: u64) -> Result<Option<Vote>, StorageError>;

    fn append<I>(&mut self, shard_id: u64, entries: I) -> Result<(), StorageError>
    where
        I: IntoIterator<Item = Entry<RaftShardConfig>> + Send;

    fn truncate(&mut self, log_id: &MetaLogId) -> Result<(), StorageError>;

    fn purge(&mut self, log_id: &MetaLogId) -> Result<(), StorageError>;
}

#[derive(Error, Debug)]
pub enum MetaRaftLogStorageError {
    #[error("{0}")]
    GeneralRaftLogError(&'static str),
    #[error("missing vote for shard {0}")]
    MissingVote(u64),
    #[error("vote payload is zero")]
    ZeroLengthVote,
    #[error("leader id payload is zero")]
    ZeroLengthLeaderId,
    #[error("log_id payload is zero")]
    ZeroLengthLogId,
    #[error("missing log_id for shard {0}")]
    MissingLogId(u64),
    #[error("failed to truncate raft log starting at {0}, {1}")]
    FailedTruncation(u64, &'static str),
    #[error("failed to purge raft log for shard id {0}, {1}")]
    FailedPurge(u64, &'static str),
}
