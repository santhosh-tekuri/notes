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

## string expansion

```
$ echo $'line1\nline2\tend'
line1
line2   end
```

---

## special parameters

`$_` final argument of last command

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

`${var/pattern/string}` longest match of `pattern` is replaced with `string`

if pattern begins with:
- `/` replace all matches, otherwise only first match
- `#` match at beginning
- `%` match at end
- if `string` is not specifed, delete match (`/` following pattern can be omitted)

```
var=abcdef

${var#abc}    # def (delete matching prefix)
${var%def}    # abc (delete matching suffix)
${var/bc/BC}  # aBCdef (replace first matching)
${var/#ab/AB} # ABcdef (repace matching prefix)
${var/%ef/EF} # abcdEF (replace matching suffix)
${var//bc/BC} # replace all matchings
${var/bc}     # adef (remove pattern)
```

<https://www.shellscript.sh/tips/pattern-substitution/>

---

## case conversion

```
var=abcdef
${var^}  # Abcdef (first letter to upper)
${var^^} # ABCDEF (all letters to upper)

var=ABCDEF
${var,}  # aBCDEF (first letter to lower)
${var,,} # abcdef (all letters to lower)
```

---

## indirect variable referencing

```
a=letter
letter=z

v1=${!a}      # v1=z
eval v2=\$$a  # v2=z
```

---

## names with dot

bash does not understand environment variables with dot

```
$ # pass variable with dot in name
$ env cluster.name=redis <command>

$ # to get value
$ printenv cluster.name
$ awk 'BEGIN {print ENVIRON["cluster.name"]}'
$ echo cluster.name | awk '{print ENVIRON[$1]}'
```

---

### References

* <http://www.gnu.org/software/bash/manual/html_node/Shell-Parameter-Expansion.html> 
* <http://tldp.org/LDP/abs/html/bashver2.html>
