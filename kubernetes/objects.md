# Kubernetes Objects

Kubernetes Objects are persistent entities in the Kubernetes system:
* expressed in `.yaml` format  
* is a **record of your intent**  
* they expresss your cluster's desired state

---

every kubernetes object includes:
* apiVersion: 
    * version of the Kubernetes API you’re using to create this object
* kind:
    * kind of object you want to create
* metadata:
    * uniquely identify the object
    * must have:
        * namespace: defaults to `default`
        * name: uniquely identifies within current namespace
        * uid: to distinguish between objects with the same name that have been deleted and recreated
    * optionally have:
        * labels:
            * a map of string keys and values
            * used to organize and categorize objects
        * annotations:
            * a map of string keys and values
            * used by external tooling to store and retrieve arbitrary metadata about this object
* spec: 
    * describes your desired state of object
    * provided by you
    * is different for every Kubernetes object
* status:
    * describes actual state of object
    * supplied and updated by kubernetes system

---

## kubectl create

```shell
$ # create objects from json/yaml file
$ kubectl create -f pod.yaml

$ # create objects from stdin
$ cat <<EOF | kubectl create -f -
apiVersion: v1
kind: Namespace
metadata:
    name: development
EOF

$ # edit the data in docker-registry.yaml in JSON then create the resource using the edited data.
$ kubectl create -f docker-registry.yaml --edit -o json

$ # use --save-config option to save object configuration in kubectl.kubernetes.io/last-applied-configuration annotation
$ kubectl create --save-config -f pod.yaml
```

---

## kubectl apply

* updates/creates resources
* to use `apply`, always create resources initially created with `apply` or `create --save-config`

```shell
$ # apply configuration from file
$ kubectl apply -f pod.yaml

$ # apply resources from directory. skips objects that already exist
$ kubectl apply -f mydir/

$ # apply resources from directory recursively
$ kubectl apply -R mydir/

```

---

## kubectl get

```shell
$ # print live configuration of objects
$ kubectl get -f simple_deployment.yaml -o yaml

$ # export live configuration with cluster-specific information stripped of
$ kubectl get <kind>/<name> -o yaml --export > <kind>_<name>.yaml
```

---

## kubectl delete

```shell
$ kubectl delete -f simple_deployment.yaml
```

---

### References

* <https://kubernetes.io/docs/concepts/overview/working-with-objects/kubernetes-objects/>
* <https://kubernetes.io/docs/tasks/manage-kubernetes-objects/declarative-config/>
