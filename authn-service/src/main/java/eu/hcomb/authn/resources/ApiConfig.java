package eu.hcomb.authn.resources;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@SwaggerDefinition(
        info = @Info(
                version = "0.1",
                title = "User service",
                description = "Exposes user login operation"
        ),
        tags = {
        	@Tag(name = "login", description = "User login")
        },
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}
)
public interface ApiConfig { }
