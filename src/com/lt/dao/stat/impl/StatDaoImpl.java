package com.lt.dao.stat.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lt.base.BaseDaoImpl;
import com.lt.core.dao.TmParams;
import com.lt.dao.stat.IStatDao;
import com.lt.bean.Stat;
import com.lt.util.TmPageInfo;
import com.lt.util.LtStringUtils;

/**
 * 
 * 统计管理
 * StatDaoImpl
 * 创建人:poseidon
 * 时间：2016年01月20日 15:54:00 
 * @version 1.0.0
 *
 */
@Repository
@Transactional
public class StatDaoImpl extends BaseDaoImpl<Stat,Integer> implements IStatDao{
	
	/**
	 * 
	 * 查询所有的内容
	 * 求总数 sql hql qbc 
	 * 方法名：findStats
	 * 创建人：poseidon 
	 * 时间：2016年01月20日 15:54:00 
	 * @param params
	 * @param pageInfo
	 * @return List<Stat>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Stat> findStats(TmParams params,TmPageInfo pageInfo){
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
	 * 方法名：countStat
	 * 创建人：poseidon 
	 * 时间：2016年01月20日 15:54:00 
	 * @param params
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public int countStat(TmParams params){
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
}
