package eu.hcomb.authn;

import io.dropwizard.client.JerseyClientConfiguration;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.hcomb.common.cors.CorsConfig;
import eu.hcomb.common.cors.CorsConfigurable;
import eu.hcomb.common.jdbc.JdbcConfig;
import eu.hcomb.common.jdbc.JdbcConfigurable;
import eu.hcomb.common.swagger.SwaggerConfig;
import eu.hcomb.common.swagger.SwaggerConfigurable;
import eu.hcomb.common.web.BaseConfig;

public class AuthenticationConfig extends BaseConfig implements CorsConfigurable, JdbcConfigurable, SwaggerConfigurable {

    protected JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    protected JdbcConfig jdbcConfig = new JdbcConfig();
	
    protected CorsConfig corsConfig = new CorsConfig();
	
    protected SwaggerConfig swaggerConfig = new SwaggerConfig();
	
    protected String authzUrl;

	public SwaggerConfig getSwaggerConfig() {
		return swaggerConfig;
	}

	public JdbcConfig getJdbcConfig() {
		return jdbcConfig;
	}

	public CorsConfig getCorsConfig() {
		return corsConfig;
	}
	
	public String getAuthzUrl() {
		return authzUrl;
	}
	

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClient;
    }
}