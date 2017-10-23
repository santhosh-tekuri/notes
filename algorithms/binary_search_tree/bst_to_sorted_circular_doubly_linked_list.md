# BST → Sorted Circular Doubly Linked List

think of left and right pointers as synonymous to the previous and next pointers in doubly linked list

```java
Node toList(Node n) {
    if(n==null)
        return null;

    Node leftList = toList(n.left);
    Node rightList = toList(n.right);

    n.left = n.right = n; // convert node n to list
    head = append(leftList, n);
    return append(head, rightList);
}

Node append(Node a, Node b) {
    if(a==null)
        return b;
    if(b==null)
        return a;
    join(a.left, b); // aLast <-> bFirst
    join(b.left, a); // bLast <-> aFirst
    return a;
}

void join(Node a, Node b) {
    a.right = b;
    b.left = a;
}
```

Running-Time: `$O(n)$`

---

### References

* <http://cslibrary.stanford.edu/109/TreeListRecursion.html>
