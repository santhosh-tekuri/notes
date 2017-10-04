# Longest Common Subsequence (LCS)

Chapter 15.4 from CLRS

Given two sequences `$X = x_1, x_2, \dots, x_m$` and `$Y = y_1, y_2, \dots, y_n$`. Find maximum-length common subsequence of `X` and `Y`

`$\begin{array}{l}
X& =A\color{red}{CGT}C\color{red}GT\color{red}GT \\
Y& =\color{red}CTA\color{red}{GTGG}AG \\
LCS& =CGTGG
\end{array}
$`

---

## 1. Prove Optimal Substructure

let `$i^{th}$` prefix of `$X$`, `$X_i$` denotes sequence `$x_1, x_2, \dots, x_i$`

let `$Z = z_1, z_2, \dots, z_k$` be any LCS of `$X$` and `$Y$`
- if `$x_m=y_n$` then
    - `$z_k=x_m=y_n$`
    - `$Z_{k-1}$` is LCS of `$X_{m-1}$` and `$Y_{n-1}$`
- else
    - if `$z_k \neq x_m$` then
        - `$Z$` is LCS of `$X_{m-1}$` and `$Y$`
    - if `$z_k \neq y_n$` then
        - `$Z$` is LCS of `$X$` and `$Y_{n-1}$`

## 2. Define Recursive Optimal Solution

let `$c[i,j]$` is length of LCS of `$X_i$` and `$Y_j$`

`$c[i,j]=\begin{cases}
0 & \text{ if } i=0 \;or\;  j=0\\ 
c[i-1,j-1]+1 & \text{ if } i,j>0 \;and\;x_i=y_j \\
max(c[i,j-1]\;,\;c[i-1,j]) & \text{ if } i,j>0 \;and\;x_i\neq y_j
\end{cases}$`

## 3. Compute Optimal Cost

```java
int LCS(char x[], char y[]) {
    int m = x.length;
    int n = y.length;

    int c[m+1][n+1];
    int d[m+1][n+1];
    for(int i: 0 to m) {
        for(int j: 0 to n) {
            if(i==0 || j==0)
                c[i][j] = 0;
            else if(x[i-1]==y[j-1]) {
                c[i][j] = c[i-1][j-1]+1;
                d[i][j] = UP_LEFT;
            }else if(c[i-1][j]>=c[i][j-1]) {
                c[i][j] = c[i-1][j];
                d[i][j] = UP;
            } else {
                c[i][j] = c[i][j-1];
                d[i][j] = LEFT;
            }
        }
    }
    // time-complexity up to here: O(mn)

    printSolution(x, d, m, n);
    return c[m][n];
}
```

## 4. Construct Optimal Solution

```java
void printSolution(char x[], int d[][], int i, int j) {
    if(i==0 || j==0)
        return;
    if(d[i][j]==UP_LEFT) {
        printSolution(x, d, i-1, j-1);
        print(x[i]);
    } else if(d[i][j]==UP)
        printSolution(x, d, i-1, j);
    else
        printSolution(x, d, i, j-1);
}
```

running-time: `$O(m+n)$`

---

Excercise 15.4-2 from CLRS

Reconstruct LCS from table `c[][]`, without using `d[]`

```java
void printSolution(char x[], int c[][], int i, int j) {
    if(i==0 || j==0)
        return;
    if(c[i][j]==c[i-1][j-1]+1) {
        printSolution(x, c, i-1, j-1);
        print(x[i]);
    } else if(c[i][j]==c[i-1][j])
        printSolution(x, c, i-1, j);
    else
        printSolution(x, c, i, j-1);
}
```

---

Exercise 15.4-4 from CLRS  
Solution: page 2 in <https://courses.csail.mit.edu/6.046/fall01/handouts/ps8sol.pdf>

Show how to compute the length of an LCS using only `2·min(m, n)` entries in the `c` table plus `$O(1)$` additional space.
Then show how to do this using `min(m, n)` entries plus `$O(1)$` additional space.

When computing a particular row of the `c` table, only the previous row is needed. So only two rows
need to be kept in memory at a time.

If `m<n` compute `LCS(Y,X)`,  otherwise compute `LCS(X, Y)`. Thus we need `2.min(m,n)` entries.

```java
int curRow[n+1], prevRow[n+1];
for(int i: 0 to n)
	prevRow[i] = 0;

for(int i: 1 to m) {
	for(int j: 0 to n) {
        if(j==0)
            curRow[j] = 0;
		if(x[i-1]==y[j-1])
			curRow[j] = prevRow[j-1]+1;
		else if(prevRow[j]>=curRow[j-1])
			curRow[j] = prevRow[j];
		else
			curRow[j] = curRow[j-1];
	}
	swap(curRow, prevRow);
}
```

Actually only one row need be kept in memory at a time. When an element of the current row
is computed, we need up/upLeft/left values.
* `curRow[i]` gives up value
* `curRow[i-1]` gives left value
* store upLeft value in temporary variable

```java
int row[n+1];
for(int i: 0 to n)
	row[i] = 0;
for(int i: 1 to m){
	int upLeft = 0;
	for(int j: 1 to n){
		int up = row[j];
		if(x[i-1]==y[j-1])
			curRow[j] = upLeft+1;
		else if(row[j]<row[j-1])
			row[j] = row[j-1];
		upLeft = up;
	}
}
```

:bulb: we can further optimize it by trimming off matching items at beginning and end before starting algorithm

---

<http://www.cs.cornell.edu/~wdtseng/icpc/notes/dp2.pdf>

Given two strings `X` and `Y`. Align `Y` below `X` by inserting some gaps in such a way
that number of matching pairs of characters is maximized.

`$\begin{matrix}
X = & A& \color{red}C& \_& \_& \color{red}G& \color{red}T& C& T& \color{red}G& T& \color{red}G& T \\
Y = & \_& \color{red}C& T& A& \color{red}G& \color{red}T& G& \_& \color{red}G& A& \color{red}G& \_
\end{matrix}$`

if we spell out matching characters, we get longest common subsequence `$CGTGG$`

Let us see another way of implementing LCS.

let `$i^{th}$` suffix of `$X$`, `$X_i$` is denoted by `$x_i, x_{i+1}, x_{i+2}, \dots$`

consider `$X_i$` and `$Y_j$`, we have 3 options:
* match `$x_i$` to `$y_j$`
* insert gap into `$X$` (match `$y_j$` to gap in `$X$`)
* insert gap into `$Y$` (match `$x_i$` to gap in `$Y$`) 

let `$c[i,j]$` is length of LCS of `$X_i$` and `$Y_j$`

`c[i,j]` is maximum of:
* `c[i+1,j+1)` (`+1` if `$x_i=y_j$`)
* `c[i,j+1]`
* `c[i+1,j]`

when either of string is empty, then answer is 0. So base cases:
* `c[i, n+1] = 0`
* `c[m+1, j] = 0`

```java
int LCS(int x[], int y[]) {
    int m = x.length;
    int n = y.length;

    int c[m+1][n+1];
    int d[m+1][n+1];

    // base cases
    for(int i=0; i<=m; i++)
        c[i][n] = 0;
    for(int j=0; j<=n; j++)
        c[m][j] = 0;

    for(int i=m-1; i>=0; i--) {
        for(int j=n-1; j>=0; j--) {
            c[i][j] = c[i+1][j+1];
            if(x[i]==y[j])
                c[i][j]++;
            d[i][j] = NO_GAP;
            if(c[i][j+1]>c[i][j]) {
                c[i][j] = c[i][j+1];
                d[i][j] = X_GAP;
            }
            if(c[i+1][j]>c[i][j]) {
                c[i][j] = c[i+1][j];
                d[i][j] = Y_GAP;
            }
        }
    }

    printLCS(x, y, d);
    return c[0][0];
}

void printLCS(int x[], int y[], int d[][]) {
    int m = x.length;
    int n = y.length;
    for(int i=0,j=0; i<=m&&j<=n;) {
        if(d[i][j]==NO_GAP) {
            if(x[i]==y[j])
                print(x[i]);
            i++;j++;
        } else if(d[i][j]==X_GAP)
            j++;
        else
            i++;
    }
}
```

---

### Applications

- In web searching, you might be interested in the smallest number of changes that are needed to morph one word into another. A "change" here is either an insertion, deletion or replacement of a single character
- In bioinformatics, LCS is used all the time to align DNA, RNA and amino acid sequences to determine evolutionary relationships between organisms
