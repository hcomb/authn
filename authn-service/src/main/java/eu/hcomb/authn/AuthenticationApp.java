package eu.hcomb.authn;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.setup.Environment;

import com.google.inject.Binder;
import com.google.inject.Guice;

import eu.hcomb.authn.resources.UsernamePasswordAuthentication;
import eu.hcomb.authn.resources.WhoAmI;
import eu.hcomb.common.auth.TokenAuthenticator;
import eu.hcomb.common.auth.UserAuthorizer;
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

	}	

	@Override
	public void run(AuthenticationConfig configuration, Environment environment) {
		
		defaultConfig(environment, configuration);

        injector = Guice.createInjector(this);

        setupSecurity(environment, "token", "jwt", "realm", "Bearer");
        
		environment.jersey().register(injector.getInstance(UsernamePasswordAuthentication.class));
		environment.jersey().register(injector.getInstance(WhoAmI.class));
				
	}

}