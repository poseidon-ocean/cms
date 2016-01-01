package com.lt.dao.content;

import java.util.List;

import com.lt.base.IBaseDao;
import com.lt.bean.Content;
import com.lt.core.dao.TmParams;
import com.lt.util.TmPageInfo;

public interface IContentDao extends IBaseDao<Content, Integer>{
	//查询所有内容
	public List<Content> findContents(TmParams params,TmPageInfo pageInfo);
	//统计总数
	public int countContent(TmParams params);
	//批量保存
	public void saveBatch(List<Content> contents);
}
