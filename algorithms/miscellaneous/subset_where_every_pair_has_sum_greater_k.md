# Subset where every pair has sum >= k 

A = {-10, -100, 5, 2, 4, 7, **10, 23, 81**, 5, **25**} and k=20  
Ans: {10, 23, 81, 25}

**Such subset can have at most one element which is `<k/2`. why ?**

if there are two elements that are `<k/2`, then sum of those two elements
is `<k`. this is contradiction.

```java
solution = {}

// include all items that are >=k/2
for item in A:
    if 2*item >=k:
        solution.append(item)

if solution.size()==0:
    return null

// include any one item that is <k/2 satisfying constraint
m = min(solution)
for item in A:
   if item+m>=k and 2*item<k:
       solution.append(item)
       break

return solution.size()==1 ? null : solution;
```

### References

* <http://stackoverflow.com/a/13022397>
