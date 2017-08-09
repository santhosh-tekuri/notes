# Heap Sort

```java
void heapSort(int a[], int count){
    heapify(a, count);

    int end = count-1;
    while(end>0){
        swap(a[0], a[end]);
        end--;
        siftDown(a, 0, end);
    }
}
```

Time Complexity: `$O(n\log_2 n)$`

heap sort is not stable
