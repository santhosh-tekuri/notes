# Are leaves at same level?

```bash
    12               12          12
   /  \              /          /  \
  5    7            5          5    7
 /      \          / \        /
3        1        3   9      3
                 /   /
                1   2
yes             yes          no
```
---

```java
int firstLeafLevel;

boolean leavesAtSameLevel(Node n) {
    firstLeafLevel = -1;
    return leavesAtSameLevel(n, 0);
}

boolean leavesAtSameLevel(Node n, int level){
    if(n==null)
        return true;
    if(n.left==null && n.right==null) {
        if(firstLeafLevel==-1)
            firstLeafLevel = level;
        else
            return level==firstLeafLevel;
    } else {
        return leavesAtSameLevel(n.left, level+1) &&
               leavesAtSameLevel(n.right, level+1);
    }
}
```
---

To avoid global variable we can do:

```java
boolean leavesAtSameLevel(Node n) {
    return leavesAtSameLevel(n, 0)>0;
}

int leavesAtSameLevel(Node n, int level) {
    if(n==null)
        return 1;
    if(n.left==null && n.right==null)
        return level+1;
    return leavesAtSameLevel(n.left, level+1)==
           leavesAtSameLevel(n.right, level+1);
}
```
---

### References

* <http://www.geeksforgeeks.org/check-leaves-level/>
