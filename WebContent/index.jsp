<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="js/sg/css/sg.css">
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/tz_util.js"></script>
	<script type="text/javascript" src="js/sg/sg.js"></script>
  </head>
  <body>
    
    <div class="t"></div>
    <div class="ccc"></div>
    <script type="text/javascript">
    
    window.localtion.href = "/index";
//     	loading("大家好!");
//     	$(".t").tzTab();
//     	$(".t").tzSlider({horizontal:false});
//     	$(".ccc").tzSlider({horizontal:true});
    	//$.tzAlert({content:"您确定删除吗"});
//     	$.tzPrompt({content:"请输入",value:"123456",callback:function(ok){
//     		if(ok){
//     			alert(ok);
//     		}
//     	}});
    </script>
  </body>
</html>
