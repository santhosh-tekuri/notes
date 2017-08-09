# Merge K Sorted Linked Lists

Extend merge of merge-sort to k sorted lists ?

to find min of all list heads use min-heap

```java
Node merge(Node lists[]) {
    if(lists.length==0)
        return null;
    else if(lists.length==1)
        return lists[0];

    int heapSize = lists.length;

    // compare values of head nodes
    minHeapify(lists, heapSize);

    Node head=null, tail=null;
    while(heapSize>1) {
        Node min = lists[0];
        if(min.next==null)
            remove(lists, heapSize--, 0);
        else
            replace(lists, heapSize, 0, min.next);

        if(head==null)
            head = tail = min;
        else {
			tail.next = min
            tail = min;
		}
    }

    tail.next = lists[0];
	return head
}
```

`minHeapify` takes `$O(k)$`  
`while` loop iterates `n` times where `n` = sum of sorted list sizes  
each `while` loop takes `$\log_2 k$` times

Time Complexity: `$O(k) + O(n\log_2 k) = O(n\log_2 k)$`

### References

* <http://www.geeksforgeeks.org/merge-k-sorted-arrays/>
