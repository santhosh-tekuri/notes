# lsof

lists open files by all processes

`lsof` list all opened files  
`lsof <file>` list who opened file  
`losf +D <dir>` list opened files under directory  
`lsof -p 1234` list opened file by process `1234`  
`lsof -i` list all open network connections. `i` stands for internet address  
`lsof -i :8080` list who is listening/connected on tcp port `8080`  
`lsof -i :8080 -s TCP:LISTEN` list who is listening tcp port `8080`  
`lsof -i TCP -s TCP:LISTEN -P` to list all tcp listening ports  
`-P` shows port numbers instead of port names

`-t` lists only pids. for example `kill -9 $(lsof -t -i :8080 -s TCP:LISTEN)` kills the process listening on tcp port `8080`
