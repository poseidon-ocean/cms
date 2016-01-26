package com.lt.service.stat.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.core.dao.TmParams;
import com.lt.base.BaseServiceImpl;
import com.lt.dao.stat.IStatDao;
import com.lt.bean.Stat;
import com.lt.service.stat.IStatService;
import com.lt.util.TmPageInfo;

/**
 * 
 * 统计管理
 * StatServiceImpl 
 * 创建人:poseidon 
 * 时间：2016年01月20日 15:54:00
 * 
 * @version 1.0.0
 * 
 */
@Service
public class StatServiceImpl extends BaseServiceImpl<Stat,Integer>  implements IStatService {
		
	public IStatDao getStatDao() {
		return (IStatDao) super.getBaseDao();
	}
	
	@Autowired
	public void setStatDao(IStatDao statDao) {
		super.setBaseDao(statDao);
	}
	
	/**
	 * 查询所有的内容--永远都在一起的，求总数
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
		return getStatDao().findStats(params, pageInfo);
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
		return getStatDao().countStat(params);
	}
}
