<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>商品详情 </title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" type="text/css" media="screen" />

<script type="text/javascript">

//页面初始化 -> 调整商品展示图片尺寸 
function onload(){
	//width="55px" height="55px" 
	var img = document.getElementById("pimg");

	//浏览器尺寸 
	var w=window.innerWidth
	|| document.documentElement.clientWidth
	|| document.body.clientWidth;

	var h=window.innerHeight
	|| document.documentElement.clientHeight
	|| document.body.clientHeight;

	//原图片尺寸 
	var imgw = img.style.width;
	var imgh = img.style.height;

	//alert(imgh/imgw);
	//新尺寸,不改变原有比例 
	w = w*0.85;
	h = (w/imgw)*imgh;
	//alert(h/w);

	//写入到style 
	img.style.width=w+"px";
	img.style.height=h+"px";


};


//获取xml对象 
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

//加入cart 
function submit(id){

	var num = Number(document.getElementById("num").innerHTML);

	var pnum = Number(document.getElementById("pnum").value);

	if(pnum > num){
		//购买数量 超出库存数量 
		alert("购买数量 超出库存数量 ");
		return;
	}


	/*
			用AJAX提交给服务器  post
	 */
	var request =  getXMLHttp();
	request.open("post","${pageContext.request.contextPath}/addCartItem",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");

	request.send("id="+id+"&num="+pnum);

	//响应 的回调函数 
	request.onreadystatechange = function(){

		if(request.readyState == 4 && request.status == 200){
			//解析xml
			var message = request.responseText;

			document.getElementById("message").innerHTML = message+"   <a href='${pageContext.request.contextPath}/showCart.jsp'>去购物车</a>";

			alert(message);
		}

	}
};

//直接购买, 跳转到购买页面 
function buy(id){
	var num = Number(document.getElementById("num").innerHTML);

	var pnum = Number(document.getElementById("pnum").value);

	if(pnum > num){
		//购买数量 超出库存数量 
		alert("购买数量 超出库存数量 ");
		return;
	}


	/*
			用AJAX提交给服务器  post
	 */
	var request =  getXMLHttp();
	request.open("post","/store/addCartItem",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");

	request.send("id="+id+"&num="+pnum);

	//需要回调函数,确认添加购物车成功,  再跳转到order.jsp
	//响应 的回调函数 
	request.onreadystatechange = function(){

		if(request.readyState == 4 && request.status == 200){
			location.href="/store/order.jsp";

		}
	}
}
	</script>

</head>

<body onload="onload()">

	<%@include file="header.jsp"%>

	<table align="center">
		<caption>查看商品</caption>
		
		<tr>
			<td>商品名称&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>${p.name}</td>
		</tr>
		
		<tr>
			<td>商品价格</td>
			<td>${p.price}</td>
		</tr>
		
		<tr>
			<td>商品类别</td>
			<td>${p.category}</td>
		</tr>
		
		<tr>
			<td>商品库存</td>
			<td id="num">${p.pnum}</td>
		</tr>

		<tr>
			<td>商品描述</td>
			<td>${p.description}</td>
		</tr>
		
		<tr>
			<td colspan="2">数量:<input type="text" value="1" name="num"
				id="pnum"></td>
		</tr>

		<tr>
			<td><a onclick="buy(${p.id})">立即购买</a></td>
			<td><img
				src="${pageContext.request.contextPath}/css/images/buy.bmp"
				id="addCart" onclick="submit(${p.id})" /></td>
		</tr>

		<tr>
			<td colspan="2"><span id="message">${message}</span></td>

		</tr>

		<tr>
			<td colspan="2"></td>

		
		</tr>
	</table>
	
	<h2 style="text-align: center;">商品图片</h2>
	
	<div align="center">
		<img src="${pageContext.request.contextPath}${p.imgurl}" id="pimg" />
	</div>


	<%@include file="foot.jsp"%>
</body>
</html>
