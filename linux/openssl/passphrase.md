# Passphrase Arguments

Several commands accept password arguments:
* typically `-passin` and `-passout` for input and output passwords respectively
* these options take single argument
* if no argument, it prompts for password

argument format:
* `pass:password`
* `env:var`
* `file:pathname` could also refer to a device or named pipe
* `fd:number` can be used to read from pipe
* `stdin`

---

### References

* <https://wiki.openssl.org/index.php/Manual:Openssl(1)#PASS_PHRASE_ARGUMENTS>