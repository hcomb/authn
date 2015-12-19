package eu.hcomb.authn;

import io.dropwizard.setup.Environment;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Provides;
import com.google.inject.name.Named;

import eu.hcomb.authn.resources.UsernamePasswordLogin;
import eu.hcomb.common.resources.WhoAmI;
import eu.hcomb.common.service.EventEmitter;
import eu.hcomb.common.service.impl.RedisEventEmitter;
import eu.hcomb.common.web.BaseApp;

public class AuthenticationApp extends BaseApp<AuthenticationConfig> {
	
	public static void main(String[] args) throws Exception {
		new AuthenticationApp().run(args);
	}
	
	AuthenticationConfig configuration;
	
	@Override
	public String getName() {
        return "authn-service";
    }
	
	public void configure(Binder binder) {
		configureSecurity(binder);

		binder
			.bind(EventEmitter.class)
			.to(RedisEventEmitter.class);
	}

	@Override
	public void run(AuthenticationConfig configuration, Environment environment) {

		init(environment, configuration);
		this.configuration  = configuration;

		injector = Guice.createInjector(this);

		defaultConfig(environment, configuration);
		
		environment.jersey().register(injector.getInstance(UsernamePasswordLogin.class));
		environment.jersey().register(injector.getInstance(WhoAmI.class));

		setUpSwagger(configuration, environment);
						
	}
		
	@Provides
	@Named("authz.url")
	public String getAuthzUrl(){
		return configuration.getAuthzUrl();
	}

}