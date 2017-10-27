# Mixtures

Harry Potter has `n` mixtures in front of him, arranged in a row. 
Each mixture has one of `100` different colors (colors have numbers from `0` to `99`)

He wants to mix all these mixtures together. At each step, he is going to take 
two mixtures that stand next to each other and mix them together, and put 
the resulting mixture in their place.

When mixing two mixtures of colors `a` and `b`:
* the resulting mixture will have the color `(a+b) mod 100`
* also, there will be some smoke in the process. The amount of smoke generated is `a*b`

Find out what is the minimum amount of smoke that Harry can get when mixing all the mixtures together.

```bash
(40 60) 20 => (0,20) => 20
smoke = 2400 + 0 = 2400
```

---

notice that:
* final color after mixing all, is same irrespective of order they are mixed.
  so it has optimal substructure.
* this is similar to matrix multiplication, where we need to find optimal paranthesization

given colors array `c[]`

let:
* `smoke[i,j]` is min smoke produced in mixing `c[i...j]`
* `color[i,j]` is color obtained after mixing `c[i...j]`

```bash
smoke[i,j] = 0 if i=j
           = min(smoke[i,k]+smoke[k+1,j]+c[i,k]*c[k+1,j]: for k = i to j-1)

color[i][j] = c[i] if i=j
            = (color[i][j-1]+c[j])%100
```

actually we don't need `color[][]`:
* instead have single dimensional array `sum[i] = sum(c[0...i])`
* then `color[i][j] = (sum[j]-sum[i])%100`

```java
int minSmoke(int c[]) {
    int n = c.length;

    // precompute prefix sums
    int sum[n];
    sum[0] = c[0];
    for(int i=1; i<n; i++)
        sum[i] += sum[i-1];

    int color(int i, int j) {
       return i==j ? c[i] : sum[j]-sum[i];
    }
    
    int smoke[n][n];
    for(int i=0; i<n; i++)
        smoke[i][i] = 0;
    for(int len: 2 to n) {
        for(int i: 0 to n-len) {
            int j = i+len-1;
            smoke[i][j] = ∞;
            for(int k: i to j-1)
                smoke[i][j] = min(smoke[i][j], smoke[i][k]+smoke[k+1][j]+color(i, k)*color(k+1,j)); 
    }
    return smoke[0][n-1];
}
```

---

### References

* <https://www.codechef.com/problems/MIXTURES/>
