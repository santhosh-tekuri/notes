# Maximum Sum Subarray

Given sequence of numbers `$X = x_1, x_2, \dots, x_n$`, find a contiguous subsequence
`$x_i, x_{i+1}, \dots, x_j$` for which sum of elements in subsequence is maximized

`$
[\color{red}{1, 2, 4, 3}] \to 10 \text{ # sum of all elements since they are +ve} \\
[-2, -3, \color{red}{4, -1, -2, 1, 5}, -3] \to 7 \\
[-2, 1, -3, \color{red}{4, -1, 2, 1}, -5, 4] \to 6 \\
[\color{red}{2, -1, 2, 3, 4}, -5] \to 10
$`

---

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

### Minimum Sum Subarray

simply invert sign of each element and run above algorithm

```java
int maxSumCircularSubarray(int x[n]) {
    int m=-x[0], ans=m;
    for(int j=1; j<n; j++) {
        m = max(m-x[j], -x[j]);
        ans = max(ans, m);
    }
    return ans;
}
```

---

### Maximum Sum Subarray in Circular Array

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
    for(int j=1; j<n; j++) {
        m1 = max(m1+x[j], x[j];
        ans1 = max(ans1, m1);

        m2 = max(m2-x[j], -x[j]);
        ans2 = max(ans2, m2);
    }
    return max(ans1, ans2);
}
```

Running Time: `$O(n)$`

---

### References

* Problem 1 in <https://people.cs.clemson.edu/~bcdean/dp_practice/>
* <https://en.wikipedia.org/wiki/Maximum_subarray_problem>
