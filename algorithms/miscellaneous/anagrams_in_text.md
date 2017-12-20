# #Anagrams of word in given text

Given a `word` and `text`, return count of occurrences of anagrams of `word` in `text`

```
word="for" text="forxxorfxdofr" ➜  3
word="for" text="forf" ➜  2
```

---

first see, how to check two strings of equal length are anagrams ?

```java
boolean areAnagrams(char s1[], char s2[]) {
    int count[256];
    int nonZeros = 0;
    for(char ch: s1) {
        if(count[ch]==0)
            nonZeros++;
        count[ch]++;
    }

    for(char ch: s2) {
        if(count[ch]==0)
            nonZeros++;
        count[ch]--;
        if(count[ch]==0)
            nonZeros--;
    }
   
    return nonZeros==0;
}
```

`@src(src/CheckAnagrams.java)`

to solve original problem: 

consider a sliding window of size `#word`, which is moving from
very left of text to the very right. Each time the sliding window
moves rightwards by one character.

keep updating values of `count[]` and `nonZeros` for current window
as we move rightwards. When `nonZeros==0` then current window is an anagram.

```java
int countAnagrams(char word[], char text[]) {
    int count[256];
    int nonZeros = 0;
    for(char ch: word){
        if(count[ch]==0)
            nonZeros++;
        count[ch]++;
    }

    int anagrams = 0;
    for(int i=0; i<text.length; i++) { // i is end of sliding window
        char ch = text[i];

        // add ch to sliding window
        if(count[ch]==0) nonZeros++;
        count[ch]--;
        if(count[ch]==0) nonZeros--;

        if(i>=word.length) {
            ch = text[i-word.length];
            // remove ch from sliding window
            if(count[ch]==0) nonZeros++;
            count[ch]++;
            if(count[ch]==0) nonZeros--;
        }

        if(nonZeros==0) {
            anagrams++;
            println("anagram found at position:", i-word.length+1);
        }
    }
    return anagrams;
}
```

`@src(src/CountAnagrams.java)`

Running Time: $O(n)$ where `n` is length of text

---

### References

* <http://stackoverflow.com/a/18815721>
