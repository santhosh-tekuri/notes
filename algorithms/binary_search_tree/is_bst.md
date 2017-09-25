# Is BST ?

## using range check

Traverse down the tree, keeping track of the narrowing min and max allowed values as it goes.

```java
boolean isBST(Node root) {
    return isBST(root, Integer.MIN, Integer.MAX);
}

boolean isBST(Node node, int min, int max) {
    if(node==null)
        return true;
    if(node.data<min || node.data>max)
        return false;
    return isBST(node.left, min, node.data)
           && isBST(node.right, node.data, max);
}
```

Time complexity: `$O(n)$`

---

## using inorder walk

While doing in-order traversal check whether current node is greater than equal to its previous node.

```java
boolean isBST(Node root){
    return root==null && inorder(root, null)!=null;
}

Node inorder(Node node, Node prev){
    if(node==null)
        return prev;

    prev = inorder(node.left, prev);
    if(prev!=null && prev.data>node.data)
        return null;

    return inorder(node.right, node);
}
```

Time complexity: `$O(n)$`

---

### References

* <http://n00tc0d3r.blogspot.in/2013/04/validate-binary-search-tree.html>
