# Egg Dropping

There is a building with `k` floors. Given `n` eggs, you need to figure out highest floor
an egg can be dropped without breaking. find mininmum number of egg drops needed to find
the solution ?

```bash
n=2 k=10  ans=4
n=2 k=36  ans=8
n=2 k=100 ans=14
n=3 k=14  ans=4
```

---

let `d[n][k]` = min drops needed to identify critical floor given `n` eggs and `k` floors

```bash
d[n][0] = 0 // no floors -> no drops needed
d[1][k] = k // drop at each floor from bottom to top
d[n][k] = 1 +            // current drop 
          min(max(
            d[n-1][x-1], // egg broke from x-th floor
            d[n][k-x]    // egg did not broke from x-th floor
          )) for x = 1, 2, ... k
```

Running Time: $O(nk^2)$

```java
int minDrops(int n, int k) {
    int d[n+1][k+1];
    for(int j=1; j<=k; j++)
        d[1][j] = j;
    for(int i=2; i<=n; i++) {
        for(int j=1; j<=k; j++) {
            d[i][j] = ∞;
            for(int x=1; x<=j; x++)
                d[i][j] = min(d[i][j], max(d[i-1][x-1], d[i][j-x]));
            d[i][j] += 1;
        }
    }
    return d[n][k];
}
```

`@src(src/EggDropping.java)`

---

## if given only 2 eggs

let us say answer is `x`:

* try floor `x`
    * if egg breaks: 
        * we have only one egg left
        * now try every floor from `1` to `x-1`
        * so total drops is `1+(x-1) -> x`
    * if egg does not break: try floor `x+(x-1)`
        * if egg breaks:
            * now try every floor from `x+1` to `x+(x-1)-1`
            * so total drops is `[x+(x-1)-1]-[x+1]-1+2 -> x` 

see how many floors we are covering in each attempt:
1. drop at floor `x`, covering `x` floors
2. drop at floor `x+(x-1)`, covering `x-1` floors
3. drop at floor `x+(x-1)+(x-2)`, covering `x-2` floors
4. and so on

using this strategy, total floors covered: $x + (x-1) + (x-2) + \dots + 2 + 1 = \frac{x(x+1)}{2}$

the building has `k` floors. so:

$\begin{align}
\frac{x(x+1)}{2} & \ge k \\
x^2 + x - 2k & \ge 0 \\
x & = \biggl\lceil \frac{-1+\sqrt{1+8k}}{2} \biggr\rceil
\end{align}$

```java
int minDrops(int k) {
    return ceil((-1.0+sqrt(1+8*k))/2.0);
}
```

Running Time: $O(1)$

---

### References

* <https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/>
* <http://en.wikipedia.org/wiki/Dynamic_programming#Egg_dropping_puzzle>