# Theme Park

Roller coasters are so much fun! It seems like everybody who visits the theme park 
wants to ride the roller coaster. Some people go alone; other people go in groups, 
and don't want to board the roller coaster unless they can all go together. And 
everyone who rides the roller coaster wants to ride again. A ride costs 1 Euro 
per person; your job is to figure out how much money the roller coaster will make today.

The roller coaster can hold `k` people at once. People queue for it in groups. 
Groups board the roller coaster, one at a time, until there are no more groups left 
or there is no room for the next group; then the roller coaster goes, whether it's 
full or not. Once the ride is over, all of its passengers re-queue in the same order. 
The roller coaster will run `R` times in a day

Input: 
* `$R$` times
* `$k$` capacity
* `$g[n]$` groups, where `$1\leq g[i] \leq k$`

note that, input can be very large:
* `$1 \leq R \leq 10^8$`
* `$1 \leq k \leq 10^9$`
* `$1 \leq n \leq 1000$`
* `$1 \leq g[i] \leq 10^7$`

```bash
R=4 k=6 g={1, 4, 2, 1}

#Riding | queue      | Riding  | queue      | earned
1       | 1, 4, 2, 1 | 1, 4    | 2, 1       | 5
2       | 2, 1, 1, 4 | 2, 1, 1 | 4          | 4
3       | 4, 2, 1, 1 | 4, 2    | 1, 1       | 6
4       | 1, 1, 4, 2 | 1, 1, 4 | 2          | 6
------------------------------------------------------
                                              21 euros

R=100 k=10 g={1} ➜ 100
R=5 k=5 g={2, 4, 2, 3, 4, 2, 1, 2, 1, 3} ➜ 20
```

---

instead of moving contents of `g[]` after each ride, we can use it as circular array

```java
(long people, int newHead) doRide(long k, long g[n], int head) {
    long people = 0;
    int newHead = head;
    do {
        if(people+g[newHead]<=k) {
            people += g[newHead];
            newHead = (newHead+1)%n;
        } else
            break;
    }while(newHead!=head);
    return (people, newHead);
}
```
`@src(src/ThemeParkRide1.java)`

Running Time: `$O(n)$`

if `k=Long.MAX_VALUE` then `people+g[newHead]<=k` will always be true because of overflow  
to fix this, instead of counting #people, count space left in roller coaster:

```java
(long people, int newHead) doRide(long k, long g[n], int head) {
    long space = k;
    int newHead = head;
    do {
        if(space-g[newHead]>=0) {
            space -= g[newHead];
            newHead = (newHead+1)%n;
        } else
            break;
    }while(newHead!=head);
    return (k-space, newHead);
}
```
`@src(src/ThemeParkRide2.java)`

```java
long findMoney(long R, long k, long g[n]) {
    long money = 0;
    int head = 0;
    for(long r=0; r<R; r++) {
        Tuple t = doRide(k, g, head);
        money += t.people;
        head = t.newHead;
    }
    return money;
}
```
`@src(src/ThemePark0.java)`

Running Time: `$O(Rn)$`

---

### Optimization 1

`doRide(...)` is a [pure function](http://en.wikipedia.org/wiki/Pure_function). i.e returns same result for same arguments  
for large `R`, we would end up calling `doRide(...)` with same arguments more than once  
why can't store `doRide(...)` return values and reuse them

```java
long findMoney(long R, long k, long g[n]) {
    // pre-processing
    long people[n];
    int newHead[n];
    for(int head=0; head<n; head++)
        (people[head], newHead[head]) = doRide(k, g, head);

    // main-computation
    long money = 0;
    int head = 0;
    for(long r=0; r<R; r++) {
        money += people[head];
        head = newHead[head];
    }
    return money;
}
```
`@src(src/ThemePark1.java)`

pre-processing: `$O(n^2)$`  
main-computation: `$O(R)$`

---

### Optimization 2

let us say, in first ride `g[0...i]` are gone → in next ride `g[1...i]` obviously can go  
so this insight can be use to reduce pre-processing from `$O(n^2)$` to `$O(n)$`

```java
// pre-processing
long people[n];
int newHead[n];
for(int head=0; head<n; head++) {
    long space = head==0 ? k : k-(people[head-1]-g[head-1]);
    int hNext = head==0 ? head : newHead[head-1];
    do {
        if(space-g[hNext]>=0) {
            space -= g[hNext];
            hNext = (hNext+1)%n;
        } else
            break;
    }while(hNext!=head);
    people[head] = k-space;
    newHead[head] = hNext;
}
```
`@src(src/ThemePark2.java)`

---

### Optimization 3

notice how head changes after each ride, we will notice a cycle

in above example: 

`$\begin{array}{c|cc}
index & 0 & 1 & 2 & 3 \\
\hline
groups & 1 & 4 & 2 & 1 \\
people & 5 & 6 & 4 & 6 \\
newHead & 2 & 3 & 1 & 2
\end{array}\;\;\;
\begin{array}{c|cc}
ride & 1 & 2 & 3 & 4 & 5 & 6 & 7 & 8 & \dots \\
\hline
head & 0 & \color{red}2 & \color{red}1 & \color{red}3 & \color{blue}2 & \color{blue}1 & \color{blue}3 & \color{red}2 & \dots
\end{array}$`

you can notice cycle: `2 ➜ 1 ➜ 3`

It turns out that a cycle must show up within the first `n+1` rides, because there are only `n` different states the queue can be in

for `R=102`:
* cycle starts after first round, and `101` rounds are remaining
* cycle contains `3` rounds. now there are `101/3=33` cycles
* remaining rounds = `101-33*3=2` rounds
* money earned in per cycle: `6+4+6=16`
* so total money = money earned in before cycle + (#cycles * money per cycle) + money earning last remaining rounds
* money = `5 + 33*16 + (6+4) = 543`

```java
int findCycleHead(int newHead[n]) {
    boolean visited[n];
    int head = 0;
    while(!visited[head]) {
        visited[head] = true;
        head = newHead[head];
    }
    return head;
}
```

Running Time: `$O(n)$`

```java
// main-computation
int cycleHead = findCycleHead(newHead);

long r = 0;
long money = 0;
int head = 0;

// ride until cycle reaches
while(r<R && head!=cycleHead) {
    money += people[head];
    head = newHead[head];
    r++;
}
if(r==R)
    return money;
assert head==cycleHead;

// ride one cycle
int ridesInCycle = 0;
long moneyInCycle = 0;
while(r<R) {
    money += people[head];
    moneyInCycle += people[head];

    head = newHead[head];
    r++;
    ridesInCycle++;
    if(head==cycleHead)
        break;
}
if(r==R)
    return money;
assert head==cycleHead;

// finish cycles in remaining rounds
long cycles = (R-r)/ridesInCycle;
r += cycles*ridesInCycle;
money += cycles*moneyInCycle;

// finish remaining rides, if any
while(r<R) {
    money += people[head];
    head = newHead[head];
    r++;
}

return money;
```
`@src(src/ThemePark3.java)`

we can merge all the following into single loop:
* finding cycle
* riding until cycle reaches
* ride one cycle to compute `ridesInCycle` and `moneyInCycle`

```java
// main-computation
long r = 0;
long money = 0;
int head = 0;

// ride until cycle detected
boolean visited[n];
long moneyBeforeRound[n];
int ridesBeforeHead[n];
while(r<R && !visited[head]) {
    visited[head] = true;
    moneyBeforeRound[(int)r] = money;
    ridesBeforeHead[head] = (int)r;

    money += people[head];
    head = newHead[head];
    r++;
}
if(r==R)
	return money;

int cycleHead = head;
int ridesBeforeCycle = ridesBeforeHead[cycleHead];
long ridesInCycle = r - ridesBeforeCycle;
long moneyInCycle = money - moneyBeforeRound[ridesBeforeCycle];

// finish cycles in remaining rounds
long cycles = (R-r)/ridesInCycle;
r += cycles*ridesInCycle;
money += cycles*moneyInCycle;

// finish remaining rides, if any
while(r<R) {
    money += people[head];
    head = newHead[head];
    r++;
}

return money;
```
`@src(src/ThemePark4.java)`

Running Time: `$O(n)$`

---

### References

* Problem: <https://code.google.com/codejam/contest/dashboard?c=433101#s=p2>
* Solution: <https://code.google.com/codejam/contest/dashboard?c=433101#s=a&a=2>
* <https://articles.leetcode.com/problem-c-theme-park-solution/>
