# Parenthesizing MINUS Expression

Given list of `n` positive integer numbers.
Repeatedly choose two neighboring integers `x` and `y`, delete them and put `x-y` in that place
i.e list size decrements by 1. You can do this `n-1` times, after that you are left with single number.
How do you choose neighbors if the last number to be left is `m` ?

```bash
example: {12, 10, 4, 3, 5}, m=4

12 [10 4] 3 5 ==> choice 2 (i.e second and third elements chosen)
12 6 [3 5] ==> choice 3
12 [6 -2] ==> choice 2
[12 8] ==> choice 1

output is choices made i.e {2, 3, 2, 1}
```

:bulb: last choice is always `1`

---

### this problems can also be visualized as below

given expression with MINUS operator, parenthesize such that result is `m`

```bash
Input: 12-10-4-3-5    m=4
answer: (12-((10-4)-(3-5)))
```

if we remove parenthesis in above answer it becomes: `12-10+4+3-5`

note that:
* first operator is always `MINUS`, rest of them can be either `PLUS` or `MINUS`
* there are `n-1` operators in expression, where first operator is fixed.

---

let us define `can[i][r]`:
* `i` means expression with first `i` operands
* `r` is the result

value of `can[i][r]` is:
* `NONE` if it is not possible to create expression with `PLUS` and `MINUS` resulting `r`
* `PLUS/MINUS`, last operand used in expression

if `can[i][r]=NONE`:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● `can[i+1][r+a[i+1]]` and `can[i+1][r-a[i+1]]` are also `NONE`  
otherwize:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● `can[i+1][r+a[i+1]] = PLUS` and `can[i+1][r-a[i+1]] = MINUS`

min result achievable `rMin` = `12-10-4-3-5 = ((((12-10)-4)-3)-5) = -10`  
max result achievable `rMax` = `12-10+4+3+5 = (12-(((10-4)-3)-5)) = 14`  
so max #number of possible results `rCount` = `rMax-rMin+1 = 14+10+1 = 25`

because arrays doesn't support -ve indexing we need to trick here:
* we use `can[n+1][rCount+1]`
* then for `can[i][j]`, `r` is `j+rMin`

---

the `can[][]` array can become very huge if given numbers are very large.  
instead we can generate each possible permutation of operators which is quite small

```java
public static final int PLUS = 0;
public static final int MINUS = 1;

int[] findOperands(int a[], int m) {
    Stack stack = new Stack(a.length-1);

    // starting permutation is "MINUS followed by PLUSes"
    int r = a[0];
    stack.push(MINUS);
    r -= a[stack.size()];
    while(stack.size()<a.length-1) {
        stack.push(PLUS);
        r += a[stack.size()];
    }

    while(true) {
        if(r==m)
            return stack;

        // remove all trailing MINUSes
        while(!stack.isEmpty() && stack.peek()==MINUS) {
            r += a[stack.size()];
            stack.pop();
        }
        if(stack.isEmpty) // visited all permutations
            return null;

        // replace PLUS at peek with MINUS
        r -= a[stack.size()];
        stack.pop();
        stack.push(MINUS);
        r -= a[stack.size()];

        // fill remaining with PLUS
        while(stack.size()<a.length-1) {
            stack.push(PLUS);
            r += a[stack.size()];
        }
    }
}

void printChoices(int op[]) {
    for(int i=op.length-1, count=0; i>=0; i--) {
        count++;
        if(i==0 || op[i]!=op[i-1]) { // group starts here
            while(count>0) {
                println(i+1);
                count--;
            }
        }
    }
}
```

---

### References

* <http://www.spoj.com/problems/MINUS/>
* <https://github.com/marioyc/Online-Judge-Solutions/blob/master/SPOJ/Classical/2005%20-%20Minus%20Operation.cpp>
