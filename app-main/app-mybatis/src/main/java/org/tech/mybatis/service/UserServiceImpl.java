package org.tech.mybatis.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tech.mybatis.dao.UserDao;
import org.tech.mybatis.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public int insertUser(User user) {
		return this.userDao.insertSelective(user);
	}
}
