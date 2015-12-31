package com.lt.core.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * 
 * 类: LtPermissionTag2 <br>
 * 描述: 权限标签 <br>
 * 作者: poseidon<br>
 * 版本: 1.0<br>
 * 时间: 2015年12月11日 上午9:38:22
 */
public class LtPermissionTag2 extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String method;
	private String model;
	private List<Object[]> permissionsList =null;
	private String var;

	/*
	 * 	lt:属于标签命名空间
	 *  permission:标签的名称
	 * 	属性和属性值
	 * 	<lt:permission method="xxxx" model="xxx">标签的开始doStartTag
	 * 		xxxx //标签体--doAfterBody
	 *	</lt:permission>标签结束 doEndTag
	 * */
	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() throws JspException {
		String bodyContent = (String) this.pageContext.getAttribute(var);
		if(bodyContent!=null){
			try {
				JspWriter out = this.pageContext.getOut();
				if(isPersmission()){
					out.print(bodyContent);
				}else{
					out.print("");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SKIP_BODY;//跳出标签体处理继续往下执行
	}
	
	

	private boolean isPersmission(){
		try {
			if((permissionsList==null) || (permissionsList!=null && permissionsList.size()==0))return false;
			boolean flag = false;
			for (Object[] objects2 : permissionsList) {
				String cmodel = String.valueOf(objects2[1]);// /index
				String cmethod = String.valueOf(objects2[2]);// /index
				if(cmodel.equals(model) && cmethod.equals(method)){
					flag= true;
					break;
				}
			}
			return flag;
		} catch (Exception e) {
			return false;
		}
	}
	

	//setter
	public void setMethod(String method) {
		this.method = method;
	}


	public void setVar(String var) {
		this.var = var;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
