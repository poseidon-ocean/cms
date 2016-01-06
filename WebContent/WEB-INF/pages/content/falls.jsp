<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css">
<%@include file="/commons/public.jsp" %>
<script type="text/javascript" src="${basePath}/js/sg/lt_page.js"></script>
<script type="text/javascript" src="${basePath}/js/ckeditor/ckeditor.js"></script>  

<style>
	#loader{width:100%;height:60px;background:url("${basePath}/images/load.gif") no-repeat center;position:fixed;bottom:0;left:0;}
</style>
</head>
<body>
  <div class="container">
	<%@include file="/commons/header.jsp" %>
     <div id="contentbox" class="ht_con" >
          <table class="news_list">
          <thead>
	          <tr>
		          <th>序号</th>
		          <th>标题</th>
		          <th>时间</th> 
		          <th>操作</th>
	          </tr>
          </thead>
          <tbody id="tz_tbody">
          	
          </tbody>
          </table>
          <div id="page" style="position: relative;"></div>
		  <div id="console"></div>
     </div>
</div>
<div id="loader"></div>
<script type="text/javascript" src="${basePath}/js/ltPage.js"></script>
<script type="text/javascript">
	$(function(){
		
		//========================查询关注组合================================================
		//页数
		var pageNo = 0;
		var psize = 10;
		var mark = true;
		var followList = $("#tz_tbody");
		var $loader = $("#loader");
		function getData(){
			if(mark){
				mark = false;
				$loader.show();
				$.ajax({
					type:"post",
					url:basePath+"/admin/content/listTemplate",
					data:{
						"page.firstResult":(pageNo*psize),
						"page.maxResults":psize
					},
					async:true,
					success:function(data){
						followList.append(data);
						$loader.hide();
						mark = true;
						if(isEmpty(data)){
							mark = false;
						}
					},
					error:function(){
						alert(88);
					}
				});
			}
		}
		
		getData();
		
		$(window).scroll(function(){
			var scrollBottom =  $(document).height() - $(window).height() - $(window).scrollTop();
			if(scrollBottom < 1){
				pageNo ++;
				getData();
			}
		});
		
	});
	
	
	var getData = function(){
		$.ajax({
			type:"post",
			url:basePath+"/admin/content/listTemplate",
			data:{
				"page.firstResult":(pageNo*psize),
				"page.maxResults":psize
			},
			async:true,
			success:function(data){
				followList.append(data);
				$loader.hide();
				mark = true;
				if(isEmpty(data)){
					mark = false;
				}
			},
			error:function(){
				alert(88);
			}
		});
	};
	$.ltpage.init({getData:getData});
	
</script>
<div class="bottom">Copyright © 2013-2015 www.tanzhouedu.com All Rights Reserved 版权所有：湖南潭州教育咨询有限公司 备案号：备13016338号</div>
</body>
</html>