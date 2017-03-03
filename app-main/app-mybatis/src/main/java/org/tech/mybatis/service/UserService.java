package org.tech.mybatis.service;

import org.tech.mybatis.model.User;

public interface UserService {

	User getUserById(int userId);
	
	int insertUser(User user);

}
