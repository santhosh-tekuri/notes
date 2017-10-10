# Sort k-sorted array

in k-sorted array, each element is at most k away from its target position  
i.e `$i^{th}$` element in sorted array, is in `$a[i-k...i+k]$`

## Option 1:

we can use [Insertion Sort](../sorting/insertion_sort.md). The inner while loop runs at most k times.  
so running-time: `$O(nk)$`

## Option 2:

we can use selection sort, where min element is selected repeatedly.  
in k-sorted array, the min element must be in first `$k+1$` positions.  
so use min-heap, to effectively find min

```java
void sort(int a[], int k){
   int b[] = int[k+1];
   copy a[0...k] to b
   minHeapify(b, k+1);

   for(int i=0,j=k+1; i<a.length; i++,j++){
       a[i] = b[0];
       if(j<a.length)
           replace(b, k+1, 0, a[j]);
       else
           remove(b, k+a.length-j, 0);
   }
}
```

`minHeapify` takes `$O(k)$`  
`n` heap operations each taking `$O(\log_2 k)$`  
so running-time: `$O(n\log_2 k)$`  
auxiliary-space: `$O(k)$`

### another way to frame same problem

Consider a situation where your data is almost sortedr. for example, 
you are receiving timestamped stock quotes and earlier quotes may arrive 
after later quotes because of differences in server loads and network routes. 
What would be the most efficient way of restoring the total order ?

### References

* <http://www.geeksforgeeks.org/nearly-sorted-algorithm/>
