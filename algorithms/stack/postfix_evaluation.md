# Postfix Evaluation

```
Input: 6 2 3 + - 3 8 2 / + * 2 $ 3 +
Output: 52
```

Each time we read an operand, we push it into a stack.  
When we reach an operator, its operands will be top two elements on stack.  
We pop these 2 elements, apply the operator and push the result into stack.

```java
int evaluate(char postFix[]) {
    Stack operands = new Stack();
    for(char symbol: postfix) {
       if(isDigit(symbol)) {
          operands.push(toDigit(symbol));
       } else {
          int operand2 = operands.pop();
          int operand1 = operands.pop();
          int value = evaluate(symbol, operand1, operand2);
          operands.push(value);
       }
    }
    return operands.pop();
}
```
