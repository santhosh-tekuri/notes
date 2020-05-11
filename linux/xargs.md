# xargs

usage: `xargs [options] utility`

splits input by tabs, spaces, blanklines and executes `utility` with the strings as arguments. if `utility` is omitted, `echo` is used

by default multiple aruments will be passed to `utility`. default maximum 5000 or max length 4096
* `-n` to limit number of arguments
* `-s` to limit length of arguments

it can run command parallelly
* `-P` to limit number of procs

```shell
$ echo one two three | xargs
one two three
$ echo one two three | xargs -n 1
one
two
three
```

`-t` prints the command to stderr before executing. useful for debugging  
`-p` print the command and ask user whether it should be executed

use `-I` to position argument at any place or place multiple times.  
`xargs -I % echo sh -c 'echo %; mv % destdir'`

`-0` splits input by NULL character. used with `find -print0`  
`-d` to change delimiter. supports C sytle escape

