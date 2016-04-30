<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML>
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
  	<form action="${basePath}/upload/upload" method="post" enctype="multipart/form-data">
  		<a href="javascript:void(0);" class="uploadbtn" onclick="openFileDialog()">文件上传</a>
  		<input type="file" name="doc" multiple="multiple" accept="image/*" id="file" />
  		<input  type="submit" value="提交"/>
  	</form>
  	
  	<img id="showimg"/>
  	<ul id="listbox"></ul>
  	<div id="percent">
  		<div id="per"></div>
  		<span id="num"></span>
  	</div>
  	<script type="text/javascript">
	  
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
		
		
		//怎么上传呢 ajax文件上传--如何触发
		//如果拿到上传的文件
		//怎么传输给后台
		//html5 --拖拽文件上传
		function uploadFile(){
			console.log("===start3============");
			//拿到文件上传的队列
			var files  = document.getElementById("file").files;
			var file = files[0];
			var name = file.name;//文件的名称
			//var pre = name.substring(0,name.lastIndexOf("."));
			var ext = name.substring(name.lastIndexOf("."), name.length);
			file.name = "20160311" + ext;
			console.log("=====================================");
			//file.name = 
			for(var k in file){
				console.log(k+"===="+file[k]);
			}
			
			//showImage(file,function(data){
			//	document.getElementById("showimg").src = data;
			//});
			
			var type = file.type;//文件的类型
			var size = file.size;//文件的大小
			
			console.log(type);
			console.log(size);
			console.log(name);
			
// 			if(type.indexOf("image")==-1){
// 				alert("请上传图片文件!!!!");
// 				return;
// 			}
			
			//设置文件信息
			var form = new FormData();
			form.append("file",file);
			console.log("===start4============");
			//如何传输给服务器端,进度条
			var xhr = new XMLHttpRequest();
			//请求服务器
			xhr.open("post","fileUp.do",true);
			//请求成功的回调函数
			xhr.onreadystatechange = function(){
				if(this.readyState ==4 && this.status==200){
					var data = this.responseText.trim();
					var jsonData = eval("("+data+")");
					var html = "";
					for(var i=0;i<jsonData.length;i++){
						html+="<li>"+jsonData[i].name+"===="+jsonData[i].sizeString+" <img width='50' height='50' src='"+jsonData[i].url+"'></li>";
					}
					document.getElementById("listbox").innerHTML +=html;
				}
			};
			
			//监听文件上传的进度
			xhr.upload.addEventListener("progress",progressFunction,false);
			xhr.send(form);
		};
		
		
		//进度条
		function progressFunction(evt){
			var percentDom = document.getElementById("per");
			var numDom = document.getElementById("num");
			if(evt.lengthComputable){
				//evt.loaded 已经上传了多少了, event.total:上传文件的总大小
				var p = evt.loaded / event.total;
				var pnum = Math.floor(p * 100)+"%";
				numDom.innerHTML =pnum ;
				numDom.style.left =(640 *p-5)  +"px";
				percentDom.style.width =pnum; 
			}
		}
		
		
		/*图片预览*/
		function showImage(f,callback){
			var reader = new FileReader();  
	        reader.onload = (function(theFile) {  
	            return function(e) {  
	                // img 元素  
	                if(callback)callback(e.target.result);
	            };  
	  
	        })(f);  
	        reader.readAsDataURL(f);  
		};	
		
  	</script>
  </body>
</html>
