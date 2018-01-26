# ReplicationSets

A ReplicaSet ensures that a specified number of pod replicas are running at any given time

```yaml
apiVersion: apps/v1
kind: ReplicationSet
metadata:
  ...
spec:
  replicas: 3  # number of desired replicas. defaults to 1
  selector:    # pod selector query should match replica count
    ...
  template:
    ...        # pod template
```

`spec.replicas`:
* number of desired replicas. defaults to 1
* number running at any time may be higher or lower, such as:
	* replicas were just increased or decreased
	* if pod is gracefully shutdown, and a replica starts early
* replicationSet can be scaled up/down, by updating this field

`spec.selector`:
* manages all the pods with labels that match the selector
* does not matter who created/deleted those pods, thus matched pods may not match the pod template
* must match `spec.template.metadata.labels`
* if multiple controllers have overlapping selectors, you will have to manage the deletion yourself
* if a pod's labels are changed, they may be removed from replicaSet and replaced by new pods
	* this might be useful for debugging and data-recovery

`spec.template`:
* `metadta.labels` must match replicationSet's `selector`
* pod's `restartPolicy` must be `Always`, which is default

---

### deletion

use `kubelet delete`

* kubectl scales replicaSet to zero and wait for each pod to delete, before deleting the replicaSet itself
	* if you are using REST API, you have to explictly: scale to zero, wait for pod deletions, then delete the replicaSet
* `--cascade=false` to delete replicaSet without affecting any of its pods
	* if you are using REST API, simply delete replicateSet

---

### References

* <https://kubernetes.io/docs/concepts/workloads/controllers/replicaset/>