# Subarray with given Sum

<http://www.geeksforgeeks.org/find-subarray-with-given-sum/>

Given array of **non-negative integers**, find subarray with given sum ?  
subarray must contain at least one element

{1, 4, **20, 3, 10**, 5}, sum = 33  
{**1, 4, 0, 0, 3**, 10, 5}, sum = 7  
{1, 4}, sum = 0 => none

---

* initialize `total` as first element. (`total` indicates sum of current subarray)
* from second element onwards add it to `total`
    * if `total==sum`, we found subarray
    * otherwize, remove beginning elements until `total>sum`

```java
int[] subarrayWithSum(int a[], int sum) {
    int total = a[0];
    int i = 0;
    for(int j=1; j<=a.length; j++) {
        // remove beginning elements until total>sum
        while(total>sum && i<j-1) {
            total -= a[i];
            i++;
        }
        if(total==sum)
            return new int[]{i, j-1};
        if(j<a.length)
            total += a[j];
    }
    return null;
}
```

at most 2 operations are applied on every element
* element is added to `total`
* element is subtracted from `total`

so number of operations = `2n`  
thus Running Time: `$O(n)$`

---

<http://stackoverflow.com/a/14953506>

What if array contains both +ve and -ve numbers, and want to print all subarrays ?

* compute `prefixSum` for each element and store it in hashtable: `ht[prefixSum]` = linked list of indices
* for each element in array from left to right:
    * search for `prefixSum-sum` in hashtable, to get indices of where subarrays start

```java
void subarraysWithSum(int a[], int sum) {
    Hashtable ht = new Hashtable();
    ht[0] = new LinkedList().append(-1);
    int prefixSum = 0;
    for(j=0; j<a.length; j++) {
        prefixSum += a[j];
        append(ht[prefixSum], j); // check null linked list
        if(ht[prefixSum-sum]!=null) {
            for(node: ht[prefixSum-sum])
                println(node.index+1, j)
        }
    }
}
```

Runnting time is `$O(n)$` with `$O(n)$` additional space

---

## Largest subarray with equal number of `0`s and `1`s

<http://algods-cracker.blogspot.in/2011/09/amazon-question-array-is-containing.html>  
<http://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/>

Given array containing only `0`s and `1`s. Find largest subarray which contain equal number of `0`s and `1`s ?

`{1, 0, 1, 1, 1, 0, 0}`  
Output: `1` to `6` (Starting and Ending indexes of output subarray)

`{1, 1, 1, 1}`  
Output: No such subarray

`{0, 0, 1, 1, 0}`  
Output: `0` to `3` OR `1` to `4`

If we treat `0`s as `-1`, then previous algorithm can be used to find longest subarray with sum `0`

Since we need only largest subarray, we can store only first value of linked list in hashtable.

if array size is `n`, then:
* `max(prefixSum) = +n`
* `min(prefixSum = -n`

so instead of using regular hashtable, we can use array `ht[2n]` as hashtable  
where value for given prefixSum is stored at `ht[prefixSum+n]`

