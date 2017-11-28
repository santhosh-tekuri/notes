# Median of two sorted arrays

Given two sorted arrays `a[m]` and `b[n]`, find median of array obtained by merging those arrays ?

median of sorted array is defined as:

```java
double median(int a[n]) {
    if(n==0)
        return 0;

    int mid = n/2;
    if(n%2==0)
        return (a[mid-1]+a[mid])/2.0;
    else
        return a[mid];
}
```

---

### Count while merging

```java
double median(int a[m], int a[n]) {
    int mid = (m+n)/2;
    int cur=0, prev=0;
    int i=0, j=0;
    for(int k=0; k<=mid; k++) {
        prev = cur;

        if(i<m && j<n) {
            if(a[i]<b[j]) {
                cur = a[i];
                i++;
            } else {
                cur = b[j];
                j++;
            }
        } else {
            int offset = mid-k;
            if(i==m) {
                cur = b[j+offset];
                if(offset>0)
                    prev = b[j+offset-1];
            } else {
                cur = a[i+offset];
                if(offset>0)
                    prev = a[i+offset-1];
            }
            break;
        }
    }

    if((m+n)%2==0)
        return (prev+cur)/2.0;
    else
        return cur;
}
```

Running Time: `$O(m+n)$`

---

### Comparing Medians

consider mid of two arrays `a[i]` and `b[j]` i.e. `{...a[i]...} {...b[j]...}`:
* if `a[i]<=b[j]` i.e. `{...a[i]...b[j]...}`
    * median must be somewhere between `a[i]...b[j]`
    * so we can dicard `m/2` elements before `a[i]`, `n/2` elements after `b[j]`
        * but if #elements discarded from each array is different, the median of the remaining two arrays may no longer be median of original problem
        * to fix this, discard `min(m/2,n/2)` elements from each array
        * but becareful of even size array, because the mid of such array has two elements
* if `a[i]>b[j]` i.e `{...b[j]...a[i]...}`
    * median must be somewhere between `b[j]...a[i]`
    * so we can discard `n/2` elements before `b[j]` and `m/2` elements after `a[i]`
        * but to maintain the integrity of subproblem, disard `min(m/2, n/2)` elements from each array

note that, you should never discard the mid elements 

doing this repeatedly, we will end up into situation where size of one of the arrays becomes `1` or `2`.  
in this case, the mid contains all elements of the array, thus we cannot discard elements any more.  
this means, these base cases has to be solved explicitly.


`$\begin{array}{l|c|c|c}
\text{sizes 1, odd} & [x] \; [\color{red}b] & [x] \; [a,\color{red}b,c] & [x] \; [---, a,\color{red}b,c, +++] \\
\hline
\text{$x$ is before center} & &[x, \color{red}{a,b}, c] & [\{---, x\}, \color{red}{a,b}, c, +++] \\
\text{$x$ is after center} & & [a, \color{red}{b,c}, x] & [---, a, \color{red}{b,c}, \{x, +++\}] \\
\text{$x$ is inside center} & [\color{red}{\{x,b\}}] & [a, \color{red}{\{x,b\}}, c] & [---, a, \color{red}{\{x,b\}}, c, +++]
\end{array}$`

notice that:
* `b` is always part of center
* mid of `x, a, c` is part of center


`$\begin{array}{l|c|c|c}
\text{sizes 1, even} & [x]\;[\color{red}{b,c}] & [x]\;[a, \color{red}{b,c}, d] & [x]\;[---, a, \color{red}{b,c}, d, +++] \\
\hline
\text{$x$ is before center} & & [x, \color{red}{a,b,c}, d] & [\{---,x\}, \color{red}{a,b,c}, d,+++] \\
\text{$x$ is after center} & & [a, \color{red}{b,c,d}, x] & [---,a, \color{red}{b,c,d}, \{x,+++\}] \\
\text{$x$ is inside center} & [\color{red}{\{x,b,c\}}] & [a, \color{red}{\{x,b,c\}}, d] & [---,a, \color{red}{\{x,b,c\}}, d,+++]
\end{array}$`

notice that:
* `b, c` are always part of center
* mid of `x, a, d` is part of center

`$\begin{array}{l|c|c|c}
\text{sizes 2, odd} & [x,y]\;[\color{red}b] & [x,y]\;[a,\color{red}b,c] & [x,y]\;[---,a,\color{red}b,c.+++] \\
\hline
\text{$x, y$ are opposite side} & & [x, \color{red}{a,b,c},y] & [\{---,x\}, \color{red}{a,b,c},\{y,+++\}]\\
\text{only $x$ is inside} & & [a, \color{red}{\{x,b,c\}}, y] & [---,a, \color{red}{\{x,b,c\}}, \{y,+++\}] \\
\text{only $y$ is inside} & & [x, \color{red}{\{y,a,b\}}, c] & [\{---,x\}, \color{red}{\{y,a,b\}}, c,+++] \\
\text{$x, y$ are inside} & [\color{red}{\{x,y,b\}}] & [a, \color{red}{\{x,y,b\}}, c] & [---,a, \color{red}{\{x,y,b\}}, c,+++]
\end{array}$`

notice that:
* `b` is always part of center
* max of `x, a` is part of center
* min of `c, y` is part of center

`$\begin{array}{l|c|c|c}
\text{sizes 2, even} & [x, y]\;[\color{red}{b, c}] & [x, y]\;[a,\color{red}{b, c}, d] & [x, y]\;[---, a,\color{red}{b, c}, d, +++] \\
\hline
\text{$x, y$ are opposite side} &  & [x, \color{red}{a, b, c, d}, y] & [\{---, x\}, \color{red}{a, b, c, d}, \{y, +++\}] \\
\text{only $x$ is inside} & & [a, \color{red}{\{x, b, c, d\}}, y] & [---, a, \color{red}{\{x, b, c, d\}}, \{y, +++\}] \\
\text{only $y$ is inside} & & [x, \color{red}{\{y, a, b, c\}}, d] & [\{---, x\}, \color{red}{\{y, a, b, c\}}, d, +++] \\
\text{$x, y$ are inside} & [\color{red}{\{x,y,b,c\}}] & [a, \color{red}{\{x,y,b,c\}}, d] & [---, a, \color{red}{\{x,y,b,c\}}, d, +++]
\end{array}$`

notice that:
* `b, c` are always part of center
* max of `x, a` is part of center
* min of `d, y` is part of center

```java
double median(int a[m], int b[n]) {
    if(m==0 && n==0)
        return 0;
    else if(m==0)
        return median(b);
    else if(n==0)
        return median(a);
    else if(m>n)
        a ⬌ b;

    int ai=0, aSize=m;
    int bi=0, bSize=n;
    while(true) {
        switch(aSize) {
        case 1:
            int mid = bi+bSize/2;
            if(bSize%2==1) {
                return median2(
                    b[mid],
                    bSize==1 ? a[ai] : median3(a[ai], b[mid-1], b[mid+1])
                );
            } else {
                return median3(
                    b[mid],
                    b[mid-1],
                    bSize==2 ? a[ai] : median3(a[ai], b[mid-2], b[mid+1])
                );
            }
        case 2:
            mid = bi+bSize/2;
            if(bSize%2==1) {
                return median3(
                    b[mid],
                    bSize==1 ? a[ai] : max(a[ai], b[mid-1]),
                    bSize==1 ? a[ai+1] : min(a[ai+1], b[mid+1])
                );
            } else {
                return median4(
                    b[mid],
                    b[mid-1],
                    bSize==2 ? a[ai] : max(a[ai], b[mid-2]),
                    bSize==2 ? a[ai+1] : min(a[ai+1], b[mid+1])
                );
            }
        default:
            int am = ai+aSize/2;
            int bm = bi+bSize/2;
            if(a[am]<=b[bm]) {
                if(aSize%2==0) // two elements in center
                    am--;
                int discard = min(
                    am-ai,           // #elements to left of a[am]
                    bSize-(bm-bi+1)  // # elements to right of b[bm]
                );
                ai += discard; 
                aSize -= discard;
                bSize -= discard;
            } else {
                if(bSize%2==0) // two elements in center
                    bm--;
                int discard = min(
                    bm-bi,           // #elements to left of b[bm]
                    aSize-(am-ai+1)  // # elements to right of a[am]
                );
                bi += discard; 
                aSize -= discard;
                bSize -= discard;
            }
        }
    }
}

double median2(int a, int b) {
    return (a+b)/2.0;
}

int median3(int a, int b, int c) {
    return a+b+c - min(a,b,c) - max(a,b,c);
}

double median4(int a, int b, int c, int d) {
    return (a+b+c+d - min(a,b,c,d) - max(a,b,c,d))/2.0;
}
```

Running Time: `$O(log_2(m+n))$`

check another [solution](../binary_search/median_of_two_sorted_arrays.md) using binary search

---

### References

* <http://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/>
* <https://articles.leetcode.com/median-of-two-sorted-arrays>
