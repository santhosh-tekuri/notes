# Private/Public Keypair

```shell
$ # generates 2048-bit RSA keypair
$ openssl genrsa -out private.pem 2048

$ # to encrypt generated keypair
$ openssl genrsa -des3 -out private.pem 2048
Enter pass phrase for private.pem:
Verifying - Enter pass phrase for private.pem:

$ # to specify password in arguments
$ openssl genrsa -des3 -passout pass:secret -out private.pem 2048

$ # to export public key from keypair
$ openssl rsa -in private.pem -outform PEM -pubout -out public.pem
Enter pass phrase for private.pem:
writing RSA key

$ # to specify password in arguments
$ openssl rsa -in private.pem -passin pass:secret -outform PEM -pubout -out public.pem
writing RSA key

$ # to export unencypted keypair from encrypted one
$ openssl rsa -in private.pem -out private_unencrypted.pem -outform PEM
Enter pass phrase for private.pem:
writing RSA key

$ # to specify password in arguments
$ openssl rsa -in private.pem -passin pass:secret -out private_unencrypted.pem -outform PEM
writing RSA key
```

---

### References

* <https://rietta.com/blog/2012/01/27/openssl-generating-rsa-key-from-command/>