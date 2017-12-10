# Sum of Two Squares

$10$ can be expressed as: $3^2+1^2$ (note that $1^2+3^2$ is not counted. because it is same)  
$25$ can be expressed as: $5^2+0^2$, $4^2+3^2$

given number $n$, find number of ways it can be written as sum of squares?

in other words:  
given $n$ find all possible $x$ and $y$ such that $n=x^2+y^2$ and $x<=y$ ?

---

```java
void sumOfSquares(int n) {
    int x = 0; // min value possible
    int y = (int)sqrt(y); // max value possible
    while(x<=y) {
        int sum = sqr(x) + sqr(y);
        if(sum==n) {
            println(x, y);
            x++; y--;
        } else if(sum<n)
            x++;
        else
            y--;
    }
}
```

`@src(src/SumOfTwoSquares.java)`

Running Time: $O(\sqrt n)$

---

### References

* <http://programmingpraxis.com/2010/01/05/the-sum-of-two-squares/2/>
* <http://leetcode.com/2011/01/double-square-problem-analysis.html>
