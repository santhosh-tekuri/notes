# Remove nodes outside given range

let given range [min, max], delete all nodes in BST that lies outside this range

```bash
     6
    / \                                      6
   /   \                                    / \
  /     \      removeOutside [-10, 3] ⇛    /   \
-13      14                              -8    13
  \     /  \                                  /
  -8  13   15                                7
      /
     7
```

---

a node `n` has to be removed only in following cases:
* `n.data<min`
    * then all nodes in its left subtree also less than `min`
    * so both `n` and its left subtree should be deleted
- `n.data>max`
    * then all nodes in its right subtree also greater than `max`
    * so both `n` and its right subtree should be deleted

```java
Node removeOutside(Node n, int min, int max) {
    if(n==null)
        return null;

    if(n.data<min)
        return removeOutside(n.right, min, max);

    if(n.data>max)
        return removeOutside(n.left, min, max);

    n.left = removeOutside(n.left, min, max);
    n.right = removeOutside(n.right, min, max);
    return n;
}
```

### References

* <http://www.geeksforgeeks.org/remove-bst-keys-outside-the-given-range/>
