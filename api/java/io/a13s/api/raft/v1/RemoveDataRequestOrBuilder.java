// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: raft/v1/raft_shard.proto

package io.a13s.api.raft.v1;

public interface RemoveDataRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:raft.v1.RemoveDataRequest)
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
}
