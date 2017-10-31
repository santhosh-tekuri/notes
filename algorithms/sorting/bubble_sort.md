# Bubble Sort

Bubble Sort works by repeatedly swapping adjacent elements that are out of order.

The algorithm gets its name, from the way smaller/larger elements *bubble* to the top of the list.

`$\begin{matrix}
First Pass && Second Pass && Third Pass \\
\begin{matrix}
\color{red}5 & \color{red}1 & 4 & 2 & 8 \\
1 & \color{red}5 & \color{red}4 & 2 & 8 \\
1 & 4 & \color{red}5 & \color{red}2 & 8 \\
1 & 4 & 2 & \color{blue}5 & \color{blue}8
\end{matrix}&&
\begin{matrix}
\color{blue}1 & \color{blue}4 & 2 & 5 & 8 \\
1 & \color{red}4 & \color{red}2 & 5 & 8 \\
1 & 2 & \color{blue}4 & \color{blue}5 & 8 \\
1 & 2 & 4 & \color{blue}5 & \color{blue}8
\end{matrix}&&
\begin{matrix}
\color{blue}1 & \color{blue}2 & 4 & 5 & 8 \\
1 & \color{blue}2 & \color{blue}4 & 5 & 8 \\
1 & 2 & \color{blue}4 & \color{blue}5 & 8 \\
1 & 2 & 4 & \color{blue}5 & \color{blue}8
\end{matrix} &
\text{no swaps, so it is sorted}
\end{matrix}$`

```java
void bubbleSort(int a[]) {
    boolean swapped;
    do{
        swapped = false;
        for(int i=1; i<a.length; i++) {
            if(a[i-1]>a[i]) {
                a[i-1] ⬌ a[i];
                swapped = true;
            }
        }
    }while(swapped)
}
```

Running Time: `$O(n^2)$`

after n<sup>th</sup> pass, n<sup>th</sup> largest is in its final position within array. 
So inner loop can avoid looking at last `n-1` elements

```java
void bubbleSort(int a[]) {
    int n = a.length;
    boolean swapped;
    do{
        swapped = false;
        for(int i=1; i<n; i++) {
            if(a[i-1]>a[i]) {
                a[i-1] ⬌ a[i];
                swapped = true;
            }
        }
        n--;
    }while(swapped)
}
```

it can happen that more than one element is placed in their final position on a single pass. 
In particular, after every pass, all elements after last swap are sorted, and do not need to be checked again.

```java
void bubbleSort(int a[]) {
    int n = a.length;
    do{
        int lastSwap = 0;
        for(int i=1; i<n; i++) {
            if(a[i-1]>a[i]) {
                a[i-1] ⬌ a[i];
                lastSwap = i;
            }
        }
        n = lastSwap;;
    }while(n!=0)
}
```

notice new code subsumes `swapped` variable

:bulb: although the algorithm is simple, it is too slow and impractical for most problems even when compared to insertion sort

### Rabbits and Turtles

Large elements moves to final position faster like Rabbits  
Small elements moves to final position slower like Tutles. Infact they move one step per pass.

---

## Bidirectional Bubble Sort

This sorts in both directions in each pass

```java
void bidirectionalBubbleSort(int a[]) {
    int lo=0; hi=a.length-1;
    while(lo<hi) {
        int i=lo;
        while(i<hi) {
            if(a[i]>a[i+1]) {
                a[i] ⬌ a[i+1];
                lastSwap = i;
            }
        }
        hi = lastSwap;

        i = hi;
        while(i>lo) {
            if(a[i]>a[i-1]) {
                a[i] ⬌ a[i-1];
                lastSwap = i-1;
            }
        }
        lo = lastSwap;
    }
}
```

Running Time: `$O(n^2)$`

---

### References

* <https://en.wikipedia.org/wiki/Bubble_sort>
