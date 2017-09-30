# Apply Permutation

Given array of integers `a[n]` and array `p[n]` containing a permutation of `0...n-1`.
Rearrange `a` such that: `a[i] = a[p[i]]`

```bash
 Input: a = [10, 11, 12] and p = [1, 0, 2] 
Output: a = [11, 10, 12]

 Input: a = [50, 40, 70, 60, 90] and p = [3,  0,  4,  1,  2] 
Output: a = [40, 60, 90, 50, 70]
```
---

```java
void permute(int a[], int p[]) {
    for(int i=0; i<a.length; i++) {
        while(p[i]!=i) {
            swap(a, i, p[i]);
            swap(p, i, p[i]);
        }
    }
}
```

this destroys the contents of `p[]` making it unrecoverable

---

## Replace in cycles

swap each element in `a` with the right element required by `p`.  
after each swap, there will be one more element in the right position.  
do this in a circular fashion for each of the positions

```bash
a = [a, b, c, d, e] and p = [4, 3, 2, 0, 1]

for cycle starting with index 0:
[a, b, c, d, e] <- P[0] = 4 != 0
 ^           ^
[e, b, c, d, a] <- P[4] = 1 != 0
    ^        ^
[e, a, c, d, b] <- P[1] = 3 != 0
    ^     ^
[e, d, c, a, b] <- P[3] = 0 == 0 => cycle finished
```

After one cycle, we find the next element in the array that does not stay in the right position, and do this again.

You can store the information of which one is in its right place by one of following ways:
* use additional boolean array
* change `p[i]` to `-p[i]-1`. this changes `p` to `[-5, -4, -3, -1, -2]`, which can be recovered in `$O(n)$` trivially.

since each position is touched a constant time (for each position, at most one operation (swap) is performed), it is `$O(n)$` time.

```java
void permute(int a[], int p[]) {
    int i = 0;
    while(i<a.length) {
        if(p[i]>=0) {
            int v = a[i];
            int x = i;
            while(p[x]!=i) {
                a[p[x]], v = v, a[p[x]];
                int t = x;
                x = p[x];
                p[t] = -p[t]-1
            }
            if(x!=i)
                p[x] = -p[x]-1
            p[i] = -p[i]-1
        }
    }

    // restore p[]
    for(int i=0; i<p.length; i++)
        p[i] = -p[i]-1
}
```
---

without modifying `p` and no additional space:

```java
void permute(int a[], int p[]){
    for(int curIndex=0; curIndex<a.length; curIndex++){
        int swapIndex = p[curIndex];
        while(swapIndex!=curIndex)
            swapIndex = p[swapIndex];
        if(curIndex!=swapIndex)
            swap(a, curIndex, swapIndex);
    }
}
```

Runnting Time: `$O(n^2)$`

---

### References

* <http://www.geeksforgeeks.org/reorder-a-array-according-to-given-indexes/>
* <https://stackoverflow.com/a/16501453/104018>
