//! This module contains a collection of various tools to use to manipulate
//! and control messages and data associated with raft.

// Copyright 2019 TiKV Project Authors. Licensed under Apache-2.0.

use std::fmt;
use std::fmt::Write;
use std::u64;

use slog::{OwnedKVList, Record, KV};

use nova_api::raft::v1::{ConfChangeSingle, ConfChangeType, ConfState, Entry, Message};
use crate::HashSet;
use protobuf::Message as PbMessage;

use slog::{b, record_static};

/// A number to represent that there is no limit.
pub const NO_LIMIT: u64 = u64::MAX;

/// Truncates the list of entries down to a specific byte-length of
/// all entries together.
///
/// # Examples
///
/// ```
/// use nova_raft::{util::limit_size, prelude::*};
/// use nova_api::raft::v1::Entry;
///
/// let template = {
///     let mut entry = Entry::default();
///     entry.data = "*".repeat(100).into_bytes().into();
///     entry
/// };
///
/// // Make a bunch of entries that are ~100 bytes long
/// let mut entries = vec![
///     template.clone(),
///     template.clone(),
///     template.clone(),
///     template.clone(),
///     template.clone(),
/// ];
///
/// assert_eq!(entries.len(), 5);
/// limit_size(&mut entries, Some(220));
/// assert_eq!(entries.len(), 2);
///
/// // `entries` will always have at least 1 Message
/// limit_size(&mut entries, Some(0));
/// assert_eq!(entries.len(), 1);
/// ```
pub fn limit_size<T: PbMessage + Clone>(entries: &mut Vec<T>, max: Option<u64>) {
    if entries.len() <= 1 {
        return;
    }
    let max = match max {
        None | Some(NO_LIMIT) => return,
        Some(max) => max,
    };

    let mut size = 0;
    let limit = entries
        .iter()
        .take_while(|&e| {
            if size == 0 {
                size += u64::from(e.compute_size());
                return true;
            }
            size += u64::from(e.compute_size());
            size <= max
        })
        .count();

    entries.truncate(limit);
}

/// Check whether the entry is continuous to the message.
/// i.e msg's next entry index should be equal to the index of the first entry in `ents`
pub fn is_continuous_ents(msg: &Message, ents: &[Entry]) -> bool {
    if !msg.entries.is_empty() && !ents.is_empty() {
        let expected_next_idx = msg.entries.last().unwrap().index + 1;
        return expected_next_idx == ents.first().unwrap().index;
    }
    true
}

struct FormatKeyValueList {
    pub buffer: String,
}

impl slog::Serializer for FormatKeyValueList {
    fn emit_arguments(&mut self, key: slog::Key, val: &fmt::Arguments) -> slog::Result {
        if !self.buffer.is_empty() {
            write!(&mut self.buffer, ", {}: {}", key, val).unwrap();
        } else {
            write!(&mut self.buffer, "{}: {}", key, val).unwrap();
        }
        Ok(())
    }
}

pub(crate) fn format_kv_list(kv_list: &OwnedKVList) -> String {
    let mut formatter = FormatKeyValueList {
        buffer: "".to_owned(),
    };
    let record = record_static!(slog::Level::Trace, "");
    kv_list
        .serialize(
            &Record::new(&record, &format_args!(""), b!()),
            &mut formatter,
        )
        .unwrap();
    formatter.buffer
}

/// Get the majority number of given nodes count.
#[inline]
pub fn majority(total: usize) -> usize {
    (total / 2) + 1
}

/// A convenient struct that handles queries to both HashSet.
pub struct Union<'a> {
    first: &'a HashSet<u64>,
    second: &'a HashSet<u64>,
}

impl<'a> Union<'a> {
    /// Creates a union.
    pub fn new(first: &'a HashSet<u64>, second: &'a HashSet<u64>) -> Union<'a> {
        Union { first, second }
    }

    /// Checks if id shows up in either HashSet.
    #[inline]
    pub fn contains(&self, id: u64) -> bool {
        self.first.contains(&id) || self.second.contains(&id)
    }

    /// Returns an iterator iterates the distinct values in two sets.
    pub fn iter(&self) -> impl Iterator<Item = u64> + '_ {
        self.first.union(self.second).cloned()
    }

    /// Checks if union is empty.
    pub fn is_empty(&self) -> bool {
        self.first.is_empty() && self.second.is_empty()
    }

    /// Gets the count of the union.
    ///
    /// The time complexity is O(n).
    pub fn len(&self) -> usize {
        // Usually, second is empty.
        self.first.len() + self.second.len() - self.second.intersection(self.first).count()
    }
}

/// Get the approximate size of entry
#[inline]
pub fn entry_approximate_size(e: &Entry) -> usize {
    //  message Entry {
    //      EntryType entry_type = 1;
    //      uint64 term = 2;
    //      uint64 index = 3;
    //      bytes data = 4;
    //      bytes context = 6;
    //      bool sync_log = 5;(Deprecated)
    // }
    // Each field has tag(1 byte) if it's not default value.
    // Tips: x bytes can represent a value up to 1 << x*7 - 1,
    // So 1 byte => 127, 2 bytes => 16383, 3 bytes => 2097151.
    // If entry_type is normal(default), in general, the size should
    // be tag(4) + term(1) + index(2) + data(2) + context(1) = 10.
    // If entry_type is conf change, in general, the size should be
    // tag(5) + entry_type(1) + term(1) + index(2) + data(1) + context(1) = 11.
    // We choose 12 in case of large index or large data for normal entry.
    e.data.len() + e.context.len() + 12
}

/// Parses a Space-delimited sequence of operations into a slice of ConfChangeSingle.
/// The supported operations are:
/// - vn: make n a voter,
/// - ln: make n a learner,
/// - rn: remove n
pub fn parse_conf_change(s: &str) -> Result<Vec<ConfChangeSingle>, String> {
    let s = s.trim();
    if s.is_empty() {
        return Ok(vec![]);
    }
    let mut ccs = vec![];
    let splits = s.split_ascii_whitespace();
    for tok in splits {
        if tok.len() < 2 {
            return Err(format!("unknown token {}", tok));
        }
        let mut cc = ConfChangeSingle::default();
        let mut chars = tok.chars();
        cc.set_change_type(match chars.next().unwrap() {
            'v' => ConfChangeType::AddNode,
            'l' => ConfChangeType::AddLearnerNode,
            'r' => ConfChangeType::RemoveNode,
            _ => return Err(format!("unknown token {}", tok)),
        });
        cc.node_id = match chars.as_str().parse() {
            Ok(id) => id,
            Err(e) => return Err(format!("parse token {} fail: {}", tok, e)),
        };
        ccs.push(cc);
    }
    Ok(ccs)
}

/// The inverse to `parse_conf_change`.
pub fn stringify_conf_change(ccs: &[ConfChangeSingle]) -> String {
    let mut s = String::new();
    for (i, cc) in ccs.iter().enumerate() {
        if i > 0 {
            s.push(' ');
        }
        match cc.get_change_type() {
            ConfChangeType::AddNode => s.push('v'),
            ConfChangeType::AddLearnerNode => s.push('l'),
            ConfChangeType::RemoveNode => s.push('r'),
        }
        write!(&mut s, "{}", cc.node_id).unwrap();
    }
    s
}


fn eq_without_order(lhs: &[u64], rhs: &[u64]) -> bool {
    for l in lhs {
        if !rhs.contains(l) {
            return false;
        }
    }
    for r in rhs {
        if !lhs.contains(r) {
            return false;
        }
    }
    true
}

/// Returns true if the inputs describe the same configuration.
#[must_use]
pub fn conf_state_eq(lhs: &ConfState, rhs: &ConfState) -> bool {
    // The orders are different only when hash algorithm or insert orders are
    // different. In most case, only one hash algorithm is used. Insert orders
    // should be the same due to the raft protocol. So in most case, they can
    // be compared directly.
    if lhs.get_voters() == rhs.get_voters()
        && lhs.get_learners() == rhs.get_learners()
        && lhs.get_voters_outgoing() == rhs.get_voters_outgoing()
        && lhs.get_learners_next() == rhs.get_learners_next()
        && lhs.auto_leave == rhs.auto_leave
    {
        return true;
    }

    eq_without_order(lhs.get_voters(), rhs.get_voters())
        && eq_without_order(lhs.get_learners(), rhs.get_learners())
        && eq_without_order(lhs.get_voters_outgoing(), rhs.get_voters_outgoing())
        && eq_without_order(lhs.get_learners_next(), rhs.get_learners_next())
        && lhs.auto_leave == rhs.auto_leave
}
