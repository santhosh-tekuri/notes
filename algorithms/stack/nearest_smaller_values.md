# Nearest Smaller Values

for each position in a sequence of numbers, search among the previous positions for the last position that contains a smaller value

compute array, which contains index of nearest smallest value or `-1` if no such index exists ?

$\begin{array}{r|rrr}
index & 0& 1& 2& 3& 4& 5& 6& 7& 8& 9& 10& 11& 12& 13& 14& 15 \\
\hline
input& 0& 8& 4& 12& 2& 10& 6& 14& 1& 9& 5& 13& 3& 11& 7& 15 \\
output& -1 & 0& 0& 2& 0& 4& 4& 6& 0& 8& 8& 10& 8& 12& 12& 14 \\
\hline
input& 1& 6& 4& 10& 2& 5 \\
output& -1& 0& 0&  2& 0& 4 \\
\hline
input& 1& 3& 0& 2& 5 \\
output& -1& 0& -1& 2& 3
\end{array}$

---

* scan input from left to right and push into stack. before pushing:
    * pop numbers greater than current number
    * top of stack gives nearest smaller value of current number

```java
int[] nearestSmallerValues(int a[n]) {
    int b[n];
    Stack stack = new Stack();
    for(int i=0; i<n; i++) {
        while(!stack.isEmpty() && a[stack.peek()]>=a[i])
            stack.pop();
        b[i] = stack.isEmpty() ? -1 : stack.peek();
        stack.push(i);
    }
    return b;
}
```
::: src
[:icon-java:](src/NearestSmallerValuesV1.java)
:::
each element is pushed once and popped at most once

Time Complexity: $O(n)$  
Space Complexity: $O(n)$

---

### Without Stack

::: tip box
all elements between nearest smaller element and current element are not smaller than current element  
i.e. $\text{$a[j] \ge a[i]$ for $j=b[i]+1$ to $i-1$}$
:::

```java
int[] nearestSmallerValues(int a[n]) {
    int b[n];
    for(int i=0; i<n; i++) {
        int j=i-1;
        while(j!=-1 && a[j]>=a[i])
            j = b[j];
        b[i] = j;
    }
    return b;
}
```

::: src
[:icon-java:](src/NearestSmallerValuesV2.java)
:::

Time Complexity: $O(n)$  
Space Complexity: $O(1)$

---

### References

* <https://en.wikipedia.org/wiki/All_nearest_smaller_values>
