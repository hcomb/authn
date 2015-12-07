package eu.hcomb.authn;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.setup.Environment;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.name.Named;

import eu.hcomb.authn.resources.UsernamePasswordLogin;
import eu.hcomb.authn.service.UserService;
import eu.hcomb.authn.service.impl.UserServiceImpl;
import eu.hcomb.common.auth.TokenAuthenticator;
import eu.hcomb.common.auth.UserAuthorizer;
import eu.hcomb.common.healthcheck.DatasourceHealthCheck;
import eu.hcomb.common.resources.WhoAmI;
import eu.hcomb.common.service.TokenService;
import eu.hcomb.common.service.impl.TokenServiceImpl;
import eu.hcomb.common.web.BaseApp;

public class AuthenticationApp extends BaseApp<AuthenticationConfig> {
	
	public static void main(String[] args) throws Exception {
		new AuthenticationApp().run(args);
	}
	
	public void configure(Binder binder) {
		
		binder
			.bind(TokenService.class)
			.to(TokenServiceImpl.class);
		
		binder
			.bind(Authenticator.class)
			.to(TokenAuthenticator.class);

		binder
			.bind(Authorizer.class)
			.to(UserAuthorizer.class);

		binder
			.bind(UserService.class)
			.to(UserServiceImpl.class);

	}	

	@Provides
	@Named("healthcheck.query")
	public String getHealthCheckQuery(){
		return ((AuthenticationConfig)configuration).getDatabase().getHealthCheckQuery();
	}

	@Override
	public void run(AuthenticationConfig configuration, Environment environment) {
		
		defaultConfig(environment, configuration);

		AuthenticationPersistence persistence = new AuthenticationPersistence(configuration);

		injector = Guice.createInjector(this, persistence);

        setupSecurity(environment);
        
		environment.jersey().register(injector.getInstance(UsernamePasswordLogin.class));
		environment.jersey().register(injector.getInstance(WhoAmI.class));
		
		environment.healthChecks().register("mysql", injector.getInstance(DatasourceHealthCheck.class));
				
	}
	

}