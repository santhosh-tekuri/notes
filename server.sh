# ensure http-server is installed:
#    npm install http-server -g

rm -f nohup.out
nohup http-server -a 127.0.0.1 -p 8080 -c-1 &
echo open http://localhost:8080/index.html in browser
