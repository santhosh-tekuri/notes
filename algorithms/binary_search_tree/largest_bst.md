# Largest BST in Binary Tree

```bash
    5             50
   / \           /  \
 [2]  4        30    [60]
 / \          /  \   / \
1   3        5   20 45  70
                       /  \
                      65  80
```

---

if left are right subtrees of a node `n` are BST, then node `n` is BST, if following 2 conditions hold:
* all nodes in left subtree are `<=n.data`
    * i.e `largest(n.left)<=n.data`
* all nodes in right subtree are `>=n.data`
    * i.e `smallest(n.right)>=n.data`

to handle empty subtrees smoothly, we say in empty tree
* largest(null) = -∞
* smallest(null) = +∞

---

```java
class BSTInfo {
    int size;
    int min, max;
}

class MaxBST {
    Node n;
    int size;
}

Node largestBST(Node n){
    MaxBST maxBST = new MaxBST();
    largestBST(n, maxBST);
    return maxBST.node;
}

BSTInfo largestBST(Node n, MaxBST maxBST){
    if(n==null)
        return New BSTInfo(0, Integer.MIN, Integer.MAX);

    BSTInfo lInfo = largestBST(n.left, maxBST);
    if(lInfo==null)
        return null;

    BSTInfo rInfo = largestBST(n.right, maxBST);
    if(rInfo==null)
        return null;

	if(n.data>=lInfo.max && n.data<=rInfo.data) {
        BSTInfo info = new BSTInfo(
            lInfo.size + rInfo.size + 1,
            lInfo.min,
            rInfo.max
        );
        if(info.size>maxBST.size) {
            maxBST.node = n;
            maxBST.size = info.size
        }
        return info;
    };

    return null;
}
```

---

### References

* <http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/>
