# Move Zeroes to end of array

Move all zeroes to end of array, but the order of non-zeroes should remain same.  
`{1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0}` becomes `{1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}`

```java
void moveZeroesToEnd(int a[]) {
    int nonZeroes = 0;
    for(int i=0; i<a.length; i++) {
        if(a[i]!=0){
            if(i!=nonZeroes) {
                a[nonZeroes] = a[i];
                a[i] = 0;
            }
            nonZeroes++;
        }
    }
}
```

Running Time: `$O(n)$`

### References

* <http://www.geeksforgeeks.org/move-zeroes-end-array/>
