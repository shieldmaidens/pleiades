// Code generated by protoc-gen-go-grpc. DO NOT EDIT.
// versions:
// - protoc-gen-go-grpc v1.2.0
// - protoc             v3.20.1
// source: pkg/server/kv.proto

package server

import (
	database "github.com/mxplusb/pleiades/pkg/api/v1/database"
	context "context"
	grpc "google.golang.org/grpc"
	codes "google.golang.org/grpc/codes"
	status "google.golang.org/grpc/status"
)

// This is a compile-time assertion to ensure that this generated file
// is compatible with the grpc package it is being compiled against.
// Requires gRPC-Go v1.32.0 or later.
const _ = grpc.SupportPackageIsVersion7

// KVStoreServiceClient is the client API for KVStoreService service.
//
// For semantics around ctx use and closing/ending streaming RPCs, please refer to https://pkg.go.dev/google.golang.org/grpc/?tab=doc#ClientConn.NewStream.
type KVStoreServiceClient interface {
	Get(ctx context.Context, in *database.GetRequest, opts ...grpc.CallOption) (*database.GetResponse, error)
	Put(ctx context.Context, in *database.PutRequest, opts ...grpc.CallOption) (*database.PutReply, error)
	Delete(ctx context.Context, in *database.DeleteRequest, opts ...grpc.CallOption) (*database.DeleteResponse, error)
}

type kVStoreServiceClient struct {
	cc grpc.ClientConnInterface
}

func NewKVStoreServiceClient(cc grpc.ClientConnInterface) KVStoreServiceClient {
	return &kVStoreServiceClient{cc}
}

func (c *kVStoreServiceClient) Get(ctx context.Context, in *database.GetRequest, opts ...grpc.CallOption) (*database.GetResponse, error) {
	out := new(database.GetResponse)
	err := c.cc.Invoke(ctx, "/server.KVStoreService/Get", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

func (c *kVStoreServiceClient) Put(ctx context.Context, in *database.PutRequest, opts ...grpc.CallOption) (*database.PutReply, error) {
	out := new(database.PutReply)
	err := c.cc.Invoke(ctx, "/server.KVStoreService/Put", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

func (c *kVStoreServiceClient) Delete(ctx context.Context, in *database.DeleteRequest, opts ...grpc.CallOption) (*database.DeleteResponse, error) {
	out := new(database.DeleteResponse)
	err := c.cc.Invoke(ctx, "/server.KVStoreService/Delete", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

// KVStoreServiceServer is the server API for KVStoreService service.
// All implementations must embed UnimplementedKVStoreServiceServer
// for forward compatibility
type KVStoreServiceServer interface {
	Get(context.Context, *database.GetRequest) (*database.GetResponse, error)
	Put(context.Context, *database.PutRequest) (*database.PutReply, error)
	Delete(context.Context, *database.DeleteRequest) (*database.DeleteResponse, error)
	mustEmbedUnimplementedKVStoreServiceServer()
}

// UnimplementedKVStoreServiceServer must be embedded to have forward compatible implementations.
type UnimplementedKVStoreServiceServer struct {
}

func (UnimplementedKVStoreServiceServer) Get(context.Context, *database.GetRequest) (*database.GetResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method Get not implemented")
}
func (UnimplementedKVStoreServiceServer) Put(context.Context, *database.PutRequest) (*database.PutReply, error) {
	return nil, status.Errorf(codes.Unimplemented, "method Put not implemented")
}
func (UnimplementedKVStoreServiceServer) Delete(context.Context, *database.DeleteRequest) (*database.DeleteResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method Delete not implemented")
}
func (UnimplementedKVStoreServiceServer) mustEmbedUnimplementedKVStoreServiceServer() {}

// UnsafeKVStoreServiceServer may be embedded to opt out of forward compatibility for this service.
// Use of this interface is not recommended, as added methods to KVStoreServiceServer will
// result in compilation errors.
type UnsafeKVStoreServiceServer interface {
	mustEmbedUnimplementedKVStoreServiceServer()
}

func RegisterKVStoreServiceServer(s grpc.ServiceRegistrar, srv KVStoreServiceServer) {
	s.RegisterService(&KVStoreService_ServiceDesc, srv)
}

func _KVStoreService_Get_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(database.GetRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(KVStoreServiceServer).Get(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/server.KVStoreService/Get",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(KVStoreServiceServer).Get(ctx, req.(*database.GetRequest))
	}
	return interceptor(ctx, in, info, handler)
}

func _KVStoreService_Put_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(database.PutRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(KVStoreServiceServer).Put(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/server.KVStoreService/Put",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(KVStoreServiceServer).Put(ctx, req.(*database.PutRequest))
	}
	return interceptor(ctx, in, info, handler)
}

func _KVStoreService_Delete_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(database.DeleteRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(KVStoreServiceServer).Delete(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/server.KVStoreService/Delete",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(KVStoreServiceServer).Delete(ctx, req.(*database.DeleteRequest))
	}
	return interceptor(ctx, in, info, handler)
}

// KVStoreService_ServiceDesc is the grpc.ServiceDesc for KVStoreService service.
// It's only intended for direct use with grpc.RegisterService,
// and not to be introspected or modified (even as a copy)
var KVStoreService_ServiceDesc = grpc.ServiceDesc{
	ServiceName: "server.KVStoreService",
	HandlerType: (*KVStoreServiceServer)(nil),
	Methods: []grpc.MethodDesc{
		{
			MethodName: "Get",
			Handler:    _KVStoreService_Get_Handler,
		},
		{
			MethodName: "Put",
			Handler:    _KVStoreService_Put_Handler,
		},
		{
			MethodName: "Delete",
			Handler:    _KVStoreService_Delete_Handler,
		},
	},
	Streams:  []grpc.StreamDesc{},
	Metadata: "pkg/server/kv.proto",
}
