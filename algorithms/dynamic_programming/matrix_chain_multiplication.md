# Matrix-chain multiplication

Given a chain $A_1, A_2, \dots, A_n$ of `n` matrices,
where for $i = 1,2, \dots, n$, matrix $A_i$ has dimension $p_{i-1} \times p_i$.
Fully parenthesize the product $A_1 A_2 \dots A_n$ in a way that minimizes number of scalar multiplications ?

---

let `$P(n)$` is number of ways `n` matrices can be paranthesized
* when `n=1`, there is only one way
* when `n>1`, we can split between `$k^{th}$` and `$(k+1)^{th}$` matrices for any `$k=1, 2, \dots, n-1$`

`$P(n)=\begin{cases}
1 & \text{ if } n= 1\\ 
\sum\limits_{k=1}^{n-1}P(k)P(n-k) & \text{ if } n\geq 2 
\end{cases}$`

number of solutions is exponential in `n`, so brute force will be exhaustive search.

---

## 1. Prove Optimal Substructure

let `$A_{i..j}$` denotes product `$A_i A_{i+1} \dots A_j$`

suppose an optimal-parenthesization of  splits between `$A_k$` and `$A_{k+1}$`,
then the paranthesization of `$A_{i..k}$`, and `$A_{k+1..n}$` must be optimal.
otherwise substituting optimal parenthesization would produce cost less than
the optimum => *contradiction*

## 2. Define Recursive Optimal Solution

Let `$m[i,j]$` is minimum number of multiplications required to compute `$A_{i..j}$`

`$m[i,j]=\begin{cases}
0 & \text{ if } i=j\\ 
\min\limits_{i \leq k< j}\{m[i,k]+m[k+1,j]+p_{i-1}p_kp_j\} & \text{ if } i< j 
\end{cases}$`

## 3. Compute Optimal Cost

```java
int matrixChainOrder(int p[]){
    int n = p.length-1;

    int m[n][n];
    for(int i=0; i<n; i++)
        m[i][i] = 0;

    int split[n][n];
    for(int len: 2 to n) {
        for(int i: 0 to n-len) {
            int j = i+len-1;
            m[i][j] = ∞;
            for(int k: i to j-1) {
                int cost = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                if(cost<m[i][j]) {
                    m[i][j] = cost;
                    split[i][j] = k;
                }
            }
        }
    }

    printSolution(split, 0, n-1);
    return m[0][n-1];
}
```

## 4. Construct Optimal Solution

```java
void printSolution(int split[][], int i, int j) {
    if(i==j)
        print("A["+i+"]")
     else{
         print("(");
         printSolution(split, i, split[i][j]);
         printSolution(split, split[i][j]+1, j);
         print(")");
     }
     println();
}
```

