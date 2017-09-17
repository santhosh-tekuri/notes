# Minimum and Maximum

Find minimum/maximum in given array with less #comparisons?

```java
int minimum(int a[]) {
    if(a.length==0)
        return null;
    int min = a[0];
    for(i=1; i<a.length; i++) {
        if(a[i]<min)
            min = a[i];
    }
    return min;
}
```

#comparisons = `$n-1$`

---

Find both minimum and maximum in given array with less #comparisons ?

```java
int[] minMax(int a[]) {
    if(a.length==0)
        return null;

    int min = a[0];
    int max = a[0];
    for(int i=1; i<a.length; i++) {
        if(a[i]<min)
            min = a[i];
        else if(a[i]>max)
            max = a[i];
    }
    return { min, max };
}
```

for each element we are doing 2 comparisons. so we used `2(n-1)` comparisons

---

If we compare pairs of elements from input first with each other, and then compare 
smaller to current minimum and larger to current maximum, we need 3 comparisons 
for every 2 elements.

initially,

- if `n` is odd, we set both `min` and `max` to first element. so we need `3(n-1)/2` comparisons
- if `n` is even, we perform 1 comparison on first 2 elements to determine initial values of `min` and `max`. so we need `1+3(n-2)/2` i.e `3n/2-2` comparisons

```java
int[] minMax(int a[]) {
    if(a.length==0)
        return null;

    int min, max, i;
    if(n%2==0) {
        if(a[0]<a[1]) {
            min = a[0];
            max = a[1];
        } else {
            min = a[1];
            max = a[0];
        }
        i = 2;
    } else {
        min = max = a[0];
        i = 1;
    }

    while(i<a.length-1) {
        if(a[i]<a[i+1]) {
            if(a[i]<min)
                min = a[i];
            if(a[i+1]>max)
                max = a[i+1];
        } else {
            if(a[i+1]<min)
                min = a[i+1];
            if(a[i]>max)
                max = a[i];
        }
        i += 2;
    }
    return { min, max };
}
```

---

Find smallest and second smallest with `$n+ \lceil\log_2 n \rceil-2$` comparisons.

the smallest of `n` elements can be found through a cup of tournament:

```bash
1   2 3   4 5   6 7   8    |   1   2 3   4 5   6     7
 \ /   \ /   \ /   \ /     |    \ /   \ /   \ /     /
  1     3     5     7      |     1     3     5     /
   \   /       \   /       |      \   /       \   /
    \ /         \ /        |       \ /         \ /
     1           5         |        1           5
      \         /          |         \         /
       \       /           |          \       /
        \     /            |           \     /
         \   /             |            \   /
          \ /              |             \ /
           1               |              1
```

`n` numbers are leaves, and each non-leaf node represents a comparison. There are `n-1`
internal nodes in an `n`-leaf full binary tree, so exactly `n-1` comparisons are made.
 
the second smallest is among the elements that were compared with the smallest during the tournament.
find second smallest by travesring from root to leaf along the nodes containing `min`, and find smallest of 
`min`'s siblings. height is `$\lceil\log_2 n\rceil$`.

#comparisons = `$(n-1) + (\lceil\log_2 n\rceil -1)$` = `$n-\lceil\log_2 n\rceil -2$`

### References

* Chapter 9.1 from Cormen
* Exercise 9.1-1 from Cormen
* <http://www.geeksforgeeks.org/second-minimum-element-using-minimum-comparisons/>
