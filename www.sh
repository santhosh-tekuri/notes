#!/bin/bash -e

if [ \( ! -f cert.pem \) -o \( ! -f key.pem \) ]; then
    openssl req -x509 -new -keyout key.pem -nodes -out cert.pem -subj '/C=IN/ST=Karnataka/O=MGMT/CN=www.abc.com'
fi
nohup openssl s_server -key key.pem -cert cert.pem -accept 8443 -WWW -quiet &
echo open https://localhost:8443/index.html in browser
echo "enable chrome://flags/#allow-insecure-localhost if needed"
