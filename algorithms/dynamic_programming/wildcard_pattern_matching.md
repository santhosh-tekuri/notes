# Wildcard Pattern Matching

`?` matches any single character  
`*` matches any number of characters

---

Let `T[i][j]` = true if first `i` characters in text matches first `j` characters in pattern

* both text and pattern are empty
    * `T[0][0] = true`
* pattern is empty
    * `T[i][0] = false`
* text is empty
    * `T[0][j] = T[0][j-1] if pattern[j-1]='*'`
* both text and pattern not empty: `T[i][j]`
    * if last character in pattern `pattern[j-1]` is:
        * `case '?':  T[i-1][j-1]`
        * `case '*': T[i][j-1] || T[i-1][j-1]`
        * `default: T[i-1][j-1] && pattern[j-1]==text[i-1]`

```java
boolean match(String text, String pattern) {
    boolean T[text.length+1][pattern.length+1];

    T[0][0] = true;

    for(int j=1; j<=pattern.length; j++) {
        if(pattern.charAt(j-1)=='*')
            T[0][j] = T[0][j-1];
    }

    for(int i=1; i<=text.length; i++) {
        for(int j=1; j<=pattern.length; j++) {
            switch(pattern.charAt(j-1) {
            case '?': 
                T[i][j] = T[i-1][j-1];
                break;
            case '*': 
                T[i][j] = T[i][j-1] || T[i-1][j];
                break;
            default: 
                T[i][j] = T[i-1][j-1] && text.charAt(i-1)==pattern.charAt(j-1);
            }
        }
    }

    return T[text.length][pattern.length];
}
```

if `m` and `n` are lengths of text and pattern:
* Time Complexity: `$O(mn)$`
* Space Complexity: `$O(mn)$`

:bulb: current row in `T[][]` only depends on previous row. so further optimizations can be done as in [LCS](lcs.md)

### References

* <http://www.geeksforgeeks.org/wildcard-pattern-matching/>
