package org.tech.mybatis.service;

import org.tech.mybatis.pojo.User;

public interface UserService {

	User getUserById(int userId);
	
	int insertUser(User user);

}
