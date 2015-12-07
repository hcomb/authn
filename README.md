# Authentication Service

## Login:
request:
```
POST /authn/rest/login HTTP/1.1
Host: localhost:8080
Cache-Control: no-cache
Content-Type: application/x-www-form-urlencoded

username=alex&password=pippo
```
response:

```json
{
    "valid": true,
    "expire": 1449517054208,
    "value": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4IiwiZXhwIjoxNDQ5NTE3MDU0LCJyb2xlcyI6WyJ1c2VyIl19.O-1sFTWZCvjoMKVMlZeT7TMDe_4ejygFbrTjfJ7qNAOByHVrQOseI1Q3VpkVIQHUMvypk8kkM2OnC9BOEHTzHg"
}
```
## Authentication with url param
```
curl http://localhost:8080/authn/rest/whoami?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyIsImV4cCI6MTQ0OTUwODU0NCwicm9sZXMiOlsidXNlciJdfQ.i31TNsQlncXzspk8ewTexTFq05l-yLWwcvuSKnKqt4ok0uH
x1qWAfibY3fpTMhJsw8MPflR2F7gl6_tc2Zj1-g

{"name":"pippo","roles":["user"]}
```

## Authentication with cookie

```
curl --header "Cookie: jwt=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyIsImV4cCI6MTQ0OTUwODU0NCwicm9sZXMiOlsidXNlciJdfQ.i31TNsQlncXzspk8ewTexTFq05l-yLWwcvuSKnKqt4ok0uHx1qWAfibY3fpTMhJsw8MPflR2F7gl6_tc2Zj1-g;" http://localhost:8080/authn/rest/whoami

{"name":"pippo","roles":["user"]}
```

## Authentication with header

```
curl --header "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyIsImV4cCI6MTQ0OTUwODU0NCwicm9sZXMiOlsidXNlciJdfQ.i31TNsQlncXzspk8ewTexTFq05l-yLWwcvuSKnKqt4ok0uHx1qWAfibY3fpTMhJsw8MPflR2F7gl6_tc2Zj1-g" http://localhost:8080/authn/rest/whoami

{"name":"pippo","roles":["user"]}
```
