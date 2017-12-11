# Postfix Evaluation

```
6 2 3 + - 3 8 2 / + * 2 ^ 3 + ➜ 52
2 1 + 3 * ➜ 9
4 13 5 / + ➜ 6
```

---

Each time we read an operand, we push it into a stack.  
When we reach an operator, its operands will be top two elements on stack.  
We pop these 2 elements, apply the operator and push the result into stack.

```java
int evaluate(String postfix) {
    Stack operands = new Stack();
    for(String token: postfix.split(" ")) {
        if(isInt(token)) {
            operands.push(toInt(token));
        } else {
            int operand2 = operands.pop();
            int operand1 = operands.pop();
            int value = evaluate(token, operand1, operand2);
            operands.push(value);
        }
    }
    return operands.pop();
}
```

`@src(src/EvalPostfix.java)`

---

## Prefix Evaluation

```
+ ^ * - 6 + 2 3 + 3 / 8 2 2 3 ➜ 52
* + 2 1 3 ➜ 9
+ 4 / 13 5 ➜ 6
```

read tokens in reverse order

```java
int evaluate(String prefix) {
    String tokens[] = prefix.split(" ");
    Stack operands = new Stack();
    for(int i=tokens.length-1; i>=0; i--) {
        if(isInt(tokens[i])) {
            operands.push(toInt(token));
        } else {
            int operand1 = operands.pop();
            int operand2 = operands.pop();
            int value = evaluate(tokens[i], operand1, operand2);
            operands.push(value);
        }
    }
    return operands.pop();
}
```

`@src(src/EvalPrefix.java)`