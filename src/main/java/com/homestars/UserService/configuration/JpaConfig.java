package com.homestars.UserService.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 *
 * @author nchopra
 *
 */
@Configuration
@EnableJpaRepositories("com.homestars.UserService.repository")
@EnableTransactionManagement
public class JpaConfig {

	public static final String DATASOURCE_TODO = "dsUser";
	public static final String JDBCTEMPLATE_TODO = "jdbcUser";

	@Bean(name = DATASOURCE_TODO)
	@Primary
	@ConfigurationProperties(prefix = "spring.boots.datasource")
	public DataSource userDataSource() {
		return dataSourceBuilder().build();
	}

	private DataSourceBuilder dataSourceBuilder() {
		return DataSourceBuilder.create().type(HikariDataSource.class);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(new String[] {"com.homestars.UserService.dao"});
		factory.setDataSource(userDataSource());
		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public TransactionTemplate transactionTemplate(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager(entityManagerFactory);
		return new TransactionTemplate(txManager);
	}

}
