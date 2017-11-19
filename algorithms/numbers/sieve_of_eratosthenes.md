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

this reduces space complexity to `$O(\text{#primes})$`

```java
interface Sequence {
    int next(); // returns 0 to signal end of sequence
}

class Numbers implements Sequence {
    int i=1, n;
    Numbers(int n) { 
        this.n = n;
    }
    public int next() {
        i++;
        return i<=n ? i : 0;
    }
}

class Filter implements Sequence {
    Sequence src;
    int n;
    Filter(Sequence src, int n) {
        this.src = src;
        this.n = n;
    }
    public int next() {
        while(true) {
            int i = src.next();
            if(i==0 || i%n!=0)
                return i;
        }
    }
}

void printPrimes(int n) {
    Sequence seq = new Numbers(n);
    while(true) {
        int i = seq.next();
        if(i==0)
            return;
        println(i);
        seq = new Filter(seq, i);
    }
}
```

---

### References

* <https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes>
* <http://www.geeksforgeeks.org/sieve-of-eratosthenes/>
