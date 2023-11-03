pub trait KVStore: 
    Send
    + Sync
    + Clone
    + Debug
    + 'static {

    fn get<T>(&self, key: KVEncoder<T>)  -> Result<Option<T>, Error>;
    fn put<T>(&self, key: KVEncoder<T>, payload: KVEncoder<T>) -> Result<(), Error>;
    fn delete<T>(&self, key: KVEncoder<T>) -> Result<(), Error>;
}
