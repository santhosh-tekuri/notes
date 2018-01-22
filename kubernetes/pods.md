# Pods

```yaml
apiVersion: v1
kind: Pod
metadata:
  - name: mypod
spec:
  containers:
  - name: ubuntu
    image: ubuntu:trusty
    command: ["echo"]
    args: ["hello world"]
```

* smallest deployable object in the Kubernetes object model
* basic building block of Kubernetes
* represends a single instance of an application in Kubernetes, 
  which might consist of either a single container or a small number of 
  containers that are tightly coupled and that share resources
* containers in a Pod are automatically co-located and co-scheduled 
  on the same physical or virtual machine in the cluster

---

### Networking

* each pod is assigned a unique IP address
* every container in a Pod shares the network namespace, including the IP address and network ports
* containers inside a Pod can communicate with one another using `localhost`

---

### Storage

* Pod can specify a set of shared storage volumes
* all containers in the Pod can access the shared volumes, allowing those containers to share data 
* volumes also allow persistent data in a Pod to survive in case one of the containers within needs to be restarted

---

### spec.restartPolicy

* possible values `Always`, `OnFailure` and `Never`
* default value is `Always`
* only refers to restarts of containers by kubelet on the same node
    * note that pods are never rebound to another node
* restarted with an exponential back-off delay
    * 10s, 20s, 40s ... capped at five minutes
    * reset after ten minutes of successful execution

---

### Container Probes

Probe is a diagnostic performed periodically by the kubelet on a Container

3 types of probe handlers are supported:

```yaml
exec:                       # Success: exit-code==0
  command:                  # simply exec'd, it is not run inside a shell
  - cat
  - /tmp/healthy
httpGet:                    # Success: code>=200 && code<400
  scheme: HTTP              # defaults to HTTP
  host: myhost              # defaults to pod IP, rather use 'Host' header
  port: 8080
  path: /healthz
  httpHeaders:
  - name: X-Custom-Header
    value: Awesome
tcpSocket:                  # Success: connection succeeded
  host: myhost              # defaults to pod IP
  port: 8080
```

two kinds of probes are suppored: `livenessProbe` and `readinessProbe`:

livenessProbe:
* if not specified, treated as `Success`
* on `Failure`, subjected to `restartPolicy`

readinessProbe:
* if not specified, treated as `Success`, otherwise treated as`Failure` before `initialDelaySeconds`
* on `Failure`, removes pod’s IP address from the endpoints of all services that match the pod

```yaml
containers:
- name: goproxy
  image: goproxy
  livenessProbe:
    initialDelaySeconds: 5 # sec after container has started, probes are initiated
    periodSeconds: 10      # how often to perform probe. defaults to 10. min is 1
    timeoutSeconds: 1      # sec after which probe times out. defaults to 1. min is 1
    successThreshold: 1    # min consecutive successes for probe to be considered success after having failed
                           # defaults to 1. min is 1. must be 1 for livenessProbe
    failureThreshold: 1    # min consecutive failures for probe to be considered failed after having succeeded
                           # defaults to 3. min is 1
    httpGet:
      ...
```

---

### Memory Resources to Containers

```yaml
containers:
- name: memory-demo-ctr
  image: vish/stress
  resources:
    limits:
      memory: "200Mi"
    requests:
      memory: "100Mi"
```

* the container has a memory request of `100MiB` and a memory limit of `200MiB`
* container can exceed its memory request, if the node has memory available
* container is not allowed to use more than its memory limit, otherwise it is terminated
* requests and limits of pod are sum of all containers
* pod is scheduled based on requests
    * if no node has requested capacity then pod remains in `PENDING` state indefinitely
* if no memory limit specified:
    * they inherit default memory limit, if specified in namespace
    * it can use all memory availabel on the node

---

### CPU Resources to Containers

```yaml
containers:
- name: memory-demo-ctr
  image: vish/stress
  resources:
    limits:
      cpu: "1"
    requests:
      cpu: "0.5"
```

* the container has a CPU request of `0.5` cpu and a CPU limit of `1` cpu
