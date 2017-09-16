# Inorder, Preorder => Postorder

```bash
Inorder = {4, 2, 5, 1, 3, 6}
Preorder = {1, 2, 4, 5, 3, 6}

    1
   / \
  2   3
 / \   \
4   5   6

Postorder = {4, 5, 2, 6, 3, 1}
```

---

```java
void printPostorder(int in[], int pre[]) {
    printPostorder(in, 0, pre, 0, in.length);
}

static void printPostorder(int in[], int inIndex, int pre[], int preIndex, int len) {
    int i = indexOf(in, inIndex, pre[preIndex]);

    int leftSize = i-inIndex;
    if(leftSize!=0) // left subtree is not empty
        printPostorder(in, inIndex, pre, preIndex+1, leftSize);

    int rightSize = len-leftSize-1;
    if(rightSize!=0) // right subtree is not empty
        printPostorder(in, i+1, pre, preIndex+leftSize+1, rightSize);

    print(pre[preIndex]+" ");
}

int indexOf(int a[], int from, int value) {
    while(from<a.length) {
        if(a[from]==value)
            return from;
        from++;
    }
    return -1;
}
```

we print each node once. and for each node, we call `indexOf` which takes `$O(n)$`  
Running Time = `$O(n^2)$`

### References

* <http://www.geeksforgeeks.org/print-postorder-from-given-inorder-and-preorder-traversals/>
