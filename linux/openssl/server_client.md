# Server & Client

## Create Self Signed Certificate

```shell
$ openssl req -x509 -newkey rsa:2048 -keyout key.pem -nodes -out cert.pem -days 900000
Generating a 2048 bit RSA private key
................+++
.............................+++
writing new private key to 'key.pem'
-----
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) []:IN
State or Province Name (full name) []:KA
Locality Name (eg, city) []:Bangalore
Organization Name (eg, company) []:ABC
Organizational Unit Name (eg, section) []:MGMT
Common Name (eg, fully qualified host name) []:www.abc.com
Email Address []:santhosh@abc.com

$ openssl req -x509 -new -keyout key.pem -nodes -out cert.pem -subj '/C=IN/ST=Karnataka/O=MGMT/CN=www.abc.com' -days 900000
Generating a 2048 bit RSA private key
.............+++
.................................................+++
writing new private key to 'server.key'
-----
```

- `-x509` output self signed certificate instead of certificate request
- `-new` generate new
- `-newkey rsa:2048` generate new RSA key of `2048` size
- `-nodes` do not encrypt the output key
- `-keyout` output private key file
- `-out` output file name
- `-days` no of days cert valid for. defaults to `30`

---

## Server

```shell
$ openssl s_server -key key.pem -cert cert.pem -accept 44330 -WWW
Using auto DH parameters
Using default temp ECDH parameters
ACCEPT
```

`-WWW` serve files from current directory

---

## Client

```shell
$ openssl s_client -connect localhost:44330
CONNECTED(00000005)
depth=0 C = IN, ST = Karnataka, O = MGMT, CN = www.abc.com
verify error:num=18:self signed certificate
verify return:1
...
```

---

### References

* https://blog.jorisvisscher.com/2015/07/22/create-a-simple-https-server-with-openssl-s_server/
