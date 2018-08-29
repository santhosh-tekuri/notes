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

### References

* <https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/>
* <http://en.wikipedia.org/wiki/Dynamic_programming#Egg_dropping_puzzle>