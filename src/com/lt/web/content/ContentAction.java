package com.lt.web.content;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lt.base.BaseAction;
import com.lt.bean.Content;
import com.lt.bean.User;
import com.lt.core.Method;
import com.lt.core.interceptor.LtRequestMethod;
import com.lt.service.content.IContentService;
import com.lt.util.LtStringUtils;
import com.lt.util.TmFileUtil;
@Controller("contentAction")
@Scope("prototype")
public class ContentAction  extends BaseAction{
	@Autowired
	private IContentService contentService;
	//集合,为什么要全局的
	private List<Content> contents;
	
	private Content content;
	
	/**
	 * 
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 * 
	 */
	public String list(){
		contents = contentService.findContents(params, page);
		int itemCount = contentService.countContent(params);
		page.setItemCount(String.valueOf(itemCount));
		return "list";
	}
	/**
	 * 
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 * 
	 */
	public String falls(){
		return "falls";
	}
	
	/**
	 * 渲染模板的方法
	 * 方法名：listtemplate
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String listTemplate(){
		contents = contentService.findContents(params, page);
		int itemCount = contentService.countContent(params);
		page.setItemCount(String.valueOf(itemCount));
		return "listTemplate";
	}
	
	//搜索，修改，添加，模糊搜索做完，改成通用模板，封装
	public String delete(){
		try {
			contentService.deleteById(id);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		return AJAX_SUCCESS;
	}
	
	/**
	 * 
	 * 描  述: 保存内容 <br>
	 * 时  间: 2015年12月23日 下午4:54:50
	 * @return
	 */
	@Method(method=LtRequestMethod.POST)
	public String save(){
		content.setUser(new User(1));
		content.setIsComment(1);
		content.setStatus(1);
		content.setSort(0);
		content.setHit(0);
		content.setIsDelete(0);
		content.setIsTop(0);
		content = contentService.save(content);
		content.setUser(null);
		result = "success";
		return AJAX_SUCCESS;
	}
	/**
	 * 更新内容信息
	 * 方法名：update
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method=LtRequestMethod.POST)
	public String update(){
		content.setUpdateTime(new Date());
		content = contentService.updateDefault(content);
		content=null;
		result ="success";
		return AJAX_SUCCESS;
	}
	
	/**
	 * 编辑方法
	 * 方法名：edit
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public void edit(){//和struts2的ajax无关了
		Content content2 = contentService.get(id);
		outObject(content2);
	}
	
	@Method(method=LtRequestMethod.POST)
	public String downimg(){
		System.out.println("您当前的下载的图片是:"+params.getImg());
		result = downNetImg(params.getImg());
		return AJAX_SUCCESS;
	}
	
	private static String downNetImg(String src){
		try {
			URL url = new URL(src);
			URLConnection connection = url.openConnection();
			int totalSize = connection.getContentLength();//网络图片的大小,字节 跑道的100米
			InputStream inputStream = connection.getInputStream();
			//获取服务器的路径
			String uploadPath = ServletActionContext.getRequest().getRealPath("upload");
			//如果upload不存在就创建
			File rootFile = new File(uploadPath);
			if(!rootFile.exists())rootFile.mkdirs();
			//获取新的文件名
			String newname = TmFileUtil.getUUID()+TmFileUtil.getExt(src);
			//获取磁盘输出流
			FileOutputStream outputStream = new FileOutputStream(new File(uploadPath,newname));
			byte[] b = new byte[2048];
			//输入流
			BufferedInputStream in = new BufferedInputStream(inputStream);
			//io读写瓶颈--
			int len = 0;
			//开始读和写
			int length =0; 
			HttpSession session = ServletActionContext.getRequest().getSession();
			while((len = in.read(b))!=-1){//读
				length += len;//已经下载的长度,小涛跑25米， 25%
				Thread.sleep(50);
				System.out.println("您当前下载了:"+LtStringUtils.getPercent(length, (float)totalSize,"#"));
				session.setAttribute(src, LtStringUtils.getPercent(length, (float)totalSize,"#"));
				outputStream.write(b, 0, len);//写
			}
			in.close();
			outputStream.close();//关闭
			inputStream.close();
			return "upload/"+newname;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//ajax获取正在上传的进度条信息
	public void imgPercent(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		result = (String) session.getAttribute(params.getImg());
		if(LtStringUtils.isEmpty(result))result = "0";
		outObject(result);
	}
	
	

	//setter/getter
	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
}
