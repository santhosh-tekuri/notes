# Min Jumps to reach End

Given array `a[n]` of non-negative integers, where each element represents max #steps that can be made forward from that element

Find minimum number of jumps to reach end of array ?

Note that if element is `0`, you can't move from that element

`$\begin{align}
{\color{red}1, \color{red}3, 5, \color{red}8, 9, 2, 6, 7, 6, 8, \color{red}9} &\to 3 \\
{\color{red}5, 4, 1, 1, 1, \color{red}3, \color{red}5} &\to 2
\end{align}$`

---

let `m[i]` is min jumps to reach end from `i`

`$m[i]=\begin{cases}
0 & \text{if $i=n-1$} & \text{# already reached end} \\
\infty & \text{if $a[i]=0$} & \text{# can't move}\\
1 & \text{if $i+a[i]\geq n-1$} & \text{# jump directly to end}\\
min(1+m[i+k]) & \text{for $k=1$ to $a[i]$}
\end{cases}$`

answer is `$\;m[0]$`

```java
int minJumps(int a[n]) {
    int m[n], b[n];
    m[n-1] = 0;
    b[n-1] = n-1;
    for(int i=n-2; i>=0; i--) {
        if(a[i]==0)
            m[i] = ∞;
        else if(i+a[i]>=n-1) {
            m[i] = 1;
            b[i] = n-1;
        } else {
            m[i] = ∞;
            for(int k=1; k<=a[i]; k++) {
                if(m[i+k]<m[i]) {
                    m[i] = m[i+k];
                    b[i] = i+k;
                }
            }
            m[i]++;
        }
    }
    if(m[0]!=∞)
        printPath(b);
    return m[0];
}

void printPath(int b[n]) {
    int i = 0;
    while(i!=n-1) {
        print(i, " ");
        i = b[i];
    }
    println(n-1);
}
```
`@src(src/MinJumpsV1.java)`

Running Time: `$O(n^2)$`

---

### Linear Time

let `m[i]` is min jumps from `0` to `i`

`$m[i]=\begin{cases}
0 & \text{if $i=0$} \\
min(\\
\;\;\;\infty, \\
\;\;\;m[k]+1 & \text{for $k=0$ to $i$, if $k+a[k]>=i$} \\
)
\end{cases}$`

let us see example:

`$\begin{array}{r|rr}
i & 0 & 1 & 2 & 3 & 4 & 5 & 6 & 7 & 8 & \dots \\
\hline
a & 3 & 4 & 2 & 3 & 1 & 2 & 1 & 3 & 5 & \dots \\
maxReach\;(i+a[i]) & 3 & 5 & 4 & 6 & 5 & 7 & 7 & 10 & 13 & \dots \\
m & 0 & 1 & 1 & 1 & 2 & 2 & 2 & 3 & 4 & \dots
\end{array}$`

notice that:
* values in `m[]` are in sorted order
* `maxReach[i]` can be computed in constant time
* we know `m[0]=0` always
* `maxReach[0]=3` → we can fill `1`s in `m[1...3]`
* now take max of `maxReach[1...3]`. it is 6 → we can fill `2`s in `m[4...6]`
* and so on...

this approach can be done in linear time. also we can compute `m[]` values on the fly

```java
int minJumps(int a[n]) {
    int i=0, m=0, maxReach=a[0];
    println("position 0");
    if(n!=1) {
        while(true) {
            m++;
            if(maxReach>=n-1)
                break;
            if(i==maxReach)
                return ∞; // impossible
            int v=0, maxk=0;
            for(int k=i+1; k<=maxReach; k++) {
                if(k+a[k]>v) {
                    v = k+a[k];
                    maxk = k;
                }
            }
            println("position", maxk);
            i = maxReach;
            maxReach = v;
        }
        println("position", n-1);
    }
    return m;
}
```
`@src(src/MinJumpsV2.java)`

Time Complexity: `$O(n)$`  
Space Complexity: `$O(1)$`

---

### References

* <http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/>
* <http://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/>
