/*
 * Copyright (c) 2023 Sienna Lloyd
 *
 * Licensed under the PolyForm Internal Use License 1.0.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License here:
 *  https://github.com/mxplusb/pleiades/blob/mainline/LICENSE
 */

// @generated by protoc-gen-es v0.1.1 with parameter "target=js+dts"
// @generated from file errors/v1/error_codes.proto (package errors.v1, syntax proto3)
/* eslint-disable */
/* @ts-nocheck */

import {proto3} from "@bufbuild/protobuf";

/**
 * The canonical error codes for gRPC APIs.
 *
 *
 * Sometimes multiple error codes may apply.  Services should return
 * the most specific error code that applies.  For example, prefer
 * `OUT_OF_RANGE` over `FAILED_PRECONDITION` if both codes apply.
 * Similarly prefer `NOT_FOUND` or `ALREADY_EXISTS` over `FAILED_PRECONDITION`.
 *
 * @generated from enum errors.v1.Code
 */
export const Code = proto3.makeEnum(
  "errors.v1.Code",
  [
    {no: 0, name: "CODE_UNSPECIFIED", localName: "UNSPECIFIED"},
    {no: 1, name: "CODE_OK", localName: "OK"},
    {no: 2, name: "CODE_CANCELLED", localName: "CANCELLED"},
    {no: 3, name: "CODE_UNKNOWN", localName: "UNKNOWN"},
    {no: 4, name: "CODE_INVALID_ARGUMENT", localName: "INVALID_ARGUMENT"},
    {no: 5, name: "CODE_DEADLINE_EXCEEDED", localName: "DEADLINE_EXCEEDED"},
    {no: 6, name: "CODE_NOT_FOUND", localName: "NOT_FOUND"},
    {no: 7, name: "CODE_ALREADY_EXISTS", localName: "ALREADY_EXISTS"},
    {no: 8, name: "CODE_PERMISSION_DENIED", localName: "PERMISSION_DENIED"},
    {no: 9, name: "CODE_UNAUTHENTICATED", localName: "UNAUTHENTICATED"},
    {no: 10, name: "CODE_RESOURCE_EXHAUSTED", localName: "RESOURCE_EXHAUSTED"},
    {no: 11, name: "CODE_FAILED_PRECONDITION", localName: "FAILED_PRECONDITION"},
    {no: 12, name: "CODE_ABORTED", localName: "ABORTED"},
    {no: 13, name: "CODE_OUT_OF_RANGE", localName: "OUT_OF_RANGE"},
    {no: 14, name: "CODE_UNIMPLEMENTED", localName: "UNIMPLEMENTED"},
    {no: 15, name: "CODE_INTERNAL", localName: "INTERNAL"},
    {no: 16, name: "CODE_UNAVAILABLE", localName: "UNAVAILABLE"},
    {no: 17, name: "CODE_DATA_LOSS", localName: "DATA_LOSS"},
  ],
);

