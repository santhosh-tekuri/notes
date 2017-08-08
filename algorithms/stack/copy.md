# Copy Stack

Copy given stack, leaving stack unchanged.

```java
Stack copy(Stack original){
	Stack temp = new Stack();

	// move all items from original to temp stack
	// temp stack will have items in reverse order
	while(!original.isEmpty())
		temp.push(original.pop());

	// now move all items from temp into original
	// and copy stack
	Stack copy = new Stack();
	while(!temp.isEmpty()){
		int item = temp.pop();
		original.push(item);
		copy.push(item);
	}

	return copy;
}
```

