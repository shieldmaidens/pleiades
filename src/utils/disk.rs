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

use std::path::Path;

pub const TEST_ROCKSDB_PATH: &str = "/tmp/pleiades/";

pub fn clear_tmp_dir() -> Result<(), std::io::Error> {

    if Path::new(TEST_ROCKSDB_PATH).exists() {
        match std::fs::remove_dir_all(TEST_ROCKSDB_PATH) {
            Ok(_) => {}
            Err(e) => return Err(e)
        }
        match std::fs::create_dir_all(TEST_ROCKSDB_PATH) {
            Ok(_) => {}
            Err(e) => return Err(e)
        }
    }
    Ok(())
}