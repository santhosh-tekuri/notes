# Rotations

Rotations are used to alter the structure, without affecting its inorder

```bash
  P                                  P
  |                                  |
  x         leftRotate(T, x) ⇛       y
 / \                                / \
A   y     ⇚ rightRotate(T, y)      x   C
   / \                            / \
  B   C                          A   B
```

**Note:** locations of `A` and `C` are never changed

**Left-Rotation:** 
* assumes `x.right` i.e, `y` is not `null`
* increases height of `x` and `A` by `1`
* decreases height of `y` and `C` by `1`

**Right-Rotation:**
* assumes `y.left` i.e, `x` is not `null`
* decreases height of `x` and `A` by `1`
* increases height of `y` and `C` by `1`

rotations are used to balance height during insert and delete

```java
// left-rotate subtree rooted at x
void leftRotate(Tree T, Node x){
    assert x.right!=null;

    Node y = x.right;
    Node B = y.left;
    Node P = x.parent;

    // create edge x-B
    x.right = B;
    B.parent = x;

    // create edge P-y
    y.parent = P;
    if(P==null)
        T.root = y;
    else if(x==P.left)
        P.left = y;
    else
        P.right = y;

    // create edge y-x
    y.left = x;
    x.parent = y;
}

// right-rotate subtree rooted at y
void rightRotate(Tree T, Node y){
    assert y.left!=null;

    Node x = y.left;
    Node B = x.right;
    Node P = y.parent;

    // create edge y-B
    y.right = B;
    B.parent = y;

    // create edge P-x
    x.parent = P;
    if(P==null)
        T.root = x;
    else if(y==P.left)
        P.left = x;
    else
        P.right = x;

    // create edge x-y
    x.right = y;
    y.parent = x;
}
```
