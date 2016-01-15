package com.lt.dao.channel.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lt.base.BaseDaoImpl;
import com.lt.core.dao.TmParams;
import com.lt.dao.channel.IChannelDao;
import com.lt.bean.Channel;
import com.lt.util.TmPageInfo;
import com.lt.util.LtStringUtils;

/**
 * 
 * 栏目管理
 * ChannelDaoImpl
 * 创建人:poseidon
 * 时间：2016年01月06日 10:01:51 
 * @version 1.0.0
 *
 */
@Repository
@Transactional
public class ChannelDaoImpl extends BaseDaoImpl<Channel,Integer> implements IChannelDao{
	
	/**
	 * 
	 * 查询所有的内容
	 * 求总数 sql hql qbc 
	 * 方法名：findChannels
	 * 创建人：poseidon 
	 * 时间：2016年01月06日 10:01:51 
	 * @param params
	 * @param pageInfo
	 * @return List<Channel>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Channel> findChannels(TmParams params,TmPageInfo pageInfo){
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if(params!=null){
			if(LtStringUtils.isNotEmpty(params.getKeyword())){
				detachedCriteria.add(Restrictions.like("name", params.getKeyword(),MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.addOrder(Order.desc("createTime"));
		detachedCriteria.add(Restrictions.eq("isDelete",0));
		return findByDetachedCriteria(detachedCriteria,pageInfo);
	}
	
	/**
	 * 求总数
	 * 方法名：countChannel
	 * 创建人：poseidon 
	 * 时间：2016年01月06日 10:01:51 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int countChannel(TmParams params){
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if(params!=null){
			if(LtStringUtils.isNotEmpty(params.getKeyword())){
				detachedCriteria.add(Restrictions.like("name", params.getKeyword(),MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.setProjection(Projections.count("id"));
		detachedCriteria.add(Restrictions.eq("isDelete",0));
		Number number = (Number)detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
		return number==null?0:number.intValue();
	}

	@Override
	public List<Channel> findRootChannels(TmParams params, TmPageInfo pageInfo) {
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if(params != null){
			if(LtStringUtils.isNotEmpty(params.getKeyword())){
				detachedCriteria.add(Restrictions.like("name", params.getKeyword(),MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.add(Restrictions.isNull("parent.id"));
		detachedCriteria.addOrder(Order.asc("sort"));
		detachedCriteria.add(Restrictions.eq("isDelete",0));
		return findByDetachedCriteria(detachedCriteria, pageInfo);
	}

	@Override
	public int countRootChannel(TmParams params) {
		DetachedCriteria detachedCriteria = getCurrentDetachedCriteria();
		if(params != null){
			if(LtStringUtils.isNotEmpty(params.getKeyword())){
				detachedCriteria.add(Restrictions.like("name", params.getKeyword(),MatchMode.ANYWHERE));
			}
		}
		detachedCriteria.add(Restrictions.isNull("parent.id"));
		detachedCriteria.setProjection(Projections.count("id"));
		detachedCriteria.add(Restrictions.eq("isDelete", 0));
		Number number = (Number)detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
		return number==null?0:number.intValue();
	}
}
