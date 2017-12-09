# Product Array without Division

Given array `a[]`. Construct product array `p[]`, where  
`p[i]` = product of all elements in `a[]` except `a[i]`  
do this without division operator?

```bash
[10, 3, 5, 6, 2] ➜ [180, 600, 360, 300, 900]
```

---

construct two arrays `left[]` and `right[]`
* `left[i]` = product of elements on left of `a[i]` i,e `a[0...i-1]`
* `right[i]` = product of elements on right of `a[i]`, i.e `a[i+1...]`

then `p[i] = left[i] * right[i]`

```java
int[n] productArray(int a[n]) {
    int left[n];
    left[0] = 1;
    for(int i=1; i<n; i++)
        left[i] = left[i-1]*a[i-1];

    int right[n];
    right[n-1] = 1;
    for(int i=n-2; i>=0; i--)
        right[i] = right[i+1]*a[i+1];

    int p[n];
    for(int i=0; i<n; i++)
        p[i] = left[i]*right[i];

    return p;
}
```

`@src(src/ProductArrayV1.java)`

Time Complexity: $O(n)$  
Space Complexity: $O(n)$

we iterate array thrice and need `2n` auxiliary space

---

### Optimize Space

to avoid additional arrays:
* use `p[]` as `left[]`
* now compute `right[i]` and `p[i]` on the fly, from right to left

```java
int[n] productArray(int a[n]) {
    int p[n];

    // compute left[] into p[]
    p[0] = 1;
    for(int i=1; i<n; i++)
        p[i] = p[i-1]*a[i-1];

    int right = 1;
    for(int i=n-2; i>=0; i--) {
        right = right*a[i+1];
        p[i] = p[i]*right;
    }

    return p;
}
```

`@src(src/ProductArrayV2.java)`

Time Complexity: $O(n)$  
Space Complexity: $O(1)$

---

### References

* <http://www.geeksforgeeks.org/a-product-array-puzzle/>
