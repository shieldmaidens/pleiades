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

use openraft::async_trait::async_trait;
use openraft::error::{
    InstallSnapshotError,
    RaftError,
    RPCError,
};
use openraft::raft::{
    AppendEntriesRequest,
    AppendEntriesResponse,
    InstallSnapshotRequest,
    InstallSnapshotResponse,
    VoteRequest,
    VoteResponse,
};
use openraft::RaftNetwork;

use crate::network::HostNode;
use crate::typedef::{NodeId, ShardConfig};

struct RaftServer {}

#[async_trait]
impl RaftNetwork<ShardConfig> for RaftServer {
    async fn send_append_entries(&mut self, rpc: AppendEntriesRequest<ShardConfig>) -> Result<AppendEntriesResponse<NodeId>, RPCError<NodeId, HostNode, RaftError<NodeId>>> {
        todo!()
    }

    async fn send_install_snapshot(&mut self, rpc: InstallSnapshotRequest<ShardConfig>) -> Result<InstallSnapshotResponse<NodeId>, RPCError<NodeId, HostNode, RaftError<NodeId, InstallSnapshotError>>> {
        todo!()
    }

    async fn send_vote(&mut self, rpc: VoteRequest<NodeId>) -> Result<VoteResponse<NodeId>, RPCError<NodeId, HostNode, RaftError<NodeId>>> {
        todo!()
    }
}