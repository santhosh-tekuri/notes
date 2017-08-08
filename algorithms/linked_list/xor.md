# XOR Linked List

Each node in ordinary doubly linked list contains pointers to previous and next nodes.  
Each node in XOR doubly linked list contains only one address, which is XOR of previous and next node addresses.

Let field `npx` field stores this XOR value.

## Traversing

Always remember the address of previous node, then `next = current->npx XOR prev`

```c
void traverseForward(Node *head){
    Node *cur = head;
    Node *prev = NULL;

    while(cur!=NULL){
        print(cur->data);
        Node *next = cur->npx XOR prev;
        prev = cur;
        cur = next;
    }
}

void traverseBackward(Node *tail){
    Node *cur = tail;
    Node *prev = tail->npx;

    while(cur!=NULL){
        print(cur->data);
        Node *prevPrev = prev->npx XOR cur;
        cur = prev;
        prev = prevPrev;
    }
}
```

## Editing

```c
Node preppend(Node *head, Node *node){
    node->npx = head;
    if(head!=null)
        head.npx = node XOR (head->npx)
    return node;
}

Node insertAfter(Node *head, Node *n, Node newNode){
    Node *cur = head;
    Node *prev = NULL;
    while(cur!=null){
        Node *next = cur->npx XOR prev;
        if(cur==n){
            // prev->cur->newNode->next
            if(next!=null)
                next.npx = newNode XOR (next.npx XOR cur)
            cur.npx = prev XOR newNode;
            newNode.npx = cur XOR prev;
            break;
        }
        prev = cur;
        cur = next;
    }
}
```

### References

* <http://www.geeksforgeeks.org/xor-linked-list-a-memory-efficient-doubly-linked-list-set-1/>
* <http://www.geeksforgeeks.org/xor-linked-list-a-memory-efficient-doubly-linked-list-set-2/>
* <http://www.linuxjournal.com/article/6828?page=0,1>
