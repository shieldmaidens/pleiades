// @generated by protoc-gen-es v0.1.1 with parameter "target=js+dts"
// @generated from file errors/v1/errors.proto (package errors.v1, syntax proto3)
/* eslint-disable */
/* @ts-nocheck */

import {proto3} from "@bufbuild/protobuf";
import {Code} from "./error_codes_pb.js";

/**
 * The `Status` type defines a logical error model that is suitable for
 * different programming environments, including REST APIs and RPC APIs.
 *
 * @generated from message errors.v1.Error
 */
export const Error = proto3.makeMessageType(
  "errors.v1.Error",
  () => [
    { no: 1, name: "code", kind: "enum", T: proto3.getEnumType(Code) },
    { no: 2, name: "message", kind: "scalar", T: 9 /* ScalarType.STRING */ },
  ],
);
