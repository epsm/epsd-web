package com.epsm.epsdweb.controller;

import com.epsm.epsdweb.service.IncomingMessageService;
import com.epsm.epsmcore.model.consumption.RandomLoadConsumerParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/randomLoadConsumer")
public class RandomLoadConsumerController {

	private Logger logger = LoggerFactory.getLogger(RandomLoadConsumerController.class);
	
	@Autowired
	private IncomingMessageService service;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public void register(RandomLoadConsumerParameters parameters){
		logger.debug("Received parameters: {}.", parameters);

		service.register(parameters);
	}
}
