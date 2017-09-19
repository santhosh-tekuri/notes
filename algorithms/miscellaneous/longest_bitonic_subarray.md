# Longest Bitonic Subarray

a sequence is called bitonic, if it is first increasing and then decreasing  
a sequence in increasing order is bitonic with decreasing part as empty  
a sequence in decreasing order is bitonic with increasing part as empty

if `a[i...j]` is the longest bitonic subarray starting at `a[i]`, it is enough to start searching from `a[j]` for next bitonic subarray

```java
int[] longestBitonicSubarray(int a[]) {
    int maxStart = 0;
    int maxLen = 0;

    int i = 0;
    while(i+maxLen<a.length-1) {
        int j = i+1;
        while(j<a.length && a[j]>a[j-1])
            j++;
        while(j<a.length && a[j]<a[j-1])
            j++;
        if(j-i>maxLen) {
            maxLen = j-i;
            maxStart = i;
        }
        i = j-1;
    }
    
    return new int[]{ maxStart, maxLen };
}
```

### References

* <http://www.geeksforgeeks.org/maximum-length-bitonic-subarray/>
