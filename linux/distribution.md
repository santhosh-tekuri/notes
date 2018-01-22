# Introspect Linux Distribution

```shell
$ uname -a # -a for all
Linux master 4.4.0-104-generic #127-Ubuntu SMP Mon Dec 11 12:16:42 UTC 2017 x86_64 x86_64 x86_64 GNU/Linux
  |     |           |          --------------------------------------------   |      |      |       |
  |     |           |                              |                          |      |      |       --operating-system
  |     |           |                              |                          |      |      --hardware-platform
  |     |           |                              |                          |      --processor
  |     --nodename  --kernel-release               --kernel-version           --machine
  --kernel-name
```

---

```shell
$ # lsb stands for "Linux Standard Base"
$ lsb_release -a # -a for all
No LSB modules are available.
Distributor ID: Ubuntu
Description:    Ubuntu 16.04.3 LTS
Release:        16.04
Codename:       xenial

$ lsb_release -i
Distributor ID: Ubuntu
$ lsb_release -is # -s for short
Ubuntu

$ lsb_release -ds
Ubuntu 16.04.3 LTS

$ lsb_release -rs
16.04

$ lsb_release -cs
xenial
```

---

```shell
$ cat /proc/version
Linux version 4.4.0-104-generic (buildd@lgw01-amd64-022) (gcc version 5.4.0 20160609 (Ubuntu 5.4.0-6ubuntu1~16.04.5) ) #127-Ubuntu SMP Mon Dec 11 12:16:42 UTC 2017

$ cat /etc/issue
Ubuntu 16.04.3 LTS \n \l

$ cat /etc/*release
SUSE LINUX Enterprise Server 9 (i586)
VERSION = 9
PATCHLEVEL = 3
```

