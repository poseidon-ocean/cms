package com.lt.dao.permission.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lt.base.BaseDaoImpl;
import com.lt.bean.Permission;
import com.lt.dao.permission.IPermissionDao;

/**
 * 
 * 内容管理
 * PermissionDaoImpl
 * @version 1.0.0
 *
 */
@Repository
@Transactional
public class PermissionDaoImpl extends BaseDaoImpl<Permission,Integer> implements IPermissionDao{
	
	
	@Override
	public List<Object[]> findPermissionByUserId(Integer userId) {
		String sql = "SELECT DISTINCT t1.url,t1.model,t1.method FROM lt_permission t1 "+
		" LEFT JOIN lt_role_permission t2 ON t1.id = t2.permission_id "+
		" LEFT JOIN lt_role t3 ON t3.id = t2.role_id "+
		" LEFT JOIN lt_role_user t4 ON t4.role_id = t3.id "+
		" LEFT JOIN lt_user t5 ON t5.id = t4.user_id "+
		" WHERE t5.id = ? ";
		return getSession().createSQLQuery(sql).setInteger(0, userId).list();
	}
	
	/*
	 * 1:查询所有的根目录
	 * 2:查询所根目录下面的子目录(递归) (如果是ajax那么久不需要递归)
	 * 3:把查询根目录和递归的子目录和页面属性插件进行结合
	 * 4:数据结构你弄清楚
	 * 
	 * 
	 * */
	
	/**
	 * 
	 * 方法名：findRoot
	 * @return List<Permission>
	 * @exception 
	 * @since  1.0.0
	 * [ 
			{name : "主题框架",url : "",opid : 1,pid : "root1"}, 
			{name : "主题框架2",url : "",opid : 2,pid : "root2"}, 
			{name : "主题框架3",url : "",opid : 3,pid : "root3"}, 
			{name : "主题框架4",url : "",opid : 4,pid : "root4"}, 
			{name : "主题框架5",url : "",opid : 5,pid : "root5"}, 
			{name : "主题框架6",url : "",opid : 6,pid : "root6"} 
		]
	 */
	public List<Permission> findRoot(){
		return getSession().createQuery("from Permission where isDelete =0 and parent.id = 1").list();
	}
	
	
	/**
	 * 根据父ID查询所有的子目录
	 * 方法名：findChildrens
	 * @param parentId
	 * @return List<Permission>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Permission> findChildrens(Integer parentId){
		return getSession().createQuery("from Permission where isDelete =0 and parent.id = "+parentId).list();
	}

}
