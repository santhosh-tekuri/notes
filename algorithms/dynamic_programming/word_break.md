# Word Break

Exercise 6.4 from [Algorithms by Dasgupta](https://isbnsearch.org/isbn/0073523402)  
Problem 2 in <http://www.csc.kth.se/utbildning/kth/kurser/DD2354/algokomp10/Mastarprov/Mastarprov1.pdf> 

You are given string of `n` characters `s[n]`, which you believe to be corrupted text document 
in which all punctuations has vanished ( so that it looks something like "itwasbestofthetimes"). 
You wish to reconstruct the document using dictionary, which is available in the form of boolean 
function `dict(w)` for any string `w`

`dict(w)` is:
* `true` if `w` is valid word
* `false` otherwise

give an algorithm that determines whether string `s`, can be reconstituted as sequence of valid words.
The running time should be at most `$O(n^2)$`, assuming calls to `dict` take constant time.

let `T[j]` is true if `s[0...j]` is valid string of words, false otherwise

`T[j] = dict(s[0...j]) or {T[i] and dict(S[i+1...j]) : for i=0 to j-1}`

answer is `T[n-1]`

```java
boolean isStringOfWords(char s[n]) {
    boolean T[n];
    int b[n];
    for(int j=0; j<n; j++) {
        T[j] = dict(s[0..j]);
        b[j] = -1;
        if(!T[j]) {
            for(int i=0; i<j; i++) {
                if(T[i] && dict(s[i+1...j])) {
                    T[j] = true;
                    b[j] = i;
                    break;
                }
            }
        }
    }
    
    if(T[n-1])
        printWords(s, b, n-1);
    return T[n-1];
}

void printWords(char s[], int b[], int i) {
    if(i!=-1) {
        printWords(s, b, b[i]);
        print(" ");
    }
    print(s[b[i]+1...i]);
}
```

Running Time: `$O(n^2)$`

we can embed `b[]` into `T[]`: treat `-2` as `false`

```java
boolean isStringOfWords(char s[]) {
    int T[n];
    for(int j=0; j<n; j++) {
         T[j] = dict(s[0...j]) ? -1 : -2;
         if(T[j]!=-2) {
             for(int i=0; i<j; i++) {
                 if(T[i]!=-2 && dict(s[i+1...j])) {
                     T[j] = i;
                     break;
                 }
             }
         }
    }

    if(T[n-1]!=-2)
        printWords(s, b, n-1);
    return T[n-1]!=-2;
}

void printWords(char s[], int T[], int i) {
    if(i!=-1) {
        printWords(s, T, T[i]);
        print(" ");
    }
    print(s[T[i]+1...i]);
}
```

---

## Palindrome Partitioning

<http://www.geeksforgeeks.org/dynamic-programming-set-17-palindrome-partitioning/>

Given a string, partition it with few cuts such that each partition is a palindrome.

`$aba\color{red}|b\color{red}|bbabb\color{red}|a\color{red}|b\color{red}|aba \to 5\;cuts$`

this problems resembels above problem:
* `dict(w)` is replaced by `isPalindrome(w)`
* partitioning is always possible. for example any string can be partitioned such that each partition has single char

let `m[j]` is minimum cuts needed for `s[0...i]`

`$m[j]=\begin{cases}
0 & \text{if $j=0\;\;\;$ # string of length 1 is a palindrome} \\
min(\\
\;\;j-1, & \text{# maximum partitions possible} \\
\;\;0, & \text{if $isPalindrome(s[0\dots j])$} \\
\;\;m[i]+1 & \text{for $i=0$ to $j-1$, if $isPalindrome(s[i+1\dots j])$} \\
) 
\end{cases}$`

answer is `m[n-1]`

```java
int minCuts(char s[n]) {
    int m[n], b[n];
    m[0]=0, b[0]=-1;
    for(int j=1; j<n; j++) {
        if(isPalindrome(s, 0, j)) {
            m[j]=0, b[j]=-1;
        } else {
            m[j]=j-1, b[j]=j-1;
            for(int i=0; i<j; i++) {
                if(m[i]+1<m[j] && isPalindrome(s, i+1, j)) {
                    m[j] = m[i]+1;
                    b[j] = i;
                }
            }
        }
    }
    printPartitions(s, b, n-1);
    return m[n-1];
}

void printPartitions(char s[], int b[], int i) {
    if(i!=-1) {
        printPartitions(s, b, b[i]);
        print("|");
    }
    print(s[b[i]+1...i]);
}
```

if we assume `isPalindrome(...)` is `$O(1)$`, then running time is `$O(n^2)$`  
in order to make `isPalindrome(...)` `$O(1)$`, we can do pre-processing:

let `p[i][j]` is true if `s[i...j]` is palindrome

`$p[i][j]=\begin{cases}
true & \text{if $i=j$} & \text{# string of length 1} \\
s[i]=s[j] & \text{if $i+1=j$} & \text{# string of length 2} \\
s[i]=s[j]\;and\;p[i+1][j-1]
\end{cases}$`

```java
boolean[][] preprocess(char s[n]) {
    boolean p[n][n];
    for(int i=0; i<n; i++)
        p[i][i] = true;
    for(int len=2; len<=n; len++) {
        for(int i=0; i<=n-len; i++) {
            int j = i+len-1;
            p[i][j] = s[i]==s[j];
            if(len>2)
                p[i][j] &&= p[i+1][j-1];
        }
    }
    return p;
}
```

Running Time: `$O(n^2)$`

```java
boolean isPalindrome(char p[], int i, int j) {
    return p[i][j];
}
```

---

## List All Possible Palindrome Partitioning

<https://leetcode.com/problems/palindrome-partitioning/description/>

`$aab \to \begin{matrix}
aa\color{red}|b \\
a\color{red}|a\color{red}|b
\end{matrix}$`

use memoization, as `m[n-1]` does not use all subproblems

```java
List listPartitions(char s[n]) {
    boolean p[][] = preprocess(s);
    List partitions[n];
    char[] get(int j) {
        if(partitions[j]==null) {
            List list = new List();
            if(p[0,j])
                list.add(s[0...j]);
            for(int i=0; i<j; i++) {
                if(p[i+1][j]) {
                    for(char partition[]: get(i))
                        list.add(partition+'|'+s[i+1...j]);
                }
            }
            partitions[j] = list;
        }
        return partitions[j];
    }
    return get(n-1);
}
```

---

### Longest Palindromic Substring

<http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/>  
http://www.geeksforgeeks.org/longest-palindromic-substring-set-2/

`$
for\color{red}{geeksskeeg}for \\
\color{red}{abaaba} \\
\color{red}{abcbabcba}bcba
$`

Why `LCS(s, reverse(s))` does not work ?  
`LCS("abxyba", "abyxba")` returns `ab` or `ba`. neither of them are palindromes

compute `p[][]` as in above problem.  
answer is `max(j-i+1) where p[i][j]=true`

```java
int[2] lps(char s[n]) {
    boolean p[n][n];
    int mi=0, mj=0;
    for(int i=0; i<n; i++)
        p[i][j] = true;
    for(int len=2; len<=n; len++) {
        for(int i=0; i<=n-len; i++) {
            int j = i+len-1;
            p[i][j] = s[i]==s[j];
            if(len>2)
                p[i][j] &&= p[i+1][j-1];
            if(p[i][j] && j-i>mj-mi) {
                mi = i;
                mj = j;
            }
        }
    }
    return new int[]{mi, mj};
}
```

Time Complexity: `$O(n^2)$`  
Space Complexity: `$O(n^2)$`

if you notice carefully, you notice that `p[i][j]` is only used in `p[i-1][j+1]`  
* i.e. there are no recurring problems. thus we can avoid space
* base cases to start are:
    * `p[i][i] for i=0 to n-1`
        * we are fixing center to `i`, and computing all palindromes of odd length with center `i`
    * `p[i][i+1] for i=0 to n-2`
        * we are fixing center to `(i,i+1)`, and computing all palindromes of even length with center `(i,i+1)`
* notice that, once `p[i][j]` becomes `false`, all subproblems using it also compute to `false`

```java
int[2] lps(char s[n]) {
    int mi=0, mj=0;
    for(int c=0; c<n; c++) {
        for(int i=c,j=c; i>=0 && j<n && s[i]==s[j]; i--, j++) {
            if(j-i>mj-mi) {
                mi = i;
                mj = j;
            }
        }
        for(int i=c,j=c+1; i>=0 && j<n && s[i]==s[j]; i--, j++) {
            if(j-i>mj-mi) {
                mi = i;
                mj = j;
            }
        }
    }
    return new int[]{mi, mj};
}
```

Time Complexity: `$O(n^2)$`  
Space Complexity: `$O(1)$`


this problem can be solved in linear time using [Manacher's Algorithm](../miscellaneous/longest_palindromic_substring.md)
             
