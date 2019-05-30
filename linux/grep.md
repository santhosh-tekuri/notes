# grep

grep stands for "**G**lobal **R**egular **E**xpression **P**rint"

usage: `grep <options> <pattern> <files>` 

`--color` highlight matching text  
`-i` ignore case while searching  
`-v` invert match i.e. show lines that do not contain the pattern  
`-n` show line numbers  
`-c` show only count of matched lines  

`-r` recursively search subdirectories  
`-l` print file names that include pattern  
`-L` print file names that do not include pattern

---

### Context

`-A 5` show `5` lines of context after match  
`-B 5` show `5` lines of context before match  
`-C5` show `5` lines of context before and after match. equivalent to `-A 5 -B 5`
