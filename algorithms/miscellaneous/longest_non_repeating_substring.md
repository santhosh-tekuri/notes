# Longest non-repeating substring

Given a string, find longest substring without repeating characters.

```bash
"ABDEFGABEF" => "ABDEFG", "BDEFGA" and "DEFGAB", with length 6
"BBBB" => "B"
"GEEKSFORGEEKS" => "EKSFORG" and "KSFORGE"
```

---

```java
// returns first and last index
int[] longestSubstringWithoutRepeating(char a[n]){
    int lastIndex[256];
    for(int i=0; i<lastIndex.length; i++)
        lastIndex[i] = -1;

    int i = 0, j = 0; // ch[i..j-1] is the non-repeating substring
    int maxi = 0, maxj = 0;
    while(j<n) {
        char ch = a[j];
        if(lastIndex[ch]>=i) { // ch is repeated
            if(j-i>maxj-maxi) {
                maxi = i;
                maxj = j;
            }
            i = lastIndex[ch]+1;
        }
        lastIndex[ch] = j;
        j++;
    }
    if(j-i>maxj-maxi) {
        maxi = i;
        maxj = j;
    }
    return new int[]{maxi, maxj-1};
}
```
`@src(src/LongestNonRepeatingSubstring.java)`

Running Time: $O(n)$

::: tip box
we can avoid initalizing `lastIndexOf[]` with `-1`s
* leave it with default inital value `0`s
* replace `lastIndexOf[ch]` with `lastIndexOf[ch]-1`

:::

---

### References

* <http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/>
