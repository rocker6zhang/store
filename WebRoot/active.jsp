<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 设置自动跳转 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" type="text/css" media="screen" />
<title>用户激活</title>
</head>
<body>
<div style="text-align:center">

	<h1 style="text-align: center;">用户激活</h1>
	<h1 style="text-align: center;">${message}</h1>
</div>
	
	<form action="${pageContext.request.contextPath }/sendActiveEmail" method="post">
		<table align="center">
				<tr>
					<td>username</td>
					<td><input type="text" id="username" name="username"><span id="username_message"></span></td>
				</tr>
				<tr>
					<td>password</td>
					<td><input type="password" id="password"name="password"><span id="password_message"></span></td>
				</tr>
				<tr>
					<td><input type="submit" value="激活"></td>
					<td><input type="button" value="取消"></td>
				</tr>
			</table>
	</form>
	<br/>
	<p style="text-align:center">将发送一份激活邮件到你的注册邮箱</p>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>