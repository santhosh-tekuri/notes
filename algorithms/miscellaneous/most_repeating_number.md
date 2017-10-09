# Most Repeating Number

Given array of size `n`, containing numbers in range `0` to `k-1`, where `k` is +ve number and `k<=n`  
Find most repeating number in the array?

a[] = {1, **2**, **2**, **2**, 0, **2**, 0, **2**, 3, 8, 0, 9, **2**, 3}, k=10  
a[] = {2, **3**, **3**, 5, **3**, 4, 1, 7}, k=8

---

### Use count[k] array

scan array `a[]` and increment `count[a[i]]`  
answer = index of max value in `count[]`

```java
int mostRepeating(int a[], int k) {
    int count[k];
    int maxIndex = 0;
    for(int v: a) {
        count[v]++;
        if(maxIndex!=v && count[v]>count[maxIndex])
            maxIndex = v;
    }
    return maxIndex;
}
```

this takes `$O(n)$` time and `$O(k)$` space

---

### Can we embed count[] in a[] ?

scan array and for each element increment value at `a[i]` by `k`  
`a[i]%k` gives original value  
note: you have to increment original value  
answer = index of max value in modified array

```java
int mostRepeating(int a[], int k) {
    for(int v: a)
        a[v%k] += k;

    int max=-1, maxIndex=-1;
    for(int i=0; i<k; i++) {
        if(a[i]>max) {
            max = a[i];
            maxIndex = i;
        }
        a[i] = a[i]%k; // reverting to original value
    }

    return maxIndex;
}
```

this takes O(n) time and O(1) space, but can cause integer overflow

---

### Get every most occurring number ?

`a[]={2, 3, 2, 3}, k=4` => most repeating numbers are `2, 3`

after modifying array as above, `a[i]/k` gives how many times `i` is repeating

```java
Set mostRepeating(int a[], int k) {
    for(int v: a)
        a[v%k] += k;

    int maxCount = 0;
    for(int i=0; i<k; i++) {
        if(a[i]/k>maxCount)
            maxCount = a[i]/k;
    }

    Set s;
    for(int i=0; i<k; i++) {
        if(a[i]/k==maxCount)
            s.add(i);
        a[i] = a[i]%k; // reverting to original value
    }
    return s;
}
```

---

### References

* <http://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/>
