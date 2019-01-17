Run
---
Prepare:
===
start database
```
docker run -e MYSQL_ROOT_PASSWORD=petclinic -e MYSQL_DATABASE=petclinic -p 3306:3306 mysql:5.7.8
```
prepare data
```
execute src/main/resources/db/mysql/*.sql
```

start app
===
```
gradle bootRun
```

post query:
===
POST http://localhost:8080/graphql
```
{
	"query":"{pets {name}}"
}
```
bit more complex
POST http://localhost:8080/graphql
```
{
	"query":"{pets {name birthDate owner{firstName lastName}}}"
}
```