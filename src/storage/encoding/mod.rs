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

pub mod key_ops;

pub type Delimiter = u8;
pub type Tag = u8;

// delimiters

/// This is the logical separator for namespacing within the hierarchy of keys
/// on disk.
pub const NAMESPACE_DELIMITER: Delimiter = b'/';

/// Used to delimit the key from it's latest version.
pub const LATEST_VERSION_DELIMITER: Delimiter = b'@';
/// Used to delimit the key from a specific version.
pub const SPECIFIC_VERSION_DELIMITER: Delimiter = b':';
/// Used to delimit a key from it's descriptor.
pub const DESCRIPTOR_DELIMITER: Delimiter = b'.';

// tags

/// This version tag is used to represent the latest version of a given key.
/// This is used with disk storage, but
pub const LATEST_VERSION_TAG: Tag = b'l';

/// The tag used to identify the delimiter.
pub const DESCRIPTOR_TAG: Tag = b'd';
