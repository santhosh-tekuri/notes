# Longest Common Substring

Given two sequences `$X = x_1, x_2, \dots, x_m$` and `$Y = y_1, y_2, \dots, y_n$`. Find maximum-length common substring of `X` and `Y`

---

let `$i^{th}$` prefix of `$X$`, `$X_i$` denotes sequence `$x_1, x_2, \dots, x_i$`

let `$L[i][j]$` is length of longest suffix of `$X_i$` and `$Y_j$`

`$L[i][j]=\begin{cases}
0 & \text{ if } i=0 \; or \; j=0 \\
L[i-1][j-1]+1 & \text{ if } i,j>0 \;and\;x_i=y_j \\
0 & \text{ if } i,j>0 \;and\;x_i\neq y_j
\end{cases}$`

then answer = max of `$L[0 \dots m][0 \dots n]$`

```java
int longestSubstring(char x[], char y[]) {
    int ans = 0;
    int L[x.length+1][y.length+1]
    for(int i=1; i<=x.length; i++) {
        for(int j=1; j<=y.length; j++) {
            if(x[i]==y[j]) {
                L[i][j] = L[i-1][j-1]+1;
                ans = max(ans, L[i][j]);
            }
        }
    }
    return ans;
}
```

Time-Complexity: `$O(mn)$`  
Space-Complexity: `$O(mn)$`

:bulb: current row in `L[][]` only depends on previous row. so further optimizations can be done as in [LCS](lcs.md)

---

### References

* <http://www.geeksforgeeks.org/longest-common-substring/>
