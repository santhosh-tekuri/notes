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
* pods are mortal. when they die, they are not resurrected

---

### Networking

* each pod is assigned a unique IP address
* every container in a Pod shares the network namespace, including the IP address and network ports
* hostname of a container is the name of the pod
* containers inside a Pod can communicate with one another using `localhost`

```yaml
spec:
  hostname: myhost        # defaults to name of pod
  containers:
    ports:                # list of ports to expose from container. cannot be updated
    - name: http          # optional. must be IANA_SVC_NAME and unique within pod
      protocol: TCP       # TCP or UDP. defaults to TCP
      containerPort: 5000 # port to expose
```

<https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#port-forward>

to forward one or more local ports to a pod temporarly for debugging:
```shell
$ # forward local ports 5000 and 6000, to pod ports 5000 and 6000 
$ kubectl port-forward mypod 5000 6000

$ # forward local port 8888 to pod port 5000
$ kubectl port-forward mypod 8888:5000

$ # forward random local port to pod port 5000
$ kubectl port-forward mypod :5000
```

::: note
port forward today only works for TCP protocol
:::

---

### Volumes

<https://kubernetes.io/docs/concepts/storage/volumes/>

at its core, a volume is just a directory, possibly with some data in it:
* used to share files between containers
* volume lifetime is same as its pod

```yaml
spec:
  volumes:           # declare volumes
    - name: volume1
      <volume-type>:
         ...
  containers:
  - name: demo
    image: demoimg
    volumeMounts:   # where to mount volumes in container
    - name: volume1
      mountPath: /var/lib/www
      subPath: ""   # sub-path inside the volume. defaults to ""(volume's root)
```

volume type determines:
* how that directory comes to be
* the medium that backs it
* the contents of volume

```yaml
emptyDir:
  sizeLimit: null # defaults to null i.e. no limit enforced
  medium: ""      # defaults to "" i.e node's default medium.
                  # "Memory" to mount tmpfs (RAM-backed filesystem), will count against container's memory limit

configMap:
  name: logconfig
  defaultMode : 0644 # mode bits to use on created files. defaults to 0644
  optional: false    # whether the ConfigMap or it's keys must be defined
  items:             # if unspecified, all key-value pairs are mounted
   - key: level
     mode: 0700      # if unspecified, 'defaultMode' is used
     path: log_level # mounted into <mountPath>/<path>

hostPath:
  path: /data # path on the host. if symlink, it is resolved
  type: ""    # possible values are:
              #  "":           (default) no checks will be performed before mounting the hostPath volume
              #  File:         a file must exist  at given path
              #  FileOrCreate: if needed creates empty file with mode 0644, group and ownership same as kubelet
              #  Directory:    a directory must exist at given path
              #  DirectoryOrCreate: if needed creates empty dir with mode 0755, group and owner same as kubelet
              #  Socket:            a unix socket must exist at given path
              #  CharDevice:        a character device must exist at given path
              #  BlockDevice:       a block device must exist at given path
```

---

### spec.restartPolicy

<https://kubernetes.io/docs/concepts/workloads/pods/pod-lifecycle/#restart-policy>

* possible values `Always`, `OnFailure` and `Never`
* default value is `Always`
* only refers to restarts of containers by kubelet on the same node
    * note that pods are never rebound to another node
* restarted with an exponential back-off delay
    * 10s, 20s, 40s ... capped at five minutes
    * reset after ten minutes of successful execution

---

### Container Environment Variables

<https://kubernetes.io/docs/tasks/inject-data-application/define-environment-variable-container/>  
<https://kubernetes.io/docs/tasks/inject-data-application/environment-variable-expose-pod-information/>

declared using `spec.containers[].env[]`:

from static values:

```yaml
name: USER_NAME                # from static values
value: scott
```

from pod fields:

```yaml
name: POD_NAME                 # from pod fields
valueFrom:
  fieldRef:
    fieldPath: metadata.name

# can select fields from:
# - metadata.name, metadata.namespace, metadata.labels, metadata.annotations
# - spec.nodeName, spec.serviceAccountName
# - status.hostIP, status.podIP
```

from container resources:

```yaml
name: MEM_LIMIT
valueFrom:
  resourceFieldRef:
    containerName: demo   # optional
    resource: limits.cpu
```

from configMap key:

```yaml
name: LOG_LEVEL
valueFrom:
  configMapKeyRef:
    name: logconfig
    key: level
```

can also be declared using `spec.containers[].envFrom[]`:

from configMap keys:

```yaml
configMapRef:
  name: logconfig
  prefix: LOG_      # optional
  optional: true    # whether the ConfigMap must be defined

# keys that are considered invalid will be skipped
# invalid names gets recorded in event log as warning 'InvalidEnvironmentVariableNames'
```

---

### Container Probes

<https://kubernetes.io/docs/concepts/workloads/pods/pod-lifecycle/#container-probes>  
<https://kubernetes.io/docs/tasks/configure-pod-container/configure-liveness-readiness-probes/>

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

two kinds of probes are suppored:

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

### spec.initContainers[]

<https://kubernetes.io/docs/concepts/workloads/pods/init-containers/>

exactly like `spec.containers[]`, except:
* they always run to completion
* if failed to start, or exits with failure, they are retried based on `spec.restartPolicy`
    * if `spec.restartPolicy: Always`, the Init Containers use `OnFailure`
* don't support `readinessProbe`
* are run sequentially, each must complete successfully, before the next one is started
    * if pod is restarted, all Init Containers must execute again
    * changes to init container image, restarts the pod
* see status in `status.initContainerStatuses[]`
* `containers` are run only after all `initContainers` have run to completion

when to use:
* to do some setup, before app containers is run
    * `git cline https://github.com/santhosh-tekuri/www-html`
    * place values like POD_IP into a configuration file and run a template tool like jinja to dynamically generate main app configuration
* block/delay startup of app containers, until some preconditions are met:
    * wait for a service to be created:  
      `for i in {1..100}; do sleep 1; if dig myservice; then exit 0; fi; done; exit 1`
    * register this Pod with a remote server from the downward API  
      `curl -X POST http://$MANAGEMENT_SERVICE_HOST:$MANAGEMENT_SERVICE_PORT/register -d 'instance=$(<POD_NAME>)&ip=$(<POD_IP>)'`
    * wait for some time before starting the app container:  
      `sleep 60`

Remember:
* should be idempotent, because they can be restarted, retried, or re-executed
* use `spec.activeDeadlineSeconds` to prevent them from failing forever
* app and init containers cannot share same name
* if `spec.restartPolicy: Always` and all containers terminate, pod is restarted causing init containers re-execution

---

### Container Lifecycle Hooks

`spec.containers[].lifecycle.postStart`:
* executes immediately after a container is created
* no guarantee that the hook will execute before the container ENTRYPOINT
* hook runs asynchronously relative to the container’s code
* hook is executed at least once, means may be called multiple times:
    * hook is not called again, if it fails
    * hook might be called again, in case of kubelet restart in middle of hook
* container status is not set to `RUNNING` until the hook handler completes

`spec.containers[].lifecycle.preStop`:
* called immediately before a container is terminated
* useful to save state prior to stopping a Container
* kubelet waits for handler completion, until `spec.terminationGracePeriodSeconds` expires (defaults to `30`)

supported hook handlers are `exec`, `httpGet` (see [container probes](#containerprobes))

Remember:
* make hook handlers as lightweight as possible
* hook failures are broadcasted as events `FailedPostStartHook`, `FailedPreStopHook`
* resources consumed by the command are counted against the container

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
