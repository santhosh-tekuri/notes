# Aggressive Cows

There are `n` stalls in a line. Given sorted `p[]`, where `p[i]` is location of stall `i` on straight line. 
we have `c` cows, and each cow has to be assigned a stall. We want to assign cows to stalls, such that
the minimum distance between any two of them is as large as possible. What is the largest minimum distance ?

`p={1, 2, 4, 8, 9} c=3` → `3`

---

```java
// is possible to arrange cows such that distance between any two cows is atleast d
boolean isPossibleDist(int p[], int c, int d) {
    int lastPos = p[0];
    for(int i=1; i<p.length; i++) {
        if(p[i]-lastPos>=x) {
            c--;
            if(c==0)
                return true;
            lastPos = p[i];
        }
    }
    return false;
}

int minDist(int p[], int c) {
    int lo=0, hi=p[p.length-1]-p[0]+1;
    while(hi-lo>1) {
        assert isPossible(lo) && !isPossible(hi)

        m = lo+(hi-lo)/2;
        if(isPossible(m))
            lo = m;
        else
            hi = m;
    }
    return lo;
}
```

---

### References

* <http://www.spoj.com/problems/AGGRCOW/>
* <https://www.codementor.io/rishabhdaal/solving-problems-binary-search-interview-questions-du1089cq6#problem-1-link-to-problem-solution-code>
