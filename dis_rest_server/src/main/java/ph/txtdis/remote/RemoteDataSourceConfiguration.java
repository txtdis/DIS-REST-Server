package ph.txtdis.remote;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration("remoteDataSourceConfiguration")
@EnableJpaRepositories(entityManagerFactoryRef = "remoteEntityManagerFactory",
		transactionManagerRef = "remoteTransactionManager")
public class RemoteDataSourceConfiguration {

	@Bean
	@ConfigurationProperties(prefix = "datasource.remote")
	public DataSource remoteDataSource() {
		return DataSourceBuilder.create().build();
	}

	private JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter a = new HibernateJpaVendorAdapter();
		a.setGenerateDdl(true);
		return a;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean remoteEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean b = new LocalContainerEntityManagerFactoryBean();
		b.setDataSource(remoteDataSource());
		b.setJpaVendorAdapter(jpaVendorAdapter());
		b.setPackagesToScan(RemoteDataSourceConfiguration.class.getPackage().getName());
		return b;
	}

	@Bean
	PlatformTransactionManager remoteTransactionManager() {
		return new JpaTransactionManager(remoteEntityManagerFactory().getObject());
	}
}
