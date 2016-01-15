<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>CMS系统-栏目管理列表</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css">
<%@include file="/commons/public.jsp" %>
<script type="text/javascript" src="${basePath}/js/sg/lt_page.js"></script>
<script type="text/javascript" src="${basePath}/js/ckeditor/ckeditor.js"></script>  
<style type="text/css">
	.tzui-btnbox{border: 1px solid #e5e5e5;height:30px;}
	.tzui-btnbox .tzui_inp{border: 0px;width: 254px;float:left;height:30px;text-indent: 0.5em; }
	.tzui-btnbox .tzui-btn{padding:4px 20px;background: #34A5A1;float:left;position: relative;top: -1px;color:#fff;cursor: pointer;}
	.tmui-status{cursor: pointer;}
</style>
</head>
<body>
  <div class="container">
	<%@include file="/commons/header.jsp" %>
     <div id="channelbox" class="ht_con clearfix" style="min-height: 700px;">
          <a href="${basePath}/admin/channel/edit">添加栏目管理</a>
          <div class="tzui-btnbox">
          	<input type="text" class="tzui_inp" id="keyword" placeholder="请输入关键字...">
          	<a class="tzui-btn" id="tzui_search" class="search" onclick="lt_search(this)">搜索</a>
          </div>
          <table class="news_list">
          <thead>
	          <tr>
		          <th>序号</th>
		          <th>logo</th>
		          <th>标题</th>
		          <th>关键字</th>
		          <th>排序号</th>
		          <th>创建时间</th>
		          <th>状态</th>
		          <th>操作</th>
	          </tr>
          </thead>
          <tbody id="lt_tbody" data-itemcount="${page.itemCount}">
          	<jsp:include page="listTemplate.jsp"></jsp:include>
          </tbody>
          </table>
          <div id="page" style="position: relative;"></div>
		  <div id="console"></div>
     </div>
</div>
<script type="text/javascript" src="${basePath}/js/admin/channel/lt_channel.js"></script>
</body>
</html>