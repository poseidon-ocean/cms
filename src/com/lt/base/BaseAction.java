package com.lt.base;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;

import com.lt.core.dao.TmParams;
import com.lt.util.TmPageInfo;


public class BaseAction {
	public static final String AJAX_SUCCESS = "ajaxSuccess";
	public static final String LIST = "list";
	public static final String ADD = "add";
	public static final String EDIT = "edit";
	public static final String DELETE = "delete";
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public static final String ERROR = "error";
	public Integer id;
	
	//分页
	public TmPageInfo page =  new TmPageInfo();
	//参数
	public TmParams params = new TmParams();
	
	protected String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public TmPageInfo getPage() {
		return page;
	}

	public void setPage(TmPageInfo page) {
		this.page = page;
	}

	public TmParams getParams() {
		return params;
	}

	public void setParams(TmParams params) {
		this.params = params;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void outObject(Object obj){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(JSONUtil.serialize(obj));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println("出现异常了-->" + e);
		}
	}
}
