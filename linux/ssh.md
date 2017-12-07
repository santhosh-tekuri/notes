# ssh

`-p <num>` specify port number. default is `22`  
`-i <identity-file>` specify private key for public-key authentication

---

### Escape Sequences

used to interact with ssh command from within a session  
only interpreted if they are the first thing that is typed after a newline

`~?` print supported escaped sequences  
`~~` to type literal `~`  
`~.` terminate connection  
`~C` open ssh command line
