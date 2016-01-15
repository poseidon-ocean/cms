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
<script type="text/javascript" src="${basePath}/js/swfupload/lt_upload.js"></script>

<style>
	.tzui-btnbox{border: 1px solid #e5e5e5;height:30px;width:322px;}
	.tzui-btnbox .tzui_inp{border: 0px;width: 254px;float:left;height:30px;text-indent: 0.5em; }
	.tzui-btnbox .tzui-btn{padding:4px 20px;background: #34A5A1;float:left;position: relative;top: -1px;color:#fff;cursor: pointer;}
	.box{width:136px;height: 100%;background: #141414;color:#fff;}
	.itemsbox{cursor:pointer;background:green;transition:all 0.3s linear;}
	.itemsbox:hover{background:#111;transition:all 0.3s linear;}
	.items{height:40px;border-bottom:1px solid #fff;line-height:40px;color:#fff;padding-left:8px;}
	.cnt{padding:8px;display:none;background:#f9f9f9;color:#111}
	.selected{background: red;}
</style>
</head>
<body>
  <div class="container">
	<%@include file="/commons/header.jsp" %>
     <div id="contentbox" class="ht_con" style="width: 480px;float:left;background:#45B3AF">
     	<div class="box fl" id="boxitems">
	     		<rz:channelList var="ch">
					<div class="itemsbox">	
						<div class="items">${ch.name}</div>
						<div class="cnt">
							<c:forEach items="${ch.channels}" var="c">
								<div id="channel-items-${c.id}" class="channel-items"><a href="javascript:void(0);" data-opid="${c.id}">${c.name}</a></div>
							</c:forEach>
						</div>
					</div>
	     		</rz:channelList>
	     </div> 
          <!-- <a href="javascript:void(0);" class="add_btn btn">增加新闻</a> -->
          <div class="fl">
	          <div class="tzui-btnbox">
	          	<input type="text" class="tzui_inp" id="keyword" placeholder="请输入关键字...">
	          	<a class="tzui-btn" id="tzui_search" class="search" onclick="lt_search(this)">搜索</a>
	          </div>
	          <table class="news_list">
		          <thead>
			          <tr>
				          <th>序号</th>
				          <th>标题</th>
				          <!-- <th>时间</th> -->
				          <th>操作</th>
			          </tr>
		          </thead>
		          <tbody id="tz_tbody">
		          	<c:forEach items="${contents}" var="content">
					   <tr id="tz-items-${content.id}">
					   		<td>${content.id}</td>
					   		<td class="tzui-key tmui-ellipsis w180">${content.title}</td>
					    	<!-- <td class="tzui-tips" tip="添加的时间是:${content.createTime}">${rz:formateDate(content.createTime,'yyyy年MM月dd日')}</td> -->
					   		<td>
					   			<a href="javascript:void(0);" data-opid="${content.id}" onclick="lt_edit(this)" class="edit_w">编辑</a>
					   			<a href="javascript:void(0);" data-opid="${content.id}" onclick="lt_delete(this)">删除</a>
					   		</td>
					   </tr>
					</c:forEach>
		          </tbody>
	          </table>
         	 <div id="page" style="position: relative;"></div>
          </div>
		  <div id="console"></div>
     </div>
</div>
<div style="height:32px;background:#5AC9C5">
	<span>当前编辑的栏目是:<label id="channelname"></label></span>
	<input type="text" autocomplete="off" id="title"  style="height: 30px;width: 320px; " placeholder="请输入标题..."/>
	<a href="javascript:void(0);" id="tzui_upcontent" onclick="lt_updateSave()">编辑</a>
	<a href="javascript:void(0);" onclick="lt_localimg()">图片本地化</a>
	<span id="uploadBtn"></span>
</div>
<textarea id="p_desc" name="description" placeholder="请输入期刊描述..." ></textarea>
</div>
<style>
	#imgbox{background: #f5f5f5;width: 640px;height: 320px;position: absolute;top: 0px;left: 0px;display: none;z-index: 100}
	#imgbox li{float: left;padding: 9px;width: 140px;background: #999;margin:1px;}
	#imgbox li img{width: 140px;height: 120px;}
	#imgbox li a{color: #fff;}
</style>
<div id="imgbox"><ul id="tzimgbox"></ul></div>
<div class="tmui-overlay" style="display: none;"></div>

<script type="text/javascript" src="${basePath}/js/admin/content/lt_content.js"></script>
<div class="bottom">Copyright © 2013-2015  All Rights Reserved 版权所有：咨询有限公司 备案号：备13016338号</div>
</body>
</html>