<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<c:forEach items="${contents}" var="content">
   <tr id="tz-items-${content.id}" data-itemcount="${page.itemCount}">
   		<td>${content.id}</td>
   		<td class="tzui-key tmui-ellipsis w180">${content.title}</td>
<%--     	<td class="tzui-tips" tip="添加的时间是:${content.createTime}">${rz:formateDate(content.createTime,'yyyy年MM月dd日')}</td> --%>
   		<td>
   			<a href="javascript:void(0);" data-opid="${content.id}" onclick="lt_edit(this)" class="edit_w">编辑</a>
   			<a href="javascript:void(0);" data-opid="${content.id}" onclick="lt_delete(this)">删除</a>
   		</td>
   </tr>
</c:forEach>