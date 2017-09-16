# Binary Tree from Inorder and Preorder

Construct Binary Tree from Inorder and Preorder

---

```bash
Inorder : D B E A F C
Preorder: A B D E C F
```

we know that the first element `A` in preorder is root of tree.  
now search for `A` in inorder. All elements to left of `A` are in left subtree.  
and all element to right of `A` are in right subtree.

```bash
          A
         / \
        /   \
 In{D B E}   In{F C}
Pre{B D E}  Pre{C F}
```

recursively do this to build complete tree.

```java
Node constructTree(char preOrder[], char inOrder[]) {
    return constructTree(preOrder, inOrder, 0, inOrder.length-1, 0);
}

Node constructTree(char preOrder[], char inOrder[], int inStart, int inEnd, int preIndex) {
    if(inStart>inEnd)
        return;
    Node node = new Node(preOrder[preIndex]);
    if(inStart!=inEnd) {
        int index = search(inOrder, node.data, inStart, inEnd);
        node.left = constructTree(preOrder, inOrder, inStart, index-1, preIndex+1);
        node.right = constructTree(preOrder, inOrder, index+1, inEnd, preIndex+(index-inStart)+1);
    }
    return node;
}

int search(char array[], char ch, int start, int end) {
    for(int i=start; i<=end; i++) {
        if(array[i]==ch)
            return i;
    }
    return -1;
}
```

### References

* <http://www.geeksforgeeks.org/archives/6633>
