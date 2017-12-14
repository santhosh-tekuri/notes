# Is array consecutive?

<http://www.geeksforgeeks.org/check-if-array-elements-are-consecutive/>

Given array of unsorted numbers. check whether array contains consecutive numbers?

```bash
{5, 2, 3, 1, 4} - true, contains consecutive numbers from 1 to 5
{83, 78, 80, 81, 79, 82} - true, contains 78 to 83
{34, 23, 52, 12, 3} - false
{7, 6, 5, 5, 3, 4} - false, 5 appears twice
{2, 1, 0, -3, -1, -2} - true, contains -3 to 2
```

---

```java
boolean isConsecutive(int a[n]) {
    if(n<2)
        return true;

    int max = max(a);
    int min = min(a);

    if(max-min+1!=a.length)
        return false;

    // check duplicates
    boolean visited[n];
    for(int x: a) {
        if(visited[x-min])
            return false; // found duplicate
        visited[x-min] = true;
    }

    return true;
}
```

`@src(src/ConsecutiveArrayV1.java)`

Running Time: $O(n)$  
Auxiliary Space: $O(n)$

---

## Optimize space

can we embed `visited[]` into `a[]` to avoid additional space

let us say `a[]` contains only positive numbers, then we can change sign of `a[i-min]` to -ve to mark number `a[i]` as visited

```java
// check duplicates
for(int x: a) {
    int pos = abs(x)-min;
    if(a[pos]<0)
        return false;
    a[pos] = -a[pos]
}
```

`@src(src/ConsecutiveArrayV2.java)`

this approach can also be used in case `a[]` contains only -ve numbers.

this can be done to `a[]` with mixed signs. simply convert them to positive by substracting `min-1` to each element

```java
// convert to positive
for(int i=0; i<n; i++)
    a[i] -= min-1;
min = 1

// check duplicates
for(int x: a) {
    int pos = abs(x)-min;
    if(a[pos]<0)
        return false;
    a[pos] = -a[pos]
}
```

`@src(src/ConsecutiveArrayV3.java)`

---

<http://www.geeksforgeeks.org/check-array-elements-consecutive-time-o1-space-handles-positive-negative-numbers/>

if all numbers in array are **distinct**, we can use arithmetric progression

consider arithmetic progression: `a, a+d, a+2d, a+3d, ...`  
then sum of first `n` terms is ${n*(2a+(n-1)d) \over 2}$

```java
boolean isConsecutive(int a[n]) {
    return sum(a) == n*(2*min(a)+(n-1)*1)/2; // d is 1
}
```

this works even if array contains both positive and negative numbers
