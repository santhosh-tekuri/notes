# Clone Linked List with next and arbit Pointer

Given linked list where each node has two pointers next and arbit, where arbit can point to any node. Clone it?

![list_arbit.jpeg](files/list_arbit.jpeg)

---

**Step 1:**

insert copy of node1 between node1 and node2  
insert copy of node2 between node2 and node3  
and so on

**Step 2:**

copy arbit pointers

```java
original = head;
while(original!=null){
    orignal.next.arbit = original.arbit.next;
    original = original.next;
}
```

**Step3:**

restore original and cloned linked lists

```java
original = head;
copy = head.next;
while(original!=null){
    original.next = original.next.next;
    copy.next = copy.next.next;
}
```

### References

* <http://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/>
