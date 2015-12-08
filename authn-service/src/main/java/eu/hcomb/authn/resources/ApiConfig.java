package eu.hcomb.authn.resources;

import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                version = "0.1",
                title = "User service",
                description = "Exposes user CRUD and user login operations"
        ),
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "JWT - Json Web Token", url = "https://en.wikipedia.org/wiki/JSON_Web_Token")
)
public interface ApiConfig { }
