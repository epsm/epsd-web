package com.epsm.epsdWeb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epsm.epsdWeb.dto.UserRequest;
import com.epsm.epsdWeb.service.UserService;

@Controller
@RequestMapping("registration")
public class RegistrationPageController {
	private static Logger logger = LoggerFactory.getLogger(RegistrationPageController.class);
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String createSingUpPage(HttpServletRequest request){
		logger.info("Requested: page from {}.", request.getRemoteAddr());
		
		return "registration";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyData(@ModelAttribute("request") @Valid UserRequest request,
			Errors errors, ModelMap model, HttpServletRequest servletRequest) {

		logger.info("Requested: page from {}.", servletRequest.getRemoteAddr());
		
		if(! wasRequestValidated(errors)){
			return "registration";
		}
		
		encodePassword(request);
		
		if(! tryToAddNewUser(request)){
			showCause(errors, model);
			return "registration";
		}

		return "login";
	}
	
	private boolean wasRequestValidated(Errors errors){
		boolean validated = !errors.hasErrors();
		logger.debug("Returned: error(s) in registration parameters {}.", errors);
		
		return validated;
	}
	
	private void encodePassword(UserRequest request){
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		String rawPassword = request.getPassword();
		String encodedPassword = encoder.encode(rawPassword);
		request.setPassword(encodedPassword);
	}
	
	private boolean tryToAddNewUser(UserRequest request){
		boolean added = service.addNewUser(request.getUserName(), request.getPassword(),
				request.getEmail());
		logger.debug("Added: {} new user with email {}.", request.getEmail());
		
		return added;
	}
	
	private void showCause(Errors errors, ModelMap model){
		errors.rejectValue("email", "", "this email is busy");
	}
	
	@ModelAttribute("request")
    public UserRequest getUserRequest() {
		return new UserRequest();
    }
}
