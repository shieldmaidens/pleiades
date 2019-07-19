// Copyright 2016 PingCAP, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// See the License for the specific language governing permissions and
// limitations under the License.

// Copyright 2015 The etcd Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
use std::cmp;

use self::inflights::Inflights;
use crate::raft::INVALID_INDEX;
pub mod inflights;
pub mod progress_set;

/// The state of the progress.
#[derive(Debug, PartialEq, Clone, Copy)]
pub enum ProgressState {
    /// Whether it's probing.
    Probe,
    /// Whether it's replicating.
    Replicate,
    /// Whethers it's a snapshot.
    Snapshot,
}

impl Default for ProgressState {
    fn default() -> ProgressState {
        ProgressState::Probe
    }
}

/// The progress of catching up from a restart.
#[derive(Debug, Clone, PartialEq)]
pub struct Progress {
    /// How much state is matched.
    pub matched: u64,
    /// The next index to apply
    pub next_idx: u64,
    /// When in ProgressStateProbe, leader sends at most one replication message
    /// per heartbeat interval. It also probes actual progress of the follower.
    ///
    /// When in ProgressStateReplicate, leader optimistically increases next
    /// to the latest entry sent after sending replication message. This is
    /// an optimized state for fast replicating log entries to the follower.
    ///
    /// When in ProgressStateSnapshot, leader should have sent out snapshot
    /// before and stop sending any replication message.
    pub state: ProgressState,
    /// Paused is used in ProgressStateProbe.
    /// When Paused is true, raft should pause sending replication message to this peer.
    pub paused: bool,
    /// This field is used in ProgressStateSnapshot.
    /// If there is a pending snapshot, the pendingSnapshot will be set to the
    /// index of the snapshot. If pendingSnapshot is set, the replication process of
    /// this Progress will be paused. raft will not resend snapshot until the pending one
    /// is reported to be failed.
    pub pending_snapshot: u64,
    /// This field is used in request snapshot.
    /// If there is a pending request snapshot, this will be set to the request
    /// index of the snapshot.
    pub pending_request_snapshot: u64,

    /// This is true if the progress is recently active. Receiving any messages
    /// from the corresponding follower indicates the progress is active.
    /// RecentActive can be reset to false after an election timeout.
    pub recent_active: bool,

    /// Inflights is a sliding window for the inflight messages.
    /// When inflights is full, no more message should be sent.
    /// When a leader sends out a message, the index of the last
    /// entry should be added to inflights. The index MUST be added
    /// into inflights in order.
    /// When a leader receives a reply, the previous inflights should
    /// be freed by calling inflights.freeTo.
    pub ins: Inflights,
}

impl Progress {
    /// Creates a new progress with the given settings.
    pub fn new(next_idx: u64, ins_size: usize) -> Self {
        Progress {
            matched: 0,
            next_idx,
            state: ProgressState::default(),
            paused: false,
            pending_snapshot: 0,
            pending_request_snapshot: 0,
            recent_active: false,
            ins: Inflights::new(ins_size),
        }
    }

    fn reset_state(&mut self, state: ProgressState) {
        self.paused = false;
        self.pending_snapshot = 0;
        self.state = state;
        self.ins.reset();
    }

    pub(crate) fn reset(&mut self, next_idx: u64) {
        self.matched = 0;
        self.next_idx = next_idx;
        self.state = ProgressState::default();
        self.paused = false;
        self.pending_snapshot = 0;
        self.pending_request_snapshot = INVALID_INDEX;
        self.recent_active = false;
        debug_assert!(self.ins.cap() != 0);
        self.ins.reset();
    }

    /// Changes the progress to a probe.
    pub fn become_probe(&mut self) {
        // If the original state is ProgressStateSnapshot, progress knows that
        // the pending snapshot has been sent to this peer successfully, then
        // probes from pendingSnapshot + 1.
        if self.state == ProgressState::Snapshot {
            let pending_snapshot = self.pending_snapshot;
            self.reset_state(ProgressState::Probe);
            self.next_idx = cmp::max(self.matched + 1, pending_snapshot + 1);
        } else {
            self.reset_state(ProgressState::Probe);
            self.next_idx = self.matched + 1;
        }
    }

    /// Changes the progress to a Replicate.
    #[inline]
    pub fn become_replicate(&mut self) {
        self.reset_state(ProgressState::Replicate);
        self.next_idx = self.matched + 1;
    }

    /// Changes the progress to a snapshot.
    #[inline]
    pub fn become_snapshot(&mut self, snapshot_idx: u64) {
        self.reset_state(ProgressState::Snapshot);
        self.pending_snapshot = snapshot_idx;
    }

    /// Sets the snapshot to failure.
    #[inline]
    pub fn snapshot_failure(&mut self) {
        self.pending_snapshot = 0;
    }

    /// Unsets pendingSnapshot if Match is equal or higher than
    /// the pendingSnapshot
    #[inline]
    pub fn maybe_snapshot_abort(&self) -> bool {
        self.state == ProgressState::Snapshot && self.matched >= self.pending_snapshot
    }

    /// Returns false if the given n index comes from an outdated message.
    /// Otherwise it updates the progress and returns true.
    pub fn maybe_update(&mut self, n: u64) -> bool {
        let need_update = self.matched < n;
        if need_update {
            self.matched = n;
            self.resume();
        };

        if self.next_idx < n + 1 {
            self.next_idx = n + 1
        }

        need_update
    }

    /// Optimistically advance the index
    #[inline]
    pub fn optimistic_update(&mut self, n: u64) {
        self.next_idx = n + 1;
    }

    /// Returns false if the given index comes from an out of order message.
    /// Otherwise it decreases the progress next index to min(rejected, last)
    /// and returns true.
    pub fn maybe_decr_to(&mut self, rejected: u64, last: u64, request_snapshot: u64) -> bool {
        if self.state == ProgressState::Replicate {
            // the rejection must be stale if the progress has matched and "rejected"
            // is smaller than "match".
            // Or rejected equals to matched and request_snapshot is the INVALID_INDEX.
            if rejected < self.matched
                || (rejected == self.matched && request_snapshot == INVALID_INDEX)
            {
                return false;
            }
            if request_snapshot == INVALID_INDEX {
                self.next_idx = self.matched + 1;
            } else {
                self.pending_request_snapshot = request_snapshot;
            }
            return true;
        }

        // The rejection must be stale if "rejected" does not match next - 1.
        // Do not consider it stale if it is a request snapshot message.
        if (self.next_idx == 0 || self.next_idx - 1 != rejected)
            && request_snapshot == INVALID_INDEX
        {
            return false;
        }

        // Do not decrease next index if it's requesting snapshot.
        if request_snapshot == INVALID_INDEX {
            self.next_idx = cmp::min(rejected, last + 1);
            if self.next_idx < 1 {
                self.next_idx = 1;
            }
        } else if self.pending_request_snapshot == INVALID_INDEX {
            // Allow requesting snapshot even if it's not Replicate.
            self.pending_request_snapshot = request_snapshot;
        }
        self.resume();
        true
    }

    /// Determine whether progress is paused.
    #[inline]
    pub fn is_paused(&self) -> bool {
        match self.state {
            ProgressState::Probe => self.paused,
            ProgressState::Replicate => self.ins.full(),
            ProgressState::Snapshot => true,
        }
    }

    /// Resume progress
    #[inline]
    pub fn resume(&mut self) {
        self.paused = false;
    }

    /// Pause progress.
    #[inline]
    pub fn pause(&mut self) {
        self.paused = true;
    }

    /// Update inflight msgs and next_idx
    pub fn update_state(&mut self, last: u64) {
        match self.state {
            ProgressState::Replicate => {
                self.optimistic_update(last);
                self.ins.add(last);
            }
            ProgressState::Probe => self.pause(),
            ProgressState::Snapshot => panic!(
                "updating progress state in unhandled state {:?}",
                self.state
            ),
        }
    }
}
