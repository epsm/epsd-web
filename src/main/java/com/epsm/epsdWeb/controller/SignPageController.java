package com.epsm.epsdWeb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epsm.epsdWeb.dto.UserRequest;
import com.epsm.epsdWeb.service.UserService;

@Controller
@RequestMapping({"/", "/sign"})
public class SignPageController {

	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String createSingUpPage(){
		return "sign";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyData(@ModelAttribute("request") @Valid UserRequest request,
			Errors errors, ModelMap model) {

		if(wasRequestValidated(errors)){
			tryToAddNewUser(request, errors, model);
		}

		return "sign";
	}
	
	private boolean wasRequestValidated(Errors errors){
		return !errors.hasErrors();
	}
	
	private void tryToAddNewUser(UserRequest request, Errors errors, ModelMap model){
		if(!wasUserAdded(request)){
			showCause(errors, model);
		}
	}
	
	private boolean wasUserAdded(UserRequest request){
		return service.addNewUser(request.getUserName(), request.getPassword(),
				request.getEmail());
	}
	
	private void showCause(Errors errors, ModelMap model){
		errors.rejectValue("email", "", "this email is busy");
	}
	
	@ModelAttribute("request")
    public UserRequest getUserRequest() {
		return new UserRequest();
    }
}
