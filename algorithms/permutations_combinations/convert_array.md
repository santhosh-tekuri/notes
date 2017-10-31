# Convert Array

Given array `$[a_1, a_2, \dots, a_n, b_1, b_2, \dots, b_n, c_1, c_2, \dots, c_n]$`  
convert it to `$[a_1, b_1, c_1, a_2, b_2, c_2, \dots, a_n, b_n, c_n]$` in-place using constant space

---

```java
// returns index in original(input) array
// for i-th position in final array
int getIndex(int i, int n){
    return (i%3)*n+i/3;
}
```

* for each element left to right:
    * find `swapIndex` is using `getIndex()`
    * if `swapIndex>curIndex`
        * swap elements
    * otherwise
        * it means element at `swapIndex` was replaced with another element in previous iterations
        * now it is somewhere else. so we should keep looking for that element
        * this can be done repeatedly calling `getIndex()` on `swapIndex` until `swapIndex<curIndex`

```java
void convert(int a[]) {
    int n = a.length/3;
    for(int i=0; i<a.length; i++) {
        int swapIndex = getIndex(i, n);
        while(swapIndex<i)
            swapIndex = getIndex(swapIndex, n);
        a[i] ⬌ a[swapIndex];
    }
}
```

Running Time is approximately `$O(n^{1.3})$`

---

### References

* <http://www.ardendertat.com/2011/10/18/programming-interview-questions-9-convert-array/>
