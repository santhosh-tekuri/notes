# Remove duplicates from array of prime numbers

```java
List removeDuplicates(int a[]) {
    List primes = new List();
    int multiplication = 1;
    for(int v: a) {
        if(multiplication%v!=0) { // v is not duplicate
            primes.append(v);
            multiplication *= v;
        }
    }
    return primes;
}
```

:bulb: this works if multiplication don't cause overflow

### References

* <http://www.geeksforgeeks.org/amazon-interview-set-27/>
