# Idioms

### package documentation

* should start with `package <name>`
* should be in `<package>.go` file
* first sentence will appear in godoc's package list

short descriptions:
```go
// Package sort provides primitives for sorting slices and user-defined
// collections.
package sort

// Package hash provides interfaces for hash functions.
package hash
```

medium descriptions:
```go
/*
	Package flag implements command-line flag parsing.

	Usage:

	Define flags using flag.String(), Bool(), Int(), etc.
	...
*/
package flag
```

large descriptions deserve its own file `doc.go`:
* <https://golang.org/src/encoding/gob/doc.go>

---

### commenting sections

sections useful to group related code with comments. see [flag.go](https://golang.org/src/flag/flag.go)

```go
// -- int64 Value
type int64Value int64
...

// -- uint Value
type uintValue uint
...

// -- uint64 Value
type uint64Value uint64
...
```

---

checking empty string

```go
// original
if len(path) == 0 {
	return "."
}

// revised
if path == "" {
	return "."
}
```

---

libraries can provide blessed ways for monkey patching

```go
// flag/flag.go
var Usage = func() {
    fmt.Fprintf(os.Stderr, "Usage of %s:\n", os.Args[0])
    PrintDefaults()
}

// http/client.go
var DefaultClient = &Client{}

// http/server.go
var DefaultServeMux = &defaultServeMux
```

---

### Mutex Hat

put `sync.Mutex` in top of block of fields that the mutex protects

```go
// database/sql.go
var (
	driversMu sync.RWMutex
	drivers   = make(map[string]driver.Driver)
)

// testing/match.go
type matcher struct {
	filter    []string
	matchFunc func(pat, str string) (bool, error)

	mu       sync.Mutex
	subNames map[string]int64
}
```

---

use `time.Duration` rather than `int` or `float` to represent time duration

```go
// original
var rpcTimeoutSecs = 30

// revised
var rpcTimeout = 30 * time.Second
```

```go
// net/dial.go
func DialTimeout(network, address string, timeout time.Duration) (Conn, error) { ... }
```

---

### optional function arguments

```go
// net/dial.go
func Dial(network, address string) (Conn, error) { ... }
func DialTimeout(network, address string, timeout time.Duration) (Conn, error) { ... }

// json/encode.go
func Marshal(v interface{}) ([]byte, error) { ... }
func MarshalIndent(v interface{}, prefix, indent string) ([]byte, error) { ... }

// gzip/gzip.go
func NewWriter(w io.Writer) *Writer { ... }
func NewWriterLevel(w io.Writer, level int) (*Writer, error) { ... }
```

---

### naming errors

```go
// error types should be of the form FooError
type ExitError struct {
	...
}

// error values should be of the form ErrFoo
var ErrFormat = errors.New("image: unknown format")
```

---

### prefix error description

```go
var ErrChecksum  = errors.New("zip: checksum error")
var ErrInvalidPublicKey = errors.New("crypto/dsa: invalid public key")
var ErrTooLarge = errors.New("bytes.Buffer: too large")

return nil, errors.New("crypto/sha512: invalid hash function")
```

---

### avoid unused method receiver names

```go
// net/http/http.go
type noBody struct{}

func (noBody) Read([]byte) (int, error)         { return 0, io.EOF }
func (noBody) Close() error                     { return nil }
func (noBody) WriteTo(io.Writer) (int64, error) { return 0, nil }
```

---

### check implements at compile time

```go
// net/http/http.go
var (
	// verify that an io.Copy from NoBody won't require a buffer:
	_ io.WriterTo   = NoBody
	_ io.ReadCloser = NoBody
)
```
