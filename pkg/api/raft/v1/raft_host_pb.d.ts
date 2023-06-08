/*
 * Copyright (c) 2023 Sienna Lloyd
 *
 * Licensed under the PolyForm Internal Use License 1.0.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License here:
 *  https://github.com/mxplusb/pleiades/blob/mainline/LICENSE
 */

// @generated by protoc-gen-es v0.1.1 with parameter "target=js+dts"
// @generated from file raft/v1/raft_host.proto (package raft.v1, syntax proto3)
/* eslint-disable */
/* @ts-nocheck */

import type {BinaryReadOptions, FieldList, JsonReadOptions, JsonValue, PartialMessage, PlainMessage} from "@bufbuild/protobuf";
import {Message, proto3} from "@bufbuild/protobuf";

/**
 * @generated from message raft.v1.CompactRequest
 */
export declare class CompactRequest extends Message<CompactRequest> {
  /**
   * replica_id is a non-zero value used to identify a node within a Raft cluster.
   *
   * @generated from field: uint64 replica_id = 1;
   */
  replicaId: bigint;

  /**
   * shard_id is the unique value used to identify a Raft cluster.
   *
   * @generated from field: uint64 shard_id = 2;
   */
  shardId: bigint;

  constructor(data?: PartialMessage<CompactRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.CompactRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): CompactRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): CompactRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): CompactRequest;

  static equals(a: CompactRequest | PlainMessage<CompactRequest> | undefined, b: CompactRequest | PlainMessage<CompactRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.CompactResponse
 */
export declare class CompactResponse extends Message<CompactResponse> {
  constructor(data?: PartialMessage<CompactResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.CompactResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): CompactResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): CompactResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): CompactResponse;

  static equals(a: CompactResponse | PlainMessage<CompactResponse> | undefined, b: CompactResponse | PlainMessage<CompactResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.LeaderTransferRequest
 */
export declare class LeaderTransferRequest extends Message<LeaderTransferRequest> {
  /**
   * shard_id is the unique value used to identify a Raft cluster.
   *
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  /**
   * @generated from field: string target_node_id = 2;
   */
  targetNodeId: string;

  constructor(data?: PartialMessage<LeaderTransferRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.LeaderTransferRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): LeaderTransferRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): LeaderTransferRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): LeaderTransferRequest;

  static equals(a: LeaderTransferRequest | PlainMessage<LeaderTransferRequest> | undefined, b: LeaderTransferRequest | PlainMessage<LeaderTransferRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.LeaderTransferResponse
 */
export declare class LeaderTransferResponse extends Message<LeaderTransferResponse> {
  constructor(data?: PartialMessage<LeaderTransferResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.LeaderTransferResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): LeaderTransferResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): LeaderTransferResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): LeaderTransferResponse;

  static equals(a: LeaderTransferResponse | PlainMessage<LeaderTransferResponse> | undefined, b: LeaderTransferResponse | PlainMessage<LeaderTransferResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.SnapshotRequest
 */
export declare class SnapshotRequest extends Message<SnapshotRequest> {
  /**
   * shard_id is the unique value used to identify a Raft cluster.
   *
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  /**
   * @generated from field: int64 timeout = 2;
   */
  timeout: bigint;

  constructor(data?: PartialMessage<SnapshotRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.SnapshotRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): SnapshotRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): SnapshotRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): SnapshotRequest;

  static equals(a: SnapshotRequest | PlainMessage<SnapshotRequest> | undefined, b: SnapshotRequest | PlainMessage<SnapshotRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.SnapshotResponse
 */
export declare class SnapshotResponse extends Message<SnapshotResponse> {
  /**
   * @generated from field: uint64 snapshot_index_captured = 1;
   */
  snapshotIndexCaptured: bigint;

  constructor(data?: PartialMessage<SnapshotResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.SnapshotResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): SnapshotResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): SnapshotResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): SnapshotResponse;

  static equals(a: SnapshotResponse | PlainMessage<SnapshotResponse> | undefined, b: SnapshotResponse | PlainMessage<SnapshotResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.StopRequest
 */
export declare class StopRequest extends Message<StopRequest> {
  constructor(data?: PartialMessage<StopRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.StopRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): StopRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): StopRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): StopRequest;

  static equals(a: StopRequest | PlainMessage<StopRequest> | undefined, b: StopRequest | PlainMessage<StopRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.StopResponse
 */
export declare class StopResponse extends Message<StopResponse> {
  constructor(data?: PartialMessage<StopResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.StopResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): StopResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): StopResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): StopResponse;

  static equals(a: StopResponse | PlainMessage<StopResponse> | undefined, b: StopResponse | PlainMessage<StopResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.GetHostConfigRequest
 */
export declare class GetHostConfigRequest extends Message<GetHostConfigRequest> {
  constructor(data?: PartialMessage<GetHostConfigRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.GetHostConfigRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): GetHostConfigRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): GetHostConfigRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): GetHostConfigRequest;

  static equals(a: GetHostConfigRequest | PlainMessage<GetHostConfigRequest> | undefined, b: GetHostConfigRequest | PlainMessage<GetHostConfigRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.GetHostConfigResponse
 */
export declare class GetHostConfigResponse extends Message<GetHostConfigResponse> {
  /**
   * @generated from field: raft.v1.HostConfig config = 1;
   */
  config?: HostConfig;

  constructor(data?: PartialMessage<GetHostConfigResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.GetHostConfigResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): GetHostConfigResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): GetHostConfigResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): GetHostConfigResponse;

  static equals(a: GetHostConfigResponse | PlainMessage<GetHostConfigResponse> | undefined, b: GetHostConfigResponse | PlainMessage<GetHostConfigResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.GetShardMembershipRequest
 */
export declare class GetShardMembershipRequest extends Message<GetShardMembershipRequest> {
  /**
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  constructor(data?: PartialMessage<GetShardMembershipRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.GetShardMembershipRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): GetShardMembershipRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): GetShardMembershipRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): GetShardMembershipRequest;

  static equals(a: GetShardMembershipRequest | PlainMessage<GetShardMembershipRequest> | undefined, b: GetShardMembershipRequest | PlainMessage<GetShardMembershipRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.HostConfig
 */
export declare class HostConfig extends Message<HostConfig> {
  /**
   * @generated from field: uint64 deployment_id = 1;
   */
  deploymentId: bigint;

  /**
   * @generated from field: string wal_dir = 2;
   */
  walDir: string;

  /**
   * @generated from field: string host_dir = 3;
   */
  hostDir: string;

  /**
   * @generated from field: uint64 round_trip_time_in_milliseconds = 4;
   */
  roundTripTimeInMilliseconds: bigint;

  /**
   * @generated from field: string raft_address = 5;
   */
  raftAddress: string;

  /**
   * @generated from field: bool address_by_host_id = 6;
   */
  addressByHostId: boolean;

  /**
   * @generated from field: string listen_address = 7;
   */
  listenAddress: string;

  /**
   * @generated from field: bool mutual_tls = 8;
   */
  mutualTls: boolean;

  /**
   * @generated from field: string ca_file = 9;
   */
  caFile: string;

  /**
   * @generated from field: string cert_file = 10;
   */
  certFile: string;

  /**
   * @generated from field: string key_file = 11;
   */
  keyFile: string;

  /**
   * @generated from field: bool enable_metrics = 12;
   */
  enableMetrics: boolean;

  /**
   * @generated from field: bool notify_commit = 13;
   */
  notifyCommit: boolean;

  constructor(data?: PartialMessage<HostConfig>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.HostConfig";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): HostConfig;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): HostConfig;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): HostConfig;

  static equals(a: HostConfig | PlainMessage<HostConfig> | undefined, b: HostConfig | PlainMessage<HostConfig> | undefined): boolean;
}

