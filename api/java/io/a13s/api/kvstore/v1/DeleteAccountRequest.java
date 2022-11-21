// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kvstore/v1/kv.proto

package io.a13s.api.kvstore.v1;

/**
 * Protobuf type {@code kvstore.v1.DeleteAccountRequest}
 */
public final class DeleteAccountRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:kvstore.v1.DeleteAccountRequest)
    DeleteAccountRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DeleteAccountRequest.newBuilder() to construct.
  private DeleteAccountRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DeleteAccountRequest() {
    owner_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DeleteAccountRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.a13s.api.kvstore.v1.KvProto.internal_static_kvstore_v1_DeleteAccountRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.a13s.api.kvstore.v1.KvProto.internal_static_kvstore_v1_DeleteAccountRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.a13s.api.kvstore.v1.DeleteAccountRequest.class, io.a13s.api.kvstore.v1.DeleteAccountRequest.Builder.class);
  }

  public static final int ACCOUNT_ID_FIELD_NUMBER = 1;
  private long accountId_;
  /**
   * <code>uint64 account_id = 1 [json_name = "accountId"];</code>
   * @return The accountId.
   */
  @java.lang.Override
  public long getAccountId() {
    return accountId_;
  }

  public static final int OWNER_FIELD_NUMBER = 2;
  private volatile java.lang.Object owner_;
  /**
   * <code>string owner = 2 [json_name = "owner"];</code>
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
   * <code>string owner = 2 [json_name = "owner"];</code>
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

  public static final int TRANSACTION_FIELD_NUMBER = 3;
  private io.a13s.api.kvstore.v1.Transaction transaction_;
  /**
   * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
   * @return Whether the transaction field is set.
   */
  @java.lang.Override
  public boolean hasTransaction() {
    return transaction_ != null;
  }
  /**
   * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
   * @return The transaction.
   */
  @java.lang.Override
  public io.a13s.api.kvstore.v1.Transaction getTransaction() {
    return transaction_ == null ? io.a13s.api.kvstore.v1.Transaction.getDefaultInstance() : transaction_;
  }
  /**
   * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
   */
  @java.lang.Override
  public io.a13s.api.kvstore.v1.TransactionOrBuilder getTransactionOrBuilder() {
    return getTransaction();
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
    if (accountId_ != 0L) {
      output.writeUInt64(1, accountId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(owner_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, owner_);
    }
    if (transaction_ != null) {
      output.writeMessage(3, getTransaction());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (accountId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, accountId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(owner_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, owner_);
    }
    if (transaction_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getTransaction());
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
    if (!(obj instanceof io.a13s.api.kvstore.v1.DeleteAccountRequest)) {
      return super.equals(obj);
    }
    io.a13s.api.kvstore.v1.DeleteAccountRequest other = (io.a13s.api.kvstore.v1.DeleteAccountRequest) obj;

    if (getAccountId()
        != other.getAccountId()) return false;
    if (!getOwner()
        .equals(other.getOwner())) return false;
    if (hasTransaction() != other.hasTransaction()) return false;
    if (hasTransaction()) {
      if (!getTransaction()
          .equals(other.getTransaction())) return false;
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
    hash = (37 * hash) + ACCOUNT_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAccountId());
    hash = (37 * hash) + OWNER_FIELD_NUMBER;
    hash = (53 * hash) + getOwner().hashCode();
    if (hasTransaction()) {
      hash = (37 * hash) + TRANSACTION_FIELD_NUMBER;
      hash = (53 * hash) + getTransaction().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.a13s.api.kvstore.v1.DeleteAccountRequest parseFrom(
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
  public static Builder newBuilder(io.a13s.api.kvstore.v1.DeleteAccountRequest prototype) {
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
   * Protobuf type {@code kvstore.v1.DeleteAccountRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:kvstore.v1.DeleteAccountRequest)
      io.a13s.api.kvstore.v1.DeleteAccountRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.a13s.api.kvstore.v1.KvProto.internal_static_kvstore_v1_DeleteAccountRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.a13s.api.kvstore.v1.KvProto.internal_static_kvstore_v1_DeleteAccountRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.a13s.api.kvstore.v1.DeleteAccountRequest.class, io.a13s.api.kvstore.v1.DeleteAccountRequest.Builder.class);
    }

    // Construct using io.a13s.api.kvstore.v1.DeleteAccountRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      accountId_ = 0L;

      owner_ = "";

      if (transactionBuilder_ == null) {
        transaction_ = null;
      } else {
        transaction_ = null;
        transactionBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.a13s.api.kvstore.v1.KvProto.internal_static_kvstore_v1_DeleteAccountRequest_descriptor;
    }

    @java.lang.Override
    public io.a13s.api.kvstore.v1.DeleteAccountRequest getDefaultInstanceForType() {
      return io.a13s.api.kvstore.v1.DeleteAccountRequest.getDefaultInstance();
    }

    @java.lang.Override
    public io.a13s.api.kvstore.v1.DeleteAccountRequest build() {
      io.a13s.api.kvstore.v1.DeleteAccountRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.a13s.api.kvstore.v1.DeleteAccountRequest buildPartial() {
      io.a13s.api.kvstore.v1.DeleteAccountRequest result = new io.a13s.api.kvstore.v1.DeleteAccountRequest(this);
      result.accountId_ = accountId_;
      result.owner_ = owner_;
      if (transactionBuilder_ == null) {
        result.transaction_ = transaction_;
      } else {
        result.transaction_ = transactionBuilder_.build();
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
      if (other instanceof io.a13s.api.kvstore.v1.DeleteAccountRequest) {
        return mergeFrom((io.a13s.api.kvstore.v1.DeleteAccountRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.a13s.api.kvstore.v1.DeleteAccountRequest other) {
      if (other == io.a13s.api.kvstore.v1.DeleteAccountRequest.getDefaultInstance()) return this;
      if (other.getAccountId() != 0L) {
        setAccountId(other.getAccountId());
      }
      if (!other.getOwner().isEmpty()) {
        owner_ = other.owner_;
        onChanged();
      }
      if (other.hasTransaction()) {
        mergeTransaction(other.getTransaction());
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
              accountId_ = input.readUInt64();

              break;
            } // case 8
            case 18: {
              owner_ = input.readStringRequireUtf8();

              break;
            } // case 18
            case 26: {
              input.readMessage(
                  getTransactionFieldBuilder().getBuilder(),
                  extensionRegistry);

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

    private long accountId_ ;
    /**
     * <code>uint64 account_id = 1 [json_name = "accountId"];</code>
     * @return The accountId.
     */
    @java.lang.Override
    public long getAccountId() {
      return accountId_;
    }
    /**
     * <code>uint64 account_id = 1 [json_name = "accountId"];</code>
     * @param value The accountId to set.
     * @return This builder for chaining.
     */
    public Builder setAccountId(long value) {
      
      accountId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 account_id = 1 [json_name = "accountId"];</code>
     * @return This builder for chaining.
     */
    public Builder clearAccountId() {
      
      accountId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object owner_ = "";
    /**
     * <code>string owner = 2 [json_name = "owner"];</code>
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
     * <code>string owner = 2 [json_name = "owner"];</code>
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
     * <code>string owner = 2 [json_name = "owner"];</code>
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
     * <code>string owner = 2 [json_name = "owner"];</code>
     * @return This builder for chaining.
     */
    public Builder clearOwner() {
      
      owner_ = getDefaultInstance().getOwner();
      onChanged();
      return this;
    }
    /**
     * <code>string owner = 2 [json_name = "owner"];</code>
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

    private io.a13s.api.kvstore.v1.Transaction transaction_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.a13s.api.kvstore.v1.Transaction, io.a13s.api.kvstore.v1.Transaction.Builder, io.a13s.api.kvstore.v1.TransactionOrBuilder> transactionBuilder_;
    /**
     * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
     * @return Whether the transaction field is set.
     */
    public boolean hasTransaction() {
      return transactionBuilder_ != null || transaction_ != null;
    }
    /**
     * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
     * @return The transaction.
     */
    public io.a13s.api.kvstore.v1.Transaction getTransaction() {
      if (transactionBuilder_ == null) {
        return transaction_ == null ? io.a13s.api.kvstore.v1.Transaction.getDefaultInstance() : transaction_;
      } else {
        return transactionBuilder_.getMessage();
      }
    }
    /**
     * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
     */
    public Builder setTransaction(io.a13s.api.kvstore.v1.Transaction value) {
      if (transactionBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        transaction_ = value;
        onChanged();
      } else {
        transactionBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
     */
    public Builder setTransaction(
        io.a13s.api.kvstore.v1.Transaction.Builder builderForValue) {
      if (transactionBuilder_ == null) {
        transaction_ = builderForValue.build();
        onChanged();
      } else {
        transactionBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
     */
    public Builder mergeTransaction(io.a13s.api.kvstore.v1.Transaction value) {
      if (transactionBuilder_ == null) {
        if (transaction_ != null) {
          transaction_ =
            io.a13s.api.kvstore.v1.Transaction.newBuilder(transaction_).mergeFrom(value).buildPartial();
        } else {
          transaction_ = value;
        }
        onChanged();
      } else {
        transactionBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
     */
    public Builder clearTransaction() {
      if (transactionBuilder_ == null) {
        transaction_ = null;
        onChanged();
      } else {
        transaction_ = null;
        transactionBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
     */
    public io.a13s.api.kvstore.v1.Transaction.Builder getTransactionBuilder() {
      
      onChanged();
      return getTransactionFieldBuilder().getBuilder();
    }
    /**
     * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
     */
    public io.a13s.api.kvstore.v1.TransactionOrBuilder getTransactionOrBuilder() {
      if (transactionBuilder_ != null) {
        return transactionBuilder_.getMessageOrBuilder();
      } else {
        return transaction_ == null ?
            io.a13s.api.kvstore.v1.Transaction.getDefaultInstance() : transaction_;
      }
    }
    /**
     * <code>.kvstore.v1.Transaction transaction = 3 [json_name = "transaction"];</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.a13s.api.kvstore.v1.Transaction, io.a13s.api.kvstore.v1.Transaction.Builder, io.a13s.api.kvstore.v1.TransactionOrBuilder> 
        getTransactionFieldBuilder() {
      if (transactionBuilder_ == null) {
        transactionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.a13s.api.kvstore.v1.Transaction, io.a13s.api.kvstore.v1.Transaction.Builder, io.a13s.api.kvstore.v1.TransactionOrBuilder>(
                getTransaction(),
                getParentForChildren(),
                isClean());
        transaction_ = null;
      }
      return transactionBuilder_;
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


    // @@protoc_insertion_point(builder_scope:kvstore.v1.DeleteAccountRequest)
  }

  // @@protoc_insertion_point(class_scope:kvstore.v1.DeleteAccountRequest)
  private static final io.a13s.api.kvstore.v1.DeleteAccountRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.a13s.api.kvstore.v1.DeleteAccountRequest();
  }

  public static io.a13s.api.kvstore.v1.DeleteAccountRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DeleteAccountRequest>
      PARSER = new com.google.protobuf.AbstractParser<DeleteAccountRequest>() {
    @java.lang.Override
    public DeleteAccountRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<DeleteAccountRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DeleteAccountRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.a13s.api.kvstore.v1.DeleteAccountRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

