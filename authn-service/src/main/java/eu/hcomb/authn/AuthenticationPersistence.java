package eu.hcomb.authn;

import java.util.Properties;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import com.google.inject.name.Names;

import eu.hcomb.authn.service.mapper.UserMapper;

public class AuthenticationPersistence extends MyBatisModule {
	
	protected AuthenticationConfig configuration;
	
	public AuthenticationPersistence(AuthenticationConfig configuration) {
		this.configuration = configuration;
	}
	
	@Override
	protected void initialize() {
		install(JdbcHelper.MySQL);
		
		bindDataSourceProviderType(PooledDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);
        addMapperClass(UserMapper.class);

        Names.bindProperties(binder(), createProperties());

	}

	private Properties createProperties() {
        Properties myBatisProperties = new Properties();
        myBatisProperties.setProperty("mybatis.environment.id", "test");
        
        myBatisProperties.setProperty("JDBC.username", configuration.getDatabase().getUsername());
        myBatisProperties.setProperty("JDBC.password", configuration.getDatabase().getPassword());
        myBatisProperties.setProperty("JDBC.autoCommit", configuration.getDatabase().getAutoCommit());
        myBatisProperties.setProperty("JDBC.host", configuration.getDatabase().getHost());
        myBatisProperties.setProperty("JDBC.port", configuration.getDatabase().getPort());
        myBatisProperties.setProperty("JDBC.schema", configuration.getDatabase().getSchema());
        return myBatisProperties;
	}
}
