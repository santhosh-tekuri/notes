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
binaryData:
  bar: jmjKxUGNs/WqDQ==
```

* stores configuration data as key-value pairs
* can be consumed by pods as [environment variables](pods.md#containerenvironmentvariables), [volumes](pods.md#volumes)
* `data` is used for UTF8 data, and `binaryData` is used for non-UTF8 data

creating explicitly with kubectl:

```shell
$ # from literals
$ kubectl create configmap logconfig --from-literal=level=warning --from-literal=target=stdout

$ # from files: key defaults to file name, value is file content
$ # if file contains non-UTF8 data, it is stored under 'binaryData' field
$ kubectl create configmap logconfig --from-file=data/level --from-file=target=data/target.txt

$ # from dir: each file in dir becomes an entry
$ kubectl create configmap logconfig --from-file=configmaps/logconfig
```

::: tip
don't use configMaps as property files.  
rather use as reference to property files (such as `\etc` folder in linux)
:::

---

### References

* <https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/>