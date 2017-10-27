# Function's Transition Point

Given monotonically increasing function `f(x)` taking non-negative integer as argument.  
Find point `n`, where:
* `f(x)<=0` for `x<n`
* `f(x)>0` for `x>=n`

---

we can use binary search to find `n`, but problem is we don't know the upper limit

we can find upper limit as follows:
* evaluate `f` for `$2^0, 2^1, 2^2,\dots$`
* let us say at `$2^k$`, the value turns +ve
* then simply do binary search in range `$(2^{k-1}, 2^k)$`

```java
int findFirstPositive(){
    if(f(0)>0)
        return 0;

    int k=1;
    while(f(k)<=0)
        k *= 2;

    int lo=k/2, hi=k;
    while(hi-lo>1){
        assert f(lo)<=0 && f(hi)>0;

        int m = lo+(hi-lo)/2;
        int fm = f(m);
        if(fm>0)
            hi = m;
        else
            lo = m;
    }
    return hi;
}
```

finding `k` takes `$O(\log_2 n)$` and binary search takes `$O(\log_2 n)$`  
so running time is `$O(\log_2 n)$`

---

### References

* <http://www.geeksforgeeks.org/find-the-point-where-a-function-becomes-negative/>
