# Nearest Smaller Values

for each position in a sequence of numbers, search among the previous positions for the last position that contains a smaller value

`$\begin{matrix}
input=& 0& 8& 4& 12& 2& 10& 6& 14& 1& 9& 5& 13& 3& 11& 7& 15 \\
output=& —& 0& 0& 4& 0& 2& 2& 6& 0& 1& 1& 5& 1& 3& 3& 7 \\
\end{matrix}$`

---

* scan input from left to right and push into stack. before pushing:
    * pop numbers greater than current number
    * top of stack gives nearest smaller value of current number

```java
int[] nearestSmallerValues(int a[]) {
    int b[a.length];
    Stack stack = new Stack();
    for(int i=0; i<a.length; i++) {
        while(!stack.isEmpty() && stack.top()>=a[i])
            stack.pop();
        b[i-1] = stack.isEmpty() ? -1 : stack.peek();
        stack.push(a[i]);
    }

    return b;
}
```

### References

* <https://en.wikipedia.org/wiki/All_nearest_smaller_values>
