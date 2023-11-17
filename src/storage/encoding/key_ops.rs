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

use std::io::{
    Error,
    ErrorKind::InvalidData,
};

use bytes::{Buf, Bytes, BytesMut};
use nova_api::raft::v1::{
    KeyValuePair,
    KeyValuePairDescriptor,
};
use prost::{
    DecodeError,
    Message,
};

use crate::storage::{
    encoding::{
        key_ops::KeyType::{
            DescriptorKey,
            LatestVersionKey,
            SpecificVersionKey,
            UnknownKey,
        },
        Delimiter,
        DESCRIPTOR_DELIMITER,
        DESCRIPTOR_TAG,
        LATEST_VERSION_DELIMITER,
        LATEST_VERSION_TAG,
        SPECIFIC_VERSION_DELIMITER,
    },
    StorageError,
};

const CRC64: crc::Crc<u64> = crc::Crc::<u64>::new(&crc::CRC_64_GO_ISO);

pub enum KeyType {
    UnknownKey,
    LatestVersionKey,
    SpecificVersionKey,
    DescriptorKey,
}

pub fn determine_key_type(delim: u8) -> KeyType {
    if delim == LATEST_VERSION_DELIMITER {
        LatestVersionKey
    } else if delim == SPECIFIC_VERSION_DELIMITER {
        SpecificVersionKey
    } else if delim == DESCRIPTOR_DELIMITER {
        DescriptorKey
    } else {
        UnknownKey
    }
}

/// Construct a [`KeyValuePair`] from disk storage and return a hash.
pub fn key_constructor(
    key: Box<[u8]>,
    val: Box<[u8]>,
    kvp: &mut KeyValuePair,
) -> Result<u64, Error> {
    let tag_byte = key[key.len() - 1];
    let delim_byte = val[val.len() - 2];

    match determine_key_type(delim_byte) {
        | UnknownKey => {
            return Err(Error::new(
                InvalidData,
                "delimiter byte cannot be determined",
            ))
        },
        | LatestVersionKey => {
            // nb (sienna): this is to force compatibility for the key tag as `@latest` is a
            // stable reference.
            if tag_byte != LATEST_VERSION_TAG {
                panic!("the latest version tag byte does not match the latest tag delimiter!")
            }
            kvp.version = 255;
        },
        | SpecificVersionKey => {
            kvp.version = u32::from(tag_byte);
            kvp.value = Bytes::from(val);
        },
        | DescriptorKey => {
            match KeyValuePairDescriptor::decode(Bytes::from(val)) {
            | Ok(v) => {
                kvp.lease = v.lease;
                kvp.create_time = v.create_time;
                kvp.mod_time = v.mod_time;
                kvp.vacuumable = v.vacuumable;
            },
            | Err(e) => return Err(Error::new(InvalidData, e.to_string())),
        }
        },
    };

    let actual_key = &key[0 .. key.len() - 2];
    kvp.key = Bytes::from(actual_key.to_owned());

    Ok(CRC64.checksum(actual_key))
}
