# Preorder → BST

```bash
preorder = [10, 5, 1, 7, 40, 50]

    10
   /  \
  5   40
 / \    \
1    7  50
```

---

### Divide & Conquer

* first element of preorder is root
* find the element larger than root
    * all elements before it are in left subtree
    * all elements after it, including the element are in right subtree

```bash
          10
         /  \
[5, 1, 7]    [40, 50]
```

```java
Node constructBST(int pre[]) {
    return constructBST(pre, 0, pre.length-1);
}

Node constructBST(int pre[], int start, int end) {
    if(start>end)
        return null;

    int i = start+1;
    while(i<=end && pre[i]<pre[start])
        i++;

    Node n = new Node(pre[start);
    n.left = constructBST(pre, start+1, i-1);
    n.right = constructBST(pre, i+1, end);
    return n;
}
```

Runnting-Time: `$O(n^2)$`

---

### Range Strategy

use range, to check whether next element is child or not

```java
Node constructBST(int pre[]) {
    IntRef i = 0;
    return constructBST(pre, i, -INF, +INF);
}

Node constructBST(int pre[], IntRef i, int min, int max) {
    if(i==pre.length)
        return null;

    int key = pre[i];
    if(key=>min && key<=max) {
        i++;
        Node n = new Node(key);
        n.left = constructBST(pre, i, min, key);
        n.right = constructBST(pre, i, key+1, max);
        return n;
    } else
        return null;
}
```

Running-Time: `$O(n)$`

```java
Node constructBST(int post[]) {
    IntRef i = post.length-1;
    return constructBST(post, i, -INF, +INF);
}

Node constructBST(int post[], IntRef i, int min, int max) {
    if(i<0)
        return null;

    int key = post[i];
    if(key<=min && key<=max) {
        i--;
        Node n = new Node(key);
        n.right = constructBST(post, i, key+1, max);
        n.left = constructBST(post, i, min, key);
        return n;
    } else
        retur null;
}
```

---

### Insertion Strategy

if you take empty BST, keep inserting nodes from preorder, the BST you get has exactly the preorder exactly same as given one.
we call insert `n` times, so running time will be `$O(n\log_2 n)$`

we can use stack to make it `$O(n)$` 

stack maintains the nodes in current insertion path  
rather than putting all nodes in stack, we put only nodes that have insertion point (i.e nodes with right children are excluded)  
for new node we have to find valid insertion point

then you have to check valid insertion place for next node in reverse order.  
notice that for last node, you can insert either in left or right. but for rest of them you can insert only insert in right.  
so the values of nodes in stack at any time will be in sorted order with min of top of stack.  
if new node to be inserted is less than stack.peek, then it is inserted as as left child  
otherwise we have to find valid insertion point that is nearest to root

```java
Node constructBST(int pre[]) {
    Stack stack = new Stack();

    // first node will be root and stack contains path to root
    stack.push(new Node(pre[0]);

    for(int i=1; i<pre.length; i++) {
        Node n = new Node(pre[i]);
        if(n.data<stack.peek().data)
            stack.peek().left = n;
        else {
            Node temp = null;
            while(!stack.isEmpty() && n.data>stack.peek().data)
                temp = stack.pop();
            assert temp!=null;
            tmp.right = n;
        }
        stack.push(n);
    }
}
```

each node is pushed and popped exactly once  
so running time is `$O(n)$`

---

### References

* <http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/>
* <http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversal-set-2/>

