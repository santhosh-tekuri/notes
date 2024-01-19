# vim

### character navigation

`h` left  
`j` down  
`k` up  
`l` right  
`gj` down in soft-wrapped line  
`gk` up in soft-wrapped line

### word navigation

`w` move to beginning of next word  
`b` move to beginning of previous word  

`W` move to beginning of next WORD  
`B` move to beginning of previous WORD

`e` move to end of next word  
`ge` move to end of previous word

`E` move to end of next WORD  
`gE` move to end of previous WORD

### current line navigation

`0` go to first char in current line  
`$` go to last char in current line

`_` go to first non-blank char in current line  
`g_` go to last non-blank char in current line

`<n>|` go to column `n` in current line

`f{char}` go to next occurence of char in current line  
`t{char}` go till next occurence of char in current line

`F{char}` go to prev occurence of char in current line  
`T{char}` go till prev occurence of char in current line

`;` repeat last search in same direction  
`,` repeat last search in opposite direction

### sentence and paragraph navigation

`(` go to prev sentence  
`)` go to next sentence

`{` go to prev paragraph  
`}` go to next paragraph

### match navigation

`%` find paranthesis/bracket under or next to cursor and go to its match

### line number navigation

`gg` go to first line in file  
`G` go to last line in file   
`{n}G` go to n-th line file  
`{n}%` go to n-percent in file

`Ctrl-G` show no of lines in file

### window navigation

`H` go to top of screen  
`M` go to middle of screen  
`L` go to bottom of screen  
`{n}H` go to n-th line from top  
`{n}H` go to n-th line from bottom

### scrolling

`Ctrl-F` scroll down whole screen  
`Ctrl-B` scroll up whole screen

`Ctrl-D` scroll down half screen  
`Ctrl-U` scroll up half screen

`Ctrl-E` scroll down a line  
`Ctrl-Y` scroll up a line

`zt` bring current line to top of screen  
`zz` bring current line to middle of screen  
`zb` bring current line to bottom of screen

### pattern search

`/` search forward  
`?' search backward

`n` repeat last search in same direction  
`N` repeat last search in opposite direction

`*` search forward for whole word under cursor  
`#` search backward for whole word under cursor

`g*` search forward for word under cursor  
`g#` search backward for word under cursor

### tabs

`vim -p file1 file2` open files in tabs  
`:tabedit existing_file`  
`:tabnew new_file`  
`:tab terminal`  
`:qa` to exit vim

---

### terminal

in vim press `ctrl-z`, vim gets suspended and you land in terminal  
to get back into vim run command `fg`, this brings vim to foreground
