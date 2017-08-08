# Decimal to Binary

Convert decimal number to binary number

```java
void printBinary(int n){
    Stack stack;
    while(n>0){
        stack.push(n%2);
        n /= 2;
    }
    while(!stack.isEmpty())
        print(stack.pop());
}
```

