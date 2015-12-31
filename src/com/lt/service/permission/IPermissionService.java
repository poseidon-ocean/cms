package com.lt.service.permission;

import java.util.HashMap;
import java.util.List;

import com.lt.base.IBaseService;
import com.lt.bean.Permission;


/**
 * 
 * 内容业务层
 * IPermissionService
 * @version 1.0.0
 *
 */
public interface IPermissionService extends IBaseService<Permission,Integer> {
	
	/**
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
	public List<HashMap<String, Object>> findRoot();
	

	/**
	 * 根据父ID查询所有的子目录
	 * 方法名：findChildrens
	 * @param parentId
	 * @return List<Permission>
	 * @exception 
	 * @since  1.0.0
	 */
	List<HashMap<String, Object>> findChildrens(Integer parentId);
}
