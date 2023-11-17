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
    fmt::Debug,
    ops::RangeBounds,
    sync::Arc,
};

use nova_api::raft::v1::{
    MetaVote,
    RaftEntryResponse,
};
use openraft::{
    async_trait::async_trait,
    storage::{
        LogFlushed,
        RaftLogStorage,
        RaftStateMachine,
    },
    testing::StoreBuilder,
    Entry,
    LogId,
    LogState,
    OptionalSend,
    RaftLogReader,
    RaftSnapshotBuilder,
    RaftTypeConfig,
    Snapshot,
    SnapshotMeta,
    StorageError,
    StoredMembership,
    Vote,
};
use tempfile::TempDir;

use crate::{
    network::HostNode,
    storage::memcache::WriteBackCache,
    typedef::{
        NodeId,
        RaftShardConfig,
        ShardId,
    },
};

/// A Raft shard storage layer that handles the persistent storage of Raft and
/// user data.
pub struct RaftShard {
    shard_id: ShardId,
    _wbc: Arc<WriteBackCache>,
}

impl RaftShard {
    pub fn new(shard_id: ShardId, _wbc: Arc<WriteBackCache>) -> Self {
        Self { shard_id, _wbc }
    }
}

#[allow(unused)]
#[async_trait]
impl RaftLogStorage<RaftShardConfig> for RaftShard {
    type LogReader = Self;

    async fn get_log_state(&mut self) -> Result<LogState<RaftShardConfig>, StorageError<NodeId>> {
        todo!()
    }

    async fn get_log_reader(&mut self) -> Self::LogReader {
        todo!()
    }

    async fn save_vote(&mut self, vote: &Vote<NodeId>) -> Result<(), StorageError<NodeId>> {
        let mvote = &MetaVote {
            shard_id: self.shard_id,
            vote: Option::from(nova_api::raft::v1::Vote {
                leader_id: Option::from(nova_api::raft::v1::LeaderId {
                    term: vote.leader_id.term,
                    node_id: vote.leader_id.node_id,
                }),
                committed: vote.committed,
            }),
        };

        Ok(())
    }

    async fn read_vote(&mut self) -> Result<Option<Vote<NodeId>>, StorageError<NodeId>> {
        todo!()
    }

    async fn append<I>(
        &mut self,
        entries: I,
        callback: LogFlushed<NodeId>,
    ) -> Result<(), StorageError<NodeId>>
    where
        I: IntoIterator<Item = Entry<RaftShardConfig>> + Send,
        I::IntoIter: OptionalSend, {
        todo!()
    }

    async fn truncate(&mut self, log_id: LogId<NodeId>) -> Result<(), StorageError<NodeId>> {
        todo!()
    }

    async fn purge(&mut self, log_id: LogId<NodeId>) -> Result<(), StorageError<NodeId>> {
        todo!()
    }
}

#[allow(unused)]
#[async_trait]
impl RaftStateMachine<RaftShardConfig> for RaftShard {
    type SnapshotBuilder = Self;

    async fn applied_state(
        &mut self,
    ) -> Result<(Option<LogId<NodeId>>, StoredMembership<NodeId, HostNode>), StorageError<NodeId>>
    {
        todo!()
    }

    async fn apply<I>(
        &mut self,
        entries: I,
    ) -> Result<Vec<RaftEntryResponse>, StorageError<NodeId>>
    where
        I: IntoIterator<Item = Entry<RaftShardConfig>> + OptionalSend,
        I::IntoIter: OptionalSend, {
        todo!()
    }

    async fn get_snapshot_builder(&mut self) -> Self::SnapshotBuilder {
        todo!()
    }

    async fn begin_receiving_snapshot(
        &mut self,
    ) -> Result<Box<<RaftShardConfig as RaftTypeConfig>::SnapshotData>, StorageError<NodeId>> {
        todo!()
    }

    async fn install_snapshot(
        &mut self,
        meta: &SnapshotMeta<NodeId, HostNode>,
        snapshot: Box<<RaftShardConfig as RaftTypeConfig>::SnapshotData>,
    ) -> Result<(), StorageError<NodeId>> {
        todo!()
    }

    async fn get_current_snapshot(
        &mut self,
    ) -> Result<Option<Snapshot<RaftShardConfig>>, StorageError<NodeId>> {
        todo!()
    }
}

#[allow(unused)]
#[async_trait]
impl RaftSnapshotBuilder<RaftShardConfig> for RaftShard {
    async fn build_snapshot(&mut self) -> Result<Snapshot<RaftShardConfig>, StorageError<NodeId>> {
        todo!()
    }
}

#[allow(unused)]
#[async_trait]
impl RaftLogReader<RaftShardConfig> for RaftShard {
    async fn try_get_log_entries<RB: RangeBounds<u64> + Clone + Debug + Send + Sync>(
        &mut self,
        _range: RB,
    ) -> Result<Vec<Entry<RaftShardConfig>>, StorageError<NodeId>> {
        todo!()
    }
}

struct RaftShardTestBuilder;

#[async_trait]
impl StoreBuilder<RaftShardConfig, RaftShard, RaftShard, TempDir> for RaftShardTestBuilder {
    async fn build(&self) -> Result<(TempDir, RaftShard, RaftShard), StorageError<NodeId>> {
        todo!()
    }
}

#[cfg(test)]
mod tests {
    use std::{
        env,
        sync::Arc,
    };

    use openraft::{
        testing::Suite,
        StorageError,
    };
    use rand::RngCore;

    use crate::{
        storage::{
            memcache::WriteBackCache,
            raft::{
                RaftShard,
                RaftShardTestBuilder,
            },
        },
        typedef::NodeId,
    };

    #[test]
    fn test_raft_shard_compliance() -> Result<(), StorageError<NodeId>> {
        let shard_id = rand::thread_rng().next_u64();
        let wbc = Arc::new(WriteBackCache::new(
            env::temp_dir().to_str().unwrap().to_string(),
        ));

        let raft_storage = RaftShard::new(shard_id, wbc);

        Suite::test_all(RaftShardTestBuilder)
    }
}
