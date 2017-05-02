package com.epsm.epsdweb.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {

    @Size(min = 6, max = 20, message="{userName.size}")
    private String userName;
    
	@Size(min = 6, max = 20, message="{password.size}")
	private String password;
	
	@Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z]+\\.[a-zA-Z]{2,6}$", message="{email.pattern}")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName.trim();
	}
}
