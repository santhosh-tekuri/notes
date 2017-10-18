# Find n<sup>th</sup> element from end of linked list

`$5 \to 6 \to \color{red}7 \to 1 \to 2$`

3rd element from end = 7

---

* take two pointers `p` and `q` both pointing to head
* move `q` by `n` steps
* move both `p` and `q` by one step, until `q==null`
* `p` will be `n`<sup>th</sup> element from end

```java
Node nthFromEnd(Node head, int n) {
    Node q = head;
    while(n>0) {
        if(q==null)
            return null;
        q = q.next;
        n--;
    }

    Node p = head;
    while(q!=null) {
        p = p.next;
        q = q.next;
    }
    return p;
}
```
