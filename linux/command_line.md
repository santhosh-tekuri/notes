# Command Line

`ctrl+a` move to beginning of line  
`ctrl+e` move to end of line

`alt+b` move back by token  
`alt+f` move forward by token

`ctrl+k` kill to end of line  
`ctrl+u` kill to cursor  
`ctrl+y` yank it back  
`alt+.` yank last argument of previous command

`ctrl+l` clear terminal  
`clear && printf '\e[3J'` to clear screen and scrollback buffer  
`ctrl+s` to pause output  
`ctrl+q` to resume output  
`reset` repair terminal 

`!!` last executed command  
`!-2` second command from history  
`!$` last argument of previous command

`ctrl+x ctrl+e` open command in $EDITOR

to clear typed password, do `ctrl+u`  
to mercilessly kill foreground process, do `ctrl+\`

<https://linuxcommand.org/lc3_adv_tput.php>
<https://invisible-island.net/xterm/xterm.faq.html#xterm_tite>
<https://stackoverflow.com/a/11024208>  
create altscreen: `tput smcup` or `echo -e "\e[?1049h""`  
close altscreen: `tput rmcup` or `echo -e "\e[?1049l"`
