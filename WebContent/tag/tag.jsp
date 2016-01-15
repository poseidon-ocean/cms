<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>标签的使用</title>
	<%@include file="/commons/public.jsp" %>
</head>
<body>
		<ul>
	<rz:channelList var="clist" pid="">
			<li>${clist.name }</li>
	</rz:channelList>
		</ul>
<br/>

============================================================================
	<rz:contentBean cid="6" var="content"/>
	${content.content }
</body>
</html>