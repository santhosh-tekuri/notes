# Sieve of Eratosthenes

Given a positive number `n`, print all prime numbers that are `<=n`

---

* starting from first prime number `2` mark all its multiple as composite
* repeat the same process with next unmarked number which is next prime

![sieve_of_eratosthenes.gif](files/sieve_of_eratosthenes.gif)

```java
void printPrimes(int n) {
    if(n<2) // there are no primes
        return;

    boolean composite[n+1];
    for(int i=2; i<=n; i++) {
        if(!composite[i]) {
            print(i);

            // mark multiple of i
            for(int j=2*i; j<=n; j+=i)
                composite[j] = true;
        }
    }
}
```

---

### References

* <https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes>
* <http://www.geeksforgeeks.org/sieve-of-eratosthenes/>
