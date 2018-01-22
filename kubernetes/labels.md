# Labels

Labels are key/value pairs that are attached to objects
* used to organize and to select subsets of objects
* are meaningful and relevant to users, but do not directly imply semantics to the core system
* can be attached to objects at creation time and subsequently added and modified at any time

```yaml
metadata:
  labels:
    env: prod
    tier: frontend
```

```bash
# below commands fail if label already exists, unless --override used
$ kubectl label <type> <name> env=prod tier=frontend
$ kubectl label <type> --all env=prod tier=frontend

# remove tag, if it exists
$ kubectl label <type> <name> env- tier-

# show labels column
$ kubectl get <type> --show-labels

# show given labels as columns
$ kubectl get <type> -L env -L tier
```

---

## Constraints

name/value of the label:
* at most 63 characters
* must begin and end with alphanumeric
* must contain alphanumerics, dash, underscore, dot

key can have optional prefix, separated by `/` with name:
* prefix must be a series of DNS labels separated by dots
* if the prefix is omitted, the label key is presumed to be private to the user
* `kubernetes.io/` prefix is reserved for Kubernetes core components
* at most 253 characters, including `/`

---

## Selectors

`kubectl get all -l <selector>`  
`?labelSelector=<selector>`

`env=prod` resources with key equal to `env` and value equal to `prod`  
`env==prod` same as above  
`env!=prod` resources with key equal to `env` and value distinct from `prod` and no key `env`  
`env=prod,tier=frontend` resources satisfying both requirements  
`partition` resources with key `partition`  
`!partition` resources without key `partition`  
`env in (prod, qa)`  
`env notin (prod, prod)` selects resources without key `env` also

::: tip
`env=prod` is same as `env in (prod)`  
`env!=prod` is same as `env notin (prod)`
:::

---

## Selectors in spec

```yaml
selector:
  matchLabels:
    component: redis
  matchExpressions:
    - { key: tier, operator: In, values: [cache] }
    - { key: env, operator: NotIn, values: [dev] }
    - { key: partition, operator: Exists }
    - { key: track, operator: DoesNotExist }
```

---

### References

* <https://kubernetes.io/docs/concepts/overview/working-with-objects/labels/>
