package com.lt.core.tag;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lt.bean.Content;
import com.lt.service.content.IContentService;
import com.lt.util.LtStringUtils;

public class ContentBeanTag extends BodyTagSupport{

	/**  */
	private static final long serialVersionUID = 1L;
	private String var;//对象的变量名称
	private Integer cid;
	
	@Override
	public int doStartTag() throws JspException {
		ServletContext context = this.pageContext.getServletContext();
		WebApplicationContext wcx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		IContentService contentService = (IContentService) wcx.getBean("contentServiceImpl");
		Content content = contentService.get(cid);
		if(LtStringUtils.isEmpty(var)) var = "content";
		pageContext.setAttribute(var, content);
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
}
