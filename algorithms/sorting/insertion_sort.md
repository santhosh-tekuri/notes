# Insertion Sort

Insertion Sort works the way many people sort a hand of playing cards

```java
void insertionSort(int a[]) {
    for(int i=1; i<a.length; i++){
        // insert a[i] into sorted sequence a[0..i-1]
        int j = i-1;
        while(j>=0 && a[j]>a[i]) {
            a[j+1] = a[j];
            j--;
        }
        a[j+1] = a[i];
    }
}
```

Best Case *(when array is already sorted)* : `$O(n)$`  
Worst Case *(when array is reverse sorted)* : `$O(n^2)$`

in worst case, each element `a[i]` is compared with all elements before it. i.e, `i` comparisons  
so #comparisons = `$O(n^2)$`  
similarly #swaps = `$O(n^2)$`

---

## Binary Insertion Sort

we can decrease #comparisons by using binary search to find correct slot for `$a[i]$` in sorted sequence `$a[0...i-1]$`  
this reduces #comparisons to `$O(n\log_2 n)$`, but #swaps remains same  
