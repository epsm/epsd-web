package com.epsm.epsdweb.configuration;

import com.epsm.epsmcore.model.simulation.TimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.epsm")
public class ApplicationConfig{

	@Bean
	public TimeService createTimeService(){
		return new TimeService();
	}
}