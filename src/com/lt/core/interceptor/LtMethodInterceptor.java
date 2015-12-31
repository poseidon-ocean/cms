package com.lt.core.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.lt.util.LtStringUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Component("methodInterceptor")
public class LtMethodInterceptor extends AbstractInterceptor{

	/**  */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		boolean flag = isMethod(invocation); 
		if(flag){
			return invocation.invoke();
		}else{
			return "error";
		}
	}
	@SuppressWarnings({"rawtypes","unchecked"})
	private boolean isMethod(ActionInvocation invocation) {
		boolean flag = true;
		try {
			//获取当前请求的action
//			Object action = invocation.getProxy().getAction();
			//获取当前请求类的class对象
			Class clz = invocation.getProxy().getAction().getClass();
			//获取当前请求的方式
			String postType = request.getMethod();
			//获取当前请求的方法
			String methodName = invocation.getProxy().getMethod();
			//根据返回判断当前方法是否存在
			Method method = clz.getDeclaredMethod(methodName, null);
			if(method!=null){//如果method不为null
				//获取当前方法上是否加有自己定义的注解ltMethod
				com.lt.core.Method ltMethod = method.getAnnotation(com.lt.core.Method.class);
				//如果有
				if(ltMethod!=null){
					//获取你自定义的value的值
					String value = ltMethod.value();
					//如果不为null那么并且如果你写入的请求方式和当前请求的方式不一致，返回false
					if(LtStringUtils.isNotEmpty(value) && !value.equalsIgnoreCase(postType)){
						flag = false;
					}else{
						//如果为null,那么根据枚举方式在进行一次判断，
						LtRequestMethod[] requestMethod = ltMethod.method();
						if(!requestMethod[0].toString().equalsIgnoreCase(postType)){
							flag = false;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	
}
