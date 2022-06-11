// Code generated by protoc-gen-go-drpc. DO NOT EDIT.
// protoc-gen-go-drpc version: v0.0.30
// source: cookie_monster.proto

package testdata

import (
	context "context"
	errors "errors"
	protojson "google.golang.org/protobuf/encoding/protojson"
	proto "google.golang.org/protobuf/proto"
	drpc "storj.io/drpc"
	drpcerr "storj.io/drpc/drpcerr"
)

type drpcEncoding_File_cookie_monster_proto struct{}

func (drpcEncoding_File_cookie_monster_proto) Marshal(msg drpc.Message) ([]byte, error) {
	return proto.Marshal(msg.(proto.Message))
}

func (drpcEncoding_File_cookie_monster_proto) MarshalAppend(buf []byte, msg drpc.Message) ([]byte, error) {
	return proto.MarshalOptions{}.MarshalAppend(buf, msg.(proto.Message))
}

func (drpcEncoding_File_cookie_monster_proto) Unmarshal(buf []byte, msg drpc.Message) error {
	return proto.Unmarshal(buf, msg.(proto.Message))
}

func (drpcEncoding_File_cookie_monster_proto) JSONMarshal(msg drpc.Message) ([]byte, error) {
	return protojson.Marshal(msg.(proto.Message))
}

func (drpcEncoding_File_cookie_monster_proto) JSONUnmarshal(buf []byte, msg drpc.Message) error {
	return protojson.Unmarshal(buf, msg.(proto.Message))
}

type DRPCCookieMonsterClient interface {
	DRPCConn() drpc.Conn

	EatCookie(ctx context.Context, in *Cookie) (*Crumbs, error)
}

type drpcCookieMonsterClient struct {
	cc drpc.Conn
}

func NewDRPCCookieMonsterClient(cc drpc.Conn) DRPCCookieMonsterClient {
	return &drpcCookieMonsterClient{cc}
}

func (c *drpcCookieMonsterClient) DRPCConn() drpc.Conn { return c.cc }

func (c *drpcCookieMonsterClient) EatCookie(ctx context.Context, in *Cookie) (*Crumbs, error) {
	out := new(Crumbs)
	err := c.cc.Invoke(ctx, "/testdata.CookieMonster/EatCookie", drpcEncoding_File_cookie_monster_proto{}, in, out)
	if err != nil {
		return nil, err
	}
	return out, nil
}

type DRPCCookieMonsterServer interface {
	EatCookie(context.Context, *Cookie) (*Crumbs, error)
}

type DRPCCookieMonsterUnimplementedServer struct{}

func (s *DRPCCookieMonsterUnimplementedServer) EatCookie(context.Context, *Cookie) (*Crumbs, error) {
	return nil, drpcerr.WithCode(errors.New("Unimplemented"), drpcerr.Unimplemented)
}

type DRPCCookieMonsterDescription struct{}

func (DRPCCookieMonsterDescription) NumMethods() int { return 1 }

func (DRPCCookieMonsterDescription) Method(n int) (string, drpc.Encoding, drpc.Receiver, interface{}, bool) {
	switch n {
	case 0:
		return "/testdata.CookieMonster/EatCookie", drpcEncoding_File_cookie_monster_proto{},
			func(srv interface{}, ctx context.Context, in1, in2 interface{}) (drpc.Message, error) {
				return srv.(DRPCCookieMonsterServer).
					EatCookie(
						ctx,
						in1.(*Cookie),
					)
			}, DRPCCookieMonsterServer.EatCookie, true
	default:
		return "", nil, nil, nil, false
	}
}

func DRPCRegisterCookieMonster(mux drpc.Mux, impl DRPCCookieMonsterServer) error {
	return mux.Register(impl, DRPCCookieMonsterDescription{})
}

type DRPCCookieMonster_EatCookieStream interface {
	drpc.Stream
	SendAndClose(*Crumbs) error
}

type drpcCookieMonster_EatCookieStream struct {
	drpc.Stream
}

func (x *drpcCookieMonster_EatCookieStream) SendAndClose(m *Crumbs) error {
	if err := x.MsgSend(m, drpcEncoding_File_cookie_monster_proto{}); err != nil {
		return err
	}
	return x.CloseSend()
}
