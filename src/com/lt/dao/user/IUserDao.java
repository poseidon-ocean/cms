package com.lt.dao.user;

import com.lt.bean.User;


public interface IUserDao {

	/**
	 * 根据账号和密码查询用户是否存在
	 * 方法名：findExitUser
	 * @param account
	 * @param password
	 * @return User
	 * @exception 
	 * @since  1.0.0
	 */
	public User findExitUser(String account,String password);
}
