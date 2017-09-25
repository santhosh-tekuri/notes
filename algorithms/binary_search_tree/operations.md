# BST Operations

## Insert

```java
void insert(BST t, Node n){
    // do search. x traces path and xp is maintained as parent of x
    Node xp = null;
    Node x = t.root;
    while(x!=null){
        xp = x;
        if(n.value<x.data)
            x = x.left;
        else
            x = x.right;
    }

    // note: xp must either leaf or has single child
    n.parent = xp;
    if(xp==null) // tree is empty
        t.root = n;
    else if(n.value<xp.data)
        xp.left = n;
    else
        xp.right = n;
}
```

## Delete

Deleting node `n` from BST has 3 cases.
* `n` is leaf:
    * simply remove it from tree
* `n` has only one child:
    *  splice out `n` (i.e remove `n` and replace it with its child)
* `n` has two children:
    * find its successor or predecessor `r`
    * note that `r` has at most one child
    * splice out `r` and replace value of `n` with value of `r`

to summarize:
* find the node to be sliced out and splice out
* if node te be deleted and node spliced out are not same, copy value of spliced node to the node to be deleted

```java
Node delete(BST t, Node n){
    // find the node to be sliced out
    Node splice;
    if(n.left==null || n.right==null) // either leaf or has single child
        splice = n;
    else
        splice = successor(n);

    // find non-null child of splice
    Node spliceChild;
    if(splice.left!=null)
        spliceChild = x.left;
    else
        spliceChild = x.right;

    // splicing out
    if(spliceChild!=null)
        spliceChild.parent = splice.parent;
    if(splice.parent==null)
        t.root = spliceChild;
    else if(splice=splice.parent.left)
        splice.parent.left = spliceChild;
    else
        splice.parent.right = spliceChild;

    if(n!=splice)
        n.data = splice.data

    return splice;
}
```


