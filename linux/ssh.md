# ssh

`-p <num>` specify port number. default is `22`  
`-i <identity-file>` specify private key for public-key authentication

---

## Escape Sequences

used to interact with ssh command from within a session  
only interpreted if they are the first thing that is typed after a newline

`~?` print supported escaped sequences  
`~~` to type literal `~`  
`~.` terminate connection  
`~C` open ssh command line

---

## Local Port Forwarding

```
ssh -L <bind-address>:<remote-address> mars
```

listens on `<bind_address>` in local side, and any connections accepted are forwarded over secure channel to `<remote-address>`

`<bind-address>` can be one of the following:
* `<host>:<port>` listens on specified host and port. host is resolved on local side
* `<port>` `*:<port>` listens on all interfaces
* `<unix-socket>` listens on unix socket

`<remote-address>` can be one of following:
* `<host>:<port>` forwards to host and port. host is resolved on remote side
* `<unix-socket>` forwards to unix socket

ssh server can eforce restrictions using its configuration `/etc/ssh/sshd_config`:
* `AllowTcpForwarding` to enforce restrictions on TCP forwardings:
    * `yes` `all` to allow all tcp forwarding. this is default value
    * `local` to allow local tcp forwardings
    * `remote` to allow remote remote forwardings
    * `no` to prevent tcp forwardings
* `AllowStreamLocalForwarding` to enforce restrictions on unix socket forwardings:
    * `yes` to allow forwardings. this is default value
    * `no` to prevent forwardings

---

## Remote Port Forwarding

```
ssh -R <bind-address>:<local-address> mars
```

listens on `<bind-address>` in remote side, and any connections accepted are forwarded over secure channel to `<local-address>`

`<bind-address>` can be one of the following:
* `<host>:<port>` listens on specified host and port. host is resolved on remote side
* `<port>` `*:<port>` listens on all interfaces
* `<unix-socket>` listens on unix socket

`<local-address>` can be one of following:
* `<host>:<port>` forwards to host and port. host is resolved on local side
* `<unix-socket>` forwards to unix socket

ssh server can eforce restrictions using its configuration `/etc/ssh/sshd_config`:
* `GatewayPorts` to enforce restrictions on TCP forwardings:
    * `yes` `all` to allow all tcp forwarding. this is default value
    * `clientspecified` bind to loopback address by default. i.e. `<port>` listens only on loopback address
    * `no` to prevent tcp forwardings

---

## Dynamic Port Forwarding (SOCKS)

```
ssh -D <bind-address> mars
```

`ssh` acts as SOCKS proxy server.  
listens on `<bind-address>` in local side, and any connections accepted are forwarded over secure channel to remote address
specified by application protocol

`<bind-address>` can be one of following:
* `<host>:<port>` listens on specified host and port. host is resolved on local side
* `<port>` `*:<port>` listens on all interfaces

---

## Execute Commands

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
`-T` disables pseudo-terminal allocation. thus supresses warning `Pseudo-terminal will not be allocated because stdin is not a terminal`

::: tip
exit code reflects the command's exit code
:::

---

## Tunneling Connection

`ProxyCommand` option allows to replace transport with stdin/stdout of given command  
this is useful to ssh a server via [bastion](https://en.wikipedia.org/wiki/Bastion_host)/[jump](https://en.wikipedia.org/wiki/Jump_server) host

```
ssh -o ProxyCommand='ssh <bastion-host> nc <target-host> 22' <target-host>
```

alternative way to achieve same: `ssh -tt <bastion-hot> ssh -tt <target-host>`  
`-t` forces pseudo-tty allocation  
`-tt` forces tty allocation, even if ssh has no local tty
