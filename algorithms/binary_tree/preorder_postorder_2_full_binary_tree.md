# Preorder, Postorder => Full Binary Tree

A *Full Binary Tree* is a binary tree where every node has either 0 or 2 children.

```bash
pre[]  = {1, 2, 4, 8, 9, 5, 3, 6, 7}
post[] = {8, 9, 4, 5, 2, 6, 7, 3, 1};

            _1_
           /   \
          2     3
         / \   / \
        4   5 6   7
       / \
      8   9
```

---

Left most node in `pre[]` is always root, i.e `1` is root.  
Since the tree is full and array size is more than one, the value next to `1` in `pre[]`, must be root of left subtree.  
All nodes before `2` in `post[]` must be in in left subtree.

```bash
             _1_
            /   \
           /     \
          /       \
post{8 9 4 5 2}  post{6 7 3}
 pre{2 4 8 9 5}   pre{3 6 7}
```

```java
Node constructTree(int pre[], int post[]) {
    return constructTree(pre, post, 0, 0, pre.length);
}

Node constructTree(int pre[], int post[], int postStart, int postEnd, int preIndex) {
    if(postStart>postIndex)
        return null;
    Node node = new Node(pre[preIndex]);
    if(postStart!=postEnd) {
        int index = search(post, int value, postStart, postEnd);
        node.left = constructTree(pre, postOrder, postStart, index, preIndex+1);
        node.right = constructTree(preOrder, inOrder, index+1, postEnd-1, preIndex+(index-postStart)+1);
    }
    return node;
}

int search(int array[], int value, int start, int end) {
    for(int i=start; i<=end; i++) {
        if(array[i]==value)
            return i;
    }
    return -1;
}
```

### References

* <http://www.geeksforgeeks.org/archives/24867>
