# Order Statistic Tree

Chapter 14.1 from [CLRS](https://en.wikipedia.org/wiki/Introduction_to_Algorithms)

`rank` of an element in BST, is its position in increasing order

An *order statistic tree* is a variant of the binary search tree that supports two additional operations:
* `select(rank)`: retrieve element with given rank
* `rank(elem)`: determine rank of given element

---
## Implementation

Augment each node in BST with additional field called `size`, which is #nodes rooted at that node.

```bash
size(nil) = 0
size(n) = size(n.left) + size(n.right) + 1
```

```java
int size(Node n) {
    return n==null ? 0 : n.size;
}
```

```java
// recursive implementation
Node select(Node n, int rank) {
    if(n==null)
        return null;
    int r = size(n.left) + 1; // rank of n withing subtree rooted at n
    if(rank==r)
        return n;
    if(rank<r)
        return select(n.left, rank);
    else
        return select(n.right, rank-r);
}

// iterative implementation (Exercise 14.1-3)
Node select(Node n, int rank) {
    while(n!=null) {
        int r = size(n.left) + 1;
        if(rank==r)
            return n;
        if(rank<r)
            n = n.left;
        else {
            n = n.right;
            rank -= r;
        }
    }
    return null;
}
```

```java
int rank(Node n) {
    int r = size(n.left) + 1;
    Node np = n.parent;
    while(np!=null) {
        if(n==np.right)
            r += size(np.left) + 1;
        n = np;
        np = n.parent;
    }
    return r;
}

// returns rank of node with given key (Exercise 14.1-4)
int rank(Node root, int key) {
    Node n = root;
    int r = 0;
    while(n!=null) {
        if(key==n.data)
            return r + size(n.left) + 1;
        if(key<n.data)
            n = n.left;
        else {
            r += size(n.left) + 1;
            n = n.right;
        }
    }
    return -1;
}

// returns i-th successor of node n (Exercise 14.1-5)
Node successor(Node n, int i) {
    int r = rank(n);
    return select(root(n), r+i);
}
```

### Maintaining Sizes

during `insert`, increment size of each node on the path traversed from root to leaf  

```java
void insert(BST t, Node n){
    // do search. x traces path and xp is maintained as parent of x
    Node xp = null;
    Node x = t.root;
    while(x!=null){
        x.size++; // <------- this is the change ----
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

during `delete`, decrement size of each node from node spliced up to root

```java
Node delete(BST t, Node n) {
    ...
    
    Node x = splice.parent;
    while(x!=null) {
        x.size--;
        x = x.parent
    }
    return splice;
}
```

during rotations, update sizes of `x` and `y` accordingly

```bash
  P                                  P
  |                                  |
  x         leftRotate(T, x) ⇛       y
 / \                                / \
A   y     ⇚ rightRotate(T, y)      x   C
   / \                            / \
  B   C                          A   B
```

```java
void leftRotate(BST t, Node x) {
    ....
    y.size = x.size;
    x.size = size(x.left) + size(x.right) + 1;
}

void rightRotate(BST t, Node y) {
    ...
    x.size = y.size
    y.size = size(y.right)+size(y.right)+1;
}
```
