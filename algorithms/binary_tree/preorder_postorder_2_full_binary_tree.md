# Preorder, Postorder → Full Binary Tree

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

Node constructTree(int pre[], int post[], int preStart, int postStart, int len) {
    if(len==0)
        return null;
    Node node = new Node(pre[preStart]);
    if(len>1) {
        int index = search(post, pre[preStart+1], postStart);
        int lcount = index-postStart+1;
        int rcount = len-lcount-1;
        node.left = constructTree(pre, post, preStart+1, postStart, lcount);
        node.right = constructTree(pre, post, preStart+1+lcount, postStart+lcount, rcount);
    }
    return node;
}

int search(int array[], int value, int from) {
    while(a[from]!=value)
        from++;
    return from;
}
```

### References

* <http://www.geeksforgeeks.org/archives/24867>
