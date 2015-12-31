package com.lt.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lt.base.BaseAction;
import com.lt.util.TmFileUtil;
/**
 * 
 * 类: UploadAction <br>
 * 描述: 文件上传action <br>
 * 作者: poseidon<br>
 * 版本: 1.0<br>
 * 时间: 2015年12月29日 上午10:24:42
 */
@Controller("uploadAction")
@Scope("prototype")
public class UploadAction extends BaseAction {

	// 接受文件
	private File file;
	// 文件名
	private String fileFileName;
	// 上传文件的类型
	private String fileContentType;
	
	public String demo(){
		return "demo";
	}
	public String swf(){
		return "swf";
	}
	public String demo2(){
		return "demo2";
	}
	//第一步建立uploadaction
		//第二部配置uploaaction
		//第三步：能够上传的页面
		//第四步：定义文件上传的方法和属性
		//第五步：正在的上传
//			System.out.println("我是文件上传!!!!");
//			System.out.println("我上传的文件是:"+file);
//			System.out.println("文件名是:"+fileFileName);
//			System.out.println("文件类型是:"+fileContentType);
	public String upload() {
		HashMap<String, Object> map = new HashMap<String,Object>();
		//1：上传放入服务器，如何获取服务器的上传的路径
		String uploadPath = ServletActionContext.getServletContext().getRealPath("upload");
		//2：如果服务器上传路径不存在，就创建
		File dirPath = new File(uploadPath);
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
		//3:进行io流读写，将本地的图片上传到服务器的上传路基下
		//对上传文件的重命名
		String newFileName = generateFileName(fileFileName);
		//文件上传了 io也可以用FileUtils
		//建立文件上传的缓存读和写的流
		BufferedOutputStream outputStream =null;//reponse.getWriter(),它也是一个输出流.往浏览器输出的
		BufferedInputStream inputStream = null;
		try {
			//将文件开始输入流
			FileInputStream fis = new FileInputStream(file);
			inputStream = new BufferedInputStream(fis);
			//输出流
			FileOutputStream out = new FileOutputStream(new File(dirPath,newFileName));
			outputStream = new BufferedOutputStream(out);
			//以多少开始读取文件的流 文件，
			byte[] buf = new byte[4096];
			int len = -1;
			while((len = inputStream.read(buf))!=-1){//读客户端的文件
				outputStream.write(buf, 0, len);//写入到服务器
			}
			map.put("name", newFileName);
			map.put("oldName", fileFileName);
			map.put("url", "upload/"+newFileName);
			map.put("size", file.length());
			map.put("ext", TmFileUtil.getExtNoPoint(fileFileName));
			map.put("type", fileContentType);
			map.put("path", "upload");
			map.put("sizeString", TmFileUtil.countFileSize(file.length()));
			result = JSONUtil.serialize(map);
		} catch (Exception e) {
		} finally{
			
			try {
				if(outputStream!=null){
					outputStream.close();
				}
				if(inputStream!=null){
					inputStream.close();
				}
			} catch (Exception e2) {
			}
		}
		return "callback";
	}
	/**
	 * 文件的重命名
	 * 方法名：generateFileName
	 * @param fileName
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	private String generateFileName(String fileName){
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int random = new Random().nextInt(100000);
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String newFileName = dateFormat+random+ext;
		return newFileName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
}
