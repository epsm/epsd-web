package com.epsm.epsdweb.controller;

import com.epsm.epsdweb.service.IncomingMessageService;
import com.epsm.epsmcore.model.consumption.ConsumerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

	private Logger logger = LoggerFactory.getLogger(ConsumerController.class);

	@Autowired
	private IncomingMessageService service;

	@RequestMapping(value="/acceptstate", method = RequestMethod.POST)
	public void acceptState(@RequestBody List<ConsumerState> states){
		logger.debug("Received cons.state: {}.", states);
		service.acceptConsumerStates(states);
	}
}
