<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" type="text/css" media="screen" />
<!-- 坑 
	元素id一定不能重复 
	js的正则表单式不是字符串
-->
<script type="text/javascript">


	function getImg() {
		var img = document.getElementById("checkCode");
		img.src = "${pageContext.request.contextPath}/checkSimpleCode" + "?time=" + (new Date()).valueOf();

	}
	function checkForm(){
		
		username = checkText("username",1);
		password = checkText("password",6);
		check = checkText("check",4);
		
	
		//console.log(username);
		//alert(username);
		//alert(username&password&email&check);
		//注意 & 与 && 不同
		
		return username && password &&  check ;
	}
	
	function checkText(id,num){
		var text = document.getElementById(id).value;
		var reg = new RegExp(".{"+num+"}");
		//var reg = /^\s*$/; //代表0个或多个空字符。	
		
		if(reg.test(text)){
			document.getElementById(id+"_message").innerHTML="" ;
			return true;
			
		}else{
			document.getElementById(id+"_message").innerHTML="<font color='red'>"+id+" 长度必须大于"+num+"</font>" ;
			return false;
		}
	}
	
</script>

</head>
<body>

	<!-- 静态包含,将文件内容包含在jsp中 解释成一个servlet-->
	<%@include file="header.jsp"%>

	<div id="all">

		<form action="${PageContext.request.getContextPath}/login" method="post" onsubmit="return checkForm();">
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
					<td>checkCode</td>
					<td><input type="text" id="check" name="checkCode"><span id="check_message"></span></td>
					<td>
					<img alt="checkCode" src="${PageContext.request.getContextPath}/checkSimpleCode"
						onclick="getImg()" id="checkCode" />
					</td>
					<td><input type="button" onclick="getImg()" value="换一张" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="登录"></td>
					<td><input type="button" value="取消"></td>
				</tr>
				
				<tr>
					<td colspan="2"><p id="wrongMessage">${LMessage}</p></td>

				</tr>


			</table>
		</form>
	</div>


	<%@include file="foot.jsp"%>
</body>
</html>