# Fixed Point in Array

if `a[i]=i` then, `i` is called **fixed point**

find fixed point in ascending array containing distinct integers ?

{-10, -5, 0, **3**, 7}  
{**0**, 2, 5, 8, 17}  
{-10, -5, 3, 4, 7, 9} // No Fixed Point

```java
int fixedPoint(int a[]) {
    int lo=0, hi=a.length-1;
    while(lo<=hi) {
        int m = lo+(hi-lo)/2;
        if(a[m]==m)
            return m;
        if(a[m]<m)
            lo = m+1;
        else
            hi = m-1;
    }
    return -1; // no fixed point
}
```

running time: `$O(n \log_2 n)$`

---

### References

* <http://crackprogramming.blogspot.in/2012/10/given-sorted-array-of-distinct-integers.html>
