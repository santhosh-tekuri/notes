# Successor & Predecessor

## Helpers

Following methods assume input argument `Node x` is not `null`

```java
boolean isRoot(Node x) {
    return x.parent==null;
}

boolean isLeftChild(Node x) {
    return !isRoot(x) && x==x.parent.left;
}

boolean isRightChild(Node x) {
    return !isRoot(x) && x==x.parent.right;
}

Node leftSibling(Node x) {
    if(isRightChild(x))
        return x.left;
    return null;
}

Node leftMost(Node n) {
    while(n.left!=null)
        n = n.left;
    return n;
}

Node rightMost(Node n) {
    while(n.right!=null)
        n = n.right;
    return n;
}

Node closestAncestorWithLeftChild(Node x) {
    while(isRightChild(x))
        x = x.parent;
    return x.parent;
}

Node closestAncestorWithRightChild(Node x) {
    while(isLeftChild(x))
        x = x.parent;
    return x.parent;
}
```

## In-Order 

`[...L...]P[...R...]`

```java
Node inorderSuccessor(Node x) {
    if(x.right!=null)
        return leftMost(x.right);
    return closestAncestorWithLeftChild(x);
}

Node inorderPredecessor(Node x) {
    if(x.left!=null)
        return rightMost(x.left);
    return closestAncesorWithRightChild(x);
}
```

## Pre-Order

`P[L...][R...]`

```java
Node preorderSuccessor(Node x) {
    if(x.left!=null)
        return x.left;
    if(x.right!=null)
        return x.right;
    int y = closestAncestorWithLeftChild(x);
    return y==null ? null : y.right;
}

Node preorderPredecessor(Node x) {
    if(isRoot(x))
        return null; // starts with root
    Node ls = leftSibling(x);
    if(ls!=null)
        return rightMost(ls);
    return x.parent;
}
```

## Post-Order

`[...L][...R]P`

```java
Node postorderSuccessor(Node x) {
    if(isRoot(x))
        return null; // ends with root
    if(isRightChild(x))
        return x.parent;
    if(x.right!=null)
        return leftMost(x.right);
    return x.parent;
}

Node postorderPredecessor(Node x) {
    if(x.right!=null)
        return x.right;
    if(x.left!=null)
        return x.left;
    int y = closestAncestorWithRightChild(x);
    return y==null ? null : y.left;
}
```

### References

* <http://facultyfp.salisbury.edu/despickler/personal/Resources/AdvancedDataStructures/Handouts/succ_pred_rules.pdf>
