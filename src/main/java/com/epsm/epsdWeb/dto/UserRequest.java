package com.epsm.epsdWeb.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z]+\\.[a-zA-Z]{2,6}$", message = "email is not valid")
    private String email;

    @Size(min = 6, max = 20, message = "password must be between 6 and 20 characters")
    private String password;

    @Size(min = 6, max = 20, message = "user name must be between 6 and 20 characters")
    private String userName;

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
