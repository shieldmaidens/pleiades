/*
 * Copyright (c) 2022 Sienna Lloyd
 *
 * Licensed under the PolyForm Strict License 1.0.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License here:
 *  https://github.com/mxplusb/pleiades/blob/mainline/LICENSE
 */

package messaging

import (
	"testing"
	"time"

	"github.com/mxplusb/pleiades/pkg/utils"
	"github.com/nats-io/nats-server/v2/server"
	"github.com/stretchr/testify/suite"
)

func TestEmbeddedEventStream(t *testing.T) {
	suite.Run(t, new(EmbeddedEventStreamTestSuite))
}

type EmbeddedEventStreamTestSuite struct {
	suite.Suite
	opts *EmbeddedMessagingStreamOpts
}

func (t *EmbeddedEventStreamTestSuite) SetupSuite() {
	t.opts = &EmbeddedMessagingStreamOpts{
		Options: &server.Options{
			Host: "localhost",
			JetStream: true,
			DontListen: true,
		},
		timeout: utils.Timeout(4000*time.Millisecond),
	}
}

func (t *EmbeddedEventStreamTestSuite) TestNew() {
	e, err := NewEmbeddedMessaging(t.opts)
	t.Require().NoError(err, "there must not be an error creating a new embedded event stream")
	t.Require().NotNil(e, "the event stream must not be nil")
}

func (t *EmbeddedEventStreamTestSuite) TestStartAndStop() {
	e, err := NewEmbeddedMessaging(t.opts)
	t.Require().NoError(err, "there must not be an error creating a new embedded event stream")
	t.Require().NotNil(e, "the event stream must not be nil")

	t.Require().NotPanics(e.Start, "the embedded server must not panic on start")

	t.Require().NotPanics(e.Stop, "the embedded server must not panic on stop")
}

func (t *EmbeddedEventStreamTestSuite) TestGetPubSubClient() {
	e, err := NewEmbeddedMessaging(t.opts)
	t.Require().NoError(err, "there must not be an error creating a new embedded event stream")
	t.Require().NotNil(e, "the event stream must not be nil")

	t.Require().NotPanics(func() {
		e.Start()
	}, "the embedded server must not panic on start")

	embeddedClient, err := e.GetPubSubClient()
	t.Require().NoError(err, "there must not be an error when creating an embedded eventStreamClient")
	t.Require().NotNil(embeddedClient, "the eventStreamClient must not be nil")
}

func (t *EmbeddedEventStreamTestSuite) TestGetStreamClient() {
	e, err := NewEmbeddedMessaging(t.opts)
	t.Require().NoError(err, "there must not be an error creating a new embedded event stream")
	t.Require().NotNil(e, "the event stream must not be nil")

	t.Require().NotPanics(func() {
		e.Start()
	}, "the embedded server must not panic on start")

	embeddedClient, err := e.GetStreamClient()
	t.Require().NoError(err, "there must not be an error when creating an embedded eventStreamClient")
	t.Require().NotNil(embeddedClient, "the eventStreamClient must not be nil")
}
