# Sliding Window Maximum

Given array `a[]`. There is a sliding window of size `w` which is moving from the 
very left of the array to the very right. You can only see the w numbers in the window. 
Each time the sliding window moves rightwards by one position. Return another
array `b[]`, where `$b[i] = max(a[i...i+w-1])$`

**Example:**

`a[] = {1, 3, -1, -3, 5, 3, 6, 7}` and `w=3`

window                           |  max
---------------------------------|:-----:
**{1, 3, -1**, -3, 5, 3, 6, 7}   |  3
{1, **3, -1, -3**, 5, 3, 6, 7}   |  3
{1, 3, **-1, -3, 5**, 3, 6, 7}   |  5
{1, 3, -1, **-3, 5, 3**, 6, 7}   |  5
{1, 3, -1, -3, **5, 3, 6**, 7}   |  6
{1, 3, -1, -3, 5, **3, 6, 7**}   |  7

`b[] = {3, 3, 5, 5, 6, 7}`

---

* use a double-ended queue to represent current window
* store dequeu elements in decreasing order, to fetch max easily

consider sliding window `[5, 3, 6]`  
as we move current window to right, 5 and 3 will never be chosen as max  
i.e 6 is better candidate for max rather than 5 and 3.  
so instead of storing all the above three elements, we can simply store 6  
to implement this: before adding new element, remove all elements from end of q, which are smaller then new element

to implement moving sliding window: we need to store indices rather than elements.

**NOTE:**
* elements are always added to end of queue
* dequeue contains at most `w` elements

```java
int[] maxSlidingWindow(int a[], int w) {
    Deque q = new Deque();

    // initialze initial sliding window
    for(int i=0; i<w; i++){
        // remove all elements from end of q
        // that are <= a[i]
        while(!q.isEmpty() && a[i]>=q.peekLast())
            q.removeLast();

        // always add to end of q
        q.addLast(i);
    }

    int b[a.length-w];
    for(int i=w; i<a.length; i++){
        b[i-w] = q.peekFirst();

        // remove all elements from end of q
        // that are <= a[i]
        while(!q.isEmpty() && a[i]>=q.peekLast())
            q.removeLast();

        // remove all elements from front of q
        // that are not in current window
        while(!q.isEmpty() && q.peekFirst()<=i-w)
            q.removeFirst();

        // always add to end of q
        q.addLast(i);
    }

    // max of last window
    b[a.length-w-1] = q.peekFirst();
}
```

every element is pushed once and popped once. so there are total `$2n$` queue modifications.

Running Time: `$O(n)$`  
Auxiliary Space: `$O(w)$`

### References

* <http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/>
* <http://leetcode.com/2011/01/sliding-window-maximum.html>
