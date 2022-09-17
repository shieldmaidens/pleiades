// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: raft/v1/raft_event.proto

package io.a13s.api.raft.v1;

public interface RaftLogEntryEventOrBuilder extends
    // @@protoc_insertion_point(interface_extends:raft.v1.RaftLogEntryEvent)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint64 shard_id = 1 [json_name = "shardId"];</code>
   * @return The shardId.
   */
  long getShardId();

  /**
   * <code>uint64 replica_id = 2 [json_name = "replicaId"];</code>
   * @return The replicaId.
   */
  long getReplicaId();

  /**
   * <code>uint64 index = 3 [json_name = "index"];</code>
   * @return The index.
   */
  long getIndex();
}
