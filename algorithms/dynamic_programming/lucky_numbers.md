# Lucky Numbers

`13` is unlucky number. In fact, any number that contains `13` is also unlucky.  
For example, **13**0, 2**13**, 2345**13**235 and 245**13**25 are all unlucky.  
All of the other numbers are lucky by default.

How many lucky numbers are there with `n` digits ?

---

in `n`-digit number:
* first digit can be anything between `1` to `9`
* subsequent digits can be anything between `0` to `9`

This difference is a little inconvenient, so we will let the first digit be `0` as
well. This way, instead of counting numbers with `n` digits, we will count all
the numbers with up to `n` digits. 

let `c[i]` = #lucky-numbers with up to `i` digits  
then #lucky-numbers with `n` digits = `c[n]-c[n-1]`

### Computing `c[i]`:

First digit can be anything between `0` to `9`. i.e `10` choices  
so pick any of `10` choices for first digit and make sure rest of the number is lucky  
so `c[i] = 10*c[i-1]`

However, if first digit is `1`, then second digit can't be `3`  
i.e. we should not count number starting with `13`  
how many such numbers exist? `c[i-2]`

`$\therefore c[i] = 10 \times c[i-1] - c[i-2]$`

base cases are:
* `c[0] = 1`
* `c[1] = 10`

:bulb: it is just enough to remember last two values

```java
int lucky(int n) {
    int c[] = luckyUpto(n);
    return c[1] - c[0];
}

int luckyUpto(int n) {
    if(n==0)
        return new int[]{0, 1};
    if(n==1)
        return new int[]{1, 10};
    
    int prev = 1, cur =10;
    for(int i=2; i<=n; i++) {
        int t = 10*cur - prev;
        prev = cur;
        cur = t;
    }
    return new int[]{prev, cur};
}
```

---

### References

* <http://www.cs.cornell.edu/~wdtseng/icpc/notes/dp1.pdf>
