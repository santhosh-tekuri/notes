# Array Manipulation

Given list(1-indexed) of size `n`, initialized with zeros. You have to 
perform `m` updates on list and output maximum value in the list.

Each update, consists of three integers `a`, `b` and `k` and you have to add `k` to
all elements in range `a...b` inclusive

```bash
n=5            ➜ [0, 0, 0, 0, 0]
a=1 b=2 k=100  ➜ [100, 100, 0, 0, 0]
a=2 b=5 k=100  ➜ [100, 200, 100, 100, 100]
a=3 b=4 k=100  ➜ [100, 200, 200, 200, 100] ➜  output=200 (i.e max-value)
```

note that, input can be very large:  
* `$3 \leq n \leq 10^7$`
* `$1 \leq m \leq 2 \times 10^5$`
* `$0 \leq k \leq 10^9$`

---

### Trivial Implementation

```java
long maxValue(int n, Update updates[m]) {
    long a[n+1];
    for(int i=0; i<m; i++) {
        for(int j=updates[i].a; j<=updates[i].b; j++)
            a[j] += ops[i].k;
    }

    long max = 0;
    for(int i=1; i<n; i++) {
        if(a[i]>max)
            max = a[i];
    }
    return max;
}
```

* each update takes `$O(n)$`
* so total running time is `$O(mn)$`

---

### Optimize Time

```java
long maxValue(int n, Update updates[m]) {
    long diffs[n+1];
    for(int i=0; i<m; i++) {
        diffs[updates[i].a] += updates[i].k;
        diffs[updates[i].b+1] -= updates[i].k;
    }

    long max=0, sum=0;
    for(int i=1; i<n; i++) {
        sum += diffs[i];
        if(sum>max)
            max = sum;
    }
    return max;
}
```

in this implementation:
* each update takes `$O(1)$`
* we are storing diffs instead of original array: 
    * `diff[i]==a[i]-a[i-1]`
    * prefix-sums of `diffs[]` gives original array
* so total running time is `$O(m+n)$`

---

### Optimize Space

notice that: 
* non-zero values in `diffs[]` exist only at `2m` indices i.e. `a` and `b` values of each update
* so we can store only those `2m` values

```java
class Pair {
    int index;
    long val;
}

long maxValue(int n, Update updates[m]) {
    List diffs = [];
    for(int i=0; i<m; i++) {
        diffs.add(new Pair(updates[i].a, updates[i].k));
        diffs.add(new Pair(updates[i].b+1, -updates[i].k));
    }

    sort(diffs, Pair#index);
    long max=0, sum=0;
    for(Pair p: diffs) {
        sum += p.val;
        if(sum>max)
            max = sum;
    }
    return max;
}
```

Time Complexity: `$O(m \log_2 m)$`  
Space Complexity: `$O(m)$`

---

### References

* <https://www.hackerrank.com/challenges/crush/problem>
