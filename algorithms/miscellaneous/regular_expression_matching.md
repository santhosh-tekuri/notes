# Regular Expression Matching

Rules for Regular Expression Matching:
* `.` matches any single character
* `*` matches zero or many repetitions of previous character
* `+` matches one or many repetitions of previous character
* `?` matches zero or one repetition of previous character
* `\` escapes next character

```java
public boolean matches(String string, String regex) {
    return matches(string, 0, regex, 0);
}

public boolean matches(String s, int i, String r, int j) {
    assert i<=s.length() && j<=r.length();

    if(j==r.length()) // pattern is empty
        return i==s.length(); // string is empty

    boolean charMatched = false;
    int jnext = j+1;
    if(i<s.length()) {
        if(r[j]=='\\' && j!=r.length()-1) { // escape char
            charMatched = r[j+1]==s[i];
            jnext = j+2;
        } else
            charMatched = r[j]=='.' || r[j]==s[i];
    }

    if(jnext!=r.length()-1) {
        switch(r[jnext]) {
        case '?': // match zero or one times
            return matches(s, i, r, jnext+1) ||
                   (charMatched && matches(s, i+1, r, jnext+1);
        case '+': // match one or many times
            return charMatched && (
                       matches(s, i+1, r, jnext+1) ||
                       matches(s, i+1, r, j)
                   );
        case '*': // match zero or many times
            return matches(s, i, r, jnext+1) ||
                   (charMatched && matches(s, i+1, r, j));
        }
    }

    return charMatched && matches(s, i+i, r, jnext);
}
```
