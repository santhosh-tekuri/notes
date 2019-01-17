# Intersection of Linked Lists

There are two linked lists, which merge at common point. How to determine the common point most effectively?

## Logic

* traverse two linked lists and find their lengths `L1` and `L2`
* traverse the longest linked list `$\lvert{L1-L2}\rvert$` nodes list from its head
    * at this point both the nodes are equidistant from the intersection point
* now traverse both the lists one node at a time and compare the nodes at each step to find match
    * the matching node is the intersecting node

```java
Node findIntersection(Node node1, Node node2) {
    if(node1 == node2) // both lists are same
        return node1

    int len1 = length(node1);
    int len2 = length(node2);

    int diff = Math.abs(len1-len2);
    if(len1>len2) {
        while(diff>0) {
            node1 = node1.next;
            diff--;
        }
    } else {
        while(diff>0) {
            node2 = node2.next;
            diff--;
        }
    }

    while(node1!=node2) {
        node1 = node1.next;
        node2 = node2.next;
    }
    return node1; // will be null if do not merge
}

int length(Node node) {
    int len = 0;
    while(node!=null) {
        node = node.next;
        len++;
    }
    return len;
}
```
