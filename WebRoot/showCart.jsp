<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>购物车 </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" type="text/css" media="screen" />
<style type="text/css">
td {
	text-align: center;
	width: 100px;
	height: 75px;
}
</style>

<script type="text/javascript">

//增加商品数量 
function add(but,max){
	//获取父节点element对象,因为button提交的数据与button节点是兄弟关系
	var paren = but.parentNode;
	//alert(paren.getElementsById(pid).value);
	
	//获取input对象list集合,  遍历取出想要的数据(value属性) 
	var list = paren.getElementsByTagName("input");

	var text = list[1];
	var number = parseInt(text.value);
		max = parseInt(max);
	if(number < max){
		number++;
		text.value = number;
		updateCart(list[3].value,1);
		setPrice(list[3].value,1);//修改小计 
		setTotalPrice(list[3].value,1);//修改总计 
	}
};
	
/**
 *js 格式调整
 */

//减少商品数量 
function sub(but){
	//获取父节点element对象,因为button提交的数据与button节点是兄弟关系
	var paren = but.parentNode;
	//alert(paren.getElementsById(pid).value);

	//获取input对象list集合,  遍历取出想要的数据(value属性) 
	var list = paren.getElementsByTagName("input");
	//alert(list);

	var num = list[1];
	//alert(num);

	var number = parseInt(num.value);
	//alert(num.value);
	if(number>1){
		num.value = number - 1;
		setPrice(list[3].value,-1);//修改小计 
		setTotalPrice(list[3].value,-1);//修改总计 

		updateCart(list[3].value,-1);
	}
};



//修改价格小计,  与数量调整联动 
//num:正数表示加,  负数表示减 
//id :商品的id 商品的小计的id=商品的id+_s, 商品单价id=商品的id+"_p"
function setPrice(id, num){
	
	var price = document.getElementById(id+"_p");//单价 
	var sum = document.getElementById(id+"_s");//小计 
	
	var number = (parseFloat(price.innerHTML) * num) + parseFloat(sum.innerHTML);//修改后的数量 
	sum.innerHTML = number.toFixed(2);
	
}

//修改价格总计,  与数量调整联动 
//num:正数表示加,  负数表示减 
//id :商品的id 
function setTotalPrice(id,num){
	
	var price = document.getElementById(id+"_p");//单价 
	var sum = document.getElementById("sum");//小计或总计 
	
	var number = (parseFloat(price.innerHTML) * num) + parseFloat(sum.innerHTML);//修改后的数量 
	sum.innerHTML = number.toFixed(2);
	
}



//修改服务器端的,购物车商品数量 
//num是要减去或增加的数,  正数表示加,  负数表示减 
function updateCart(pid,num){
	//
	
	
	/*
	用AJAX提交给服务器  post
	*/
	var request =  getXMLHttp();
	request.open("post","${pageContext.request.contextPath}/addCartItem",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	request.send("id="+pid+"&num="+num);
	
	//响应 的回调函数 
	request.onreadystatechange = function(){
		var message
		if(request.readyState == 4 && request.status == 200){
			//解析xml
			//message = request.responseText;
			//与本地数量同步 
			
			}
			
		}
	
}



function showProduct(pid){
	location.href="${pageContext.request.contextPath}/showProduct?id="+pid;
};

//select
function getChecked() {
	var ids = new Array();
	var delCks = document.getElementsByName("select");
	var j = 0;
	for ( var i = 0; i < delCks.length; i++) {
		if (delCks[i].checked == true) {
			ids[j++] = delCks[i].value;		
		}
	}
	
	return ids;
};

//checkbox  全选 
function selectCk(main) {
	var flag = main.checked;

	var delCks = document.getElementsByName("select");

	for ( var i = 0; i < delCks.length; i++) {

		delCks[i].checked = flag;
	}
}

//结算
function submit(){
	//href="/order.jsp"
	//if the cart is not null, jump to order.jsp
	if(hasSelect()){
		location.href="${pageContext.request.contextPath}/order.jsp";
	}
}

//判断购物车是否为null 
function hasSelect() {
	
	var selects = document.getElementsByName("select");

	if(selects.length == 0){
		//alert("null")
		return false;
	}
	return true;
}

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

//监听商品数量输入框,  只允许输入数字 
//小键盘输入无效??? -- 待解决 
function numberText(e) {
	var keyCode;
	if (e && e.preventDefault) {
		//判断是firefox浏览器
		keyCode = e.which;
	} else {
		//ie浏览器
		keyCode = window.event.keyCode;
	}
	//alert(keyCode);
	//0-9之间的键码值是48-57
	if (!(keyCode >= 48 && keyCode <= 57 || keyCode == 8)) {
		//阻止事件的默认行为
		if (e && e.preventDefault) {
			// e对象存在，preventDefault方法存在 ---- 火狐浏览器
			e.preventDefault();
		} else {
			// 不支持e对象，或者没有preventDefault方法 ---- IE
			window.event.returnValue = false;
		}

	}
};
</script>

</head>
<body>
	<%@include file="header.jsp"%>
	<%!int sum = 0;%>
	<c:set var="sum" value="0" scope="request" />


	<table align="center" border='1' cellspacing='0'>

		<caption>
			<b>购物车1</b>
		</caption>

		<tr>
			<td>select</td>
			<td>商品图片</td>
			<td>商品名称</td>
			<td>单价</td>
			<td>数量</td>
			<td>小计</td>
			<td>remove</td>
		</tr>
		<c:forEach items="${cart}" var="cartItem" varStatus="st">
			<tr>
				<td><input type="checkbox" name="select" value="${cartItem.key.id}" /></td>
				<td>
					<img src="${pageContext.request.contextPath}${cartItem.key.imgurl_s}" width="100px"
					height="75px" alt="an image" class="image"
					onclick="showProduct(${cartItem.key.id})" />
				</td>
				<td>${cartItem.key.name}</td>
				<td id="${cartItem.key.id}_p">${cartItem.key.price}</td>
				
				<!-- 调整商品数量 -->
				<td><input type="button" value="+" onclick="add(this,${cartItem.key.pnum})" /> 
					<input type="text" readonly="readonly" size="1" value="${cartItem.value}" onkeydown="numberText(event)" onBlur="setBuyNum(this)" style="text-align:center"/>
					<input type="button" value="-" onclick="sub(this)" />
					<input type="hidden" value="${cartItem.key.id}"/>
					<p style="text-align:center">可购买数量:${cartItem.key.pnum}</p>
				</td>
				
				
				<!-- 小计 -->
				<td id="${cartItem.key.id}_s" >${cartItem.value * cartItem.key.price}</td>
				
				<!-- 删除 可以改成ajax处理 -->
				<td><form action="${pageContext.request.contextPath}/removeCartItem">
						<input type="hidden" value="${cartItem.key.id}" name="id">
						<input type="submit" value="remove">
					</form></td>
			</tr>
			<c:set var="sum" value="${sum+cartItem.value * cartItem.key.price}"
				scope="request" />
		</c:forEach>
		
		<tr>
			<td>全选<input type="checkbox" onclick="selectCk(this)"/></td>
			<td>合计</td>
			<td colspan="5" id="sum">${sum}</td>
		</tr>
		
		<tr>
			<td colspan="7"><font size="4" color="green">
				<a href="/home">商品页</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- 不做商品选择,  提交订单默认是购买购物车所以商品 -->
				<a  onclick="submit()">结算 (默认结算购物车所有商品)</a></font></td>
		</tr>
		
	</table>

	<%@include file="foot.jsp"%>
</body>
</html>