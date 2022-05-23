package fsm

import (
	"encoding/binary"
	"encoding/json"
	"errors"
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strconv"
	"strings"
	"sync"

	"github.com/lni/dragonboat/v3/statemachine"
	"go.etcd.io/bbolt"
	"go.etcd.io/etcd/api/v3/mvccpb"
)

const (
	monotonicLogBucket string = "monotonic-log"
	monotonicLogKey    string = "last-index-applied"
	maxKeyDepth        int    = 25
)

var _ statemachine.IOnDiskStateMachine = &BBoltStateMachine{}

type BBoltStateMachine struct {
	ClusterId uint64
	NodeId    uint64
	BasePath  string
	Options   *bbolt.Options

	db *bbolt.DB
	mu sync.RWMutex
}

func NewBBoltStateMachine(clusterId uint64, nodeId uint64, basePath string, options *bbolt.Options) *BBoltStateMachine {
	return &BBoltStateMachine{ClusterId: clusterId, NodeId: nodeId, BasePath: basePath, Options: options}
}

// dbPath returns the database path with or without appending the database file name.
func (b *BBoltStateMachine) dbPath(withDb bool) string {
	core := filepath.Join(b.BasePath,
		fmt.Sprintf("cluster-%s", strconv.FormatUint(b.ClusterId, 10)),
		fmt.Sprintf("node-%s", strconv.FormatUint(b.NodeId, 10)))
	if !withDb {
		return core
	}
	return filepath.Join(core, "store.db")
}

// Open the bbolt backend and read the last index.
// todo (sienna): leverage stopc at some point on bbolt.Open
func (b *BBoltStateMachine) Open(stopc <-chan struct{}) (uint64, error) {

	// create if not exist
	_, err := os.Stat(b.dbPath(true))
	if errors.Is(err, os.ErrNotExist) {
		err = os.MkdirAll(b.dbPath(false), os.FileMode(dbDirModeVal))
		if err != nil {
			return uint64(0), err
		}
	}

	b.db, err = bbolt.Open(b.dbPath(true), os.FileMode(dbFileModeVal), b.Options)
	if err != nil {
		return 0, err
	}

	var index uint64

	b.mu.Lock()
	err = b.db.Update(func(tx *bbolt.Tx) error {
		// todo (sienna): implement db stats on open
		//tx.Stats()

		internalBucket, err := tx.CreateBucketIfNotExists([]byte(monotonicLogBucket))
		if err != nil {
			return err
		}

		// todo (sienna): add createIfNotExists to the key.
		key, val := internalBucket.Cursor().Last()
		if key == nil || val == nil {
			index = 0
			return nil
		}

		index = binary.LittleEndian.Uint64(val)
		return nil
	})
	b.mu.Unlock()

	if err != nil {
		return 0, err
	}

	return index, nil
}

func (b *BBoltStateMachine) Update(entries []statemachine.Entry) ([]statemachine.Entry, error) {
	var lastApplied uint64
	applied := make([]statemachine.Entry, 0)

	b.mu.Lock()
	err := b.db.Batch(func(tx *bbolt.Tx) error {
		monotonicBucket, err := tx.CreateBucketIfNotExists([]byte(monotonicLogBucket))
		if err != nil {
			return err
		}

		// prep the last known good applied commit
		lastAppliedVal := monotonicBucket.Get([]byte(monotonicLogKey))
		lastApplied = binary.LittleEndian.Uint64(lastAppliedVal)

		for idx := range entries {
			var kvp *mvccpb.KeyValue
			if err := json.Unmarshal(entries[idx].Cmd, &kvp); err != nil {
				return err
			}

			// verify we're not trying to create an empty bucket and skip the first item
			bucketHierarchy := strings.Split(string(kvp.Key[:]), "/")[1:]
			bucketHierarchyLen := len(bucketHierarchy)
			if bucketHierarchy[bucketHierarchyLen-1] == "" {
				return errors.New("cannot create empty bucket")
			}

			if bucketHierarchyLen < 3 {
				return errors.New("there must be an account bucket and bucket name")
			}

			if bucketHierarchyLen+3 > maxKeyDepth {
				return fmt.Errorf("the nested key cannot be more than %d levels deep", maxKeyDepth)
			}

			parentBucketName := bucketHierarchy[0]
			childBucketNames := bucketHierarchy[1 : len(bucketHierarchy)-1]

			parentBucket, err := tx.CreateBucketIfNotExists([]byte(parentBucketName))
			if err != nil {
				return err
			}
			if err := putKey(parentBucket, childBucketNames, kvp.Value); err != nil {
				return err
			}

			// store the current index as the last applied commit
			lastAppliedPayload := make([]byte, 8)
			binary.LittleEndian.PutUint64(lastAppliedPayload, entries[idx].Index)
			if err := monotonicBucket.Put([]byte(monotonicLogKey), lastAppliedPayload); err != nil {
				return err
			}

			entries[idx].Result = statemachine.Result{Value: uint64(len(entries[idx].Cmd))}
			applied = append(applied, entries[idx])
		}

		return tx.Commit()
	})
	b.mu.Unlock()

	if err != nil {
		return make([]statemachine.Entry, 0), err
	}

	err = b.db.View(func(tx *bbolt.Tx) error {
		monotonicBucket, err := tx.CreateBucketIfNotExists([]byte(monotonicLogBucket))
		if err != nil {
			return err
		}

		// prep the last known good applied commit
		lastAppliedVal := monotonicBucket.Get([]byte(monotonicLogKey))
		currentIndex := binary.LittleEndian.Uint64(lastAppliedVal)
		if currentIndex == lastApplied {
			return errors.New("none of the commits were applied")
		}

		if currentIndex != entries[len(entries)-1].Index {
			return errors.New("not all entries were applied")
		}
		return nil
	})
	if err != nil {
		return applied, err
	}

	return entries, err
}

// putKey recursively creates buckets until it can put the key
func putKey(parentBucket *bbolt.Bucket, bucketHierarchy []string, val []byte) error {
	if len(bucketHierarchy) < 2 {
		return errors.New("cannot set a key in a bucket if it's not set properly")
	}
	if len(bucketHierarchy) == 2 {
		childBucket, err := parentBucket.CreateBucketIfNotExists([]byte(bucketHierarchy[0]))
		if err != nil {
			return err
		}
		return childBucket.Put([]byte(bucketHierarchy[1]), val)
	}

	if len(bucketHierarchy) > 2 {
		childBucket, err := parentBucket.CreateBucketIfNotExists([]byte(bucketHierarchy[0]))
		if err != nil {
			return err
		}
		return putKey(childBucket, bucketHierarchy[1:], val)
	}

	return nil
}

func (b *BBoltStateMachine) Lookup(i interface{}) (interface{}, error) {
	var payload interface{}

	b.mu.Lock()
	err := b.db.Update(func(tx *bbolt.Tx) error {
		b, err := tx.CreateBucketIfNotExists([]byte(monotonicLogBucket))
		if err != nil {
			return err
		}

		val := b.Get(i.([]byte))
		if err != nil {
			return err
		}
		payload = val

		return nil
	})
	b.mu.Unlock()

	return payload, err
}

func (b *BBoltStateMachine) Sync() error {
	b.mu.Lock()
	defer b.mu.Unlock()
	return b.db.Sync()
}

func (b *BBoltStateMachine) PrepareSnapshot() (interface{}, error) {
	return nil, nil
}

func (b *BBoltStateMachine) SaveSnapshot(ctx interface{}, writer io.Writer, done <-chan struct{}) error {
	b.mu.Lock()
	defer b.mu.Unlock()
	return b.db.Update(func(tx *bbolt.Tx) error {
		_, err := tx.WriteTo(writer)
		return err
	})
}

func (b *BBoltStateMachine) RecoverFromSnapshot(reader io.Reader, i <-chan struct{}) error {
	fn := func(r io.Reader) error {
		target, err := os.Create(b.dbPath(true))
		if err != nil {
			return err
		}
		_, err = io.Copy(target, reader)
		if err != nil {
			return err
		}
		return nil
	}

	// verify the existing database is closed
	b.mu.Lock()
	err := b.db.Close()
	if err != nil {
		b.mu.Unlock()
		return err
	}
	b.mu.Unlock()

	b.mu.Lock()
	_, err = os.Stat(b.dbPath(true))
	if err != nil {
		if os.IsNotExist(err) {
			b.mu.Unlock()
			return fn(reader)
		}
		b.mu.Unlock()
		return err
	}
	b.mu.Unlock()

	b.mu.Lock()
	err = os.Remove(b.dbPath(true))
	if err != nil {
		b.mu.Unlock()
		return err
	}
	b.mu.Unlock()

	return fn(reader)
}

func (b *BBoltStateMachine) Close() error {
	b.mu.Lock()

	err := b.db.Close()
	if err != nil {
		return err
	}

	b.db = nil
	b.mu.Unlock()

	return nil
}
