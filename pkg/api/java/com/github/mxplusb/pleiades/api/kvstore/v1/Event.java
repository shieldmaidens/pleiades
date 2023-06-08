// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kvstore/v1/kv.proto

package com.github.mxplusb.pleiades.api.kvstore.v1;

/**
 * Protobuf type {@code kvstore.v1.Event}
 */
public final class Event extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:kvstore.v1.Event)
    EventOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Event.newBuilder() to construct.
  private Event(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Event() {
    type_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Event();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.github.mxplusb.pleiades.api.kvstore.v1.KvProto.internal_static_kvstore_v1_Event_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.github.mxplusb.pleiades.api.kvstore.v1.KvProto.internal_static_kvstore_v1_Event_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.github.mxplusb.pleiades.api.kvstore.v1.Event.class, com.github.mxplusb.pleiades.api.kvstore.v1.Event.Builder.class);
  }

  private int bitField0_;
  public static final int TYPE_FIELD_NUMBER = 1;
  private int type_ = 0;
  /**
   * <pre>
   * type is the kind of event. If type is a PUT, it indicates
   * new data has been stored to the key. If type is a DELETE,
   * it indicates the key was deleted.
   * </pre>
   *
   * <code>.kvstore.v1.KeyOperationType type = 1 [json_name = "type"];</code>
   * @return The enum numeric value on the wire for type.
   */
  @java.lang.Override public int getTypeValue() {
    return type_;
  }
  /**
   * <pre>
   * type is the kind of event. If type is a PUT, it indicates
   * new data has been stored to the key. If type is a DELETE,
   * it indicates the key was deleted.
   * </pre>
   *
   * <code>.kvstore.v1.KeyOperationType type = 1 [json_name = "type"];</code>
   * @return The type.
   */
  @java.lang.Override public com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType getType() {
    com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType result = com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType.forNumber(type_);
    return result == null ? com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType.UNRECOGNIZED : result;
  }

  public static final int KV_FIELD_NUMBER = 2;
  private com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue kv_;
  /**
   * <pre>
   * kv holds the KeyValue for the event.
   * A PUT event contains current kv pair.
   * A PUT event with kv.Version=1 indicates the creation of a key.
   * A DELETE/EXPIRE event contains the deleted key with
   * its modification revision set to the revision of deletion.
   * </pre>
   *
   * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
   * @return Whether the kv field is set.
   */
  @java.lang.Override
  public boolean hasKv() {
    return kv_ != null;
  }
  /**
   * <pre>
   * kv holds the KeyValue for the event.
   * A PUT event contains current kv pair.
   * A PUT event with kv.Version=1 indicates the creation of a key.
   * A DELETE/EXPIRE event contains the deleted key with
   * its modification revision set to the revision of deletion.
   * </pre>
   *
   * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
   * @return The kv.
   */
  @java.lang.Override
  public com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue getKv() {
    return kv_ == null ? com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.getDefaultInstance() : kv_;
  }
  /**
   * <pre>
   * kv holds the KeyValue for the event.
   * A PUT event contains current kv pair.
   * A PUT event with kv.Version=1 indicates the creation of a key.
   * A DELETE/EXPIRE event contains the deleted key with
   * its modification revision set to the revision of deletion.
   * </pre>
   *
   * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
   */
  @java.lang.Override
  public com.github.mxplusb.pleiades.api.kvstore.v1.KeyValueOrBuilder getKvOrBuilder() {
    return kv_ == null ? com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.getDefaultInstance() : kv_;
  }

  public static final int PREV_KV_FIELD_NUMBER = 3;
  private com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue prevKv_;
  /**
   * <pre>
   * prev_kv holds the key-value pair before the event happens.
   * </pre>
   *
   * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
   * @return Whether the prevKv field is set.
   */
  @java.lang.Override
  public boolean hasPrevKv() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <pre>
   * prev_kv holds the key-value pair before the event happens.
   * </pre>
   *
   * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
   * @return The prevKv.
   */
  @java.lang.Override
  public com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue getPrevKv() {
    return prevKv_ == null ? com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.getDefaultInstance() : prevKv_;
  }
  /**
   * <pre>
   * prev_kv holds the key-value pair before the event happens.
   * </pre>
   *
   * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
   */
  @java.lang.Override
  public com.github.mxplusb.pleiades.api.kvstore.v1.KeyValueOrBuilder getPrevKvOrBuilder() {
    return prevKv_ == null ? com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.getDefaultInstance() : prevKv_;
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
    if (type_ != com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType.KEY_OPERATION_TYPE_UNSPECIFIED.getNumber()) {
      output.writeEnum(1, type_);
    }
    if (kv_ != null) {
      output.writeMessage(2, getKv());
    }
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(3, getPrevKv());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (type_ != com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType.KEY_OPERATION_TYPE_UNSPECIFIED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, type_);
    }
    if (kv_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getKv());
    }
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getPrevKv());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.github.mxplusb.pleiades.api.kvstore.v1.Event)) {
      return super.equals(obj);
    }
    com.github.mxplusb.pleiades.api.kvstore.v1.Event other = (com.github.mxplusb.pleiades.api.kvstore.v1.Event) obj;

    if (type_ != other.type_) return false;
    if (hasKv() != other.hasKv()) return false;
    if (hasKv()) {
      if (!getKv()
          .equals(other.getKv())) return false;
    }
    if (hasPrevKv() != other.hasPrevKv()) return false;
    if (hasPrevKv()) {
      if (!getPrevKv()
          .equals(other.getPrevKv())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + type_;
    if (hasKv()) {
      hash = (37 * hash) + KV_FIELD_NUMBER;
      hash = (53 * hash) + getKv().hashCode();
    }
    if (hasPrevKv()) {
      hash = (37 * hash) + PREV_KV_FIELD_NUMBER;
      hash = (53 * hash) + getPrevKv().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event parseFrom(
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
  public static Builder newBuilder(com.github.mxplusb.pleiades.api.kvstore.v1.Event prototype) {
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
   * Protobuf type {@code kvstore.v1.Event}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:kvstore.v1.Event)
      com.github.mxplusb.pleiades.api.kvstore.v1.EventOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.github.mxplusb.pleiades.api.kvstore.v1.KvProto.internal_static_kvstore_v1_Event_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.github.mxplusb.pleiades.api.kvstore.v1.KvProto.internal_static_kvstore_v1_Event_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.github.mxplusb.pleiades.api.kvstore.v1.Event.class, com.github.mxplusb.pleiades.api.kvstore.v1.Event.Builder.class);
    }

    // Construct using com.github.mxplusb.pleiades.api.kvstore.v1.Event.newBuilder()
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
        getKvFieldBuilder();
        getPrevKvFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      type_ = 0;
      kv_ = null;
      if (kvBuilder_ != null) {
        kvBuilder_.dispose();
        kvBuilder_ = null;
      }
      prevKv_ = null;
      if (prevKvBuilder_ != null) {
        prevKvBuilder_.dispose();
        prevKvBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.github.mxplusb.pleiades.api.kvstore.v1.KvProto.internal_static_kvstore_v1_Event_descriptor;
    }

    @java.lang.Override
    public com.github.mxplusb.pleiades.api.kvstore.v1.Event getDefaultInstanceForType() {
      return com.github.mxplusb.pleiades.api.kvstore.v1.Event.getDefaultInstance();
    }

    @java.lang.Override
    public com.github.mxplusb.pleiades.api.kvstore.v1.Event build() {
      com.github.mxplusb.pleiades.api.kvstore.v1.Event result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.github.mxplusb.pleiades.api.kvstore.v1.Event buildPartial() {
      com.github.mxplusb.pleiades.api.kvstore.v1.Event result = new com.github.mxplusb.pleiades.api.kvstore.v1.Event(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.github.mxplusb.pleiades.api.kvstore.v1.Event result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.type_ = type_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.kv_ = kvBuilder_ == null
            ? kv_
            : kvBuilder_.build();
      }
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.prevKv_ = prevKvBuilder_ == null
            ? prevKv_
            : prevKvBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      result.bitField0_ |= to_bitField0_;
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
      if (other instanceof com.github.mxplusb.pleiades.api.kvstore.v1.Event) {
        return mergeFrom((com.github.mxplusb.pleiades.api.kvstore.v1.Event)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.github.mxplusb.pleiades.api.kvstore.v1.Event other) {
      if (other == com.github.mxplusb.pleiades.api.kvstore.v1.Event.getDefaultInstance()) return this;
      if (other.type_ != 0) {
        setTypeValue(other.getTypeValue());
      }
      if (other.hasKv()) {
        mergeKv(other.getKv());
      }
      if (other.hasPrevKv()) {
        mergePrevKv(other.getPrevKv());
      }
      this.mergeUnknownFields(other.getUnknownFields());
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
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              type_ = input.readEnum();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 18: {
              input.readMessage(
                  getKvFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 26: {
              input.readMessage(
                  getPrevKvFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000004;
              break;
            } // case 26
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private int type_ = 0;
    /**
     * <pre>
     * type is the kind of event. If type is a PUT, it indicates
     * new data has been stored to the key. If type is a DELETE,
     * it indicates the key was deleted.
     * </pre>
     *
     * <code>.kvstore.v1.KeyOperationType type = 1 [json_name = "type"];</code>
     * @return The enum numeric value on the wire for type.
     */
    @java.lang.Override public int getTypeValue() {
      return type_;
    }
    /**
     * <pre>
     * type is the kind of event. If type is a PUT, it indicates
     * new data has been stored to the key. If type is a DELETE,
     * it indicates the key was deleted.
     * </pre>
     *
     * <code>.kvstore.v1.KeyOperationType type = 1 [json_name = "type"];</code>
     * @param value The enum numeric value on the wire for type to set.
     * @return This builder for chaining.
     */
    public Builder setTypeValue(int value) {
      type_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * type is the kind of event. If type is a PUT, it indicates
     * new data has been stored to the key. If type is a DELETE,
     * it indicates the key was deleted.
     * </pre>
     *
     * <code>.kvstore.v1.KeyOperationType type = 1 [json_name = "type"];</code>
     * @return The type.
     */
    @java.lang.Override
    public com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType getType() {
      com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType result = com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType.forNumber(type_);
      return result == null ? com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType.UNRECOGNIZED : result;
    }
    /**
     * <pre>
     * type is the kind of event. If type is a PUT, it indicates
     * new data has been stored to the key. If type is a DELETE,
     * it indicates the key was deleted.
     * </pre>
     *
     * <code>.kvstore.v1.KeyOperationType type = 1 [json_name = "type"];</code>
     * @param value The type to set.
     * @return This builder for chaining.
     */
    public Builder setType(com.github.mxplusb.pleiades.api.kvstore.v1.KeyOperationType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000001;
      type_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * type is the kind of event. If type is a PUT, it indicates
     * new data has been stored to the key. If type is a DELETE,
     * it indicates the key was deleted.
     * </pre>
     *
     * <code>.kvstore.v1.KeyOperationType type = 1 [json_name = "type"];</code>
     * @return This builder for chaining.
     */
    public Builder clearType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      type_ = 0;
      onChanged();
      return this;
    }

    private com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue kv_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.Builder, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValueOrBuilder> kvBuilder_;
    /**
     * <pre>
     * kv holds the KeyValue for the event.
     * A PUT event contains current kv pair.
     * A PUT event with kv.Version=1 indicates the creation of a key.
     * A DELETE/EXPIRE event contains the deleted key with
     * its modification revision set to the revision of deletion.
     * </pre>
     *
     * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
     * @return Whether the kv field is set.
     */
    public boolean hasKv() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <pre>
     * kv holds the KeyValue for the event.
     * A PUT event contains current kv pair.
     * A PUT event with kv.Version=1 indicates the creation of a key.
     * A DELETE/EXPIRE event contains the deleted key with
     * its modification revision set to the revision of deletion.
     * </pre>
     *
     * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
     * @return The kv.
     */
    public com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue getKv() {
      if (kvBuilder_ == null) {
        return kv_ == null ? com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.getDefaultInstance() : kv_;
      } else {
        return kvBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * kv holds the KeyValue for the event.
     * A PUT event contains current kv pair.
     * A PUT event with kv.Version=1 indicates the creation of a key.
     * A DELETE/EXPIRE event contains the deleted key with
     * its modification revision set to the revision of deletion.
     * </pre>
     *
     * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
     */
    public Builder setKv(com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue value) {
      if (kvBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        kv_ = value;
      } else {
        kvBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * kv holds the KeyValue for the event.
     * A PUT event contains current kv pair.
     * A PUT event with kv.Version=1 indicates the creation of a key.
     * A DELETE/EXPIRE event contains the deleted key with
     * its modification revision set to the revision of deletion.
     * </pre>
     *
     * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
     */
    public Builder setKv(
        com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.Builder builderForValue) {
      if (kvBuilder_ == null) {
        kv_ = builderForValue.build();
      } else {
        kvBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * kv holds the KeyValue for the event.
     * A PUT event contains current kv pair.
     * A PUT event with kv.Version=1 indicates the creation of a key.
     * A DELETE/EXPIRE event contains the deleted key with
     * its modification revision set to the revision of deletion.
     * </pre>
     *
     * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
     */
    public Builder mergeKv(com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue value) {
      if (kvBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
          kv_ != null &&
          kv_ != com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.getDefaultInstance()) {
          getKvBuilder().mergeFrom(value);
        } else {
          kv_ = value;
        }
      } else {
        kvBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * kv holds the KeyValue for the event.
     * A PUT event contains current kv pair.
     * A PUT event with kv.Version=1 indicates the creation of a key.
     * A DELETE/EXPIRE event contains the deleted key with
     * its modification revision set to the revision of deletion.
     * </pre>
     *
     * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
     */
    public Builder clearKv() {
      bitField0_ = (bitField0_ & ~0x00000002);
      kv_ = null;
      if (kvBuilder_ != null) {
        kvBuilder_.dispose();
        kvBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <pre>
     * kv holds the KeyValue for the event.
     * A PUT event contains current kv pair.
     * A PUT event with kv.Version=1 indicates the creation of a key.
     * A DELETE/EXPIRE event contains the deleted key with
     * its modification revision set to the revision of deletion.
     * </pre>
     *
     * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
     */
    public com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.Builder getKvBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getKvFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * kv holds the KeyValue for the event.
     * A PUT event contains current kv pair.
     * A PUT event with kv.Version=1 indicates the creation of a key.
     * A DELETE/EXPIRE event contains the deleted key with
     * its modification revision set to the revision of deletion.
     * </pre>
     *
     * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
     */
    public com.github.mxplusb.pleiades.api.kvstore.v1.KeyValueOrBuilder getKvOrBuilder() {
      if (kvBuilder_ != null) {
        return kvBuilder_.getMessageOrBuilder();
      } else {
        return kv_ == null ?
            com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.getDefaultInstance() : kv_;
      }
    }
    /**
     * <pre>
     * kv holds the KeyValue for the event.
     * A PUT event contains current kv pair.
     * A PUT event with kv.Version=1 indicates the creation of a key.
     * A DELETE/EXPIRE event contains the deleted key with
     * its modification revision set to the revision of deletion.
     * </pre>
     *
     * <code>.kvstore.v1.KeyValue kv = 2 [json_name = "kv"];</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.Builder, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValueOrBuilder> 
        getKvFieldBuilder() {
      if (kvBuilder_ == null) {
        kvBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.Builder, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValueOrBuilder>(
                getKv(),
                getParentForChildren(),
                isClean());
        kv_ = null;
      }
      return kvBuilder_;
    }

    private com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue prevKv_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.Builder, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValueOrBuilder> prevKvBuilder_;
    /**
     * <pre>
     * prev_kv holds the key-value pair before the event happens.
     * </pre>
     *
     * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
     * @return Whether the prevKv field is set.
     */
    public boolean hasPrevKv() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <pre>
     * prev_kv holds the key-value pair before the event happens.
     * </pre>
     *
     * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
     * @return The prevKv.
     */
    public com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue getPrevKv() {
      if (prevKvBuilder_ == null) {
        return prevKv_ == null ? com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.getDefaultInstance() : prevKv_;
      } else {
        return prevKvBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * prev_kv holds the key-value pair before the event happens.
     * </pre>
     *
     * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
     */
    public Builder setPrevKv(com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue value) {
      if (prevKvBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        prevKv_ = value;
      } else {
        prevKvBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * prev_kv holds the key-value pair before the event happens.
     * </pre>
     *
     * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
     */
    public Builder setPrevKv(
        com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.Builder builderForValue) {
      if (prevKvBuilder_ == null) {
        prevKv_ = builderForValue.build();
      } else {
        prevKvBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * prev_kv holds the key-value pair before the event happens.
     * </pre>
     *
     * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
     */
    public Builder mergePrevKv(com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue value) {
      if (prevKvBuilder_ == null) {
        if (((bitField0_ & 0x00000004) != 0) &&
          prevKv_ != null &&
          prevKv_ != com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.getDefaultInstance()) {
          getPrevKvBuilder().mergeFrom(value);
        } else {
          prevKv_ = value;
        }
      } else {
        prevKvBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * prev_kv holds the key-value pair before the event happens.
     * </pre>
     *
     * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
     */
    public Builder clearPrevKv() {
      bitField0_ = (bitField0_ & ~0x00000004);
      prevKv_ = null;
      if (prevKvBuilder_ != null) {
        prevKvBuilder_.dispose();
        prevKvBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <pre>
     * prev_kv holds the key-value pair before the event happens.
     * </pre>
     *
     * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
     */
    public com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.Builder getPrevKvBuilder() {
      bitField0_ |= 0x00000004;
      onChanged();
      return getPrevKvFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * prev_kv holds the key-value pair before the event happens.
     * </pre>
     *
     * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
     */
    public com.github.mxplusb.pleiades.api.kvstore.v1.KeyValueOrBuilder getPrevKvOrBuilder() {
      if (prevKvBuilder_ != null) {
        return prevKvBuilder_.getMessageOrBuilder();
      } else {
        return prevKv_ == null ?
            com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.getDefaultInstance() : prevKv_;
      }
    }
    /**
     * <pre>
     * prev_kv holds the key-value pair before the event happens.
     * </pre>
     *
     * <code>optional .kvstore.v1.KeyValue prev_kv = 3 [json_name = "prevKv"];</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.Builder, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValueOrBuilder> 
        getPrevKvFieldBuilder() {
      if (prevKvBuilder_ == null) {
        prevKvBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValue.Builder, com.github.mxplusb.pleiades.api.kvstore.v1.KeyValueOrBuilder>(
                getPrevKv(),
                getParentForChildren(),
                isClean());
        prevKv_ = null;
      }
      return prevKvBuilder_;
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


    // @@protoc_insertion_point(builder_scope:kvstore.v1.Event)
  }

  // @@protoc_insertion_point(class_scope:kvstore.v1.Event)
  private static final com.github.mxplusb.pleiades.api.kvstore.v1.Event DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.github.mxplusb.pleiades.api.kvstore.v1.Event();
  }

  public static com.github.mxplusb.pleiades.api.kvstore.v1.Event getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Event>
      PARSER = new com.google.protobuf.AbstractParser<Event>() {
    @java.lang.Override
    public Event parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<Event> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Event> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.github.mxplusb.pleiades.api.kvstore.v1.Event getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
