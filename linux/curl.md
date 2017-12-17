# curl

`-L` `--location` follow redirects  
`-f` `--fail` exit code reflecting status code  
`-s` `--silent` silent mode, do not show progress, error messages

---

### Sending Data

* `-d <name>:<value>` `--data <name>:<value>`
* `-d @<file>` reads data from file
* `-d @-` reads data from stdin

unless explicitly set:
* method will be `POST`
* `Content-Type` will be `application/x-www-form-urlencoded`

```
$ curl -d 'name=tom' -d 'friend=jerry' http://disney.com
$ curl -d '{"user": "tom"}' -H "Content-Type: application/json" http://server/
$ curl -d @data.txt http://server/widgets
$ curl -d @- http://server/widgets
{ "name": "Widget 1" }
^D
```

---

### Save to file

`-o <file>` `--output <file>` save output to file  
`-O` `--remote-file` output file name matches remote file

---

### HTTPS

`-k` `--insecure` allow insecure connections  

---

### Authentication

`-u <user:password>` user and password for server authentication  
`-u <user>` user for server authentication, prompt for password

---

### HTTP(S) Proxy  

`--proxy http://125.119.175.48:8909` specify proxy server  
`-U <user:password>` specify proxy authentication  
