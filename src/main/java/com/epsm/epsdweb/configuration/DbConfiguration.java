package com.epsm.epsdweb.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DbConfiguration {

	@Value("${database.url}")
	private String databaseUrl;
	
	@Value("${database.username}")
	private String username;
	
	@Value("${database.password}")
	private String password;
	
	@Value("${database.driver}")
	private String databaseDriver;
	
	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	
    @Bean
    public DataSource dataSource() {    	
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(databaseDriver);
        
        return dataSource;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
        Properties properties = new Properties();
        
        properties.put("hibernate.dialect", hibernateDialect);
               
        factory.setJpaVendorAdapter(adaptor);
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.epsm.epsdweb.domain");
        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();

        return factory;
    }
    
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
    }
}