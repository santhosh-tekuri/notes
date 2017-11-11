# Longest Balanced Subsequence

`$
\{\color{red}{((}\}\color{red}{))} \to 4
$`

---

let `m[i][j]` is length of longest balanced subsequence of `s[i...j]`

`$m[i][j]=\begin{cases}
0 & \text{if $i=j$} \\
\left.
\begin{array}{l}
2 & \text{if $s[i]$ pairs with $s[j]$} \\
0 & \text{otherwise}
\end{array}
\right \} & \text{if $i+1=j$} \\
max(m[i][k]+m[k+1][j]) & \text{for $k=i$ to $j-1$}
\end{cases}$`

```java
int lbs(char s[n]) {
    int m[n][n];

    // length 1
    for(int i=0; i<n; i++)
        m[i][i] = 0;

    // length 2
    for(int i=0; i<n-1; i++)
        m[i][i+1] = isPair(s[i], s[j]) ? 2 : 0;

    for(int len=3; len<n; len++) {
        for(int i=0,j=len-1; j<n; i++,j--) {
            m[i][j] = 0;
            for(int k=i; k<j; k++)
                m[i][j] = max(m[i][j], m[i][k]+m[k+1][j]);
        }
    }

    return m[0][n-1];
}
```

Running Time: `$O(n^3)$`

---

### References

* <http://www.geeksforgeeks.org/length-longest-balanced-subsequence/>
