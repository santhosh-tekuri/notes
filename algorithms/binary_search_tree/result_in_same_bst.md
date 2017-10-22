# Result in Same BST ?

Given 2 arrays of integers, find if we create BST from those arrays,
whether they are identical or not but without creating BST ?

for example, following arrays create same BST:
* `{2, 4, 3, 1}` and `{2, 1, 4, 3}`
* `{8, 3, 6, 1, 4, 7, 10, 14, 13}` and `{8, 10, 14, 3, 6, 4, 1, 7, 13}`

---

if we see BST insert algorithm:
* a node inserted always as child to existing node in BST
* i.e in array, a node always come after its parent
* in other words, a node's children always come after it

to understand this, see few examples:

```bash
  B
 / \
A   C
```
* in input array, `A` and `C` should come after `B`
* so possible input arrays are:
    * `{B, A, C}`
    * `{B, C, A}`

```bash
    D
   / \
  B   E
 / \
A   C
```
* in input array:
    * `A` and `C` should come after `B`
    * `B` and `E` should come after `D`
* so possible input arrays are:
    * `{D E B A C}` `{D B E A C}` `{D B A E C}` `{D B A C E}`
    * `{D E B C A}` `{D B E C A}` `{D B C E A}` `{D B C A E}`

we can use range property to find children of a node

```java
boolean isSameBST(int a[], int b[]) {
    if(a.length!=b.length)
        return false;
    return isSameBST(a, b, 0, 0, -INF, +INF);
}

boolean isSameBST(int a[], int b[], int ai, int bi, int min, int max) {
    int aChild = findIndex(a, ai, min, max);
    int bChild = findIndex(b, bi, min, max);

    if(aChild==-1 && bChild==-1) // leaf in both arrays
        return true;
    if(aChild==-1 || bChild==-1) // leaf in one array & non-leaf in another
        return false;
    if(a[aChild]!=b[bChild]) // child found must be same in both arrays
        return false;
    return isSameBST(a, b, aChild+1, bChild+1, min, a[aChild]) // left subtree `<=`
           && isSameBST(a, b, aChild+1, bChild+1, a[aChild]+1, max); // right subtree '>`
}

int findChild(int x[], int from, int min, int max) {
    while(from<x.length) {
        if(x[from]>=min && x[from]<=max)
            return from;
        from++;
    }
    return -1;
}
```

Running-Time: `$O(n)$`

---

### References

* <http://www.geeksforgeeks.org/check-for-identical-bsts-without-building-the-trees/>
