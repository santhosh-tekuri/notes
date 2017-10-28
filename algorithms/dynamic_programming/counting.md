# Counting

Gustavo knows how to count, but he is just now learning how to write numbers. 
He has already learned the digits `1`, `2`, `3`, and `4`. But he does not yet 
realize that `4` is different than `1`, so he thinks that `4` is just another way to write `1`.

He is having fun with a little game he created: he makes numbers with the four digits that he knows and sums their values.

For example:  
132 = 1 + 3 + 2 = 6  
11231**4** = 1 + 1 + 2 + 3 + 1 + **1** = 9 (remember that Gustavo thinks that 4 = 1)

Gustavo now wants to know how many such numbers he can create whose sum is a number `n` ?

For example: if `n=2`, we can make 5 numbers: `11, 14, 41, 44, 2`

---

let `c[i]` is #numbers that sum to `i`

```bash
c[1] = 1 + // ending with 1
       1   // ending with 4

c[2] = c[2-1] + // ending with 1
       1 +      // ending with 2
       c[2-1]   // ending with 4

c[3] = c[3-1] + // ending with 1
       c[3-2] + // ending with 2
       1 +      // ending with 3
       c[3-1]   // ending with 4

c[i] = c[i-1] + // ending with 1
       c[i-2] + // ending with 2
       c[i-3] + // ending with 3
       c[i-1] + // ending with 4
```

:bulb: to compute `c[i]`, we only need previous three values

```java
int countNumbers(int n) {
    long c[3];
    c[0] = 1 + 1;
    c[1] = 2*c[0] + 1;
    c[2] = 2*c[1] + c[0] + 1;

    // circular array
    // 0 1 2 0 1 2 0 1 2 ...
    // 0 1 2 3 4 5 6 7 8 ...
    for(int i=3; i<n; i++) {
        c[i%3] = 2*c[(i-1)%3] + c[(i-2)%3] + c[(i-2)%3];
    }
    return c[(n-1)%3];
}
```

Running Time: `$O(n)$`

---

## References

* [UVa 10198](https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1139)
