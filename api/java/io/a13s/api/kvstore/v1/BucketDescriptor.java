// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kvstore/v1/kv.proto

package io.a13s.api.kvstore.v1;

/**
 * Protobuf type {@code kvstore.v1.BucketDescriptor}
 */
public final class BucketDescriptor extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:kvstore.v1.BucketDescriptor)
    BucketDescriptorOrBuilder {
private static final long serialVersionUID = 0L;
  // Use BucketDescriptor.newBuilder() to construct.
  private BucketDescriptor(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BucketDescriptor() {
    owner_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new BucketDescriptor();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.a13s.api.kvstore.v1.KvProto.internal_static_kvstore_v1_BucketDescriptor_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.a13s.api.kvstore.v1.KvProto.internal_static_kvstore_v1_BucketDescriptor_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.a13s.api.kvstore.v1.BucketDescriptor.class, io.a13s.api.kvstore.v1.BucketDescriptor.Builder.class);
  }

  public static final int OWNER_FIELD_NUMBER = 1;
  private volatile java.lang.Object owner_;
  /**
   * <code>string owner = 1 [json_name = "owner"];</code>
   * @return The owner.
   */
  @java.lang.Override
  public java.lang.String getOwner() {
    java.lang.Object ref = owner_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      owner_ = s;
      return s;
    }
  }
  /**
   * <code>string owner = 1 [json_name = "owner"];</code>
   * @return The bytes for owner.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getOwnerBytes() {
    java.lang.Object ref = owner_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      owner_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SIZE_FIELD_NUMBER = 2;
  private long size_;
  /**
   * <code>uint64 size = 2 [json_name = "size"];</code>
   * @return The size.
   */
  @java.lang.Override
  public long getSize() {
    return size_;
  }

  public static final int KEY_COUNT_FIELD_NUMBER = 3;
  private long keyCount_;
  /**
   * <code>uint64 key_count = 3 [json_name = "keyCount"];</code>
   * @return The keyCount.
   */
  @java.lang.Override
  public long getKeyCount() {
    return keyCount_;
  }

  public static final int CREATED_FIELD_NUMBER = 4;
  private com.google.protobuf.Timestamp created_;
  /**
   * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
   * @return Whether the created field is set.
   */
  @java.lang.Override
  public boolean hasCreated() {
    return created_ != null;
  }
  /**
   * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
   * @return The created.
   */
  @java.lang.Override
  public com.google.protobuf.Timestamp getCreated() {
    return created_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : created_;
  }
  /**
   * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
   */
  @java.lang.Override
  public com.google.protobuf.TimestampOrBuilder getCreatedOrBuilder() {
    return getCreated();
  }

  public static final int LAST_UPDATED_FIELD_NUMBER = 5;
  private com.google.protobuf.Timestamp lastUpdated_;
  /**
   * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
   * @return Whether the lastUpdated field is set.
   */
  @java.lang.Override
  public boolean hasLastUpdated() {
    return lastUpdated_ != null;
  }
  /**
   * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
   * @return The lastUpdated.
   */
  @java.lang.Override
  public com.google.protobuf.Timestamp getLastUpdated() {
    return lastUpdated_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : lastUpdated_;
  }
  /**
   * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
   */
  @java.lang.Override
  public com.google.protobuf.TimestampOrBuilder getLastUpdatedOrBuilder() {
    return getLastUpdated();
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(owner_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, owner_);
    }
    if (size_ != 0L) {
      output.writeUInt64(2, size_);
    }
    if (keyCount_ != 0L) {
      output.writeUInt64(3, keyCount_);
    }
    if (created_ != null) {
      output.writeMessage(4, getCreated());
    }
    if (lastUpdated_ != null) {
      output.writeMessage(5, getLastUpdated());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(owner_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, owner_);
    }
    if (size_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, size_);
    }
    if (keyCount_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(3, keyCount_);
    }
    if (created_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getCreated());
    }
    if (lastUpdated_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, getLastUpdated());
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
    if (!(obj instanceof io.a13s.api.kvstore.v1.BucketDescriptor)) {
      return super.equals(obj);
    }
    io.a13s.api.kvstore.v1.BucketDescriptor other = (io.a13s.api.kvstore.v1.BucketDescriptor) obj;

    if (!getOwner()
        .equals(other.getOwner())) return false;
    if (getSize()
        != other.getSize()) return false;
    if (getKeyCount()
        != other.getKeyCount()) return false;
    if (hasCreated() != other.hasCreated()) return false;
    if (hasCreated()) {
      if (!getCreated()
          .equals(other.getCreated())) return false;
    }
    if (hasLastUpdated() != other.hasLastUpdated()) return false;
    if (hasLastUpdated()) {
      if (!getLastUpdated()
          .equals(other.getLastUpdated())) return false;
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
    hash = (37 * hash) + OWNER_FIELD_NUMBER;
    hash = (53 * hash) + getOwner().hashCode();
    hash = (37 * hash) + SIZE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getSize());
    hash = (37 * hash) + KEY_COUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getKeyCount());
    if (hasCreated()) {
      hash = (37 * hash) + CREATED_FIELD_NUMBER;
      hash = (53 * hash) + getCreated().hashCode();
    }
    if (hasLastUpdated()) {
      hash = (37 * hash) + LAST_UPDATED_FIELD_NUMBER;
      hash = (53 * hash) + getLastUpdated().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.a13s.api.kvstore.v1.BucketDescriptor parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.a13s.api.kvstore.v1.BucketDescriptor parseFrom(
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
  public static Builder newBuilder(io.a13s.api.kvstore.v1.BucketDescriptor prototype) {
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
   * Protobuf type {@code kvstore.v1.BucketDescriptor}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:kvstore.v1.BucketDescriptor)
      io.a13s.api.kvstore.v1.BucketDescriptorOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.a13s.api.kvstore.v1.KvProto.internal_static_kvstore_v1_BucketDescriptor_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.a13s.api.kvstore.v1.KvProto.internal_static_kvstore_v1_BucketDescriptor_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.a13s.api.kvstore.v1.BucketDescriptor.class, io.a13s.api.kvstore.v1.BucketDescriptor.Builder.class);
    }

    // Construct using io.a13s.api.kvstore.v1.BucketDescriptor.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      owner_ = "";

      size_ = 0L;

      keyCount_ = 0L;

      if (createdBuilder_ == null) {
        created_ = null;
      } else {
        created_ = null;
        createdBuilder_ = null;
      }
      if (lastUpdatedBuilder_ == null) {
        lastUpdated_ = null;
      } else {
        lastUpdated_ = null;
        lastUpdatedBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.a13s.api.kvstore.v1.KvProto.internal_static_kvstore_v1_BucketDescriptor_descriptor;
    }

    @java.lang.Override
    public io.a13s.api.kvstore.v1.BucketDescriptor getDefaultInstanceForType() {
      return io.a13s.api.kvstore.v1.BucketDescriptor.getDefaultInstance();
    }

    @java.lang.Override
    public io.a13s.api.kvstore.v1.BucketDescriptor build() {
      io.a13s.api.kvstore.v1.BucketDescriptor result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.a13s.api.kvstore.v1.BucketDescriptor buildPartial() {
      io.a13s.api.kvstore.v1.BucketDescriptor result = new io.a13s.api.kvstore.v1.BucketDescriptor(this);
      result.owner_ = owner_;
      result.size_ = size_;
      result.keyCount_ = keyCount_;
      if (createdBuilder_ == null) {
        result.created_ = created_;
      } else {
        result.created_ = createdBuilder_.build();
      }
      if (lastUpdatedBuilder_ == null) {
        result.lastUpdated_ = lastUpdated_;
      } else {
        result.lastUpdated_ = lastUpdatedBuilder_.build();
      }
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
      if (other instanceof io.a13s.api.kvstore.v1.BucketDescriptor) {
        return mergeFrom((io.a13s.api.kvstore.v1.BucketDescriptor)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.a13s.api.kvstore.v1.BucketDescriptor other) {
      if (other == io.a13s.api.kvstore.v1.BucketDescriptor.getDefaultInstance()) return this;
      if (!other.getOwner().isEmpty()) {
        owner_ = other.owner_;
        onChanged();
      }
      if (other.getSize() != 0L) {
        setSize(other.getSize());
      }
      if (other.getKeyCount() != 0L) {
        setKeyCount(other.getKeyCount());
      }
      if (other.hasCreated()) {
        mergeCreated(other.getCreated());
      }
      if (other.hasLastUpdated()) {
        mergeLastUpdated(other.getLastUpdated());
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
            case 10: {
              owner_ = input.readStringRequireUtf8();

              break;
            } // case 10
            case 16: {
              size_ = input.readUInt64();

              break;
            } // case 16
            case 24: {
              keyCount_ = input.readUInt64();

              break;
            } // case 24
            case 34: {
              input.readMessage(
                  getCreatedFieldBuilder().getBuilder(),
                  extensionRegistry);

              break;
            } // case 34
            case 42: {
              input.readMessage(
                  getLastUpdatedFieldBuilder().getBuilder(),
                  extensionRegistry);

              break;
            } // case 42
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

    private java.lang.Object owner_ = "";
    /**
     * <code>string owner = 1 [json_name = "owner"];</code>
     * @return The owner.
     */
    public java.lang.String getOwner() {
      java.lang.Object ref = owner_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        owner_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string owner = 1 [json_name = "owner"];</code>
     * @return The bytes for owner.
     */
    public com.google.protobuf.ByteString
        getOwnerBytes() {
      java.lang.Object ref = owner_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        owner_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string owner = 1 [json_name = "owner"];</code>
     * @param value The owner to set.
     * @return This builder for chaining.
     */
    public Builder setOwner(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      owner_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string owner = 1 [json_name = "owner"];</code>
     * @return This builder for chaining.
     */
    public Builder clearOwner() {
      
      owner_ = getDefaultInstance().getOwner();
      onChanged();
      return this;
    }
    /**
     * <code>string owner = 1 [json_name = "owner"];</code>
     * @param value The bytes for owner to set.
     * @return This builder for chaining.
     */
    public Builder setOwnerBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      owner_ = value;
      onChanged();
      return this;
    }

    private long size_ ;
    /**
     * <code>uint64 size = 2 [json_name = "size"];</code>
     * @return The size.
     */
    @java.lang.Override
    public long getSize() {
      return size_;
    }
    /**
     * <code>uint64 size = 2 [json_name = "size"];</code>
     * @param value The size to set.
     * @return This builder for chaining.
     */
    public Builder setSize(long value) {
      
      size_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 size = 2 [json_name = "size"];</code>
     * @return This builder for chaining.
     */
    public Builder clearSize() {
      
      size_ = 0L;
      onChanged();
      return this;
    }

    private long keyCount_ ;
    /**
     * <code>uint64 key_count = 3 [json_name = "keyCount"];</code>
     * @return The keyCount.
     */
    @java.lang.Override
    public long getKeyCount() {
      return keyCount_;
    }
    /**
     * <code>uint64 key_count = 3 [json_name = "keyCount"];</code>
     * @param value The keyCount to set.
     * @return This builder for chaining.
     */
    public Builder setKeyCount(long value) {
      
      keyCount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 key_count = 3 [json_name = "keyCount"];</code>
     * @return This builder for chaining.
     */
    public Builder clearKeyCount() {
      
      keyCount_ = 0L;
      onChanged();
      return this;
    }

    private com.google.protobuf.Timestamp created_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> createdBuilder_;
    /**
     * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
     * @return Whether the created field is set.
     */
    public boolean hasCreated() {
      return createdBuilder_ != null || created_ != null;
    }
    /**
     * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
     * @return The created.
     */
    public com.google.protobuf.Timestamp getCreated() {
      if (createdBuilder_ == null) {
        return created_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : created_;
      } else {
        return createdBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
     */
    public Builder setCreated(com.google.protobuf.Timestamp value) {
      if (createdBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        created_ = value;
        onChanged();
      } else {
        createdBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
     */
    public Builder setCreated(
        com.google.protobuf.Timestamp.Builder builderForValue) {
      if (createdBuilder_ == null) {
        created_ = builderForValue.build();
        onChanged();
      } else {
        createdBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
     */
    public Builder mergeCreated(com.google.protobuf.Timestamp value) {
      if (createdBuilder_ == null) {
        if (created_ != null) {
          created_ =
            com.google.protobuf.Timestamp.newBuilder(created_).mergeFrom(value).buildPartial();
        } else {
          created_ = value;
        }
        onChanged();
      } else {
        createdBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
     */
    public Builder clearCreated() {
      if (createdBuilder_ == null) {
        created_ = null;
        onChanged();
      } else {
        created_ = null;
        createdBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
     */
    public com.google.protobuf.Timestamp.Builder getCreatedBuilder() {
      
      onChanged();
      return getCreatedFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
     */
    public com.google.protobuf.TimestampOrBuilder getCreatedOrBuilder() {
      if (createdBuilder_ != null) {
        return createdBuilder_.getMessageOrBuilder();
      } else {
        return created_ == null ?
            com.google.protobuf.Timestamp.getDefaultInstance() : created_;
      }
    }
    /**
     * <code>.google.protobuf.Timestamp created = 4 [json_name = "created"];</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> 
        getCreatedFieldBuilder() {
      if (createdBuilder_ == null) {
        createdBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder>(
                getCreated(),
                getParentForChildren(),
                isClean());
        created_ = null;
      }
      return createdBuilder_;
    }

    private com.google.protobuf.Timestamp lastUpdated_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> lastUpdatedBuilder_;
    /**
     * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
     * @return Whether the lastUpdated field is set.
     */
    public boolean hasLastUpdated() {
      return lastUpdatedBuilder_ != null || lastUpdated_ != null;
    }
    /**
     * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
     * @return The lastUpdated.
     */
    public com.google.protobuf.Timestamp getLastUpdated() {
      if (lastUpdatedBuilder_ == null) {
        return lastUpdated_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : lastUpdated_;
      } else {
        return lastUpdatedBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
     */
    public Builder setLastUpdated(com.google.protobuf.Timestamp value) {
      if (lastUpdatedBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        lastUpdated_ = value;
        onChanged();
      } else {
        lastUpdatedBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
     */
    public Builder setLastUpdated(
        com.google.protobuf.Timestamp.Builder builderForValue) {
      if (lastUpdatedBuilder_ == null) {
        lastUpdated_ = builderForValue.build();
        onChanged();
      } else {
        lastUpdatedBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
     */
    public Builder mergeLastUpdated(com.google.protobuf.Timestamp value) {
      if (lastUpdatedBuilder_ == null) {
        if (lastUpdated_ != null) {
          lastUpdated_ =
            com.google.protobuf.Timestamp.newBuilder(lastUpdated_).mergeFrom(value).buildPartial();
        } else {
          lastUpdated_ = value;
        }
        onChanged();
      } else {
        lastUpdatedBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
     */
    public Builder clearLastUpdated() {
      if (lastUpdatedBuilder_ == null) {
        lastUpdated_ = null;
        onChanged();
      } else {
        lastUpdated_ = null;
        lastUpdatedBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
     */
    public com.google.protobuf.Timestamp.Builder getLastUpdatedBuilder() {
      
      onChanged();
      return getLastUpdatedFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
     */
    public com.google.protobuf.TimestampOrBuilder getLastUpdatedOrBuilder() {
      if (lastUpdatedBuilder_ != null) {
        return lastUpdatedBuilder_.getMessageOrBuilder();
      } else {
        return lastUpdated_ == null ?
            com.google.protobuf.Timestamp.getDefaultInstance() : lastUpdated_;
      }
    }
    /**
     * <code>.google.protobuf.Timestamp last_updated = 5 [json_name = "lastUpdated"];</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> 
        getLastUpdatedFieldBuilder() {
      if (lastUpdatedBuilder_ == null) {
        lastUpdatedBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder>(
                getLastUpdated(),
                getParentForChildren(),
                isClean());
        lastUpdated_ = null;
      }
      return lastUpdatedBuilder_;
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


    // @@protoc_insertion_point(builder_scope:kvstore.v1.BucketDescriptor)
  }

  // @@protoc_insertion_point(class_scope:kvstore.v1.BucketDescriptor)
  private static final io.a13s.api.kvstore.v1.BucketDescriptor DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.a13s.api.kvstore.v1.BucketDescriptor();
  }

  public static io.a13s.api.kvstore.v1.BucketDescriptor getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BucketDescriptor>
      PARSER = new com.google.protobuf.AbstractParser<BucketDescriptor>() {
    @java.lang.Override
    public BucketDescriptor parsePartialFrom(
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

  public static com.google.protobuf.Parser<BucketDescriptor> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BucketDescriptor> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.a13s.api.kvstore.v1.BucketDescriptor getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

