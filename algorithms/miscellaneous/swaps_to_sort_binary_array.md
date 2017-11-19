# #Swaps to sort Binary Array

Count number of swaps required to sort binary array i.e. array containing only `0`s and `1`s.  
Swapping is permitted only with adjacent elements.

---

* scan array from right to left
* keep track number of `0`s
* when you encounter `1`, add current number of `0`s to total no of swaps

```java
int countSwaps(int a[n]) {
    int swaps = 0;
    int zeros = 0;
    for(int i=n-1; i>=0; i--) {
        if(a[i]==0)
            zeros++;
        else
            swaps += zeros;
    }
    return swaps;
}
```

---

### References

* <https://www.careercup.com/question?id=13674661>
