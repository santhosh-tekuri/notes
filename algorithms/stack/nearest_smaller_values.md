# Nearest Smaller Values

<https://en.wikipedia.org/wiki/All_nearest_smaller_values>

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

::: note box
if you carefully notice, you will realize that `b[]` is actually recording the peek of the stack at end
of `for` loop. this menas from `b[]` we can get stack by following chain of peek pointers
:::

```java
int[] nearestSmallerValues(int a[n]) {
    int b[n];
    for(int i=0; i<n; i++) {
        int j = i-1;
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

## Replace with Next Greater Element

<http://www.geeksforgeeks.org/next-greater-element/>

Given an array `a[]`, replace each element `a[i]` with `a[j]`
* where `j` is smallest index such that `j>i` and `a[j]>a[i]`
* if no such `j` exists, replace with `-1`

```bash
[4, 5, 2, 25] ➜  [5, 25, 25, -1]
[13, 7, 6, 12, 15] ➜ [15, 12, 12, 15, -1]
```

::: note box
this is variation of previous problem:
* in previous problem, we are finding left nearest smaller value
* in this problem, we have to find right nearest greater value

<!-- -->
:::
* scan array from left to right and push them(indexes) into stack
    * before pushing, pop values that are smaller than that
        * for index being popped, next greater element = current element

```java
void replaceWithNGE(int a[]) {
	Stack stack = new Stack();
	for(int i=0; i<a.length; i++) {
		while(!stack.isEmpty() && a[stack.peek()]<a[i])
			a[stack.pop()] = a[i];
		stack.push(i);
	}

	while(!stack.isEmpty())
		a[stack.pop()] = -1;
}
```
::: src
[:icon-java:](src/ReplaceWithNGE.java)
:::

Every index is pushed once and popped once. So running time is `$O(n)$`
