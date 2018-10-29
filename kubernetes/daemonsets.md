# DaemonSets

A DaemonSet ensures that all nodes run a copy of a pod
* as nodes are added to the cluster, pods are added to them
* as nodes are removed from the cluster, those pods are garbage collected

usecases: running logs-collection/cluster-storage/node-monitoring daemon on every node

```yaml
apiVersion: apps/v1
kind: DaemonSet
metadata:
	...
spec:
	selector:
		...
	template:  # pod template
		...
```

`spec` is same as ReplicationSet's `spec` without `replicas` field

---

### References

* <https://kubernetes.io/docs/concepts/workloads/controllers/daemonset/>