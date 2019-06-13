# Namespaces

namespaces enable virtual clusters backed by the same physical cluster
* provide scope for names
* resource names must be unique within a namespace, but not across namespaces
* most resources are in some namespaces, but not all
	* namespaces themselves are not in a namespace
	* low-level resources like nodes, persistentVolumes are not in any namespace
	* events may or may not have namespaces, depending on the object the event is about

```shell
$ # Kubernetes starts with three initial namespaces
$ kubectl get namespaces
NAME          STATUS    AGE
default       Active    1d   # default namespace for objects with no other namespace
kube-system   Active    1d   # namespace for objects created by the Kubernetes system
kube-public   Active    1d   # readable by all users, including those not authenticated
                             # mostly reserved for cluster usage

$ # to temporarly set namespace for a command
$ kubectl --namespace=kube-system get pods
$ kubectl -n=kube-system get pods

$ # to permanently set namespace for all subsequent kubectl commands
$ kubectl config set-context $(kubectl config current-context) --namespace=myns

$ # to check current namespace
$ kubectl config view --minify --output 'jsonpath={..namespace}'

$ # few commands can be run over all namespaces
$ kubectl --all-namespaces get all
```

---

### References

* <https://kubernetes.io/docs/concepts/overview/working-with-objects/namespaces/>
