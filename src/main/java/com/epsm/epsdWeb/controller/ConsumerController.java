package com.epsm.epsdWeb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epsm.epsdWeb.service.IncomingMessageService;
import com.epsm.epsmCore.model.consumption.ConsumerParametersStub;
import com.epsm.epsmCore.model.consumption.ConsumerState;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {
	private Logger logger = LoggerFactory.getLogger(ConsumerController.class);
	
	@Autowired
	private IncomingMessageService service;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> registerConsumer(@RequestBody ConsumerParametersStub parameters){
		logger.debug("Received cons.par.: {}.", parameters);
		
		if(service.registerConsumer(parameters)){
			logger.debug("Returned OK (request was {})", parameters);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else{
			logger.warn("Returned CONFLICT (request was {})", parameters);
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value="/acceptstate", method=RequestMethod.POST)
	public @ResponseBody void acceptConsumerState(@RequestBody ConsumerState state){
		logger.debug("Received cons.state: {}.", state);
		service.acceptConsumerState(state);
	}
}
