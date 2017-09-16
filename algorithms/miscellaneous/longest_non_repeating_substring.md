# Longest non-repeating substring

```java
// returns first and last index
int[] longestSubstringWithoutRepeating(char a[]){
    int lastIndex[] = new int[256];
    for(int i=0; i<lastIndex.length; i++)
        lastIndex[i] = -1;

    int i = 0, j = 0; // ch[i..j-1] is the non-repeating substring
    int maxi = 0, maxj = 0;
    while(j<a.length) {
        char ch = a[j];
        if(lastIndex[ch]>=i) {
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

### References

* <http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/>
