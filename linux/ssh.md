# ssh

`-p <num>` specify port number. default is `22`  
`-i <identity-file>` specify private key for public-key authentication

---

### Escape Sequences

used to interact with ssh command from within a session  
only interpreted if they are the first thing that is typed after a newline

`~?` print supported escaped sequences  
`~~` to type literal `~`  
`~.` terminate connection  
`~C` open ssh command line

---

### Execute commands

```
$ ssh mars 'pwd; whoami'
/Users/santhosh
santhosh
```

use `-t` if commands require interactive shell:
```
$ ssh -t mars 'sudo pwd'
[sudo] password for santhosh:
/Users/santhosh
```

to read commands from file or stdin:
```
$ ssh -T mars <test.sh
$ ssh -T mars <<EOF
pwd
ls
EOF
```
`-T` flag disables warning `Pseudo-terminal will not be allocated because stdin is not a terminal`

::: tip
exit code reflects the command's exit code
:::
