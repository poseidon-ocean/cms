/**
 * 系统平台
 */
package com.lt.service.ws;

import java.util.List;
import java.util.Map;

import com.lt.bean.Channel;


public interface IChannelServiceWeb {
	public String save(Channel channel);
	public Map<String, Object> save(Map<String, Object> map); 
	public List<Map<String, Object>> findChannels();
}
