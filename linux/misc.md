# miscellaneous

### cd to script dir

```shell
cd $(dirname "$BASH_SOURCE")
```

---

### echo to stderr

```shell
>&2 echo "error"
```

---

### multiline comment

```shell
: <<'COMMENT'
echo inside comment line1
echo inside comment line2
echo inside comment line3
COMMENT
echo this is after comment
```

---

### host aliases

```shell
export HOSTALIASES=~/.hosts
echo 'mygoogle www.google.com' > ~/.hosts
ping mygoogle
```

---

### check if command exists

```shell
if [ -x "$(command -v git)" ]; then
    echo git is installed
fi
```

`command -v` prints location of command if found. exit-code zero if found  
`-x` to ensure that file is executable

---

### check if run as root

```shell
if [ $(id -u) -ne 0 ]; then
    echo "Please run as root"
    exit 1
fi
```

---

### special parameters

<https://www.gnu.org/savannah-checkouts/gnu/bash/manual/bash.html#Special-Parameters>

`$$` pid of shell  
`$!` pid of job most recently placed into background  
`$?` exit code of most recent foreground pipeline  
`$#` number of positional parameters  
`$*` positional parameters starting from one

---

### random numbers

`$RANDOM` returns random number between 0 and 32767

if you assign a number to `RANDOM`, the sequence of values generated after that will be consistent

---

### timing

`SECONDS` returns #sec since shell started

after assigning `SECONDS` to zero, it returns #sec since the assignment

---

### add ca cert

```shell
$ domain=myregistry.com

$ # ubuntu
$ cp domain.crt /usr/local/share/ca-certificates/${domain}.crt
$ update-ca-certificates

$ # redhat
$ cp domain.crt /etc/pki/ca-trust/source/anchors/${domain}.crt
$ update-ca-trust
```
make sure any folder created should be 755 and for file 644

---

### ensure only one instance of script running at a time

```shell
exec 4<>"$BASH_SOURCE".lock
flock -n 4 || (echo cannot flock. aborting; exit 1)
```

---

### open alternate screen

```shell
tput smcup # switch to secondary screen
...
tput rmcup # switch back to primary screen

# less elegant but portable
echo -e "\e[?1049h"
...
echo -e "\e[?1049l"
```


