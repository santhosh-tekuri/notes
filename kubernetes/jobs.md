# Jobs

creates one or more Pods and ensures that a specified number of them successfully terminate

```yaml
apiVersion: batch/v1
kind: Job
metadata:
  name: pi
spec:
  completions: 1
  parallelism: 1
  backoffLimit: 6
  template:                  # pod template
    spec:
      restartPolicy: Never   # only Never or OnFailure allowed
```

---

## pod cleanup

- when job completes, the pods are not deleted
- deleting job will cleanup the pods it created
- `spec.ttlSecondsAfterFinished`
  - alpha feature
  - will delete the job and its pods 
  - if `0` deletes job is deleted immediately after it finishes
- CronJob can be used to cleanup

---

## non-parallel jobs

- only one pod is started, unless the pod fails
- job is complete, as soon as its pod terminates successfully
- do not specify `completions` and `parallelism`
  - when both are unset, both are defaulted to `1`

---

## spec.backoffLimit

- number of retries. defaults to `6`
- retried with exponential back-off delay (10s, 20s, 40s...) capped at six minutes

---

### Reference

<https://kubernetes.io/docs/concepts/workloads/controllers/jobs-run-to-completion/>

