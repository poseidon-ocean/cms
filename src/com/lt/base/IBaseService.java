package com.lt.base;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 类: IBaseService <br>
 * 描述: TODO <br>
 * 作者: poseidon<br>
 * 版本: 1.0<br>
 * 时间: 2015年12月11日 下午12:39:46
 * @param <T>
 * @param <PK>
 */
public interface IBaseService<T,PK extends Serializable> {

	/**
	 * 保存方法
	 * 方法名：save
	 * @param t
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public T save(T t); 
	
	/**
	 * 根据主键获取实体
	 * 方法名：get
	 * @param id
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public T get(PK id) ;
	
	/**
	 * 根据主键获取实体
	 * 方法名：load
	 * @param id
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public T load(PK id) ;
	
	/**
	 * 根据主键删除
	 * 方法名：get
	 * @param id
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public void delete(T entity);
	
	/**
	 * 根据主键获取实体
	 * 方法名：get
	 * @param id
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public T deleteById(PK id); 
	
	public T update(T entity);
	
	public T updateDefault(T entity);
	
	public List<T> find(String sql ,Object...args);
	
}
