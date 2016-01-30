package com.epsm.epsdWeb.repository;

import com.epsm.epsdWeb.domain.User;

public interface UserDao {
	void saveUser(User user);
	User findByEmail(String email);
}
