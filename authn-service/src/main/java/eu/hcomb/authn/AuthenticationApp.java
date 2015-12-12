package eu.hcomb.authn;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

import org.mybatis.guice.datasource.helper.JdbcHelper;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Provides;
import com.google.inject.name.Named;

import eu.hcomb.authn.resources.UsernamePasswordLogin;
import eu.hcomb.common.healthcheck.DatasourceHealthCheck;
import eu.hcomb.common.jdbc.DefaultPersistenceModule;
import eu.hcomb.common.resources.WhoAmI;
import eu.hcomb.common.web.BaseApp;

public class AuthenticationApp extends BaseApp<AuthenticationConfig> {
	
	public static void main(String[] args) throws Exception {
		new AuthenticationApp().run(args);
	}
	
	AuthenticationConfig configuration;
	
	Client client;
	
	public void configure(Binder binder) {
		configureSecurity(binder);

		
	}

	@Override
	public void run(AuthenticationConfig configuration, Environment environment) {

		this.configuration = configuration;

		injector = Guice.createInjector(this);

		defaultConfig(environment, configuration);
        
		client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());
		
		environment.jersey().register(injector.getInstance(UsernamePasswordLogin.class));
		environment.jersey().register(injector.getInstance(WhoAmI.class));

		setUpSwagger(configuration, environment);
						
	}
		
	@Provides
	@Named("authz.url")
	public String getAuthzUrl(){
		return configuration.getAuthzUrl();
	}

	@Provides
	public Client getClient(){
		return this.client;
	}
}