<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>register </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist.js"></script>
<script type="text/javascript">


	function getImg() {
		//doc
		//var img = document.getElementById("checkCode");
		//img.src = "${pageContext.request.contextPath}/checkImg?time="+ new Date().getTime();
		
		var img = document.getElementById("checkCode");
		img.src = "${pageContext.request.contextPath}/checkSimpleCode" + "?time=" + (new Date()).valueOf();
		
		//console.log("getImg")
		//console.log(img)
		//test();
		
		//checkForm();
		

	}
	

	//username 是否已注册 
	function checkRename(text){
		var name = text.value;
		if(name == ""){
			document.getElementById("username_message").innerHTML="" ;	
			return;
		}

		/*
			用AJAX提交给服务器  post
		 */
		var request =  getXMLHttp();
		request.open("post","${pageContext.request.contextPath}/checkRename",true);
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");

		request.send("name="+name);

		//响应 的回调函数 
		request.onreadystatechange = function(){
			var message
			if(request.readyState == 4 && request.status == 200){
				//解析xml
				message = request.responseText;
				//alert(message);
				//alert('ok'==message);
				if(message == "ok"){
					document.getElementById("username_message").innerHTML="name 可以使用" ;				
				}else{
					document.getElementById("username_message").innerHTML="name 不可以使用" ;	
				}
			}

		}

	}
	</script>
</head>
<body>

	<!-- 静态包含,将文件内容包含在jsp中 解释成一个servlet-->
	<%@include file="header.jsp"%>

	<div id="all">
<%-- action='${PageContext.request.getContextPath}/regist'总是编译错,  所以这里写死 --%>
		<form action="/store/regist"
			method="post" onsubmit="return checkForm();">
			<table align="center">
				
				<tr>
					<td>username</td>
					<td><input type="text" id="username" name="username" onchange="checkRename(this)"><span id="username_message"></span></td>
				</tr>
				
				<tr>
					<td>password</td>
					<td><input type="password" id="password"name="password"><span id="password_message"></span></td>
				</tr>
				
				<tr>
					<td>password</td>
					<td><input type="password" id="repassword" name="repassword"><span id="repassword_message"></span></td>
				</tr>
				
				<tr>
					<td>邮箱</td>
					<td><input type="text" id="email" name="email"><span id="email_message"></span></td>
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
					<td><input type="submit" value="注册"></td>
					<td><input type="button" value="取消"></td>
				</tr>
				
				<tr>
					<td colspan="2"><p id="wrongMessage" style="font-size:20px">${wrongMessage}</p></td>

				</tr>


			</table>
		</form>
	</div>


	<%@include file="foot.jsp"%>
</body>
</html>