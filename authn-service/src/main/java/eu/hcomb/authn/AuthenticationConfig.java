package eu.hcomb.authn;

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
}