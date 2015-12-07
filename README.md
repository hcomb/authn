# Authentication Service

## Login:
request:
```
POST /authn/rest/login HTTP/1.1
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
GET /authn/rest/whoami?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4IiwiZXhwIjoxNDQ5NTE3NDA0LCJyb2xlcyI6WyJ1c2VyIl19.-ypSzNo1JlS9pWVR7jZOs1Z2xCWA4NK2ganxA3a-HBysspJoE1JhWk5ZUpeMKKwitz9jjWVhuMB_SVV-_dOp7w HTTP/1.1
```

```json
{
    "name": "alex",
    "roles": [
        "user"
    ]
}
```

## Authentication with cookie

```
GET /authn/rest/whoami HTTP/1.1
Cookie: jwt=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4IiwiZXhwIjoxNDQ5NTE3NDA0LCJyb2xlcyI6WyJ1c2VyIl19.-ypSzNo1JlS9pWVR7jZOs1Z2xCWA4NK2ganxA3a-HBysspJoE1JhWk5ZUpeMKKwitz9jjWVhuMB_SVV-_dOp7w;
```

```json
{
    "name": "alex",
    "roles": [
        "user"
    ]
}
```

## Authentication with header

```
GET /authn/rest/whoami HTTP/1.1
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4IiwiZXhwIjoxNDQ5NTE3NDA0LCJyb2xlcyI6WyJ1c2VyIl19.-ypSzNo1JlS9pWVR7jZOs1Z2xCWA4NK2ganxA3a-HBysspJoE1JhWk5ZUpeMKKwitz9jjWVhuMB_SVV-_dOp7w
```

```json
{
    "name": "alex",
    "roles": [
        "user"
    ]
}
```
