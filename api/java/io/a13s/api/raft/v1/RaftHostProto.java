// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: raft/v1/raft_host.proto

package io.a13s.api.raft.v1;

public final class RaftHostProto {
  private RaftHostProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_CompactRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_CompactRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_CompactResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_CompactResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_LeaderTransferRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_LeaderTransferRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_LeaderTransferResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_LeaderTransferResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_SnapshotRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_SnapshotRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_SnapshotResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_SnapshotResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_StopRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_StopRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_StopResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_StopResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_GetHostConfigRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_GetHostConfigRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_GetHostConfigResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_GetHostConfigResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_GetShardMembershipRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_GetShardMembershipRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_raft_v1_HostConfig_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_raft_v1_HostConfig_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027raft/v1/raft_host.proto\022\007raft.v1\"J\n\016Co" +
      "mpactRequest\022\035\n\nreplica_id\030\001 \001(\004R\treplic" +
      "aId\022\031\n\010shard_id\030\002 \001(\004R\007shardId\"\021\n\017Compac" +
      "tResponse\"X\n\025LeaderTransferRequest\022\031\n\010sh" +
      "ard_id\030\001 \001(\004R\007shardId\022$\n\016target_node_id\030" +
      "\002 \001(\tR\014targetNodeId\"\030\n\026LeaderTransferRes" +
      "ponse\"F\n\017SnapshotRequest\022\031\n\010shard_id\030\001 \001" +
      "(\004R\007shardId\022\030\n\007timeout\030\002 \001(\003R\007timeout\"J\n" +
      "\020SnapshotResponse\0226\n\027snapshot_index_capt" +
      "ured\030\001 \001(\004R\025snapshotIndexCaptured\"\r\n\013Sto" +
      "pRequest\"\016\n\014StopResponse\"\026\n\024GetHostConfi" +
      "gRequest\"D\n\025GetHostConfigResponse\022+\n\006con" +
      "fig\030\001 \001(\0132\023.raft.v1.HostConfigR\006config\"6" +
      "\n\031GetShardMembershipRequest\022\031\n\010shard_id\030" +
      "\001 \001(\004R\007shardId\"\336\003\n\nHostConfig\022#\n\rdeploym" +
      "ent_id\030\001 \001(\004R\014deploymentId\022\027\n\007wal_dir\030\002 " +
      "\001(\tR\006walDir\022\031\n\010host_dir\030\003 \001(\tR\007hostDir\022D" +
      "\n\037round_trip_time_in_milliseconds\030\004 \001(\004R" +
      "\033roundTripTimeInMilliseconds\022!\n\014raft_add" +
      "ress\030\005 \001(\tR\013raftAddress\022+\n\022address_by_ho" +
      "st_id\030\006 \001(\010R\017addressByHostId\022%\n\016listen_a" +
      "ddress\030\007 \001(\tR\rlistenAddress\022\035\n\nmutual_tl" +
      "s\030\010 \001(\010R\tmutualTls\022\027\n\007ca_file\030\t \001(\tR\006caF" +
      "ile\022\033\n\tcert_file\030\n \001(\tR\010certFile\022\031\n\010key_" +
      "file\030\013 \001(\tR\007keyFile\022%\n\016enable_metrics\030\014 " +
      "\001(\010R\renableMetrics\022#\n\rnotify_commit\030\r \001(" +
      "\010R\014notifyCommitB}\n\023io.a13s.api.raft.v1B\r" +
      "RaftHostProtoP\001Z\032a13s.io/api/raft/v1;raf" +
      "tv1\242\002\003RXX\252\002\007Raft.V1\312\002\007Raft\\V1\342\002\023Raft\\V1\\" +
      "GPBMetadata\352\002\010Raft::V1b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_raft_v1_CompactRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_raft_v1_CompactRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_CompactRequest_descriptor,
        new java.lang.String[] { "ReplicaId", "ShardId", });
    internal_static_raft_v1_CompactResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_raft_v1_CompactResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_CompactResponse_descriptor,
        new java.lang.String[] { });
    internal_static_raft_v1_LeaderTransferRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_raft_v1_LeaderTransferRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_LeaderTransferRequest_descriptor,
        new java.lang.String[] { "ShardId", "TargetNodeId", });
    internal_static_raft_v1_LeaderTransferResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_raft_v1_LeaderTransferResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_LeaderTransferResponse_descriptor,
        new java.lang.String[] { });
    internal_static_raft_v1_SnapshotRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_raft_v1_SnapshotRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_SnapshotRequest_descriptor,
        new java.lang.String[] { "ShardId", "Timeout", });
    internal_static_raft_v1_SnapshotResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_raft_v1_SnapshotResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_SnapshotResponse_descriptor,
        new java.lang.String[] { "SnapshotIndexCaptured", });
    internal_static_raft_v1_StopRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_raft_v1_StopRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_StopRequest_descriptor,
        new java.lang.String[] { });
    internal_static_raft_v1_StopResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_raft_v1_StopResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_StopResponse_descriptor,
        new java.lang.String[] { });
    internal_static_raft_v1_GetHostConfigRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_raft_v1_GetHostConfigRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_GetHostConfigRequest_descriptor,
        new java.lang.String[] { });
    internal_static_raft_v1_GetHostConfigResponse_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_raft_v1_GetHostConfigResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_GetHostConfigResponse_descriptor,
        new java.lang.String[] { "Config", });
    internal_static_raft_v1_GetShardMembershipRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_raft_v1_GetShardMembershipRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_GetShardMembershipRequest_descriptor,
        new java.lang.String[] { "ShardId", });
    internal_static_raft_v1_HostConfig_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_raft_v1_HostConfig_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_raft_v1_HostConfig_descriptor,
        new java.lang.String[] { "DeploymentId", "WalDir", "HostDir", "RoundTripTimeInMilliseconds", "RaftAddress", "AddressByHostId", "ListenAddress", "MutualTls", "CaFile", "CertFile", "KeyFile", "EnableMetrics", "NotifyCommit", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
