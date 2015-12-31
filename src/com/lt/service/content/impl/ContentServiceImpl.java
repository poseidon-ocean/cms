package com.lt.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.base.BaseServiceImpl;
import com.lt.bean.Content;
import com.lt.core.dao.TmParams;
import com.lt.dao.content.IContentDao;
import com.lt.service.content.IContentService;
import com.lt.util.TmPageInfo;
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content, Integer> implements IContentService{

	public IContentDao getContentDao() {
		return (IContentDao) super.getBaseDao();
	}
	
	@Autowired
	public void setContentDao(IContentDao contentDao) {
		super.setBaseDao(contentDao);
	}
	
	@Override
	public List<Content> findContents(TmParams params, TmPageInfo pageInfo) {
		return getContentDao().findContents(params, pageInfo);
	}

	@Override
	public int countContent(TmParams params) {
		return getContentDao().countContent(params);
	}

	
	
}
