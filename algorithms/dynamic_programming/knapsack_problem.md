# Knapsack Problem

Chapter 6.4 from [Algorithms by Dasgupta](https://isbnsearch.org/isbn/0073523402)

During a robbery, a burglar finds much more loot than he expected and has to decide what to take. 
His bag/knapsack will hold a total weight of at most `W` pounds. There are `n` items to pick from, 
of weight `$w_1, w_2, \dots, w_n$` and dollar value of `$v_1, v_2, \dots, v_n$`. 

what is most valuable combination of items he can fit in his bag?

```bash
W = 10
w = [ 6,  3,  4, 2]
v = [30, 14, 16, 9]
```

There are two versions of this problem:
* there are unlimited quantities of each item available. this is called **unbounded knapsack** or **knapsack with repetition**
    * answer = `$1*v_1 + 2*v_4 = 1*30 + 2*18 = 48$`
* there is only one of each item available. this is called **0/1 knapsack** or **knapsack without repetition**
    * answer = `$v_1 + v_3 = 30 + 16 = 46$`

---

## Knapsack with Repetition

let `k[w]` is maximum value achievable with knapsack of capacity `w`

`$k[w]=\begin{cases}
0 & \text{if $w=0$} & \text{# cannot pick any item} \\
max(k[w-w_i]+v_i) & \text{for $i=1$ to $n$ and $w_i\leq w$} & \text{# pick item i that can fit}
\end{cases}$`

```java
int knapsack(int w[], int v[], int W) {
    int k[W+1];
    k[0] = 0;
    for(int wt=1; wt<=W; wt++) {
        for(int i=0; i<w.length; i++) {
            if(w[i]<wt)
                k[wt] = max(k[wt], k[wt-w[i]]+v[i]);
        }
    }
    return k[W];
}
```

Running Time: `$O(nW)$`

consider the case where `$W$` and all weights `$w_i$` are multiples of `100`  
notice that any `k[w]` where `w` is not multiple of `100` is useless  

in this case **memoization** pays off:
* without memoization, we solve every subproblem that could conceivably needed
* with memoization, we only end up solving the ones that are actually used

```java
int knapsack(int w[], int v[], int W) {
    Hashtable ht;
    int getValue(int wt) {
        if(ht[wt]==null) {
            int val = 0;
            for(int i=0; i<w.length; i++) {
                if(w[i]<wt)
                    val = max(val, getValue(wt-w[i])+v[i]);
            }
            ht[wt] = val;
        }
        return ht[wt];
    }

    return getValue(W);
}
```

Variation:
* given weights of each item `$w_i$` and knapsack weight `$W$`, find the combination of items where sum of weights is greatest ?
    * this is special case of previous problem where `value` and `weight` of item are the same

---

## Knapsack without Repetition

let `k[w][j]` is maximum value achievable using knapsack of capacity `w` and items `1...j`

`$k[w][j]=\begin{cases}
0 & \text{if $w=0$ or $j=0$} \\
max( \\
\;\;\;\;k[w-w_j][j-1]+v_j, & \text{item $j$ is included, only when $w_j\leq w$} \\
\;\;\;\;k[w][j-1] & \text{item $j$ is not included} \\
)
\end{cases}$`

answer is `k[W][n]`

```java
int knapsack(int w[], int v[], int W) {
    int n = w.length;
    int k[W+1][n+1];
    for(int i=0; i<=W; i++) {
        for(int j=0; j<=n; j++) {
            if(i==0 || j==0)
                k[i][j] = 0;
            else {
                k[i][j] = k[i][j-1]
                if(w[j]<i)
                    k[i][j] = max(k[i][j], k[i-w[j]][j-1]+v[j]);
            }
        }
    }
    return k[W][n];
}
```

Running Time: `$O(nW)$`

The solution can also be formulated differently as below:

let `k[w][j]` is maximum value achievable for **exactly** filling knapsack of capacity `w` with items `1...j`

`$k[w][j]=\begin{cases}
0 & \text{if $w=0$} \\
-\infty & \text{if $w\neq 0$ and $j=0$} \\
max( \\
\;\;\;\;k[w-w_j][j-1]+v_j, & \text{item $j$ is included, only when $w_j\leq w$} \\
\;\;\;\;k[w][j-1] & \text{item $j$ is not included} \\
)
\end{cases}$`

answer is `max(k[...][n])` i.e max of last column

Variations:
* `$n$` projects are available to an investor and profit obtained from `$j^{th}$` project is `$p_j$`. 
   it costs `$c_j$` to invest in `$j^{th}$` project and only `$C$` dollars are available. 
   find projects to be chosen to maximize the profit.
* wide variety of resource-constrained selection tasks, can be generalized to knapsack problem
    * replace "weight" with CPU units, and "only W pounds can be taken" with "only W units of CPU time are available"
    * replace "weight" with bandwidth

---

## Sumset Sum

Given a set of `n` non-negative integers, and a value `S`, determine if there is a subset of the given set with sum equal to `S`

```bash
sum=9, a={3, 34, 4, 12, 5, 2} ➜ 4+5
sum=10, a={2, 3, 5, 6, 8, 10} ➜ 5+2+3, 2+8, 10
sum=10, a={1, 2, 3, 4, 5} ➜ 4+3+2+1, 5+3+2, 5+4+1
```

the second formulation in **knapsack without repetition** can be used

let `k[s][j]` is true if there is subset whose sum is `s` from `a[0...i]`

`$k[s][j]=\begin{cases}
true & \text{if $s=0$} \\
s=a[j] & \text{if $s\neq 0$ and $j=0$} \\
k[s-a[j]][j-1] \;or & \text{// $a[j]$ is included, only when $a[j]\leq s$}\\
\;\;\; k[s][j-1] & \text{// $a[j]$ is not included}
\end{cases}$`

answer is `k[S][n]`

```java
boolean hasSubset(int a[], int S) {
    boolean k[S+1][a.length];

    for(int j=0; j<a.length; j++)
        k[0][j] = true;

    k[a[0]][0] = true;

    for(int i=1; i<=S; i++) {
        for(int j=1; j<a.length; j++) {
            k[i][j] = k[i][j-1];
            if(a[j-1]<=i)
                k[i][j] |= k[i-a[j-1]][j-1];
        }
    }

    if(k[S][a.length-1])
        printSubsets(a, k, S, a.length-1, new ArrayList());

    return k[S][a.length-1];
}

void printSubsets(int a[], boolean k[][], int i, int j, List subset) {
    assert k[i][j];

    if(i==0)
        println(subset);
    else if(j==0) {
        subset.add(a[j]);
        println(subset);
        subset.remove(subset.size()-1);
    else{
        if(k[i][j-1])
            printSubsets(a, k, i, j-1, subset);
        if(a[j-1]<=i && k[i-a[j-1]][j-1]) {
            subset.add(a[j-1]);
            printSubsets(a, k, i-a[j-1], j-1, subset);
            subset.remove(subset.size()-1);
        }
    }
}
```
