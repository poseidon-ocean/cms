<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<c:forEach items="${channels}" var="channel">
   <tr id="tz-items-${channel.id}" data-itemcount="${page.itemCount}">
   		<td>${channel.id}</td>
   		<td class="tzui-key tzui-tips tmui-ellipsis">${channel.name}</td>
    	<td class="tzui-tips" tip="添加的时间是:${channel.createTime}">${rz:formateDate(channel.createTime,'yyyy年MM月dd日')}</td>
    	<td><span data-opid="${channel.id}" data-status="${channel.status}" class="tmui-status ${channel.status==1?'green':'red'}">${channel.status==1?"发布":"未发布"}</span></td>
   		<td>
   			<a href="${basePath}/admin/channel/edit?id=${channel.id}" data-opid="${channel.id}" class="edit_w">编辑</a>
   			<a href="javascript:void(0);" data-opid="${channel.id}" onclick="lt_delete(this)">删除</a>
   		</td>
   </tr>
</c:forEach>