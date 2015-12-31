package com.lt.service.permission.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.base.BaseServiceImpl;
import com.lt.bean.Permission;
import com.lt.dao.permission.IPermissionDao;
import com.lt.service.permission.IPermissionService;


/**
 * 
 * 
 * PermissionServiceImpl 
 * 
 * @version 1.0.0
 * 
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Integer>
		implements IPermissionService {

	public IPermissionDao getPermissionDao() {
		return (IPermissionDao) super.getBaseDao();
	}

	@Autowired
	public void setPermissionDao(IPermissionDao permissionDao) {
		super.setBaseDao(permissionDao);
	}
	
	public List<Object[]> findPermissionByUserId(Integer userId) {
		return getPermissionDao().findPermissionByUserId(userId);
	}

	/**
	 * 容器--List<map>
	 * [ 
			{name : "主题框架",url : "",opid : 1,pid : "root1"},=map.put("name":"主题框架"); 
			{name : "主题框架2",url : "",opid : 2,pid : "root2"}, =map.put("name":"主题框架");
			{name : "主题框架3",url : "",opid : 3,pid : "root3"}, map.put("name":"主题框架");
			{name : "主题框架4",url : "",opid : 4,pid : "root4"}, map.put("name":"主题框架");
			{name : "主题框架5",url : "",opid : 5,pid : "root5"}, map.put("name":"主题框架");
			{name : "主题框架6",url : "",opid : 6,pid : "root6"} map.put("name":"主题框架");
		]
	 */
	public List<HashMap<String, Object>> findRoot() {
		List<Permission> permissions = getPermissionDao().findRoot();
		List<HashMap<String, Object>> maps = null;
		if(permissions!=null && permissions.size()>0){
			maps = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> map = null;
			for (Permission permission : permissions) {
				map = new HashMap<String, Object>();
				map.put("opid", permission.getId());
				map.put("name", permission.getName());
				map.put("url", "javascript:void(0);");
				map.put("pid", permission.getId());
				maps.add(map);
			}
		}
		return maps;
	}
	
	

	/**
	 * 根据父ID查询所有的子目录
	 * 方法名：findChildrens
	 * @param parentId
	 * @return List<Permission>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<HashMap<String, Object>> findChildrens(Integer parentId){
		List<Permission> permissions = getPermissionDao().findChildrens(parentId);
		List<HashMap<String, Object>> maps = null;
		if(permissions!=null && permissions.size()>0){
			maps = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> map = null;
			for (Permission permission : permissions) {
				map = new HashMap<String, Object>();
				map.put("opid", permission.getId());
				map.put("name", permission.getName());
				map.put("url", "javascript:void(0);");
				map.put("pid", permission.getId());
				maps.add(map);
			}
		}
		return maps;
	}
}
