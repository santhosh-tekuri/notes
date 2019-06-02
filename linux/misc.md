# miscellaneous

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
