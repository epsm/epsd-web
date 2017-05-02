package com.epsm.epsdweb.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.epsm.epsdweb.repository")
public class DbTestConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/epsd");
        dataSource.setUsername("postgres");
        dataSource.setPassword("secret");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
        Properties properties = new Properties();
        
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        factory.setJpaVendorAdapter(adaptor);
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.epsm.epsdweb.domain");
        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();

        return factory;
    }
}