package com.lt.service.ws.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.bean.Channel;
import com.lt.service.channel.IChannelService;
import com.lt.service.ws.IChannelServiceWeb;
import com.lt.util.BeanMapUtils;
@Service
public class ChannelServiceWebImpl implements IChannelServiceWeb{

	@Autowired
	private IChannelService channelservice;
	
	@Override
	public String save(Channel channel) {
		try {
			channel = channelservice.save(channel);
			return JSONUtil.serialize(channel);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> save(Map<String, Object> map) {
		try {
			Channel channel = (Channel)BeanMapUtils.toBean(Channel.class, map);
			channel = channelservice.save(channel);
			return BeanMapUtils.toMap(channel);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> findChannels() {
		try {
			List<Channel> channels = channelservice.find("from Channel");
			List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
			for (Channel channel : channels) {
					maps.add(BeanMapUtils.toMap(channel));
			}
			return maps;
		} catch (Exception e) {
			return null;
		}
	}

}
