package com.epsm.epsdWeb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.epsm.epsdCore.model.DispatcherFactory;
import com.epsm.epsdCore.model.ObjectsConnector;
import com.epsm.epsdCore.model.StateSaver;
import com.epsm.epsmCore.model.dispatch.Dispatcher;
import com.epsm.epsmCore.model.generalModel.TimeService;

@Configuration
@Import(TimeService.class)
public class DispatcherConfig {
	
	@Autowired
	private TimeService timeService;
	
	@Autowired
	private ObjectsConnector connector;
	
	@Autowired
	private StateSaver saver;
	
	@Bean
	public Dispatcher createDisaptcher(){
		DispatcherFactory factory = new DispatcherFactory(timeService, saver, connector);
		
		return factory.createDispatcher();
	}
}
