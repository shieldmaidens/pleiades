// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kvstore/v1/transactions.proto

package com.github.mxplusb.pleiades.api.kvstore.v1;

public interface NewTransactionRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:kvstore.v1.NewTransactionRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint64 shard_id = 1 [json_name = "shardId"];</code>
   * @return The shardId.
   */
  long getShardId();

  /**
   * <code>uint64 client_id = 2 [json_name = "clientId"];</code>
   * @return The clientId.
   */
  long getClientId();
}