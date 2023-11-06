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

use std::io;
use prost::{DecodeError, EncodeError};
use thiserror::Error;

pub mod db;

pub const DEFAULT_DB_PATH: &'static str = "/var/lib/pleiades/data";
const COLUMN_FAMILY_DESCRIPTOR_KEY: &'static str = "column_family_descriptor";

#[derive(Error, Debug)]
pub enum StorageError {
    #[error("{0}")]
    GeneralError(&'static str),
    #[error("shard id not found")]
    MissingShardId,
    #[error("missing the key value pair")]
    MissingKeyValuePair,
    #[error("key length is zero")]
    ZeroLengthKey,
    #[error("cannot decode key value pair, {0}")]
    KeyValuePairDecodeError(DecodeError),
    #[error("cannot encode key value pair, {0}")]
    KeyValuePairEncodeError(EncodeError),
    #[error("missing shard column family {0} in rocks")]
    MissingColumnFamily(u64),
    #[error("{0}")]
    DiskEngineError(rocksdb::Error),
    #[error("io error, {0}")]
    IoError(io::Error),
}
