docker run -e MYSQL_ROOT_PASSWORD=petclinic -e MYSQL_DATABASE=petclinic -p 3306:3306 mysql:5.7.8
execute src/main/resources/db/mysql/*.sql

RUN:
```
gradle bootRun
```


Query:
POST http://localhost:8080/graphql
```
{
	"query":"{pets {name}}"
}

```