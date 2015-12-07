# Authentication Service

## Login:
 $ curl http://localhost:8080/authn/rest/login?username=pippo

 {"expire":1449508544771,"value":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyIsImV4cCI6MTQ0OTUwODU0NCwicm9sZXMiOlsidXNlciJdfQ.i31TNsQlncXzspk8ewTexTFq05l-yLWwcvuSKnKqt4ok0uHx1qWAfibY3fpTMhJsw8MP
flR2F7gl6_tc2Zj1-g"}

## Authentication with url param
 $ curl http://localhost:8080/authn/rest/whoami?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyIsImV4cCI6MTQ0OTUwODU0NCwicm9sZXMiOlsidXNlciJdfQ.i31TNsQlncXzspk8ewTexTFq05l-yLWwcvuSKnKqt4ok0uH
x1qWAfibY3fpTMhJsw8MPflR2F7gl6_tc2Zj1-g

 {"name":"pippo","roles":["user"]}
 
## Authentication with cookie

 curl --header "Cookie: jwt=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyIsImV4cCI6MTQ0OTUwODU0NCwicm9sZXMiOlsidXNlciJdfQ.i31TNsQlncXzspk8ewTexTFq05l-yLWwcvuSKnKqt4ok0uHx1qWAfibY3fpTMhJsw8MPflR2F7gl6_tc2Zj1-g;" http://localhost:8080/authn/rest/whoami

 {"name":"pippo","roles":["user"]}

## Authentication with header

 curl --header "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyIsImV4cCI6MTQ0OTUwODU0NCwicm9sZXMiOlsidXNlciJdfQ.i31TNsQlncXzspk8ewTexTFq05l-yLWwcvuSKnKqt4ok0uHx1qWAfibY3fpTMhJsw8MPflR2F7gl6_tc2Zj1-g" http://localhost:8080/authn/rest/whoami

 {"name":"pippo","roles":["user"]}
