# Lowest Common Ancestor

Let `T` be a rooted tree. LCA of two nodes `p`, `q` is defined as lowest node in `T` that has both `p` and `q` as descendants.
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

## with parent pointers

```java
Node LCA(Node p, Node q) {
    int pHeight = height(p);
    int qHeight = height(q);

    if(pHeight<qHeight)
        swap(p, q);
    // assert p.height>=q.height

    // Go up the tree until the p and q are at the same level
    for(int diff=pHeight-qHeight; diff>0; diff--)
        p = p.parent;

    while(p!=null && q!=null){
        if(p==q)
            return p;
         p = p.parent;
         q = q.parent;
     }
     return null;
}
```

---

## without parent pointers

if `n` is LCA of `p` and `q`, one of following conditions should be true:
* `n==p`
* `n==q`
* `n.left` contains `p` && `n.right` contains `q`
* `n.left` contains `q` && `n.right` contains `p`

keep checking above conditions from leaf to root, until one of them is satisfied

```java
Node LCA(Node n, Node p, Node q) {
    if(n==null)
        return null;

    if(n==q || n==q)
        return n;

    Node l = LCA(n.left, p, q);
    Node r = LCA(n.right, q, q);
    if(l!=null && r!=nul)
        return n;
    return l!=null? l : r; // return non-null
}
```

---

### References

* <http://leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-ii.html>
* <http://leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-i.html>
