package com.epsm.epsdWeb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="/esatblishconnection", method = RequestMethod.POST)
	public @ResponseBody void establishConnection(@RequestBody ConsumerParametersStub parameters){
		logger.debug("Receiver: {}.", parameters);
		service.registerConsumer(parameters);
	}
	
	@RequestMapping(value="/acceptstate", method=RequestMethod.POST)
	public @ResponseBody void acceptConsumerState(@RequestBody ConsumerState state){
		logger.debug("Received: {}.", state);
		service.acceptConsumerState(state);
	}
}
