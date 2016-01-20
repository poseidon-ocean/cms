package com.lt.service.ws.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.bean.User;
import com.lt.service.user.IUserService;
import com.lt.service.ws.IUserServiceWeb;

@Service
public class UserServiceWebImpl implements IUserServiceWeb {

	@Autowired
	private IUserService userService;
	
	@Override
	public List<User> findUsers() {
		return userService.find("from User");
	}

	@Override
	public boolean saveUser(User user) {
		user = userService.save(user);
		return user == null ? false : true;
	}

	@Override
	public String loveYou(String msg) {
		System.out.println(msg);
		return "你的信息收到了";
	}

}
