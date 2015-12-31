package com.lt.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.bean.User;
import com.lt.dao.user.IUserDao;
import com.lt.service.user.IUserService;
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;

	@Override
	public User findExitUser(String account, String password) {
		return userDao.findExitUser(account, password);
	}
	
}
