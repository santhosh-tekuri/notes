# Min Length Unsorted Subarray

Given unsorted array `a`, find minimum length subarray such that sorting that subarray makes the whole array sorted

{10, 12, 20, **30, 25, 40, 32, 31, 35**, 50, 60}  
{0, 1, **15, 25, 6, 7**, 30, 40, 50}

---

**step1: find the candidate of unsorted subarray**

`s` = smallest index such that `a[s]>a[s+1]`  
note that no such `s` exists if `a` is already sorted

`e` = largest index such that `a[e]<a[e-1]`  
note that `s<e` always holds

```bash
               s               e
  {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60}
```

**step2: grow it to find answer if necessary**

min = min in `a[s...e]` // 25  
max = max in `a[s...e]` // 40

decrement `s` while `a[s-1]>min`  
increment `e` while `a[e+1]<max`

answer is `a[s...e]`

```java
int[] minLengthUnsoredSubarray(int a[]) {
    int n = a.length;

    int s = 0;
    while(s<n-1 && a[s]<=a[s+1])
        s++;
    if(s==n-1)
        return null; // a[] is already sorted

    int e = n-1;
    while(e>0 && a[e]>=a[e-1])
        e--;
    assert e>s

    int min = min(a, s, e);
    int max = max(a, s, e);

    while(s>0 && a[s-1]>min)
        s--;
    while(e<n-1 && a[e+1]<max)
        e++;
    return new int[]{s, e};
}
```

running-time: `$O(n)$`

### References

* <http://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/>
