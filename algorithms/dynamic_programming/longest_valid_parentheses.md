# Longest Valid Parentheses

`$
(((\color{red}{()} \\
))))\color{red}{()} \\
(()(\color{red}{(())} \\
\color{red}{()()})(((()) \\
\color{red}{()()()}
$`

---

let `L[j]` is length of longest valid paranthesis ending at `i`

`$L[j]=\begin{cases}
0 & \text{if $j=0$} \\
0 & \text{if $s[j]='('$} \\
\left.
\begin{array}{l}
L[j-2]+2 & \text{if $s[j-1]='('$} \\
L[j−1]+L[j−L[j−1]−2]+2 & \text{if $s[j-1]=')'$ and $s[j−L[j−1]−1]='('$}
\end{array}
\right\} & \text{if $s[j]=')'$}
\end{cases}$`

&nbsp;

* case 1: if `j=0`
    * string of length 1 is not balanced
* case 2: if `s[j]='('`
    * balanced string does not end with `(`
* case 3: if `s[j]=')'`
    * case 3a: if `s[j-1]='('`
        * string looks like `$\_\_\_\_\color{red}{\_\_\_\_}\color{blue}{()}$`
        * `$\color{red}{L[j-2]}+\color{blue}2$`
    * case 3b: if `s]j-1]=')'`
        * string looks like `$\_\_\_\_\color{lime}{\_\_\_\_}\color{green}(\color{red}{\_\_\_\_)}\color{blue})$`
        * `$\color{lime}{L[j-L[j-1]-2]}+\color{green}1+\color{red}{L[j-1]}+\color{blue}1$`

note to check negative array indices and exclude them

```java
int longestBalanced(char s[n]) {
    int longest = 0;
    int L[n];
    for(int j=1; j<n; j++) {
        if(s[j]==')') {
            if(s[j-1]=='(') {
                L[j] = 2;
                if(j-2>=0)
                    L[j] += L[j-2];
            } else {
                L[j] = L[j-1] + 2;
                if(j-L[j-1]-2>=0)
                    L[j] += L[j-L[j-1]-2];
            }
            longest = max(longest, L[j]);
        }
    }
    return longest;
}
```

Time Complexity: `$O(n)$`  
Space Complexity: `$O(n)$`

this problem can be [solved easily](../stack/longest_parantheses_matching.md) using stack

---

### References

* <https://leetcode.com/articles/longest-valid-parentheses/#approach-2-using-dynamic-programming-accepted>
* <https://www.quora.com/How-do-I-solve-longest-valid-parenthesis-problem-using-dynamic-programming>
