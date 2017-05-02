package com.epsm.epsdweb.controller;

import com.epsm.epsdweb.service.IncomingMessageService;
import com.epsm.epsmcore.model.consumption.ScheduledLoadConsumerParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scheduledLoadConsumer")
public class ScheduledLoadConsumerController {

	private Logger logger = LoggerFactory.getLogger(ScheduledLoadConsumerController.class);
	
	@Autowired
	private IncomingMessageService service;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public void register(ScheduledLoadConsumerParameters parameters){
		logger.debug("Received parameters: {}.", parameters);

		service.register(parameters);
	}
}
