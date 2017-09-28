# Least Common Ancestor in BST

Let `T` be a rooted tree. LCA of two nodes `n1`, `n2` is defined as lowest node in `T` that has both `n1` and `n2` as descendants.
Note that we allow a node to be descendant of it self.

```bash
     20           LCA(10, 14) = 12
    /  \          LCA(14, 8)  = 8
   8   22         LCA(10, 22) = 20
  / \
 4  12
   /  \
  10  14
```

---

* recursively traverse BST
    * if node's value is greater than `n1` and `n2`, LCA lies in left-subtree of node
    * if node's value is less than `n1` and `n2`, LCA lies in right-subtree of node
    * otherwise current node is LCA


```java
int lca(Node n, int n1, int n2) {
    while(n!=null) {
        if(n.data>n1 && n.data>n2)
            n = n.left;
        else if(n.data<n1 && n.data<n2)
            n = n.right;
        else
            return n.data;
    }
    return nil;
}
```

**NOTE:** we are assuming that `n1` and `n2` indeed exist in BST

---

### References

* <http://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/>
