package com.lt.service.channel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.core.dao.TmParams;
import com.lt.base.BaseServiceImpl;
import com.lt.dao.channel.IChannelDao;
import com.lt.bean.Channel;
import com.lt.service.channel.IChannelService;
import com.lt.util.TmPageInfo;

/**
 * 
 * 栏目管理
 * ChannelServiceImpl 
 * 创建人:poseidon 
 * 时间：2016年01月06日 10:01:51
 * 
 * @version 1.0.0
 * 
 */
@Service
public class ChannelServiceImpl extends BaseServiceImpl<Channel,Integer>  implements IChannelService {
		
	public IChannelDao getChannelDao() {
		return (IChannelDao) super.getBaseDao();
	}
	
	@Autowired
	public void setChannelDao(IChannelDao channelDao) {
		super.setBaseDao(channelDao);
	}
	
	/**
	 * 查询所有的内容--永远都在一起的，求总数
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
		return getChannelDao().findChannels(params, pageInfo);
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
		return getChannelDao().countChannel(params);
	}

	@Override
	public List<Channel> findRootChannels(TmParams params, TmPageInfo pageInfo) {
		return getChannelDao().findRootChannels(params, pageInfo);
	}

	@Override
	public int countRootChannel(TmParams params) {
		return getChannelDao().countRootChannel(params);
	}
}
