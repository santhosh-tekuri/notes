# #Different Binary Trees

Calculate number of different binary trees with `n` nodes for $n \ge 0$

```
n=0 ans=1
n=3 ans=5
    o     o     o      o     o
   /     /     / \      \     \
  o     o     o   o      o     o
 /       \              /       \
o         o            o         o

n=10 ans=16796
```
---

### recursive

a binary tree with `n` nodes consists of a root node, a left subtree with `m` nodes, 
and a right subtree with `n-1-m` nodes for some $0 \le m \le n-1$

```java
int nTrees(int n) {
    if(n==0)
        return 1;
    int s = 0;
    for(int m=0; m<n; m++)
        s += nTrees(m) * nTrees(n-1-m);
    return s;
}
```

`@src(src/CountBinaryTreesV1.java)`

---

### iterative

calculate counts from bottom up

```java
int nTrees(int n) {
    int c[n+1];
    c[0] = 1;
    for(int i=1; i<=n; i++) {
        int s = 0;
        for(int m=0; m<i; m++)
            s += c[m] * c[i-1-m];
        c[i] = s;
    }
    return c[n];
}
```

`@src(src/CountBinaryTreesV2.java)`
