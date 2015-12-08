package eu.hcomb.authn.resources;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@SwaggerDefinition(
        info = @Info(
                version = "0.1",
                title = "User service",
                description = "Exposes user CRUD and user login operations"
        ),
        tags = {
        	@Tag(name = "user", description = "User CRUD"),
        	@Tag(name = "login", description = "User login")
        },
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}
)
public interface ApiConfig { }
