# Longest Parantheses Matching

$
(((\color{red}{()} \\
))))\color{red}{()} \\
(()(\color{red}{(())} \\
\color{red}{()()})(((()) \\
\color{red}{()()()}
$

---

let us mathematically define what balanced mean ?
* smallest balananced is `()`
* if `A` is balanced, then `(A)` is also balanced
* if `A` and `B` are balanced, then `AB` is also balanced

let us use stack:
* for each character in expression:
    * if it is `(`, push into stack
    * otherwise (obviously it is `)`)
        * if stack is empty, means no matching `(` found
        * else pop from stack, which gives matching `(`
            * can we find longest balanced ending at current position ?
            * notice that stack at this point, contains all `(`s, for which matching `)` not found
                * after consuming `((A`, stack contains `((`
                    * longest balanced is `current_index - peek_index`  
                      let us call `peek_index` as `mark`  
                      i.e. longest balanced start after `mark`  
                      we can say, longest balanced is `current_index - mark`, where `mark=peek_index`
                * what if stack is empty ? what will be `mark` in such scenario ?  
                  following are two cases, where this happens:
                    * `A` or `ABC` : here `mark=-1` gives longest balanced
                        * i.e. intialize `mark` as `-1`
                    * `)A` or `)))A` : here `mark` should be last unmatching `)`
                        * i.e. when we encounter unmatching `)`, save current index as `mark`

```java
int longestBalanced(char s[n]) {
    int longest = 0;

    Stack stack;
    int mark = -1;
    for(int i=0; i<n; i++) {
        if(s[i]=='(')
            stack.push(i);
        else {
            if(stack.isEmpty())
                mark = i; // last unmatching ')`, so update mark
            else {
                stack.pop();
                int len = i - (stack.isEmpty() ? mark : stack.peek());
                longest = max(longest, len);
            }
        }
    }

    return longest;
}
```

Time Complexity: `$O(n)$`  
Space Complexity: `$O(n)$`

---

### Without Stack

in above algorithm, we are finding longest balanced ending at current character using `current_index - mark`

`mark` value comes from:
* `-1` initially
* index of last unmatching `)`
* peek index of stack
    * only this case needs stack
    * this case happens after parsing `(A` or `(((A`
    * obviously, we can't get peek index, without using stack
    * tricky solution is, if we scan from right to left, then we can find length of `A` in above expressions
        * but we need to skip finding longest balanced in this case, while scanning from left to right
        * to identify this case, we can maintain `depth` i.e size of stack
            * `depth>0` identifies this case

```java
int longestBalanced(char s[n]) {
    int longest = 0;

    int mark = -1;
    int depth = 0;
    for(int i=0; i<n; i++) {
        if(s[i]=='(')
            depth++;
        else {
            if(depth==0)
                mark = i;
            else {
                depth--;
                if(depth==0)
                    longest = max(longest, i-mark);
            }
        }
    }

    mark = n;
    depth = 0;
    for(int i=n-1; i>=0; i--) {
        if(s[i]==')')
            depth++;
        else {
            if(depth==0)
                mark = i;
            else {
                depth--;
                if(depth==0)
                    longest = max(longest, mark-i);
            }
        }
    }

    return longest;
}
```

Time Complexity: `$O(n)$`  
Space Complexity: `$O(1)$`

we can refactor two `for` loops, into a single for loop:

```java
int longestBalanced(char s[]) {
    return max(
        longestBalanced(s, 0, n, +1, '('),
        longestBalanced(s, n-1, -1, -1, ')')
    );
}

int longestBalanced(char s[], int from, int until, int step, char ch) {
    int longest = 0;
    int mark = from-step;
    int depth = 0;
    for(int i=from; i!=until; i+=step) {
        if(s[i]==ch)
            depth++;
        else {
            if(depth==0)
                mark = i;
            else {
                depth--;
                if(depth==0)
                    longest = max(longest, abs(i-mark));
            }
        }
    }
    return longest;
}
```

this problem can also be [solved](../dynamic_programming/longest_valid_parentheses.md) using dynamic programming

---

### References

* <https://leetcode.com/articles/longest-valid-parentheses/>
* <http://n00tc0d3r.blogspot.in/2013/04/longest-valid-parentheses.html>
