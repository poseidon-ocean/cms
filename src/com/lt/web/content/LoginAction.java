package com.lt.web.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lt.base.BaseAction;
import com.lt.bean.User;
import com.lt.service.permission.IPermissionService;
import com.lt.service.user.IUserService;
import com.lt.util.LtConstanst;
import com.lt.util.LtStringUtils;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * 类: LoginAction <br>
 * 描述: 登录 <br>
 * 作者: poseidon<br>
 * 版本: 1.0<br>
 * 时间: 2015年12月5日 下午7:23:40
 */
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction{
	@Autowired
	private IUserService userService;
	@Autowired
	private IPermissionService permissionService;

	private String account;
	private String password;
	
	/**
	 * 跳转到登陆页面 方法名：login 
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String login() {
		User user = (User)ActionContext.getContext().getSession().get(LtConstanst.SESSION_USERKEY);
		if (user == null) {
			return "login";
		} else {
			return "toIndex";
		}
		
	}

	/**
	 * 登陆方法 方法名：logined 
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String logined() {
		if (LtStringUtils.isNotEmpty(account)
				&& LtStringUtils.isNotEmpty(password)) {
			password = LtStringUtils.md5Base64(password);
			User user = userService.findExitUser(account, password);
			if (user != null) {
				ActionContext.getContext().getSession()
						.put(LtConstanst.SESSION_USERKEY, user);
				result = "success";
				
				//获取用户所有的权限
				List<Object[]> permissions = permissionService.findPermissionByUserId(user.getId());
				ActionContext.getContext().getSession().put(LtConstanst.PERMISSION_SESSION_USERKEY, permissions);
			} else {
				result = "fail";
			}
		} else {
			result = "empty";
		}
		account = null;
		password = null;// 密码没有必要返回给浏览器
		return AJAX_SUCCESS;
	}
	
	/**
	 * 退出登录
	 * 方法名：logout
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String logout(){
		ActionContext.getContext().getSession().clear();
		return "toLogin";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
