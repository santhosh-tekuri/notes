# Heapify

array elements `$\lfloor length[A]/2\rfloor...1$` are all leaves of the tree, thus each is one-element heap.

```java
void heapify(int a[], int size){
    for(int i=size/2-1; i>=0; i--) // from index of last parent node
        siftDown(a, size, i);
}
```

Time Complexity: `$O(n)$`

alternatively heapify can be implemented using `siftUp`

```java
void heapify(int a[], int size){
    for(int i=1; i<size; i++)
        siftUp(a, i);
}
```

Time Complexity: `$O(n\log_2 n)$`

---

### Find the `k` smallest of `N` items, where `k<<N` ?

```java
void findLargest(int a[], int k){
    heapify(a, k); // create max-heap with first k elements
    for(int i=k+1; i<a.length; i++){
        if(a[i]<a[0])
            replace(a, k, 0); // replace with root
    }
    for(int i=0; i<k; i++)
		print(a[i])
}
```

Time Complexity: `$ O(N)+O(N\log_2 k)$`

---

### Find all nodes less than some value `x` in min-heap ?

```java
void printLessThan(int a[], int size, int x){
    printLessThan(a, size, 0, x);
}

void printLessThan(int a[], int size, int root, int x){
    if(root<size && a[root]<x){
        print(a[root]);
        printLessThan(a, size, left(root), x);
        printLessThan(a, size, right(root), x);
    }
}
```

Running Time: `$O(m)$` where `m = #items less than x`
