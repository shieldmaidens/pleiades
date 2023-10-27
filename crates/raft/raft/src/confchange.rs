// Copyright 2020 TiKV Project Authors. Licensed under Apache-2.0.
mod changer;
#[cfg(test)]
pub mod datadriven_test;
mod restore;

use nova_api::raft::v1::{ConfChangeTransition, ConfChangeV2, Snapshot};
pub use self::changer::{Changer, MapChange, MapChangeType};
pub use self::restore::restore;

use crate::tracker::Configuration;

#[inline]
pub(crate) fn joint(cfg: &Configuration) -> bool {
    !cfg.voters().outgoing.is_empty()
}

#[inline]
pub fn enter_joint(conf_change: &ConfChangeV2) -> Option<bool> {
    // NB: in theory, more config changes could qualify for the "simple"
    // protocol but it depends on the config on top of which the changes apply.
    // For example, adding two learners is not OK if both nodes are part of the
    // base config (i.e. two voters are turned into learners in the process of
    // applying the conf change). In practice, these distinctions should not
    // matter, so we keep it simple and use Joint Consensus liberally.
    if conf_change.get_transition() != ConfChangeTransition::Auto || conf_change.get_changes().len() > 1 {
        match conf_change.get_transition() {
            ConfChangeTransition::Auto | ConfChangeTransition::Implicit => Some(true),
            ConfChangeTransition::Explicit => Some(false),
        }
    } else {
        None
    }
}

#[inline]
pub fn leave_joint(conf_change: &ConfChangeV2) -> bool {
    conf_change.get_transition() == ConfChangeTransition::Auto && conf_change.changes.is_empty()
}

#[inline]
pub fn snapshot_is_empty(snapshot: &Snapshot) -> bool {
    snapshot.get_metadata().index == 0
}