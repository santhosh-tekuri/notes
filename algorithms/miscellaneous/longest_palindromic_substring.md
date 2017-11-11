# Longest Palindromic Substring

`$
for\color{red}{geeksskeeg}for \\
\color{red}{abaaba} \\
\color{red}{abcbabcbabcba} \\
ba\color{red}{anana}s \\
abr\color{red}{aca}dabra
$`

---

consider following palindromes:

```bash
[---|---]       [--|--]
a b a b a       a b b a
0 1 2 3 4       0 1 2 3
```

in first string center is at `2` and in second string center is between `1` and `2`

transform the string by inserting some boundary character say `#`:
* between characters
* before first character
* after last character

```bash
[---------|---------]      [-------|-------]
# a # b # a # b # a #      # a # b # b # a #
0 1 2 3 4 5 6 7 8 9 10     0 1 2 3 4 5 6 7 8
```

notice that:
* now center is a real index in both cases
* length of transformed string is always odd
* even positions are boundary characters, odd positions are original characters
* string `s[n]` is transformed to `t[2n+1]` ( `n` characters + `n-1` between chars + `2` at first and last)
* #chars after/before the center (called *span*) = length of original palindrome

transformed string is easier to work, because of real index

### Mirror Indices

```bash
      x   c   y
[-----|---|---|-----]
# a # b # a # b # a #
0 1 2 3 4 5 6 7 8 9 10
```

indices `x` and `y` mirror each other with center `c`  
mirror of index `i`:
* if `i>c` then `c-(i-c)=2c-i`
* if `i<c` then `c+(c-i)=2c-i`

### Largest Palindrome with Center

```bash
      ci              c               cj
      [---------------|---------------]
# c # a # x # b # a # b # a # b # x # a # b # a #

```

let us denote largest palindrome with center `c` as p<sub>c</sub>  
its left boundary as `ci`, right boundary as `cj`

```bash
      ci              c               cj
      [---------------|---------------]
# d # a # a # b # a # b # a # b # a # a # b # a #
          [-------|-------]
                  [-------|-------]
```

pick any index `x` where `ci<x<cj` and its mirror index `y`  
if p<sub>x</sub> is within p<sub>c</sub> i.e. `xi>ci && xj<cj` then:
* p<sub>y</sub> is also within p<sub>c</sub>
* length of p<sub>y</sub> = length of p<sub>x</sub>

note that, if p<sub>x</sub> touches boundary `ci` or `cj`, above two statements may not be true  
for example:

```bash
      ci              c               cj
      [---------------|---------------]
# d # b # a # b # a # b # a # b # a # b # a # a #
      [-----------|-----------]
          [---------------|---------------]
```

:bulb: largest palindrome with center always begins and ends with `#`

### Manarche's Algorithm

let `m[i]` is span of largest palindrome with center `i`  
answer is `max(m[])`

trivial computation of `m[]` will be `$O(n^2)$`  
but we can use above insight, to reduce it to `$O(n)$`

```java
char[] lps(char s[n]) {
    // beware of empty string check
    if(n==0)
        return new char[0];

    // construct transformed string
    char t[2*n+1];
    for(int i=0; i<n; i++) {
        t[2*i] = '#';
        t[2*i+1] = s[i];
    }
    t[t.length-1] = '#';

    int m[t.length];
    int maxi = 0;
    m[0]=0;
    int c=0, r=0; // reference palindrome's center and right
    for(int i=1; i<t.length; i++) {
        if(i<r) {
            int j = 2*c-i; // i's mirror
            m[i] = min(r-i, m[j]);
        } else
            m[i] = 0;
        
        if(i+m[i]>=r) { 
            // expand
            int x=i-m[i]-1, y=i+m[i]+1;
            while(x>=0 && y<t.length && t[x]==t[y]) {
                m[i]++, x--, y++;
            }

            if(i+m[i]>r) {
                c=i, r=i+m[i]; // update reference palindrome
                if(m[i]>m[maxi])
                    maxi = i;
            }
        }
    }

    int i=maxi-m[maxi], j=maxi+m[maxi];
    assert t[i]=='#' && t[j]=='#';
    return s[(i+1)/2...(j-1)/2];
}
```

Running Time: `$O(n)$`

we can avoid constructing transformed string `t[]`, because `t[i]` is `i%2==0 ? '#' : s[i/2]`  
* replace `t.length` with `2*n+1`
* replace `t[x]==t[y]` with `x%2==0 || s[x/2]==s[y/2]` (both `x` and `y` are either even or odd)

this problem can also be solved using [Dynamic Programming](../dynamic_programming/word_break.md#longestpalindromicsubstring)

---

### References

* <https://articles.leetcode.com/longest-palindromic-substring-part-ii/>
* <https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher.27s_algorithm>
