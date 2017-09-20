# Smallest Window with all characters in given string

Given two strings `a` and `b`, find the smallest substring in `a` containing all characters of `b` efficiently

"this is a tes**t stri**ng" "tist"  
"abd**cba**" "abc"  
"abeca**adaeb**" "aadb"  
"afghijbzzz**cyydba**" "abcd"  
"ADOBECODE**BANC**" "ABC"

```java
int[] smallestWindow(char a[], char b[]) {
    if(a.length<b.length)
        return null;

    // calculate counts and positive entry count
    int count[] = new int[256];
    int positives = 0;
    for(char ch: b) {
        if(count[ch]==0)
            positives++;
        count[ch]++;
    }

    int smalli=0, smallj=a.length;
    for(int i=0,j=0; j<a.length; j++) {
        char ch = a[j];
        count[ch]--;
        if(count[ch]==0) {
            positives--;
            if(positives==0) {
                if(j-i<smallj-smalli) {
                    smalli = i;
                    smallj = j;
                }
            }
        }

        // minimize cur window by removing extra characters from beginning
        while(count[a[i]]<0){
            count[a[i]]++;
            i++;
        }

        if(positives==0) {
            if(j-i<smallj-smalli) {
                smalli = i;
                smallj = j;
            }
        }
    }

	if(smallj-smalli==a.length)
        return null
    else
        return new int[]{ smalli, smallj };
}
```

Running Time: `$O(n)$`

**Similar Problem:**

Given paragraph and keywords. Find minimum length snippet from paragraph which contains all keywords in any order?

### References

* <http://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/>
* <http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html>
