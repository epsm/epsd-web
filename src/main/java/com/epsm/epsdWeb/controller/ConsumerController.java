package com.epsm.epsdWeb.controller;

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
	
	@Autowired
	private IncomingMessageService service;
	
	@RequestMapping(value="/esatblishconnection", method = RequestMethod.POST)
	public @ResponseBody void establishConnection(@RequestBody ConsumerParametersStub parameters){
		service.establishConnectionWithConsumer(parameters);
	}
	
	@RequestMapping(value="/acceptstate", method=RequestMethod.POST)
	public @ResponseBody void acceptConsumerState(@RequestBody ConsumerState state){
		service.acceptConsumerState(state);
	}
}
