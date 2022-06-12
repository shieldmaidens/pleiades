// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.28.0
// 	protoc        (unknown)
// source: etcd/v3/membership.proto

package v3

import (
	protoreflect "google.golang.org/protobuf/reflect/protoreflect"
	protoimpl "google.golang.org/protobuf/runtime/protoimpl"
	_ "r3t.io/pleiades/pkg/pb/gogoproto"
	reflect "reflect"
	sync "sync"
)

const (
	// Verify that this generated code is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(20 - protoimpl.MinVersion)
	// Verify that runtime/protoimpl is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(protoimpl.MaxVersion - 20)
)

// RaftAttributes represents the raft related attributes of an etcd member.
type RaftAttributes struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	// peerURLs is the list of peers in the raft cluster.
	PeerUrls []string `protobuf:"bytes,1,rep,name=peer_urls,json=peerUrls,proto3" json:"peer_urls,omitempty"`
	// isLearner indicates if the member is raft learner.
	IsLearner bool `protobuf:"varint,2,opt,name=is_learner,json=isLearner,proto3" json:"is_learner,omitempty"`
}

func (x *RaftAttributes) Reset() {
	*x = RaftAttributes{}
	if protoimpl.UnsafeEnabled {
		mi := &file_etcd_v3_membership_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *RaftAttributes) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*RaftAttributes) ProtoMessage() {}

func (x *RaftAttributes) ProtoReflect() protoreflect.Message {
	mi := &file_etcd_v3_membership_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use RaftAttributes.ProtoReflect.Descriptor instead.
func (*RaftAttributes) Descriptor() ([]byte, []int) {
	return file_etcd_v3_membership_proto_rawDescGZIP(), []int{0}
}

func (x *RaftAttributes) GetPeerUrls() []string {
	if x != nil {
		return x.PeerUrls
	}
	return nil
}

func (x *RaftAttributes) GetIsLearner() bool {
	if x != nil {
		return x.IsLearner
	}
	return false
}

// Attributes represents all the non-raft related attributes of an etcd member.
type Attributes struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Name       string   `protobuf:"bytes,1,opt,name=name,proto3" json:"name,omitempty"`
	ClientUrls []string `protobuf:"bytes,2,rep,name=client_urls,json=clientUrls,proto3" json:"client_urls,omitempty"`
}

func (x *Attributes) Reset() {
	*x = Attributes{}
	if protoimpl.UnsafeEnabled {
		mi := &file_etcd_v3_membership_proto_msgTypes[1]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *Attributes) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*Attributes) ProtoMessage() {}

func (x *Attributes) ProtoReflect() protoreflect.Message {
	mi := &file_etcd_v3_membership_proto_msgTypes[1]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use Attributes.ProtoReflect.Descriptor instead.
func (*Attributes) Descriptor() ([]byte, []int) {
	return file_etcd_v3_membership_proto_rawDescGZIP(), []int{1}
}

func (x *Attributes) GetName() string {
	if x != nil {
		return x.Name
	}
	return ""
}

func (x *Attributes) GetClientUrls() []string {
	if x != nil {
		return x.ClientUrls
	}
	return nil
}

type Member struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	ID               uint64          `protobuf:"varint,1,opt,name=ID,proto3" json:"ID,omitempty"`
	RaftAttributes   *RaftAttributes `protobuf:"bytes,2,opt,name=raft_attributes,json=raftAttributes,proto3" json:"raft_attributes,omitempty"`
	MemberAttributes *Attributes     `protobuf:"bytes,3,opt,name=member_attributes,json=memberAttributes,proto3" json:"member_attributes,omitempty"`
}

func (x *Member) Reset() {
	*x = Member{}
	if protoimpl.UnsafeEnabled {
		mi := &file_etcd_v3_membership_proto_msgTypes[2]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *Member) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*Member) ProtoMessage() {}

func (x *Member) ProtoReflect() protoreflect.Message {
	mi := &file_etcd_v3_membership_proto_msgTypes[2]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use Member.ProtoReflect.Descriptor instead.
func (*Member) Descriptor() ([]byte, []int) {
	return file_etcd_v3_membership_proto_rawDescGZIP(), []int{2}
}

func (x *Member) GetID() uint64 {
	if x != nil {
		return x.ID
	}
	return 0
}

func (x *Member) GetRaftAttributes() *RaftAttributes {
	if x != nil {
		return x.RaftAttributes
	}
	return nil
}

func (x *Member) GetMemberAttributes() *Attributes {
	if x != nil {
		return x.MemberAttributes
	}
	return nil
}

type ClusterVersionSetRequest struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Ver string `protobuf:"bytes,1,opt,name=ver,proto3" json:"ver,omitempty"`
}

func (x *ClusterVersionSetRequest) Reset() {
	*x = ClusterVersionSetRequest{}
	if protoimpl.UnsafeEnabled {
		mi := &file_etcd_v3_membership_proto_msgTypes[3]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *ClusterVersionSetRequest) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*ClusterVersionSetRequest) ProtoMessage() {}

func (x *ClusterVersionSetRequest) ProtoReflect() protoreflect.Message {
	mi := &file_etcd_v3_membership_proto_msgTypes[3]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use ClusterVersionSetRequest.ProtoReflect.Descriptor instead.
func (*ClusterVersionSetRequest) Descriptor() ([]byte, []int) {
	return file_etcd_v3_membership_proto_rawDescGZIP(), []int{3}
}

func (x *ClusterVersionSetRequest) GetVer() string {
	if x != nil {
		return x.Ver
	}
	return ""
}

type ClusterMemberAttrSetRequest struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Member_ID        uint64      `protobuf:"varint,1,opt,name=member_ID,json=memberID,proto3" json:"member_ID,omitempty"`
	MemberAttributes *Attributes `protobuf:"bytes,2,opt,name=member_attributes,json=memberAttributes,proto3" json:"member_attributes,omitempty"`
}

func (x *ClusterMemberAttrSetRequest) Reset() {
	*x = ClusterMemberAttrSetRequest{}
	if protoimpl.UnsafeEnabled {
		mi := &file_etcd_v3_membership_proto_msgTypes[4]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *ClusterMemberAttrSetRequest) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*ClusterMemberAttrSetRequest) ProtoMessage() {}

func (x *ClusterMemberAttrSetRequest) ProtoReflect() protoreflect.Message {
	mi := &file_etcd_v3_membership_proto_msgTypes[4]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use ClusterMemberAttrSetRequest.ProtoReflect.Descriptor instead.
func (*ClusterMemberAttrSetRequest) Descriptor() ([]byte, []int) {
	return file_etcd_v3_membership_proto_rawDescGZIP(), []int{4}
}

func (x *ClusterMemberAttrSetRequest) GetMember_ID() uint64 {
	if x != nil {
		return x.Member_ID
	}
	return 0
}

func (x *ClusterMemberAttrSetRequest) GetMemberAttributes() *Attributes {
	if x != nil {
		return x.MemberAttributes
	}
	return nil
}

type DowngradeInfoSetRequest struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Enabled bool   `protobuf:"varint,1,opt,name=enabled,proto3" json:"enabled,omitempty"`
	Ver     string `protobuf:"bytes,2,opt,name=ver,proto3" json:"ver,omitempty"`
}

func (x *DowngradeInfoSetRequest) Reset() {
	*x = DowngradeInfoSetRequest{}
	if protoimpl.UnsafeEnabled {
		mi := &file_etcd_v3_membership_proto_msgTypes[5]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *DowngradeInfoSetRequest) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*DowngradeInfoSetRequest) ProtoMessage() {}

func (x *DowngradeInfoSetRequest) ProtoReflect() protoreflect.Message {
	mi := &file_etcd_v3_membership_proto_msgTypes[5]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use DowngradeInfoSetRequest.ProtoReflect.Descriptor instead.
func (*DowngradeInfoSetRequest) Descriptor() ([]byte, []int) {
	return file_etcd_v3_membership_proto_rawDescGZIP(), []int{5}
}

func (x *DowngradeInfoSetRequest) GetEnabled() bool {
	if x != nil {
		return x.Enabled
	}
	return false
}

func (x *DowngradeInfoSetRequest) GetVer() string {
	if x != nil {
		return x.Ver
	}
	return ""
}

var File_etcd_v3_membership_proto protoreflect.FileDescriptor

var file_etcd_v3_membership_proto_rawDesc = []byte{
	0x0a, 0x18, 0x65, 0x74, 0x63, 0x64, 0x2f, 0x76, 0x33, 0x2f, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72,
	0x73, 0x68, 0x69, 0x70, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x12, 0x02, 0x76, 0x33, 0x1a, 0x14,
	0x67, 0x6f, 0x67, 0x6f, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x2f, 0x67, 0x6f, 0x67, 0x6f, 0x2e, 0x70,
	0x72, 0x6f, 0x74, 0x6f, 0x22, 0x4c, 0x0a, 0x0e, 0x52, 0x61, 0x66, 0x74, 0x41, 0x74, 0x74, 0x72,
	0x69, 0x62, 0x75, 0x74, 0x65, 0x73, 0x12, 0x1b, 0x0a, 0x09, 0x70, 0x65, 0x65, 0x72, 0x5f, 0x75,
	0x72, 0x6c, 0x73, 0x18, 0x01, 0x20, 0x03, 0x28, 0x09, 0x52, 0x08, 0x70, 0x65, 0x65, 0x72, 0x55,
	0x72, 0x6c, 0x73, 0x12, 0x1d, 0x0a, 0x0a, 0x69, 0x73, 0x5f, 0x6c, 0x65, 0x61, 0x72, 0x6e, 0x65,
	0x72, 0x18, 0x02, 0x20, 0x01, 0x28, 0x08, 0x52, 0x09, 0x69, 0x73, 0x4c, 0x65, 0x61, 0x72, 0x6e,
	0x65, 0x72, 0x22, 0x41, 0x0a, 0x0a, 0x41, 0x74, 0x74, 0x72, 0x69, 0x62, 0x75, 0x74, 0x65, 0x73,
	0x12, 0x12, 0x0a, 0x04, 0x6e, 0x61, 0x6d, 0x65, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x04,
	0x6e, 0x61, 0x6d, 0x65, 0x12, 0x1f, 0x0a, 0x0b, 0x63, 0x6c, 0x69, 0x65, 0x6e, 0x74, 0x5f, 0x75,
	0x72, 0x6c, 0x73, 0x18, 0x02, 0x20, 0x03, 0x28, 0x09, 0x52, 0x0a, 0x63, 0x6c, 0x69, 0x65, 0x6e,
	0x74, 0x55, 0x72, 0x6c, 0x73, 0x22, 0x92, 0x01, 0x0a, 0x06, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72,
	0x12, 0x0e, 0x0a, 0x02, 0x49, 0x44, 0x18, 0x01, 0x20, 0x01, 0x28, 0x04, 0x52, 0x02, 0x49, 0x44,
	0x12, 0x3b, 0x0a, 0x0f, 0x72, 0x61, 0x66, 0x74, 0x5f, 0x61, 0x74, 0x74, 0x72, 0x69, 0x62, 0x75,
	0x74, 0x65, 0x73, 0x18, 0x02, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x12, 0x2e, 0x76, 0x33, 0x2e, 0x52,
	0x61, 0x66, 0x74, 0x41, 0x74, 0x74, 0x72, 0x69, 0x62, 0x75, 0x74, 0x65, 0x73, 0x52, 0x0e, 0x72,
	0x61, 0x66, 0x74, 0x41, 0x74, 0x74, 0x72, 0x69, 0x62, 0x75, 0x74, 0x65, 0x73, 0x12, 0x3b, 0x0a,
	0x11, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x5f, 0x61, 0x74, 0x74, 0x72, 0x69, 0x62, 0x75, 0x74,
	0x65, 0x73, 0x18, 0x03, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x0e, 0x2e, 0x76, 0x33, 0x2e, 0x41, 0x74,
	0x74, 0x72, 0x69, 0x62, 0x75, 0x74, 0x65, 0x73, 0x52, 0x10, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72,
	0x41, 0x74, 0x74, 0x72, 0x69, 0x62, 0x75, 0x74, 0x65, 0x73, 0x22, 0x2c, 0x0a, 0x18, 0x43, 0x6c,
	0x75, 0x73, 0x74, 0x65, 0x72, 0x56, 0x65, 0x72, 0x73, 0x69, 0x6f, 0x6e, 0x53, 0x65, 0x74, 0x52,
	0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x12, 0x10, 0x0a, 0x03, 0x76, 0x65, 0x72, 0x18, 0x01, 0x20,
	0x01, 0x28, 0x09, 0x52, 0x03, 0x76, 0x65, 0x72, 0x22, 0x77, 0x0a, 0x1b, 0x43, 0x6c, 0x75, 0x73,
	0x74, 0x65, 0x72, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x41, 0x74, 0x74, 0x72, 0x53, 0x65, 0x74,
	0x52, 0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x12, 0x1b, 0x0a, 0x09, 0x6d, 0x65, 0x6d, 0x62, 0x65,
	0x72, 0x5f, 0x49, 0x44, 0x18, 0x01, 0x20, 0x01, 0x28, 0x04, 0x52, 0x08, 0x6d, 0x65, 0x6d, 0x62,
	0x65, 0x72, 0x49, 0x44, 0x12, 0x3b, 0x0a, 0x11, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x5f, 0x61,
	0x74, 0x74, 0x72, 0x69, 0x62, 0x75, 0x74, 0x65, 0x73, 0x18, 0x02, 0x20, 0x01, 0x28, 0x0b, 0x32,
	0x0e, 0x2e, 0x76, 0x33, 0x2e, 0x41, 0x74, 0x74, 0x72, 0x69, 0x62, 0x75, 0x74, 0x65, 0x73, 0x52,
	0x10, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x41, 0x74, 0x74, 0x72, 0x69, 0x62, 0x75, 0x74, 0x65,
	0x73, 0x22, 0x45, 0x0a, 0x17, 0x44, 0x6f, 0x77, 0x6e, 0x67, 0x72, 0x61, 0x64, 0x65, 0x49, 0x6e,
	0x66, 0x6f, 0x53, 0x65, 0x74, 0x52, 0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x12, 0x18, 0x0a, 0x07,
	0x65, 0x6e, 0x61, 0x62, 0x6c, 0x65, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x08, 0x52, 0x07, 0x65,
	0x6e, 0x61, 0x62, 0x6c, 0x65, 0x64, 0x12, 0x10, 0x0a, 0x03, 0x76, 0x65, 0x72, 0x18, 0x02, 0x20,
	0x01, 0x28, 0x09, 0x52, 0x03, 0x76, 0x65, 0x72, 0x42, 0x71, 0x0a, 0x06, 0x63, 0x6f, 0x6d, 0x2e,
	0x76, 0x33, 0x42, 0x0f, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x73, 0x68, 0x69, 0x70, 0x50, 0x72,
	0x6f, 0x74, 0x6f, 0x50, 0x01, 0x5a, 0x1e, 0x72, 0x33, 0x74, 0x2e, 0x69, 0x6f, 0x2f, 0x70, 0x6c,
	0x65, 0x69, 0x61, 0x64, 0x65, 0x73, 0x2f, 0x70, 0x6b, 0x67, 0x2f, 0x70, 0x62, 0x2f, 0x65, 0x74,
	0x63, 0x64, 0x2f, 0x76, 0x33, 0xa2, 0x02, 0x03, 0x56, 0x58, 0x58, 0xaa, 0x02, 0x02, 0x56, 0x33,
	0xca, 0x02, 0x02, 0x56, 0x33, 0xe2, 0x02, 0x0e, 0x56, 0x33, 0x5c, 0x47, 0x50, 0x42, 0x4d, 0x65,
	0x74, 0x61, 0x64, 0x61, 0x74, 0x61, 0xea, 0x02, 0x02, 0x56, 0x33, 0xc8, 0xe1, 0x1e, 0x00, 0xc8,
	0xe2, 0x1e, 0x01, 0xd0, 0xe2, 0x1e, 0x01, 0xe0, 0xe2, 0x1e, 0x01, 0x62, 0x06, 0x70, 0x72, 0x6f,
	0x74, 0x6f, 0x33,
}

var (
	file_etcd_v3_membership_proto_rawDescOnce sync.Once
	file_etcd_v3_membership_proto_rawDescData = file_etcd_v3_membership_proto_rawDesc
)

func file_etcd_v3_membership_proto_rawDescGZIP() []byte {
	file_etcd_v3_membership_proto_rawDescOnce.Do(func() {
		file_etcd_v3_membership_proto_rawDescData = protoimpl.X.CompressGZIP(file_etcd_v3_membership_proto_rawDescData)
	})
	return file_etcd_v3_membership_proto_rawDescData
}

var file_etcd_v3_membership_proto_msgTypes = make([]protoimpl.MessageInfo, 6)
var file_etcd_v3_membership_proto_goTypes = []interface{}{
	(*RaftAttributes)(nil),              // 0: v3.RaftAttributes
	(*Attributes)(nil),                  // 1: v3.Attributes
	(*Member)(nil),                      // 2: v3.Member
	(*ClusterVersionSetRequest)(nil),    // 3: v3.ClusterVersionSetRequest
	(*ClusterMemberAttrSetRequest)(nil), // 4: v3.ClusterMemberAttrSetRequest
	(*DowngradeInfoSetRequest)(nil),     // 5: v3.DowngradeInfoSetRequest
}
var file_etcd_v3_membership_proto_depIdxs = []int32{
	0, // 0: v3.Member.raft_attributes:type_name -> v3.RaftAttributes
	1, // 1: v3.Member.member_attributes:type_name -> v3.Attributes
	1, // 2: v3.ClusterMemberAttrSetRequest.member_attributes:type_name -> v3.Attributes
	3, // [3:3] is the sub-list for method output_type
	3, // [3:3] is the sub-list for method input_type
	3, // [3:3] is the sub-list for extension type_name
	3, // [3:3] is the sub-list for extension extendee
	0, // [0:3] is the sub-list for field type_name
}

func init() { file_etcd_v3_membership_proto_init() }
func file_etcd_v3_membership_proto_init() {
	if File_etcd_v3_membership_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_etcd_v3_membership_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*RaftAttributes); i {
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
		file_etcd_v3_membership_proto_msgTypes[1].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*Attributes); i {
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
		file_etcd_v3_membership_proto_msgTypes[2].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*Member); i {
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
		file_etcd_v3_membership_proto_msgTypes[3].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*ClusterVersionSetRequest); i {
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
		file_etcd_v3_membership_proto_msgTypes[4].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*ClusterMemberAttrSetRequest); i {
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
		file_etcd_v3_membership_proto_msgTypes[5].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*DowngradeInfoSetRequest); i {
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
	type x struct{}
	out := protoimpl.TypeBuilder{
		File: protoimpl.DescBuilder{
			GoPackagePath: reflect.TypeOf(x{}).PkgPath(),
			RawDescriptor: file_etcd_v3_membership_proto_rawDesc,
			NumEnums:      0,
			NumMessages:   6,
			NumExtensions: 0,
			NumServices:   0,
		},
		GoTypes:           file_etcd_v3_membership_proto_goTypes,
		DependencyIndexes: file_etcd_v3_membership_proto_depIdxs,
		MessageInfos:      file_etcd_v3_membership_proto_msgTypes,
	}.Build()
	File_etcd_v3_membership_proto = out.File
	file_etcd_v3_membership_proto_rawDesc = nil
	file_etcd_v3_membership_proto_goTypes = nil
	file_etcd_v3_membership_proto_depIdxs = nil
}
