# Universal Sink

**Universal Sink** is a vertex with in-degree `$|V|-1$` and out-degree `$0$`

---

Exercise 22-1.6 from Cormen (Solution from Instructor’s manual)

Given directed graph `G` as adjacency matrix, determine whether it has universal sink ?

if `adj[i,j]=1`:
* then `i` cannot be a universal sink
* i.e. if row `i` contains `1`, `i` cannot be universal sink
* NOTE: if there is self-loop `a[i,i]=1`, then `i` cannot be universal sink

if `adj[i,j]=0`:
* then `j` cannot be universal sink if `i!=j`
* i.e. if column `j` contains `0` in any position other than `(j,j)`, then `j` cannot be universal sink

```java
boolean isUniversalSink(int adj[][], Vertex k) {
    // check for 1 in row k
    for(int j=0; j<|V|; j++) {
        if(adj[k][j]==1)
            return false;

    // check for 0 in column k
    for(int i=0; i<|V|; i++) {
        if(i!=k && a[i][k]==0)
            return false;
    }

    return true;
}
```

Runnting Time: `$O(V)$`

---

**Theorem:** A directed graph can have at most one universal sink  
**Proof:** If vertex `j` is universal sink, then for every vertex `i!=j`, there is an edge `(i,j)`  
so no other vertex `i` could be universal sink

---

* start at upper-left corner of adjacency matrix. i.e. `i=0, j=0`
* when we see `1`, move down. i.e. `i++`
    * i.e. all rows above `i` has `1`
    * i.e. all vertices `<i` cannot be universal sink
* when we see `0`, move right. i.e. `j++`
    * i.e. all columns `<j` have `0`
* do this until we reach wall. i,e `i==|V|` or `j==|V|`
* if `i==|V|`, there is no universal sink
* if `i<|V|`, then we must have `j==|V|`
    * i.e. all columns `<j` have `0`
    * i.e. all columns have `0`
    * we never examine any values in rows `>i`
    * i.e. all `0`s we examined in columns '>i' were off-diagonal
    * i.e. all vertices `>i` cannot be universal sink
* the only remaining possibility is  vertex `i` might be universal sink. So simply check if it is universal sink

```java
int findUniversalSink(int adj[][]) {
    int i=0; j=0;
    while(i<|V| && j<|V|){
        if(adj[i][j]==1)
            i++;
        else
            j++;
    }

    if(i==|V|)
        return -1; // no universal sink
    if(isUniversalSink(adj, i))
        return i;
    else
        return -1; // no universal sink
}
```

in each iteration of while loop, either `i` or `j` is incremented.  
so the while loop makes at most `2|V|-1` iterations  
each iteration takes `$O(1)$`, thus while loop takes `$O(V)$` time  
call to `isUniversalSink(i)` also takes `$O(V)$` time  
`$\therefore$` Running Time: `$O(V)$`

---

## Find Celebrity

<http://www.geeksforgeeks.org/the-celebrity-problem/>

In a party of `N` people, we need to identify a celebrity, if one exists. A celebrity is person
that is known by every other person, but doesn't know any of them. Identify celebrity using as
few questions as possible.

consider people as vertices. and if `A` knows `B` then draw a directed edge from `A` to `B`.
Then celebrity is actually universal sink in graph.

* if `A` knows `B`:
    * `A` cannot be celebrity
    * `B` may be celebrity
* if `A` does not know `B`:
    * `A` may be celebrity
    * `B` cannot be celebrity

```java
boolean knows(int a, int b) {
    return a knows b;
}

int findCelebrity(int n) {
    int candidate = 1;
    int next = 2;
    while(next<=n){
        if(knows(candidate, next)))
            candidate = next;
        next++;
    }

    for(int i=1; i<=n; i++){
        if(i!=candidate){
            if(knows(candidate, i))
                return -1;
            if(!knows(i, candidate))
                return -1;
        }
    }
    return candidate;
}

```

we used most `3(n-1)` questions
