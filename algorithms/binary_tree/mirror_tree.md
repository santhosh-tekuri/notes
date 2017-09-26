# Mirror Tree

Mirror of a Binary Tree is another Binary Tree with left and right children of all non-leaf nodes interchanged.

for example, following trees are mirror of each other:

```bash
        1                1
       / \              / \
      3   2            2   3
         / \          / \
        5   4        4   5
```

---

```java
void mirror(Node n) {
    if(n!=null) {
       mirror(n.left);
       mirror(n.right);
       swapChildren(n);
    }
}
```

```java
boolean isMirror(Node n1, Node n2) {
    if(n1!=null && n2!=null) {
        if(n1.data!=n2.data)
            return false;
        return isMirror(n1.left, n2.right)
               && isMirror(n1.right, n2.left);
    } else
        return n1==null && n2==null;
}
```

---

**Given BST, invert the signs of the elements and finally have a new BST ?**

after inverting sign of each node, its mirror tree will be the new BST

---

### References

* <http://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/>
