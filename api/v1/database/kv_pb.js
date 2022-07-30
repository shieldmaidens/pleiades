// source: api/v1/database/kv.proto
/**
 * @fileoverview
 * @enhanceable
 * @suppress {missingRequire} reports error on implicit type usages.
 * @suppress {messageConventions} JS Compiler reports an error if a variable or
 *     field starts with 'MSG_' and isn't a translatable message.
 * @public
 */
// GENERATED CODE -- DO NOT EDIT!
/* eslint-disable */
// @ts-nocheck

var jspb = require('google-protobuf');
var goog = jspb;
var global = (function() { return this || window || global || self || Function('return this')(); }).call(null);

goog.exportSymbol('proto.database.Event', null, global);
goog.exportSymbol('proto.database.Event.EventType', null, global);
goog.exportSymbol('proto.database.KeyValue', null, global);
/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.database.KeyValue = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, null);
};
goog.inherits(proto.database.KeyValue, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  /**
   * @public
   * @override
   */
  proto.database.KeyValue.displayName = 'proto.database.KeyValue';
}
/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.database.Event = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, null);
};
goog.inherits(proto.database.Event, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  /**
   * @public
   * @override
   */
  proto.database.Event.displayName = 'proto.database.Event';
}



if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * Optional fields that are not set will be set to undefined.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     net/proto2/compiler/js/internal/generator.cc#kKeyword.
 * @param {boolean=} opt_includeInstance Deprecated. whether to include the
 *     JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @return {!Object}
 */
proto.database.KeyValue.prototype.toObject = function(opt_includeInstance) {
  return proto.database.KeyValue.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Deprecated. Whether to include
 *     the JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.database.KeyValue} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.database.KeyValue.toObject = function(includeInstance, msg) {
  var f, obj = {
    key: msg.getKey_asB64(),
    createRevision: jspb.Message.getFieldWithDefault(msg, 2, 0),
    modRevision: jspb.Message.getFieldWithDefault(msg, 3, 0),
    version: jspb.Message.getFieldWithDefault(msg, 4, 0),
    value: msg.getValue_asB64(),
    lease: jspb.Message.getFieldWithDefault(msg, 6, 0)
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.database.KeyValue}
 */
proto.database.KeyValue.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.database.KeyValue;
  return proto.database.KeyValue.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.database.KeyValue} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.database.KeyValue}
 */
proto.database.KeyValue.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {!Uint8Array} */ (reader.readBytes());
      msg.setKey(value);
      break;
    case 2:
      var value = /** @type {number} */ (reader.readInt64());
      msg.setCreateRevision(value);
      break;
    case 3:
      var value = /** @type {number} */ (reader.readInt64());
      msg.setModRevision(value);
      break;
    case 4:
      var value = /** @type {number} */ (reader.readInt64());
      msg.setVersion(value);
      break;
    case 5:
      var value = /** @type {!Uint8Array} */ (reader.readBytes());
      msg.setValue(value);
      break;
    case 6:
      var value = /** @type {number} */ (reader.readInt64());
      msg.setLease(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.database.KeyValue.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.database.KeyValue.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.database.KeyValue} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.database.KeyValue.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = message.getKey_asU8();
  if (f.length > 0) {
    writer.writeBytes(
      1,
      f
    );
  }
  f = message.getCreateRevision();
  if (f !== 0) {
    writer.writeInt64(
      2,
      f
    );
  }
  f = message.getModRevision();
  if (f !== 0) {
    writer.writeInt64(
      3,
      f
    );
  }
  f = message.getVersion();
  if (f !== 0) {
    writer.writeInt64(
      4,
      f
    );
  }
  f = message.getValue_asU8();
  if (f.length > 0) {
    writer.writeBytes(
      5,
      f
    );
  }
  f = message.getLease();
  if (f !== 0) {
    writer.writeInt64(
      6,
      f
    );
  }
};


/**
 * optional bytes key = 1;
 * @return {!(string|Uint8Array)}
 */
proto.database.KeyValue.prototype.getKey = function() {
  return /** @type {!(string|Uint8Array)} */ (jspb.Message.getFieldWithDefault(this, 1, ""));
};


/**
 * optional bytes key = 1;
 * This is a type-conversion wrapper around `getKey()`
 * @return {string}
 */
proto.database.KeyValue.prototype.getKey_asB64 = function() {
  return /** @type {string} */ (jspb.Message.bytesAsB64(
      this.getKey()));
};


/**
 * optional bytes key = 1;
 * Note that Uint8Array is not supported on all browsers.
 * @see http://caniuse.com/Uint8Array
 * This is a type-conversion wrapper around `getKey()`
 * @return {!Uint8Array}
 */
proto.database.KeyValue.prototype.getKey_asU8 = function() {
  return /** @type {!Uint8Array} */ (jspb.Message.bytesAsU8(
      this.getKey()));
};


/**
 * @param {!(string|Uint8Array)} value
 * @return {!proto.database.KeyValue} returns this
 */
proto.database.KeyValue.prototype.setKey = function(value) {
  return jspb.Message.setProto3BytesField(this, 1, value);
};


/**
 * optional int64 create_revision = 2;
 * @return {number}
 */
proto.database.KeyValue.prototype.getCreateRevision = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 2, 0));
};


/**
 * @param {number} value
 * @return {!proto.database.KeyValue} returns this
 */
proto.database.KeyValue.prototype.setCreateRevision = function(value) {
  return jspb.Message.setProto3IntField(this, 2, value);
};


/**
 * optional int64 mod_revision = 3;
 * @return {number}
 */
proto.database.KeyValue.prototype.getModRevision = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 3, 0));
};


/**
 * @param {number} value
 * @return {!proto.database.KeyValue} returns this
 */
proto.database.KeyValue.prototype.setModRevision = function(value) {
  return jspb.Message.setProto3IntField(this, 3, value);
};


/**
 * optional int64 version = 4;
 * @return {number}
 */
proto.database.KeyValue.prototype.getVersion = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 4, 0));
};


/**
 * @param {number} value
 * @return {!proto.database.KeyValue} returns this
 */
proto.database.KeyValue.prototype.setVersion = function(value) {
  return jspb.Message.setProto3IntField(this, 4, value);
};


/**
 * optional bytes value = 5;
 * @return {!(string|Uint8Array)}
 */
proto.database.KeyValue.prototype.getValue = function() {
  return /** @type {!(string|Uint8Array)} */ (jspb.Message.getFieldWithDefault(this, 5, ""));
};


/**
 * optional bytes value = 5;
 * This is a type-conversion wrapper around `getValue()`
 * @return {string}
 */
proto.database.KeyValue.prototype.getValue_asB64 = function() {
  return /** @type {string} */ (jspb.Message.bytesAsB64(
      this.getValue()));
};


/**
 * optional bytes value = 5;
 * Note that Uint8Array is not supported on all browsers.
 * @see http://caniuse.com/Uint8Array
 * This is a type-conversion wrapper around `getValue()`
 * @return {!Uint8Array}
 */
proto.database.KeyValue.prototype.getValue_asU8 = function() {
  return /** @type {!Uint8Array} */ (jspb.Message.bytesAsU8(
      this.getValue()));
};


/**
 * @param {!(string|Uint8Array)} value
 * @return {!proto.database.KeyValue} returns this
 */
proto.database.KeyValue.prototype.setValue = function(value) {
  return jspb.Message.setProto3BytesField(this, 5, value);
};


/**
 * optional int64 lease = 6;
 * @return {number}
 */
proto.database.KeyValue.prototype.getLease = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 6, 0));
};


/**
 * @param {number} value
 * @return {!proto.database.KeyValue} returns this
 */
proto.database.KeyValue.prototype.setLease = function(value) {
  return jspb.Message.setProto3IntField(this, 6, value);
};





if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * Optional fields that are not set will be set to undefined.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     net/proto2/compiler/js/internal/generator.cc#kKeyword.
 * @param {boolean=} opt_includeInstance Deprecated. whether to include the
 *     JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @return {!Object}
 */
proto.database.Event.prototype.toObject = function(opt_includeInstance) {
  return proto.database.Event.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Deprecated. Whether to include
 *     the JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.database.Event} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.database.Event.toObject = function(includeInstance, msg) {
  var f, obj = {
    type: jspb.Message.getFieldWithDefault(msg, 1, 0),
    kv: (f = msg.getKv()) && proto.database.KeyValue.toObject(includeInstance, f),
    prevKv: (f = msg.getPrevKv()) && proto.database.KeyValue.toObject(includeInstance, f)
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.database.Event}
 */
proto.database.Event.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.database.Event;
  return proto.database.Event.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.database.Event} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.database.Event}
 */
proto.database.Event.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {!proto.database.Event.EventType} */ (reader.readEnum());
      msg.setType(value);
      break;
    case 2:
      var value = new proto.database.KeyValue;
      reader.readMessage(value,proto.database.KeyValue.deserializeBinaryFromReader);
      msg.setKv(value);
      break;
    case 3:
      var value = new proto.database.KeyValue;
      reader.readMessage(value,proto.database.KeyValue.deserializeBinaryFromReader);
      msg.setPrevKv(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.database.Event.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.database.Event.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.database.Event} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.database.Event.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = message.getType();
  if (f !== 0.0) {
    writer.writeEnum(
      1,
      f
    );
  }
  f = message.getKv();
  if (f != null) {
    writer.writeMessage(
      2,
      f,
      proto.database.KeyValue.serializeBinaryToWriter
    );
  }
  f = message.getPrevKv();
  if (f != null) {
    writer.writeMessage(
      3,
      f,
      proto.database.KeyValue.serializeBinaryToWriter
    );
  }
};


/**
 * @enum {number}
 */
proto.database.Event.EventType = {
  PUT: 0,
  DELETE: 1
};

/**
 * optional EventType type = 1;
 * @return {!proto.database.Event.EventType}
 */
proto.database.Event.prototype.getType = function() {
  return /** @type {!proto.database.Event.EventType} */ (jspb.Message.getFieldWithDefault(this, 1, 0));
};


/**
 * @param {!proto.database.Event.EventType} value
 * @return {!proto.database.Event} returns this
 */
proto.database.Event.prototype.setType = function(value) {
  return jspb.Message.setProto3EnumField(this, 1, value);
};


/**
 * optional KeyValue kv = 2;
 * @return {?proto.database.KeyValue}
 */
proto.database.Event.prototype.getKv = function() {
  return /** @type{?proto.database.KeyValue} */ (
    jspb.Message.getWrapperField(this, proto.database.KeyValue, 2));
};


/**
 * @param {?proto.database.KeyValue|undefined} value
 * @return {!proto.database.Event} returns this
*/
proto.database.Event.prototype.setKv = function(value) {
  return jspb.Message.setWrapperField(this, 2, value);
};


/**
 * Clears the message field making it undefined.
 * @return {!proto.database.Event} returns this
 */
proto.database.Event.prototype.clearKv = function() {
  return this.setKv(undefined);
};


/**
 * Returns whether this field is set.
 * @return {boolean}
 */
proto.database.Event.prototype.hasKv = function() {
  return jspb.Message.getField(this, 2) != null;
};


/**
 * optional KeyValue prev_kv = 3;
 * @return {?proto.database.KeyValue}
 */
proto.database.Event.prototype.getPrevKv = function() {
  return /** @type{?proto.database.KeyValue} */ (
    jspb.Message.getWrapperField(this, proto.database.KeyValue, 3));
};


/**
 * @param {?proto.database.KeyValue|undefined} value
 * @return {!proto.database.Event} returns this
*/
proto.database.Event.prototype.setPrevKv = function(value) {
  return jspb.Message.setWrapperField(this, 3, value);
};


/**
 * Clears the message field making it undefined.
 * @return {!proto.database.Event} returns this
 */
proto.database.Event.prototype.clearPrevKv = function() {
  return this.setPrevKv(undefined);
};


/**
 * Returns whether this field is set.
 * @return {boolean}
 */
proto.database.Event.prototype.hasPrevKv = function() {
  return jspb.Message.getField(this, 3) != null;
};


goog.object.extend(exports, proto.database);