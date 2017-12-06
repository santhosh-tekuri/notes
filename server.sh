rm -f nohup.out
nohup http-server -c-1 &
echo open http://localhost:8080/index.html in browser
