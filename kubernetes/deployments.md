# Deployments

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  ...
spec:
  replicas: 3        # number of desired replicas. defaults to 1
  minReadySeconds: 0 # min sec pod status is ready, for replicationSet to be ready 
  selector:          # pod selector query should match replica count
    ...
  template:          # pod template
    ...
```

* pod `template` must satisfy the `selector` rules
* creates replicationSet with `<deployment-name>-<pod-template-hash>`
* `pod-template-hash` label automatically added to each pod

::: warning
you should not manage ReplicaSets owned by a Deployment
:::

---

```shell
$ kubectl get deployments
NAME               DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
nginx-deployment   3         0         0            0           1s

$ # watch status of latest rollout until it's done
$ # use `--wait=false` to simply view status without waiting
$ # use `--revision=N` to pin to a specific revision
$ kubectl rollout status deployment/nginx-deployment
Waiting for rollout to finish: 2 out of 3 new replicas have been updated...
deployment "nginx-deployment" successfully rolled out
```
DESIRED: `spec.replicas` desired number of replicas defined in deploymented  
CURRENT: `status.replicas` number of replicas currently running  
UP-TO-DATE: `status.updatedReplicas` number of replicas that are updated to achieve the desired state  
AVAILABLE: `status.availableReplicas` number of replicates availabel to users

---

### References

* <https://kubernetes.io/docs/concepts/workloads/controllers/deployment/>