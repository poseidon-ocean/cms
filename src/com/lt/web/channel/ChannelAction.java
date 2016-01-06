package com.lt.web.channel;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lt.core.Method;
import com.lt.base.BaseAction;
import com.lt.core.interceptor.LtRequestMethod;
import com.lt.bean.Channel;
import com.lt.bean.User;
import com.lt.service.channel.IChannelService;

/**
 * 
 * 栏目管理
 * ChannelAction
 * 创建人:poseidon 
 * 时间：2016年01月06日 10:01:51
 * @version 1.0.0
 *
 */
@Controller("channelAction")
@Scope("prototype")
public class ChannelAction extends BaseAction{

	@Autowired
	private IChannelService channelService;
	private List<Channel> channels;
	private Channel channel;
	
	
	/**
	 * 
	 * 保存
	 * 创建人：poseidon 
	 * 时间：2016年01月06日 10:01:51
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 * 
	 */
	public String list(){
		channels = channelService.findChannels(params, page);
		int itemCount = channelService.countChannel(params);
		page.setItemCount(String.valueOf(itemCount));
		return "list";
	}
	
	/**
	 * 渲染模板的方法
	 * 方法名：listtemplate
	 * 创建人：poseidon 
	 * 时间：2016年01月06日 10:01:51
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String listTemplate(){
		channels = channelService.findChannels(params, page);
		int itemCount = channelService.countChannel(params);
		page.setItemCount(String.valueOf(itemCount));
		return "listTemplate";
	}
	
	/**
	 * 编辑方法
	 * 方法名：edit
	 * 创建人：poseidon 
	 * 时间：2016年01月06日 10:01:51
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String edit(){
		if(id!=null){
			channel = channelService.get(id);
		}
		return "edit";
	}
	
	/**
	 * 保存方法
	 * 方法名：save
	 * 创建人：poseidon 
	 * 时间：2016年01月06日 10:01:51
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method=LtRequestMethod.POST)
	public String save(){
		channel.setUser(new User(1));
		channel.setStatus(1);
		channel.setIsDelete(0);
		channel = channelService.save(channel);
		channel.setUser(null);
		result = "success";
		return AJAX_SUCCESS;
	}
	
	/**
	 * 更新内容信息
	 * 方法名：update
	 * 创建人：poseidon 
	 * 时间：2016年01月06日 10:01:51 
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method=LtRequestMethod.POST)
	public String update(){
		channel.setUpdateTime(new Date());
		channel = channelService.updateDefault(channel);
		channel=null;
		result ="success";
		return AJAX_SUCCESS;
	}
	
	/**
	 * 删除方法
	 * 方法名：delete
	 * 创建人：poseidon 
	 * 时间：2015年5月13日-上午12:02:57 
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String delete(){
		try {
			channel.setUpdateTime(new Date());
			channel.setIsDelete(1);
			channelService.updateDefault(channel);
			result = "success";
		} catch (Exception e) {
			result = "fail";
		}
		return AJAX_SUCCESS;
	}

	//setter/getter
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
}