package com.lt.service.content;

import java.util.List;

import com.lt.base.IBaseService;
import com.lt.bean.Content;
import com.lt.core.dao.TmParams;
import com.lt.util.TmPageInfo;

/**
 * 
 * 类: IContentService <br>
 * 描述: 内容业务层 <br>
 * 作者: poseidon<br>
 * 版本: 1.0<br>
 * 时间: 2015年12月22日 上午11:24:59
 */
public interface IContentService extends IBaseService<Content, Integer>{
	
	/**
	 * 查询所有的内容--永远都在一起的，求总数
	 * 方法名：findContents
	 * @param params
	 * @param pageInfo
	 * @return List<Content>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Content> findContents(TmParams params,TmPageInfo pageInfo);
	
	/**
	 * 求总数
	 * 方法名：countContent
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int countContent(TmParams params);
	
}
