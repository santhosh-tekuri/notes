# timeout

run a command with time limit

```shell
$ # allow ping to run for 15 sec
$ timeout 15 ping google.com

$ # time limit of 2 min
$ # you can use 's', 'm', 'h' or 'd'
$ timeout 2m ping google.com
```

---

## signals

timeout sends `SIGTERM` signal to the program to politely ask them to terminate.
some programs may ignore this signal

```shell
$ # send SIGKILL signal after timeout
$ timeout -s SIGKILL 10 ping google.com

$ # send SIGTERM after 30s, SIGKILL after 40s
$ # use -k or --kill-after
$ timeout -k 40 30 ping google.com
```

---

## exit code

`0` if terminated before timeout  
`124` if terminated by `SIGTERM`  
`137` if terminated by `SIGKILL`

```shell
$ # to get program's actual exit code
$ timeout --preserve-status 1m ping google.com
```

---

### References

* <https://www.howtogeek.com/423286/how-to-use-the-timeout-command-on-linux/>
