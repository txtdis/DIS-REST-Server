package ph.txtdis;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import ph.txtdis.domain.Warehouse;

//@Configuration("primaryDataSourceConfiguration")
@EnableJpaRepositories(entityManagerFactoryRef = "primaryEntityManagerFactory",
		transactionManagerRef = "primaryTransactionManager")
public class PrimaryDataSourceConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	private JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter a = new HibernateJpaVendorAdapter();
		a.setGenerateDdl(true);
		return a;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean b = new LocalContainerEntityManagerFactoryBean();
		b.setDataSource(primaryDataSource());
		b.setJpaVendorAdapter(jpaVendorAdapter());
		b.setPackagesToScan(Warehouse.class.getPackage().getName());
		return b;
	}

	@Bean
	PlatformTransactionManager primaryTransactionManager() {
		return new JpaTransactionManager(primaryEntityManagerFactory().getObject());
	}
}