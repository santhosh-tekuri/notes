# Counting Boolean Parenthesizations

You are given a boolean expression consisting of a string of the symbols `true`, `false`, `and`, `or`, and `xor`.
Count the number of ways to parenthesize the expression such that it will evaluate to `true`.
For example, there are 2 ways to parenthesize `true and false xor true` such that it evaluates to true

**Input:**  
operand array `opd[n]`, where `opd[i] = T/F`  
operator array `opr[n-1]` where `opr[i] = and/or/xor`

---

Let:
* `$T_{i..j}$` = #ways to parenthesize `opd[i...j]` such that it evaluates to `true`
* `$F_{i..j}$` = #ways to parenthesize `opd[i...j]` such that it evaluates to `false`
* `$C_{i..j}$` = #ways to parenthesize `opd[i...j]` = `$T_{i..j} + F_{i..j}$`

then:

`$T_{i..j}=\begin{cases}
\left.
\begin{array}{l}
1&\text{if $opd[i]=T$}\\
0&\text{if $opd[i]=F$}
\end{array}
\right\}
if i=j\\
\left.
\begin{array}{l}
\sum\limits_{k=i}^{j} T_{i..k} T_{k+1..j}& \text{if $opr[k]=and$}\\
\sum\limits_{k=i}^{j} C_{i..k} C_{k+1..j} - F_{i..k} F_{k+1..j}& \text{if $opr[k]=or$}\\
\sum\limits_{k=i}^{j} T_{i..k} F_{k+1..j} + F_{i..k} T_{k+1..j}& \text{if $opr[k]=xor$}
\end{array}
\right\}
if i\neq j\\
\end{cases}$`

`$F_{i..j}=\begin{cases}
\left.
\begin{array}{l}
1&\text{if $opd[i]=F$}\\
0&\text{if $opd[i]=T$}
\end{array}
\right\}
if i=j\\
\left.
\begin{array}{l}
\sum\limits_{k=i}^{j} C_{i..k} C_{k+1..j} - T_{i..k} T_{k+1..j}& \text{if $opr[k]=and$}\\
\sum\limits_{k=i}^{j} F_{i..k} F_{k+1..j}& \text{if $opr[k]=or$}\\
\sum\limits_{k=i}^{j} T_{i..k} T_{k+1..j} + F_{i..k} F_{k+1..j}& \text{if $opr[k]=xor$}
\end{array}
\right\}
if i\neq j\\
\end{cases}$`

---

```java
int countParenthesizations(boolean opd[], char opr[]) {
    int n = opd.length;
    int t[n][n], f[n][n];
    for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {
            if(i==j) {
                t[i][j] = opd[i] ? 1 : 0;
                f[i][j] = opd[i] ? 0 : 1;
            } else {
                for(int k=i; k<=j; k++) {
                    switch(opr[i]) {
                    case '&':
                        t[i][j] += t[i][k]*t[k+1][j];
                        f[i][j] += (t[i][k]+f[i][k])*(t[k+1][j]+f[k+1][j]) - t[i][k]*t[k+1][j];
                        break;
                    case '|':
                        t[i][j] += (t[i][k]+f[i][k])*(t[k+1][j]+f[k+1][j]) - f[i][k]*f[k+1][j];
                        f[i][j] += f[i][k]*f[k+1][j];
                        break;
                    case '^':
                        t[i][j] += t[i][k]*f[k+1][j] + f[i][k]*t[k+1][j];
                        f[i][j] += t[i][k]*t[k+1][j] + f[i][k]*f[k+1][j];
                    }
                }
            }
        }
    }
    return t[0][n-1];
}
```

Time Complexity: `$O(n^3)$`

---

### References

* Problem 9 from <https://people.cs.clemson.edu/~bcdean/dp_practice/>
<https://www.youtube.com/watch?v=oyj9tRZhmis>
