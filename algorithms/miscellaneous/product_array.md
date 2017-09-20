# Product Array without Division

Given array `a[]`. Construct product array `p[]`, where  
`p[i]` = product of all elements in `a[]` except `a[i]`  
do this without division operator?

---

construct two arrays `left[]` and `right[]`
* `left[i]` = product of elements on left of `a[i]` i,e `a[0...i-1]`
* `right[i]` = product of elements on right of `a[i]`, i.e `a[i+1...]`

then `p[i] = left[i] * right[i]`

we iterate array thrice and need `2n` auxiliary space

---

to avoid additional arrays:
* use `p[]` as `left[]`
* now compute `right[i]` and `p[i]` on the fly, from right to left

```java
void constructProductArray(int a[], int p[]) {
    p[0] = 1;
    for(int i=1; i<a.length; i++)
        p[i] = p[i-1]*a[i-1];

    int right = 1;
    for(int i=a.length-2; i>=0; i--) {
        right = right*a[i+1];
        p[i] = p[i]*right;
    }
}
```

### References

* <http://www.geeksforgeeks.org/a-product-array-puzzle/>
