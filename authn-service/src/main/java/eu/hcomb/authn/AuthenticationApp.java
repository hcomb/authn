package eu.hcomb.authn;

import io.dropwizard.setup.Environment;

import org.mybatis.guice.datasource.helper.JdbcHelper;

import com.google.inject.Binder;
import com.google.inject.Guice;

import eu.hcomb.authn.resources.UsernamePasswordLogin;
import eu.hcomb.authn.service.UserService;
import eu.hcomb.authn.service.impl.UserServiceImpl;
import eu.hcomb.authn.service.mapper.UserMapper;
import eu.hcomb.common.healthcheck.DatasourceHealthCheck;
import eu.hcomb.common.jdbc.DefaultPersistenceModule;
import eu.hcomb.common.resources.WhoAmI;
import eu.hcomb.common.web.BaseApp;

public class AuthenticationApp extends BaseApp<AuthenticationConfig> {
	
	public static void main(String[] args) throws Exception {
		new AuthenticationApp().run(args);
	}
	
	public void configure(Binder binder) {
		configureSecurity(binder);

		binder
			.bind(UserService.class)
			.to(UserServiceImpl.class);

	}

	@Override
	public void run(AuthenticationConfig configuration, Environment environment) {
		
		DefaultPersistenceModule persistence = new DefaultPersistenceModule(configuration) {
			@Override
			protected void initialize() {
				install(JdbcHelper.MySQL);
				setup();
		        addMapperClass(UserMapper.class);				
			}
		};

		injector = Guice.createInjector(this, persistence);

		defaultConfig(environment, configuration);
        
		environment.jersey().register(injector.getInstance(UsernamePasswordLogin.class));
		environment.jersey().register(injector.getInstance(WhoAmI.class));
		
		environment.healthChecks().register("mysql", injector.getInstance(DatasourceHealthCheck.class));
				
	}
	
}