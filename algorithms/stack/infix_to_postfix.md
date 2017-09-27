# Infix to Postfix (Shunting-yard algorithm)


* scan the infix string from left to right
    * if we see operand, append to postfix string
    * if we see operator
        * pop operators from stack that have higher precedence and append to postfix string
        * push operator into stack
    * pop remaining operators from stack and append to postfix string

```java
String toPostfix(String infix) {
    String postfix = "";
    Stack stack = new Stack();

    for(char ch: infix) {
        if(isOperand(ch))
            postfix.append(ch);
        else {
            while(!stack.isEmpty() && precedence(stack.peek(), ch))
                postfix.append(stack.pop());
            stack.push(ch); // Line 11
        }
     }

     while(!stack.isEmtpy())
         postfix.append(stack.pop()); // Line 16

     return postfix;
}

// returns true if op1 should be evaluated first
boolean precedence(char op1, char op2) {
    // precedence('+', '+') is true
    // precedence('*', '+') is true
    // precedence('+', '*') is false
}
```
## Associativity:

if `op1` and `op2` have same precedence `precedence(op1, op2)` returns
* `true` for left associativity
* `false` for right associativity

## Parentheses:

* when we see left parenthesis, push to stack without popping any operators
    * `precedence(op, '(')` returns `false`
* when we see right parenthesis, pop all operators up to left parenthesis, which is popped and discarded
    * `precedence(op, ')')` returns `op!='('`
    * never push right parenthesis into stack. This can be achieved by changing line 11

      ```java
      if(ch!=')')
          stack.push(ch);
      else
          stack.pop(); // discarding '('
      ```
* one line 16, if we encounter left parenthesis, then there are mismatched parentheses

## Unary Operators:

* when we see unary operator, we are allowed only to pop unary operators from stack
    * `precedence(binaryOp, unaryOp)` returns `false`
    * suppose `^` has higher precedence than unary `-`
        * `a^-b` is parsed correctly (note we can evaluate `^` before `-` here)
        * in `-a^b` we want to apply `^` first
    * how to distinguish binary, prefix and postfix unary operator?
        * if previous token is operand, current operator is binary or postfix-unary operator
        * if previous token is operator or null, current operator is prefix-unary operator
        * treat left parenthesis as operator and right parenthesis as operand for this purpose


## Function Calls:

* when we see function name token, we push it into stack
* when we see function argument separator(comma), pop all operators up to left parenthesis
    * `precedence(op, ',')` returns `op!='('`
* when right parenthesis encountered, do as before. and if top of stack is function name token pop it and append to postfix

---

## Infix to Prefix

do following modifications to above algorithm:
* scan infix backwards
* always preppend to prefix string
* flip the left and right paranthesis behavior

---

### References

* <http://www.reedbeta.com/blog/2011/12/11/the-shunting-yard-algorithm/>
* <http://en.wikipedia.org/wiki/Shunting_yard_algorithm>
