# Longest Arithmetic Progression (LAP)

Given sequence of sorted numbers `$X=x_1, x_2, \dots, x_n$`, 
find longest subsequence whose elements are in arithmetic progression ?

---

### Brute Force

* consider all possible pairs (`$n^2$` pairs)
* for each pair, find LAP (`$O(n)$` time)

Running Time: `$O(n^3)$`

---

recommended to solve [Arithmetic Progression of size 3](../miscellaneous/arithmetic_progression_of_size3.md) before this

let `L[i][j]` is length of LAP with `$x_i$` and `$x_j$` as first two starting elements, where `i<j`

`$L[i][j]=\begin{cases}
2 & \text{if $j=n$} \\ 
max( \\
\;\;\;2, \\
\;\;\;1+L[j][k] & \text{for k=$j+1$ to $n$, if $2x_j=x_i+x_k$} \\
)
\end{cases}$`

answer is `max(L[][])`

compute columns in `L[][]` from right to left. right most column is always `2`

three are `$n^2$` entries in `L[][]` and each entry takes `$O(n)$` time. so running time will be `$O(n^3)$`

but we can use the trick in above problem, to make it `$O(n^2)$`:
* fix `j`
* find values of `i` and `k` in `$O(n)$` time

```java
int lap(int x[n]) {
    int L[n][n];

    for(int i=0; i<n; i++)
        L[i][n-1] = 2;

    int ans = 2;
    for(int j=n-2; j>0; j--) {
        int i=j-1, k=j+1;
        while(i>=0 && k<n) {
            if(x[i]+x[k]<2*a[j])
                k++;
            else if(x[i]+x[k]>2*a[j]) {
                L[i][j] = 2;
                i--;
            } else {
                L[i][j] = 1+L[j][k];
                ans = max(ans, L[i][j]);
                i--; k++;
            }
        }

        // if the loop was stopped due to `k>=x.length`
        // fill remaining entries in j as 2
        while(i>=0) {
            L[i][j] = 2;
            i--;
        }
    }

    return ans;
}
```

Time Complexity: `$O(n^2)$`  
Space Complexity: `$O(n^2)$`

---

### References

* <http://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/>
* <http://jeffe.cs.illinois.edu/pubs/pdf/arith.pdf>
