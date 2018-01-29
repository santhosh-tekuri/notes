# Services

an abstraction which defines a logical set of Pods and a policy by which to access them
* kubernetes updates `endpoints` whenever set of pods in service changes

```yaml
apiVersion: v1
kind: Service
metadata:
  name: my-service
spec:
  ...
```

---

### spec.type

there are four kinds of services supported:

`ClusterIP`:
* this is the default
* exposes service on a cluster-internal IP
* service only reachable from within the cluster

`NodePort`:
* exposes service on each node'IP at a static port
* service reachable from outside the cluster

`LoadBalancer`:
* exposes service externally using a cloud provider's load balancer

`ExternalName`:
* maps service to the externalName by CNAME alias dns record

---

### spec.selector

service traffic is routed to pods matching this selector

```yaml
spec:
  selector:
    app: myapp
```

* not applicable for type `ExertnalName`
* creates and manages endpoints object with same name

if selector is not specified, you should manage endpoints yourself:

```yaml
apiVersion: v1
kind: Endpoints
metadata:
  name: my-service       # must match service name
subsets:                 # traffic routed to 1.2.3.4:9376
- addresses:
  - ip : 1.2.3.4
  ports:
  - port: 9376
```

---

### spec.clusterIP

IP address of the service:
* usually assigned randomly by master
* if specified, must not be in use by others
* cannot be updated after creation
* not applicable for type `ExternalName`
* value `None` is used for headless service when proxying is not required

---

### spec.ports

```yaml
spec:
  ports:
  - name: http        # must be specified if multiple ports. must be unique. maps to name of endpointPorts
    protocol: TCP     # possible values TCP, UDP. defaults to TCP
    port: 80          # port that will be exposed by this service
    targetPort: 9376  # port to access on the pods targeted by the service
                      # if string, will be looked up as a named port in the target pod's container ports
                      # if not specifed, defaults to value of port specified
                      # ignored if clusterIP is None
    nodePort: 8080    # port on each node on which this service is exposed
                      # applicable when type is NodePort or LoadBalancer
                      # if unspecified, assigned by the system
```

---

### spec.externalName

external applications can also be abstracted using service:
* examples include: 
	* external database cluster
	* service in another namespace
	* service in another kubernetes cluster
* such service has no `selector`
* no endpoints will be created
* kubernetes return as a CNAME record for this service points to externalName

```yaml
kind: Service
apiVersion: v1
metadata:
  name: my-service
  namespace: prod
spec:
  type: ExternalName
  externalName: my.database.example.com
```

lookup of `my-service.prod.svc.CLUSTER`, returns a CNAME record with the value `my.database.example.com`

---

### References

* <https://kubernetes.io/docs/concepts/services-networking/service/>