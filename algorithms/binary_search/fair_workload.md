# Fair Wordload

We have to paint `n` boards of length `$A_1, A_2,\dots ,A_n$`. There are `k` painters
available and you are also given how much time a painter takes to paint `1` unit of board.
You have to get this job done as soon as possible under the constraints that 
any painter will only paint continuous sections of board.

```bash
k=3, 10 20 30 40 50 | 60 70 | 80 90 ➜ answer=170
k=5, 10 20 30 40 | 50 60 | 70 | 80 | 90 ➜ answer=110
k=4, 568 712 412 | 231 241 393 865 | 287 128 457 238 98 | 980 23 782 ➜ answer=1785
```

NOTE: there might be multiple way of partitioning a problem

```bash
k=2  answer=200 (2 ways to partition)
50 50 50 | 50 50 50 50
50 50 50 50 | 50 50 50

k=6 answer=950 (25 ways to partition)
950 | 650 | 250 | 250 350 | 100 650 150 | 150 700
950 | 650 | 250 | 250 350 100 | 650 150 | 150 700
950 | 650 | 250 | 250 350 100 | 650 150 150 | 700
950 | 650 | 250 250 | 350 | 100 650 150 | 150 700
950 | 650 | 250 250 | 350 100 | 650 150 | 150 700
950 | 650 | 250 250 | 350 100 | 650 150 150 | 700
950 | 650 | 250 250 350 | 100 | 650 150 | 150 700
950 | 650 | 250 250 350 | 100 | 650 150 150 | 700
950 | 650 | 250 250 350 | 100 650 | 150 | 150 700
950 | 650 | 250 250 350 | 100 650 | 150 150 | 700
950 | 650 | 250 250 350 | 100 650 150 | 150 | 700
950 | 650 | 250 250 350 100 | 650 | 150 | 150 700
950 | 650 | 250 250 350 100 | 650 | 150 150 | 700
950 | 650 | 250 250 350 100 | 650 150 | 150 | 700
950 | 650 250 | 250 | 350 | 100 650 150 | 150 700
950 | 650 250 | 250 | 350 100 | 650 150 | 150 700
950 | 650 250 | 250 | 350 100 | 650 150 150 | 700
950 | 650 250 | 250 350 | 100 | 650 150 | 150 700
950 | 650 250 | 250 350 | 100 | 650 150 150 | 700
950 | 650 250 | 250 350 | 100 650 | 150 | 150 700
950 | 650 250 | 250 350 | 100 650 | 150 150 | 700
950 | 650 250 | 250 350 | 100 650 150 | 150 | 700
950 | 650 250 | 250 350 100 | 650 | 150 | 150 700
950 | 650 250 | 250 350 100 | 650 | 150 150 | 700
950 | 650 250 | 250 350 100 | 650 150 | 150 | 700
```

---

consider `a[] = { 100, 200, 300, 400, 500, 600, 700, 800, 900 }` and `k=3`

suppose we have unlimited painters:
* t<sub>min</sub> = min time spent in painting all boards
    * assign each painter one board
    * `max(a[]) = 900`
* t<sub>max</sub> = max time spent in painting all boards
    * assign one painter all boards
    * `sum(a[]) = 4500`

```java
// returns #painters required, if each painter
// can paint at most m units
int getRequiredPainters(int a[], int m) {
    int total = 0;
    int painters = 1;
    for(int i=0; i<n; i++){
        total += a[i];
        if(total>m) {
            total = a[i];
            painters++;
        }
    }
    return painters;
}
```

Running Time: `$O(n)$`

```bash
    time 900-----------2700---------------4500
painters  6              2                  1
```

note that:
* as time increases, `#painters` required decreases or remains same
* so do binary search in time, to find the first occurrence of time whose `#painters=k`

```java
int partition(int a[], int k) {
    int lo = max(a);
    int hi = sum(a);
    int time;
    while(lo<hi) {
        int mid = lo+(hi-lo)/2;
        int painters = getRequiredPainters(a, mid);
        if(painters>k)
            lo = mid+1;
        else {
            if(painters==k)
                time = mid;
            hi = mid+1;
        }
    }
    return time;
}
```

Running Time: `$O(n \log_2 sum(a))$`

check another [solution](../dynamic_programming/fair_workload.md) using Dynamic Programming

---

### Similar Problem

Dive an array of non-negative integers `a[]` into `k` or fewer partitions,
such that the maximum sum over all the partitions is minimized

---

### References

* <https://community.topcoder.com/stat?c=problem_statement&pm=1901&rd=4650>
* <https://articles.leetcode.com/the-painters-partition-problem-part-ii/>
