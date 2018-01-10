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

### References

* <https://kubernetes.io/docs/concepts/overview/working-with-objects/kubernetes-objects/>
