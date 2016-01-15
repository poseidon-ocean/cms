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
	<div style="width:600px;margin:30px 100px;">
		<a href="javascript:;" onclick="static()">静态化</a>
	</div>	
	

	<script type="text/javascript">
		function static(){
			$.ajax({
				type:"post",
				url:"static.jsp",
				success:function(data){
					save_html(data);
				},
				error:function(){
					
				}
			});
		}
		
		function save_html(data){
			$.ajax({
				type:"post",
				url:"sop.jsp",
				success:function(data){
					alert("静态化成功");
				},
				error:function(){
					
				}
		}
	</script>
</body>
</html>