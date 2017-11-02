# Piggy Bank

You are saving coins in a piggy bank. You are in need of money and 
you dont want to prematurely break it. How would you find the minimum 
amount of money the piggy bank guaranteed to contain, given:
* `E` =  empty weight of pig
* `F` = weight of pig with coins
* `p[]` = value of coins
* `w[]` = weight of coins

```bash
E=10 F=110 p={1, 30} w={1, 50} ➜ 60
E=10 F=110 p={1, 50} w={1, 30} ➜ 100
E=1  F=6   p={10, 20} w={3, 4} ➜ -1 (impossible)
```

---

:bulb: this is small variation to **knapsack with repetition** where knapsack capacity is `F-E`

let `m[k]` is minimum amount guaranteed with coins weighing `k`

`$m[k]=\begin{cases}
0 & \text{if $k=0$} \\
min( \\
\;\;\;\;\infty, & \text{// coins weight $k$ is impossible} \\
\;\;\;\;m[k-w[i]]+p[i] & \text{for $i=1$ to $n$, where $w[i]\leq k$} \\
) \\
\end{cases}$`

answer is `m[F-E]`

```java
int minAmount(int E, int F, int p[], int w[]) {
    int m[F-E+1];
    m[0] = 0;
    for(int k=1; k<m.length; k++) {
        m[k] = ∞;
        for(int i=0; i<p.length; i++) {
            if[w[i]<=k)
                m[k] = min(m[k], m[k-w[i]]+p[i]);
        }
    }
    return m[m.length-1] == ∞ ? -1 : m[m.length-1];
}
```

Running Time: `$O(n(F-E))$`

---

### References

* <http://www.spoj.com/problems/PIGBANK/>
* <https://github.com/marioyc/Online-Judge-Solutions/blob/master/SPOJ/Classical/39%20-%20Piggy%20Bank.cpp>
