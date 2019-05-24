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

/*!

## Creating a Raft node

You can use [`RawNode::new`](raw_node/struct.RawNode.html#method.new) to create the Raft node. To
create the Raft node, you need to provide a [`Storage`](storage/trait.Storage.html) component, and
a [`Config`](struct.Config.html) to the [`RawNode::new`](raw_node/struct.RawNode.html#method.new)
function.

```rust
use raft::{
    Config,
    storage::MemStorage,
    raw_node::RawNode,
};

// Select some defaults, then change what we need.
let config = Config {
    id: 1,
    ..Default::default()
};
// ... Make any configuration changes.
// After, make sure it's valid!
config.validate().unwrap();
// We'll use the built-in `MemStorage`, but you will likely want your own.
// Finally, create our Raft node!
let storage = MemStorage::new_with_conf_state((vec![1], vec![]));
let mut node = RawNode::new(&config, storage).unwrap();
// We will coax it into being the lead of a single node cluster for exploration.
node.raft.become_candidate();
node.raft.become_leader();
```

## Ticking the Raft node

Use a timer to tick the Raft node at regular intervals. See the following example using Rust
channel `recv_timeout` to drive the Raft node at least every 100ms, calling
[`tick()`](raw_node/struct.RawNode.html#method.tick) each time.

```rust
# use raft::{Config, storage::MemStorage, raw_node::RawNode};
# let config = Config { id: 1, ..Default::default() };
# let store = MemStorage::new_with_conf_state((vec![1], vec![]));
# let mut node = RawNode::new(&config, store).unwrap();
# node.raft.become_candidate();
# node.raft.become_leader();
use std::{sync::mpsc::{channel, RecvTimeoutError}, time::{Instant, Duration}};

// We're using a channel, but this could be any stream of events.
let (tx, rx) = channel();
let timeout = Duration::from_millis(100);
let mut remaining_timeout = timeout;

// Send the `tx` somewhere else...

loop {
    let now = Instant::now();

    match rx.recv_timeout(remaining_timeout) {
        Ok(()) => {
            // Let's save this for later.
            unimplemented!()
        },
        Err(RecvTimeoutError::Timeout) => (),
        Err(RecvTimeoutError::Disconnected) => unimplemented!(),
    }

    let elapsed = now.elapsed();
    if elapsed >= remaining_timeout {
        remaining_timeout = timeout;
        // We drive Raft every 100ms.
        node.tick();
    } else {
        remaining_timeout -= elapsed;
    }
#    break;
}
```

## Proposing to, and stepping the Raft node

Using the `propose` function you can drive the Raft node when the client sends a request to the
Raft server. You can call `propose` to add the request to the Raft log explicitly.

In most cases, the client needs to wait for a response for the request. For example, if the
client writes a value to a key and wants to know whether the write succeeds or not, but the
write flow is asynchronous in Raft, so the write log entry must be replicated to other followers,
then committed and at last applied to the state machine, so here we need a way to notify the client
after the write is finished.

One simple way is to use a unique ID for the client request, and save the associated callback
function in a hash map. When the log entry is applied, we can get the ID from the decoded entry,
call the corresponding callback, and notify the client.

You can call the `step` function when you receive the Raft messages from other nodes.

Here is a simple example to use `propose` and `step`:

```rust
# use raft::{Config, storage::MemStorage, raw_node::RawNode, eraftpb::Message};
# use std::{
#     sync::mpsc::{channel, RecvTimeoutError},
#     time::{Instant, Duration},
#     collections::HashMap
# };
#
# let config = Config { id: 1, ..Default::default() };
# let store = MemStorage::new_with_conf_state((vec![1], vec![]));
# let mut node = RawNode::new(&config, store).unwrap();
# node.raft.become_candidate();
# node.raft.become_leader();
#
# let (tx, rx) = channel();
# let timeout = Duration::from_millis(100);
# let mut remaining_timeout = timeout;
#
enum Msg {
    Propose {
        id: u8,
        callback: Box<Fn() + Send>,
    },
    Raft(Message),
}

// Simulate a message coming down the stream.
tx.send(Msg::Propose { id: 1, callback: Box::new(|| ()) });

let mut cbs = HashMap::new();
loop {
    let now = Instant::now();

    match rx.recv_timeout(remaining_timeout) {
        Ok(Msg::Propose { id, callback }) => {
            cbs.insert(id, callback);
            node.propose(vec![], vec![id]).unwrap();
        }
        Ok(Msg::Raft(m)) => node.step(m).unwrap(),
        Err(RecvTimeoutError::Timeout) => (),
        Err(RecvTimeoutError::Disconnected) => unimplemented!(),
    }

    let elapsed = now.elapsed();
    if elapsed >= remaining_timeout {
        remaining_timeout = timeout;
        // We drive Raft every 100ms.
        node.tick();
    } else {
        remaining_timeout -= elapsed;
    }
    break;
}
```

In the above example, we use a channel to receive the `propose` and `step` messages. We only
propose the request ID to the Raft log. In your own practice, you can embed the ID in your request
and propose the encoded binary request data.

## Processing the `Ready` State

When your Raft node is ticked and running, Raft should enter a `Ready` state. You need to first use
`has_ready` to check whether Raft is ready. If yes, use the `ready` function to get a `Ready`
state:

```rust,ignore
if !node.has_ready() {
    return;
}

// The Raft is ready, we can do something now.
let mut ready = node.ready();
```

The `Ready` state contains quite a bit of information, and you need to check and process them one
by one:

1. Check whether `snapshot` is empty or not. If not empty, it means that the Raft node has received
a Raft snapshot from the leader and we must apply the snapshot:

    ```rust,ignore
    if !raft::is_empty_snap(ready.snapshot()) {
        // This is a snapshot, we need to apply the snapshot at first.
        node.mut_store()
            .wl()
            .apply_snapshot(ready.snapshot().clone())
            .unwrap();
    }

    ```

2. Check whether `entries` is empty or not. If not empty, it means that there are newly added
entries but has not been committed yet, we must append the entries to the Raft log:

    ```rust,ignore
    if !ready.entries.is_empty() {
        // Append entries to the Raft log
        node.mut_store().wl().append(ready.entries()).unwrap();
    }

    ```

3. Check whether `hs` is empty or not. If not empty, it means that the `HardState` of the node has
changed. For example, the node may vote for a new leader, or the commit index has been increased.
We must persist the changed `HardState`:

    ```rust,ignore
    if let Some(hs) = ready.hs() {
        // Raft HardState changed, and we need to persist it.
        node.mut_store().wl().set_hardstate(hs.clone());
    }
    ```

4. Check whether `messages` is empty or not. If not, it means that the node will send messages to
other nodes. There has been an optimization for sending messages: if the node is a leader, this can
be done together with step 1 in parallel; if the node is not a leader, it needs to reply the
messages to the leader after appending the Raft entries:

    ```rust,ignore
    if !is_leader {
        // If not leader, the follower needs to reply the messages to
        // the leader after appending Raft entries.
        let msgs = ready.messages.drain(..);
        for _msg in msgs {
            // Send messages to other peers.
        }
    }
    ```

5. Check whether `committed_entires` is empty or not. If not, it means that there are some newly
committed log entries which you must apply to the state machine. Of course, after applying, you
need to update the applied index and resume `apply` later:

    ```rust,ignore
    if let Some(committed_entries) = ready.committed_entries.take() {
        let mut _last_apply_index = 0;
        for entry in committed_entries {
            // Mostly, you need to save the last apply index to resume applying
            // after restart. Here we just ignore this because we use a Memory storage.
            _last_apply_index = entry.index;

            if entry.data.is_empty() {
                // Emtpy entry, when the peer becomes Leader it will send an empty entry.
                continue;
            }

            match entry.entry_type() {
                EntryType::EntryNormal => handle_normal(entry),
                EntryType::EntryConfChange => handle_conf_change(entry),
            }
        }
    }
    ```

6. Call `advance` to prepare for the next `Ready` state.

    ```rust,ignore
    node.advance(ready);
    ```

For more information, check out an [example](examples/single_mem_node/main.rs#L113-L179).

## Arbitrary Membership Changes

> **Note:** This is an experimental feature.

When building a resilient, scalable distributed system there is a strong need to be able to change
the membership of a peer group *dynamically, without downtime.* This Raft crate supports this via
**Joint Consensus**
([Raft paper, section 6](https://web.stanford.edu/~ouster/cgi-bin/papers/raft-atc14)).

It permits resilient arbitrary dynamic membership changes. A membership change can do any or all of
the following:

* Add peer (learner or voter) *n* to the group.
* Remove peer *n* from the group.
* Remove a leader (unmanaged, via stepdown)
* Promote a learner to a voter.
* Replace a node *n* with another node *m*.

It (currently) does not:

* Allow control of the replacement leader during a stepdown.
* Optionally roll back a change during a peer group pause where the new peer group configuration
fails.
* Provide automated promotion of newly added voters from learner to voter when they are caught up.
This must be done as a two stage process for now.

> PRs to enable these are welcome! We'd love to mentor/support you through implementing it.

This means it's possible to do:

```rust
use raft::{Config, storage::MemStorage, raw_node::RawNode, eraftpb::*};
use prost::Message as ProstMsg;
let mut config = Config { id: 1, ..Default::default() };
let store = MemStorage::new_with_conf_state((vec![1, 2], vec![]));
let mut node = RawNode::new(&mut config, store).unwrap();
node.raft.become_candidate();
node.raft.become_leader();

// Call this on the leader, or send the command via a normal `MsgPropose`.
node.raft.propose_membership_change((
    // Any IntoIterator<Item=u64>.
    // Voters
    vec![1,3], // Remove 2, add 3.
    // Learners
    vec![4,5,6], // Add 4, 5, 6.
)).unwrap();
# let idx = node.raft.raft_log.last_index();

# let entry = &node.raft.raft_log.entries(idx, 1).unwrap()[0];
// ...Later when the begin entry is recieved from a `ready()` in the `entries` field...
let conf_change = ConfChange::decode(&entry.data).unwrap();
node.raft.begin_membership_change(&conf_change).unwrap();
assert!(node.raft.is_in_membership_change());
assert!(node.raft.prs().voter_ids().contains(&2));
assert!(node.raft.prs().voter_ids().contains(&3));
#
# // We hide this since the user isn't really encouraged to blindly call this, but we'd like a short
# // example.
# node.raft.raft_log.commit_to(idx);
# node.raft.commit_apply(idx);
#
# let idx = node.raft.raft_log.last_index();
# let entry = &node.raft.raft_log.entries(idx, 1).unwrap()[0];
// ...Later, when the finalize entry is recieved from a `ready()` in the `entries` field...
let conf_change = ConfChange::decode(&entry.data).unwrap();
node.raft.finalize_membership_change(&conf_change).unwrap();
assert!(!node.raft.prs().voter_ids().contains(&2));
assert!(node.raft.prs().voter_ids().contains(&3));
assert!(!node.raft.is_in_membership_change());
```

This process is a two-phase process, during the midst of it the peer group's leader is managing
**two independent, possibly overlapping peer sets**.

> **Note:** In order to maintain resiliency guarantees  (progress while a majority of both peer sets is
active), it is very important to wait until the entire peer group has exited the transition phase
before taking old, removed peers offline.

*/

#![deny(clippy::all)]
#![deny(missing_docs)]
#![recursion_limit = "128"]

#[cfg(feature = "failpoint")]
#[macro_use]
extern crate fail;

#[macro_use]
extern crate log;
#[macro_use]
extern crate quick_error;
#[macro_use]
extern crate getset;

mod config;
mod prost;
/// This module supplies the needed message types. However, it is autogenerated and thus cannot be
/// documented by field.
pub use crate::prost::eraftpb;
mod errors;
mod log_unstable;
mod progress;
#[cfg(test)]
pub mod raft;
#[cfg(not(test))]
mod raft;
mod raft_log;
pub mod raw_node;
mod read_only;
mod status;
pub mod storage;
pub mod util;

pub use self::config::Config;
pub use self::errors::{Error, Result, StorageError};
pub use self::log_unstable::Unstable;
pub use self::progress::inflights::Inflights;
pub use self::progress::progress_set::{Configuration, ProgressSet};
pub use self::progress::{Progress, ProgressState};
pub use self::raft::{vote_resp_msg_type, Raft, SoftState, StateRole, INVALID_ID, INVALID_INDEX};
pub use self::raft_log::{RaftLog, NO_LIMIT};
pub use self::raw_node::{is_empty_snap, Peer, RawNode, Ready, SnapshotStatus};
pub use self::read_only::{ReadOnlyOption, ReadState};
pub use self::status::Status;
pub use self::storage::{RaftState, Storage};

pub mod prelude {
    //! A "prelude" for crates using the `raft` crate.
    //!
    //! This prelude is similar to the standard library's prelude in that you'll
    //! almost always want to import its entire contents, but unlike the standard
    //! library's prelude you'll have to do so manually:
    //!
    //! ```
    //! use raft::prelude::*;
    //! ```
    //!
    //! The prelude may grow over time as additional items see ubiquitous use.

    pub use crate::eraftpb::{
        ConfChange, ConfChangeType, ConfState, Entry, EntryType, HardState, Message, MessageType,
        Snapshot, SnapshotMetadata,
    };

    pub use crate::config::Config;
    pub use crate::raft::Raft;

    pub use crate::storage::{RaftState, Storage};

    pub use crate::raw_node::{Peer, RawNode, Ready, SnapshotStatus};

    pub use crate::progress::Progress;

    pub use crate::status::Status;

    pub use crate::read_only::{ReadOnlyOption, ReadState};
}
