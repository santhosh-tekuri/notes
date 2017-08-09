# Heap Operations

## Addition

* add new element to end of heap
* keep moving it up until it is larger than its parent

```java
void add(int a[], int size, int v){
    a[size] = v;
    siftUp(a, size);
}

void siftUp(int a[], int i){
    while(i>0){
        int ip = parent(i);
        if(a[ip]<a[i]){
            swap(a, ip, i);
            i = ip;
        }else
            return;
    }
}
```

`siftUp` assumes `$a[0...i-1]$` is heap

Time Complexity: `$O(\log_2 n)$`

---

## Deletion

* replace element to be deleted with last element of heap
* keep moving down until it is smaller than its children

```java
void delete(int a[], int size, int i){
    a[i] = a[size-1];
    siftDown(a, size-1, i);
}

void siftDown(int a[], int size, int i){
    while(left(i)<size){ // i is not leaf
        int max = i;

        int left = left(i);
        if(a[left]>a[max])
            max = left;

        int right = right(i);
        if(right<size && a[right]>a[max]) // right child exists and it is bigger
            max = right;

        if(max!=root){
            swap(a, i, max);
            i = max;
        }else
            break;
    }
}
```

* `siftDown` assumes that, binary trees rooted at `left(i)` and `right(i)` are max-heaps
   but `a[i]` may be smaller than its children, thus violating max-heap property
* in `siftDown`, `a[i]` flow-down so that, subtree rooted at `a[i]` becomes a max-heap
* notice that `siftDown` is actually merging two heaps

Time Complexity: `$O(\log_2 n)$`

---

## Replace

if new element is less than element being replaced do `siftDown`, otherwise do `siftUp`

```java
// replace a[i] with v
void replace(int a[], int size, int i, int v){
    int oldV = a[i];
    a[i] = v;
    if(v<oldV)
        siftDown(a, end, i);
    else if(v>oldV)
        siftUp(a, i);
    return oldV;
}
```

Time Complexity: `$O(\log_2 n)$`
