# Local Minimum in Binary Tree

Given `n`-node complete binary tree, where `$n=2^d-1$` for some `d`, without duplicates.
A node is called local minimum, if it is less than all nodes that are joined to that node
i.e node which is less than its parent and children.

Find Local Minimum ?

---

the lowest node in the tree is local minimum, so any tree without duplicates should have at least one local minimum

start from root, see if node is leaf or smaller than its children, then it is local minimum
otherwise move to any smaller child and so on.

```java
Node localMinimum(Node n) {
    if(n==null)
        return null;

    while(true) {
        if(n.left!=null && n.data>n.left.data)
            n = n.left;
        else if(n.right!=null && n.data>n.right.data)
            n = n.right;
        else
            return n;
    }
}
```

algorithm ends when:
* we found local minimum
* we reach leaf

so algorithm always ends.

**correctness:**
* if root is local minimum, proved
* then algorithm ensures the the parent node is always less than current node

Running Time: `$O(\log_2 n)$`

### References

* Unsolved Exercise 5.6 from Algorithm Design by Jon Kleinberg, Eva Tardos
