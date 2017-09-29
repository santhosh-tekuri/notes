# Find Missing Number

Given array of `n-1` integers containing integers in range of `[1... n]` without duplicates.
Find missing integer ?

`a[] = {1, 2, 4, 6, 3, 7, 8}`  missing = `5`

```java
// missing = sum(1...n) - sum(a)
int findMissing(int a[]) {
    int n = a.length-1;
    int sum = n*(n+1)/2;
    for(int v: a)
        sum -= v;
    return sum
}
```

This works, but `sum` can overflow for large values of `n`

```java
// missing = xor(1...n) ^ xor(a)
int findMissing(int a[]) {
    int n = a.length-1;
    int xor = 1;
    for(int i=2; i<=n; i++)
        xor ^= i;
    for(int v: a)
        xor ^= v;
    return xor;
}
```

---

## Similar Problem

Given two arrays. first array contains non-negative integers.
second array contains the all elements of first array in random
order except one element. Find that missing element ?

```java
int findMissing(int a[], int b[]) {
    return sum(a)-sum(b);
}
```

Time Complexity:  `$O(n)$`

this works, but integer overflow will occur for large arrays.

trick is to use `xor`:

```java
int findMissing(int a[], int b[]) {
    int result = a[0];
    for(int i=2; i<a.length; i++)
        result ^= a[i];
    for(int v: arr2)
        result ^= v;
    return result;
}
```

---

### References

* <http://www.geeksforgeeks.org/find-the-missing-number/>
