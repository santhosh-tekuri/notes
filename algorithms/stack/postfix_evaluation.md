# Postfix Evaluation

```
Input: 6 2 3 + - 3 8 2 / + * 2 $ 3 +
Output: 52
```

Each time we read an operand, we push it into a stack.  
When we reach an operator, its operands will be top two elements on stack.  
We pop these 2 elements, apply the operator and push the result into stack.

```java
int evaluate(char postFix[]){
    Stack operandStack = new Stack();
    for(char symbol: postfix){
       if(isDigit(symbol))
          operandStack.push(toDigit(symbol));
       else{
          int operand2 = operandStack.pop();
          int operand1 = operandStack.pop();
          int value = evaluate(symbol, operand1, operand2);
          operandStack.push(value);
       }
    }
    return operandStack.pop();
}
```
