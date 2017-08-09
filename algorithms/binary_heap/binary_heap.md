# Binary Heap

Binary Heap is a binary tree with two additional constraints

* shape property

  the tree nearly complete binary tree. *i.e all levels are completely filled except possibly the lowest, which is filled from left up to a point*
* heap property

  the nodes are either `>=` *(max-heap)* or `<=` *(min-heap)* each of its children

Heaps are commonly implemented using arrays with `A[0]` as root

```java
int left(int i) {
    return 2*i + 1;
}

int right(int i) {
    return 2*i + 2;
}

// note: parent(0) returns 0
int parent(int i) {
    return Math.floor((i-1)/2);
}

int height(int n) {
    return Math.floor(log2(n));
}
```

in max-heap: `A[parent[i]] >= A[i]` and largest element is at root  
in min-heap: `A[parent[i]] <= A[i]` and smallest element is at root

root is said to be at level 0  
#elements at level L = `$2L$`  
total #elements from root to level L = `$2^0+2^1+2^2+...+2^L = 2^{L+1}-1$`

height of n element heap = `$\lfloor{\log_2 n}\rfloor$`  
min #elements in heap of height h = `$2^h$`  
max #elements in heap of height h = `$2^{h+1}-1$`
