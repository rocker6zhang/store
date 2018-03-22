<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>商品管理 </title>
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

var pid;

/**
 *删除 ,提交到服务器,  
 *flag 标记用于批量删除,  
 */
function removeProduct(id,flag){
	pid = id;
	var name = document.getElementById(pid+"_name").value;
	//是删除单个产品? 
	if(flag){
		//是 
		var s = confirm(" 确认删除产品 :"+name+"--并不删除数据库 ");
		if(!s){
			return;	
		}
	}
	/*
	用AJAX提交给服务器  post 方式
	 */
	var request =  getXMLHttp();
	request.open("post","${pageContext.request.contextPath}/removeProduct",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");

	request.send("id="+pid);

	//响应 的回调函数 
	request.onreadystatechange = function(){
		var message
		if(request.readyState == 4 && request.status == 200){
			//解析xml
			message = request.responseText;
			if(message=="删除成功"){
				remove_callback(id);//传递参数 

			}else{
				alert(message);
			}

		}

	}

}

function remove_callback(id){
	var tr = document.getElementById(id+"_tr");
	//var table = document.getElementById("table");
	//table.removeChild(tr);//删除失败 table 的子节点是 tbody 
	//tr.style.display="none";//失败 
	tr.parentNode.removeChild(tr);
}
/**
 *更新数据 , 提交给服务器 ,结果写回到页面 
 **/
function updateProduct(id){
	pid = id;

	var tr = document.getElementById(pid+"_tr");
	//tr里面是 input,  拿到所以 input 
	var list = tr.getElementsByTagName("input");

	//顺序一一对应 
	var name = list[1].value;
	var price = list[2].value;
	var pnum = list[3].value;
	var description = list[4].value;


	/*
	用AJAX提交给服务器  post
	 */
	var request =  getXMLHttp();
	request.open("post","${pageContext.request.contextPath}/updateProduct",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");

	request.send("id="+pid+"&name="+name+"&price="+price+"&pnum="+pnum+"&description="+description);

	//响应 的回调函数 
	request.onreadystatechange = function(){
		var message
		if(request.readyState == 4 && request.status == 200){
			//解析xml
			message = request.responseText;
			update_callback(id, message);
		}

	}

}

function update_callback(id, message){
	document.getElementById(id+"_msg").innerHTML=message;
}

/*
 * 跳转到产品查看页 
 */
function showProduct(pid){
	location.href="${pageContext.request.contextPath}/showProduct?id="+pid;
};


/**
 * 批量更新
 * 取得所以选中的行, 然后循环调用 updateProduct() 
 */
function updateSelect(){
	//alert("in");
	var checks = getChecked() ;
	var len = checks.length;
	var products = "";
	
	if(checks.length == 0){
		return;
	}
	
	//获取要更新的商品name 序列 , 给用户确认 
	for(i = 0; i < len; i++){
		products += document.getElementById(checks[i].id+"_name").value;
		products += "  ";
	}

	var s = confirm(" 确认修改"+len+"件产品 :"+products);
	if(!s){

		return;	
	}

	//遍历调用 updateProduct();
	for(i = 0; i < len; i++){
		updateProduct(checks[i].id,false);
	}

}

/**
 * 批量删除 
 * 取得所以选中的行, 然后循环调用 removeProduct() 
 */
function removeSelect(){
	var checks = getChecked() ;
	var len = checks.length;
	var products = "";
	
	if(checks.length == 0){
		return;
	}
	
	//获取要更新的商品name 序列 , 给用户确认 
	for(i = 0; i < len; i++){
		products += document.getElementById(checks[i].id+"_name").value;
		products += "  ";
	}

	var s = confirm(" 确认删除"+len+"件产品 :"+products+"--并不删除数据库 ");
	if(!s){

		return;	
	}
	
	//遍历调用 removeProduct();
	for(i = 0; i < len; i++){
		removeProduct(checks[i].id,false);
	}

};

/*
 * 获取所有选中的行 
 */
function getChecked() {
	var ids = new Array();
	var delCks = document.getElementsByName("select");
	var j = 0;
	for ( var i = 0; i < delCks.length; i++) {
		if (delCks[i].checked == true) {
			ids[j++] = delCks[i];		
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
	<%!int sum = 0;%>
	<c:set var="sum" value="0" scope="request" />

	<table id="table" align="center" border='1' cellspacing='0'>

		<caption>
			<b>商品管理</b>
		</caption>

		<tr>
			<td>全选<input type="checkbox" onclick="selectCk(this)" /></td>
			<td>商品编号</td>
			<td>商品图片</td>
			<td>商品名称</td>
			<td>单价</td>
			<td>数量</td>
			<td>描述</td>
			<td>操作</td>
			<td>操作结果</td>
		</tr>
		
		<c:forEach items="${products}" var="p" varStatus="st">
			<tr id="${p.id}_tr">

				<td><input type="checkbox" id="${p.id}" name="select" /></td>
				<td>${p.id}</td>
				<!-- 图片暂时不可修改 -->
				<td>
					<img src="${pageContext.request.contextPath}${p.imgurl_s}" width="100px" height="75px" alt="an image" class="image" onclick="showProduct(${p.id})" />
				</td>
				<td><input type="text" id="${p.id}_name" name="name" value="${p.name}" /></td>
				<td><input type="text" name="price" value="${p.price}" /></td>
				<td><input type="text" name="pnum" value="${p.pnum}" /></td>
				<td><input type="text" name="pnum" value="${p.description}" /></td>
				<!-- 删除 可以改成ajax处理 -->
				<td><font size="4" color="green">
					 <a onclick="removeProduct(${p.id},true)">remove</a><br />
					 <a onclick="updateProduct(${p.id})">update</a>
				</font></td>
				<td id="${p.id}_msg"></td>
			</tr>
			
		</c:forEach>

		<tr>
			<td colspan="2">批量删除</td>
			<td colspan="7"><a onclick="removeSelect()">删除选中</a></td>

		</tr>
		
		<tr>
			<td colspan="2">批量修改</td>
			<td colspan="7"><a onclick="updateSelect()">修改选中</a></td>
		</tr>
		
	</table>

	<%@include file="foot.jsp"%>
</body>
</html>