# Merge Overlapping Intervals

Given linked list of intervals, merge overlapping intervals into one.  
the output should have only mutually exclusive intervals

`$\{\color{red}{[1,3], [2,4]}, \color{blue}{[5,7], [6,8]} \} \to \{[1,4],[5,8]\}$`

---

* sort intervals by starting time
* if an interval `i` doesn't overlap with interval `j` where `j>i`, then it never overlaps with intervals after `j`

```java
void merge(Interval n) {
    if(n==null)
        return;
    n = sort(n, Interval#start);
    while(n.next!=null) {
        Interval x = n.next;
        if(x.start<=n.end) { // overlaps
            n.end = x.end; // merge x into n
            n.next = x.next; // remove x
        } else
            n = x;
    }
}
```

Sorting: `$O(n\log_2 n)$`  
Merging: `$O(n)$`

---

### References

* <http://www.geeksforgeeks.org/merging-intervals/>
