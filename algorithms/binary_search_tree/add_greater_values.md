# Add all greater values to every node in a given BST

For each node in BST, add all greater values in given BST

```bash
     50                     260
    /  \                   /   \
   /    \                 /     \
  30     70     ===>   330       150
 /  \   /  \          /   \     /   \
20  40 60  80       350   300 210    80
```

---

do reverse inorder and keep summing the values as you visited, and update node's value

```java
int update(Node n) {
    update(n, 0);
}

int update(Node n, int sum) {
    if(n==null)
        return sum;
    sum = update(n.right, sum);
    sum += n.data;
    n.data = sum;
    return update(n.left, sum);
}
```

Running-Time: `$O(n)$`

### References

* <http://www.geeksforgeeks.org/add-greater-values-every-node-given-bst/>
