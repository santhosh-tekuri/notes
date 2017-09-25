# Searching BST

```java
// returns minimum element in subtree rooted at node n
Node minimum(Node n) {
    return leftMost(n);
}

// returns maximum element in subtree rooted at node n
Node maximum(Node n) {
    return rightMost(n);
}

Node search(Node n, int v) {
    while(n!=null && n.data!=v){
        if(v<n.data)
            n = n.left;
        else
            n = n.right;
    }
    return n;
}
```

---

## Is BST Path ?

Given an array of integers, find whether it could be sequence of nodes examined in BST to search for last element in array?

`$
2, 252, 401, 398, 330, 344, 397, 363      => true \\
924, 220, 911, 244, 898, 258, 362, 363    => true \\
925, 202, \color{red}{911}, 240, \color{red}{912}, 245, 363         => false \\
2, 399, 387, 219, 266, 382, 381, 278, 363 => true \\
935, 278, \color{red}{347}, 621, \color{red}{299}, 392, 358, 363    => false
$`

in valid sequence: for any `a[i]`, all values appearing after it are either `>=a[i]` or `<=a[i]`

```java
boolean isSearchSequence(int a[]) {
    int min = -∞;
    int max = +∞;

    for(int i=0; i<a.length; i++) {
        if(a[i]>=min && a[i]<=max) {
            if(a[i]<=a[a.length-1])
                min = a[i];
            if(a[i]>=a[a.length-1])
                max = a[i];
        } else
            return false;
        i++;
    }
}
```

---

## Closest to `k`

Find a node in binary search tree whose value is closest to given value `k`?

traverse the tree as you would find the element `k`. While doing that record node closest to `k`.  
If you didn't find the node, return the closed recorded, else return the node found.

```java
Node findClosest(Node n, int k) {
    Node closest = null;
    while(n!=null) {
        if(n.data==k)
            return n;

        if(closest==null)
            closest = n;
        else if(Math.abs(n.data-k)<Math.abs(closest.data-k))
            closest = n;

        if(k<node.data)
            n = n.left;
        else
            n = n.right;
   }
   return closest;
}
```

---

## Floor and Ceil

Consider designing a memory management system in which free nodes are arranged in BST.
You have to find Best Fit for the input request i.e, ceil

```java
// returns node with smallest data larger than or equal to given value
Node ceil(Node node, int input){
    while(true) {
        if(node.data==input)
            return node;
        if(node.data>input) {
            if(node.left==null)
                return node; // line 6
            node = node.left;
        } else {
            if(node.right==null)
                return null; // line 10
            node = node.right;
        }
    }
}

// returns node with largest data smaller than or equal to given value
Node floor(Node node, int input) {
    while(true) {
        if(node.data==input)
            return node;
        if(node.data>input) {
            if(node.left==null)
                return null; // line 6
            node = node.left;
        } else {
            if(node.right==null)
                return node; // line 10
            node = node.right;
        }
    }
}
```

NOTE: swapping line 6 and 10, in `ceil` function gives `floor` function.

---

## Print values in given range

<http://www.geeksforgeeks.org/archives/11103>

Given two values `k1` and `k2` where `k1<k2` and root of BST.
Print all keys in BST in range `k1` and `k2` in increasing order.

```bash
       20       k1 = 10 and k2 = 22
      /  \      ans = 12, 20, 22
     8   22
    / \
   4  12
```

We can do this by modified inOrder.

```java
void printRange(Node node, int k1, int k2) {
    if(node!=null){
        if(node.data>=k1)
            printRange(node.left, k1, k2);
        if(node.data>=k1 && node.data<=k2)
            print(node.data);
        if(node.data<=k2)
            printRange(node.right, k1, k2);
    }
}
```

TimeComplexity: `$O(n)$` where `n` is number of nodes in tree.
