<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>静态化</title>
	<%@include file="/commons/public.jsp" %>
</head>
<body>
		
<br/>

============================================================================
	<rz:contentBean cid="6" var="content"/>
	${content.content }
</body>
</html>