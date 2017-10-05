# Longest ZigZag Subsequence

### ZigZag Sequence

Sequence of numbers is called *ZigZag*, if the difference between successive numbers
strictly alternate between positive and negative. The first difference(if one exists)
may be either positive or negative. A sequence with fewer than two elements is trivially
a zig-zag sequence. It is also called *longest alternating sequence* or *longest oscillating
sequence*

Example: `{1, 7, 4, 9, 2, 5}`

### Problem

Given sequence of numbers `$X = x_1, x_2, \dots, x_n$`, find longest zigzag subsequence

Examples:
* `{1, 5, 4}` => `{1, 5, 4}`
* `{10, 22, 9, 33, 49, 50, 31, 60}`
    * `{10, 22, 9, 33, 31, 60}`
    * `{10, 22, 9, 49, 31, 60}`
    * `{10, 22, 9, 50, 31, 60}`

---

Let
* `incr[i]` is length of longest zigzag subsequence ending at `$x_i$`, with last step is increasing
* `decr[i]` is length of longest zigzag subsequence ending at `$x_i$`, with last step is decreasing

`$incr[i]=\begin{cases}
1 & if i=1 \\
max(decr[k]+1)\text{ $\forall$ $k<i$ and $x_k<x_i$} & if i>1 
\end{cases}$`

`$decr[i]=\begin{cases}
1 & if i=1 \\
max(incr[k]+1)\text{ $\forall$ $k<i$ and $x_k>x_i$} & if i>1 
\end{cases}$`

then length of longest zigzag subsequence = `$max(incr[], decr[])$`

```java
int LZS(int x[]) {
    int m[2][x.length]; // treat m[0][] as incr[], m[1][] as decr[]

    int maxi = 0, maxj = 0;
    for(int i=0; i<x.length; i++) {
        for(int k=0; k<i; k++) {
            if(x[k]<x[i]) {
                if(m[1][k]>m[0][i])
                    m[0][i] = m[1][k];
            }else if(x[k]>x[i]) {
                if(m[0][k]>m[1][i])
                    m[1][i] = m[0][k];
            }
        }
        m[0][i]++; m[1][i]++;
        if(m[0][i]>m[maxi][maxj]) {
            maxi = 0; maxj = i;
        } else if(m[1][i]>m[maxi][maxj]) {
            maxi = 1; maxj = i;
        }
    }

    return m[maxi][maxj];
}
```

Runnting Time: `$O(n^2)$`

:bulb: this problem can be solved in [linear time](../miscellaneous/lzs.md)

---

### References

* <http://www.geeksforgeeks.org/longest-zig-zag-subsequence/>
