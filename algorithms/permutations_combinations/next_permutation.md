# Next Permutation

Find Next lexicographical permutation?

`$(0, 1, 2, 5, 3, 3, 0) => (0, 1, 3, 0, 2, 3, 5)$`

---

### Step 1: Find longest non-increasing suffix

`$(0, 1, 2, \color{green}{5, 3, 3, 0})$`

Notice that we can't make next permutation just by reordering elements in this suffix.
This is because this suffix is already longest permutation

NOTE: this suffix can't be empty

### Step 2: find pivot, which is element immediately left to suffix

`$(0, 1, \color{red}2, \color{green}{5, 3, 3, 0})$`

if there is no pivot, it means the given permutation is the last permutation i.e, entire permutation is non-increasing

### Step 3: find rightmost element in suffix, that is larger than pivot. we call this successor

`$(0, 1, \color{red}2, \color{green}{5, 3, \color{blue}3, 0})$`

### Step 4: swap pivot with successor

`$(0, 1, \color{blue}3, \color{green}{5, 3, \color{red}2, 0})$`

### Step 5: sort the suffix to get next permutation

because the suffix is already in non-increasing order, we can simply reverse it

`$(0, 1, \color{blue}3, \color{green}{0, \color{red}2, 3, 5})$`

```java
boolean nextPermutation(int a[]) {
    int i = a.length-1;
    while(i>0 && a[i-1]>=a[i])
        i--;
    // a[i..] is the suffix

    if(i==0)
        return false;
    // a[i-1] is pivot

    int j = a.length-1;
    while(a[j]<=a[i-1])
        j--;
    // a[j] is successor

    swap(a, i-1, j);

    //reverse a[i..]
    int j = a.length-1;
    while(i<j) {
        swap(a, i, j)
        i++;
        j--;
    }
    return true;
}
```

## Similar Problem

<http://www.spoj.com/problems/JNEXT/>

Given an array of digits which is forming a number currently (will be called as given number).
Find the just next greater number which can be formed using digits of given number.

---

## Previous Permutation ?

`$(0, 1, 3, 0, 2, 3, 5) => (0, 1, 2, 5, 3, 3, 0)$`

### find longest non-decreasing suffix and pivot

`$(0, 1, \color{red}3, \color{green}{0, 2, 3, 5})$`

### find rightmost element in suffix, that is smaller than pivot

`$(0, 1, \color{red}3, \color{green}{0, \color{blue}2, 3, 5})$`

### swap them and reverse the suffix

`$(0, 1, \color{blue}2, \color{green}{5, \color{red}3, 3, 0})$`

---

### References

* <http://nayuki.eigenstate.org/page/next-lexicographical-permutation-algorithm>
