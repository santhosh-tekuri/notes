# Median of Integer Stream

**median** is the middle element in an odd length sorted array, and in case of even length it’s the average of the middle elements.

Given a stream of unsorted integers, find the median element in sorted order at any given time ?

## Logic

use 2 heaps simultaneously, a max-heap and a min-heap with following invariants

**order invariant:**
* max-heap contains the smallest half of the numbers and min-heap contains the largest half
* so the numbers in max-heap are always less than or equal to the numbers in min-heap

**size invariant:**
* #elements in max-heap is either equal to or 1 more than #elements in the min-heap
* so if we received `2N` elements (even) up to now, max-heap and min-heap will both contain `N` elements.
* if we have received `2N+1` elements (odd), max-heap will contain `N+1` and min-heap `N`

```java
class IntStream {
	int maxHeap[], minHeap[];
	int size = 0;

	double getMedian() {
		if(size%2 == 0)
			return (maxHeap[0]+minHeap[0])/2.0;
		else
			return maxHeap[0];
	}

	void append(int v) {
		if(size%2 == 0) { // sizes: N, N
			if(v<=minHeap[0])
				add(maxHeap, size/2, v); // N+1, N
			else {
				add(minHeap, size/2, v); // N, N+1

				// move minHeap root to maxHeap
				add(maxHeap, size/2, minHeap[0]);
				delete(minHeap, (size/2)+1, 0);
			}
		} else { // sizes: N+1, N
			if(v>=maxHeap[0])
				add(minHeap, size/2, v);
			else {
				add(maxHeap, (size/2)+1, v); // N+2, N

				// move maxHeap root to minHeap
				add(minHeap, size/2, maxHeap[0]);
				delete(maxHeap, (size/2)+2, 0);
			}
		}

		size++;
	}
}
```

### References

* <http://www.ardendertat.com/2011/11/03/programming-interview-questions-13-median-of-integer-stream/>
