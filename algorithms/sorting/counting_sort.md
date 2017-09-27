# Counting Sort

Counting Sort assumes that input array contains integers ranging from `0` to `k`.

**Basic Idea:**

for each element `x` determine number of elements less than `x`.
this can be used to place element `x` directly into its final position.

Ex: if there are `17` elements less than `x`, then final position of `x` will be `17`.

The above scheme must be modified to handle duplicate elements in input.

```java
int[] countingSort(int a[], int k) {
    int c[k+1]; // initially all elements are zero

    for(int v: a)
        c[v]++;
    // now c[i] contains the number of elements equal to i

    for(int i=1; i<=k; i++)
        c[i] += c[i-1];
    // now c[i] contains number of elements less than or equal to i
    // in other words: c[i] contains (last index of i)+1

    int b[a.length];
    for(int i=a.length-1; i>=0; i--) {
        b[c[a[i]]-1] = a[i];
        c[a[i]]--;
    }
    return b;
}
```

Time Complexity: `$O(n+k)$`

This is not in-place algorithm but is stable i.e numbers with same value appear
in the output array in the same order as they do in the input array.

note that, if the last `for` loop is changed to fill left to right, it still works.
but won't be stable. Stability is important when there is additional data besides the key.

rather than counting #elements "less than or equal", we can count "less than":

```java
int[] countingSort(int a[], int k) {
    int c[k+1]; // initially all elements are zero

    for(int v: a)
        c[v]++;
    // now c[i] contains the number of elements equal to i

    int total = 0;
    for(int i=0; i<=k; i++) {
        int temp = c[i];
        c[i] = total;
        total += temp;
    }
    // in other words: c[i] contains first index of i

    int b[a.length];
    for(int i=0; i<a.length; i++){
        b[c[a[i]]] = a[i];
        c[a[i]]++;
    }
    return b;
}
```

the above implementation is stable.

---

## Inplace

<http://www.eecs.wsu.edu/~ananth/CptS223/Lectures/sorting.pdf>

```java
void countingSort(int a[], int k){
    int count[k+1]; // initially all elements are zero

    for(int v: a)
        count[v]++;
    // now count[i] contains the number of elements equal to i

    int total = 0;
    for(int i=0; i<=k; i++) {
        int temp = count[i];
        count[i] = total-1;
        total += temp;
    }
    // now count[i] contains last index of i

    int i = 0;
    while(i<a.length) {
        ai = a[i];
        if(i==c[ai])
            i++;
        else 
            swap(a, i, c[ai]); // move a[i] to correct position
        c[ai]--;
    }
}
```

this is not stable.

---

Given n integers in the range `0` to `k`, describe an algorithm that preprocesses its input
and then answers any query about how many of the `n` integers fall into a range `[a..b]` in `$O(1)$` time ?
Your algorithm should use `$\theta(n + k)$` preprocessing time.

The algorithm uses up to second for loop of counting sort as *preprocessing step*. After preprocessing
`count[i]` contains #elements `<=i`

```java
int CountInRange(int a, int b) {
    if(a==0)
        return count[b];
    else
        return count[b] - count[a-1];
}
```
