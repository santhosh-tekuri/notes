# sudoers file

`/etc/sudoers` file is used to grant `sudo` privileges to users

---

## User Specification

```
<users> <hosts>=<run-as> <tags> <commands>
  |        |        |      |        |
  |        |        |      |        what commands
  |        |        |      special things
  |        |        as what users
  |        on what machines
  who can run
```

`<users>` is comma separated list of:
* `<user-name>`
* `#<uid>`
* `%<group>`
* `%#<gid>`
* `<alias>`

`<run-as>` is same as `<users>` but encosed in parenthesis

`<tags>` is optional and concatenated list of:
* `NOPASSWD:` no need to authenticate user by asking password

`<commands>` is comma separate list of:
* `<file>`
* `<directory>`
* `<alias>`

---

## Aliases

`XXX_Alias = <list>` where `XXX` can be `User`, `Host`, `Runas`, `Cmnd`

`ALL` is built-in alias for all types

---

## Examples

```
tom ALL=(ALL) ALL
jerry ALL=(ALL) NOPASSWD: ALL
jerry ALL=(ALL) NOPASSWD: /usr/local/nginx/bin

Cmnd_Alias SHUTDOWN_CMDS = /sbin/poweroff, /sbin/reboot, /sbin/halt
harry ALL= NOPASSWD: SHUTDOWN_CMDS
```

---

## visudo

use `visudo` rather than `vi` to edit `sudoers` file. advantages:
* will not save the changes if there is a syntax error
* locks the `sudoers` file against multiple simultaneous edits
* provides basic sanity checks

---

## sudoers.d directory

some systems like ubuntu has following line in `sudoers` file:  
`#includedir /etc/sudoers.d`

the `includedir` directive:
* reads each file in directory in lexical order. note `1_file` is loaded after `10_file`
* skips file names
    * ending with `~`
    * contain `.`
    * editor temporary/backup files

advantages:
* changes in files in `/etc/sudoers.d` remain in place if you upgrade the system
* allow application to enable `sudo` capability on installation and remove them when un-installed
* isolate changes required to grant access to specific user

::: tip
use `visudo -f <file>` to edit files in directory  
create self-contained files by not using definitions from sudoers file
:::
