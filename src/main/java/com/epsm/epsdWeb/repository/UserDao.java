package com.epsm.epsdWeb.repository;

import java.util.List;

import com.epsm.epsdWeb.domain.User;

public interface UserDao {
	void saveUser(User user);
	List<User> findByEmail(String email);
}
