// @generated by protoc-gen-es v0.1.1 with parameter "target=js+dts"
// @generated from file raft/v1/raft_shard.proto (package raft.v1, syntax proto3)
/* eslint-disable */
/* @ts-nocheck */

import type {BinaryReadOptions, FieldList, JsonReadOptions, JsonValue, PartialMessage, PlainMessage} from "@bufbuild/protobuf";
import {Message, proto3} from "@bufbuild/protobuf";

/**
 * @generated from enum raft.v1.StateMachineType
 */
export declare enum StateMachineType {
  /**
   * @generated from enum value: STATE_MACHINE_TYPE_UNSPECIFIED = 0;
   */
  UNSPECIFIED = 0,

  /**
   * @generated from enum value: STATE_MACHINE_TYPE_TEST = 0;
   */
  TEST = 0,

  /**
   * @generated from enum value: STATE_MACHINE_TYPE_KV = 1;
   */
  KV = 1,
}

/**
 * @generated from message raft.v1.AddReplicaRequest
 */
export declare class AddReplicaRequest extends Message<AddReplicaRequest> {
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

  /**
   * @generated from field: string hostname = 4;
   */
  hostname: string;

  /**
   * @generated from field: int64 timeout = 5;
   */
  timeout: bigint;

  constructor(data?: PartialMessage<AddReplicaRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.AddReplicaRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): AddReplicaRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): AddReplicaRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): AddReplicaRequest;

  static equals(a: AddReplicaRequest | PlainMessage<AddReplicaRequest> | undefined, b: AddReplicaRequest | PlainMessage<AddReplicaRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.AddReplicaResponse
 */
export declare class AddReplicaResponse extends Message<AddReplicaResponse> {
  constructor(data?: PartialMessage<AddReplicaResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.AddReplicaResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): AddReplicaResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): AddReplicaResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): AddReplicaResponse;

  static equals(a: AddReplicaResponse | PlainMessage<AddReplicaResponse> | undefined, b: AddReplicaResponse | PlainMessage<AddReplicaResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.AddReplicaObserverRequest
 */
export declare class AddReplicaObserverRequest extends Message<AddReplicaObserverRequest> {
  /**
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  /**
   * @generated from field: uint64 replica_id = 2;
   */
  replicaId: bigint;

  /**
   * @generated from field: string hostname = 3;
   */
  hostname: string;

  /**
   * @generated from field: int64 timeout = 4;
   */
  timeout: bigint;

  constructor(data?: PartialMessage<AddReplicaObserverRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.AddReplicaObserverRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): AddReplicaObserverRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): AddReplicaObserverRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): AddReplicaObserverRequest;

  static equals(a: AddReplicaObserverRequest | PlainMessage<AddReplicaObserverRequest> | undefined, b: AddReplicaObserverRequest | PlainMessage<AddReplicaObserverRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.AddReplicaObserverResponse
 */
export declare class AddReplicaObserverResponse extends Message<AddReplicaObserverResponse> {
  constructor(data?: PartialMessage<AddReplicaObserverResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.AddReplicaObserverResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): AddReplicaObserverResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): AddReplicaObserverResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): AddReplicaObserverResponse;

  static equals(a: AddReplicaObserverResponse | PlainMessage<AddReplicaObserverResponse> | undefined, b: AddReplicaObserverResponse | PlainMessage<AddReplicaObserverResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.AddReplicaWitnessRequest
 */
export declare class AddReplicaWitnessRequest extends Message<AddReplicaWitnessRequest> {
  /**
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  /**
   * @generated from field: uint64 replica_id = 2;
   */
  replicaId: bigint;

  /**
   * @generated from field: string hostname = 3;
   */
  hostname: string;

  /**
   * @generated from field: int64 timeout = 4;
   */
  timeout: bigint;

  constructor(data?: PartialMessage<AddReplicaWitnessRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.AddReplicaWitnessRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): AddReplicaWitnessRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): AddReplicaWitnessRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): AddReplicaWitnessRequest;

  static equals(a: AddReplicaWitnessRequest | PlainMessage<AddReplicaWitnessRequest> | undefined, b: AddReplicaWitnessRequest | PlainMessage<AddReplicaWitnessRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.AddReplicaWitnessResponse
 */
export declare class AddReplicaWitnessResponse extends Message<AddReplicaWitnessResponse> {
  constructor(data?: PartialMessage<AddReplicaWitnessResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.AddReplicaWitnessResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): AddReplicaWitnessResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): AddReplicaWitnessResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): AddReplicaWitnessResponse;

  static equals(a: AddReplicaWitnessResponse | PlainMessage<AddReplicaWitnessResponse> | undefined, b: AddReplicaWitnessResponse | PlainMessage<AddReplicaWitnessResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.RemoveReplicaRequest
 */
export declare class RemoveReplicaRequest extends Message<RemoveReplicaRequest> {
  /**
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  /**
   * @generated from field: uint64 replica_id = 2;
   */
  replicaId: bigint;

  /**
   * @generated from field: int64 timeout = 3;
   */
  timeout: bigint;

  constructor(data?: PartialMessage<RemoveReplicaRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.RemoveReplicaRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): RemoveReplicaRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): RemoveReplicaRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): RemoveReplicaRequest;

  static equals(a: RemoveReplicaRequest | PlainMessage<RemoveReplicaRequest> | undefined, b: RemoveReplicaRequest | PlainMessage<RemoveReplicaRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.RemoveReplicaResponse
 */
export declare class RemoveReplicaResponse extends Message<RemoveReplicaResponse> {
  constructor(data?: PartialMessage<RemoveReplicaResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.RemoveReplicaResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): RemoveReplicaResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): RemoveReplicaResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): RemoveReplicaResponse;

  static equals(a: RemoveReplicaResponse | PlainMessage<RemoveReplicaResponse> | undefined, b: RemoveReplicaResponse | PlainMessage<RemoveReplicaResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.GetLeaderIdRequest
 */
export declare class GetLeaderIdRequest extends Message<GetLeaderIdRequest> {
  /**
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  /**
   * @generated from field: uint64 replica_id = 2;
   */
  replicaId: bigint;

  /**
   * @generated from field: int64 timeout = 3;
   */
  timeout: bigint;

  constructor(data?: PartialMessage<GetLeaderIdRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.GetLeaderIdRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): GetLeaderIdRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): GetLeaderIdRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): GetLeaderIdRequest;

  static equals(a: GetLeaderIdRequest | PlainMessage<GetLeaderIdRequest> | undefined, b: GetLeaderIdRequest | PlainMessage<GetLeaderIdRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.GetLeaderIdResponse
 */
export declare class GetLeaderIdResponse extends Message<GetLeaderIdResponse> {
  /**
   * @generated from field: uint64 leader = 1;
   */
  leader: bigint;

  /**
   * @generated from field: bool available = 2;
   */
  available: boolean;

  constructor(data?: PartialMessage<GetLeaderIdResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.GetLeaderIdResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): GetLeaderIdResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): GetLeaderIdResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): GetLeaderIdResponse;

  static equals(a: GetLeaderIdResponse | PlainMessage<GetLeaderIdResponse> | undefined, b: GetLeaderIdResponse | PlainMessage<GetLeaderIdResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.GetShardMembersRequest
 */
export declare class GetShardMembersRequest extends Message<GetShardMembersRequest> {
  /**
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  constructor(data?: PartialMessage<GetShardMembersRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.GetShardMembersRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): GetShardMembersRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): GetShardMembersRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): GetShardMembersRequest;

  static equals(a: GetShardMembersRequest | PlainMessage<GetShardMembersRequest> | undefined, b: GetShardMembersRequest | PlainMessage<GetShardMembersRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.GetShardMembersResponse
 */
export declare class GetShardMembersResponse extends Message<GetShardMembersResponse> {
  /**
   * @generated from field: uint64 config_change_id = 1;
   */
  configChangeId: bigint;

  /**
   * @generated from field: map<uint64, string> replicas = 2;
   */
  replicas: { [key: string]: string };

  /**
   * @generated from field: map<uint64, string> observers = 3;
   */
  observers: { [key: string]: string };

  /**
   * @generated from field: map<uint64, string> witnesses = 4;
   */
  witnesses: { [key: string]: string };

  /**
   * @generated from field: map<uint64, string> removed = 5;
   */
  removed: { [key: string]: string };

  constructor(data?: PartialMessage<GetShardMembersResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.GetShardMembersResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): GetShardMembersResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): GetShardMembersResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): GetShardMembersResponse;

  static equals(a: GetShardMembersResponse | PlainMessage<GetShardMembersResponse> | undefined, b: GetShardMembersResponse | PlainMessage<GetShardMembersResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.NewShardRequest
 */
export declare class NewShardRequest extends Message<NewShardRequest> {
  /**
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  /**
   * @generated from field: uint64 replica_id = 2;
   */
  replicaId: bigint;

  /**
   * @generated from field: raft.v1.StateMachineType type = 3;
   */
  type: StateMachineType;

  /**
   * @generated from field: string hostname = 4;
   */
  hostname: string;

  /**
   * @generated from field: int64 timeout = 5;
   */
  timeout: bigint;

  constructor(data?: PartialMessage<NewShardRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.NewShardRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): NewShardRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): NewShardRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): NewShardRequest;

  static equals(a: NewShardRequest | PlainMessage<NewShardRequest> | undefined, b: NewShardRequest | PlainMessage<NewShardRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.NewShardResponse
 */
export declare class NewShardResponse extends Message<NewShardResponse> {
  constructor(data?: PartialMessage<NewShardResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.NewShardResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): NewShardResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): NewShardResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): NewShardResponse;

  static equals(a: NewShardResponse | PlainMessage<NewShardResponse> | undefined, b: NewShardResponse | PlainMessage<NewShardResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.RemoveDataRequest
 */
export declare class RemoveDataRequest extends Message<RemoveDataRequest> {
  /**
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  /**
   * @generated from field: uint64 replica_id = 2;
   */
  replicaId: bigint;

  constructor(data?: PartialMessage<RemoveDataRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.RemoveDataRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): RemoveDataRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): RemoveDataRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): RemoveDataRequest;

  static equals(a: RemoveDataRequest | PlainMessage<RemoveDataRequest> | undefined, b: RemoveDataRequest | PlainMessage<RemoveDataRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.RemoveDataResponse
 */
export declare class RemoveDataResponse extends Message<RemoveDataResponse> {
  constructor(data?: PartialMessage<RemoveDataResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.RemoveDataResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): RemoveDataResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): RemoveDataResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): RemoveDataResponse;

  static equals(a: RemoveDataResponse | PlainMessage<RemoveDataResponse> | undefined, b: RemoveDataResponse | PlainMessage<RemoveDataResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.StartReplicaRequest
 */
export declare class StartReplicaRequest extends Message<StartReplicaRequest> {
  /**
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  /**
   * @generated from field: uint64 replica_id = 2;
   */
  replicaId: bigint;

  /**
   * @generated from field: raft.v1.StateMachineType type = 3;
   */
  type: StateMachineType;

  constructor(data?: PartialMessage<StartReplicaRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.StartReplicaRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): StartReplicaRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): StartReplicaRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): StartReplicaRequest;

  static equals(a: StartReplicaRequest | PlainMessage<StartReplicaRequest> | undefined, b: StartReplicaRequest | PlainMessage<StartReplicaRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.StartReplicaResponse
 */
export declare class StartReplicaResponse extends Message<StartReplicaResponse> {
  constructor(data?: PartialMessage<StartReplicaResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.StartReplicaResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): StartReplicaResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): StartReplicaResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): StartReplicaResponse;

  static equals(a: StartReplicaResponse | PlainMessage<StartReplicaResponse> | undefined, b: StartReplicaResponse | PlainMessage<StartReplicaResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.StartReplicaObserverRequest
 */
export declare class StartReplicaObserverRequest extends Message<StartReplicaObserverRequest> {
  /**
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  /**
   * @generated from field: uint64 replica_id = 2;
   */
  replicaId: bigint;

  /**
   * @generated from field: raft.v1.StateMachineType type = 3;
   */
  type: StateMachineType;

  constructor(data?: PartialMessage<StartReplicaObserverRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.StartReplicaObserverRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): StartReplicaObserverRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): StartReplicaObserverRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): StartReplicaObserverRequest;

  static equals(a: StartReplicaObserverRequest | PlainMessage<StartReplicaObserverRequest> | undefined, b: StartReplicaObserverRequest | PlainMessage<StartReplicaObserverRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.StartReplicaObserverResponse
 */
export declare class StartReplicaObserverResponse extends Message<StartReplicaObserverResponse> {
  constructor(data?: PartialMessage<StartReplicaObserverResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.StartReplicaObserverResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): StartReplicaObserverResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): StartReplicaObserverResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): StartReplicaObserverResponse;

  static equals(a: StartReplicaObserverResponse | PlainMessage<StartReplicaObserverResponse> | undefined, b: StartReplicaObserverResponse | PlainMessage<StartReplicaObserverResponse> | undefined): boolean;
}

/**
 * @generated from message raft.v1.StopReplicaRequest
 */
export declare class StopReplicaRequest extends Message<StopReplicaRequest> {
  /**
   * shard_id is the unique value used to identify a Raft cluster.
   *
   * @generated from field: uint64 shard_id = 1;
   */
  shardId: bigint;

  constructor(data?: PartialMessage<StopReplicaRequest>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.StopReplicaRequest";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): StopReplicaRequest;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): StopReplicaRequest;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): StopReplicaRequest;

  static equals(a: StopReplicaRequest | PlainMessage<StopReplicaRequest> | undefined, b: StopReplicaRequest | PlainMessage<StopReplicaRequest> | undefined): boolean;
}

/**
 * @generated from message raft.v1.StopReplicaResponse
 */
export declare class StopReplicaResponse extends Message<StopReplicaResponse> {
  constructor(data?: PartialMessage<StopReplicaResponse>);

  static readonly runtime: typeof proto3;
  static readonly typeName = "raft.v1.StopReplicaResponse";
  static readonly fields: FieldList;

  static fromBinary(bytes: Uint8Array, options?: Partial<BinaryReadOptions>): StopReplicaResponse;

  static fromJson(jsonValue: JsonValue, options?: Partial<JsonReadOptions>): StopReplicaResponse;

  static fromJsonString(jsonString: string, options?: Partial<JsonReadOptions>): StopReplicaResponse;

  static equals(a: StopReplicaResponse | PlainMessage<StopReplicaResponse> | undefined, b: StopReplicaResponse | PlainMessage<StopReplicaResponse> | undefined): boolean;
}

