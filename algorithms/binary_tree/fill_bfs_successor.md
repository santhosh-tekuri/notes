# Fill BFS Successor

Given binary tree with additional field `bfsSuccessor`, fill `bfsSuccessor` for each node with constant space ?

`BFS` needs queue. use linked list chained with `bfsSuccessor` as queue to avoid linear space.

```java
void bfs(Node n) {
    Node head = n;
    Node tail = n;

    while(head!=null){
        // dequeue
        n = head;
        head = head.bfsSuccessor;

        // enqueue children
        if(n.left!=null)
            tail = tail.bfsSuccessor = n.left;
        if(n.right!=null)
            tail = tail.bfsSuccessor = n.right;
    }
}
```

### References

* <http://leetcode.com/2010/03/first-on-site-technical-interview.html>
