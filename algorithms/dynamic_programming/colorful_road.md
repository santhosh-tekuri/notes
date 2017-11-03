# Colorful Road

A road contains `n` consecutive parts where each part is colored either RED, GREEN or BLUE  
Given array `c[n]`, where `c[i]` gives color of part `i`

You are standing on part `0` and would like to reach last part with sequence of steps.

constraints:
* you can only jump ahead. i.e from part `i` you can jump only to one of `i+1...n-1`
* *color restriction*: you can move `R->G`, `G->B`, `B->R`

jumping from part `i` to part `j` requires `$(j-i)^2$` energy

Can you reach last part ? If so, find minimum energy required to reach last part?

`$\begin{align}
\color{red}RG\color{red}GG\color{red}B &\to 2^2+2^2 = 4 \\
\color{red}{RGBRGBRGB} &\to 8*1^2 = 8 \\
RBBGGGRR &\to -1(impossible) \\
RBRRBGGGBBBBR &\to 50 \\
\color{red}{RG} &\to 1 \\
RBRGBGBGGBGRGGG &\to 52 \\
\end{align}$`

---

let `m[i]` is minimum energy required to reach last part from part `i`

`$m[i]=\begin{cases}
0 & \text{if $i=n-1$} \\
min( \\
\;\;\;\infty, & \text{# not possible} \\
\;\;\;m[k]+(k-i)^2 & \text{for $k=i+1$ to $n-1$, if $c[i] \to c[k]$ is valid} \\
)
\end{cases}$`

answer is `m[0]`

```java
int minEnergy(char c[n]) {
    int m[n], b[n];
    m[n-1] = 0;
    for(int i=n-2; i>=0; i--) {
        m[i] = ∞;
        b[i] = -1;
        int nextColor = next(c[i]);
        for(int k=i+1; k<n; k++) {
            if(c[k]==nextColor) {
                int v = m[k]+(k-i)*(k-i);
                if(v<m[i]) {
                    m[i] = v;
                    b[i] = k;
                }
            }
        }
    }

    if(m[0]!=∞)
        printSteps(b);
    return m[0];
}

char next(char from) {
    switch(from) {
    case 'R': return 'G';
    case 'G': return 'B';
    case 'B': return 'R';
    }
    assert false;
}

void printSteps(int b[n]) {
    for(int i=0; i!=n-1; i=b[i])
        println(i);
    println(n-1);
}
```

Running Time: `$O(n^2)$`

---

### References

* <https://community.topcoder.com/stat?c=problem_statement&pm=12837&rd=15708>
* <https://apps.topcoder.com/wiki/display/tc/SRM+596#ColorfulRoad>
