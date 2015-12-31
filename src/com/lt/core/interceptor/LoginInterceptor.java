package com.lt.core.interceptor;

import org.springframework.stereotype.Component;

import com.lt.bean.User;
import com.lt.util.LtConstanst;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 
 * 登陆拦截器
 * LoginInterceptor
 * @version 1.0.0
 *
 */
@Component("loginInterceptor")
public class LoginInterceptor extends AbstractInterceptor{

	/**  */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("您当前执行的Action是:"+invocation.getAction().getClass().getName());
		System.out.println("您当前执行的方法是:"+invocation.getProxy().getMethod());
		//获取action的上下文
		ActionContext context = invocation.getInvocationContext();
		//获取session中的值
		User user = (User) context.getSession().get(LtConstanst.SESSION_USERKEY);
		if(user!=null){
			return invocation.invoke();
		}else{
			//return "toLogin"; 
			return invocation.invoke();
		}
	}

}
