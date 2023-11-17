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

/// A trait for encoding and decoding keys and values.
/// May be used for anything, but is primarily used for
/// the KVStore.

pub trait KVEncoder<T>: 
    Send
    + Sync
    + Clone
    + Debug {
    fn encode_key(&self, key: Vec<u8>) -> Result<Vec<u8>, Error>;

    fn encode(&self) -> Result<Vec<u8>, Error>;

    fn decode(&self) -> Result<T, Error>;
}