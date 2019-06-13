# KubeConfig

`kubectl` looks for config in order:
* `--kubeconfig` flag
* `$KUBECONFIG` environment variable
* `$HOME/.kube/config` file

```shell
$ # use multiple kubeconfigs at once
$ export KUBECONFIG=file1:file2
$ kubectl get pods --context=cluster-1
$ kubectl get pods --context=cluster-2

$ # merging kubeconfig files
$ # --flatten keeps credentials unredacted
$ KUBECONFIG=file1:file2:file3 kubectl config view --merge --flatten > out.txt

$ # extract current context
# kubectl config view --minify --flatten > out.txt

$ # extract given context
# kubectl config view --minify --flatten --context=context-1 > out.txt

$ # without kubeconfig
$ # set $KUBECONFIG to empty to prevent accidentally picking up some settings from the ~/.kube/config file
$ KUBECONFIG= kubectl get nodes \
    --server https://localhost:6443 \
    --user docker-for-desktop \
    --client-certificate my.cert \
    --client-key my.key \
    --insecure-skip-tls-verify
```


### References

* <https://ahmet.im/blog/mastering-kubeconfig/>
