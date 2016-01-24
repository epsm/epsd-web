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
import com.epsm.epsmCore.model.generation.PowerStationParameters;
import com.epsm.epsmCore.model.generation.PowerStationState;

@RestController
@RequestMapping("/api/powerstation")
public class PowerStationController {
	private Logger logger = LoggerFactory.getLogger(PowerStationController.class);
	
	@Autowired
	private IncomingMessageService service;
	
	@RequestMapping(value="/esatblishconnection", method = RequestMethod.POST)
	public @ResponseBody void establishConnection(@RequestBody PowerStationParameters parameters){
		logger.debug("Received: {}.", parameters);
		service.registerPowerStation(parameters);
	}
	
	@RequestMapping(value="/acceptstate", method=RequestMethod.POST)
	public @ResponseBody void acceptPowerStationState(@RequestBody PowerStationState state){
		logger.debug("Received: {}.", state);
		service.acceptPowerStationState(state);
	}
}
