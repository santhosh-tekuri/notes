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

### Run Command in Container

<https://kubernetes.io/docs/tasks/debug-application-cluster/get-shell-running-container/>

```shell
$ # run command in a container
$ kubectl exec my-pod -c my-container date
$ kubectl exec my-pod --container my-container date

$ # if container is omitted, first container in pod is chosen
$ kubectl exec my-pod date

$ # open shell to a container
$ # -i pass stdin to container
$ # -t stdin is a TTY
$ kubectl exec my-pod -c my-container -it /bin/bash

$ # if command has any flags, do not surround with quotes
$ # instead use '--' to separate your command
$ kubectl exec my-pod -c my-container -it -- /bin/bash -il
```

---

### Container Logs

<https://kubernetes.io/docs/concepts/cluster-administration/logging/>

* everything a containerized application writes to `stdout` and `stderr`
* if systemd is present, logs are written to journald
	* otherwise written to `/var/log/containers/<pod>_<namespace>_<container>-<containerID>.log`

```shell
$ kubectl logs my-pod/my-container
$ kubectl logs my-pod -c my-container

$ # Display only the most recent 20 lines
$ kubectl logs --tail=20 my-pod/my-container

$ # follow/stream the logs
$ kubectl logs -f my-pod/my-container

$ # if pod contains only one container
$ kubectl logs my-pod

$ # to display logs of all containers
$ kubectl logs my-pod --all-containers=true

$ # to display logs of containers with specific label
$  kubectl logs -lapp=nginx --all-containers=true
```

if a container restarts, the kubelet keeps one terminated container with its logs. to view them use `-p` flag  

logs are not rotate by default. logrotation can be performed by external process. in such case only latest log file will be available with `kubectl logs`

```bash
# rotate the log file if its size is > 100Mb OR if one day has elapsed
# keeps only 5 old (rotated) logs
function setup-logrotate() {
  mkdir -p /etc/logrotate.d/
  cat > /etc/logrotate.d/allvarlogs <<EOF
/var/log/*.log {
    rotate ${LOGROTATE_FILES_MAX_COUNT:-5}
    copytruncate
    missingok
    notifempty
    compress
    maxsize ${LOGROTATE_MAX_SIZE:-100M}
    daily
    dateext
    dateformat -%Y%m%d-%s
    create 0644 root root
}
EOF
}
```

---

### Copy files/directories

<https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#cp>

```shell
$ # copy to container
$ kubectl cp /tmp/foo.txt mypod:/tmp/bar.txt -c mycontainer

$ # copy from container
$ kubectl cp mypod:/tmp/bar.txt /tmp/foo.txt -c mycontainer

$ # tar binary should be present in container
$ kubectl cp /tmp/foodir mypod:/tmp/bardir -c mycontainer

$ # for pod in remote namespace
$ kubectl cp mynamespace/mypod:/tmp/foo tmp/bar
```

---

### Networking

* each pod is assigned a unique IP address
* every container in a Pod shares the network namespace, including the IP address and network ports
* hostname of a container is the name of the pod
* containers inside a Pod can communicate with one another using `localhost`

```yaml
spec:
  hostname: myhost        # defaults to name of pod
  hostNetwork: false      # use host's network. if true, ports must be specified. defaults to false
  containers:
    ports:                # list of ports to expose from container. cannot be updated
    - name: http          # optional. must be IANA_SVC_NAME and unique within pod
      protocol: TCP       # TCP or UDP. defaults to TCP
      containerPort: 5000 # port to expose
      hostPort: 6000      # port exposed on host 
```

---

### Port Forward

<https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#port-forward>

to forward one or more local ports to a pod temporarly for debugging:
```shell
$ # forward local ports 5000 and 6000, to pod ports 5000 and 6000 
$ kubectl port-forward pod/mypod 5000 6000

$ # forward local port 8888 to pod port 5000
$ kubectl port-forward pod/mypod 8888:5000

$ # forward random local port to pod port 5000
$ kubectl port-forward pod/mypod :5000
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
    volumeMounts:      # where to mount volumes in container
    - name: volume1
      mountPath: /var/lib/www
      readOnly: false  # defaults to false
      subPath: ""      # sub-path inside the volume. defaults to ""(volume's root)
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

secret:
  secretName: my-secret
  defaultMode : 0644 # mode bits to use on created files. defaults to 0644
  optional: false    # whether the Secret or it's keys must be defined
  items:             # if unspecified, all key-value pairs are mounted
   - key: passwd
     mode: 0700      # if unspecified, 'defaultMode' is used
     path: password  # mounted into <mountPath>/<path>

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

from secret key:

```yaml
name: SECRET_PASSWORD
valueFrom:
  secretKeyRef:
    name: mysecret
    key: password
```

can also be declared using `spec.containers[].envFrom[]`:

```yaml
envFrom:
- configMapRef:
    name: logconfig
    prefix: LOG_      # optional
    optional: true    # whether the ConfigMap must be defined
- secretRef:
    name: mysecret
    prefix: SECRET_   # optional
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

<https://kubernetes.io/docs/concepts/containers/container-lifecycle-hooks/>  
<https://kubernetes.io/docs/tasks/configure-pod-container/attach-handler-lifecycle-event/>

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

---

### spec.nodeSelector

<https://kubernetes.io/docs/concepts/configuration/assign-pod-node/#nodeselector>

a node must match this selector, for pod to be scheduled onto it

nodes have following built-in labels:
* `kubernetes.io/hostname`
* `beta.kubernetes.io/os`
* `beta.kubernetes.io/arch`
* `beta.kubernetes.io/instance-type`
* `failure-domain.beta.kubernetes.io/zone`
* `failure-domain.beta.kubernetes.io/region`

to set label on a node: `kubectl label nodes node1 disktype=ssd`

---

### spec.affinity

<https://kubernetes.io/docs/concepts/configuration/assign-pod-node/>

```yaml
affinity:
  nodeAffinity:
    requiredDuringSchedulingIgnoredDuringExecution:  # hard rule: must be met to schedule onto a node
      nodeSelectorTerms:                             # termes are ORed
      - matchExpressions:                            # expressions are ANDed
          ...
    preferredDuringSchedulingIgnoredDuringExecution: # soft rule: try to enforce but will not guarantee
    - weight: 1                                      # prefers node with greatest sum of weights. range 1 to 100
      preference:
        matchExpressions:
          ...
  podAffinity:                                       # should schedule on node, that has pod(s) matching specified rules 
    requiredDuringSchedulingIgnoredDuringExecution:
    - labelSelector:
        matchExpressions:
          ...
      topologyKey: ...
    preferredDuringSchedulingIgnoredDuringExecution:
    - weight: 1
      podAffinityTerm:
        labelSelector:
          matchExpressions:
            ...
        topologyKey: ...
  podAntiAffinity:                                   # shouldn't schedule on node, that has pod(s) matching specified rules 
    requiredDuringSchedulingIgnoredDuringExecution:
    - labelSelector:
        matchExpressions:
          ...
      topologyKey: failure-domain.beta.kubernetes.io/zone  # optional. should schedule on a node, with same label-value, that has pod(s) matching above rules
      namespaces:                                    # optional. list of namespaces which labelSelector should match against
      - ns1                                          # defaults to pod namespace
      - ns2                                          # empty means all namespaces
    preferredDuringSchedulingIgnoredDuringExecution:
    - weight: 1
      podAffinityTerm:
        labelSelector:
          matchExpressions:
            ...
        topologyKey: kubernetes.io/hostname
```

* `IgnoredDuringExecution` means, if labels on a node change at runtime such that affinity rules are
no longer met, the pod will still continue to run on the node
* operators supported in `nodeAffinity`: `In`, `NotIn`, `Exists`, `DoesNotExist`, `Gt`, `Lt`
* operators supported in `podAffinity`, `podAntiAffinity`: `In`, `NotIn`, `Exists`, `DoesNotExist`

---

### Access Kubernetes API

<https://kubernetes.io/docs/tasks/administer-cluster/access-cluster-api/>

* kubernetes has a service named `kubernetes` in `default` namespace. so you can use `kubernetes.default.svc` hostname for api-server
* by default pod is associated with a service account and you can find credentials in `/var/run/secrets/kubernetes.io/serviceaccount`
    * this folder contains `ca.crt`, `namespace` and `token` files
* you can use `kubectl` or `curl` to access the api

```shell
$ TOKEN=`cat /var/run/secrets/kubernetes.io/serviceaccount/token`
$ curl -k https://kubernetes/api --header "Authorization: Bearer $TOKEN"
{
  "kind": "APIVersions",
  "versions": [
    "v1"
  ],
  "serverAddressByClientCIDRs": [
    {
      "clientCIDR": "0.0.0.0/0",
      "serverAddress": "172.28.128.10:6443"
    }
  ]
} 
```
