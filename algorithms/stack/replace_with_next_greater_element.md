# Replace with Next Greater Element

Given an array `a[]`, replace each element `a[i]` with `a[j]`
* where `j` is smallest index such that `j>i` and `a[j]>a[i]`
* if no such `j` exists, replace with `-1`

## Examples

* `[4, 5, 2, 25]` becomes `[5, 25, 25, -1]`
* `[13, 7, 6, 12, 15]` becomes `[15, 12, 12, -1]`

## Logic


* scan array from left to right and push them(indexes) into stack
    * before pushing, pop values that are smaller than that
        * for index being popped, next greater element = current element

```java
void replaceWithNGE(int a[]){
	Stack stack;
	for(int i=0; i<a.length; i++){
		while(!stack.isEmpty() && a[stack.peek()]<a[i])
			a[stack.pop()] = a[i];
		stack.push(i);
	}

	while(!stack.isEmpty())
		a[stack.pop()] = -1;
}
```

Every index is pushed once and popped once. So running time is `$O(n)$`

### References

* <http://www.geeksforgeeks.org/next-greater-element/>
