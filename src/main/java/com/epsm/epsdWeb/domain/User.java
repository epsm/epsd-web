package com.epsm.epsdWeb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User implements Serializable{
	private static final long serialVersionUID = -2449109249832280403L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long Id;

	@NotEmpty
    @Column(name="name")
    private String name;

	@NotEmpty
    @Column(name="password")
    private String password;
    
	@NotEmpty
    @Column(name="email")
    private String email;

	@NotEmpty
    @Column(name = "role")
    private String role;

	public long getId() {
		return Id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
