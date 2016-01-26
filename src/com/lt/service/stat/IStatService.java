package com.lt.service.stat;

import java.util.List;

import com.lt.core.dao.TmParams;
import com.lt.base.IBaseService;
import com.lt.bean.Stat;
import com.lt.util.TmPageInfo;

/**
 * 
 * 统计管理业务层
 * IStatService
 * 创建人:poseidon
 * 时间：2016年01月20日 15:54:00
 * @version 1.0.0
 *
 */
public interface IStatService extends IBaseService<Stat,Integer> {
	/**
	 * 查询所有的内容
	 * 方法名：findStats
	 * 创建人：poseidon 
	 * 时间：2016年01月20日 15:54:00 
	 * @param params
	 * @param pageInfo
	 * @return List<Stat>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Stat> findStats(TmParams params,TmPageInfo pageInfo);
	
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
	public int countStat(TmParams params);
}
