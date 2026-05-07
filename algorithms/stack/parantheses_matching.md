# Parantheses Matching

```
() - nested correctly
)( - nested incorrectly
(  - nested incorrectly
```

---

### If expression contains only one type of paranthesis

* scan expression from left to right:
    * when open parenthesis encountered increment `depth`
    * when close parenthesis encountered decrement `depth`
        * if `depth<0`, it is not balanced.
* at end of expression if `depth==0`, it is balanced.

```java
boolean isBalanced(String expr) {
    int depth = 0;
    for (char ch: expr) {
        if (isOpen(ch))
            depth++;
        else {
            depth--;
            if(depth<0)
                return false; // no matching open parenthesis
        }
    }
    return depth==0;
}
```

---

### If expression contains more than one type of parenthesis

* scan expression from left to right:
    * when open parenthesis encountered, push into stack.
    * when close parenthesis encountered, pop from stack and check that the parenthesis match.
* at end of expression stack should be empty.

```java
boolean isBalanced(String expr) {
    Stack stack = new Stack();

    for (char ch: expr) {
        if(isOpen(ch))
            stack.push(ch);
        else {
            if (stack.isEmpty())
                return false;
            else if(getOpen(ch)!=stack.pop())
                return false;
        }
    }
    return stack.isEmpty();
}
```
