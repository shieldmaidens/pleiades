/**
 * Generated by the protoc-gen-ts.  DO NOT EDIT!
 * compiler version: 3.20.1
 * source: api/v1/database/store.proto
 * git: https://github.com/thesayyn/protoc-gen-ts */
import * as dependency_1 from "./transactions";
import * as dependency_2 from "./kv";
import * as pb_1 from "google-protobuf";
export namespace database {
    export class DeleteResponse extends pb_1.Message {
        #one_of_decls: number[][] = [];
        constructor(data?: any[] | {}) {
            super();
            pb_1.Message.initialize(this, Array.isArray(data) ? data : [], 0, -1, [], this.#one_of_decls);
            if (!Array.isArray(data) && typeof data == "object") { }
        }
        static fromObject(data: {}): DeleteResponse {
            const message = new DeleteResponse({});
            return message;
        }
        toObject() {
            const data: {} = {};
            return data;
        }
        serialize(): Uint8Array;
        serialize(w: pb_1.BinaryWriter): void;
        serialize(w?: pb_1.BinaryWriter): Uint8Array | void {
            const writer = w || new pb_1.BinaryWriter();
            if (!w)
                return writer.getResultBuffer();
        }
        static deserialize(bytes: Uint8Array | pb_1.BinaryReader): DeleteResponse {
            const reader = bytes instanceof pb_1.BinaryReader ? bytes : new pb_1.BinaryReader(bytes), message = new DeleteResponse();
            while (reader.nextField()) {
                if (reader.isEndGroup())
                    break;
                switch (reader.getFieldNumber()) {
                    default: reader.skipField();
                }
            }
            return message;
        }
        serializeBinary(): Uint8Array {
            return this.serialize();
        }
        static deserializeBinary(bytes: Uint8Array): DeleteResponse {
            return DeleteResponse.deserialize(bytes);
        }
    }
    export class DeleteRequest extends pb_1.Message {
        #one_of_decls: number[][] = [];
        constructor(data?: any[] | {
            payload?: dependency_2.database.KeyValue;
            session?: dependency_1.database.Transaction;
        }) {
            super();
            pb_1.Message.initialize(this, Array.isArray(data) ? data : [], 0, -1, [], this.#one_of_decls);
            if (!Array.isArray(data) && typeof data == "object") {
                if ("payload" in data && data.payload != undefined) {
                    this.payload = data.payload;
                }
                if ("session" in data && data.session != undefined) {
                    this.session = data.session;
                }
            }
        }
        get payload() {
            return pb_1.Message.getWrapperField(this, dependency_2.database.KeyValue, 1) as dependency_2.database.KeyValue;
        }
        set payload(value: dependency_2.database.KeyValue) {
            pb_1.Message.setWrapperField(this, 1, value);
        }
        get has_payload() {
            return pb_1.Message.getField(this, 1) != null;
        }
        get session() {
            return pb_1.Message.getWrapperField(this, dependency_1.database.Transaction, 2) as dependency_1.database.Transaction;
        }
        set session(value: dependency_1.database.Transaction) {
            pb_1.Message.setWrapperField(this, 2, value);
        }
        get has_session() {
            return pb_1.Message.getField(this, 2) != null;
        }
        static fromObject(data: {
            payload?: ReturnType<typeof dependency_2.database.KeyValue.prototype.toObject>;
            session?: ReturnType<typeof dependency_1.database.Transaction.prototype.toObject>;
        }): DeleteRequest {
            const message = new DeleteRequest({});
            if (data.payload != null) {
                message.payload = dependency_2.database.KeyValue.fromObject(data.payload);
            }
            if (data.session != null) {
                message.session = dependency_1.database.Transaction.fromObject(data.session);
            }
            return message;
        }
        toObject() {
            const data: {
                payload?: ReturnType<typeof dependency_2.database.KeyValue.prototype.toObject>;
                session?: ReturnType<typeof dependency_1.database.Transaction.prototype.toObject>;
            } = {};
            if (this.payload != null) {
                data.payload = this.payload.toObject();
            }
            if (this.session != null) {
                data.session = this.session.toObject();
            }
            return data;
        }
        serialize(): Uint8Array;
        serialize(w: pb_1.BinaryWriter): void;
        serialize(w?: pb_1.BinaryWriter): Uint8Array | void {
            const writer = w || new pb_1.BinaryWriter();
            if (this.has_payload)
                writer.writeMessage(1, this.payload, () => this.payload.serialize(writer));
            if (this.has_session)
                writer.writeMessage(2, this.session, () => this.session.serialize(writer));
            if (!w)
                return writer.getResultBuffer();
        }
        static deserialize(bytes: Uint8Array | pb_1.BinaryReader): DeleteRequest {
            const reader = bytes instanceof pb_1.BinaryReader ? bytes : new pb_1.BinaryReader(bytes), message = new DeleteRequest();
            while (reader.nextField()) {
                if (reader.isEndGroup())
                    break;
                switch (reader.getFieldNumber()) {
                    case 1:
                        reader.readMessage(message.payload, () => message.payload = dependency_2.database.KeyValue.deserialize(reader));
                        break;
                    case 2:
                        reader.readMessage(message.session, () => message.session = dependency_1.database.Transaction.deserialize(reader));
                        break;
                    default: reader.skipField();
                }
            }
            return message;
        }
        serializeBinary(): Uint8Array {
            return this.serialize();
        }
        static deserializeBinary(bytes: Uint8Array): DeleteRequest {
            return DeleteRequest.deserialize(bytes);
        }
    }
    export class PutRequest extends pb_1.Message {
        #one_of_decls: number[][] = [];
        constructor(data?: any[] | {
            payload?: dependency_2.database.KeyValue;
            session?: dependency_1.database.Transaction;
        }) {
            super();
            pb_1.Message.initialize(this, Array.isArray(data) ? data : [], 0, -1, [], this.#one_of_decls);
            if (!Array.isArray(data) && typeof data == "object") {
                if ("payload" in data && data.payload != undefined) {
                    this.payload = data.payload;
                }
                if ("session" in data && data.session != undefined) {
                    this.session = data.session;
                }
            }
        }
        get payload() {
            return pb_1.Message.getWrapperField(this, dependency_2.database.KeyValue, 1) as dependency_2.database.KeyValue;
        }
        set payload(value: dependency_2.database.KeyValue) {
            pb_1.Message.setWrapperField(this, 1, value);
        }
        get has_payload() {
            return pb_1.Message.getField(this, 1) != null;
        }
        get session() {
            return pb_1.Message.getWrapperField(this, dependency_1.database.Transaction, 2) as dependency_1.database.Transaction;
        }
        set session(value: dependency_1.database.Transaction) {
            pb_1.Message.setWrapperField(this, 2, value);
        }
        get has_session() {
            return pb_1.Message.getField(this, 2) != null;
        }
        static fromObject(data: {
            payload?: ReturnType<typeof dependency_2.database.KeyValue.prototype.toObject>;
            session?: ReturnType<typeof dependency_1.database.Transaction.prototype.toObject>;
        }): PutRequest {
            const message = new PutRequest({});
            if (data.payload != null) {
                message.payload = dependency_2.database.KeyValue.fromObject(data.payload);
            }
            if (data.session != null) {
                message.session = dependency_1.database.Transaction.fromObject(data.session);
            }
            return message;
        }
        toObject() {
            const data: {
                payload?: ReturnType<typeof dependency_2.database.KeyValue.prototype.toObject>;
                session?: ReturnType<typeof dependency_1.database.Transaction.prototype.toObject>;
            } = {};
            if (this.payload != null) {
                data.payload = this.payload.toObject();
            }
            if (this.session != null) {
                data.session = this.session.toObject();
            }
            return data;
        }
        serialize(): Uint8Array;
        serialize(w: pb_1.BinaryWriter): void;
        serialize(w?: pb_1.BinaryWriter): Uint8Array | void {
            const writer = w || new pb_1.BinaryWriter();
            if (this.has_payload)
                writer.writeMessage(1, this.payload, () => this.payload.serialize(writer));
            if (this.has_session)
                writer.writeMessage(2, this.session, () => this.session.serialize(writer));
            if (!w)
                return writer.getResultBuffer();
        }
        static deserialize(bytes: Uint8Array | pb_1.BinaryReader): PutRequest {
            const reader = bytes instanceof pb_1.BinaryReader ? bytes : new pb_1.BinaryReader(bytes), message = new PutRequest();
            while (reader.nextField()) {
                if (reader.isEndGroup())
                    break;
                switch (reader.getFieldNumber()) {
                    case 1:
                        reader.readMessage(message.payload, () => message.payload = dependency_2.database.KeyValue.deserialize(reader));
                        break;
                    case 2:
                        reader.readMessage(message.session, () => message.session = dependency_1.database.Transaction.deserialize(reader));
                        break;
                    default: reader.skipField();
                }
            }
            return message;
        }
        serializeBinary(): Uint8Array {
            return this.serialize();
        }
        static deserializeBinary(bytes: Uint8Array): PutRequest {
            return PutRequest.deserialize(bytes);
        }
    }
    export class PutReply extends pb_1.Message {
        #one_of_decls: number[][] = [];
        constructor(data?: any[] | {}) {
            super();
            pb_1.Message.initialize(this, Array.isArray(data) ? data : [], 0, -1, [], this.#one_of_decls);
            if (!Array.isArray(data) && typeof data == "object") { }
        }
        static fromObject(data: {}): PutReply {
            const message = new PutReply({});
            return message;
        }
        toObject() {
            const data: {} = {};
            return data;
        }
        serialize(): Uint8Array;
        serialize(w: pb_1.BinaryWriter): void;
        serialize(w?: pb_1.BinaryWriter): Uint8Array | void {
            const writer = w || new pb_1.BinaryWriter();
            if (!w)
                return writer.getResultBuffer();
        }
        static deserialize(bytes: Uint8Array | pb_1.BinaryReader): PutReply {
            const reader = bytes instanceof pb_1.BinaryReader ? bytes : new pb_1.BinaryReader(bytes), message = new PutReply();
            while (reader.nextField()) {
                if (reader.isEndGroup())
                    break;
                switch (reader.getFieldNumber()) {
                    default: reader.skipField();
                }
            }
            return message;
        }
        serializeBinary(): Uint8Array {
            return this.serialize();
        }
        static deserializeBinary(bytes: Uint8Array): PutReply {
            return PutReply.deserialize(bytes);
        }
    }
    export class GetRequest extends pb_1.Message {
        #one_of_decls: number[][] = [];
        constructor(data?: any[] | {
            key?: string;
            clusterId?: number;
        }) {
            super();
            pb_1.Message.initialize(this, Array.isArray(data) ? data : [], 0, -1, [], this.#one_of_decls);
            if (!Array.isArray(data) && typeof data == "object") {
                if ("key" in data && data.key != undefined) {
                    this.key = data.key;
                }
                if ("clusterId" in data && data.clusterId != undefined) {
                    this.clusterId = data.clusterId;
                }
            }
        }
        get key() {
            return pb_1.Message.getFieldWithDefault(this, 1, "") as string;
        }
        set key(value: string) {
            pb_1.Message.setField(this, 1, value);
        }
        get clusterId() {
            return pb_1.Message.getFieldWithDefault(this, 3, 0) as number;
        }
        set clusterId(value: number) {
            pb_1.Message.setField(this, 3, value);
        }
        static fromObject(data: {
            key?: string;
            clusterId?: number;
        }): GetRequest {
            const message = new GetRequest({});
            if (data.key != null) {
                message.key = data.key;
            }
            if (data.clusterId != null) {
                message.clusterId = data.clusterId;
            }
            return message;
        }
        toObject() {
            const data: {
                key?: string;
                clusterId?: number;
            } = {};
            if (this.key != null) {
                data.key = this.key;
            }
            if (this.clusterId != null) {
                data.clusterId = this.clusterId;
            }
            return data;
        }
        serialize(): Uint8Array;
        serialize(w: pb_1.BinaryWriter): void;
        serialize(w?: pb_1.BinaryWriter): Uint8Array | void {
            const writer = w || new pb_1.BinaryWriter();
            if (this.key.length)
                writer.writeString(1, this.key);
            if (this.clusterId != 0)
                writer.writeUint64(3, this.clusterId);
            if (!w)
                return writer.getResultBuffer();
        }
        static deserialize(bytes: Uint8Array | pb_1.BinaryReader): GetRequest {
            const reader = bytes instanceof pb_1.BinaryReader ? bytes : new pb_1.BinaryReader(bytes), message = new GetRequest();
            while (reader.nextField()) {
                if (reader.isEndGroup())
                    break;
                switch (reader.getFieldNumber()) {
                    case 1:
                        message.key = reader.readString();
                        break;
                    case 3:
                        message.clusterId = reader.readUint64();
                        break;
                    default: reader.skipField();
                }
            }
            return message;
        }
        serializeBinary(): Uint8Array {
            return this.serialize();
        }
        static deserializeBinary(bytes: Uint8Array): GetRequest {
            return GetRequest.deserialize(bytes);
        }
    }
    export class GetResponse extends pb_1.Message {
        #one_of_decls: number[][] = [];
        constructor(data?: any[] | {
            results?: dependency_2.database.KeyValue[];
        }) {
            super();
            pb_1.Message.initialize(this, Array.isArray(data) ? data : [], 0, -1, [1], this.#one_of_decls);
            if (!Array.isArray(data) && typeof data == "object") {
                if ("results" in data && data.results != undefined) {
                    this.results = data.results;
                }
            }
        }
        get results() {
            return pb_1.Message.getRepeatedWrapperField(this, dependency_2.database.KeyValue, 1) as dependency_2.database.KeyValue[];
        }
        set results(value: dependency_2.database.KeyValue[]) {
            pb_1.Message.setRepeatedWrapperField(this, 1, value);
        }
        static fromObject(data: {
            results?: ReturnType<typeof dependency_2.database.KeyValue.prototype.toObject>[];
        }): GetResponse {
            const message = new GetResponse({});
            if (data.results != null) {
                message.results = data.results.map(item => dependency_2.database.KeyValue.fromObject(item));
            }
            return message;
        }
        toObject() {
            const data: {
                results?: ReturnType<typeof dependency_2.database.KeyValue.prototype.toObject>[];
            } = {};
            if (this.results != null) {
                data.results = this.results.map((item: dependency_2.database.KeyValue) => item.toObject());
            }
            return data;
        }
        serialize(): Uint8Array;
        serialize(w: pb_1.BinaryWriter): void;
        serialize(w?: pb_1.BinaryWriter): Uint8Array | void {
            const writer = w || new pb_1.BinaryWriter();
            if (this.results.length)
                writer.writeRepeatedMessage(1, this.results, (item: dependency_2.database.KeyValue) => item.serialize(writer));
            if (!w)
                return writer.getResultBuffer();
        }
        static deserialize(bytes: Uint8Array | pb_1.BinaryReader): GetResponse {
            const reader = bytes instanceof pb_1.BinaryReader ? bytes : new pb_1.BinaryReader(bytes), message = new GetResponse();
            while (reader.nextField()) {
                if (reader.isEndGroup())
                    break;
                switch (reader.getFieldNumber()) {
                    case 1:
                        reader.readMessage(message.results, () => pb_1.Message.addToRepeatedWrapperField(message, 1, dependency_2.database.KeyValue.deserialize(reader), dependency_2.database.KeyValue));
                        break;
                    default: reader.skipField();
                }
            }
            return message;
        }
        serializeBinary(): Uint8Array {
            return this.serialize();
        }
        static deserializeBinary(bytes: Uint8Array): GetResponse {
            return GetResponse.deserialize(bytes);
        }
    }
}
