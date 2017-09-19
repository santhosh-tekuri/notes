# Leaders in Array

Print leaders in given array. An element is leader if it is greater than all the elements to its right side.

Note that last element of array is always a leader.

In array `$[16, \color{red}{17}, 4, 3, \color{red}{5}, \color{red}{2}]$` leaders are `17, 5, 2`

---

Scan array from right to left, keeping track of maximum till now.  
When maximum changes its value, print it.

```java
void printLeaders(int arr[]){
    if(arr.length==0)
        return;

    int max = arr[arr.length-1];
    println(max);
    for(int i=arr.length-2; i>=0; i--) {
        if(arr[i]>max) {
            println(arr[i]);
            max = arr[i];
        }
    }
}
```
