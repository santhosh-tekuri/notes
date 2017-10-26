# n<sup>th</sup> element in union of intervals

Given a list of integers, find the `n`<sup>th</sup> smallest number, i.e., the number
that appears at index `n` (0-based) when they are sorted in non-descending order. 

The numbers will be given in intervals  
For example, the intervals `(1, 3)` and `(5, 7)` represent the list of numbers `{ 1, 2, 3, 5, 6, 7 }`

a number may be present in more than one interval, and it appears in the list once for each interval it is in  
For example, the intervals `(1, 4)` and `(3, 5)` represent the list of numbers `{ 1, 2, 3, 3, 4, 4, 5 }`

```bash
lower = {1, 5} upper = {3, 7} n = 4 ➜ 6
lower = {1, 3} upper = {4, 5} n = 3 ➜ 3
lower = {-1500000000} upper = {1500000000} n = 1500000091 ➜  91
```

---

* it is possible to quickly find #elements in list less than some number `x`
* if this count is:
    * `>n` then the answer is certainly `<x`
    * `=<n` then the answer is certainly `>=x`

```java
// returns #elements less than x in the list
int countLessThan(int lower[], int upper[], int x) {
    int count = 0;
    for(int i=0; i<lower.length; i++) {
        if(x>upper[i]) // all elements less than x => (li, ui) x
            count += upper[i]-lower[i]+1;
        else if(x>=lower[i] && x<=upper[i]) // x is part of interval => (li--x--ui)
            count += x-lower[i];
    }
    return count;
}

int nthElement(int lower[], int upper[], int n) {
    int lo=Integer.MIN, hi=Integer.MAX;
    while(lo<hi) {
        int m = lo+(hi-lo)/2;
        if(count>n)
            hi = m-1;
        else
            lo = m;
    }
    return lo;
}
```

---

### References

* <http://community.topcoder.com/stat?c=problem_statement&pm=4823&rd=8074>
* <http://community.topcoder.com/tc?module=Static&d1=match_editorials&d2=srm277>
