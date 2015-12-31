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
	.tzui-btnbox{border: 1px solid #e5e5e5;height:30px;width:318px;}
	.tzui-btnbox .tzui_inp{border: 0px;width: 254px;float:left;height:30px;text-indent: 0.5em; }
	.tzui-btnbox .tzui-btn{padding:4px 18px;background: #34A5A1;float:left;position: relative;top: -1px;color:#fff;cursor: pointer;}
</style>
</head>
<body>
  <div class="container">
	<%@include file="/commons/header.jsp" %>
     <div id="contentbox" class="ht_con" style="width: 320px;float:left;">
          <!-- <a href="javascript:void(0);" class="add_btn btn">增加新闻</a> -->
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
		  <div id="console"></div>
     </div>
</div>
<div style="height:32px;background:#5AC9C5">
	<input type="text" autocomplete="off" id="title"  style="height: 30px;width: 320px; " placeholder="请输入标题..."/>
	<a href="javascript:void(0);" id="tzui_upcontent" onclick="lt_updateSave()">编辑</a>
	<a href="javascript:void(0);" onclick="lt_localimg()">图片本地化</a>
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

<script type="text/javascript">
	//======================图片本地化==============================================
	function lt_localimg(){
		//第一步获取编辑器里面的图片
		var ctext = getEditorText("p_desc");
		if(isEmpty(ctext)){
			loading("请选择一个编辑的内容...",3);
			return false;
		}
		//获取编辑器的内容
		var content = getEditorHtml("p_desc");
		//讲编辑器的内容补齐，进行解析
		var $contentBox = $("<div>"+content+"</div>");
		//获取内容中图片
		var imgs = $contentBox.find("img");
		var length = imgs.length;
		if(length>0){//代表内容里面有网络图片
			//将图片获取放入数组中，传递给后台。
			var html = "";
			imgs.each(function(){
				var src = $(this).attr("src");
				if(isValidatorimg(src)){
					html +="<li><img src='"+src+"'><a href='javascript:void(0);' onclick='lt_downimg(this)'>下载</a><span></span></li>";
				}
			});
			if(isEmpty(html)){
				loading("非常抱歉，没有找到你需要本地化的图片....",4);
				return ;
			}
			
			var $imgbox = $("#imgbox").removeClass().addClass(tz_animateIn()).show();
			ltcenter_pos($imgbox);
			$imgbox.next().click(function(){
				$imgbox.fadeOut("slow");
				$("#tzimgbox").empty();
				$(this).hide();
			}).show();
			$("#tzimgbox").html(html);
		}else{
			loading("没有找到需要本地化的图片!!",4);
		}
	}
	
	function isValidatorimg(src){
		if(src.indexOf(basePath)==-1){//方便扩展
			return true;
		}
		return false;
	};
	//居中
	function ltcenter_pos(obj){
		var top = ($(window).height() - obj.height())/2;
		var left = ($(window).width() - obj.width())/2;
		obj.css({top:top,left:left});
	};
	
	//当前服务器的图片不要参与的下载。
	//上传是否可以带进度条呢?
	
	///json/模块名称/方法名 == ajax
	///admin/模块名称/方法名===非ajax
	//带有进度条，文件下载.
	//下载网络图片
	function lt_downimg(obj){
		var src = $(obj).prev().attr("src");
		//通过ajax进行异步传输，下载图片放入服务器
		$(obj).removeAttr("onclick").text("下载中...");
		$.ajax({
			type:"post",
			url:basePath+"/json/content/downimg",
			data:{"params.img":src},
			error:function(){
				loading("下载出现问题了...",4);
				$(obj).attr("onclick","lt_downimg(this)").text("下载");
			},
			success:function(data){
				//获取编辑器的内容
				var content = getEditorHtml("p_desc");
				//讲编辑器的内容补齐，进行解析
				var $contentBox = $("<div>"+content+"</div>");
				//替换编辑器里面的图片
				$contentBox.find("img[src='"+src+"']").attr("src",basePath+"/"+data.result);
				//替换编辑器里的内容
				setEditorContent("p_desc",$contentBox.html());
				$(obj).parent().remove();
				//自动更新
				$("#tzui_upcontent").trigger("click");
				tm_closeDiv();
			}
		});
		
		//window也是一个对象,你要模拟一个javascript的多线程
		window[src] = setInterval(function(){
			tm_getPercent(src);
		},300);
	};
	
	function tm_getPercent(src){
		$.ajax({
			type:"post",
			url:basePath+"/admin/content/imgPercent",
			data:{"params.img":src},
			success:function(data){
				if(data==100){
					clearInterval(window[src]);
				}
				$("#tzimgbox").find("img[src='"+src+"']").parent().find("span").html(data);
			}
		});
	}
	
	//当全部图片下载完毕以后，关闭弹出层
	function tm_closeDiv(){
		var length = $("#tzimgbox").find("li").length;
		if(length==0){
			 $("#imgbox").addClass(tz_animateOut()).next().trigger("click");
		}
	}
</script>

<script type="text/javascript">
	$(function(){
		//初始化分页
		lt_initPage("${page.itemCount}");
		//敲回车触发点击搜索
		$(document).keydown(function(e){
			if(e.keyCode == 13){
				$("#tzui_search").trigger("click");
			}
		});
		// 编辑器插件
		$("#contentbox").height($(window).height()-60);
		$(window).unbind("resize").resize(function(){
			$("#contentbox").height($(this).height()-60);
		});
		
		CKEDITOR.replace("p_desc",{height:$(window).height()-200});	
		
	});
	
	var pageNo = 0;
	var pageSize = 10;
	function lt_initPage(itemCount){
		$("#page").tzPage("${page.itemCount}", {
			num_edge_entries : 1, //边缘页数
			num_display_entries :2, //主体页数
			num_edge_entries:2,
			current_page:pageNo,
			showGo:false,
			showSelect : true,
			items_per_page : pageSize, //每页显示X项
			prev_text : "前一页",
			next_text : "后一页",
			callback : function(pageNo,psize){
				lt_loadingTemplate(pageNo,psize);
			}
		});
	}
	
	/**
		点击分页加载回调函数,
		如果一个ajax,访问一个同步url，最终就返回页面的源代码.静态化+ajax(节流)+模板
	*/
	var timer = null;
	function lt_loadingTemplate(pno,psize,callback){
		clearTimeout(timer);
		timer = setTimeout(function(){
			var keyword = $("#keyword").val();
			$.ajax({
				type:"post",
				data:{"page.firstResult":(pno*psize),"page.maxResults":psize,"params.keyword":keyword},
				url:basePath+"/admin/content/listTemplate",
				success:function(data){
					$("#tz_tbody").html(data);
					lt_keywordHighlighter(keyword);//关键字高亮
					if(callback){
						var itemcount = $("#tz_tbody").find("tr").eq(0).data("itemcount");
						callback(itemcount);
					}
				}
			});
		},200);
	}
	/*高亮*/
	function lt_keywordHighlighter(keyword){
		$("#tz_tbody").find(".tzui-key").each(function(){
			var text = $(this).text();
			var regex = new RegExp(keyword,"ig");
			var rtext = text.replace(regex,"<span style='color:red'>"+keyword+"</span>");
			 $(this).html(rtext);
		});
	};
	
	
	
	//=======================编辑================================================
	function lt_edit(obj){
		var opid = $(obj).data("opid");
		$.ajax({
			type:"post",
			url:basePath+"/admin/content/edit",
			data:{"id":opid},
			success:function(data){
				$("#title").data("opid",opid).val(data.title);
				setEditorContent("p_desc",data.content);
			}
		});
	}
	//=======================更新================================================
	function lt_updateSave(){
		var title = $("#title").val();
		var ctext = getEditorText("p_desc");
		var opid = $("#title").data("opid");
		var content = getEditorHtml("p_desc");
		if(isEmpty(title)){
			loading("请输入标题...",3);
			$("#title").focus();
			return false;
		}
		
		if(isEmpty(ctext)){
			loading("请输入内容...",3);
			editorFocus("p_desc");
			return false;
		}
		var params = {"content.title":title,"content.content":content};
		var method = "save";
		if(isNotEmpty(opid)){
			method = "update";
			params["content.id"] = opid;
		}
		$.ajax({
			type:"post",
			url:basePath+"/json/content/"+method,
			data:params,
			success:function(data){
				if(data.result=="success"){
					if(isEmpty(opid)){
						loading("保存成功！！！",4);
						$("#title").data("opid",data.content.id);
						$("#tz_tbody").prepend("<tr id='tz-items-"+data.content.id+"'>"+
						"	<td>55</td>"+
						"		<td class='tmui-ellipsis w180 title'>"+title+"</td>"+
						"		<td>"+
						"			<a href='javascript:void(0);' data-opid='"+data.content.id+"' onclick='lt_edit(this)' class='edit_w'>编辑</a>"+
						"			<a href='javascript:void(0);' data-opid='"+data.content.id+"' onclick='lt_delete(this)'>删除</a>"+
						"		</td>"+
						"	 </tr>");
						//setEditorContent("p_desc","");
					}else{
						loading("更新成功!!!",4);
						//$("#title").removeData("opid").val("");
						//setEditorContent("p_desc","");
						//$("#tz-items-"+opid).find(".title").text(title);
					}
				}
			}
		});
	}
	//=======================搜索=================================================
	function lt_search(obj){
		lt_loadingTemplate(pageNo,pageSize,function(itemcount){
			lt_initPage(itemcount);
		});
	}
	
	
	//========================删除==================================================
	function lt_delete(obj){
		$.tzConfirm({title:"删除提示",content:"您确定删除吗?",callback:function(ok){
			if(ok){
				var opid = $(obj).data("opid");
				$.tzAjax.request({model:"json/content",method:"delete",callback:function(data){
					if(data.result=="success"){
						loading("删除成功!",4);
						$("#tz-items-"+opid).remove();
					}else{
						loading("删除失败!",4);
					}
				}},{id:opid});
			}
		}});
	};
	
	//获取带有格式的内容--html
	function getEditorHtml(editorId) {
		var editor = CKEDITOR.instances[editorId];
	    return editor.getData();
	};
	
	//获取编辑器内容,纯文本 text
	function getEditorText(editor) {
		var stemTxt = CKEDITOR.instances[editor].document.getBody().getText();
		return stemTxt;
	};
	
	//得到焦点 
	function editorFocus(id) {
		CKEDITOR.instances[id].focus();
	}
	
	//插入编辑器内容--覆盖
	function setEditorContent(editorId,message) {
		var editor = CKEDITOR.instances[editorId];
		editor.setData(message);
	};
</script>
<div class="bottom">Copyright © 2013-2015 www.tanzhouedu.com All Rights Reserved 版权所有：湖南潭州教育咨询有限公司 备案号：备13016338号</div>
</body>
</html>