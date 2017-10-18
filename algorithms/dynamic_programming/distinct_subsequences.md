# Distinct Subsequence

Given `$X=x_1,x_2,\dots,x_m$` and `$Z=z_1,z_2,\dots,z_k$`, find number of distinct occurences of `Z` in `X` as subsequence

Examples:
* `bag` occurs 5 times in `babgbag`  
* `rabbit` occurs 3 times in `rabbbit`  
* `welcome to code jam` occurs 256 times in `wweellccoommee to code qps jam`

---

let `$c[i,j]$` is number of times `$z_{1 \dots i}$` appears in `$x_{1 \dots j}$`

`$c[i][j]=\begin{cases}
1 & \text{ if } i=0 & \text{ # empty string appears once in any string} \\
0 & \text{ if } i>0 \;and\; j=0 & \text{ # non-empty subsequence cannot appear in empty string} \\
c[i-1][j-1] + c[i][j-1] & \text{ if } i,j>0 \;and\; z_i=x_j \\
c[i][j-1] & \text{ if } i,j>0 \;and\; z_i \neq x_j
\end{cases}$`

notice that `c[i][j]` is `0` if `i>j`, because subsequence cannot be larger than original string

answer is `$c[k][m]$`

```java
int countSubsequences(char x[], char z[]) {
    int c[z.length+1][x.length+1];
    for(int i=0; i<=z.length; i++) {
        for(int j=i; j<=x.length; j++) {
            if(i==0)
                c[i][j] = 1;
            else if(j==0)
                c[i][j] = 0;
            else {
                c[i][j] = c[i][j-1];
                if(z[i-1]==x[j-1])
                    c[i][j] += c[i-1][j-1];
            }
        }
    }
    return c[z.length][x.length];
}
```

Time-Complexity: `$O(mk)$`  
Space-Complexity: `$O(mk)$`

:bulb: current row in `c[][]` only depends on previous row. so further optimizations can be done as in [LCS](lcs.md)

---

### References

* <https://code.google.com/codejam/contest/dashboard?c=90101#s=p2>
* <http://www.questtosolve.com/browse.php?pid=10069>
