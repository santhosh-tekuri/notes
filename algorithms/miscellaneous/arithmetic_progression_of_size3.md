# Arithmetic Progression of size 3

Find whether sorted array `a[n]` contains a three-term arithmetic progression ?

if `x, y, z` is an arithmetic progression:
* `y-x=z-y` → `2y=x+z` → `y=(x+z)/2`
* i.e, `y` is average of `x` and `y`

so the problem is equivalently:
> Find whether any element in sorted array `a[n]` is the average of two others ?

$
[\color{red}1, 7, 10, \color{red}{15}, 27, \color{red}{29}] \\
[\color{red}1, 7, 10, \color{red}{12}, 22, \color{red}{23}] \\
[1, 2, 5, 6]
$

---

```java
int[] arithmeticProgression(int a[n]) {
    for(int j=2; j<n; j++) { // a[j] is middle element
        int i=j-1, k=j+1;
        while(i>=0 && k<n) {
            if(a[i]+a[k]<2*a[j])
                k++;
            else if(a[i]+a[k]>2*a[j])
                i--;
            else
                return new int[]{i, j, k};
        }
    }
    return null;
}
```

`@src(src/ArithmeticProgression3.java)`

Running Time: $O(n^2)$

---

### References

* <http://jeffe.cs.illinois.edu/pubs/pdf/arith.pdf>
