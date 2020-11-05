<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<font size=5>
<h1>检测您未登录，非法访问！</h1>
<h2><a href="<%=basePath%>login.jsp">返回登录页面</a></h2>
</font>
</body>
</html>