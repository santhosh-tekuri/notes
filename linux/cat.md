# cat

usage: `cat <files>`

`-n` display line numbers  
`-b` number only non empty lines  
`-s` sqeeze adjacent empty lines to single empty line  
`-e` print `$` at end of each line (useful to identify trailing whitespaces)  
`-t` display tab as `^I`  
`-u` disable output buffering

---

### Stdin

if no `<files>` are specified, it reads from stdin

`-` can be used to specify stdin along with files

```
cat file1 - file2 - file3
```

this prints contents of `file1` print data it receives from the standard input until it receives an EOF (`^D`) character, print
the contents of `file2`, read and output contents of the standard input again, then finally output the contents of `file3`

::: note box
if the standard input referred to a file, entire contents of the file would be 
consumed by first dash, leaving any following dashes no effect
:::
