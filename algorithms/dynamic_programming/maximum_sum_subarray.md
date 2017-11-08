# Maximum Sum Subarray

Problem 1 in <https://people.cs.clemson.edu/~bcdean/dp_practice/>  
<https://en.wikipedia.org/wiki/Maximum_subarray_problem>

Given sequence of numbers `$X = x_1, x_2, \dots, x_n$`, find a contiguous subsequence
`$x_i, x_{i+1}, \dots, x_j$` for which sum of elements in subsequence is maximized

`$
[\color{red}{1, 2, 4, 3}] \to 10 \text{ # sum of all elements since they are +ve} \\
[-2, -3, \color{red}{4, -1, -2, 1, 5}, -3] \to 7 \\
[-2, 1, -3, \color{red}{4, -1, 2, 1}, -5, 4] \to 6 \\
[\color{red}{2, -1, 2, 3, 4}, -5] \to 10
$`

---
Optimal Substructure:
> if `$x_i,\dots,x_j$` is maximum sum subarray ending at `$x_j$`, then `$x_i,\dots,x_{j-1}$` is maximum sum subarray ending at `$x_{j-1}$`

let `m[j]` is maximum sum over all subarrays ending at `$x_j$`

`$m[j]=\begin{cases}
x_1 & \text{if $j=1$} \\
max(m[j-1]+x_j,\;x_j) & \text{if $j>1$}
\end{cases}$`

answer is `max(m[])`

:bulb: any value in `m[]` depends only on its previous value. so we can compute `m[]` on the fly

```java
int maxSumSubarray(int x[n]) {
    int m = x[0], mStart=0;
    int ans=m, ansStart=0, ansEnd=0;
    for(int j=1; j<n; j++) {
        if(m+x[j]>x[j])
            m = m+x[j];
        else{
            m = x[j];
            mStart = j;
        }

        if(m>ans) {
            ans = m;
            ansStart = mlo;
            ansEnd = j;
        }
    }
    println("subarray", ansStart, "to" ansEnd);
    return ans;
}
```

without tracking subarray location:

```java
int maxSumSubarray(int x[n]) {
    int m=x[0], ans=m;
    for(int j=1; j<n; j++) {
        m = max(m+x[j], x[j]);
        ans = max(ans, m);
    }
    return ans;
}
```

Running Time: `$O(n)$`

:bulb: this is actually [Kadane's Algorithm](https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane.27s_algorithm_.28Algorithm_3:_Dynamic_Programming.29)

---

### Maximum Sum Subarray with at least K elements

<http://www.geeksforgeeks.org/largest-sum-subarray-least-k-numbers/>

`$
k=2, [-4, \color{red}{-2, 1}, -3] \to -1 \\
k=2, [\color{red}{1, 1, 1, 1, 1, 1}] \to 6 \\
k=5, [\color{red}{5, 7, -9, 3, -4, 2, 1, -8, 9, 10}] \to 19 \\
k=2, [-100, \color{red}{51, 51}, -102, 1, 100, -1000] \to 102
$`

let `m[j]` is maximum sum over all subarrays of at least `k` elements  ending at `$x_j$`

`$m[j]=\begin{cases}
sum(x_1 \dots x_k) & \text{if $j=k$} \\
max(m[j-1]+x_j,\;sum(x_{j-k+1} \dots x_j) & \text{if $j>k$}
\end{cases}$`

answer is `max(m[k...n])`

:bulb: we can use sliding window to maintain sum of `k` elements as we move from left to right

```java
int maxSumSubarrayK(int x[n], int k) {
    // sum of sliding window of size k
    int sum = 0;
    for(int i=0; i<k; i++)
        sum += x[i];
    
    int m=sum, ans=m;
    for(int j=k; j<n; j++) {
        sum += x[j] - x[j-k];
        m = max(m+x[j], sum);
        ans = max(ans, m);
    }
    return ans;
}
```

Running Time: `$O(n)$`

---

### Minimum Sum Subarray

simply invert sign of each element and run above algorithm. do not forget to negate the original answer

```java
int minSumSubarray(int x[n]) {
    int m=-x[0], ans=m;
    for(int j=1; j<n; j++) {
        m = max(m-x[j], -x[j]);
        ans = max(ans, m);
    }
    return -ans;
}
```

---

### Maximum Sum Subarray in Circular Array

<http://www.geeksforgeeks.org/maximum-contiguous-circular-sum/>

`$
[\color{red}{10} ,-7, 5, -7, \color{red}{5, 7}] \to 22 \\
[\color{red}{2, 1}, -5, 4, -3, 1, -3, \color{red}{4, -1}] \to 6 \\
[\color{red}{8, -8, 9, -9, 10}, -11, \color{red}{12}] \to 22 \\
[\color{red}{10}, -3, -4, \color{red}{7, 6, 5, -4, -1}] \to 23 \\
[\color{red}{-1, 40}, -14, \color{red}{7, 6, 5, -4, -1}] \to 52
$`

consider maximum sum subarray is `x[i...j]`. there are two cases
1. `i<=j` i.e no wrapping is there
    * above algorithm works
2. `i>j` i.e wrapping is there
    * change wrapping to non-wrapping
    * wrapping of contributing elements implies non-wrapping of non-contributing elements. 
    * to find them, invert sign of each element and run above algorithm. 
    * sum of contributing elements = total sum - sum of non-contributing elements

```java
int maxSumCircularSubarray(int x[n]) {
    int ans1 = maxSumSubarray(x);

    int total = 0;
    for(int i=0; i<n; i++) {
        total += x[i];
        x[i] = -x[i];
    }
    int ans2 = total + maxSumSubarray(x); // notice addition

    return max(ans1, ans2);
}
```

without changing array:

```java
int maxSumCircularSubarray(int x[n]) {
    int m1=x[0], ans1=m1;
    int m2=-x[0], ans2=m2;
    int total = x[0];
    for(int j=1; j<n; j++) {
        total += x[j];

        m1 = max(m1+x[j], x[j]);
        ans1 = max(ans1, m1);

        m2 = max(m2-x[j], -x[j]);
        ans2 = max(ans2, m2);
    }
    return max(ans1, total+ans2);
}
```

Running Time: `$O(n)$`

---

### Maximum Sum Balanced Subarray

<https://www.codechef.com/problems/SUBARRAY>  
<https://stackoverflow.com/a/28528898/104018>

Find the maximum sum subarray in the integer array such that the 
corresponding subarray in the character array has balanced parenthesis

paranthesis used are: `[]` `()` `{}` `<>`

`$
"()()" [-1, -2, \color{red}{3, 4}] \to 7 \\
"(()]" [-1, \color{red}{-2, 3}, 4] \to 1 \\
"[\{]\{" [1, 2, 3, 4] \to 0
$`

---

### Maximum Sum with no adjacent elements

<http://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/>  
<https://stackoverflow.com/questions/4487438/maximum-sum-of-non-consecutive-elements>

`$
[-1, -2, \color{red}3] \to 3 \\
[5, \color{red}5, 10, \color{red}{100}, 10, \color{red}5] \to 110 \\
[\color{red}1, 2, \color{red}3] \to 4 \\
[1, \color{red}{20}, 3] \to 20
$`

let `m[i]` is max sum with no adjacent elements of `$x_1 \dots x_i$`

`$m[i]=\begin{cases}
x_1 & \text{if $i=1$} \\
max(x_1, \;x_2) & \text{if $i=2$} \\
max(m[i-1], \;m[i-2]+x_i, \;x_i) & \text{if $i>2$}
\end{cases}$`

answer is `m[n]`

:bulb: any value in `m[]` depends only on previous two values. so we can compute m[] on the fly

```java
int maxSumNoAdjacent(int x[n]) {
    int mPrev=x[0], m=max(x[0], x[1]);
    for(int i=2; i<n; i++) {
        int cur = max(m, mPrev+x[i], x[i]);
        mPrev = m;
        m = cur;
    }
    return m;
}
```

Running Time: `$O(n)$`

:bulb: if input contains no -ve numbers, then case3 (`if i>2`) can be simplified: `$max(m[i-1], \;m[i-2]+x_i)$`

---

### Maximum Sum with no adjacent elements in Circular Array

in circular array `$[x_1, x_2, \dots, x_n]$`, `$\;\;x_1$` and `$x_n$` are adjacent

answer is:

`$max( \\
\;\;maxSumNoAdjacent(x_1,\dots,x_{n-1}), \\
\;\;maxSumNoAdjacent(x_2,\dots,x_n) \\
)$`

### Bad Neighbors

<https://community.topcoder.com/stat?c=problem_statement&pm=2402&rd=5009>

The old song declares "Go ahead and hate your neighbor", and the residents of 
Onetinville have taken those words to heart. Every resident hates his next-door 
neighbors on both sides. Nobody is willing to live farther away from the town's 
well than his neighbors, so the town has been arranged in a big circle around 
the well. Unfortunately, the town's well is in disrepair and needs to be restored. 
You have been hired to collect donations for the Save Our Well fund.

Each of the town's residents is willing to donate a certain amount, as specified 
in the `int[]` donations, which is listed in clockwise order around the well. 
However, nobody is willing to contribute to a fund to which his neighbor has also 
contributed. Next-door neighbors are always listed consecutively in donations, 
except that the first and last entries in donations are also for next-door neighbors. 

You must calculate and return the maximum amount of donations that can be collected.

`$
[\color{red}{10}, 3, \color{red}2, 5, \color{red}7, 8] \to 19 \\
[11, \color{red}{15}] \to 15 \\
[\color{red}7, 7, \color{red}7, 7, \color{red}7, 7, 7] \to 21 \\
[1, 2, 3, 4, 5, 1, 2, 3, 4, 5] \to 16 \\
[94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, \\ 
 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, \\
 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72] \to 2926
$`

---

### Max Sum with no 3 consecutive numbers

`$m[i]=\begin{cases}
x_1 & \text{if $i=1$} \\
max(x_1, \;x_2, \;x_1+x_2) & \text{if $i=2$} \\
max(m[2], \;m[1]+x_3, \;x_3, x_2+x+3)& \text{if $i=3$} \\
max( \\
\;\;m[i-1], \\
\;\;m[i-2]+x_i, \;x_i, \\
\;\;m[i-3]+x_{i-1}+x_i, \;x_{i-1}+x_i \\
)
\end{cases}$`

answer is `m[n]`

if input contains no -ve numbers:

`$m[i]=\begin{cases}
x_1 & \text{if $i=1$} \\
x_1+x_2 & \text{if $i=2$} \\
max(x_1+x_2, \;x_1+x_3, \;x_2+x_3)& \text{if $i=3$} \\
max( \\
\;\;m[i-1], \\
\;\;m[i-2]+x_i, \\
\;\;m[i-3]+x_{i-1}+x_i \\
)
\end{cases}$`

---

### Maximum Product Subarray

<http://www.geeksforgeeks.org/maximum-product-subarray/>

Given sequence of numbers `$X = x_1, x_2, \dots, x_n$`, find a contiguous subsequence
`$x_i, x_{i+1}, \dots, x_j$` for which product of elements in subsequence is maximized

`$
[\color{red}{6, -3, -10}, 0, 2] \to 180 \\
[-1, -3, -10, 0, \color{red}{60}] \to 60 \\
[-2, -3, 0, \color{red}{-2, -40}] \to 80
$`

Optimal Substructure:
> if `$x_i, x_{i+1}, \dots, x_{j-1}, x_j$` is max product subarray ending at `$x_j$`, then `$x_i, x_{i+1}, \dots, x_{j-1}$` must be
  * max product subarray ending at `$x_{j-1}$`, if `$x_j\geq 0$`
  * min product subarray ending at `$x_{j-1}$`, if `$x_j<0$`

let:
* `$L[j]$` is largest product subarray ending at `$x_j$`
* `$S[j]$` is smallest product subarray ending at `$x_j$`

`$L[j]=\begin{cases}
x_1 & \text{if $j=1$} \\
\left.
\begin{array}{l}
max( \\
\;\;x_j, \\
\;\;L[j-1]\times x_j, & \text{if $x_j\geq 0$} \\
\;\;S[j-1]\times x_j & \text{if $x_j<0$} \\
) \\
\end{array}
\right\} & \text{if $j>1$}
\end{cases}$`

`$S[j]=\begin{cases}
x_1 & \text{if $j=1$} \\
\left.
\begin{array}{l}
min( \\
\;\;x_j, \\
\;\;S[j-1]\times x_j, & \text{if $x_j\geq 0$} \\
\;\;L[j-1]\times x_j & \text{if $x_j<0$} \\
) \\
\end{array}
\right\} & \text{if $j>1$}
\end{cases}$`

:bulb: any value in `L[]` and `S[]`  depends only on its previous value. so we can compute them on the fly

```java
public int maxProductSubarray(int x[n]) {
    int L=x[0], S=x[0], ans=x[0];
    for(int j=1; j<n; j++) {
        int Lj = max(x[j], x[j]>=0 ? L*x[j] : S*x[j]);
        int Sj = min(x[j], x[j]>=0 ? S*x[j] : L*x[j]);
        L = Lj;
        S = Sj;
        ans = max(ans, L);
    }
    return ans;
}
```

Running Time: `$O(n)$`

---

### Maximum Sum Submatrix

<http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/>  
<https://prismoskills.appspot.com/lessons/Dynamic_Programming/Chapter_07_-_Submatrix_with_largest_sum.jsp>

`$
\begin{bmatrix}
1& 2& -1& -4& -20 \\
-8& \color{red}{-3}& \color{red}4& \color{red}2& 1 \\
3& \color{red}8& \color{red}{10}& \color{red}1& 3 \\
-4& \color{red}{-1}& \color{red}1& \color{red}7& -6
\end{bmatrix} \to 29
\;\;\;
\begin{bmatrix}
0& -2& -7& 0 \\
\color{red}9& \color{red}2& -6& 2 \\
\color{red}{-4}& \color{red}1& -4& 1 \\
\color{red}{-1}& \color{red}8& 0& -2
\end{bmatrix} \to 15
$`

fix top and bottom rows, convert values between them to array, and run kadane algorithm

`$\begin{array}{c|cc}
top & a & b & c & d \\
& e & f & g & h \\
bottom & i & j & k & l \\
& m & n & o & p
\end{array} \to \;\;[a+e+i, \;\;b+f+j, \;\;c+g+k, \;\;d+h+l]
$`

```java
int maxSumSubmatrix(int x[m][n]) {
    int ans = -∞;

    int tmp[n];
    for(int top=0; top<m; top++) {
        for(int i=0; i<n; i++)
            tmp[i] = 0;
        for(int bottom=top; bottom<m; bottom++) {
            for(int i=0; i<n; i++)
                tmp[i] += x[bottom][i];
            ans = max(ans, maxSumSubarray(tmp));
        }
    }

    return ans;
}
```

Running Time: `$O(m^2n)$`

:bulb: fix columns, if `m>n`
