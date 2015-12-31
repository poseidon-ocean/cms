package com.lt.dao.permission;


import java.util.List;

import com.lt.base.IBaseDao;
import com.lt.bean.Permission;


/**
 * 
 * 内容接口
 * IPermissionDao
 * @version 1.0.0
 *
 */
public interface IPermissionDao extends IBaseDao<Permission,Integer> {
	
	
	/**
	 * 
	 * 根据用户找到对应角色在对应所有的权限
	 * 方法名：findPermissionByUserId
	 * @param userId
	 * @return List<Object[]>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Object[]> findPermissionByUserId(Integer userId);
	
	/**
	 * 查询所有的根目录
	 * 方法名：findRoot
	 * @return List<Permission>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Permission> findRoot();
	

	/**
	 * 根据父ID查询所有的子目录
	 * 方法名：findChildrens
	 * @param parentId
	 * @return List<Permission>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Permission> findChildrens(Integer parentId);
}
