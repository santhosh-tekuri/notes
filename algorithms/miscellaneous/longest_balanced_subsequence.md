# Longest Balanced Subsequence

`$
\color{red}{()()}) \to 4 \\
\color{red}{()}(((((\color{red}{()} \to 4
$`

---

```java
int lbs(char s[]) {
    int open = 0;
    int len = 0;
    for(char ch: s) {
        if(ch=='(')
            open++;
        else {
            open--;
            len += 2;
        }
    }
    return len;
}
```

Running Time: `$O(n)$`

if input contains more than one type of parantheses, use [Dynamic Programming](../dynamic_programming/longest_balanced_subsequence.md)

---

### References

* <http://www.geeksforgeeks.org/length-longest-balanced-subsequence/>
