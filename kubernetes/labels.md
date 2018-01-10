# Labels

Labels are key/value pairs that are attached to objects
* used to organize and to select subsets of objects
* are meaningful and relevant to users, but do not directly imply semantics to the core system
* can be attached to objects at creation time and subsequently added and modified at any time

```yaml
labels:
  env: prod
  tier: frontend
```

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

## Label Selectors

`kubectl get all -l <label-selector>`  
`?labelSelector=<label-selector>`

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
