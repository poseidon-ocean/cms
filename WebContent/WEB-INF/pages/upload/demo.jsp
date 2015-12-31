<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
测试 demo

	<form action="http://localhost:8081/ltcms/upload/upload" method="post" enctype="multipart/form-data">
  		<input type="file" name="file">
  		<input type="submit" value="提交"/>
  	</form>
</body>
</html>