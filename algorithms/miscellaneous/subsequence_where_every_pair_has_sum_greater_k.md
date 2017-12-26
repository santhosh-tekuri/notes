# Subsequence where every pair has sum >= k 

A = {-10, -100, 5, 2, 4, 7, **10, 23, 81**, 5, **25**} and k=20  
Ans: {10, 23, 81, 25}

---

**Such subsequence can have at most one element which is `<k/2`. why ?**

if there are two elements that are `<k/2`, then sum of those two elements
is `<k`. this is contradiction.

```java
int[] findSubsequence(int A[n], int k) {
    int solution = []

    // include all items that are >=k/2
    for(int item:A) {
        if(2*item>=k)
            solution.add(item);
    }

    if(solution.isEmpty())
        return null;

    // include any one item that is <k/2 satisfying constraint
    int m = min(solution);
    for(int item:A) {
       if(2*item<k && item+m>=k) {
           solution.add(item);
           break;
       }
    }

    return solution.size()==1 ? null : solution;
}
```

`@src(src/SubsequenceWithPairsGEk.java)`

Running Time: $O(n)$

---

### References

* <http://stackoverflow.com/a/13022397>
