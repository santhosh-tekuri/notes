# I/O Redirection

each open file gets assigned a file descriptor
* `0` file descriptor for stdin
* `1` file descriptor for stdout
* `2` file descriptor for stderr
* additional opened files are assigned from `3` onwards

file descriptors are not unique across processes. they are just indices of process's own file descriptor table

![filedescriptors.npg](files/filedescriptors.jpg)

---

## Here Documents

<http://tldp.org/LDP/abs/html/here-docs.html>


```bash
$ cat test.sh
cat <<EOF
hello
$(date)
\$(date)
$USER
EOF
$ ./test.sh
hello
Thu Dec  7 18:26:33 IST 2017
$(date)
santhosh
```

* the word `EOF` is called limit-string
* all lines until a line with just limit-string are redirected as input to command
* limit-string can be any thing, sufficiently unusual that it will not occur anywhere in the content
* notice that here document supports parameter and command substitution
    * to turn off this use one of the following:
        * `<<'EOF'`
        * `<<"EOF"`
        * `<<\EOF`

use `<<-` to suppress leading tabs in output. but leading whitespaces are not stripped

```bash
$ cat test.sh
if true; then
    cat <<-EOF
        hello
        $(date)
        $USER
    EOF
fi
$ ./test.sh
hello
Thu Dec  7 18:46:15 IST 2017
santhosh
```

---

## Here Strings

<http://tldp.org/LDP/abs/html/x17837.html>

a stripped-down form of a here document  
`COMMAND<<<WORD`: `WORD` is expanded and fed to stdin of `COMMAND`

```bash
$ cat <<<hello
hello
$ cat <<<$USER
santhosh
$ cat <<<$(date)
Thu Dec  7 20:05:19 IST 2017
```

---

## Redirection

<http://tldp.org/LDP/abs/html/io-redirection.html>

`M>F`
* create file `F` if does not exit
* redirect file descritor `M` to file `F`
* `M` defaults to `1`
* contents of file `F` are overwritten

`M>>F`
* same as above except appended to file `F`

`M>&N`
* redirect file descriptor `M` to file descriptor `N`
* `M` defaults to `1`

`0<F` or `<F`
* stdin from file `F`

`M<>F`
* create file `F` if does not exist
* open it for reading and writing 
* assign file descriptor `M` to it

`M<&-`
* close input descriptor `M`
* `M` defaults to `0`

`M>&-`
* close output descriptor `M`
* `M` defaults to `1`

`cmd1 | cmd2`
* redirect stdout of `cmd1` to stdin of `cmd2`

::: tip
multiple instances of input and output redirection and/or pipes can be combined in a single command line
:::

```bash
# append both stdout and stderr to file
bad_command >>filename 2>&1

# send stderr through pipe
bad_command 2>&1 | awk '{print $5}'

# both of below are equivalent
command < input-file > output-file
< input-file command > output-file

# Sorts the output of all the .txt files and deletes duplicate lines, finally saves results to "result-file"
cat *.txt | sort | uniq > result-file
```

bash 4 supports following additional operators:

```bash
# append both stdout and stderr to file
bad_command &>filename 2>&1

# send stderr through pipe
bad_command |& awk '{print $5}'
```

---

## Using exec

<http://tldp.org/LDP/abs/html/x17974.html>

### redirecting stdin using exec

```bash
exec 6<&0       # link file descriptor 6 with stdin i.e. save stdin
exec <input.txt # stdin replaced by file input.txt
read a          # read first line from input.txt
echo $a
exec <&6 6<&-   # restore stdin from fd #6 & close fd #6 to free
read b          # reads from stdin
echo $b
```

### redirecting stdout using exec

```bash
exec 6>&1        # link fd #6 with stdout i.e. save stdout
exec >output.txt # stdout replaced by file output.txt, note that content of file are lost
date             # output goes to output.txt
exec >&6 6>&-    # restore stdout from fd #6 & clode fd #6 to free
cal
```

### redirecting both stdin and stdout using exec

```bash
# this script converts input file to uppercase
# args: <input-file> <output-file>

exec 4<&0 # save stdin
exec 5>&1 # save stdout
exec <$1  # stdin replaced by input-file
exec >$2  # stdin replaced by output-file

cat - | tr a-z A-z

exec <&4 4<&- # restore stdin and close fd #4
exec >&5 5>&- # restore stdout and close fd #5

echo $1 is written to $2 as uppercase   # goes to stdout
```

```bash
#  writing at a specified place in a file
echo 1234567890 > File    # Write string to "File".
exec 3<> File             # Open "File" and assign fd 3 to it.
read -n 4 <&3             # Read only 4 characters.
echo -n . >&3             # Write a decimal point there.
exec 3>&-                 # Close fd 3.
cat File                  # prints 1234.67890

# Redirecting only stderr to a pipe.
exec 3>&1                              # Save current "value" of stdout.
ls -l 2>&1 >&3 3>&- | grep bad 3>&-    # Close fd 3 for 'grep' (but not 'ls').
#              ^^^^   ^^^^
exec 3>&-                              # Now close it for the remainder of the script.
```

TODO:
* [Redirecting codeblocks](http://tldp.org/LDP/abs/html/redircb.html)
