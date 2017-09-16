# Is array consecutive?

Given array of unsorted numbers. check whether array contains consecutive numbers?

```bash
{5, 2, 3, 1, 4} - true, contains consecutive numbers from 1 to 5
{83, 78, 80, 81, 79, 82} - true, contains 78 to 83
{34, 23, 52, 12, 3 } - false
{7, 6, 5, 5, 3, 4} - false, 5 appears twice
```

```java
boolean isConsecutive(int a[]) {
    if(a.length<2)
        return true;

    int max = max(a);
    int min = min(a);

    if(max-min+1!=a.length)
        return false;

    // check duplicates
    boolean visited[a.length];
    for(int x: a){
        if(visited[x-min])
            return false; // found duplicate
        visited[x-min] = true;
    }

    return true;
}
```

Running Time: `$O(n)$`  
Auxiliary Space: `$O(n)$`

---

can we embed `visited[]` into `a[]` to avoid additional space

let us say `a[]` contains only positive numbers, then we can change sign of `a[i-min]` to -ve to mark number `a[i]` as visited

```java
//check duplicates
for(int x: a){
    int pos = x-min;
    if(a[pos]<0)
        return false;
    a[pos] = -a[pos]
}
```

this approach can also be used in case `a[]` contains only -ve numbers.

this can be done to `a[]` with mixed signs. simply convert them to positive by adding `-min+1` to each element

### References

* <http://www.geeksforgeeks.org/check-if-array-elements-are-consecutive/>
