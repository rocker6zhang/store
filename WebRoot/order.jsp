<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>订单结算 </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" type="text/css" media="screen" />
<style type="text/css">
td {
	text-align: center;
	width: 100px;
	height: 75px;
}
.inputText{
    height:40px;
    width:300px;
    font-size:20px;
    }
</style>

<script type="text/javascript">


//jump to product page 
function showProduct(pid){
	location.href="${pageContext.request.contextPath}/showProduct?id="+pid;
};

// 提交订单 
// 给出收货地址即可,订单项默认是购物车全部内容 
function addOrder(a){
	var address = document.getElementById("address").value;
	a.href = a.href+"?address="+address;
}

</script>
</head>
<body>
		<c:if test="${empty cart}">
			<%response.sendRedirect("/home"); %>
		</c:if>


	<%@include file="header.jsp"%>
	<%!int sum = 0;%>
	<c:set var="sum" value="0" scope="request" />


	<table align="center" border='1' cellspacing='0'>

		<caption>
			
			<font size="5"><b>结算页面</b></font>
		</caption>
		<tr>
			
			<td>商品图片</td>
			<td>商品名称</td>
			<td>单价</td>
			<td>数量</td>
			<td>小计</td>
		</tr>
		<c:forEach items="${cart}" var="cartItem" varStatus="st">
			<tr>
			
				<td><img src="${pageContext.request.contextPath}/${cartItem.key.imgurl_s}" width="100px"
					height="75px" alt="an image" class="image"
					onclick="showProduct(${cartItem.key.id})" /></td>
				<td >${cartItem.key.name}</td>
				<td id="${cartItem.key.id}_p">${cartItem.key.price}</td>
				
				<!-- 商品数量 -->
				<td>${cartItem.value}</td>
				
				
				<!-- 小计 -->
				<td id="${cartItem.key.id}_s" >${cartItem.value * cartItem.key.price}</td>
				
				
			</tr>
			<c:set var="sum" value="${sum+cartItem.value * cartItem.key.price}"
				scope="request" />
		</c:forEach>
		<tr>
			
			<td>合计</td>
			<td colspan="4" id="sum">${sum}</td>
		</tr>
		<tr>
			<td colspan="5"><span><font size="5">收货地址:<input type="text" id="address"name="address" class="inputText"/></font></span>
			</td>
		</tr>
		<tr>
			<td colspan="5"><font size="4" color="green">
				<a href="${pageContext.request.contextPath}/addOrder" onclick="addOrder(this)">提交订单</a></font></td>
		</tr>
	</table>

	<%@include file="foot.jsp"%>
</body>
</html>