package eu.hcomb.authn;

import eu.hcomb.common.dto.JdbcProperties;
import eu.hcomb.common.web.BaseConfig;

public class AuthenticationConfig extends BaseConfig {

	protected JdbcProperties database = new JdbcProperties();

	public JdbcProperties getDatabase() {
		return database;
	}
	
}