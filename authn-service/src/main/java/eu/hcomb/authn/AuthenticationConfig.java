package eu.hcomb.authn;

import io.dropwizard.client.JerseyClientConfiguration;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.hcomb.common.cors.CorsConfig;
import eu.hcomb.common.cors.CorsConfigurable;
import eu.hcomb.common.redis.JedisConfig;
import eu.hcomb.common.redis.JedisConfigurable;
import eu.hcomb.common.swagger.SwaggerConfig;
import eu.hcomb.common.swagger.SwaggerConfigurable;
import eu.hcomb.common.web.BaseConfig;

public class AuthenticationConfig extends BaseConfig implements CorsConfigurable, SwaggerConfigurable, JedisConfigurable {

    protected JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    protected CorsConfig corsConfig = new CorsConfig();
	
    protected SwaggerConfig swaggerConfig = new SwaggerConfig();
	
    protected JedisConfig redis = new JedisConfig();

    protected String authzUrl;

	public JedisConfig getRedis() {
		return redis;
	}

	public SwaggerConfig getSwaggerConfig() {
		return swaggerConfig;
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