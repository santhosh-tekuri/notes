# Modify Boolean Matrix

<http://www.geeksforgeeks.org/a-boolean-matrix-question/>

Given a boolean matrix `A[m][n]`, modify it such that if `A[i][j]` is `1` then make all the cells of $i^{th}$ row and $j^{th}$ column as `1`

$\begin{bmatrix}
1 & 0 \\
0 & 0 \\
\end{bmatrix} => \begin{bmatrix}
1 & 1 \\
1 & 0 \\
\end{bmatrix}$

$\begin{bmatrix}
0 & 0 & 0 \\
0 & 0 & 1 \\
\end{bmatrix} => \begin{bmatrix}
0 & 0 & 1 \\
1 & 1 & 1 \\
\end{bmatrix}$

$\begin{bmatrix}
1 & 0 & 0 & 1 \\
0 & 0 & 1 & 0 \\
0 & 0 & 0 & 0 \\
\end{bmatrix} => \begin{bmatrix}
1 & 1 & 1 & 1 \\
1 & 1 & 1 & 1 \\
1 & 0 & 1 & 1 \\
\end{bmatrix}$

---

use two temporary arrays:
* `row[m]`: `row[i]` is `1` if $i^{th}$ row has `1`, otherwize `0`
* `col[n]`: `col[j]` is `j` if $j^{th}$ column has `1`, otherwize `0`

now modify `A[m][n]`:
* `A[i][j]` is `1` if `row[i]==1 or col[j]==0`, otherwize `0`

```java
void modifyBooleanMatrix(int A[m][n]) {
    int row[m], col[n]; // initially zeroed
    for(int i=0; i<m; i++) {
        for(int j=0; j<n; j++) {
            row[i] |= A[i][j];
            col[j] |= A[i][j];
        }
    }

    for(int i=0; i<m; i++)
        for(int j=0; j<n; j++)
            A[i][j] = row[i] | col[j];
}
```

`@src(src/ModifyBooleanMatrixV1.java)`

Time Complexity: $O(mn)$  
Space Complexity: $O(m+n)$

---

## Avoid Auxiliary Space

use two flags:
* `row0`: `1` if $0^{th}$ row contains `1`, otherwize `0`
* `col0`: `1` if $0^{th}$ column contains `1`, otherwize `0`

now use $0^{th}$ row and $0^{th}$ column in `A`, as auxiliary arrays  
finally modify $0^{th}$ row and $0^{th}$ column, based on `row0` and `col0` values

```java
void modifyBooleanMatrix(int A[m][n]) {
    int row0 = 0;
    for(int j=0; j<n; j++)
        row0 |= A[0][j];

    int col0 = 0;
    for(int i=0; i<m; i++)
        col0 |= A[i][0];

    for(int i=1; i<m; i++) {
        for(int j=1; j<n; j++) {
            A[0][j] |= A[i][j];
            A[i][0] |= A[i][j];
        }
    }

    for(int i=1; i<m; i++)
        for(int j=1; j<n; j++)
            A[i][j] = A[i][0] | A[0][j];

    // modify 0th row if there were any 1
    if(row0==1) {
        for(int j=0; j<n; j++)
            A[0][j] = 1;
    }

    // modify 0th col if there were any 1
    if(col0==1) {
        for(int i=0; i<m; i++)
            A[i][0] = 1;
    }
}
```

`@src(src/ModifyBooleanMatrixV2.java)`

Time Complexity: $O(mn)$  
Space Complexity: $O(1)$

---

### Set Matrix Zeroes

<https://leetcode.com/problems/set-matrix-zeroes/description/>

Given `A[m][n]`, if an element is `0`, set its entire row and column to `0`. Do it in place  

::: note
intput is not binary matrix
:::

```java
void setZeroes(int A[m][n]) {
    boolean row0 = false;
    for(int j=0; j<n; j++)
        row0 |= A[0][j]==0;

    boolean col0 = false;
    for(int i=0; i<m; i++)
        col0 |= A[i][0]==0;

    for(int i=1; i<m; i++) {
        for(int j=1; j<n; j++) {
            if(A[i][j]==0) {
                A[0][j] = 0;
                A[i][0] = 0;
            }
        }
    }

    for(int i=1; i<m; i++) {
        for(int j=1; j<n; j++) {
            if(A[i][0]==0 || A[0][j]==0)
                A[i][j] = 0;
        }
    }

    // modify 0th row if there were any 1
    if(row0) {
        for(int j=0; j<n; j++)
            A[0][j] = 0;
    }

    // modify 0th col if there were any 1
    if(col0) {
        for(int i=0; i<m; i++)
            A[i][0] = 0;
    }
}
```

`@src(src/SetMatrixZeroes.java)`
