package com.lt.core.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.lt.util.LtConstanst;

/**
 * 
 * 类: LtPermissionTag <br>
 * 描述: 权限标签 <br>
 * 作者: poseidon<br>
 * 版本: 1.0<br>
 * 时间: 2015年12月11日 上午9:26:21
 */
public class LtPermissionTag extends BodyTagSupport{

	/**  */
	private static final long serialVersionUID = 1L;
	private String method;
	private String model;
	private List<Object[]> permissionsList = null;
	
	/*
	 * 	lt:属于标签命名空间
	 *  permission:标签的名称
	 * 	属性和属性值
	 * 	<lt:permission method="xxxx" model="xxx">标签的开始doStartTag
	 * 		xxxx //标签体--doAfterBody
	 *	</lt:permission>标签结束 doEndTag
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public void doInitBody() throws JspException {
		permissionsList = (List<Object[]>)this.pageContext.getSession().getAttribute(LtConstanst.PERMISSION_SESSION_USERKEY);
	}
	@Override
	public int doAfterBody() throws JspException {
		String bodyContent = this.getBodyContent().getString();
		if(bodyContent != null){
			try {
				JspWriter out = this.getPreviousOut();
				//if(isPersmission()){
					out.print(bodyContent);
				//}else{
				//	out.print("");
				//}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SKIP_BODY;//跳出标签体处理继续往下执行
	}
	private boolean isPersmission() {
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
			e.printStackTrace();
			return false;
		}
	}
	
	//setter
	public void setMethod(String method) {
		this.method = method;
	}


	public void setModel(String model) {
		this.model = model;
	}

}
