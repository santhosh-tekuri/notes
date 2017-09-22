# Stable Marriage Problem

Given `n` men and `n` women, where each person has ranked all members of the
opposite sex with a unique number between `1` and `n` in order of preference,
marry the men and women together such that there are no two people of opposite
sex who would both rather have each other than their current partners.
If there are no such people, all the marriages are **stable**.

---

let us say:
* men represented by `{A,B,C,...}`
* women represented by `{a,b,c,...}`

Married pairs `X-a`, `Y-b` are unstable, if:
* man `X` prefers another woman `b` over his current wife `a`. AND
* woman `b` prefers `X` more than her current husband `Y`

in this case `X-b` is called **rouge couple**

---

```bash
while(there is unengaged man) {
    pick unengaged man X
    pick first woman w in his list
    remove w from his list, so that she will not be picked again
    if(w is not engaged)
       X is engaged with w
    else {
       if(w prefers X over his current partner Y) {
           X is engaged with w
           Y becomes unpaired
       }
    }
}
```

**NOTE:**
* once woman becomes engaged, she remains engaged, but can change partner
  for a better mate that proposes to her. This makes this algorithm greedy for women.
* man never proposes a girl more than once

**Does the algorithm terminate ?**

in each iteration, a man eliminates a choice from his list.
There are `n` men and `n` women, so the #choices is `$n^2$`.
So the max #iterations to exhaust them is `$n^2$`

**Are resulting marriages stable ?**

assume there is rouge couple `X-b`, where the marriages are `X-a` and `Y-b`. then:
* `X.preferences = ...b...a...`
* `b.preferences = ...X...Y...`

in such case `X` must have proposed to `b` before `a`

there are only two scenarios where `Y-b` can be formed:
* woman `b` rejected `X`. means `b` has better partner than `X`. then when `Y` proposes her, she must have rejected => contradiction
* woman `b` accepted `X`, later dropped `X` for better partner. but `Y` is not better partner than `X` => contradiction

---

```java
final int FREE = -1;
int stableMarriage(int mPref[][], int wPref[][]) {
    int n = mPref.length;

    // woman i is engaged to wEngaged[i]
    int wEngaged[n];
    for(int i=0; i<n; i++)
        wEngaged[i] = FREE;

    Stack freeMen = new Stack();
    for(int i=0; i<n; i++)
        freeMen.add(i);

    // next woman to be proposed
    int next[n];

    while(!freeMean.isEmpty()) {
        int m = freeMen.peek();
        int w = mPref[m][next[m]];
        next[m]++;
        if(wEngaged[w]==FREE) {
            wEngaged[w] = m;
            freeMen.pop();
        } else {
            int mEngaged = wEngaged[w];
            if(prefers(wPref[w], m, mEngaged)){
                wEngaged[w] = m;
                freeMen.push(mEngaged);
            }
        }
    }

    return wEngaged;
}

// true if m1 is preferred over m2
boolean prefers(int pref[], int m1, int m2) {
    for(int m: pref) {
        if(m==m1)
            return true;
        if(m==m2)
            return false;
    }
}
```

### References

* <http://www.csee.wvu.edu/~ksmani/courses/fa01/random/lecnotes/lecture5.pdf>
