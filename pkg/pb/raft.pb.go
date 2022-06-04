// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.28.0
// 	protoc        v3.19.4
// source: raft.proto

package pb

import (
	protoreflect "google.golang.org/protobuf/reflect/protoreflect"
	protoimpl "google.golang.org/protobuf/runtime/protoimpl"
	reflect "reflect"
	sync "sync"
)

const (
	// Verify that this generated code is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(20 - protoimpl.MinVersion)
	// Verify that runtime/protoimpl is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(protoimpl.MaxVersion - 20)
)

type IsConfigType int32

const (
	IsConfigType_System      IsConfigType = 0
	IsConfigType_Exchange    IsConfigType = 1
	IsConfigType_CustomerFsm IsConfigType = 100
)

// Enum value maps for IsConfigType.
var (
	IsConfigType_name = map[int32]string{
		0:   "System",
		1:   "Exchange",
		100: "CustomerFsm",
	}
	IsConfigType_value = map[string]int32{
		"System":      0,
		"Exchange":    1,
		"CustomerFsm": 100,
	}
)

func (x IsConfigType) Enum() *IsConfigType {
	p := new(IsConfigType)
	*p = x
	return p
}

func (x IsConfigType) String() string {
	return protoimpl.X.EnumStringOf(x.Descriptor(), protoreflect.EnumNumber(x))
}

func (IsConfigType) Descriptor() protoreflect.EnumDescriptor {
	return file_raft_proto_enumTypes[0].Descriptor()
}

func (IsConfigType) Type() protoreflect.EnumType {
	return &file_raft_proto_enumTypes[0]
}

func (x IsConfigType) Number() protoreflect.EnumNumber {
	return protoreflect.EnumNumber(x)
}

// Deprecated: Use IsConfigType.Descriptor instead.
func (IsConfigType) EnumDescriptor() ([]byte, []int) {
	return file_raft_proto_rawDescGZIP(), []int{0}
}

type RaftConfig struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	NodeId                  uint64       `protobuf:"varint,1,opt,name=NodeId,proto3" json:"NodeId,omitempty"`
	ClusterId               uint64       `protobuf:"varint,2,opt,name=ClusterId,proto3" json:"ClusterId,omitempty"`
	CheckQuorum             bool         `protobuf:"varint,3,opt,name=CheckQuorum,proto3" json:"CheckQuorum,omitempty"`
	ElectionRoundTripTime   uint64       `protobuf:"varint,4,opt,name=ElectionRoundTripTime,proto3" json:"ElectionRoundTripTime,omitempty"`
	HeartbeatRoundTripTime  uint64       `protobuf:"varint,5,opt,name=HeartbeatRoundTripTime,proto3" json:"HeartbeatRoundTripTime,omitempty"`
	SnapshotEntries         uint64       `protobuf:"varint,6,opt,name=SnapshotEntries,proto3" json:"SnapshotEntries,omitempty"`
	CompactionOverhead      uint64       `protobuf:"varint,7,opt,name=CompactionOverhead,proto3" json:"CompactionOverhead,omitempty"`
	OrderedConfigChange     bool         `protobuf:"varint,8,opt,name=OrderedConfigChange,proto3" json:"OrderedConfigChange,omitempty"`
	MaxInMemLogSize         uint64       `protobuf:"varint,9,opt,name=MaxInMemLogSize,proto3" json:"MaxInMemLogSize,omitempty"`
	SnapshotCompressionType uint64       `protobuf:"varint,10,opt,name=SnapshotCompressionType,proto3" json:"SnapshotCompressionType,omitempty"`
	EntryCompressionType    uint64       `protobuf:"varint,11,opt,name=EntryCompressionType,proto3" json:"EntryCompressionType,omitempty"`
	DisableAutoCompactions  bool         `protobuf:"varint,12,opt,name=DisableAutoCompactions,proto3" json:"DisableAutoCompactions,omitempty"`
	IsObserver              bool         `protobuf:"varint,13,opt,name=IsObserver,proto3" json:"IsObserver,omitempty"`
	IsWitness               bool         `protobuf:"varint,14,opt,name=IsWitness,proto3" json:"IsWitness,omitempty"`
	Quiesce                 bool         `protobuf:"varint,15,opt,name=Quiesce,proto3" json:"Quiesce,omitempty"`
	Type                    IsConfigType `protobuf:"varint,16,opt,name=Type,proto3,enum=IsConfigType" json:"Type,omitempty"`
}

func (x *RaftConfig) Reset() {
	*x = RaftConfig{}
	if protoimpl.UnsafeEnabled {
		mi := &file_raft_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *RaftConfig) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*RaftConfig) ProtoMessage() {}

func (x *RaftConfig) ProtoReflect() protoreflect.Message {
	mi := &file_raft_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use RaftConfig.ProtoReflect.Descriptor instead.
func (*RaftConfig) Descriptor() ([]byte, []int) {
	return file_raft_proto_rawDescGZIP(), []int{0}
}

func (x *RaftConfig) GetNodeId() uint64 {
	if x != nil {
		return x.NodeId
	}
	return 0
}

func (x *RaftConfig) GetClusterId() uint64 {
	if x != nil {
		return x.ClusterId
	}
	return 0
}

func (x *RaftConfig) GetCheckQuorum() bool {
	if x != nil {
		return x.CheckQuorum
	}
	return false
}

func (x *RaftConfig) GetElectionRoundTripTime() uint64 {
	if x != nil {
		return x.ElectionRoundTripTime
	}
	return 0
}

func (x *RaftConfig) GetHeartbeatRoundTripTime() uint64 {
	if x != nil {
		return x.HeartbeatRoundTripTime
	}
	return 0
}

func (x *RaftConfig) GetSnapshotEntries() uint64 {
	if x != nil {
		return x.SnapshotEntries
	}
	return 0
}

func (x *RaftConfig) GetCompactionOverhead() uint64 {
	if x != nil {
		return x.CompactionOverhead
	}
	return 0
}

func (x *RaftConfig) GetOrderedConfigChange() bool {
	if x != nil {
		return x.OrderedConfigChange
	}
	return false
}

func (x *RaftConfig) GetMaxInMemLogSize() uint64 {
	if x != nil {
		return x.MaxInMemLogSize
	}
	return 0
}

func (x *RaftConfig) GetSnapshotCompressionType() uint64 {
	if x != nil {
		return x.SnapshotCompressionType
	}
	return 0
}

func (x *RaftConfig) GetEntryCompressionType() uint64 {
	if x != nil {
		return x.EntryCompressionType
	}
	return 0
}

func (x *RaftConfig) GetDisableAutoCompactions() bool {
	if x != nil {
		return x.DisableAutoCompactions
	}
	return false
}

func (x *RaftConfig) GetIsObserver() bool {
	if x != nil {
		return x.IsObserver
	}
	return false
}

func (x *RaftConfig) GetIsWitness() bool {
	if x != nil {
		return x.IsWitness
	}
	return false
}

func (x *RaftConfig) GetQuiesce() bool {
	if x != nil {
		return x.Quiesce
	}
	return false
}

func (x *RaftConfig) GetType() IsConfigType {
	if x != nil {
		return x.Type
	}
	return IsConfigType_System
}

type PutRaftConfigRequest struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Enable bool        `protobuf:"varint,1,opt,name=Enable,proto3" json:"Enable,omitempty"`
	Name   string      `protobuf:"bytes,2,opt,name=Name,proto3" json:"Name,omitempty"`
	Config *RaftConfig `protobuf:"bytes,3,opt,name=Config,proto3" json:"Config,omitempty"`
}

func (x *PutRaftConfigRequest) Reset() {
	*x = PutRaftConfigRequest{}
	if protoimpl.UnsafeEnabled {
		mi := &file_raft_proto_msgTypes[1]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *PutRaftConfigRequest) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*PutRaftConfigRequest) ProtoMessage() {}

func (x *PutRaftConfigRequest) ProtoReflect() protoreflect.Message {
	mi := &file_raft_proto_msgTypes[1]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use PutRaftConfigRequest.ProtoReflect.Descriptor instead.
func (*PutRaftConfigRequest) Descriptor() ([]byte, []int) {
	return file_raft_proto_rawDescGZIP(), []int{1}
}

func (x *PutRaftConfigRequest) GetEnable() bool {
	if x != nil {
		return x.Enable
	}
	return false
}

func (x *PutRaftConfigRequest) GetName() string {
	if x != nil {
		return x.Name
	}
	return ""
}

func (x *PutRaftConfigRequest) GetConfig() *RaftConfig {
	if x != nil {
		return x.Config
	}
	return nil
}

type NewRaftConfigResponse struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Valid bool    `protobuf:"varint,1,opt,name=Valid,proto3" json:"Valid,omitempty"`
	Name  *string `protobuf:"bytes,2,opt,name=Name,proto3,oneof" json:"Name,omitempty"`
	Error *string `protobuf:"bytes,3,opt,name=Error,proto3,oneof" json:"Error,omitempty"`
}

func (x *NewRaftConfigResponse) Reset() {
	*x = NewRaftConfigResponse{}
	if protoimpl.UnsafeEnabled {
		mi := &file_raft_proto_msgTypes[2]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *NewRaftConfigResponse) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*NewRaftConfigResponse) ProtoMessage() {}

func (x *NewRaftConfigResponse) ProtoReflect() protoreflect.Message {
	mi := &file_raft_proto_msgTypes[2]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use NewRaftConfigResponse.ProtoReflect.Descriptor instead.
func (*NewRaftConfigResponse) Descriptor() ([]byte, []int) {
	return file_raft_proto_rawDescGZIP(), []int{2}
}

func (x *NewRaftConfigResponse) GetValid() bool {
	if x != nil {
		return x.Valid
	}
	return false
}

func (x *NewRaftConfigResponse) GetName() string {
	if x != nil && x.Name != nil {
		return *x.Name
	}
	return ""
}

func (x *NewRaftConfigResponse) GetError() string {
	if x != nil && x.Error != nil {
		return *x.Error
	}
	return ""
}

type GetRaftConfigRequest struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Name string `protobuf:"bytes,1,opt,name=Name,proto3" json:"Name,omitempty"`
}

func (x *GetRaftConfigRequest) Reset() {
	*x = GetRaftConfigRequest{}
	if protoimpl.UnsafeEnabled {
		mi := &file_raft_proto_msgTypes[3]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *GetRaftConfigRequest) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*GetRaftConfigRequest) ProtoMessage() {}

func (x *GetRaftConfigRequest) ProtoReflect() protoreflect.Message {
	mi := &file_raft_proto_msgTypes[3]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use GetRaftConfigRequest.ProtoReflect.Descriptor instead.
func (*GetRaftConfigRequest) Descriptor() ([]byte, []int) {
	return file_raft_proto_rawDescGZIP(), []int{3}
}

func (x *GetRaftConfigRequest) GetName() string {
	if x != nil {
		return x.Name
	}
	return ""
}

type GetRaftConfigResponse struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Configuration *RaftConfig `protobuf:"bytes,1,opt,name=Configuration,proto3" json:"Configuration,omitempty"`
}

func (x *GetRaftConfigResponse) Reset() {
	*x = GetRaftConfigResponse{}
	if protoimpl.UnsafeEnabled {
		mi := &file_raft_proto_msgTypes[4]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *GetRaftConfigResponse) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*GetRaftConfigResponse) ProtoMessage() {}

func (x *GetRaftConfigResponse) ProtoReflect() protoreflect.Message {
	mi := &file_raft_proto_msgTypes[4]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use GetRaftConfigResponse.ProtoReflect.Descriptor instead.
func (*GetRaftConfigResponse) Descriptor() ([]byte, []int) {
	return file_raft_proto_rawDescGZIP(), []int{4}
}

func (x *GetRaftConfigResponse) GetConfiguration() *RaftConfig {
	if x != nil {
		return x.Configuration
	}
	return nil
}

type ListRaftConfigsRequest struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields
}

func (x *ListRaftConfigsRequest) Reset() {
	*x = ListRaftConfigsRequest{}
	if protoimpl.UnsafeEnabled {
		mi := &file_raft_proto_msgTypes[5]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *ListRaftConfigsRequest) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*ListRaftConfigsRequest) ProtoMessage() {}

func (x *ListRaftConfigsRequest) ProtoReflect() protoreflect.Message {
	mi := &file_raft_proto_msgTypes[5]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use ListRaftConfigsRequest.ProtoReflect.Descriptor instead.
func (*ListRaftConfigsRequest) Descriptor() ([]byte, []int) {
	return file_raft_proto_rawDescGZIP(), []int{5}
}

type ListRaftConfigsResponse struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	AvailableConfigs map[string]*RaftConfig `protobuf:"bytes,1,rep,name=AvailableConfigs,proto3" json:"AvailableConfigs,omitempty" protobuf_key:"bytes,1,opt,name=key,proto3" protobuf_val:"bytes,2,opt,name=value,proto3"`
}

func (x *ListRaftConfigsResponse) Reset() {
	*x = ListRaftConfigsResponse{}
	if protoimpl.UnsafeEnabled {
		mi := &file_raft_proto_msgTypes[6]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *ListRaftConfigsResponse) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*ListRaftConfigsResponse) ProtoMessage() {}

func (x *ListRaftConfigsResponse) ProtoReflect() protoreflect.Message {
	mi := &file_raft_proto_msgTypes[6]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use ListRaftConfigsResponse.ProtoReflect.Descriptor instead.
func (*ListRaftConfigsResponse) Descriptor() ([]byte, []int) {
	return file_raft_proto_rawDescGZIP(), []int{6}
}

func (x *ListRaftConfigsResponse) GetAvailableConfigs() map[string]*RaftConfig {
	if x != nil {
		return x.AvailableConfigs
	}
	return nil
}

var File_raft_proto protoreflect.FileDescriptor

var file_raft_proto_rawDesc = []byte{
	0x0a, 0x0a, 0x72, 0x61, 0x66, 0x74, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x22, 0xa9, 0x05, 0x0a,
	0x0a, 0x52, 0x61, 0x66, 0x74, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x12, 0x16, 0x0a, 0x06, 0x4e,
	0x6f, 0x64, 0x65, 0x49, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x04, 0x52, 0x06, 0x4e, 0x6f, 0x64,
	0x65, 0x49, 0x64, 0x12, 0x1c, 0x0a, 0x09, 0x43, 0x6c, 0x75, 0x73, 0x74, 0x65, 0x72, 0x49, 0x64,
	0x18, 0x02, 0x20, 0x01, 0x28, 0x04, 0x52, 0x09, 0x43, 0x6c, 0x75, 0x73, 0x74, 0x65, 0x72, 0x49,
	0x64, 0x12, 0x20, 0x0a, 0x0b, 0x43, 0x68, 0x65, 0x63, 0x6b, 0x51, 0x75, 0x6f, 0x72, 0x75, 0x6d,
	0x18, 0x03, 0x20, 0x01, 0x28, 0x08, 0x52, 0x0b, 0x43, 0x68, 0x65, 0x63, 0x6b, 0x51, 0x75, 0x6f,
	0x72, 0x75, 0x6d, 0x12, 0x34, 0x0a, 0x15, 0x45, 0x6c, 0x65, 0x63, 0x74, 0x69, 0x6f, 0x6e, 0x52,
	0x6f, 0x75, 0x6e, 0x64, 0x54, 0x72, 0x69, 0x70, 0x54, 0x69, 0x6d, 0x65, 0x18, 0x04, 0x20, 0x01,
	0x28, 0x04, 0x52, 0x15, 0x45, 0x6c, 0x65, 0x63, 0x74, 0x69, 0x6f, 0x6e, 0x52, 0x6f, 0x75, 0x6e,
	0x64, 0x54, 0x72, 0x69, 0x70, 0x54, 0x69, 0x6d, 0x65, 0x12, 0x36, 0x0a, 0x16, 0x48, 0x65, 0x61,
	0x72, 0x74, 0x62, 0x65, 0x61, 0x74, 0x52, 0x6f, 0x75, 0x6e, 0x64, 0x54, 0x72, 0x69, 0x70, 0x54,
	0x69, 0x6d, 0x65, 0x18, 0x05, 0x20, 0x01, 0x28, 0x04, 0x52, 0x16, 0x48, 0x65, 0x61, 0x72, 0x74,
	0x62, 0x65, 0x61, 0x74, 0x52, 0x6f, 0x75, 0x6e, 0x64, 0x54, 0x72, 0x69, 0x70, 0x54, 0x69, 0x6d,
	0x65, 0x12, 0x28, 0x0a, 0x0f, 0x53, 0x6e, 0x61, 0x70, 0x73, 0x68, 0x6f, 0x74, 0x45, 0x6e, 0x74,
	0x72, 0x69, 0x65, 0x73, 0x18, 0x06, 0x20, 0x01, 0x28, 0x04, 0x52, 0x0f, 0x53, 0x6e, 0x61, 0x70,
	0x73, 0x68, 0x6f, 0x74, 0x45, 0x6e, 0x74, 0x72, 0x69, 0x65, 0x73, 0x12, 0x2e, 0x0a, 0x12, 0x43,
	0x6f, 0x6d, 0x70, 0x61, 0x63, 0x74, 0x69, 0x6f, 0x6e, 0x4f, 0x76, 0x65, 0x72, 0x68, 0x65, 0x61,
	0x64, 0x18, 0x07, 0x20, 0x01, 0x28, 0x04, 0x52, 0x12, 0x43, 0x6f, 0x6d, 0x70, 0x61, 0x63, 0x74,
	0x69, 0x6f, 0x6e, 0x4f, 0x76, 0x65, 0x72, 0x68, 0x65, 0x61, 0x64, 0x12, 0x30, 0x0a, 0x13, 0x4f,
	0x72, 0x64, 0x65, 0x72, 0x65, 0x64, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x43, 0x68, 0x61, 0x6e,
	0x67, 0x65, 0x18, 0x08, 0x20, 0x01, 0x28, 0x08, 0x52, 0x13, 0x4f, 0x72, 0x64, 0x65, 0x72, 0x65,
	0x64, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x43, 0x68, 0x61, 0x6e, 0x67, 0x65, 0x12, 0x28, 0x0a,
	0x0f, 0x4d, 0x61, 0x78, 0x49, 0x6e, 0x4d, 0x65, 0x6d, 0x4c, 0x6f, 0x67, 0x53, 0x69, 0x7a, 0x65,
	0x18, 0x09, 0x20, 0x01, 0x28, 0x04, 0x52, 0x0f, 0x4d, 0x61, 0x78, 0x49, 0x6e, 0x4d, 0x65, 0x6d,
	0x4c, 0x6f, 0x67, 0x53, 0x69, 0x7a, 0x65, 0x12, 0x38, 0x0a, 0x17, 0x53, 0x6e, 0x61, 0x70, 0x73,
	0x68, 0x6f, 0x74, 0x43, 0x6f, 0x6d, 0x70, 0x72, 0x65, 0x73, 0x73, 0x69, 0x6f, 0x6e, 0x54, 0x79,
	0x70, 0x65, 0x18, 0x0a, 0x20, 0x01, 0x28, 0x04, 0x52, 0x17, 0x53, 0x6e, 0x61, 0x70, 0x73, 0x68,
	0x6f, 0x74, 0x43, 0x6f, 0x6d, 0x70, 0x72, 0x65, 0x73, 0x73, 0x69, 0x6f, 0x6e, 0x54, 0x79, 0x70,
	0x65, 0x12, 0x32, 0x0a, 0x14, 0x45, 0x6e, 0x74, 0x72, 0x79, 0x43, 0x6f, 0x6d, 0x70, 0x72, 0x65,
	0x73, 0x73, 0x69, 0x6f, 0x6e, 0x54, 0x79, 0x70, 0x65, 0x18, 0x0b, 0x20, 0x01, 0x28, 0x04, 0x52,
	0x14, 0x45, 0x6e, 0x74, 0x72, 0x79, 0x43, 0x6f, 0x6d, 0x70, 0x72, 0x65, 0x73, 0x73, 0x69, 0x6f,
	0x6e, 0x54, 0x79, 0x70, 0x65, 0x12, 0x36, 0x0a, 0x16, 0x44, 0x69, 0x73, 0x61, 0x62, 0x6c, 0x65,
	0x41, 0x75, 0x74, 0x6f, 0x43, 0x6f, 0x6d, 0x70, 0x61, 0x63, 0x74, 0x69, 0x6f, 0x6e, 0x73, 0x18,
	0x0c, 0x20, 0x01, 0x28, 0x08, 0x52, 0x16, 0x44, 0x69, 0x73, 0x61, 0x62, 0x6c, 0x65, 0x41, 0x75,
	0x74, 0x6f, 0x43, 0x6f, 0x6d, 0x70, 0x61, 0x63, 0x74, 0x69, 0x6f, 0x6e, 0x73, 0x12, 0x1e, 0x0a,
	0x0a, 0x49, 0x73, 0x4f, 0x62, 0x73, 0x65, 0x72, 0x76, 0x65, 0x72, 0x18, 0x0d, 0x20, 0x01, 0x28,
	0x08, 0x52, 0x0a, 0x49, 0x73, 0x4f, 0x62, 0x73, 0x65, 0x72, 0x76, 0x65, 0x72, 0x12, 0x1c, 0x0a,
	0x09, 0x49, 0x73, 0x57, 0x69, 0x74, 0x6e, 0x65, 0x73, 0x73, 0x18, 0x0e, 0x20, 0x01, 0x28, 0x08,
	0x52, 0x09, 0x49, 0x73, 0x57, 0x69, 0x74, 0x6e, 0x65, 0x73, 0x73, 0x12, 0x18, 0x0a, 0x07, 0x51,
	0x75, 0x69, 0x65, 0x73, 0x63, 0x65, 0x18, 0x0f, 0x20, 0x01, 0x28, 0x08, 0x52, 0x07, 0x51, 0x75,
	0x69, 0x65, 0x73, 0x63, 0x65, 0x12, 0x21, 0x0a, 0x04, 0x54, 0x79, 0x70, 0x65, 0x18, 0x10, 0x20,
	0x01, 0x28, 0x0e, 0x32, 0x0d, 0x2e, 0x49, 0x73, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x54, 0x79,
	0x70, 0x65, 0x52, 0x04, 0x54, 0x79, 0x70, 0x65, 0x22, 0x67, 0x0a, 0x14, 0x50, 0x75, 0x74, 0x52,
	0x61, 0x66, 0x74, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x52, 0x65, 0x71, 0x75, 0x65, 0x73, 0x74,
	0x12, 0x16, 0x0a, 0x06, 0x45, 0x6e, 0x61, 0x62, 0x6c, 0x65, 0x18, 0x01, 0x20, 0x01, 0x28, 0x08,
	0x52, 0x06, 0x45, 0x6e, 0x61, 0x62, 0x6c, 0x65, 0x12, 0x12, 0x0a, 0x04, 0x4e, 0x61, 0x6d, 0x65,
	0x18, 0x02, 0x20, 0x01, 0x28, 0x09, 0x52, 0x04, 0x4e, 0x61, 0x6d, 0x65, 0x12, 0x23, 0x0a, 0x06,
	0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x18, 0x03, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x0b, 0x2e, 0x52,
	0x61, 0x66, 0x74, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x52, 0x06, 0x43, 0x6f, 0x6e, 0x66, 0x69,
	0x67, 0x22, 0x74, 0x0a, 0x15, 0x4e, 0x65, 0x77, 0x52, 0x61, 0x66, 0x74, 0x43, 0x6f, 0x6e, 0x66,
	0x69, 0x67, 0x52, 0x65, 0x73, 0x70, 0x6f, 0x6e, 0x73, 0x65, 0x12, 0x14, 0x0a, 0x05, 0x56, 0x61,
	0x6c, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x08, 0x52, 0x05, 0x56, 0x61, 0x6c, 0x69, 0x64,
	0x12, 0x17, 0x0a, 0x04, 0x4e, 0x61, 0x6d, 0x65, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09, 0x48, 0x00,
	0x52, 0x04, 0x4e, 0x61, 0x6d, 0x65, 0x88, 0x01, 0x01, 0x12, 0x19, 0x0a, 0x05, 0x45, 0x72, 0x72,
	0x6f, 0x72, 0x18, 0x03, 0x20, 0x01, 0x28, 0x09, 0x48, 0x01, 0x52, 0x05, 0x45, 0x72, 0x72, 0x6f,
	0x72, 0x88, 0x01, 0x01, 0x42, 0x07, 0x0a, 0x05, 0x5f, 0x4e, 0x61, 0x6d, 0x65, 0x42, 0x08, 0x0a,
	0x06, 0x5f, 0x45, 0x72, 0x72, 0x6f, 0x72, 0x22, 0x2a, 0x0a, 0x14, 0x47, 0x65, 0x74, 0x52, 0x61,
	0x66, 0x74, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x52, 0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x12,
	0x12, 0x0a, 0x04, 0x4e, 0x61, 0x6d, 0x65, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x04, 0x4e,
	0x61, 0x6d, 0x65, 0x22, 0x4a, 0x0a, 0x15, 0x47, 0x65, 0x74, 0x52, 0x61, 0x66, 0x74, 0x43, 0x6f,
	0x6e, 0x66, 0x69, 0x67, 0x52, 0x65, 0x73, 0x70, 0x6f, 0x6e, 0x73, 0x65, 0x12, 0x31, 0x0a, 0x0d,
	0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x75, 0x72, 0x61, 0x74, 0x69, 0x6f, 0x6e, 0x18, 0x01, 0x20,
	0x01, 0x28, 0x0b, 0x32, 0x0b, 0x2e, 0x52, 0x61, 0x66, 0x74, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67,
	0x52, 0x0d, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x75, 0x72, 0x61, 0x74, 0x69, 0x6f, 0x6e, 0x22,
	0x18, 0x0a, 0x16, 0x4c, 0x69, 0x73, 0x74, 0x52, 0x61, 0x66, 0x74, 0x43, 0x6f, 0x6e, 0x66, 0x69,
	0x67, 0x73, 0x52, 0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x22, 0xc7, 0x01, 0x0a, 0x17, 0x4c, 0x69,
	0x73, 0x74, 0x52, 0x61, 0x66, 0x74, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x73, 0x52, 0x65, 0x73,
	0x70, 0x6f, 0x6e, 0x73, 0x65, 0x12, 0x5a, 0x0a, 0x10, 0x41, 0x76, 0x61, 0x69, 0x6c, 0x61, 0x62,
	0x6c, 0x65, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x73, 0x18, 0x01, 0x20, 0x03, 0x28, 0x0b, 0x32,
	0x2e, 0x2e, 0x4c, 0x69, 0x73, 0x74, 0x52, 0x61, 0x66, 0x74, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67,
	0x73, 0x52, 0x65, 0x73, 0x70, 0x6f, 0x6e, 0x73, 0x65, 0x2e, 0x41, 0x76, 0x61, 0x69, 0x6c, 0x61,
	0x62, 0x6c, 0x65, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x73, 0x45, 0x6e, 0x74, 0x72, 0x79, 0x52,
	0x10, 0x41, 0x76, 0x61, 0x69, 0x6c, 0x61, 0x62, 0x6c, 0x65, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67,
	0x73, 0x1a, 0x50, 0x0a, 0x15, 0x41, 0x76, 0x61, 0x69, 0x6c, 0x61, 0x62, 0x6c, 0x65, 0x43, 0x6f,
	0x6e, 0x66, 0x69, 0x67, 0x73, 0x45, 0x6e, 0x74, 0x72, 0x79, 0x12, 0x10, 0x0a, 0x03, 0x6b, 0x65,
	0x79, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x03, 0x6b, 0x65, 0x79, 0x12, 0x21, 0x0a, 0x05,
	0x76, 0x61, 0x6c, 0x75, 0x65, 0x18, 0x02, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x0b, 0x2e, 0x52, 0x61,
	0x66, 0x74, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x52, 0x05, 0x76, 0x61, 0x6c, 0x75, 0x65, 0x3a,
	0x02, 0x38, 0x01, 0x2a, 0x39, 0x0a, 0x0c, 0x49, 0x73, 0x43, 0x6f, 0x6e, 0x66, 0x69, 0x67, 0x54,
	0x79, 0x70, 0x65, 0x12, 0x0a, 0x0a, 0x06, 0x53, 0x79, 0x73, 0x74, 0x65, 0x6d, 0x10, 0x00, 0x12,
	0x0c, 0x0a, 0x08, 0x45, 0x78, 0x63, 0x68, 0x61, 0x6e, 0x67, 0x65, 0x10, 0x01, 0x12, 0x0f, 0x0a,
	0x0b, 0x43, 0x75, 0x73, 0x74, 0x6f, 0x6d, 0x65, 0x72, 0x46, 0x73, 0x6d, 0x10, 0x64, 0x42, 0x18,
	0x5a, 0x16, 0x72, 0x33, 0x74, 0x2e, 0x69, 0x6f, 0x2f, 0x70, 0x6c, 0x65, 0x69, 0x61, 0x64, 0x65,
	0x73, 0x2f, 0x70, 0x6b, 0x67, 0x2f, 0x70, 0x62, 0x62, 0x06, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x33,
}

var (
	file_raft_proto_rawDescOnce sync.Once
	file_raft_proto_rawDescData = file_raft_proto_rawDesc
)

func file_raft_proto_rawDescGZIP() []byte {
	file_raft_proto_rawDescOnce.Do(func() {
		file_raft_proto_rawDescData = protoimpl.X.CompressGZIP(file_raft_proto_rawDescData)
	})
	return file_raft_proto_rawDescData
}

var file_raft_proto_enumTypes = make([]protoimpl.EnumInfo, 1)
var file_raft_proto_msgTypes = make([]protoimpl.MessageInfo, 8)
var file_raft_proto_goTypes = []interface{}{
	(IsConfigType)(0),               // 0: IsConfigType
	(*RaftConfig)(nil),              // 1: RaftConfig
	(*PutRaftConfigRequest)(nil),    // 2: PutRaftConfigRequest
	(*NewRaftConfigResponse)(nil),   // 3: NewRaftConfigResponse
	(*GetRaftConfigRequest)(nil),    // 4: GetRaftConfigRequest
	(*GetRaftConfigResponse)(nil),   // 5: GetRaftConfigResponse
	(*ListRaftConfigsRequest)(nil),  // 6: ListRaftConfigsRequest
	(*ListRaftConfigsResponse)(nil), // 7: ListRaftConfigsResponse
	nil,                             // 8: ListRaftConfigsResponse.AvailableConfigsEntry
}
var file_raft_proto_depIdxs = []int32{
	0, // 0: RaftConfig.Type:type_name -> IsConfigType
	1, // 1: PutRaftConfigRequest.Config:type_name -> RaftConfig
	1, // 2: GetRaftConfigResponse.Configuration:type_name -> RaftConfig
	8, // 3: ListRaftConfigsResponse.AvailableConfigs:type_name -> ListRaftConfigsResponse.AvailableConfigsEntry
	1, // 4: ListRaftConfigsResponse.AvailableConfigsEntry.value:type_name -> RaftConfig
	5, // [5:5] is the sub-list for method output_type
	5, // [5:5] is the sub-list for method input_type
	5, // [5:5] is the sub-list for extension type_name
	5, // [5:5] is the sub-list for extension extendee
	0, // [0:5] is the sub-list for field type_name
}

func init() { file_raft_proto_init() }
func file_raft_proto_init() {
	if File_raft_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_raft_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*RaftConfig); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_raft_proto_msgTypes[1].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*PutRaftConfigRequest); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_raft_proto_msgTypes[2].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*NewRaftConfigResponse); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_raft_proto_msgTypes[3].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*GetRaftConfigRequest); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_raft_proto_msgTypes[4].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*GetRaftConfigResponse); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_raft_proto_msgTypes[5].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*ListRaftConfigsRequest); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_raft_proto_msgTypes[6].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*ListRaftConfigsResponse); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
	}
	file_raft_proto_msgTypes[2].OneofWrappers = []interface{}{}
	type x struct{}
	out := protoimpl.TypeBuilder{
		File: protoimpl.DescBuilder{
			GoPackagePath: reflect.TypeOf(x{}).PkgPath(),
			RawDescriptor: file_raft_proto_rawDesc,
			NumEnums:      1,
			NumMessages:   8,
			NumExtensions: 0,
			NumServices:   0,
		},
		GoTypes:           file_raft_proto_goTypes,
		DependencyIndexes: file_raft_proto_depIdxs,
		EnumInfos:         file_raft_proto_enumTypes,
		MessageInfos:      file_raft_proto_msgTypes,
	}.Build()
	File_raft_proto = out.File
	file_raft_proto_rawDesc = nil
	file_raft_proto_goTypes = nil
	file_raft_proto_depIdxs = nil
}