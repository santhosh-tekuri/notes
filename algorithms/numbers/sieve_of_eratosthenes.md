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

### Optimization

* start enumerating the multiples of each prime `$i$` from `$i^2$`
* no need to mark composites for primes greater than `$\sqrt n$`

```java
void printPrimes(int n) {
    if(n<2) // there are no primes
        return;

    boolean composite[n+1];
    int end = sqrt(n);
    for(int i=2; i<=end; i++) {
        if(!composite[i]) {
            println(i);

            // mark multiple of i
            for(int j=i*i; j<=n; j+=i)
                composite[j] = true;
        }
    }

    for(int i=end+1; i<=n; i++) {
        if(!composite(arr[i])
            println(i);
    }
}
```

### Incremental

```java
void printPrimes(int n) {
    List primes = [];
    for(int i=2; i<=n; i++) {
        boolean composite = false;
        for(int p: primes) {
            if(i%p==0) {
                composite = true;
                break;
            }
        }
        if(!composite) {
            println(i);
            primes.add(i);
        }
    }
}
```

Space Complexity: `$O(\text{#primes})$`

---

### References

* <https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes>
* <http://www.geeksforgeeks.org/sieve-of-eratosthenes/>
