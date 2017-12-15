# First non-repeating character

<http://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/>

Find first non-repeating character in a given string ?

---

scan string and construct `count[]`  
scan string once again, to find `count[ch] = 1`

```java
int firstNonRepeating(String str) {
    int count[256];
    for(char ch: str)
        count[ch]++;
    for(int i=0; i<str.length(); i++) {
        if(count[str.charAt(i)]==1)
            return i;
    }
    return -1;
}
```

`@src(src/FirstNonRepeatingCharV1.java)`

Running Time: `$O(n)$` where `n` is string.length

---

### Large Input String

if `size(alphabet)<<string.length`, [ex: DNA String of million letters, but `size(alphabet)=4`]  
it is highly inefficient to scan string second time

::: tip  
store index of first occurrence in `firstIndex[]`. now scan `count[]` instead of input string
:::

```java
int firstNonRepeating(String str) {
    int count[256], firstIndex[256];
    for(int i=0; i<str.length(); i++) {
        char ch = str.charAt(i);
        count[ch]++;
        if(count[ch]==1)
            firstIndex[ch] = i;
    }

    int ans = -1;
    for(int ch=0; ch<256; ch++) {
        if(count[ch]==1)
            ans = ans==-1 ? firstIndex[ch] : min(ans, firstIndex[ch]);
    }
    return ans;
}
```

`@src(src/FirstNonRepeatingCharV2.java)`

---

## Stream of Characters

<http://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/>

**Given a stream of characters**, find the first non-repeating character from stream.
You need to tell the first non-repeating character in `$O(1)$` time **at any moment**.

```java
class Stream {
    boolean repeated[256]; // repeated[ch] = true if ch is repeated
    Node head = null; // doubly linked list to find first non repeating character 
    Node nodes[256]; // nodes[ch] = reference node in linked list for that character

    void consume(char ch) {
        if(!repeated[ch]) {
            if(nodes[ch]==null) { // first occurrence of ch
                Node n = new Node(ch);
                append(n);
                nodes[ch] = n;
            } else {
                remove(nodes[ch]);
                nodes[ch] = null;
                repeated[ch] = true;
            }
        }
    }

    Character firstNonRepeating(){
        return head==null ? null : head.data;
    }
}

Character firstNonRepeating(String str) {
    Stream stream = new Stream();
    for(char ch: str)
        stream.consume(ch);
    return stream.firstNonRepeating();
}
```

`@src(src/FirstNonRepeatingCharV3.java)`

Running Time: $O(n)$

---

## Kth non-repeating character

<http://www.geeksforgeeks.org/kth-non-repeating-character/>

use the second approach: 
* init `firstIndex[]` with `n`
* compute `firstIndex[]`
* sort `firstIndex[]`
* answer is `firstIndex[k]`

```java
int kthNonRepating(String str, int k) {
    int n = str.length();

    int count[256], firstIndex[256];
    Arrays.fill(firstIndex, n);
    for(int i=0; i<n; i++) {
        char ch = str.charAt(i);
        count[ch]++;
        firstIndex[ch] = count[ch]==1 ? i : n;
    }

    Arrays.sort(firstIndex);
    return firstIndex[k-1]==n ? -1 : firstIndex[k-1];
}
```

`@src(src/KthNonRepeatingChar.java)`

note that: sorting `firstIndex[256]` takes constant time

Running Time: $O(n)$ 
