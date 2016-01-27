package com.epsm.epsdWeb.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epsm.epsdCore.model.DispatcherFactory;
import com.epsm.epsdCore.model.ObjectsConnector;
import com.epsm.epsdCore.model.PowerObjectsDateTimeSource;
import com.epsm.epsdCore.model.StateSaver;
import com.epsm.epsmCore.model.dispatch.Dispatcher;
import com.epsm.epsmCore.model.generalModel.TimeService;

@Configuration
@ComponentScan("com.epsm.epsdWeb")		
public class ApplicationConfig{
	private static Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
	
	@Bean
	public TimeService createTimeService(){
		logger.debug("TimeService @Bean created.");
		return new TimeService();
	}
	
	@Bean
	public PowerObjectsDateTimeSource createPowerObjectsDateTimeSource(){
		logger.debug("PowerObjectsDateTimeSource @Bean created.");
		return new PowerObjectsDateTimeSource();
	}
	
	@Bean
	public Dispatcher createDisaptcher(TimeService timeService, StateSaver saver,
			ObjectsConnector connector, PowerObjectsDateTimeSource dateTimeSource){
		
		DispatcherFactory factory
				= new DispatcherFactory(timeService, saver, connector, dateTimeSource);
		
		logger.info("EPS Dispatcher created and run.");
		
		return factory.createDispatcher();
	}
}