# Parameter Expansion

## basic usgae
```
$var
${var}
${"var"}
```
when variable name includes `}`:
- use backslash to escape (or)
- use quoted string

---

## defaults
```
${var:-default} # if var is unset/empty, replaced by default
${var:+default} # if var is not unset/empty, replaced by default
```

---

## substring

`${var:offset}` or `${var:offset:length}`

```
var=01234567890abcdefgh

${var:7}      # 7890abcdefgh
${var:7:2}    # 78
${var:7:-2}   # 7890abcdef
${var: -7}    # bcdefgh
${var: -7:2}  # bc
```

---

## length

```
var=one
${#var} # 3
```

---

## pattern matching

```
var=abcdef

${var#abc} # def (delete matching prefix)
${var%def} # abc (delete matching suffix)
${a/bc/BC} # aBCdef (replace matching)
```

---

### References

* <http://www.gnu.org/software/bash/manual/html_node/Shell-Parameter-Expansion.html> 