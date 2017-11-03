# Moving on a Checkerboard

Given `$n\times n$` checkerboard, move from bottom-most row to top-most row  

you can only move:
* UP
* UP-LEFT, if you are not in leftmost column
* UP-RIGHT, if you are not in rightmost column

you can start from any square in bottom-most row and reach any square in top-most row  
on moving from square `(x,y)`, you will earn `p[x][y]` dollars, it can be -ve

Find path to earn max dollars?

---

let `m[i][j]` is maximum dollars earnable to reach square `(i,j)`

`$m[i][j]=\begin{cases}
0 & \text{if $i=1$} & \text{# we start from $(i,j)$} \\
max(\\
\;\;\;m[i-1][j] + p[i-1][j], && \text{# with last move UP} \\
\;\;\;m[i-1][j-1] + p[i-1][j-1], & \text{if $j>1$} & \text{# with last move UP-LEFT} \\
\;\;\;m[i-1][j+1] + p[i-1][j+1] & \text{if $j<n$} & \text{# with last move UP-RIGHT} \\
)
\end{cases}$`

answer is `$\;\;max(d[n][\dots])$`

```java
int maxDollars(int p[n][n]) {
    int m[n][n];
    int b[n][n]; // b[i][j] gives the column of prev square from which we moved to (i,j)

    // m[0][...] already contains zeros. so no need to initialize them

    for(int i=1; i<n; i++) {
        for(int j=0; j<n; j++) {
            m[i][j] = m[i-1][j] + p[i-1][j];
            w[i][j] = j;
            if(j>0) {
                int v = m[i-1][j-1] + p[i-1][j-1];
                if(v>m[i][j]) {
                    m[i][j] = v;
                    w[i][j] = j-1;
                }
            }
            if(j<n-1) {
                int v = m[i-1][j+1] + p[i-1][j+1];
                if(v>m[i][j]) {
                    m[i][j] = v;
                    w[i][j] = j+1;
                }
            }
        }
    }

    int maxj = 0;
    for(int j=1; j<n; j++) {
        if(d[n][j]>d[n][maxj])
            maxj = j;
    }

    printPath(w, n-1, maxj);
    return d[n-1][maxj];
}

void printPath(int w[n][n], int i, int j) {
    if(i>0)
        printPath(w, i-1, w[i][j]);
    println(i, j);
}
```

Running Time: `$O(n^2)$`

---

### References

* Problem 15-7 from [CLRS Second Edition](https://isbnsearch.org/isbn/0262032937)
* <http://www.8bitavenue.com/2011/12/dynamic-programming-moving-on-a-checkerboard/>
