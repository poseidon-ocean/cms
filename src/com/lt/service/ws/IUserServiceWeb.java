/**
 * 系统平台
 */
package com.lt.service.ws;

import java.util.List;

import com.lt.bean.User;

/**
 * 
 * 类: IUserServiceWeb <br>
 * 描述: TODO <br>
 * 作者: poseidon<br>
 * 版本: 1.0<br>
 * 时间: 2016年1月19日 上午11:14:41
 */
public interface IUserServiceWeb {

	public List<User> findUsers();

	public boolean saveUser(User user);
	
	public String loveYou(String msg);
}
