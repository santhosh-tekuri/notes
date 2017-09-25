# Recover BST

Two nodes of BST are swapped by mistake.  
Recover tree without changing structure

```bash
    10           10
   /  \         /  \
  5   [8]  =>  5  [20]
 / \          / \
2 [20]       2  [8]
```

---

InOrder of BST is always a sorted array. so the problem reduces to:  
*when two elements in sorted array are swapped*

let us take inorder `$[3, 5, 7, 8, 10, 15, 20, 25]$`

There are two cases to be handled.

**case 1: swapped nodes are not adjacent**

`$[3, \color{red}{25}, 7, 8, 10, 15, 20, \color{red}5]$` (`5` and `25` are swapped)

* find the first element which is smaller than its previous element i.e `7`
* if we do this again we get `5`
* now simply swap the element before `7` (i.e `25`) with `5`

**case 2: swapped nodes are adjacent**

`$[3, 5, \color{red}8, \color{red}7, 10, 15, 20, 25]$` (`7` and `8` are swapped)

* find the first element which is smaller than its previous element i.e `7`
* if we do this again we find nothing. this implies *case 2*
* now simply swap the element before `7` (i.e `8`) with `7`

---

to formalize, take 3 pointers:
* `middle` = first element which is smaller than its previous element
* `first` = element before middle
*  `last` = next element which is smaller than its previous element

```java
if(first==null) // nothing was swapped
    return; 
if(last==null) // case 2
    swap(first, middle)
else // case 1
    swap(first, last)
```

---

```java
void recoverBST(Node node){
    Node arr[] = new Node[3]; // {first, middle, last}
    inorder(node, arr, null);

    if(arr[0]!=null) {
        if(arr[2]==null)
           swapData(arr[0], arr[1]);
        else
           swapData(arr[0], arr[2]);
    }
}

// returns last node in inorder
Node inorder(Node node, Node arr[], Node prev) {
    if(node==null)
        return prev;

    prev = inorder(node.left, arr, prev);

    if(prev!=null && node.data<prev.data) {
        if(arr[0]==null) {
            arr[0] = prev;
            arr[1] = node;
        } else
            arr[2] = node;
    }

    return inorder(node.right, arr, node);
}

void swapData(Node a, Node b) {
    int temp = a.data;
    a.data = b.data;
    b.data = temp;
}
```

---

### References

* <http://n00tc0d3r.blogspot.in/2013/05/recover-binary-search-tree.html>
* <http://www.geeksforgeeks.org/archives/23616>

