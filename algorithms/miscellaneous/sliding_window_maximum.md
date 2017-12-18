# Sliding Window Maximum

Given array `a[n]`. There is a sliding window of size `w` which is moving from the 
very left of the array to the very right. You can only see the w numbers in the window. 
Each time the sliding window moves rightwards by one position. Return another
array `b[n-w+1]`, where `b[i] = max(a[i...i+w-1])`

**Example:**

`a={1, 3, -1, -3, 5, 3, 6, 7} w=3`

window                           |  max
---------------------------------|:-----:
**{1, 3, -1**, -3, 5, 3, 6, 7}   |  3
{1, **3, -1, -3**, 5, 3, 6, 7}   |  3
{1, 3, **-1, -3, 5**, 3, 6, 7}   |  5
{1, 3, -1, **-3, 5, 3**, 6, 7}   |  5
{1, 3, -1, -3, **5, 3, 6**, 7}   |  6
{1, 3, -1, -3, 5, **3, 6, 7**}   |  7

`b = {3, 3, 5, 5, 6, 7}`

```bash
a={1, 2, 3, 1, 4, 5, 2, 3, 6} w=3 ➜ {3, 3, 4, 5, 5, 5, 6}
a={8, 5, 10, 7, 9, 4, 15, 12, 90, 13} w=4 ➜ {10, 10, 10, 15, 15, 90, 90}
```

---

* use a double-ended queue to represent current window
* store dequeue elements in decreasing order, to fetch max easily

consider sliding window `[5, 3, 6]`  
as we move current window to right, 5 and 3 will never be chosen as max  
i.e 6 is better candidate for max rather than 5 and 3.  
so instead of storing all the above three elements, we can simply store 6  
to implement this: before adding new element, remove all elements from end of q, which are smaller then new element

to implement moving sliding window: we need to store indices rather than elements.

::: note
elements are always added to end of queue  
dequeue contains at most `w` elements
:::

```java
int[] maxSlidingWindow(int a[n], int w) {
    assert w>0 && w<=n;

    Deque q = new Deque(w);

    // initialize initial sliding window
    for(int i=0; i<w; i++) {
        // remove all elements from end of q
        // that are < a[i]
        while(!q.isEmpty() && a[q.peekLast()]<a[i])
            q.removeLast();

        // add to window
        q.addLast(i);
    }

    int b[n-w+1];
    for(int i=w; i<n; i++) {
        b[i-w] = a[q.peekFirst()];

        // remove one element from window
        if(!q.isEmpty() && q.peekFirst()==i-w)
            q.removeFirst();

        // remove all elements from end of q
        // that are <= a[i]
        while(!q.isEmpty() && a[q.peekLast()]<a[i])
            q.removeLast();

        // add to window
        q.addLast(i);
    }

    // max of last window
    b[n-w] = a[q.peekFirst()];

    return b;
}
```

`@src(src/SlidingWindowMaximum.java)`

every element is pushed once and popped once. so there are total $2n$ queue modifications.

Time Complexity: $O(n)$  
Space Complexity: $O(w)$

---

### References

* <http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/>
* <http://leetcode.com/2011/01/sliding-window-maximum.html>
