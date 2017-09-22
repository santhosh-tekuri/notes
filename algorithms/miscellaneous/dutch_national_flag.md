# Dutch National Flag

Given `n` objects colored red, white and blue, sort them so that objects of same color are adjacent,
with the colors in the order red, white and blue. To generalize the problem, color can be any thing.

![dutch_flag.gif](files/dutch_flag.gif)

if colors are numbered(red=0, white=1, blue=2), it becomes quicksort's partition operation. we partition
into "colors less than pivot" and "colors greater than pivot"

**NOTE:** one could just count the number of each color and overwrite the array contents accordingly.
However the intent is that the array items are reasonably complex and that they happen to have an 
attribute such as color.

---

## 2 Colors

```bash
000000???????1111111
      i     j
```

have two variable `i`, `j`  
elements before `i` = section with `0`s  
elements after `j` = section with `1`s  
thus elements between `[i,j]` = unknown

```java
void partition2(int a[]) {
    int i=0, j=a.length-1;
    while(i<=j) {
        if(a[i]==0)
            i++;
        else {
            swap(a, i, j);
            j--;
        }
    }
}
```

Running Time: `$O(n)$`

**2-way Quicksort:**
* pick `x` (possibly median)
* partition array into two sections: `<=x`,  `>x`
* sort each section recursively

---

## 3 Colors

```bash
0000001111111???????2222222
      i      j     k
```

have two variables `i`, `j`, `k`  
elements before `i` = section with `0`s  
elements from `i` to `j-1` = section with `1`s  
element after `k` = section with `2`s
thus elements between `[j,k]` = unknown

```java
void partition3(int a[]) {
    int i=0, j=0, k=a.length-1;
    while(j<=k) {
        if(a[j]==0) {
            swap(a, i, j);
            i++; j++;
        } else if(a[j]==1)
            j++;
        else {
            swap(a, j, k);
            k--;
        }
    }
}
```

Running Time: O(n)

**3-way Quicksort**:
* pick `x` (possibly median)
* partition array into three sections: `<x`, `=x`, `>x`
* sort each section recursively

OR

* pick two elements `v1` and `v2`, such that `v1<v2`
* partition array into three sections: `<=v1`, `>v1 && <=v2`, `>v2`
* sort each section recursively

---

This can be extended to four, or even more. But it becomes more and more
complex to write, and constant of proportionality in its running-time increases.

### References

* <http://users.monash.edu/~lloyd/tildeAlgDS/Sort/Flag/>
