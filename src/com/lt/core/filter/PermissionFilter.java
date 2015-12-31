package com.lt.core.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.bean.User;
import com.lt.util.LtConstanst;

/**
 * 
 * 类: PermissionFilter <br>
 * 描述: 权限过滤器 <br>
 * 作者: poseidon<br>
 * 版本: 1.0<br>
 * 时间: 2015年12月10日 下午4:22:50
 */
public class PermissionFilter implements Filter{

	private ServletContext context;
	private String devmode;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.devmode = filterConfig.getInitParameter("devmode");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		if("true".equals(devmode)){
			filterChain.doFilter(request, response);
			return;
		}
		User user = (User)request.getSession().getAttribute(LtConstanst.SESSION_USERKEY);
		//如果没用struts2框架或者springmvc
		if(user!=null){
			//访问的链接地址
			String url = request.getRequestURI();
			List<Object[]> permissions = (List<Object[]>) request.getSession().getAttribute(LtConstanst.PERMISSION_SESSION_USERKEY);
			if(isPersmission(url, permissions)){
				filterChain.doFilter(request, response);//filter的过让，让步（让下一个拦截器或者servlet执行）
			}else{
				response.sendRedirect(request.getContextPath()+"/error.jsp");
			}
		}else{
			response.sendRedirect(request.getContextPath()+"/login");
		}
	}

	private boolean isPersmission(String url, List<Object[]> permissions) {
		if((permissions==null) || (permissions!=null && permissions.size()==0))return false;
		boolean flag = false;
		for (Object[] objects2 : permissions) {
			String linkUrl = String.valueOf(objects2[0]);// /index
			if(url.indexOf(linkUrl)!=-1){
				flag= true;
				break;
			}
		}
		return flag;
	}

	@Override
	public void destroy() {
		
		
	}

}
