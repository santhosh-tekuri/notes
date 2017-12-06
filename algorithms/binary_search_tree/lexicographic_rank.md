# Lexicographic Rank of a string

Given a string, find its rank among all its permutations sorted lexicographically.  
NOTE: rank starts from 1 

Ex: `rank(abc)=1, rank(acb)=2, ..., rank(cba)=6`

---

say given `STRING` (total `6` characters)

* take first character `S`. there are `4` characters smaller than `S` following it.  
  so there are `4*5!` permutations before `SXXXXX`  
  they are as following:  
  `RXXXXX`  
  `IXXXXX`  
  `NXXXXX`  
  `GXXXXX`
* now take second character `T`. there are `4` character smaller than `T` following it.  
  so there are `4*4!` permutations before `STXXXX`  
  `SRXXXX`  
  `SIXXXX`  
  `SNXXXX`  
  `SGXXXX`
* repeat same for remaining characters  

```bash
S -> 4*5!
T -> 4*4!
R -> 3*3!
I -> 1*2!
N -> 1*1!
G -> 0*0!
----------
sum -> 597
```

i.e there are `597` permutations before `STRING`  
so rank of `STRING` = `1+597` = `598`

---

**does this work if given string contains duplicates ?**

minor changes are required.

say given `STRINING` (total `8` characters)

take first character `S`.  
there are `6` characters smaller than `S` following it, among them `2` are duplicates.  
i.e there are `4` distinct characters smaller than `S` following it.  
so there are `4*7!` permutations before `SXXXXXXX`

---

### References

* <http://www.geeksforgeeks.org/lexicographic-rank-of-a-string/>
