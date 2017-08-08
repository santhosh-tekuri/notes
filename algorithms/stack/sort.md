# Sort a stack in ascending order

Sorting can be performed using an additional stack

## Logic

Pop items from original stack and push it into new stack. If pushing an item, violates sorting order of new stack, we need to remove enough items from it, so that it is possible to push new item. Since items
removed are pushed to the original stack, we are back where we started.

```java
public Stack sort(Stack stack){
	Stack newStack = new Stack();
	while(!stack.isEmpty()){
		int temp = stack.pop();
		while(!newStack.isEmpty() && newStack.peek()>temp)
			stack.push(newStack.pop());
		newStack.push(temp);
	}
	return newStack;
}
```
