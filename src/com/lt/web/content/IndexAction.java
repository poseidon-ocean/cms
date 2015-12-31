package com.lt.web.content;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lt.base.BaseAction;
import com.lt.core.Method;
import com.lt.core.interceptor.LtRequestMethod;

/**
 * 
 * 登录IndexAction  
 * 
 * @version 1.0.0
 * 
 */
@Controller("indexAction")
@Scope("prototype")
public class IndexAction  extends BaseAction{

	//@Method(method = LtRequestMethod.POST)
	public String execute(){
		return SUCCESS;
	}
}
