// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: raft/v1/raft_shard.proto

package io.a13s.api.raft.v1;

/**
 * Protobuf type {@code raft.v1.StartReplicaRequest}
 */
public final class StartReplicaRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:raft.v1.StartReplicaRequest)
    StartReplicaRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StartReplicaRequest.newBuilder() to construct.
  private StartReplicaRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StartReplicaRequest() {
    type_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new StartReplicaRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StartReplicaRequest(
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
          case 16: {

            replicaId_ = input.readUInt64();
            break;
          }
          case 24: {
            int rawValue = input.readEnum();

            type_ = rawValue;
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
    return io.a13s.api.raft.v1.RaftShardProto.internal_static_raft_v1_StartReplicaRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.a13s.api.raft.v1.RaftShardProto.internal_static_raft_v1_StartReplicaRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.a13s.api.raft.v1.StartReplicaRequest.class, io.a13s.api.raft.v1.StartReplicaRequest.Builder.class);
  }

  public static final int SHARD_ID_FIELD_NUMBER = 1;
  private long shardId_;
  /**
   * <code>uint64 shard_id = 1 [json_name = "shardId"];</code>
   * @return The shardId.
   */
  @java.lang.Override
  public long getShardId() {
    return shardId_;
  }

  public static final int REPLICA_ID_FIELD_NUMBER = 2;
  private long replicaId_;
  /**
   * <code>uint64 replica_id = 2 [json_name = "replicaId"];</code>
   * @return The replicaId.
   */
  @java.lang.Override
  public long getReplicaId() {
    return replicaId_;
  }

  public static final int TYPE_FIELD_NUMBER = 3;
  private int type_;
  /**
   * <code>.raft.v1.StateMachineType type = 3 [json_name = "type"];</code>
   * @return The enum numeric value on the wire for type.
   */
  @java.lang.Override public int getTypeValue() {
    return type_;
  }
  /**
   * <code>.raft.v1.StateMachineType type = 3 [json_name = "type"];</code>
   * @return The type.
   */
  @java.lang.Override public io.a13s.api.raft.v1.StateMachineType getType() {
    @SuppressWarnings("deprecation")
    io.a13s.api.raft.v1.StateMachineType result = io.a13s.api.raft.v1.StateMachineType.valueOf(type_);
    return result == null ? io.a13s.api.raft.v1.StateMachineType.UNRECOGNIZED : result;
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
    if (replicaId_ != 0L) {
      output.writeUInt64(2, replicaId_);
    }
    if (type_ != io.a13s.api.raft.v1.StateMachineType.STATE_MACHINE_TYPE_UNSPECIFIED.getNumber()) {
      output.writeEnum(3, type_);
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
    if (replicaId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, replicaId_);
    }
    if (type_ != io.a13s.api.raft.v1.StateMachineType.STATE_MACHINE_TYPE_UNSPECIFIED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, type_);
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
    if (!(obj instanceof io.a13s.api.raft.v1.StartReplicaRequest)) {
      return super.equals(obj);
    }
    io.a13s.api.raft.v1.StartReplicaRequest other = (io.a13s.api.raft.v1.StartReplicaRequest) obj;

    if (getShardId()
        != other.getShardId()) return false;
    if (getReplicaId()
        != other.getReplicaId()) return false;
    if (type_ != other.type_) return false;
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
    hash = (37 * hash) + REPLICA_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getReplicaId());
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + type_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.a13s.api.raft.v1.StartReplicaRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.a13s.api.raft.v1.StartReplicaRequest parseFrom(
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
  public static Builder newBuilder(io.a13s.api.raft.v1.StartReplicaRequest prototype) {
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
   * Protobuf type {@code raft.v1.StartReplicaRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:raft.v1.StartReplicaRequest)
      io.a13s.api.raft.v1.StartReplicaRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.a13s.api.raft.v1.RaftShardProto.internal_static_raft_v1_StartReplicaRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.a13s.api.raft.v1.RaftShardProto.internal_static_raft_v1_StartReplicaRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.a13s.api.raft.v1.StartReplicaRequest.class, io.a13s.api.raft.v1.StartReplicaRequest.Builder.class);
    }

    // Construct using io.a13s.api.raft.v1.StartReplicaRequest.newBuilder()
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

      replicaId_ = 0L;

      type_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.a13s.api.raft.v1.RaftShardProto.internal_static_raft_v1_StartReplicaRequest_descriptor;
    }

    @java.lang.Override
    public io.a13s.api.raft.v1.StartReplicaRequest getDefaultInstanceForType() {
      return io.a13s.api.raft.v1.StartReplicaRequest.getDefaultInstance();
    }

    @java.lang.Override
    public io.a13s.api.raft.v1.StartReplicaRequest build() {
      io.a13s.api.raft.v1.StartReplicaRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.a13s.api.raft.v1.StartReplicaRequest buildPartial() {
      io.a13s.api.raft.v1.StartReplicaRequest result = new io.a13s.api.raft.v1.StartReplicaRequest(this);
      result.shardId_ = shardId_;
      result.replicaId_ = replicaId_;
      result.type_ = type_;
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
      if (other instanceof io.a13s.api.raft.v1.StartReplicaRequest) {
        return mergeFrom((io.a13s.api.raft.v1.StartReplicaRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.a13s.api.raft.v1.StartReplicaRequest other) {
      if (other == io.a13s.api.raft.v1.StartReplicaRequest.getDefaultInstance()) return this;
      if (other.getShardId() != 0L) {
        setShardId(other.getShardId());
      }
      if (other.getReplicaId() != 0L) {
        setReplicaId(other.getReplicaId());
      }
      if (other.type_ != 0) {
        setTypeValue(other.getTypeValue());
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
      io.a13s.api.raft.v1.StartReplicaRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.a13s.api.raft.v1.StartReplicaRequest) e.getUnfinishedMessage();
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
     * <code>uint64 shard_id = 1 [json_name = "shardId"];</code>
     * @return The shardId.
     */
    @java.lang.Override
    public long getShardId() {
      return shardId_;
    }
    /**
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
     * <code>uint64 shard_id = 1 [json_name = "shardId"];</code>
     * @return This builder for chaining.
     */
    public Builder clearShardId() {
      
      shardId_ = 0L;
      onChanged();
      return this;
    }

    private long replicaId_ ;
    /**
     * <code>uint64 replica_id = 2 [json_name = "replicaId"];</code>
     * @return The replicaId.
     */
    @java.lang.Override
    public long getReplicaId() {
      return replicaId_;
    }
    /**
     * <code>uint64 replica_id = 2 [json_name = "replicaId"];</code>
     * @param value The replicaId to set.
     * @return This builder for chaining.
     */
    public Builder setReplicaId(long value) {
      
      replicaId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 replica_id = 2 [json_name = "replicaId"];</code>
     * @return This builder for chaining.
     */
    public Builder clearReplicaId() {
      
      replicaId_ = 0L;
      onChanged();
      return this;
    }

    private int type_ = 0;
    /**
     * <code>.raft.v1.StateMachineType type = 3 [json_name = "type"];</code>
     * @return The enum numeric value on the wire for type.
     */
    @java.lang.Override public int getTypeValue() {
      return type_;
    }
    /**
     * <code>.raft.v1.StateMachineType type = 3 [json_name = "type"];</code>
     * @param value The enum numeric value on the wire for type to set.
     * @return This builder for chaining.
     */
    public Builder setTypeValue(int value) {
      
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.raft.v1.StateMachineType type = 3 [json_name = "type"];</code>
     * @return The type.
     */
    @java.lang.Override
    public io.a13s.api.raft.v1.StateMachineType getType() {
      @SuppressWarnings("deprecation")
      io.a13s.api.raft.v1.StateMachineType result = io.a13s.api.raft.v1.StateMachineType.valueOf(type_);
      return result == null ? io.a13s.api.raft.v1.StateMachineType.UNRECOGNIZED : result;
    }
    /**
     * <code>.raft.v1.StateMachineType type = 3 [json_name = "type"];</code>
     * @param value The type to set.
     * @return This builder for chaining.
     */
    public Builder setType(io.a13s.api.raft.v1.StateMachineType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      type_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.raft.v1.StateMachineType type = 3 [json_name = "type"];</code>
     * @return This builder for chaining.
     */
    public Builder clearType() {
      
      type_ = 0;
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


    // @@protoc_insertion_point(builder_scope:raft.v1.StartReplicaRequest)
  }

  // @@protoc_insertion_point(class_scope:raft.v1.StartReplicaRequest)
  private static final io.a13s.api.raft.v1.StartReplicaRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.a13s.api.raft.v1.StartReplicaRequest();
  }

  public static io.a13s.api.raft.v1.StartReplicaRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StartReplicaRequest>
      PARSER = new com.google.protobuf.AbstractParser<StartReplicaRequest>() {
    @java.lang.Override
    public StartReplicaRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StartReplicaRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StartReplicaRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StartReplicaRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.a13s.api.raft.v1.StartReplicaRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

