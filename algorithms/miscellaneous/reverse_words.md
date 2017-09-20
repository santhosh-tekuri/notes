# Reverse words inline

`"i like this program very much"` becomes `"much very program this like i"`

* first reverse each word: `"i ekil siht margorp yrev hcum"`
* now reverse entire string: `"much very program this like i"`

```java
void reverseWords(char s[]) {
    int i = 0;
    while(i<s.length){
        int j = i;
        while(j+1<s.length && s[j+1]!=' ')
            j++;
        reverse(s, i, j);
        i = j+2;
    }
}

void reverse(char s[], int begin, int end) {
    while(begin<end) {
        swap(s, begin, end);
        begin++;
        end--;
    }
}
```

Running Time: `$O(n)$`

### References

* <http://www.geeksforgeeks.org/reverse-words-in-a-given-string/>
