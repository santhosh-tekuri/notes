# arrays

- variable containing multiple values
- no limit to size
- indexes/assignments need not be contiguous
- zero-based

```
# declaration
declare -a arr
arr=()
arr=(val0 val1 val2)
declare -a arr=(val0 val1 val2)
arr=(val0 [10]=val10 val11) # val3 is at index 11

arr=(val0 val1 val2)
${arr[*]} # val0 val1 val2
${arr[2]} # val2
arr[5]=val5
${arr[*]} # val0 val1 val2 val5
unset arr[5]

$ arr=(one "two 2" three)
$ for v in "${arr[*]}"; do echo $v; done
one two 2 three
$ for v in "${arr[@]}"; do echo $v; done
one
two 2
three

${!arr[@]}    # array indices
${#arr[@]}    # array length
${#arr[0]}    # length of item 0
arr+=(4)      # append value
${arr[@]:5:2} # get 2 elements starting at index 5

# read array
read -r -a arr <<< "$var"
IFS=',' read -r -a arr <<< "$var"
```

---

## References

<https://tldp.org/LDP/Bash-Beginners-Guide/html/sect_10_02.html>
