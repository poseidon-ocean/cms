package com.lt.dao.user.impl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lt.base.BaseDaoImpl;
import com.lt.bean.User;
import com.lt.dao.user.IUserDao;

@Repository
@Transactional
public class UserDaomImpl extends BaseDaoImpl<User, Integer> implements IUserDao {
	@Override
	public User findExitUser(String account, String password) {
		String hql = "from User where account = ? and password = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, account);
		query.setString(1, password);
		User user = (User) query.uniqueResult();
		return user;
	}
}
