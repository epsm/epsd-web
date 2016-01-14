package com.epsm.epsdWeb.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epsm.epsdCore.model.DispatcherFactory;
import com.epsm.epsdCore.model.ObjectsConnector;
import com.epsm.epsdCore.model.StateSaver;
import com.epsm.epsmCore.model.dispatch.Dispatcher;
import com.epsm.epsmCore.model.generalModel.TimeService;

@Configuration
public class DispatcherConfig {
	private Logger logger = LoggerFactory.getLogger(DispatcherConfig.class);
	
	@Autowired
	private TimeService timeService;
	
	@Autowired
	private ObjectsConnector connector;
	
	@Autowired
	private StateSaver saver;
	
	@Bean
	public Dispatcher createDisaptcher(){
		DispatcherFactory factory = new DispatcherFactory(timeService, saver, connector);
		
		logger.info("Dispatcher model @Bean created.");
		
		return factory.createDispatcher();
	}
}
