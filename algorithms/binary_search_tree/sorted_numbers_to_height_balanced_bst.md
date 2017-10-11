# Sorted Numbers → Height-Balanced BST

## Given Sorted Array

* pick middle element of array
* set it as root
* recursively build left and right subtrees from lower and higher halves of array

```java
Node toBST(int a[]){
    return toBST(a, 0, a.length-1);
}

Node toBST(int a[], int begin, int end){
    if(begin>end)
        return null;
    int mid = begin+(end-begin)/2;
    Node n = new Node(a[mid]);
    n.left = toBST(a, begin, mid-1);
    n.right = toBST(a, mid+1, end);
    return n;
}
```

`$T(n) = 2T(\frac{n}{2}) + O(1) = O(n)$`

---

## Given Sorted Linked List

above implementation can be used. in such case we have to find middle node every time.  
finding middle node takes `$O(\frac{n}{2})$`

`$T(n) = 2T(\frac{n}{2}) + O(\frac{n}{2}) = O(n\log_2{n})$`

**Can we do better ?**

* first construct left-subtree, parent and right-subtree in order
* have a reference to `head` of list
* move `head` ahead one step, after creating BST node
    * before creating BST node, `head` should be pointing to middle node

```java
BNode toBST(Node head) {
    int size = size(head);
    return new Converter(head).run(0, size-1);
}

class Converter {
    private Node head;
    public Converter(Node head) {
        this.head = head;
    }

    BNode run(int begin, int end) {
        if(begin>end)
            return null;
        int mid = begin+(end-begin)/2;
        BNode left = run(begin, mid-1);
        BNode root = new BNode(head[0].data);
        head[0] = head[0].next;
        root.left = left;
        root.right = run(mid+1, end);
        return root;
    }
}
```

---

## Given Sorted Doubly Linked List

reuse nodes with `prev` as left and `next` as right

```java
Node toBST(Node head) {
    int size = size(head);
    return new Converter(head).run(size);
}

class Converter {
    private Node head;
    public Converter(Node head) {
        this.head = head;
    }

    Node run(int size) {
        if(size<=0)
            return null;
        Node left = run(size/2);
        Node root = head;
        head = head.next;
        root.prev = left;
        root.next = run(size - size/2 - 1);
        return root;
    }
}
```

---

### References

* <http://n00tc0d3r.blogspot.in/2013/02/covert-sorted-arraylist-to-bst.html>
