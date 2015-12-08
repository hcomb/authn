package eu.hcomb.authn;

import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.hcomb.common.cors.CorsConfig;
import eu.hcomb.common.cors.CorsConfigurable;
import eu.hcomb.common.jdbc.JdbcConfig;
import eu.hcomb.common.jdbc.JdbcConfigurable;
import eu.hcomb.common.web.BaseConfig;

public class AuthenticationConfig extends BaseConfig implements CorsConfigurable, JdbcConfigurable {

	protected JdbcConfig jdbcConfig = new JdbcConfig();

	public JdbcConfig getJdbcConfig() {
		return jdbcConfig;
	}

	protected CorsConfig corsConfig = new CorsConfig();

	public CorsConfig getCorsConfig() {
		return corsConfig;
	}
	
	protected String authzUrl;

	public String getAuthzUrl() {
		return authzUrl;
	}
	
	@Valid
    @NotNull
    protected JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClient;
    }
}