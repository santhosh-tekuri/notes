# trap

`trap COMMAND SIGNAL...`

traps the specified signals and executes the command

`SIGNALS` signal names with or without `SIG` prefix or signal numbers
- `EXIT`: when shel exits
- `DEBUG`: after every simple command
- `ERR`: after every command with non-zero status
- `INT`: trap ctrl+c

when bash receives a signal, where a command is running, trap is executed only after the command completed. 
but `wait` built-in, returs immediately with exitcode >128, if trap is set on that signal.

to clean temp file on exit:
```
trap "rm -f tempfile" EXIT

# can call function on trap
cleanup(){
    rm -f tempfile
}
trap cleanup EXIT
```

to prevent Ctrl-C to exit in critical section of script:
```
# Run something important, no Ctrl-C allowed.
trap "" SIGINT
important_command
# Less important stuff from here on out, Ctrl-C allowed.
trap - SIGINT
not_so_important_command
```

to print script success/failure always:
```
set -e
show_status(){
    if [ $? -eq 0 ]; then
        echo 'script succeeded'
    else
        echo "script failed at line $BASH_LINENO"
    fi
}
trap show_status EXIT
```

---

trap replaces any previous ones on same signal:
```
trap foo EXIT
trap bar EXIT
# here bar is called on exit, foo is never called
```

---

## References

- <https://tldp.org/LDP/Bash-Beginners-Guide/html/sect_12_02.html>
- <https://www.linuxjournal.com/content/bash-trap-command>
