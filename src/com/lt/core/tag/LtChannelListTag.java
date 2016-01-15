package com.lt.core.tag;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lt.bean.Channel;
import com.lt.service.channel.IChannelService;
import com.lt.util.LtStringUtils;

/**
 * 
 * 类: LtChannelListTag <br>
 * 描述: 栏目列表  自定义标签 <br>
 * 作者: poseidon<br>
 * 版本: 1.0<br>
 * 时间: 2016年1月4日 上午9:15:23
 */
public class LtChannelListTag extends BodyTagSupport{
	/**  */
	private static final long serialVersionUID = 1L;
	private String var;//变量的对象名称
	private Iterator<Channel> iterator = null;
	private Integer index;
	
	@Override
	public int doStartTag() throws JspException {
		ServletContext context = this.pageContext.getServletContext();
		WebApplicationContext wcx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		IChannelService channelService = (IChannelService) wcx.getBean("channelServiceImpl");
		if(LtStringUtils.isEmpty(var)) var = "channel";
		List<Channel> channels = channelService.find("from Channel where isDelete = 0 and parent.id is null order by sort asc");
		if(channels != null && channels.size() > 0){
			index = 0;
			this.pageContext.setAttribute(var+"_size", channels.size());
			this.pageContext.setAttribute(var+"_index", index);
			iterator = channels.iterator();
			this.pageContext.setAttribute(var, iterator.next());
			return EVAL_BODY_INCLUDE;
		}else{
			return SKIP_BODY;
		}
	}
	
	@Override
	public int doAfterBody() throws JspException {
		if(iterator.hasNext()){
			index ++;
			this.pageContext.setAttribute(var+"_index", index);
			this.pageContext.setAttribute(var, iterator.next());
			return EVAL_BODY_AGAIN;
		}else{
			return SKIP_BODY;
		}
		
	}
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
}
