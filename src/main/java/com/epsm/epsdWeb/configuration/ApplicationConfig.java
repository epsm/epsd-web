package com.epsm.epsdWeb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.epsm.epsmCore.model.generalModel.TimeService;

@Configuration
@ComponentScan("com.epsm.epsdWeb")		
public class ApplicationConfig{
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer 
				= new PropertySourcesPlaceholderConfigurer();
		configurer.setLocation(new ClassPathResource("application.properties"));
        
        return configurer;
    }
	
	@Bean
	public TimeService createTimeService(){
		return new TimeService();
	}
}