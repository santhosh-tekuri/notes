# Basic Types

Rust is a statically typed language 

---

### Integer Types

unsigned integers:
- `u8 u16 u32 u64 usize`  
- range $0$ to $2^n-1$ where `n` is number of bits

signed integers:
- `i8 i16 i32 i64 isize`  
- range $-2^{n-1}$ to $2^{n-1}-1$ where `n` is number of bits

`usize isize`:
- size of address on the machine. usually `32` or `64` bits
- rust requires array indices, size of array or vector, 
  count of elements in a datastructure as usize type

integer literals:
- can be suffixed by their type: `42u8 1729isize`
- without suffix, type inference is used:
  - if several types would work:
    - defaults to `i32` if that is among possibilities
    - results in error
- prefixes `0x 0o 0b` designate hexadecimal, octal and binary literals
- underscores can be inserted to make numbers more legible: `4_294_967_295 0xffff_ffff 127_u8`

byte literals:
- for ascii code of a character: `b'X'`
- supports escape character: `b'\'' b'\\' b'\n'`
- can use hexadecimal form `b'\xHH'`

type casts:
```rust
assert_eq!( 10_i8 as u16, 10_u16); // in range
assert_eq!( 2525_u16 as i16, 2525_i16); // in range
assert_eq!( -1_i16 as i32, -1_i32); // sign-extended
assert_eq!(65535_u16 as i32, 65535_i32); // zero-extended

// if destination is outi of range, value will be module 2^N
// where N is the width of the destination in bits. This
// is sometimes called "truncation".
assert_eq!( 1000_i16 as u8, 232_u8);
assert_eq!(65535_u32 as i16, -1_i16);
assert_eq!( -1_i8 as u8, 255_u8);
assert_eq!( 255_u8 as i8, -1_i8);
```

integer overflow:
```rust
let big_val = std::i32::MAX;

// panics in debug builds, but wraps in release builds
let x = big_val + 1;

// to wrap explicitly
let x = big_val.wrapping_add(1);
```

methods:

std library provides methods for basic operations: [see](https://doc.rust-lang.org/std/primitive.i32.html)
```rust
assert_eq!(2u16.pow(4), 16);
assert_eq!((-4i32).abs(), 4);
assert_eq!(0b101101u8.count_ones(), 4);
```

---

### Floating-Point Types

- IEEE single precision `f32`
- IEEE double precision `f64`

literals:
~~~
31415.926e-4f64

31415 integer part
.926  fractional part
e-4   exponent
f64   type suffix
~~~
- except integer part, rest of them are optional
- but one of the remaining 3 must be present to distinguish from integer literal
- fractional part can hase lone decimal point: `5.`
- wihtout type suffix, defaults to f64 if several types could work
- std::f32 and std::f64 modules
  - IEEE special values: `INFINITY NEG_INFINITY NAN MIN MAX`
  - mathematical constants: `E PI`
  - methods like `sqrt floor`
- rust does not to implicit convertions

---

### bool Type

uses one byte in memory, thus allowing pointer to it

can be converted to integer types but not vice versa:
~~~rust
assert_eq!(false as i32, 0);
assert_eq!(true as i32, 1);
~~~

---

### char Type

- 32-bit value
- represents single unicode character
- literals are characters enclosed in single quotes: `'8' 'i' '錆'`
- backslash used as escape character: `'\'' '\\' '\n'`
- for characters in range U+0000 to U+007F: `'\xHH'`
- for any unicode character: `'\u{HHHHHH}' '\u{CA0}'`
- must be valid unicode point


---

`type inference` helps to omit types where it is obvious:

```rust
// without type inference
fn build_vector() -> Vec<i16> {
    let mut v: Vec<i16> = Vec::<i16>::new();
    v.push(10i16);
    v.push(20i16);
    v
}

// with type inference
fn build_vector() -> Vec<i16> {
    let mut v = Vec::new();
    v.push(10);
    v.push(20);
    v
}
```

