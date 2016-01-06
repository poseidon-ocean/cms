package com.lt.service.channel;

import java.util.List;

import com.lt.core.dao.TmParams;
import com.lt.base.IBaseService;
import com.lt.bean.Channel;
import com.lt.util.TmPageInfo;

/**
 * 
 * 栏目管理业务层
 * IChannelService
 * 创建人:poseidon
 * 时间：2016年01月06日 10:01:51
 * @version 1.0.0
 *
 */
public interface IChannelService extends IBaseService<Channel,Integer> {
	/**
	 * 查询所有的内容
	 * 方法名：findChannels
	 * 创建人：poseidon 
	 * 时间：2016年01月06日 10:01:51 
	 * @param params
	 * @param pageInfo
	 * @return List<Channel>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Channel> findChannels(TmParams params,TmPageInfo pageInfo);
	
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
	public int countChannel(TmParams params);
}
