# Boxing

In Olympic boxing, there are five judges who press a button when they think that a particular boxer has just landed a punch. 
The times of the button presses are recorded in milliseconds, and the boxer receives credit for a punch if at least three of 
the judges press their buttons within 1 second of each other.

Given times recorded by each judge in milliseconds, find maximum number of disjoint intervals for duration <= 1 second

the interval is inclusive i.e. 
* `[1,4]` and `[5,22]` are disjoint
* `[1,4]` and `[4,22]` are not disjoint

`$\left.\begin{array}{l}
\{1,2,3,4,5,6\} \\
\{1,2,3,4,5,6,7\} \\
\{1,2,3,4,5,6\} \\
\{0,1,2\} \\
\{1,2,3,4,5,6,7,8\}
\end{array}\right\} \to 6 \to [1,1], [2,2], [3,3], [4,4], [5,5], [6,6]$`

`$\left.\begin{array}{l}
\{100,200,300,1200,6000\} \\
\{\} \\
\{900,902,1200,4000,5000,6001\} \\
\{0,2000,6002\} \\
\{1,2,3,4,5,6,7,8\}
\end{array}\right\} \to 3 \to [0,1000], [1001,2000], [6000,6002]$`

`$\left.\begin{array}{l}
\{5000,6500\} \\
\{6000\} \\
\{6500\} \\
\{6000\} \\
\{0,5800,6000\}
\end{array}\right\} \to 1 \to [5000,6000]$`

---

repeat:
* find the earliest interval <= 1 second  that contains 3 judges scoring a hit
* remove all hits that occur during that interval or before it

This works because: if you can get `N` hits by time `t`, that is always better than getting `N` hits by time `t+1`

```java
int maxCredits(int t[5][]) {
    int credits = 0;
    int index[] = {0, 0, 0, 0, 0}; // indices of judges
    while(true) {
        List list = {};
        for(int j=0; j<5; j++) {
            if(index[j]<t[j].length)
                list.add(t[j][index[j]]);
        }
        if(list.size()<3)
            break;

        sort(list);
        int now = list[0];
        if(list[2]-list[0]<=1000) {
            println(list[0], list[2]);
            credits++;
            now = list[2];
        }

        for(int j=0; j<5; j++) {
            while(index[j]<t[j].length && t[j][index[j]]<=now)
                index[j]++;
        }
    }
    return credits;
}
```

notice that, `sort` takes `$O(1)$` time, because the list size is at most 5

Runnning Time: `$O(n)$` where `n` is total number of timestamps

---

### References

* <https://community.topcoder.com/stat?c=problem_statement&pm=2977&rd=5880>
* <https://community.topcoder.com/tc?module=Static&d1=match_editorials&d2=tco04_online_rd_3>
