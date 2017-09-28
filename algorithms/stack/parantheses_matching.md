# Parantheses Matching

```
(A+B)*C - nested correctly
)A+B(*C - nested incorrectly
A+B(    - nested incorrectly
```

### If expression contains only one type of paranthesis

* scan expression from left to right:
    * when left parenthesis encountered increment `depth`
    * when right parenthesis encountered decrement `depth`
        * if `depth<0`, it is not balanced.
* at end of expression if `depth!=0`, it is not balanced.

```java
boolean isNestedCorrectly(String expr) {
    int depth = 0;
    for(char ch: expr) {
        if(isLeftParenthesis(ch))
            depth++;
        else if(isRightParenthesis(ch)) {
            depth--;
            if(depth<0)
                return false;
        }
    }
    return depth==0;
}
```

### If expression contain more than one type of parenthesis

* scan expression from left to right:
    * when left parenthesis encountered, push into stack.
    * when right parenthesis encountered, pop from stack and check that the parenthesis match.
* at end of expression stack should be empty.

```java
boolean isNestedCorrectly(String expr) {
    Stack stack = new Stack();

    for(char ch: expr) {
        if(isLeftParenthesis(ch))
            stack.push(ch);
        if(isRightParenthesis(ch)) {
            if(stack.isEmpty())
                return false;
            else if(getRightParenthesis(stack.pop())!=ch)
                return false;
        }
    }
    return stack.isEmpty();
}
```
