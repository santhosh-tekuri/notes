# Quick Sort

* pick an element called **pivot** from the array
* reorder array such that all elements less than pivot come before the pivot and 
  all elements greater than pivot come after the pivot. Equal values can go either way.
  This step is called **partition**. After partitioning, the pivot is in its final position
* recursively apply above steps to left and right partitions

```java
void quickSort(int a[]) {
    quickSort(a, 0, a.length-1);
}

void quickSort(int a[], int lo, int hi) {
    if(lo<hi) {
        int p = partition(a, lo, hi);
        quickSort(a, lo, p-1);
        quickSort(a, p+1, high);
    }
}

int partition(int a[], int lo, int hi) {
    int p = a[hi]; // last element is pivot
    int i = lo-1;
    for(int j=lo; j<hi; j++){
        // a[lo..i] is left partition. a[i+1..j-1] is right partition
        if(a[j]<p){
            i++;
            a[i] ⟷ a[j];
            swap(a, i, j);
        }
    }
    a[i+1] ⟷ a[right]; // move pivot to its final place
    return i+1;
}
```

to randomize pivot selection:

```java
int randomizedPartition(int a[], int lo, int hi) {
    int r = random(lo, hi);
    a[r] ⟷ a[hi];
    return partition(a, lo, hi);
}
```

### Worst-case: 

happens when pivot is either smallest or largest element, resulting partitions of sizes `0` and `n-1`

`$\begin{align}
T(n)&= T(n-1) + T(0) + O(n) \\
    &= T(n-1) + O(n) \\
    &= O(1) + \dots + O(n-1) + O(n) \\
    &= O(n^2) \\
\end{align}$`

### Best-case:

happens when partitions are nearly equal sizes

`$\begin{align}
T(n)&= 2T({n \over 2}) + O(n) \\
    &= O(n \log_2 n) \\
\end{align}$`

see [Dutch National Flag Problem](../miscellaneous/dutch_national_flag.md) for various ways of partitioning

---

## Iterative Quick Sort

```java
void quickSort(int a[]) {
    Stack stack = new Stack();
    stack.push(0); stack.push(a.length-1);
    while(!stack.isEmpty()) {
        int hi = stack.pop();
        int lo = stack.pop();
        if(lo<hi) {
            int p = partition(a, lo, hi);
            stack.push(lo); stack.push(p-1);
            stack.push(p+1); stack.push(hi);
        }
    }
}
```

---

## k<sup>th</sup> smallest element

Chapter 9.2 from CLRS

```java
int kthSmallest(int a[], int k) {
    return kthSmallest(a, 0, a.length-1, k);
}

int kthSmallest(int a[], int lo, int hi, int k) {
    if(lo==hi)
        return a[lo];
    int p = partition(a, lo, hi);
    int pos = p-lo+1;
    if(k==pos)
        return a[p];
    else if(k<pos)
        return kthSmallest(a, lo, p-1, k);
    else
        return kthSmallest(a, p+1, hi, k-pos);
}
```

Best-case: `$T(n) = T({n \over 2}) + O(n) = O(n)$`  
Worst-case: `$T(n) = T(n-1) + O(n) = O(n^2)$`

### Iterative Version

```java
int kthSmallest(int a[], int k) {
    int lo=0, hi=a.length-1;
    while(true) {
        if(lo==hi)
            return a[lo];
        int p = partition(a, lo, hi);
        int pos = p-lo+1;
        if(k==pos)
            return a[p];
        else if(k<pos)
            hi = p-1; 
        else {
            lo = p+1;
            k -= pos;
        }
    }
}
```

---

## k<sup>th</sup> largest element

instead of doing same as above, we can find where k<sup>th</sup> largest lies in sorted array

```java
int kthLargest(int a[], int k) {
    int pos = a.length - k;	// k-th largest element lies at a[pos] when sorted

    int lo=0; int hi=a.length-1;
    while(true) {
        if(lo==hi)
            return a[lo]
        int p = partition(a, lo, hi);
        if(p==pos)
            return a[p];
        else if(pos<p)
            hi = p-1;
        else
            lo = p+1;
    }
}
```

---

## Arrange Players

We have `n` players. Chess game is conducted with every possible pair, and the result is recorded.
Can you arrange those `n` players in a line, such that if we pick any two adjacent players `x` and `y`,
`x` must have won over `y` in chess.

it means, if we pick a player `x` in line, then:
* `x` must have lost with player before him
* `x` must have wont with player after him

use partioning technique:
* pick player `x`
* move all players who won with `x` to before `x`
* move all players who lost with `x` to after `x`
* repeat the steps to left and right partitions

---

## Water Jugs

Problem 8-4 from CLRS  
<http://ripcrixalis.blog.com/2011/02/08/clrs-chapter-8/>

Suppose that you are given n red and n blue water jugs, all of different shapes and sizes.
All red jugs hold different amounts of water, as do the blue ones. Moreover, for every red
jug, there is a blue jug that holds the same amount of water, and vice versa.

It is your task to find a grouping of the jugs into pairs of red and blue jugs that hold
the same amount of water. To do so, you may perform the following operation: pick a pair
of jugs in which one is red and one is blue, fill the red jug with water, and then pour
the water into the blue jug. This operation will tell you whether the red or the blue
jug can hold more water, or if they are of the same volume. Assume that such a comparison
takes one time unit. Your goal is to find an algorithm that makes a minimum number of 
comparisons to determine the grouping. Remember that you may not directly compare 
two red jugs or two blue jugs.

### Naive Implementation:

compare each red jug with each blue jug  
#comparisons = `$O(n^2)$`

### Quicksort approach:

```java
void matchJugs(R, B) {
    if R.size==0
        return;
    if R.size==1 {
        let R={r}, B={b}
        print (r,b)
    } else {
        r = R.choseRandom()
        compare r with every jug B
        B1 = jugs in B smaller than r
        B2 = jugs in B larger than r
        b = jug in B with same size as r

        compare b with every jug in R-{r}
        R1 = red jugs smaller than b
        R2 = red jugs larger than b

        print (r, b)
        matchJugs(R1, B1)
        matchJugs(R2, B2)
    }
}
```

#comparisons = `$O(n \log_2 n)$`  
in worst-case #comparisons = `$O(n^2)$`

---

## Nuts and Bolts

Exercise 2.3.15 from Algorithms by Robert Sedgewick and Kevin Wayne

You have a mixed pile of `N` nuts and `N` bolts and need to quickly find the 
corresponding pairs of nuts and bolts. Each nut matches exactly one bolt, 
and each bolt matches exactly one nut. By fitting a nut and bolt to- gether, 
you can see which is bigger, but it is not possible to directly compare two 
nuts or two bolts. Give an efficient method for solving the problem.

this problems is same as `Water Jugs` problem explained above

