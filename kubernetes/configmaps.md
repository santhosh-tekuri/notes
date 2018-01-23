# ConfigMaps

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: logconfig
data:
  level: warning
  target: elk
  connection.properties: |
    elk_host: 145.232.2.1
    ell_port: 1234
```

* stores configuration data as key-value pairs
* can be consumed by pods as [environment variables](pods.md#containerenvironmentvariables), [volumes](pods.md#volumes)

creating explicitly with kubectl:

```shell
$ # from literals
$ kubectl create configmap logconfig --from-literal=level=warning --from-literal=target=stdout

$ # from files: key defaults to file name, value is file content
$ kubectl create configmap logconfig --from-file=data/level --from-file=target=data/target.txt

$ # from dir: each file in dir becomes an entry
$ kubectl create configmap logconfig --from-file=configmaps/logconfig
```

::: tip
don't use configMaps as property files.  
rather use as reference to property files (such as `\etc` folder in linux)
:::