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

use std::io::Cursor;
use openraft::Entry;

use nova_api::raft::v1::{RaftEntryRequest, RaftEntryResponse};

use crate::network;

pub type ShardId = u64;
pub type NodeId = u64;

openraft::declare_raft_types!(
    pub RaftShardConfig:
        D = RaftEntryRequest,
        R = RaftEntryResponse,
        NodeId = NodeId,
        Node = network::HostNode,
        Entry = Entry<RaftShardConfig>,
        SnapshotData = Cursor<Vec<u8>>,
        AsyncRuntime = openraft::TokioRuntime
);

pub const SYSTEM_SHARD_RANGE_START: ShardId = 1;
pub const SYSTEM_SHARD_RANGE_STOP: ShardId = 100;