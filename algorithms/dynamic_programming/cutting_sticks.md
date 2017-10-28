# Cutting Sticks

You have to cut a wood stick into several pieces. The most affordable company, 
Analog Cutting Machinery (ACM), charges money according to the length of the 
stick being cut. Their cutting saw allows them to make only one cut at a time.

It is easy to see that different cutting orders can lead to different prices. 
For example, consider a stick of length 10 m that has to be cut at 2, 4, and 7 m from one end. There are several choices. 
* One can cut first at 2, then at 4, then at 7. This leads to a price of 10+8+6 = 24 
  because the first stick was of 10 m, the resulting stick of 8 m, and the last one of 6 m. 
* Another choice could cut at 4, then at 2, then at 7. 
  This would lead to a price of 10+4+6 = 20, which is better for us.

Your boss demands that you write a program to find the minimum possible cutting cost for any given stick.

```bash
len=100 cuts=[25, 50, 75] ➜ 200
len=10  cuts=[4, 5, 7, 8] ➜ 22
``` 

`cuts[]` is strictly increasing order

---

This problem is similar to [Matrix-chain Multiplication](matrix_chain_multiplication.md)

consider `len=10, cuts=[2, 4, 7]`

```bash
|--|--|---|---|
0  2  4   7   10
```

the stick can be visualized as 4 blocks, where each block is represented by its begin and end position  
`b=[0, 2, 4, 7, 10]` i.e preppend `0` and append `len` to `cuts[]`

let `m[i][j]` is min cost of cutting `b[i...j]`

```bash
m[i][i+1] = 0
m[i][j] = min(
             m[i][k] +
             m[k][i] + 
             b[j]-b[i] // length of stick
          ) for k = i+1 to j-1
```
answer is `m[0][b.length-1]`

```java
int minCost(int len, int cuts[]) {
    int b = cuts.length + 2;
    int m[0][b];
    for(int size: 2 to b-1) {
        for(int i: 0 to b-size-1) {
            int j = i + size;

            int bi = i==0 ? 0 : cuts[i-1];
            int bj = j==b-1 ? len : cuts[j-1];
            m[i][j] = ∞;
            for(int k: i+1 to j-1)
                m[i][j] = min(m[i][j], m[i][k]+m[k][j]+bj-bi);
        }
    }
    return m[0][b-1];
}
```

---

### References

* [UVa 10003](https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=944)
* <https://rounaktibrewal.wordpress.com/2011/10/01/uva-10003-cutting-sticks/>
