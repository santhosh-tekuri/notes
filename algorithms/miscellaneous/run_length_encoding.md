# Run-Length Encoding

Run-length encoding(RLE) is a very simple form of data compression in which 
runs of data (that is, sequences in which the same data value occurs in many 
consecutive data elements) are stored as a single data value and count, rather 
than as the original run.

`RLE("wwwwaaadexxxxxx") = "w4a3d1e1x6"`

```java
String runLengthEncode(String s){
    // max encoded size is when a has no duplicates
    StringBuilder dest = new StringBuilder(s.length*2);

    for(int i=0; i<s.length; i++){
        dest.append(s[i]);
        int count = 0;
        while(i+1<s.length && s[i+1]==s[i]){
            count++;
            i++;
        }
        dest.append(String.valueOf(count));
    }
    return dest.toString();
}
```

### References

* <http://www.geeksforgeeks.org/run-length-encoding/>
* <http://en.wikipedia.org/wiki/Run-length_encoding>
