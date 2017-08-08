# Parantheses Matching

```
(A+B)*C - nested correctly
)A+B(*C - nested incorrectly
A+B(    - nested incorrectly
```

### If expression contains only one type of parantheses

* scan expression from left to right:
    * when left parentheses encountered increment `depth`
    * when right parentheses encountered decrement `depth`
        * if `depth<0`, it is not balanced.
* at end of expression if `depth!=0`, it is not balanced.

```java
boolean isNestedCorrectly(String expr){
    int depth = 0;
    for(char ch: expr){
        if(isLeftParentheses(ch))
            depth++;
        else if(isRightParentheses(ch)){
            depth--;
            if(depth<0)
                return false;
        }
    }
    return depth==0;
}
```

### If expression contain more than one type of parentheses

* scan expression from left to right:
    * when left parentheses encountered, push into stack.
    * when right parentheses encountered, pop from stack and check that the parentheses match.
* at end of expression stack should be empty.

```java
boolean isNestedCorrectly(String expr){
    Stack stack = new Stack();

    for(char ch: expr){
        if(isLeftParentheses(ch))
            stack.push(ch);
        if(isRightParentheses(ch)){
            if(stack.isEmpty())
                return false;
            else if(getRightParentheses(stack.pop())!=ch)
                return false;
        }
    }
    return stack.isEmpty();
}
```
