# Stack with getMin in Constant Time

Design a stack that supports push, pop, and retrieving the minimum element in constant time. Can you do this?

Use an extra stack called `minStack` to maintain minimums

* top of `minStack` gives the current minimum
* while pushing an element, if pushed element is equal or less than current minimum, then you push it to `minStack` also
* while popping an element, if popped element is equal to current minimum, then pop it from `minStack` also

Similarly we can implement `getMax` in constant time

```java

class MyStack{
	Stack elements = new Stack();
	Stack minStack = new Stack();
	Stack maxStack = new Stack();
    
	public void push(int x){
		elements.push(x);
		if(minStack.isEmpty() || x<=minStack.top())
			minStack.push(x);
		if(maxStack.isEmpty() || x>=maxStack.top())
			maxStack.push(x);
	}
    
	public int pop(){
		int x = elements.pop();
		if(x==minStack.top())
			minStack.pop();
		if(x==maxStack.top())
			maxStack.pop();
		return x;
	}

	public int getMin(){
		return minStack.top();
	}

	public int getMax(){
		return maxStack.top();
	}
}
```

### References

* Exercise 4-44 from The Algorithm Design Manual
* <http://www.leetcode.com/2010/11/stack-that-supports-push-pop-and-getmin.html>

