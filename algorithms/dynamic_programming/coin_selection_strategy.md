# Coin Selection Stragery

Problem 10 in <https://people.cs.clemson.edu/~bcdean/dp_practice/>

Consider a row of `n` coins of values `$v_1, v_2, \dots, v_n$`, where `n` is even. 
We play a game against an opponent by alternating turns. In each turn, a player 
selects either the first or last coin from the row, removes it from the row permanently, 
and receives the value of the coin. 

Determine the maximum possible amount of money we can definitely win if we move first ?

```bash
5, 3, 7, 10 ➜ 10+5 = 15
8, 15, 3, 7 ➜ 7+15 = 22
20, 30, 2, 2, 2, 10 ➜ 10+2+30 = 42
2, 2, 2, 2 ➜ 2+2 = 4
```

---

### Why Greedy doesn't work ?

chose max value coin in each turn

consider `1, 1, 4, 3`
* greedy: `3+1 = 4`
* optimal: `1+4 = 5`

---

let `m[i][j]` is max value we can definitely win from coins `v[i..j]`, it it is our turn

`$m[i][j]=\begin{cases}
\begin{array}{l}
v[i] & \text{if $i=j$} & \text{// only single coin} \\
max(v[i],v[j]) & \text{if $i+1=j$} & \text{// only two coins, select max} \\
\end{array} \\
\begin{array}{l}
max( \\
\left.\begin{array}{l}
&v[i]+min( \\
&\;\;\;\;m[i+2][j], & \text{// opponent chose $v[i+1]$} \\
&\;\;\;\;m[i+1][j-1] & \text{// opponent chose $v[j]$} \\
&), 
\end{array}\right \} \text{we chose $v[i]$}\\
\left.\begin{array}{l}
&v[j]+min( \\
&\;\;\;\;m[i+1][j-1], & \text{// opponent chose $v[i]$} \\
&\;\;\;\;m[i][j-2] & \text{// opponent chose $v[j-1]$} \\
&)
\end{array}\right \} \text{we chose $v[j]$}\\
)
\end{array}
\end{cases}$`

```java
int maxWin(int v[]) {
    int n = v.length;
    int m[n][n];

    // single coin
    for(int i=0; i<n; i++)
        m[i][i] = v[i];

    // two coins
    for(int i=0; i<n-1; i++)
        m[i][i+1] = max(v[i], v[i+1]);

    for(int len=3; len<=n; len++) {
        for(int i=0,j=len-1; j<n; i++,j--) {
            m[i][j] = max(
                v[i] + min(m[i+2][j], m[i+1][j-1]),
                v[j] + min(m[i+1][j-1], m[i][j-2])
            );
        }
    }

    return m[0][n-1];
}
```

Running Time: `$O(n^2)$`

---

### What is strategy to always win ?

<https://articles.leetcode.com/coins-in-line/>

* always chose first turn
* compute sum of coins at odd positions, and sum of coins at even positions
    * if `oddSum>evenSum`, always chose coins at odd positions
    * if `oddSum<evenSum`, always chose coins at event positions
    * if `oddSum=evenSum`, always chose either odd or event → it will a tie

How you can always choose odd-numbered/even-numbered coins ?

consider `v[1...10]` and you want always chose odd ones
* you pick `v[1]` and remaining are `v[2...10]`
* opponent have to chose `v[2]` or `v[10]` i.e, forced to chose even ones

note that, this strategy doesn't always give maximum amount:
* consider `3, 2, 2, 3, 1, 2`
* `oddSum=3+2+1=6 evenSum=2+3+2=7`, so our strategy yields `7`
* optimal solution:
    * pick `3`
    * no matter which coin opponent chose, always take `2`
    * now coins will be `2, 3, 1`
    * in your last turn you always get `3`
    * so total amount is `3+2+3=8`

---

### Variation

Assume that your opponent is so dumb that you are able to manipulate him into choosing the coins you want him to choose. Now, what is the maximum possible amount of money you can win?

in `m[i][j]` replace `min(...)` with `max(...)`
