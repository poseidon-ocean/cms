<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<%@include file="/commons/public.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajax文件上传的开发</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style type="text/css">
		*{padding:0;margin:0}
		a{text-decoration:none; }
		body{font-size:14px;font-family:"微软雅黑";padding:10px;}
		.uploadbtn{padding:8px 14px;background:#111;color:#fff}
		
		#percent{width:640px;height:34px;border:1px solid #111;position:relative;}
		#per{position:absolute;top:0;left:0;height:34px;width:0%;background:green;color:#fff;line-height: 34px;}
		#num{position:relative;} 
	</style>
  </head>
  <body>
  	<form id="form" action="${basePath}/upload/upload" method="post" enctype="multipart/form-data">
  		<a href="javascript:void(0);" class="uploadbtn" onclick="openFileDialog()">文件上传</a>
  		<input type="file" name="file" multiple="multiple" accept="image/*" id="file"  style="display: none;"/>
  	</form>
  	
  	<img id="showimg"/>
  	<ul id="listbox"></ul>
  	<div id="percent">
  		<div id="per"></div>
  		<span id="num"></span>
  	</div>
  	
  	<img id="preview" src="">
  	
  	<canvas id="canvas"></canvas>
  	<script type="text/javascript">
  	
  	 $("#file").on("change",function(){
  		var windowURL = window.URL|| window.webkitURL;
  		var dataURL;
		var $file = $(this);
 		//拿到文件上传的队列
 		var files  = document.getElementById("file").files;
 		
 		var fileObj = files[0];
 		var name = fileObj.name;//文件的名称
 		
 		var ext = name.substring(name.lastIndexOf("."), name.length);
		var filename = "20160311" + ext;
		
		
		var $img = $("#preview");
		
		
		if(fileObj && fileObj.files && fileObj.files[0]){
			dataURL = windowURL.createObjectURL(fileObj.files[0]);
			$img.attr('src',dataURL);
		}else{
			dataURL = $file.val();
			var imgObj = document.getElementById("preview");
			// 两个坑:
			// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
			// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
			imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
		}
 		 console.log(name);
 	 });
  	
 // 上传图片，jQuery版 
  	function sendImage(){ 
  		// 创建canvas DOM 对象 
  		var canvas = document.querySelector("canvas");
	  	// 获取Base64编码后的图像数据，格式是字符串 
	  	// "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。 
	  	var dataurl = canvas.toDataURL("image/png"); 
	  	// 为安全 对URI进行编码 
	  	// data%3Aimage%2Fpng%3Bbase64%2C 开头 
	  	var imagedata = encodeURIComponent(dataurl); 
	  	var url = $("#form").attr("action"); 
	  	var data = { 
	  			imagename: "myImage.png", 
	  			imagedata: imagedata 
	  			}; 
	  	
	  	$.ajax( { 
	  		url : url, 
	  		data : data, 
	  		type : "POST", 
	  		// 期待的返回值类型 
	  		dataType: "json", 
	  		complete : function(xhr,result) { 
		  		if(!xhr){ 
			  		alert('网络连接失败!'); 
			  		return false; 
		  		} 
		  		var text = xhr.responseText; 
		  		if(!text){ 
			  		alert('网络错误!'); 
			  		return false; 
		  		}
		  		var json = eval("("+text+")"); 
		  		if(!json){ 
			  		alert('解析错误!'); 
			  		return false; 
		  		} else { 
		  			alert(json.message); 
		  		} 
	  		}
	  	});
 	}
 
	
	  	
  	
	 
		function openFileDialog(){
  			console.log("===start1============");
			var ie = navigator.appName == "Microsoft Internet Explorer"?true:false;
			if(ie){
				//如果是ie浏览器
				document.getElementById("file").click();
			}else{
				//自定义事件es5中的内容
				var a = document.createEvent("MouseEvents");
				a.initEvent("click",true,true);
				document.getElementById("file").dispatchEvent(a);
			}
			console.log("===start2============");
		};
  	</script>
</html>
