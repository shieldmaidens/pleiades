// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: raft/v1/raft_host.proto

package io.a13s.api.raft.v1;

/**
 * Protobuf type {@code raft.v1.GetHostConfigResponse}
 */
public final class GetHostConfigResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:raft.v1.GetHostConfigResponse)
    GetHostConfigResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetHostConfigResponse.newBuilder() to construct.
  private GetHostConfigResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetHostConfigResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetHostConfigResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetHostConfigResponse(
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
          case 10: {
            io.a13s.api.raft.v1.HostConfig.Builder subBuilder = null;
            if (config_ != null) {
              subBuilder = config_.toBuilder();
            }
            config_ = input.readMessage(io.a13s.api.raft.v1.HostConfig.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(config_);
              config_ = subBuilder.buildPartial();
            }

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
    return io.a13s.api.raft.v1.RaftHostProto.internal_static_raft_v1_GetHostConfigResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.a13s.api.raft.v1.RaftHostProto.internal_static_raft_v1_GetHostConfigResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.a13s.api.raft.v1.GetHostConfigResponse.class, io.a13s.api.raft.v1.GetHostConfigResponse.Builder.class);
  }

  public static final int CONFIG_FIELD_NUMBER = 1;
  private io.a13s.api.raft.v1.HostConfig config_;
  /**
   * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
   * @return Whether the config field is set.
   */
  @java.lang.Override
  public boolean hasConfig() {
    return config_ != null;
  }
  /**
   * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
   * @return The config.
   */
  @java.lang.Override
  public io.a13s.api.raft.v1.HostConfig getConfig() {
    return config_ == null ? io.a13s.api.raft.v1.HostConfig.getDefaultInstance() : config_;
  }
  /**
   * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
   */
  @java.lang.Override
  public io.a13s.api.raft.v1.HostConfigOrBuilder getConfigOrBuilder() {
    return getConfig();
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
    if (config_ != null) {
      output.writeMessage(1, getConfig());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (config_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getConfig());
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
    if (!(obj instanceof io.a13s.api.raft.v1.GetHostConfigResponse)) {
      return super.equals(obj);
    }
    io.a13s.api.raft.v1.GetHostConfigResponse other = (io.a13s.api.raft.v1.GetHostConfigResponse) obj;

    if (hasConfig() != other.hasConfig()) return false;
    if (hasConfig()) {
      if (!getConfig()
          .equals(other.getConfig())) return false;
    }
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
    if (hasConfig()) {
      hash = (37 * hash) + CONFIG_FIELD_NUMBER;
      hash = (53 * hash) + getConfig().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.a13s.api.raft.v1.GetHostConfigResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.a13s.api.raft.v1.GetHostConfigResponse parseFrom(
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
  public static Builder newBuilder(io.a13s.api.raft.v1.GetHostConfigResponse prototype) {
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
   * Protobuf type {@code raft.v1.GetHostConfigResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:raft.v1.GetHostConfigResponse)
      io.a13s.api.raft.v1.GetHostConfigResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.a13s.api.raft.v1.RaftHostProto.internal_static_raft_v1_GetHostConfigResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.a13s.api.raft.v1.RaftHostProto.internal_static_raft_v1_GetHostConfigResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.a13s.api.raft.v1.GetHostConfigResponse.class, io.a13s.api.raft.v1.GetHostConfigResponse.Builder.class);
    }

    // Construct using io.a13s.api.raft.v1.GetHostConfigResponse.newBuilder()
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
      if (configBuilder_ == null) {
        config_ = null;
      } else {
        config_ = null;
        configBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.a13s.api.raft.v1.RaftHostProto.internal_static_raft_v1_GetHostConfigResponse_descriptor;
    }

    @java.lang.Override
    public io.a13s.api.raft.v1.GetHostConfigResponse getDefaultInstanceForType() {
      return io.a13s.api.raft.v1.GetHostConfigResponse.getDefaultInstance();
    }

    @java.lang.Override
    public io.a13s.api.raft.v1.GetHostConfigResponse build() {
      io.a13s.api.raft.v1.GetHostConfigResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.a13s.api.raft.v1.GetHostConfigResponse buildPartial() {
      io.a13s.api.raft.v1.GetHostConfigResponse result = new io.a13s.api.raft.v1.GetHostConfigResponse(this);
      if (configBuilder_ == null) {
        result.config_ = config_;
      } else {
        result.config_ = configBuilder_.build();
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
      if (other instanceof io.a13s.api.raft.v1.GetHostConfigResponse) {
        return mergeFrom((io.a13s.api.raft.v1.GetHostConfigResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.a13s.api.raft.v1.GetHostConfigResponse other) {
      if (other == io.a13s.api.raft.v1.GetHostConfigResponse.getDefaultInstance()) return this;
      if (other.hasConfig()) {
        mergeConfig(other.getConfig());
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
      io.a13s.api.raft.v1.GetHostConfigResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.a13s.api.raft.v1.GetHostConfigResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.a13s.api.raft.v1.HostConfig config_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.a13s.api.raft.v1.HostConfig, io.a13s.api.raft.v1.HostConfig.Builder, io.a13s.api.raft.v1.HostConfigOrBuilder> configBuilder_;
    /**
     * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
     * @return Whether the config field is set.
     */
    public boolean hasConfig() {
      return configBuilder_ != null || config_ != null;
    }
    /**
     * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
     * @return The config.
     */
    public io.a13s.api.raft.v1.HostConfig getConfig() {
      if (configBuilder_ == null) {
        return config_ == null ? io.a13s.api.raft.v1.HostConfig.getDefaultInstance() : config_;
      } else {
        return configBuilder_.getMessage();
      }
    }
    /**
     * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
     */
    public Builder setConfig(io.a13s.api.raft.v1.HostConfig value) {
      if (configBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        config_ = value;
        onChanged();
      } else {
        configBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
     */
    public Builder setConfig(
        io.a13s.api.raft.v1.HostConfig.Builder builderForValue) {
      if (configBuilder_ == null) {
        config_ = builderForValue.build();
        onChanged();
      } else {
        configBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
     */
    public Builder mergeConfig(io.a13s.api.raft.v1.HostConfig value) {
      if (configBuilder_ == null) {
        if (config_ != null) {
          config_ =
            io.a13s.api.raft.v1.HostConfig.newBuilder(config_).mergeFrom(value).buildPartial();
        } else {
          config_ = value;
        }
        onChanged();
      } else {
        configBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
     */
    public Builder clearConfig() {
      if (configBuilder_ == null) {
        config_ = null;
        onChanged();
      } else {
        config_ = null;
        configBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
     */
    public io.a13s.api.raft.v1.HostConfig.Builder getConfigBuilder() {
      
      onChanged();
      return getConfigFieldBuilder().getBuilder();
    }
    /**
     * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
     */
    public io.a13s.api.raft.v1.HostConfigOrBuilder getConfigOrBuilder() {
      if (configBuilder_ != null) {
        return configBuilder_.getMessageOrBuilder();
      } else {
        return config_ == null ?
            io.a13s.api.raft.v1.HostConfig.getDefaultInstance() : config_;
      }
    }
    /**
     * <code>.raft.v1.HostConfig config = 1 [json_name = "config"];</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.a13s.api.raft.v1.HostConfig, io.a13s.api.raft.v1.HostConfig.Builder, io.a13s.api.raft.v1.HostConfigOrBuilder> 
        getConfigFieldBuilder() {
      if (configBuilder_ == null) {
        configBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.a13s.api.raft.v1.HostConfig, io.a13s.api.raft.v1.HostConfig.Builder, io.a13s.api.raft.v1.HostConfigOrBuilder>(
                getConfig(),
                getParentForChildren(),
                isClean());
        config_ = null;
      }
      return configBuilder_;
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


    // @@protoc_insertion_point(builder_scope:raft.v1.GetHostConfigResponse)
  }

  // @@protoc_insertion_point(class_scope:raft.v1.GetHostConfigResponse)
  private static final io.a13s.api.raft.v1.GetHostConfigResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.a13s.api.raft.v1.GetHostConfigResponse();
  }

  public static io.a13s.api.raft.v1.GetHostConfigResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetHostConfigResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetHostConfigResponse>() {
    @java.lang.Override
    public GetHostConfigResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetHostConfigResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetHostConfigResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetHostConfigResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.a13s.api.raft.v1.GetHostConfigResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

