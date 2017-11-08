# Largest Independent Set of Binary Tree

A subset of nodes is called independent set, if there is no edge between any two nodes in subset.

Given a binary tree, find size of largest independent set in it ?

```bash
    [10]
    /  \
   20   30
  /  \    \
[40]  50  [60]
     /  \
  [70]  [80]
```

---

### Why greedy does not work ?

Heaviest-First Greedy Approach:

```bash
while(some node remains in graph){
    pick node n with maximum weight
    remove n and its neighbors
}
```

```bash
2
 \     greedy: {3}
  3    optimal: {2, 2}
 /
2
```

Alternate Greedy Approach:

* S<sub>odd</sub> is set containing nodes at odd level
* S<sub>even</sub> is set containing nodes at even level
* answer is largest(S<sub>odd</sub>, S<sub>even</sub>)

```bash
    10
   /  \
  20  30      greedy: {10, 40, 50, 60} or {20, 30, 70, 80}
 /  \   \     optimal: {10, 40, 60, 70, 80}
40  50  60
   /  \
  70  80
```

---

if a node is included in independent set, then:
* its children cannot be included
* its grandchildren can be included

let `L[x]` is size of largest independent set of tree rooted at `x`

```bash
L[x] = max(
          1 + sum(L[x.grandChildren]), # x is included
          sum(L[x.children])           # x is not included
       )
```

Overlapping Subproblems:

```bash
a
 \     LIS(c) is used while computing LIS(b) and LIS(a)
  b
 /
c
```

careful recursion can avoid recomputing overlapping subproblmes

```java
int sizeOfLIS(Node root) {
    return computeLIS(root).self;
}

class LIS {
	int self;
    int left;
    int right;
}

LIS computeLIS(Node x) {
    LIS lis = new LIS();
    if(x!=null) {
        LIS left = computeLIS(x.left);
        LIS right = computeLIS(x.right);
        lis.left = left.self;
        lis.right = right.self;
        lis.self = max(1+left.left+left.right+right.left+right.right, left.self+right.self);
    }
    return lis;
}
```

Running Time: `$O(n)\;\;$` where `n` is number of nodes

---

### References

* Unsolved Exercise 6.1 from Algorithm Design by Jon Kleinberg, Eva Tardos
* <http://www.geeksforgeeks.org/largest-independent-set-problem/>
