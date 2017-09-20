# First non-repeating character

Find first non-repeating character in a given string ?

scan string and construct `count[]`  
scan string once again, to find `count[ch] = 1`

Running Time: `$O(n)$` where `n` is string.length

if size(alphabet)<<string.length, [ex: DNA String of million letters, but size(alphabet)=4]  
it is highly inefficient to scan string second time

**IDEA:**  
instead of storing #occurrences in `count[]`, store index where the character occurred first time.
now scan `count[]` instead of input string

---

**Given a stream of characters**, find the first non-repeating character from stream.
You need to tell the first non-repeating character in `$O(1)$` time **at any moment**.

```java
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

char firstNonRepeating(){
    return head;
}
```

### References

* <http://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/>
* <http://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/>
