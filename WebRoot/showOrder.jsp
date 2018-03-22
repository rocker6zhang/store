<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>订单查询 </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" type="text/css" media="screen" />
<style type="text/css">
td {
	text-align: center;
	width: 100px;
	height: 75px;
}

.inputText {
	height: 40px;
	width: 300px;
	font-size: 20px;
}
</style>

<script type="text/javascript">
var order_id ;

//jump to product page 
function showProduct(pid){
	location.href="${pageContext.request.contextPath}/showProduct?id="+pid;
};

//确认收货 
function receipt(a){
	//location.href="${pageContext.request.contextPath}/receipt?id="+id;
	order_id = a.name;
	
	/*
	用AJAX提交给服务器  post
	*/
	var request =  getXMLHttp();
	request.open("post","${pageContext.request.contextPath}/receipt",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	request.send("id="+order_id);
	
	//响应 的回调函数 
	request.onreadystatechange = function(){
		var message
		if(request.readyState == 4 && request.status == 200){
			//解析xml
			message = request.responseText;
			//alert(message);
			//alert('ok'==message);
			if(message=="ok"){
				
				document.getElementById(order_id+"_a").style.display="none";
				document.getElementById(order_id+"_receipt").innerHTML="已收货";
			
			}
			}
			
		}
	
	
};

function returnOrder(){
	alert("不退也不换╰(*°▽°*)╯");
	};


	function getXMLHttp(){
		var xmlhttp;
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}else{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}

		return xmlhttp;
	};
</script>
</head>
<body>



	<%@include file="header.jsp"%>
	
	<table align="center" border='1' cellspacing='0'>

		<caption>

			<font size="5"><b>我的订单</b></font>
		</caption>
		<tr bgcolor="#F5F5F5">

			<td colspan="3">订单详情</td>
			<td>收货地址</td>
			<td>金额</td>
			<td>状态</td>
			<td>操作</td>

		</tr>
		<tr style="height: 10px"></tr>
		<c:forEach items="${orders}" var="order" varStatus="st">
			<!-- 单个订单开始 -->
			<tr bgcolor="#F5F5F5" >

				<td colspan="8" style="height: auto "><p style="text-align：left ">日期: ${order.createDate}
					&nbsp;&nbsp;&nbsp;&nbsp;订单号: ${order.id}</p></td>

			</tr>
			<!-- 订单明细开始  图片,描述,数量    跨行 收货地址, 金额, 状态 -->
			<c:forEach items="${order.ois}" var="item" varStatus="it">
				<tr>

					<td>
						<img src="${pageContext.request.contextPath}${item.product.imgurl_s}" width="100px"
						height="75px" alt="an image" class="image"
						onclick="showProduct(${item.product.id})" />
					</td>
						
					<td>${item.product.description}</td>
					
					<td >数量: x${item.num}</td>
					
					<c:if test="${it.count == 1}">
						<!-- 列跨行 -->
					<td rowspan="${order.size}">${order.address}</td>
					<td rowspan="${order.size}">${order.price}</td>
					<td rowspan="${order.size}">
					<c:if test="${order.pay == 0}" >
						<p>未支付</p>
					</c:if>
					<c:if test="${order.pay != 0}" >
						<p>已支付</p>
					</c:if>
					<c:if test="${order.receipt == 0}" >
						<p id="${order.id}_receipt">未收货</p>
					</c:if>
					<c:if test="${order.receipt != 0}" >
						<p>已收货</p>
					</c:if>
					
					</td>
					
					<td rowspan="${order.size}">
					<c:if test="${order.receipt == 0}" >
						<a id="${order.id}_a" name="${order.id}" onclick="receipt(this)">确认收货</a><br/>
					</c:if>
					
					<a onclick="returnOrder()">退换货</a>
					</td>

					</c:if>

				</tr>
			</c:forEach>
			<!-- 订单明细结束 -->>
			<!-- 单个订单结束 -->>
			
			<tr style="height: 10px"></tr>

		</c:forEach>
		
	</table>

	<%@include file="foot.jsp"%>
</body>
</html>