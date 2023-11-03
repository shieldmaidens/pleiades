/// A trait for encoding and decoding keys and values.
/// May be used for anything, but is primarily used for
/// the KVStore.

pub trait KVEncoder<T>: 
    Send
    + Sync
    + Clone
    + Debug {
    fn encode_key(&self, key: Vec<u8>) -> Result<Vec<u8>, Error>;

    fn encode(&self) -> Result<Vec<u8>, Error>;

    fn decode(&self) -> Result<T, Error>;
}