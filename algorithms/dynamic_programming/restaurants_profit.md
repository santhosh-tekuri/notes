# Restaurents Profit


Yuckdonald’s is considering opening a series of restaurants along Quaint Valley Highway (QVH).
The `n` possible locations are along a straight line, and the distances of these locations 
from the start of QVH are, in miles and in increasing order, `$m_1, m_2, \dots, m_n$`. 

The constraints are as follows:
* At each location, Yuckdonald’s may open at most one restaurant. The expected profit from 
  opening a restaurant at location `i` is `$p_i$`, where `$p_i>0$` and `$i = 1, 2, \dots, n$`
* Any two restaurants should be at least `k` miles apart, where `k` is a positive integer.

Give an efficient algorithm to compute the maximum expected total profit subject to the given constraints?

---

### Why greedy does not work ?

Repeat:
* Pick the location `i` with highest profit p<sub>i</sub>
* Remove all locations within `k` miles of location `i`

```bash
k = 10
m = 5, 10, 15
p = 2, 3, 2
```
greedy: p<sub>2</sub> = 3  
optimal: p<sub>1</sub>+p<sub>2</sub> = 2+2 = 4

---

let:
* `P[i]` is maximum profit achievable using first `i` locations
* `prev[i]` is largest index `<i` such that `m[i]-m[prev[i]] >= k` or `0` if there is no such index
    * i.e if we have a restaurant at `i` we can't have restaurant at locations `prev[i]+1` to `i-1`

```bash
P[0] = 0
P[i] = max(
           P[i-1],           // no restaurant at location i
           p[i] + P[prev[i]] // restaurant at location i
       )
```

answer is `P[n]`

```java
int maxProfit(int m[], int p[], int k) {
    int n = m.length;

    // compute prev array. prev[i]=-1 if no restaurent can be possible
    int prev[n];
    int x = -1;
    for(int i=0; i<n; i++) {
        while(m[i]-m[x+1]>=k)
            x++;
        prev[i] = x;
    }

    int P[n];
    P[0] = p[i]; // if we have only one location, open restaurant there
    for(int i=1; i<n; i++)
        P[i] = max(P[i-1], p[i]+(prev[i]==-1 ? 0 : P[prev[i]]);

    return P[n-1];
}
```

Running Time: `$O(n)$`

:bulb: we can avoid `prev[]` by combining for loops:

---

### References

* Exercise 6.3 from [Algorithms by Dasgupta](https://isbnsearch.org/isbn/0073523402)
* <https://cseweb.ucsd.edu/~dasgupta/101/sol7.pdf>
