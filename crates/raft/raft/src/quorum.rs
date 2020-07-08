// Copyright 2020 TiKV Project Authors. Licensed under Apache-2.0.

pub mod joint;
pub mod majority;

use std::collections::HashMap;
use std::fmt::{self, Debug, Display, Formatter};

/// VoteResult indicates the outcome of a vote.
#[derive(Clone, Copy, Debug, PartialEq)]
pub enum VoteResult {
    /// Pending indicates that the decision of the vote depends on future
    /// votes, i.e. neither "yes" or "no" has reached quorum yet.
    Pending,
    // Lost indicates that the quorum has voted "no".
    Lost,
    // Won indicates that the quorum has voted "yes".
    Won,
}

/// Index is a Raft log position.
#[derive(Default, Clone, Copy)]
pub struct Index {
    pub index: u64,
    pub group_id: u64,
}

impl Display for Index {
    #[inline]
    fn fmt(&self, f: &mut Formatter<'_>) -> fmt::Result {
        if self.index != u64::MAX {
            write!(f, "[{}]{}", self.group_id, self.index)
        } else {
            write!(f, "[{}]∞", self.group_id)
        }
    }
}

impl Debug for Index {
    #[inline]
    fn fmt(&self, f: &mut Formatter<'_>) -> fmt::Result {
        Display::fmt(self, f)
    }
}

pub trait AckedIndexer {
    fn acked_index(&self, voter_id: u64) -> Option<Index>;
}

pub type AckIndexer = HashMap<u64, Index>;

impl AckedIndexer for AckIndexer {
    #[inline]
    fn acked_index(&self, voter: u64) -> Option<Index> {
        self.get(&voter).cloned()
    }
}
