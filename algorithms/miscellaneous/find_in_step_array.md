# Find in Step Array

Given an array, where each element is one more or one less than its preceding element.
How to search for an element efficiently?

---

if `a[0]=x` then:
* `a[1]` is either `x+1` or `x-1`
* all elements at distance `k` i.e `a[1...k]` are in range `[x-k, x+k]`

suppose we are searching for y:
* if `a[0]=x` and we can jump `abs(y-x)` steps ahead
* because everything before `abs(y-x)` steps is not candidate for match

```java
int findIndex(int a[], int y) {
    for(int i=0; i<a.length;) {
        if(a[i]==y)
            return i;
        i += abs(y-a[i]);
    }
    return -1;
}
```

Running Time: `$O(n)$`

**Worst-Case:**

given array where all elements at even index are `0`, you are searching for `2`.  
you will end up checking every even index.

---

**what if we just want to know whether an elements exists or not? and such queries are very frequent.**

scan the array to find `min` and `max` values in `$O(n)$` time.  
if given element is in range `[min, max]`, then it exists, otherwise not.

---

### References

* <http://www.quora.com/Algorithms/Given-an-array-each-element-is-one-more-or-one-less-than-its-preceding-element-We-need-to-search-for-a-particular-element-in-it-What-is-the-most-optimal-solution>
