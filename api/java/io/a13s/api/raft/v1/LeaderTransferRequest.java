// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: raft/v1/raft_host.proto

package io.a13s.api.raft.v1;

/**
 * Protobuf type {@code raft.v1.LeaderTransferRequest}
 */
public final class LeaderTransferRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:raft.v1.LeaderTransferRequest)
    LeaderTransferRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use LeaderTransferRequest.newBuilder() to construct.
  private LeaderTransferRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LeaderTransferRequest() {
    targetNodeId_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new LeaderTransferRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private LeaderTransferRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            shardId_ = input.readUInt64();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            targetNodeId_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (com.google.protobuf.UninitializedMessageException e) {
      throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.a13s.api.raft.v1.RaftHostProto.internal_static_raft_v1_LeaderTransferRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.a13s.api.raft.v1.RaftHostProto.internal_static_raft_v1_LeaderTransferRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.a13s.api.raft.v1.LeaderTransferRequest.class, io.a13s.api.raft.v1.LeaderTransferRequest.Builder.class);
  }

  public static final int SHARD_ID_FIELD_NUMBER = 1;
  private long shardId_;
  /**
   * <pre>
   * shard_id is the unique value used to identify a Raft cluster.
   * </pre>
   *
   * <code>uint64 shard_id = 1 [json_name = "shardId"];</code>
   * @return The shardId.
   */
  @java.lang.Override
  public long getShardId() {
    return shardId_;
  }

  public static final int TARGET_NODE_ID_FIELD_NUMBER = 2;
  private volatile java.lang.Object targetNodeId_;
  /**
   * <code>string target_node_id = 2 [json_name = "targetNodeId"];</code>
   * @return The targetNodeId.
   */
  @java.lang.Override
  public java.lang.String getTargetNodeId() {
    java.lang.Object ref = targetNodeId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      targetNodeId_ = s;
      return s;
    }
  }
  /**
   * <code>string target_node_id = 2 [json_name = "targetNodeId"];</code>
   * @return The bytes for targetNodeId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTargetNodeIdBytes() {
    java.lang.Object ref = targetNodeId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      targetNodeId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (shardId_ != 0L) {
      output.writeUInt64(1, shardId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(targetNodeId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, targetNodeId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (shardId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, shardId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(targetNodeId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, targetNodeId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.a13s.api.raft.v1.LeaderTransferRequest)) {
      return super.equals(obj);
    }
    io.a13s.api.raft.v1.LeaderTransferRequest other = (io.a13s.api.raft.v1.LeaderTransferRequest) obj;

    if (getShardId()
        != other.getShardId()) return false;
    if (!getTargetNodeId()
        .equals(other.getTargetNodeId())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SHARD_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getShardId());
    hash = (37 * hash) + TARGET_NODE_ID_FIELD_NUMBER;
    hash = (53 * hash) + getTargetNodeId().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.a13s.api.raft.v1.LeaderTransferRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.a13s.api.raft.v1.LeaderTransferRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.a13s.api.raft.v1.LeaderTransferRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code raft.v1.LeaderTransferRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:raft.v1.LeaderTransferRequest)
      io.a13s.api.raft.v1.LeaderTransferRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.a13s.api.raft.v1.RaftHostProto.internal_static_raft_v1_LeaderTransferRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.a13s.api.raft.v1.RaftHostProto.internal_static_raft_v1_LeaderTransferRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.a13s.api.raft.v1.LeaderTransferRequest.class, io.a13s.api.raft.v1.LeaderTransferRequest.Builder.class);
    }

    // Construct using io.a13s.api.raft.v1.LeaderTransferRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      shardId_ = 0L;

      targetNodeId_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.a13s.api.raft.v1.RaftHostProto.internal_static_raft_v1_LeaderTransferRequest_descriptor;
    }

    @java.lang.Override
    public io.a13s.api.raft.v1.LeaderTransferRequest getDefaultInstanceForType() {
      return io.a13s.api.raft.v1.LeaderTransferRequest.getDefaultInstance();
    }

    @java.lang.Override
    public io.a13s.api.raft.v1.LeaderTransferRequest build() {
      io.a13s.api.raft.v1.LeaderTransferRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.a13s.api.raft.v1.LeaderTransferRequest buildPartial() {
      io.a13s.api.raft.v1.LeaderTransferRequest result = new io.a13s.api.raft.v1.LeaderTransferRequest(this);
      result.shardId_ = shardId_;
      result.targetNodeId_ = targetNodeId_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.a13s.api.raft.v1.LeaderTransferRequest) {
        return mergeFrom((io.a13s.api.raft.v1.LeaderTransferRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.a13s.api.raft.v1.LeaderTransferRequest other) {
      if (other == io.a13s.api.raft.v1.LeaderTransferRequest.getDefaultInstance()) return this;
      if (other.getShardId() != 0L) {
        setShardId(other.getShardId());
      }
      if (!other.getTargetNodeId().isEmpty()) {
        targetNodeId_ = other.targetNodeId_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.a13s.api.raft.v1.LeaderTransferRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.a13s.api.raft.v1.LeaderTransferRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long shardId_ ;
    /**
     * <pre>
     * shard_id is the unique value used to identify a Raft cluster.
     * </pre>
     *
     * <code>uint64 shard_id = 1 [json_name = "shardId"];</code>
     * @return The shardId.
     */
    @java.lang.Override
    public long getShardId() {
      return shardId_;
    }
    /**
     * <pre>
     * shard_id is the unique value used to identify a Raft cluster.
     * </pre>
     *
     * <code>uint64 shard_id = 1 [json_name = "shardId"];</code>
     * @param value The shardId to set.
     * @return This builder for chaining.
     */
    public Builder setShardId(long value) {
      
      shardId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * shard_id is the unique value used to identify a Raft cluster.
     * </pre>
     *
     * <code>uint64 shard_id = 1 [json_name = "shardId"];</code>
     * @return This builder for chaining.
     */
    public Builder clearShardId() {
      
      shardId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object targetNodeId_ = "";
    /**
     * <code>string target_node_id = 2 [json_name = "targetNodeId"];</code>
     * @return The targetNodeId.
     */
    public java.lang.String getTargetNodeId() {
      java.lang.Object ref = targetNodeId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        targetNodeId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string target_node_id = 2 [json_name = "targetNodeId"];</code>
     * @return The bytes for targetNodeId.
     */
    public com.google.protobuf.ByteString
        getTargetNodeIdBytes() {
      java.lang.Object ref = targetNodeId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        targetNodeId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string target_node_id = 2 [json_name = "targetNodeId"];</code>
     * @param value The targetNodeId to set.
     * @return This builder for chaining.
     */
    public Builder setTargetNodeId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      targetNodeId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string target_node_id = 2 [json_name = "targetNodeId"];</code>
     * @return This builder for chaining.
     */
    public Builder clearTargetNodeId() {
      
      targetNodeId_ = getDefaultInstance().getTargetNodeId();
      onChanged();
      return this;
    }
    /**
     * <code>string target_node_id = 2 [json_name = "targetNodeId"];</code>
     * @param value The bytes for targetNodeId to set.
     * @return This builder for chaining.
     */
    public Builder setTargetNodeIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      targetNodeId_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:raft.v1.LeaderTransferRequest)
  }

  // @@protoc_insertion_point(class_scope:raft.v1.LeaderTransferRequest)
  private static final io.a13s.api.raft.v1.LeaderTransferRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.a13s.api.raft.v1.LeaderTransferRequest();
  }

  public static io.a13s.api.raft.v1.LeaderTransferRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<LeaderTransferRequest>
      PARSER = new com.google.protobuf.AbstractParser<LeaderTransferRequest>() {
    @java.lang.Override
    public LeaderTransferRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new LeaderTransferRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<LeaderTransferRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LeaderTransferRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.a13s.api.raft.v1.LeaderTransferRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

