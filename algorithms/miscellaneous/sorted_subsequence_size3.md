# Sorted Subsequence of size 3

Given an array of `n` integers, find 3 elements such that `a[i]<a[j]<a[k]` in `$O(n)$` time ?

```bash
{12, 11, 10, 5, 6, 2, 30} => 5, 6, 30
{1, 2, 3, 4} => 1, 2, 3 OR 1, 2, 4 OR 2, 3, 4
{4, 3, 2, 1} => no such triplet
```

---

use Auxiliary space:
* `smaller[i]` gives:
    * index of number which is smaller that `a[i]` and is on left side of `a[i]`
    * -1 if no such element exists
* `greater[i]` gives:
    * index of number which is greater that `a[i]` and is on right side of `a[i]`
    * -1 if no such element exists

traverse both `smaller[]` and `greater[]` to find index `i` such that `smaller[i]` and `greater[i]` are not `-1`

```java
int[] findTriplet(int a[]) {
    int smaller[a.length];
    smaller[0] = -1;
    int minIndex = 0;
    for(int i=1; i<a.length; i++) {
        if(a[i]<=a[minIndex]) {
            minIndex = i;
            smaller[i] = -1;
        } else
            smaller[i] = minIndex;
    }

    int greater[a.length];
    greater[a.length-1] = -1;
    int maxIndex = a.length-1;
    for(int i=a.length-2; i>=0; i--) {
        if(a[i]>=a[maxIndex]) {
            maxIndex = i;
            greater[i] = -1;
        } else
            greater[i] = maxIndex;
    }

    for(int i=0; i<a.length; i++){
        if(smaller[i]!=-1 && greater[i]!=-1)
            return { smaller[i], i, greater[i] };
    }

    return null;
}
```

we can actually merge last two `for` loops to avoid `greater[]`:

```java
int maxIndex = a.length-1;
for(int i=a.length-2; i>=0; i--) {
    if(a[i]>=a[maxIndex])
        maxIndex = i;
    else if(smaller[i]!=-1)
        return {smaller[i], i, maxIndex}
}

```

---

### References

* <http://www.geeksforgeeks.org/find-a-sorted-subsequence-of-size-3-in-linear-time/>
