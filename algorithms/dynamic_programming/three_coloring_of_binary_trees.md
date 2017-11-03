# Three-coloring of Binary Trees

A **tree** consists of a node and some (zero, one or two) subtrees connected to it. These subtrees are called children.

A **specification** of the tree is a sequence of digits. If the number of children in the tree is:
* zero, then the specification is a sequence with only one element `0`;
* one, the specification begins with `1` followed by the specification of the child;
* two, the specification begins with `2` followed by the specification of the first child, and then by the specification of the second child.

Each of the vertices in the tree must be painted either red or green or blue.  
However, we need to obey the following rules:
* the vertex and its child cannot have the same color,
* if a vertex has two children, then they must have different colors.

How many vertices may be painted green? (i.e Find both minimum and maximum)

```bash
indexes: 0 1 2 3 4 5 6 7 8 9
   spec: 1 1 2 2 0 0 2 0 1 0

tree with indexes:
         0
        /
       1
      /
     2
    / \
   /   \
  3     6
 / \   / \
4   5 7   8

answer: max=5, min=2
```

---

we denote green with `G` and non-green with `g`

let `$M_{n,c}$` represents max vertices may be painted green in subtree rooted at `n`, if `n` is colored `c`

if `n` is leaf:
> `$\begin{align}
M_{n,G} &= 1 \\
M_{n,g} &= 0
\end{align}$`

if `n` has one child:
> `$\begin{align}
M_{n,G} &= 1 + M_{child,g} \\
M_{n,g} &= max(M_{child,G}, M_{child,g})
\end{align}$`

if `n` has two children:
> `$\begin{align}
M_{n,G} &= 1 + M_{child1,g} + M_{child2,g} \\
M_{n,g} &= max(M_{child1,G} + M_{child2,g}, M_{child1,g} + M_{child2,G})
\end{align}$`

:bulb: notice that subproblems does not reoccur. so simple recursion can be used

similarly we can define min green nodes, by replacing `max` with `min` in above formulation

```java
int[] minMax(int spec[]) {
    int n = 0;
    (GMin, gMin, GMax, gMax) minMax() {
        switch(spec[n]) {
        case 0:
            return (1, 0, 1, 0);
        case 1:
            n++;
            GMin, gMin, GMax, gMax = minMax();
            return (
                1+gMin, min(GMin,gMin), 
                1+gMax, max(GMax,gMax)
            );
        default:
            n++;
            GMin1, gMin1, GMax1, gMax1 = minMax();
            n++;
            GMin2, gMin2, GMax2, gMax2 = minMax();
            return (
                1+gMin1,gMin2, min(GMin1+gMin2, gMin1,GMin2),
                1+gMax1,gMax2, max(GMax1+gMax2, gMax1,GMax2)
            );
        }
    }
    GMin, gMin, GMax, gMax = minMax();
    return new int[]{ min(GMin,gMin), max(GMax,gMax) };
}
```

Running Time: `$O(n)$`

### Iterative Version

```java
int[] minMax(int spec[]) {
    Stack<(GMin,gMin,GMax,gMax)> stack;
    for(int i=spec.length-1; i>=0; i--) {
        switch(spec[i]){
        case 0:
            stack.push((1, 0, 1, 0));
            break; 
        case 1:
            (GMin, gMin, GMax, gMax) = stack.pop();
            stack.push((
                1+gMin, min(GMin,gMin),
                1+gMax, max(GMax,gMax)
            ));
            break;
        default:
            (GMin1, gMin1, GMax1, gMax1) = stack.pop();
            (GMin2, gMin2, GMax2, gMax2) = stack.pop();
            stack.push((
                1+gMin1,gMin2, min(GMin1+gMin2, gMin1,GMin2),
                1+gMax1,gMax2, max(GMax1+gMax2, gMax1,GMax2)
            ));
        }
    }
    (GMin, gMin, GMax, gMax) = stack.pop();
    return new int[]{ min(GMin,gMin), max(GMax,gMax) };
}
```

### if more that 3 colors used

minimum 3 colors are required to color with the constraints
* min green = 0 (color entire tree with other colors)
* max green remains same as with 3 colors

---

### References

* <http://www.spoj.com/problems/THREECOL/>
