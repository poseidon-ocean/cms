<%@page import="org.apache.struts2.json.JSONUtil"%>
<%@page import="com.lt.util.StringUtils"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	
	String userDir = request.getParameter("dirpath");
	if(StringUtils.isEmpty(userDir))userDir = "default";
	System.out.println("==========="+userDir);

	/*文件上传的方式*/
	
	//1：获取文件上传的工厂类
	FileItemFactory factory = new DiskFileItemFactory();
	//2:解析上传文件的工厂
	ServletFileUpload fileUpload = new ServletFileUpload(factory);
	//3:通过解析类解析request对象中的二进制文件 request.getInputStream()
	List<FileItem> fileItems = fileUpload.parseRequest(request);
	//4:开始获取二进制文件，开始写入给服务器端
	
	
	//获取服务器的路径 getRealPath()获取当前项目所在服务器的根目录I:\tomcat764\webapps\tz_desk\resource
	String fpath = "resource/"+userDir;
	String path = request.getRealPath(fpath);
	File rootPath = new File(path);
	//如果目录不存在，就动态创建
	if(!rootPath.exists()){
		rootPath.mkdirs();
	}
	
	//重构文件的名称---头像上传的原理就是文件的覆盖 
	List<HashMap<String,Object>> maps = new ArrayList<HashMap<String,Object>>();
	//如果有文件，就开始进行读和写
	if(fileItems!=null && fileItems.size()>0){
		for(FileItem fileItem : fileItems){
			if(!fileItem.isFormField()){//如果是普通的表单元素就直接忽略 ,我只需要file控制的
				HashMap<String,Object> map = new HashMap<String,Object>();
				//获取文件的名称
				String filename = fileItem.getName();
				//文件大小
				long fileSize = fileItem.getSize();
				//获取文件后缀
				String ext = StringUtils.getExt(filename, true);
				//重构文件名称
				//String fname = UUID.randomUUID().toString()+ext;
				String fname = StringUtils.generateFileName(filename,9999,"yyyyMMddHHmmss");
				//上传服务器的路径
				File fileName = new File(rootPath,fname);
				//开始写入
				fileItem.write(fileName);
				//UUID-点击 UMAC地址 U主机名，D时间，IP地址
				
				map.put("name", filename);
				map.put("size", fileSize);
				map.put("sizeString", StringUtils.countFileSize(fileSize));
				map.put("url",fpath+"/"+fname);
				maps.add(map);
			}
			//和数据返回预览 --如果实现QQ空间文件上传,进度条
		}
		out.print(JSONUtil.serialize(maps));
	}else{
		out.print("null");
	}
	
	//5:在服务器创建一个上传的目录
	//6:开始写入
	//7：返回数据
	
	
	//=====文件上传的原理
	//是直接到目标目录吗 
	//如果不是，为什么要这样做.
	
	
	



%>