package com.epsm.epsdweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/", "/login"})
public class LoginPageController {
	private static Logger logger = LoggerFactory.getLogger(LoginPageController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String createSingUpPage(HttpServletRequest request){
		logger.info("Requested: page from {}.", request.getRemoteAddr());
		
		return "login";
	}
}
