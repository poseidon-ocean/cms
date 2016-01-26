package com.lt.web.stat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lt.core.Method;
import com.lt.base.BaseAction;
import com.lt.core.interceptor.LtRequestMethod;
import com.lt.bean.Stat;
import com.lt.bean.User;
import com.lt.service.stat.IStatService;

/**
 * 
 * 统计管理
 * StatAction
 * 创建人:poseidon 
 * 时间：2016年01月20日 15:54:00
 * @version 1.0.0
 *
 */
@Controller("statAction")
@Scope("prototype")
public class StatAction extends BaseAction{

	@Autowired
	private IStatService statService;
	private List<Stat> stats;
	private Stat stat;
	
	
	/**
	 * 
	 * 保存
	 * 创建人：poseidon 
	 * 时间：2016年01月20日 15:54:00
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 * 
	 */
	public String list(){
		stats = statService.findStats(params, page);
		int itemCount = statService.countStat(params);
		page.setItemCount(String.valueOf(itemCount));
		return "list";
	}
	
	/**
	 * 渲染模板的方法
	 * 方法名：listtemplate
	 * 创建人：poseidon 
	 * 时间：2016年01月20日 15:54:00
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String listTemplate(){
		stats = statService.findStats(params, page);
		int itemCount = statService.countStat(params);
		page.setItemCount(String.valueOf(itemCount));
		return "listTemplate";
	}
	
	/**
	 * 编辑方法
	 * 方法名：edit
	 * 创建人：poseidon 
	 * 时间：2016年01月20日 15:54:00
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String edit(){
		if(id!=null){
			stat = statService.get(id);
		}
		return "edit";
	}
	
	/**
	 * 保存方法
	 * 方法名：save
	 * 创建人：poseidon 
	 * 时间：2016年01月20日 15:54:00
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method=LtRequestMethod.POST)
	public String save(){
		stat.setUser(new User(1));
		stat.setIsDelete(0);
		stat = statService.save(stat);
		stat.setUser(null);
		result = "success";
		return AJAX_SUCCESS;
	}
	
	/**
	 * 更新内容信息
	 * 方法名：update
	 * 创建人：poseidon 
	 * 时间：2016年01月20日 15:54:00 
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method=LtRequestMethod.POST)
	public String update(){
		stat.setUpdateTime(new Date());
		stat = statService.updateDefault(stat);
		stat=null;
		result ="success";
		return AJAX_SUCCESS;
	}
	
	/**
	 * 删除方法
	 * 方法名：delete
	 * 创建人：poseidon 
	 * 时间：2015年5月13日-上午12:02:57 
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String delete(){
		try {
			stat.setUpdateTime(new Date());
			stat.setIsDelete(1);
			statService.updateDefault(stat);
			result = "success";
		} catch (Exception e) {
			result = "fail";
		}
		return AJAX_SUCCESS;
	}

	//setter/getter
	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}

	public List<Stat> getStats() {
		return stats;
	}

	public void setStats(List<Stat> stats) {
		this.stats = stats;
	}
}