package com.epsm.epsdweb.controller;

import com.epsm.epsdweb.service.IncomingMessageService;
import com.epsm.epsmcore.model.generation.PowerStationParameters;
import com.epsm.epsmcore.model.generation.PowerStationState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/powerstation")
public class PowerStationController {

	private Logger logger = LoggerFactory.getLogger(PowerStationController.class);
	
	@Autowired
	private IncomingMessageService service;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public void register(PowerStationParameters parameters){
		logger.debug("Received parameters: {}.", parameters);

		service.register(parameters);
	}

	@RequestMapping(value="/acceptstate", method = RequestMethod.POST)
	public void acceptState(@RequestBody List<PowerStationState> states){
		logger.debug("Received cons.state: {}.", states);
		service.acceptPowerStationStates(states);
	}
}
