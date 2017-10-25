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

let `m[i][j]` is min time required to paint `a[0...i]` with `j` painters

base-case:
* `m[i][1] = sum(a[0...i])`

to compute `m[i][j]`:
* `j-1` painters has painted first `0` to `i` boards
* if all `i` boards are alreday painted, cost = `m[i][j-1]`
* if only `b` baords are painted, cost = `min(m[b][j-1], sum(a[b+1...i]))`

notice that:
* we are comuting `m[][]` column by column
* current column can be computed with values from previous column
* range sum can be computed in constant time using prefix sums

```java
int partition(int a[], int k) {
    // pre-computing prefix sums
    int sum[a.length];
    sum[0] = a[0];
    for(int b=1; b<a.length; b++)
        sum[b] = sum[b-1] + a[b];

    int mPrev[a.length], mCur[a.length];

    // compute m[][1]
    for(int b=0; b<a.length; b++)
        mPrev[b] = sum[b];

    // compute m[][j]
    for(int j=2; j<=k; j++) {
        for(int i=0; i<m.length; i++) {
            m[i] = mPrev[i];
            for(int b=0; b<i; b++)
                m[i] = min(m[i], max(mPrev[b], sum[i]-sum[b]));
        }
        m ⟷ mPrev;
    }
    
    return mPrev[a.length-1];
}
```

Time Complexity: `$O(kn_2)$`
Space Complexity: `$O(n)$`

check better [solution](../divide_conquer/fair_workload.md) using Divide & Conquer

---

### Similar Problem

Dive an array of non-negative integers `a[]` into `k` or fewer partitions,
such that the maximum sum over all the partitions is minimized

---

### References

* <https://community.topcoder.com/stat?c=problem_statement&pm=1901&rd=4650>
* <https://articles.leetcode.com/the-painters-partition-problem/>
