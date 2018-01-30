# Secrets

* an object that contains a small amount of sensitive data, such as:
	* passwords
	* oauth tokens
	* ssh keys
* these sensitive information can be:
	* injected into pod as environment variable
	* mounted into pod as volume
	* used by kubelet when pulling images for pod
* individual secrets are limited to 1MB in size

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: mysecret
type: Opaque
data:                          # values are base64 encoded without newlines
  username: YWRtaW4=
  password: MWYyZDFlMmU2N2Rm
```

```shell
$ # to base64 encode
$ echo -n "admin" | base64 | tr -d '\n'
YWRtaW4=

$ # to base64 decode
$ echo -n "YWRtaW4=" | base64 --decode
```

---

### stringData

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: mysecret
type: Opaque
stringData:                          # values are liteal strings
  username: admin
  password: 1f2d1e2e67df
```

* usuful when creating using yaml file
* only non-binary secret data can be specified
* merged into `data`, overwriting any existing values

---

### from files, directories and literal values

```shell
$ # from literal values
$ kubectl create secret generic my-secret --from-literal=key1=supersecret --from-literal=key2=topsecret

$ # from files: key defaults to file name, value is file content
$ kubectl create secret generic my-secret --from-file=path/to/bar --from-file=ssh-privatekey=~/.ssh/id_rsa

$ # from dir: each file in dir becomes an entry
$ kubectl create secret generic mysecrets --from-file=secrets/creadentials
```

* `type` is set to `Opaque`

---

### from public/private key pair

```shell
$ openssl req -newkey rsa:2048 -nodes -keyout key.pem -x509 -days 365 -out cert.pem
$ kubectl create secret tls tls-secret --cert=cert.pem --key=key.pem
```

* uses `tls.crt` and `tls.key` as keys
* `type` is set to `kubernetes.io/tls`

---

### for use with docker registries

```shell
$ # email is optional
$ kubectl create secret docker-registry my-secret \
--docker-server=DOCKER_REGISTRY_SERVER \
--docker-username=DOCKER_USER \
--docker-password=DOCKER_PASSWORD \
--docker-email=DOCKER_EMAIL
```

alternatively, you can use `~/.dockercfg` created by docker:
```shell
$ # produces ~/.dockercfg file
$ docker login DOCKER_REGISTRY_SERVER \
--username=DOCKER USER \
--password=DOCKER PASSWORD \
--email=DOCKER _EMAIL

$ kubectl create secret docket-registry my-secret -f ~/.dockercfg
```

* used for authentication to pull images from docker registry
* uses `.dockerconfigjson` as key
* `type` is set to `kubernetes.io/dockerconfigjson`

---

### Risks

* secrets are stored as plain text in etcd
* even if apiserver policy does not allow a user to read secret, he can run
  a pod that uses the secret and see its value
* by default, etcd replicas communicate without TLS 

---

### References

* <https://kubernetes.io/docs/concepts/configuration/secret/>

