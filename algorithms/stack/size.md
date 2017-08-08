# Size of Stack

Count the number of elements in stack, leaving stack unchanged ?

```java
int count(Stack stack){
    int count = 0;
    Stack temp = new Stack();

    // push elements into temp stack and update count
    while(!stack.isEmpty()){
        temp.push(stack.pop);
        count++;
    }

    // restore original stack
    while(!temp.isEmpty())
        stack.push(temp.pop());

    return count;
}
```
