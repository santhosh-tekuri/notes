# Cutting Rod

Assume a company buys long steel rods and cuts them into small rods for sale to its customers.
If each cut is free and rods of different lengths can be sold at different rates, 
we wish to determine how to best cut a given rod to maximize the revenue.

let
* length of rod is `n` inches and assume we only cut integral lengths.
* rod of length `i` has price p<sub>i</sub>

`$\begin{matrix}
length & 1 & 2 & 3 & 4 & 5 & 6 & 7 & 8 & \\
price & 1 & 5 & 8 & 9 & 10 & 17 & 17 & 20 & \to & p_2+p_6 & =22 \\
      & 3 & 5 & 8 & 9 & 10 & 17 & 17 & 20 & \to & 8p_3 & =24
\end{matrix}$`

---

### Brute Force

At each increment we have a binary decision, whether to cut or not.
Obviously the last increment is not included as it does not produce any new pieces

so #possible ways to cut is equal to number of binary patterns of `n-1` bits, which is `$2^{n-1}$`

---

### Dynamic Programming

let `r[i]` is max revenue achievable in cutting rod of length `i`

```bash
r[0] = 0
r[i] = max(p[k]+r[i-k]) for k = 1 to i
```

```java
int maxRevenue(int p[]) {
    int n = p.length;
    int r[n+1];
    r[0] = 0;
    for(int i=1; i<=n; i++) {
        r[i] = -∞;
        for(int k=1; k<=i; k++)
            r[i] = max(r[i], p[k-1]+r[i-k];
    }
    return r[n];
}
```

Running Time: `$O(n^2)$`

---

### References

* <http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/>
