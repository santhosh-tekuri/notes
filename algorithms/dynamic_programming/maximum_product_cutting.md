# Maximum Product Cutting

given a rope of length `n` meters, cut the rope in different parts of integral 
lengths in a way that maximizes product of lengths of all parts. you must make 
at least one cut. Assume that rope of length is more than `2` meters.

```bash
2 ➜ 1*1 = 2
3 ➜ 1*2 = 2
4 ➜ 2*2 = 4
5 ➜ 2*3 = 6
10 ➜ 3*3*4 = 36
```

---

let `m[i]` is maximum product achievable for rope of length `i` meters

```bash
m[1] = 0
m[i] = max(k*(i-k), k*m[i-k]) for k = 1 to i-1
```

```java
int maxProd(int n) {
    int m[n+1];
    m[1] = 0;
    for(int i=0; i<=n; i++) {
        m[i] = 0;
        for(int k=1; k<i; k++)
            m[i] = max(m[i], k*(i-k), k*m[i-k]);
    }
    return m[n];
}
```

---

### References

* <http://www.geeksforgeeks.org/dynamic-programming-set-36-cut-a-rope-to-maximize-product/>
