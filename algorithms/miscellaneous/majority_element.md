# Majority Element

A majority element in array of size `n`, is an element that appears more than `n/2` times.
there is at most one such element in any array

majority of `[3, 3, 4, 2, 4, 4, 2, 4, 4]` = `4`  
majority of `[3, 3, 4, 2, 4, 4, 2, 4]` = none

## Theorem

If you take two distinct elements from array and discard them away, the majority element remains majority of the remaining elements

**Proof:**

let majority element occurred `m` times out of `n` elements, then `m/n > 1/2`  
the two discarded distinct elements can have at most one of the majority elements
so after discarding, ratio of previous majority element will be:  
`m/(n-2)` or `(m-1)/(n-2)`

it is simple to verify that:  
if `m/n > 1/2` then `m/(n-2) > (m-1)/(n-2) > 1/2`

## Boyer-Moore’s Voting Algorithm

scan the array from left to right, as soon as we encounter more than one distinct element, we can discard one instance of each element and what we are left with in the end must be majority element

```java
int getMajority(int a[]) {
    int candidate;
    int count = 0;

    for(int i=0; i<a.length; i++) {
        if(count==0) {
            candidate = a[i];
            count = 1; // resetting count
        else if(a[i]==candidate)
            count++;
        else
            count--;
    }

    return candidate;
}
```

the above code assumes that majority exists in array. if array has no majority, the return value is meaningless

```java
// returns index of majority element if present
// returns -1 if no majority element found
int findMajorityIndex(int a[]){
    int candidate = getMajority(a);

    int count = 0;
    for(int i=0; i<a.length; i++){
        if(v==a[candidateIndex]){
            count++;
            if(count>a.length/2)
                return i;
        }
    }

    return -1;
}
```

uses `$O(1)$` space with running-time `$O(n)$`

---

let us say we want to find element appearing `>= n/2` times in array

the above algorithm doesn't work.  
findCandidateIndex({1, 2, 1, 3}) returns index -1 rather than index 0 or 2

resetting count to 2 instead of 1 in getMajority works

---

## Distributed Boyer-Moore

the first phase of Boyer-Moore can be solved by combining the results for sub-sequences of the original input as long as both the `candidate` and `count` values are preserved

majority of `[1, 1, 1, 2, 1, 2, 1, 2, 2]` is `1`  
Bayer-Moore's first pass returns `candidate = 1, count = 1`

```bash
split1:  
[1, 1, 1, 2, 1]  
candidate = 1, count = 3

split2:
[2, 1, 2, 2]
candidate = 2, count = 2
```

now converge `candidate`, `count` pairs from the above splits, as if it was array containing only the value `candidate` repeated `count` times.  
`part1: [1, 1, 1] part2: [2, 2]`

```
candidate = 0
count = 0
for part: parts {
    if part.candidate = candidate
        count += part.count
    else if part.count > count {
        count = part.count - count
        candidate = part.candidate
    } else
        count = count - part.count
}
```
---

Given an array of size n, and number k. Find all elements that appear more than n/k times?

This is generalization of original problem. the original problem is case where `k=2`  
There can be at most `k-1` such elements.

here we can repeatedly take `k` distinct elements from array and discard them

scan the array from left to right, as soon as we encounter `k` distinct elements,
we can discard one instance of each element and what we are left with in the end must be candidate elements
these candidate elements should be checked again to see where they appear more than `n/k` times.

```bash
Consider k = 4, n = 9
Given array: {3 1 2 2 2 1 4 3 3}

value | value/count
------|------------
3     | 3/1
1     | 3/1,1/1
2     | 3/1,1/1,2/1
2     | 3/1,1/1,2/2
2     | 3/1,1/1,2/3
1     | 3/1,1/2,2/3
4     | 1/1,2/2            # encountered 4th distinct element
      |                    # discarding one instance of each
3     | 1/1,2/2,3/1
3     | 1/1,2/2,3/2

so possible candidates are 1, 2, 3
```

```java
class Element {
    int value;
    int count;
}

void moreThanNbK(int a[], int k) {
    int n = a.length;
    Element temp[k-1];
    for(int v: a) {
        int i = 0;
        while(i<k-1 && temp[i]!=null && temp[i].value!=v)
            i++;
        if(i==k-1) { // found k distinct elements
        }

    }
}

// TODO
```
### References

* <https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html>
* <https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm>
* <http://www.geeksforgeeks.org/given-an-array-of-of-size-n-finds-all-the-elements-that-appear-more-than-nk-times/>
* <http://www.geeksforgeeks.org/majority-element/>
