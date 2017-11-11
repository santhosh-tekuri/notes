# Longest Palindromic Subsequence

Given a sequence `$X=x_1,x_2,…,x_n$`, find longest subsequence that is palindromic ?

`$
\color{red}{bbb}a\color{red}b \\
c\color{red}{bb}d \\
B\color{red}{BABCB}C\color{red}{AB}
$`

---

let `L[i][j]` is length of longest palindromic subsequence

`$L[i][j]=\begin{cases}
1 & \text{if $i=j$} \\
\left.
\begin{array}{l}
2 & \text{if $i+1=j$} \\
2 + L[i+1][j-1] & \text{otherwise}
\end{array} 
\right\} & \text{if $x_i=x_j$} \\
max(L[i+1][j], \;L[i][j-1]) & \text{otherwise}
\end{cases}$`

answer is `L[1][n]`

```java
int lps(char s[n]) {
    int L[n][n];
    for(int i=0; i<n; i++)
        L[i][i] = 1;
    for(int len=2; len<n; len++) {
        for(int i=0,j=len-1; j<n; i++,j--) {
            if(s[i]==s[j]) {
                L[i][j] = 2;
                if(len>2)
                    L[i][j] += L[i+1][j-1];
            } else
                L[i][j] = max(L[i+1][j], L[i][j-1]);
        }
    }
    return L[0][n-1];
}
```

Running Time: `$O(n^2)$`

:bulb: this problem can be solved as `LCS(s, reverse(s))`

---

### References

* Exercise 6.7 in Algorithms by Dasgupta
* Problem 6.7 in <http://users.eecs.northwestern.edu/~dda902/336/hw6-sol.pdf>
* <http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/>
