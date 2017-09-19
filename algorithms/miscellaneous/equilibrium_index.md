# Equilibrium Index

Equilibrium index of an array is an index such that:  
sum of elements at lower indexes = sum of elements at higher indexes

```bash
[-7, 1, 5, 2, -4, 3, 0] equilibrium index is 3
           ^
6 is also equilibrium index [-7, 1, 5, 2, -4, 3, 0]
                                                 ^

[3, -3, 3, 3, -3] has two equilibrium indices
     ^  ^
```

---

scan array and update left and right sum, until they are equal

```java
int equilibriumIndex(int a[]) {
    int sum = sum(a);

    int leftSum = 0;
    int rightSum = sum;
    for(int i=0; i<a.length; i++){
        rightSum -= a[i];
        if(leftSum==rightSum)
            return i;
        leftSum += a[i];
    }
    return -1;
}
```

we are iterating array twice, so running-time: `$O(n)$`

---

if array contains non-negative integers, we need only single iteration

```java
int equilibriumIndex(int a[]) {
    int i=0;
    int sumi = a[i]; // sum(a[0...i])

    int j=a.length-1;
    int sumj = a[a.length-1]; // sum(a[j...])

    while(i!=j) {
        if(sumi<sumj) {
            i++;
            sumi += a[i];
        } else {
            j--;
            sumj += a[j];
        }
    }
    return sumi==sumj ? i : -1;
}
```

### References

* <http://www.geeksforgeeks.org/equilibrium-index-of-an-array/>
