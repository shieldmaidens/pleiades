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

// Copyright 2015 CoreOS, Inc.
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

#![allow(dead_code)]
use std::cmp;
use raft::storage::Storage;
use rand::{self, Rng};
use kvproto::raftpb::{HardState, Entry, EntryType, Message, Snapshot, MessageType};
use protobuf::repeated::RepeatedField;
use raft::progress::{Progress, Inflights, ProgressState};
use raft::errors::{Result, Error, StorageError};
use std::collections::HashMap;
use raft::raft_log::{self, RaftLog};
use std::sync::Arc;


#[derive(Debug, PartialEq, Clone, Copy)]
pub enum StateRole {
    Follower,
    Candidate,
    Leader,
}

impl Default for StateRole {
    fn default() -> StateRole {
        StateRole::Follower
    }
}

pub const INVALID_ID: u64 = 0;

/// Config contains the parameters to start a raft.
#[derive(Default)]
pub struct Config {
    /// id is the identity of the local raft. ID cannot be 0.
    pub id: u64,

    /// peers contains the IDs of all nodes (including self) in
    /// the raft cluster. It should only be set when starting a new
    /// raft cluster.
    /// Restarting raft from previous configuration will panic if
    /// peers is set.
    /// peer is private and only used for testing right now.
    pub peers: Vec<u64>,

    /// ElectionTick is the number of node.tick invocations that must pass between
    /// elections. That is, if a follower does not receive any message from the
    /// leader of current term before ElectionTick has elapsed, it will become
    /// candidate and start an election. election_tick must be greater than
    /// HeartbeatTick. We suggest election_tick = 10 * HeartbeatTick to avoid
    /// unnecessary leader switching
    pub election_tick: usize,
    /// HeartbeatTick is the number of node.tick invocations that must pass between
    /// heartbeats. That is, a leader sends heartbeat messages to maintain its
    /// leadership every heartbeat ticks.
    pub heartbeat_tick: usize,

    /// Applied is the last applied index. It should only be set when restarting
    /// raft. raft will not return entries to the application smaller or equal to Applied.
    /// If Applied is unset when restarting, raft might return previous applied entries.
    /// This is a very application dependent configuration.
    pub applied: u64,

    /// MaxSizePerMsg limits the max size of each append message. Smaller value lowers
    /// the raft recovery cost(initial probing and message lost during normal operation).
    /// On the other side, it might affect the throughput during normal replication.
    /// Note: math.MaxUusize64 for unlimited, 0 for at most one entry per message.
    pub max_size_per_msg: u64,
    /// max_inflight_msgs limits the max number of in-flight append messages during optimistic
    /// replication phase. The application transportation layer usually has its own sending
    /// buffer over TCP/UDP. Setting MaxInflightMsgs to avoid overflowing that sending buffer.
    /// TODO: feedback to application to limit the proposal rate?
    pub max_inflight_msgs: usize,

    /// check_quorum specifies if the leader should check quorum activity. Leader steps down when
    /// quorum is not active for an electionTimeout.
    pub check_quorum: bool,

    /// tag is only used for logging
    pub tag: String,
}

impl Config {
    pub fn validate(&self) -> Result<()> {
        if self.id == INVALID_ID {
            return Err(Error::ConfigInvalid("invalid node id".to_owned()));
        }

        if self.heartbeat_tick == 0 {
            return Err(Error::ConfigInvalid("heartbeat tick must greater than 0".to_owned()));
        }

        if self.election_tick <= self.heartbeat_tick {
            return Err(Error::ConfigInvalid("election tick must be greater than heartbeat tick"
                .to_owned()));
        }

        if self.max_inflight_msgs == 0 {
            return Err(Error::ConfigInvalid("max inflight messages must be greater than 0"
                .to_owned()));
        }

        Ok(())
    }
}

// SoftState provides state that is useful for logging and debugging.
// The state is volatile and does not need to be persisted to the WAL.
#[derive(Default, PartialEq, Debug)]
pub struct SoftState {
    pub leader_id: u64,
    pub raft_state: StateRole,
}

#[derive(Default)]
pub struct Raft<T: Storage> {
    pub term: u64,
    pub vote: u64,

    pub id: u64,

    /// the log
    pub raft_log: RaftLog<T>,

    pub max_inflight: usize,
    pub max_msg_size: u64,
    pub prs: HashMap<u64, Progress>,

    pub state: StateRole,

    pub votes: HashMap<u64, bool>,

    pub msgs: Vec<Message>,

    /// the leader id
    pub leader_id: u64,

    /// lead_transferee is id of the leader transfer target when its value is not None.
    /// Follow the procedure defined in raft thesis 3.10.
    pub lead_transferee: Option<u64>,

    /// New configuration is ignored if there exists unapplied configuration.
    pub pending_conf: bool,

    /// number of ticks since it reached last electionTimeout when it is leader
    /// or candidate.
    /// number of ticks since it reached last electionTimeout or received a
    /// valid message from current leader when it is a follower.
    pub election_elapsed: usize,

    /// number of ticks since it reached last heartbeatTimeout.
    /// only leader keeps heartbeatElapsed.
    heartbeat_elapsed: usize,

    pub check_quorum: bool,

    heartbeat_timeout: usize,
    election_timeout: usize,

    // randomized_election_timeout is a random number between
    // [election_timeout, 2 * election_timeout - 1]. It gets reset
    // when raft changes its state to follower or candidate.
    randomized_election_timeout: usize,

    /// Will be called when step** is about to be called.
    /// return false will skip step**.
    pub allow_step: bool,

    /// tag is only used for logging
    tag: String,
}

fn new_progress(next_idx: u64, ins_size: usize) -> Progress {
    Progress {
        next_idx: next_idx,
        ins: Inflights::new(ins_size),
        ..Default::default()
    }
}

fn new_message(to: u64, field_type: MessageType, from: Option<u64>) -> Message {
    let mut m = Message::new();
    m.set_to(to);
    if let Some(id) = from {
        m.set_from(id);
    }
    m.set_msg_type(field_type);
    m
}

impl<T: Storage> Raft<T> {
    pub fn new(c: &Config, store: Arc<T>) -> Raft<T> {
        c.validate().expect("configuration is invalid");
        let rs = store.initial_state().expect("");
        let raft_log = RaftLog::new(store);
        let mut peers: &[u64] = &c.peers;
        if !rs.conf_state.get_nodes().is_empty() {
            if !peers.is_empty() {
                // TODO: the peers argument is always nil except in
                // tests; the argument should be removed and these tests should be
                // updated to specify their nodes through a snap
                panic!("cannot specify both new(peers) and ConfState.Nodes")
            }
            peers = rs.conf_state.get_nodes();
        }
        let mut r = Raft {
            id: c.id,
            raft_log: raft_log,
            max_inflight: c.max_inflight_msgs,
            max_msg_size: c.max_size_per_msg,
            prs: HashMap::with_capacity(peers.len()),
            state: StateRole::Follower,
            check_quorum: c.check_quorum,
            heartbeat_timeout: c.heartbeat_tick,
            election_timeout: c.election_tick,
            votes: Default::default(),
            msgs: Default::default(),
            leader_id: Default::default(),
            lead_transferee: None,
            term: Default::default(),
            election_elapsed: Default::default(),
            pending_conf: Default::default(),
            allow_step: true,
            vote: Default::default(),
            heartbeat_elapsed: Default::default(),
            randomized_election_timeout: 0,
            tag: c.tag.to_owned(),
        };
        for p in peers {
            r.prs.insert(*p, new_progress(1, r.max_inflight));
        }
        if rs.hard_state != HardState::new() {
            r.load_state(rs.hard_state);
        }
        if c.applied > 0 {
            r.raft_log.applied_to(c.applied);
        }
        let term = r.term;
        r.become_follower(term, INVALID_ID);
        info!("{} newRaft {} [peers: {:?}, term: {:?}, commit: {}, applied: {}, last_index: {}, \
               last_term: {}]",
              r.tag,
              r.id,
              r.nodes(),
              r.term,
              r.raft_log.committed,
              r.raft_log.get_applied(),
              r.raft_log.last_index(),
              r.raft_log.last_term());
        r
    }

    pub fn get_store(&self) -> Arc<T> {
        self.raft_log.get_store()
    }

    fn has_leader(&self) -> bool {
        self.leader_id != INVALID_ID
    }

    pub fn soft_state(&self) -> SoftState {
        SoftState {
            leader_id: self.leader_id,
            raft_state: self.state,
        }
    }

    pub fn hard_state(&self) -> HardState {
        let mut hs = HardState::new();
        hs.set_term(self.term);
        hs.set_vote(self.vote);
        hs.set_commit(self.raft_log.committed);
        hs
    }

    fn quorum(&self) -> usize {
        self.prs.len() / 2 + 1
    }

    pub fn get_election_timeout(&self) -> usize {
        self.election_timeout
    }

    pub fn get_heartbeat_timeout(&self) -> usize {
        self.heartbeat_timeout
    }

    pub fn nodes(&self) -> Vec<u64> {
        let mut nodes = Vec::with_capacity(self.prs.len());
        nodes.extend(self.prs.keys());
        nodes.sort();
        nodes
    }

    // send persists state to stable storage and then sends to its mailbox.
    fn send(&mut self, mut m: Message) {
        m.set_from(self.id);
        // do not attach term to MsgPropose
        // proposals are a way to forward to the leader and
        // should be treated as local message.
        if m.get_msg_type() != MessageType::MsgPropose {
            m.set_term(self.term);
        }
        self.msgs.push(m);
    }

    fn prepare_send_snapshot(&mut self, m: &mut Message, to: u64) -> bool {
        let pr = self.prs.get_mut(&to).unwrap();
        if !pr.recent_active {
            debug!("{} ignore sending snapshot to {} since it is not recently active",
                   self.tag,
                   to);
            return false;
        }

        m.set_msg_type(MessageType::MsgSnapshot);
        let snapshot_r = self.raft_log.snapshot();
        if let Err(e) = snapshot_r {
            if e == Error::Store(StorageError::SnapshotTemporarilyUnavailable) {
                debug!("{} {} failed to send snapshot to {} because snapshot is termporarily \
                        unavailable",
                       self.tag,
                       self.id,
                       to);
                return false;
            }
            panic!("unexpected error: {:?}", e);
        }
        let snapshot = snapshot_r.unwrap();
        if snapshot.get_metadata().get_index() == 0 {
            panic!("need non-empty snapshot");
        }
        let (sindex, sterm) = (snapshot.get_metadata().get_index(),
                               snapshot.get_metadata().get_term());
        m.set_snapshot(snapshot);
        debug!("{} {} [firstindex: {}, commit: {}] sent snapshot[index: {}, term: {}] to {} \
                [{:?}]",
               self.tag,
               self.id,
               self.raft_log.first_index(),
               self.raft_log.committed,
               sindex,
               sterm,
               to,
               pr);
        pr.become_snapshot(sindex);
        debug!("{} {} paused sending replication messages to {} [{:?}]",
               self.tag,
               self.id,
               to,
               pr);
        true
    }

    fn prepare_send_entries(&mut self, m: &mut Message, to: u64, term: u64, ents: Vec<Entry>) {
        let pr = self.prs.get_mut(&to).unwrap();
        m.set_msg_type(MessageType::MsgAppend);
        m.set_index(pr.next_idx - 1);
        m.set_log_term(term);
        m.set_entries(RepeatedField::from_vec(ents));
        m.set_commit(self.raft_log.committed);
        if !m.get_entries().is_empty() {
            match pr.state {
                ProgressState::Replicate => {
                    let last = m.get_entries().last().unwrap().get_index();
                    pr.optimistic_update(last);
                    pr.ins.add(last);
                }
                ProgressState::Probe => pr.pause(),
                _ => {
                    panic!("{} {} is sending append in unhandled state {:?}",
                           self.tag,
                           self.id,
                           pr.state)
                }
            }
        }
    }

    // send_append sends RPC, with entries to the given peer.
    pub fn send_append(&mut self, to: u64) {
        let (term, ents) = {
            let pr = self.prs.get(&to).unwrap();
            if pr.is_paused() {
                return;
            }
            (self.raft_log.term(pr.next_idx - 1),
             self.raft_log.entries(pr.next_idx, self.max_msg_size))
        };
        let mut m = Message::new();
        m.set_to(to);
        if term.is_err() || ents.is_err() {
            // send snapshot if we failed to get term or entries
            if !self.prepare_send_snapshot(&mut m, to) {
                return;
            }
        } else {
            self.prepare_send_entries(&mut m, to, term.unwrap(), ents.unwrap());
        }
        self.send(m);
    }

    // send_heartbeat sends an empty MsgAppend
    fn send_heartbeat(&mut self, to: u64) {
        // Attach the commit as min(to.matched, self.raft_log.committed).
        // When the leader sends out heartbeat message,
        // the receiver(follower) might not be matched with the leader
        // or it might not have all the committed entries.
        // The leader MUST NOT forward the follower's commit to
        // an unmatched index.
        let mut m = Message::new();
        m.set_to(to);
        m.set_msg_type(MessageType::MsgHeartbeat);
        let commit = cmp::min(self.prs.get(&to).unwrap().matched, self.raft_log.committed);
        m.set_commit(commit);
        self.send(m);
    }

    // bcast_append sends RPC, with entries to all peers that are not up-to-date
    // according to the progress recorded in r.prs.
    pub fn bcast_append(&mut self) {
        // TODO: avoid copy
        let ids: Vec<_> = self.prs.keys().cloned().collect();
        for id in ids {
            if id == self.id {
                continue;
            }
            self.send_append(id);
        }
    }

    // bcast_heartbeat sends RPC, without entries to all the peers.
    pub fn bcast_heartbeat(&mut self) {
        // TODO: avoid copy
        let ids: Vec<_> = self.prs.keys().cloned().collect();
        for id in ids {
            if id == self.id {
                continue;
            }
            self.send_heartbeat(id);
            self.prs.get_mut(&id).unwrap().resume()
        }
    }

    // maybe_commit attempts to advance the commit index. Returns true if
    // the commit index changed (in which case the caller should call
    // r.bcast_append).
    pub fn maybe_commit(&mut self) -> bool {
        // TODO: optimize
        let mut mis = Vec::with_capacity(self.prs.len());
        for p in self.prs.values() {
            mis.push(p.matched);
        }
        // reverse sort
        mis.sort_by(|a, b| b.cmp(a));
        let mci = mis[self.quorum() - 1];
        let term = self.term;
        self.raft_log.maybe_commit(mci, term)
    }

    pub fn reset(&mut self, term: u64) {
        if self.term != term {
            self.term = term;
            self.vote = INVALID_ID;
        }
        self.leader_id = INVALID_ID;
        self.election_elapsed = 0;
        self.heartbeat_elapsed = 0;
        self.reset_randomized_election_timeout();

        self.abort_leader_transfer();

        self.votes = HashMap::new();
        let (last_index, max_inflight) = (self.raft_log.last_index(), self.max_inflight);
        let self_id = self.id;
        for (id, p) in &mut self.prs {
            *p = new_progress(last_index + 1, max_inflight);
            if id == &self_id {
                p.matched = last_index;
            }
        }
        self.pending_conf = false;
    }

    pub fn append_entry(&mut self, es: &mut [Entry]) {
        let li = self.raft_log.last_index();
        for (i, e) in es.iter_mut().enumerate() {
            e.set_term(self.term);
            e.set_index(li + 1 + i as u64);
        }
        self.raft_log.append(es);
        self.prs.get_mut(&self.id).unwrap().maybe_update(self.raft_log.last_index());
        // Regardless of maybe_commit's return, our caller will call bcastAppend.
        self.maybe_commit();
    }

    pub fn tick(&mut self) {
        match self.state {
            StateRole::Candidate | StateRole::Follower => self.tick_election(),
            StateRole::Leader => self.tick_heartbeat(),
        }
    }

    // tick_election is run by followers and candidates after self.election_timeout.
    // TODO: revoke pub when there is a better way to test.
    pub fn tick_election(&mut self) {
        if !self.promotable() {
            self.election_elapsed = 0;
            return;
        }
        self.election_elapsed += 1;
        if self.pass_election_timeout() {
            self.election_elapsed = 0;
            let m = new_message(INVALID_ID, MessageType::MsgHup, Some(self.id));
            self.step(m).is_ok();
        }
    }

    // tick_heartbeat is run by leaders to send a MsgBeat after self.heartbeat_timeout.
    fn tick_heartbeat(&mut self) {
        self.heartbeat_elapsed += 1;
        self.election_elapsed += 1;

        if self.election_elapsed >= self.election_timeout {
            self.election_elapsed = 0;
            if self.check_quorum {
                let m = new_message(INVALID_ID, MessageType::MsgCheckQuorum, Some(self.id));
                self.step(m).is_ok();
            }
            if self.state == StateRole::Leader && self.lead_transferee.is_some() {
                self.abort_leader_transfer()
            }
        }

        if self.state != StateRole::Leader {
            return;
        }

        if self.heartbeat_elapsed >= self.heartbeat_timeout {
            self.heartbeat_elapsed = 0;
            let m = new_message(INVALID_ID, MessageType::MsgBeat, Some(self.id));
            self.step(m).is_ok();
        }
    }

    pub fn become_follower(&mut self, term: u64, leader_id: u64) {
        self.reset(term);
        self.leader_id = leader_id;
        self.state = StateRole::Follower;
        info!("{} {} became follower at term {}",
              self.tag,
              self.id,
              self.term);
    }

    // TODO: revoke pub when there is a better way to test.
    pub fn become_candidate(&mut self) {
        assert!(self.state != StateRole::Leader,
                "invalid transition [leader -> candidate]");
        let term = self.term + 1;
        self.reset(term);
        let id = self.id;
        self.vote = id;
        self.state = StateRole::Candidate;
        info!("{} {} became candidate at term {}",
              self.tag,
              self.id,
              self.term);
    }

    // TODO: revoke pub when there is a better way to test.
    pub fn become_leader(&mut self) {
        assert!(self.state != StateRole::Follower,
                "invalid transition [follower -> leader]");
        let term = self.term;
        self.reset(term);
        self.leader_id = self.id;
        self.state = StateRole::Leader;
        let begin = self.raft_log.committed + 1;
        let ents = self.raft_log
            .entries(begin, raft_log::NO_LIMIT)
            .expect("unexpected error getting uncommitted entries");
        for e in ents {
            if e.get_entry_type() != EntryType::EntryConfChange {
                continue;
            }
            assert!(!self.pending_conf,
                    "unexpected double uncommitted config entry");
            self.pending_conf = true;
        }
        self.append_entry(&mut [Entry::new()]);
        info!("{} {} became leader at term {}",
              self.tag,
              self.id,
              self.term);
    }

    fn campaign(&mut self) {
        self.become_candidate();
        let id = self.id;
        let poll_res = self.poll(id, true);
        if self.quorum() == poll_res {
            self.become_leader();
            return;
        }
        let ids: Vec<_> = self.prs.keys().cloned().collect();
        for id in ids {
            if id == self.id {
                continue;
            }
            info!("{} {} [logterm: {}, index: {}] sent vote request to {} at term {}",
                  self.tag,
                  self.id,
                  self.raft_log.last_term(),
                  self.raft_log.last_index(),
                  id,
                  self.term);
            let mut m = new_message(id, MessageType::MsgRequestVote, None);
            m.set_index(self.raft_log.last_index());
            m.set_log_term(self.raft_log.last_term());
            self.send(m);
        }
    }

    fn poll(&mut self, id: u64, v: bool) -> usize {
        if v {
            info!("{} {} received vote from {} at term {}",
                  self.tag,
                  self.id,
                  id,
                  self.term)
        } else {
            info!("{} {} received vote rejection from {} at term {}",
                  self.tag,
                  self.id,
                  id,
                  self.term)
        }
        self.votes.entry(id).or_insert(v);
        self.votes.values().filter(|x| **x).count()
    }

    pub fn step(&mut self, m: Message) -> Result<()> {
        if m.get_msg_type() == MessageType::MsgHup {
            if self.state == StateRole::Leader {
                debug!("{} {} ignoring MsgHup because already leader",
                       self.tag,
                       self.id);
            } else {
                info!("{} {} is starting a new election at term {}",
                      self.tag,
                      self.id,
                      self.term);
                self.campaign();
            }
            return Ok(());
        }
        if m.get_msg_type() == MessageType::MsgTransferLeader && self.state != StateRole::Leader {
            debug!("{} {} [term {} state {:?}] ignoring MsgTransferLeader to {}",
                   self.tag,
                   self.id,
                   self.term,
                   self.state,
                   m.get_from());
        }

        if m.get_term() == 0 {
            // local message
        } else if m.get_term() > self.term {
            let leader_id = if m.get_msg_type() == MessageType::MsgRequestVote {
                INVALID_ID
            } else {
                m.get_from()
            };
            info!("{} {} [term: {}] received a {:?} message with higher term from {} [term: {}]",
                  self.tag,
                  self.id,
                  self.term,
                  m.get_msg_type(),
                  m.get_from(),
                  m.get_term());
            self.become_follower(m.get_term(), leader_id);
        } else if m.get_term() < self.term {
            // ignore
            info!("{} {} [term: {}] ignored a {:?} message with lower term from {} [term: {}]",
                  self.tag,
                  self.id,
                  self.term,
                  m.get_msg_type(),
                  m.get_from(),
                  m.get_term());
            return Ok(());
        }

        assert!(self.allow_step);
        match self.state {
            StateRole::Candidate => self.step_candidate(m),
            StateRole::Follower => self.step_follower(m),
            StateRole::Leader => self.step_leader(m),
        }
        Ok(())
    }

    fn handle_append_response(&mut self,
                              m: &Message,
                              old_paused: &mut bool,
                              send_append: &mut bool,
                              maybe_commit: &mut bool) {
        self.prs.get_mut(&m.get_from()).unwrap().recent_active = true;
        if m.get_reject() {
            let pr = self.prs.get_mut(&m.get_from()).unwrap();
            debug!("{} {} received msgAppend rejection(lastindex: {}) from {} for index {}",
                   self.tag,
                   self.id,
                   m.get_reject_hint(),
                   m.get_from(),
                   m.get_index());
            if pr.maybe_decr_to(m.get_index(), m.get_reject_hint()) {
                debug!("{} {} decreased progress of {} to [{:?}]",
                       self.tag,
                       self.id,
                       m.get_from(),
                       pr);
                if pr.state == ProgressState::Replicate {
                    pr.become_probe();
                }
                *send_append = true;
            }
            return;
        }

        {
            let pr = self.prs.get_mut(&m.get_from()).unwrap();
            *old_paused = pr.is_paused();
            if !pr.maybe_update(m.get_index()) {
                return;
            }
        }

        // Transfer leadership is in progress.
        if let Some(lead_transferee) = self.lead_transferee {
            if m.get_from() == lead_transferee &&
               self.prs.get_mut(&m.get_from()).unwrap().matched == self.raft_log.last_index() {
                info!("{} {} sent MsgTimeoutNow to {} after received MsgAppResp",
                      self.tag,
                      self.id,
                      m.get_from());
                self.send_timeout_now(m.get_from());
            }
        }

        let pr = self.prs.get_mut(&m.get_from()).unwrap();
        match pr.state {
            ProgressState::Probe => pr.become_replicate(),
            ProgressState::Snapshot => {
                if !pr.maybe_snapshot_abort() {
                    return;
                }
                debug!("{} {} snapshot aborted, resumed sending replication messages to {} \
                        [{:?}]",
                       self.tag,
                       self.id,
                       m.get_from(),
                       pr);
                pr.become_probe();
            }
            ProgressState::Replicate => pr.ins.free_to(m.get_index()),
        }
        *maybe_commit = true;
    }

    fn handle_transfer_leader(&mut self, m: &Message) {
        let lead_transferee = m.get_from();
        let last_lead_transferee = self.lead_transferee;
        if last_lead_transferee.is_some() {
            if last_lead_transferee.unwrap() == lead_transferee {
                info!("{} {} [term {}] transfer leadership to {} is in progress, ignores request \
                       to same node {}",
                      self.tag,
                      self.id,
                      self.term,
                      lead_transferee,
                      lead_transferee);
                return;
            }
            self.abort_leader_transfer();
            info!("{} {} [term {}] abort previous transferring leadership to {}",
                  self.tag,
                  self.id,
                  self.term,
                  last_lead_transferee.unwrap());
        }
        if lead_transferee == self.id {
            debug!("{} {} is already leader. Ignored transferring leadership to self",
                   self.tag,
                   self.id);
            return;
        }
        // Transfer leadership to third party.
        info!("{} {} [term {}] starts to transfer leadership to {}",
              self.tag,
              self.id,
              self.term,
              lead_transferee);
        // Transfer leadership should be finished in one electionTimeout
        // so reset r.electionElapsed.
        self.election_elapsed = 0;
        self.lead_transferee = Some(lead_transferee);
        if self.prs.get(&m.get_from()).unwrap().matched == self.raft_log.last_index() {
            self.send_timeout_now(lead_transferee);
            info!("{} {} sends MsgTimeoutNow to {} immediately as {} already has up-to-date log",
                  self.tag,
                  self.id,
                  lead_transferee,
                  lead_transferee);
        } else {
            self.send_append(lead_transferee);
        }
    }

    fn handle_snapshot_status(&mut self, m: &Message) {
        let pr = self.prs.get_mut(&m.get_from()).unwrap();
        if m.get_reject() {
            pr.snapshot_failure();
            pr.become_probe();
            debug!("{} {} snapshot failed, resumed sending replication messages to {} [{:?}]",
                   self.tag,
                   self.id,
                   m.get_from(),
                   pr);
        } else {
            pr.become_probe();
            debug!("{} {} snapshot succeeded, resumed sending replication messages to {} [{:?}]",
                   self.tag,
                   self.id,
                   m.get_from(),
                   pr);
        }
        // If snapshot finish, wait for the msgAppResp from the remote node before sending
        // out the next msgAppend.
        // If snapshot failure, wait for a heartbeat interval before next try
        pr.pause();
    }

    /// check message's progress to decide which action should be taken.
    fn check_message_with_progress(&mut self,
                                   m: &Message,
                                   send_append: &mut bool,
                                   old_paused: &mut bool,
                                   maybe_commit: &mut bool) {
        if !self.prs.contains_key(&m.get_from()) {
            debug!("{} no progress available for {}", self.tag, m.get_from());
            return;
        }
        match m.get_msg_type() {
            MessageType::MsgAppendResponse => {
                self.handle_append_response(m, old_paused, send_append, maybe_commit);
            }
            MessageType::MsgHeartbeatResponse => {
                let pr = self.prs.get_mut(&m.get_from()).unwrap();
                pr.recent_active = true;

                // free one slot for the full inflights window to allow progress.
                if pr.state == ProgressState::Replicate && pr.ins.full() {
                    pr.ins.free_first_one();
                }
                if pr.matched < self.raft_log.last_index() {
                    *send_append = true;
                }
            }
            MessageType::MsgSnapStatus => {
                if self.prs[&m.get_from()].state != ProgressState::Snapshot {
                    return;
                }
                self.handle_snapshot_status(m);
            }
            MessageType::MsgUnreachable => {
                let pr = self.prs.get_mut(&m.get_from()).unwrap();
                // During optimistic replication, if the remote becomes unreachable,
                // there is huge probability that a MsgAppend is lost.
                if pr.state == ProgressState::Replicate {
                    pr.become_probe();
                }
                debug!("{} {} failed to send message to {} because it is unreachable [{:?}]",
                       self.tag,
                       self.id,
                       m.get_from(),
                       pr);
            }
            MessageType::MsgTransferLeader => {
                self.handle_transfer_leader(m);
            }
            _ => {}
        }
    }

    fn log_vote_reject(&self, m: &Message) {
        info!("{} {} [logterm: {}, index: {}, vote: {}] rejected vote from {} [logterm: {}, \
               index: {}] at term {}",
              self.tag,
              self.id,
              self.raft_log.last_term(),
              self.raft_log.last_index(),
              self.vote,
              m.get_from(),
              m.get_log_term(),
              m.get_index(),
              self.term);
    }

    fn log_vote_approve(&self, m: &Message) {
        info!("{} {} [logterm: {}, index: {}, vote: {}] voted for {} [logterm: {}, index: {}] \
               at term {}",
              self.tag,
              self.id,
              self.raft_log.last_term(),
              self.raft_log.last_index(),
              self.vote,
              m.get_from(),
              m.get_log_term(),
              m.get_index(),
              self.term);
    }

    fn step_leader(&mut self, mut m: Message) {
        // These message types do not require any progress for m.From.
        match m.get_msg_type() {
            MessageType::MsgBeat => {
                self.bcast_heartbeat();
                return;
            }
            MessageType::MsgCheckQuorum => {
                if !self.check_quorum_active() {
                    warn!("{} {} stepped down to follower since quorum is not active",
                          self.tag,
                          self.id);
                    let term = self.term;
                    self.become_follower(term, INVALID_ID);
                }
                return;
            }
            MessageType::MsgPropose => {
                if m.get_entries().is_empty() {
                    panic!("{} {} stepped empty MsgProp", self.tag, self.id);
                }
                if !self.prs.contains_key(&self.id) {
                    // If we are not currently a member of the range (i.e. this node
                    // was removed from the configuration while serving as leader),
                    // drop any new proposals.
                    return;
                }
                if self.lead_transferee.is_some() {
                    debug!("{} {} [term {}] transfer leadership to {} is in progress; dropping \
                            proposal",
                           self.tag,
                           self.id,
                           self.term,
                           self.lead_transferee.unwrap());
                    return;
                }

                for e in m.mut_entries().iter_mut() {
                    if e.get_entry_type() == EntryType::EntryConfChange {
                        if self.pending_conf {
                            *e = Entry::new();
                            e.set_entry_type(EntryType::EntryNormal);
                        }
                        self.pending_conf = true;
                    }
                }
                self.append_entry(&mut m.mut_entries());
                self.bcast_append();
                return;
            }
            MessageType::MsgRequestVote => {
                self.log_vote_reject(&m);
                let mut to_sent = Message::new();
                to_sent.set_to(m.get_to());
                to_sent.set_msg_type(MessageType::MsgRequestVoteResponse);
                to_sent.set_reject(true);
                self.send(to_sent);
                return;
            }
            _ => {}
        }

        let mut send_append = false;
        let mut maybe_commit = false;
        let mut old_paused = false;
        self.check_message_with_progress(&m, &mut send_append, &mut old_paused, &mut maybe_commit);
        if maybe_commit {
            if self.maybe_commit() {
                self.bcast_append();
            } else if old_paused {
                // update() reset the wait state on this node. If we had delayed sending
                // an update before, send it now.
                send_append = true;
            }
        }

        if send_append {
            self.send_append(m.get_from());
        }
    }

    fn step_candidate(&mut self, m: Message) {
        let term = self.term;
        match m.get_msg_type() {
            MessageType::MsgPropose => {
                info!("{} {} no leader at term {}; dropping proposal",
                      self.tag,
                      self.id,
                      term);
                return;
            }
            MessageType::MsgAppend => {
                self.become_follower(term, m.get_from());
                self.handle_append_entries(m);
            }
            MessageType::MsgHeartbeat => {
                self.become_follower(term, m.get_from());
                self.handle_heartbeat(m);
            }
            MessageType::MsgSnapshot => {
                self.become_follower(term, m.get_from());
                self.handle_snapshot(m);
            }
            MessageType::MsgRequestVote => {
                self.log_vote_reject(&m);
                let t = MessageType::MsgRequestVoteResponse;
                let mut to_send = new_message(m.get_from(), t, None);
                to_send.set_reject(true);
                self.send(to_send);
            }
            MessageType::MsgRequestVoteResponse => {
                let gr = self.poll(m.get_from(), !m.get_reject());
                let quorum = self.quorum();
                info!("{} {} [quorum:{}] has received {} votes and {} vote rejections",
                      self.tag,
                      self.id,
                      quorum,
                      gr,
                      self.votes.len() - gr);
                if quorum == gr {
                    self.become_leader();
                    self.bcast_append();
                } else if quorum == self.votes.len() - gr {
                    self.become_follower(term, INVALID_ID);
                }
            }
            MessageType::MsgTimeoutNow => {
                debug!("{} {} [term {} state {:?}] ignored MsgTimeoutNow from {}",
                       self.tag,
                       self.id,
                       self.term,
                       self.state,
                       m.get_from())
            }
            _ => {}
        }
    }

    fn step_follower(&mut self, mut m: Message) {
        let term = self.term;
        match m.get_msg_type() {
            MessageType::MsgPropose => {
                if self.leader_id == INVALID_ID {
                    info!("{} {} no leader at term {}; dropping proposal",
                          self.tag,
                          self.id,
                          term);
                    return;
                }
                m.set_to(self.leader_id);
                self.send(m);
            }
            MessageType::MsgAppend => {
                self.election_elapsed = 0;
                self.leader_id = m.get_from();
                self.handle_append_entries(m);
            }
            MessageType::MsgHeartbeat => {
                self.election_elapsed = 0;
                self.leader_id = m.get_from();
                self.handle_heartbeat(m);
            }
            MessageType::MsgSnapshot => {
                self.election_elapsed = 0;
                self.handle_snapshot(m);
            }
            MessageType::MsgRequestVote => {
                let t = MessageType::MsgRequestVoteResponse;
                if (self.vote == INVALID_ID || self.vote == m.get_from()) &&
                   self.raft_log.is_up_to_date(m.get_index(), m.get_log_term()) {
                    self.log_vote_approve(&m);
                    self.election_elapsed = 0;
                    self.vote = m.get_from();
                    let mut to_send = new_message(m.get_from(), t, None);
                    to_send.set_reject(false);
                    self.send(to_send);
                } else {
                    self.log_vote_reject(&m);
                    let mut to_send = new_message(m.get_from(), t, None);
                    to_send.set_reject(true);
                    self.send(to_send);
                }
            }
            MessageType::MsgTimeoutNow => {
                info!("{} {} [term {}] received MsgTimeoutNow from {} and starts an election to \
                       get leadership.",
                      self.tag,
                      self.id,
                      self.term,
                      m.get_from());
                self.campaign();
            }
            _ => {}
        }
    }

    // TODO: revoke pub when there is a better way to test.
    pub fn handle_append_entries(&mut self, m: Message) {
        if m.get_index() < self.raft_log.committed {
            let mut to_send = Message::new();
            to_send.set_to(m.get_from());
            to_send.set_msg_type(MessageType::MsgAppendResponse);
            to_send.set_index(self.raft_log.committed);
            self.send(to_send);
            return;
        }
        let mut to_send = Message::new();
        to_send.set_to(m.get_from());
        to_send.set_msg_type(MessageType::MsgAppendResponse);
        match self.raft_log.maybe_append(m.get_index(),
                                         m.get_log_term(),
                                         m.get_commit(),
                                         m.get_entries()) {
            Some(mlast_index) => {
                to_send.set_index(mlast_index);
                self.send(to_send);
            }
            None => {
                debug!("{} {} [logterm: {}, index: {}] rejected msgApp [logterm: {}, index: {}] \
                        from {}",
                       self.tag,
                       self.id,
                       self.raft_log.zero_term_on_err_compacted(self.raft_log.term(m.get_index())),
                       m.get_index(),
                       m.get_log_term(),
                       m.get_index(),
                       m.get_from());
                to_send.set_index(m.get_index());
                to_send.set_reject(true);
                to_send.set_reject_hint(self.raft_log.last_index());
                self.send(to_send);
            }
        }
    }

    // TODO: revoke pub when there is a better way to test.
    pub fn handle_heartbeat(&mut self, m: Message) {
        self.raft_log.commit_to(m.get_commit());
        let mut to_send = Message::new();
        to_send.set_to(m.get_from());
        to_send.set_msg_type(MessageType::MsgHeartbeatResponse);
        self.send(to_send);
    }

    fn handle_snapshot(&mut self, mut m: Message) {
        let (sindex, sterm) = (m.get_snapshot().get_metadata().get_index(),
                               m.get_snapshot().get_metadata().get_term());
        if self.restore(m.take_snapshot()) {
            info!("{} {} [commit: {}] restored snapshot [index: {}, term: {}]",
                  self.tag,
                  self.id,
                  self.raft_log.committed,
                  sindex,
                  sterm);
            let mut to_send = Message::new();
            to_send.set_to(m.get_from());
            to_send.set_msg_type(MessageType::MsgAppendResponse);
            to_send.set_index(self.raft_log.last_index());
            self.send(to_send);
        } else {
            info!("{} {} [commit: {}] ignored snapshot [index: {}, term: {}]",
                  self.tag,
                  self.id,
                  self.raft_log.committed,
                  sindex,
                  sterm);
            let mut to_send = Message::new();
            to_send.set_to(m.get_from());
            to_send.set_msg_type(MessageType::MsgAppendResponse);
            to_send.set_index(self.raft_log.committed);
            self.send(to_send);
        }
    }

    fn restore_raft(&mut self, snap: &Snapshot) -> Option<bool> {
        let meta = snap.get_metadata();
        if self.raft_log.match_term(meta.get_index(), meta.get_term()) {
            info!("{} {} [commit: {}, lastindex: {}, lastterm: {}] fast-forwarded commit to \
                   snapshot [index: {}, term: {}]",
                  self.tag,
                  self.id,
                  self.raft_log.committed,
                  self.raft_log.last_index(),
                  self.raft_log.last_term(),
                  meta.get_index(),
                  meta.get_term());
            self.raft_log.commit_to(meta.get_index());
            return Some(false);
        }

        info!("{} {} [commit: {}, lastindex: {}, lastterm: {}] starts to restore snapshot \
               [index: {}, term: {}]",
              self.tag,
              self.id,
              self.raft_log.committed,
              self.raft_log.last_index(),
              self.raft_log.last_term(),
              meta.get_index(),
              meta.get_term());
        self.prs = HashMap::with_capacity(meta.get_conf_state().get_nodes().len());
        for &n in meta.get_conf_state().get_nodes() {
            let next_idx = self.raft_log.last_index() + 1;
            let matched = if n == self.id {
                next_idx - 1
            } else {
                0
            };
            self.set_progress(n, matched, next_idx);
            info!("{} {} restored progress of {} [{:?}]",
                  self.tag,
                  self.id,
                  n,
                  self.prs[&n]);
        }
        None
    }

    // restore recovers the state machine from a snapshot. It restores the log and the
    // configuration of state machine.
    pub fn restore(&mut self, snap: Snapshot) -> bool {
        if snap.get_metadata().get_index() < self.raft_log.committed {
            return false;
        }
        if let Some(b) = self.restore_raft(&snap) {
            return b;
        }
        self.raft_log.restore(snap);
        true
    }

    // promotable indicates whether state machine can be promoted to leader,
    // which is true when its own id is in progress list.
    pub fn promotable(&self) -> bool {
        self.prs.contains_key(&self.id)
    }

    pub fn add_node(&mut self, id: u64) {
        if self.prs.contains_key(&id) {
            // Ignore any redundant addNode calls (which can happen because the
            // initial bootstrapping entries are applied twice).
            return;
        }
        let last_index = self.raft_log.last_index();
        self.set_progress(id, 0, last_index + 1);
        self.pending_conf = false;
    }

    pub fn remove_node(&mut self, id: u64) {
        self.del_progress(id);
        self.pending_conf = false;

        // do not try to commit or abort transferring if there is no nodes in the cluster.
        if self.prs.is_empty() {
            return;
        }

        // The quorum size is now smaller, so see if any pending entries can
        // be committed.
        if self.maybe_commit() {
            self.bcast_append();
        }
        // If the removed node is the lead_transferee, then abort the leadership transferring.
        if self.state == StateRole::Leader && self.lead_transferee == Some(id) {
            self.abort_leader_transfer()
        }
    }

    pub fn reset_pending_conf(&mut self) {
        self.pending_conf = false;
    }

    pub fn set_progress(&mut self, id: u64, matched: u64, next_idx: u64) {
        let mut p = new_progress(next_idx, self.max_inflight);
        p.matched = matched;
        self.prs.insert(id, p);
    }

    fn del_progress(&mut self, id: u64) {
        self.prs.remove(&id);
    }

    // TODO: revoke pub when there is a better way to test.
    pub fn load_state(&mut self, hs: HardState) {
        if hs.get_commit() < self.raft_log.committed ||
           hs.get_commit() > self.raft_log.last_index() {
            panic!("{} {} hs.commit {} is out of range [{}, {}]",
                   self.tag,
                   self.id,
                   hs.get_commit(),
                   self.raft_log.committed,
                   self.raft_log.last_index())
        }
        self.raft_log.committed = hs.get_commit();
        self.term = hs.get_term();
        self.vote = hs.get_vote();
    }

    /// `pass_election_timeout` returns true iff `election_elapsed` is greater
    /// than or equal to the randomized election timeout in
    /// [`election_timeout`, 2 * `election_timeout` - 1].
    pub fn pass_election_timeout(&self) -> bool {
        self.election_elapsed >= self.randomized_election_timeout
    }

    pub fn reset_randomized_election_timeout(&mut self) {
        self.randomized_election_timeout = self.election_timeout +
                                           rand::thread_rng().gen_range(0, self.election_timeout)
    }

    // check_quorum_active returns true if the quorum is active from
    // the view of the local raft state machine. Otherwise, it returns
    // false.
    // check_quorum_active also resets all recent_active to false.
    fn check_quorum_active(&mut self) -> bool {
        let mut act = 0;
        let self_id = self.id;
        for (id, p) in &mut self.prs {
            if id == &self_id {
                // self is always active
                act += 1;
                continue;
            }

            if p.recent_active {
                act += 1;
            }

            p.recent_active = false;
        }
        act >= self.quorum()
    }

    pub fn send_timeout_now(&mut self, to: u64) {
        let msg = new_message(to, MessageType::MsgTimeoutNow, None);
        self.send(msg);
    }

    pub fn abort_leader_transfer(&mut self) {
        self.lead_transferee = None;
    }
}
