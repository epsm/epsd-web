package com.epsm.epsdWeb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epsm.epsmCore.model.generalModel.TimeService;

@RestController
@RequestMapping("/")
public class IndicatorController{
	private StringBuilder builder = new StringBuilder();
	private Logger logger = LoggerFactory.getLogger(IndicatorController.class);
	
	@Autowired
	private TimeService timeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String indicate(){
		builder.append("user.dir: " + System.getProperty("user.dir") + ". ");
		builder.append("OPENSHIFT_MYSQL_DB_HOST: " + System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ". ");
		builder.append("OPENSHIFT_MYSQL_DB_PORT: " + System.getenv("OPENSHIFT_MYSQL_DB_PORT") + ". ");
		builder.append("proc: " + Runtime.getRuntime().availableProcessors() + ".");
		builder.append("#2  date and time on dispatcher server: ");
		builder.append(timeService.getCurrentDateTime());
		
		logger.debug("Invoked.");
		
		return builder.toString();
	}
}