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
* pod's `restartPolicy` must be `Always`, which is default
* `spec.selector` cannot be updated after creation
* creates replicationSet with `<deployment-name>-<pod-template-hash>`
* `pod-template-hash` label automatically added to each pod

::: warning
you should not manage ReplicaSets owned by a Deployment
:::

```shell
$ kubectl get deployments
NAME               DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
nginx-deployment   3         0         0            0           1s
```
DESIRED: `spec.replicas` desired number of replicas defined in deploymented  
CURRENT: `status.replicas` number of replicas currently running  
UP-TO-DATE: `status.updatedReplicas` number of replicas that are updated to achieve the desired state  
AVAILABLE: `status.availableReplicas` number of replicates availabel to users

---

### Updating

* updating `spec.template` triggers a rollout
* other updates such as scaling, does not trigger a rollout
* during update, deployment ensures that:
	* at least 25% less than desired number of pods are up (25% max unavailable)
	* at most 25% more than the desired number of Pods are up (25% max surge)
* it means, durng update:
	* does not kil old Pods until a sufficient number of new pods have come up
	* does not create new pods until a sufficient number of old pods have been killed
* sets annotation `deployment.kubernetes.io/revision`
* updating while an existing rollout is in progress: 
	* will create a new replicaSet as per the update and start scaling that up
	* will roll over (scaling down) the replicaSet that it was scaling up previously

```shell
$ # use EDITOR to edit deployment
$ # kubectl edit deployment/nginx-deployment
deployment "nginx-deployment" edited

$ kubectl set image deployment/nginx-deployment busybox=busybox ngnix=ngnix:1.9.1
deployment "nginx-deployment" image updated
```

```shell
$ # watch status of latest rollout until it's done
$ # use `--wait=false` to simply view status without waiting
$ # use `--revision=N` to pin to a specific revision
$ # if rollout succeedes, returns zero exit code
$ kubectl rollout status deployment/nginx-deployment
Waiting for rollout to finish: 2 out of 3 new replicas have been updated...
deployment "nginx-deployment" successfully rolled out

$ # command used to create deployment is by default recorded using --record
$ kubectl rollout history deployment/nginx-deployment
deployments "nginx-deployment"
REVISION    CHANGE-CAUSE
1           kubectl create -f docs/user-guide/nginx-deployment.yaml --record
2           kubectl set image deployment/nginx-deployment nginx=nginx:1.9.1
3           kubectl set image deployment/nginx-deployment nginx=nginx:1.91

$ # to see details for specific revision
$ kubectl rollout history deployment/nginx-deployment --revision=2

$ # to undo the current rollout and rollback to the previous revision
$ kubectl rollout undo deployment/nginx-deployment
deployment "nginx-deployment" rolled back

$ # to rollback to a specific revision
$ kubectl rollout undo deployment/nginx-deployment --to-revision=2
deployment "nginx-deployment" rolled back

$ # pause & resume allows to apply multiple updates without triggering unnecessary rollouts
$ # note: you cannot rollback a paused Deployment until you resume it
$ kubectl rollout pause deployment/nginx-deployment
deployment "nginx-deployment" paused
$ kubectl set image deploy/nginx-deployment nginx=nginx:1.9.1
deployment "nginx-deployment" image updated
$ kubectl set resources deployment nginx-deployment -c=nginx --limits=cpu=200m,memory=512Mi
deployment "nginx-deployment" resource requirements updated
$ kubectl rollout resume deploy/nginx-deployment
deployment "nginx-deployment" resumed
```

`spec.progressDeadlineSeconds`:
* max sec for deployment to make progress before it is considered to be failed
* defaults to 600
* failure is added to `spec.status.conditions[]` with `{type: Progressing, Status: false, Reason:ProgressDeadlineExceeded}`
* failed deployments can still be processed, for example scaling up/down, rollback, pause to apply more tweaks
* paused rollout does not trigger deadline exceeded

`spec.revisionHistoryLimit`:
* number of old replicaSets to retain to allow rollback
* defaults to 10
* setting to 0, results in cleaning up all history, thus no way to rollback

---

### References

* <https://kubernetes.io/docs/concepts/workloads/controllers/deployment/>