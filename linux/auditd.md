# auditd

```shell
$ # install auditd
$ yum install audit audit-libs
$ apt-get install auditd audispd-plugins

$ # edit configuration
$ vi /etc/audit/auditd.conf
# number of audit log files to keep
num_logs = 10
# maximum log file size in MB
max_log_file = 30
# what action to take once the size is reached
max_log_file_action = ROTATE

$ # to log all commands
$ cat <EOF >/etc/audit/rules.d/audit.rules
-a exit,always -F arch=b64 -S execve
-a exit,always -F arch=b32 -S execve
EOF
$ service auditd restart

$ # list audit rules
$ auditctl -l

$ # reload audit rules
$ auditctl -R /etc/audit/rules.d/audit.rules

$ # view audit logs
$ cat /var/log/audit/audit.log
```

---

### References

* <https://www.digitalocean.com/community/tutorials/how-to-use-the-linux-auditing-system-on-centos-7>
* <https://www.tecmint.com/linux-system-auditing-with-auditd-tool-on-centos-rhel/>
* <https://www.cyberciti.biz/tips/linux-audit-files-to-see-who-made-changes-to-a-file.html>
* <http://whmcr.com/2011/10/14/auditd-logging-all-commands/>
* <https://linux-audit.com/configuring-and-auditing-linux-systems-with-audit-daemon/>
