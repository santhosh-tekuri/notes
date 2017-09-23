# Pancake Sort

Given unsorted array `a[]`  
the only operation allowed is `flip(a, i)` i.e reverse `a[0...i]`  
sort with as few flips as possible

---

find max element and move it to its final position, repeatedly

to move element at `i` to position `j` where `i<j`:
* `flip(a,i)` (this moves `a[i]` to `a[0]`)
* `flip(a,j)` (this moves `a[0]` to `a[j]`)

```java
void sort(int a[]) {
    for(int k=a.length-1; k>1; k--) {
        // invariant: a[k+1...] is sorted
        int mi = maxIndex(a[0...k]);
        if(mi!=k) {
            flip(a, mi);
            flip(a, k);
        }
    }
}
```
Running Time: `$O(n^2)$`  
#flips: `$O(n)$` (max `2n` flips)

### References

* <http://www.geeksforgeeks.org/pancake-sorting/>
