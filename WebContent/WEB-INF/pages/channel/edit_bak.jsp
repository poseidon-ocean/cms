<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>CMS系统-栏目管理列表</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css">
<%@include file="/commons/public.jsp" %>
</head>
<body>
	<%@include file="/commons/header.jsp" %>
<form id="channel_form">
	<input type="hidden" name="channel.id" id="opid" value="${channel.id}"/>
	<div class="add_nr">
               <div class="row_sty clearfix">
                   <p>栏目标题：</p>
                   <input type="text" name="channel.name" value="${empty channel.name?'我叫pipi':channel.name}"/>
               </div>
               <div class="row_sty clearfix">
                   <p>&nbsp;</p>
                   <a href="javascript:void(0);" class="submit btn" onclick="lt_save(this)">立即提交</a>
               </div>               
          </div>
</form>

<script type="text/javascript">
	$(function(){
		//loading("我爱你们!!!!");
	});
	
	function lt_save(obj){
		var opid = $("#opid").val();
		var method = "save";
		if(isNotEmpty(opid)){
			method = "update";
		}
		var params = $("#channel_form").serialize();
		$(obj).removeAttr("onclick").val("保存中...");
		$.ajax({
			type:"post",
			data:params,
			beforeSend:function(){loading("请稍后，执行中...");},
			url:basePath+"/json/channel/"+method,
			success:function(data){
				$(obj).attr("onclick","lt_save(this)").val("保存");
				loading("数据添加成功",4);
				window.location.href = basePath+"/admin/channel/list";
			}
		});	
	}
</script>
</body>
</html>