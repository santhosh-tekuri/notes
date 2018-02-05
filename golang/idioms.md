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

optional function arguments

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

