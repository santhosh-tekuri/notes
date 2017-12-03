# Prefix to Postfix

`+-435` => `43-5+`  
`-*+ABC*-DE+FG` => `AB+C*DE-FG+*-`

---

* scan each character from input from right to left. if character is:
    * operand: push to stack
    * operator: pop two operands from stack, concatenate them as `operand1 + operand2 + operator`, and push this into stack
* after parsing complete input, stack contains single element, which is postfix expression

```java
String toPostfix(String prefix) {
    Stack<String> stack = new Stack<>();
    for(int i=prefix.length()-1; i>=0; i--) {
        char ch = prefix.charAt(i);
        if(isOperator(ch)) {
            String op1 = stack.pop();
            String op2 = stack.pop();
            stack.push(op1 + op2 + String.valueOf(ch));
        } else
            stack.push(String.valueOf(ch));
    }
    return stack.pop();
}
```

::: src
[:icon-go:](src/Prefix2Postfix.go)
[:icon-java:](src/Prefix2Postfix.java)
:::

---

## Postfix to Prefix

same algorithm can be used, but scan input from left to right

::: tip box
always scan in direction of operands to operator
:::

---

### References

* <http://qa.geeksforgeeks.org/6252/it-possible-convert-prefix-expression-postfix-expression>
