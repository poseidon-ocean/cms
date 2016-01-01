package com.lt.dao.content.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lt.base.BaseDaoImpl;
import com.lt.bean.Content;
import com.lt.core.dao.TmParams;
import com.lt.dao.content.IContentDao;
import com.lt.util.LtStringUtils;
import com.lt.util.TmPageInfo;
@Repository
@Transactional
public class ContentDaoImpl extends BaseDaoImpl<Content, Integer> implements IContentDao{

	//查询所有内容
	public List<Content> findContents(TmParams params,TmPageInfo pageInfo){
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if(params!=null){
			if(LtStringUtils.isNotEmpty(params.getKeyword())){
				detachedCriteria.add(Restrictions.like("title", params.getKeyword(),MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.addOrder(Order.desc("createTime"));
		return findByDetachedCriteria(detachedCriteria,pageInfo);
	}
	//统计总数
	public int countContent(TmParams params){
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if(params!=null){
			if(LtStringUtils.isNotEmpty(params.getKeyword())){
				detachedCriteria.add(Restrictions.like("title", params.getKeyword(),MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.setProjection(Projections.count("id"));
		Number number = (Number)detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
		return number==null?0:number.intValue();
	}
	//批量保存
	@Override
	public void saveBatch(List<Content> contents) {//数据的导入和导出,excel表的数据导入到数据库中
		Session session = getSession();
		for (int i = 0; i < contents.size(); i++) {
			session.save(contents.get(i));
			if(i%50==0){
				session.flush();
				session.clear();
			}
		}
	}
	
	//批量修改
	public void updateBatch(List<Content> contents) {
		Session session = getSession();
		for (int i = 0; i < contents.size(); i++) {
//			必须根据id去更新
			session.update(contents.get(i));
			if(i%50==0){
				session.flush();
				session.clear();
			}
		}
	}

}
