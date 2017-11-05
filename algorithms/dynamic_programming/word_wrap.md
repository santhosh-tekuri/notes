# Word Wrap

The input text is sequence of `$n$` words of length `$l_1, l_2, \dots, l_n$`.
Print them neatly on number of lines each of maximum `$m$` characters. Leave 
exactly one space between words. Assume that each word is at most length `$m$`

if a line contains words `i` to `j`, extra spaces at end of line = `$m-(l_i+l_{i+1}+\dots+l_j)-(j-i)$`

cost of line = (extra spaces)<sup>3</sup>  
total cost = sum of costs of all lines except last

Find an arrangement which minimizes cost ?

---

### why cubes ?

:bulb: idea behind cost function is to balance the spaces among lines

consider 3 lines where:
1. first line has `3` extra spaces and rest has `0`. cost = `$3^3+0^3 = 27$`
2. each line has `1` extra space. cost = `$1^3+1^3 = 2$`

thus using **cubes** favors arrangement-2 over arrangement-1

---

### Why greedy does not work ?

try to put as many words as possible in each line

consider input "aaa bb cc ddddd" and m=6

* greedy:

  `$\left.
  \begin{array}{l}
  aaa\;bb\\
  cc    \\
  ddddd 
  \end{array}
  \right\} \text{cost} = 0^3+4^3 = 64
  $`
* optimal:

  `$\left.
  \begin{array}{l}
  aaa\\
  bb\;cc  \\
  ddddd 
  \end{array}
  \right\} \text{cost} = 3^3+1^3 = 28
  $`

---

Optimal Substructure:
> Let us say in optimal solution first line ends at `$l_i$`, then arrangement 
> of words from `$i+1$` to `$n$` in that optimal solution must be optimal

let `l[i][j]` is length of line with words `i...j`

`$l[i][j] = \sum\limits_{k=i}^{k=j}{l_k}-(j-i)$`

let `lc[i][j]` is cost of line with words `i...j`

`$lc[i][j]=\begin{cases}
\infty & \text{if $l[i][j]>m$} & \text{# won't fit in line} \\
0 & \text{if $j=n$} & \text{# last line} \\
(m-l[i][j])^3
\end{cases}$`

let `c[i]` is min cost of arranging words `i...n`

`$c[i]=\begin{cases}
0 & \text{if $i=n$} \\
min(lc[i][k]+c[k+1]) & \text{for $k=i$ to $n$}
\end{cases}$`

answer is `c[1]`

```java
int wordWrap(int l[n], int m) {
    int c[n], b[n];
    c[n-1]=0; b[n-1]=n
    for(int i=n-2; i>=0; i--) {
        c[i] = ∞;
        int len = -1;
        for(int k=i; k<n; k++) {
            len += 1 + l[k]; / space + word
            if(len>m)
                break;
            lc = k==n-1 ? 0 : Math.pow(m-len, 3);
            int v = lc + c[k+1];
            if(v<c[i]) {
                c[i] = v;
                b[i] = k;
            }
        }
    }
    printLines(p);
    return c[0];
}

void printLines(int p[n]) {
    int i = 0;
    while(i<n) {
        println(i, "to", p[i]);
        i = p[i]+1;
    }
}
```

Running Time: `$O(n^2)$`

---

### References

* Problem 15-2 from [CLRS Second Edition](https://isbnsearch.org/isbn/0262032937)
* <http://sites.math.rutgers.edu/~sk1233/courses/algorithms-S12/hw2-sols.pdf>
* <http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/>
