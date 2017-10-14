# Largest Multiple of 3

<http://www.geeksforgeeks.org/find-the-largest-number-multiple-of-3/>  
<https://ideone.com/txZ88G>

Given array of numbers in range `[0, 9]`, find the largest multiple of `3` that can be
formed from array elements.

[8, 1, 9] → 981  
[8, 1, 7, 6, 0] → 8760

---
* sort array in decreasing order
* if `sum(array)%3` is:
    * `0`
        * the whole number is divisible by `3`
        * printing the array gives number
    * `1`
        * remove smallest element remainder `1`
        * if no such element exists, remove two smallest elements with remainder `2`
        * if no such elements exists largest multiple of 3 cannot be formed
    * `2`
        * remove smallest element with remainder `2`
        * if no such element exists, remove two smallest elements with remainder `1`
        * if no such elements exist, largest multiple of 3 cannot be formed

```java
void printLargestMultipleOf3(int a[]) {
    sortDescending(a);

    switch(sum(a)%3) {
    case 0:
        printNumber(a, -1, -1);
        return;
    case 1:
        int i = lastIndexOf(a, a.length-1, 1);
        if(i!=-1) {
            printNumber(a, i, -1);
            return;
        }
        i = lastIndexOf(a, a.length-1, 2);
        j = lastIndexOf(a, i-1, 2);
        if(i>=0 && j>=0)
            printNumber(a, i, j);
        return;
    case 2:
        int i = lastIndexOf(a, a.length-1, 2);
        if(i!=-1) {
            printNumber(a, i, -1);
            return;
        }
        i = lastIndexOf(a, a.length-1, 1);
        j = lastIndexOf(a, i-1, 1);
        if(i>=0 && j>=0)
            printNumber(a, i, j);
    }
}

void printNumber(int a[], int exclude1, int exclude2) {
    for(int i=0; i<a.length; i++) {
        if(i!=exclude1 && i!=exclude2)
            print(a[i]);
    }
}

int lastIndexOf(int a[], int from, int remainder) {
    while(from>=0) {
        if(a[from]%3==remainder)
            return from;
        from--;
    }
    return -1;
}
```

The above solution does not work, if the array contains multi-digit numbers.  
[1, 1, 1, 32] prints `111`, but expected `321`

---

### Largest Multiple of 2, 3 and 5

<http://www.geeksforgeeks.org/find-the-largest-multiple-of-2-3-and-5/>

A number is divisible by both `2` and `5`, if it ends with `0`

* if array does not contain `0`, such number cannot be formed
* otherwise, simply find largest multiple of `3` using above algorithm (note that it always ends with zero)

