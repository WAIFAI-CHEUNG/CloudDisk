<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>抱歉，系统出错了</title>
</head>
<body>
	<br /><h3>统一异常处理，当前出错信息：${ex.message }</h3>
	<br />
	<h4><a href="<%=basePath%>user/pass.action">返回主页</a></h4>
</body>
</html>