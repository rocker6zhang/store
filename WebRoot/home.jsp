<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
    Created by Artisteer v2.6.0.35446
    Base template (without user's data) checked by http://validator.w3.org : "This page is valid XHTML 1.0 Transitional"
    -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>购物商城</title>
 <link rel="icon" href="${pageContext.request.contextPath}/css/images/title1.ico" type="img/x-ico" />



<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<!--[if IE 6]><link rel="stylesheet" href="style.ie6.css" type="text/css" media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="style.ie7.css" type="text/css" media="screen" /><![endif]-->

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/script.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/photo.js"></script>
<script type="text/javascript">


//给图片数组赋值 , 需要使用pageContext.request.contextPath,  所以放在这里 数组及定义的方法在 photo.js里 
	imgs[0]="${pageContext.request.contextPath}/css/images/photo1.jpg";
	imgs[1]="${pageContext.request.contextPath}/css/images/photo5.jpg";
	imgs[2]="${pageContext.request.contextPath}/css/images/photo.jpg";
	imgs[3]="${pageContext.request.contextPath}/css/images/photo4.jpg";
	imgs[4]="${pageContext.request.contextPath}/css/images/photo9.jpg";


	var focus_width = 632;
	var focus_height = 320;
	var picPath;
	var linkUrl = "";
	var swfPath = "adImage.swf";
	var sp = "|";
	var banners = new Array("images/screen1.jpg", "images/screen2.jpg",
			"images/screen3.jpg", "images/screen4.jpg", "images/screen5.jpg",
			"images/screen6.jpg");
	var links = new Array("#", "#", "#", "#", "#", "#");

	for (var i = 0; i < banners.length; i++) {
		if (i == banners.length - 1) {
			sp = "";
		}
		picPath += banners[i] + sp;
		var index = picPath.indexOf("undefined");
		if (index != -1) {
			picPath = picPath.substr(index + 9, picPath.length);
		}
		linkUrl += links[i] + sp;
	};
	function showProduct(id){
		location.href="${pageContext.request.contextPath}/showProduct?id="+id;
	};
	
	function search1(){
	var key = document.getElementById("search_input").value;
	if(key =="请输入关键字:*key*" || key==""){
		return;
	}
	location.href="${pageContext.request.contextPath}/searchProduct?key="+key;
	};
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

</script>

</head>
<body onload="countTime()">

	<div id="art-main">

		<div id="head" style="text-align: center; margin: 0 auto; background-color: #fff; width: 100%; height: 52px;">
			
			<span style="position: relative; top: 50%; transform: translateY(-50%); float:left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;logo -- 本站仅供测试
			</span> 
			<span style="position: relative; top: 25%; transform: translateY(-50%); float:neno">
					<input id="search_input" type="text" placeholder="请输入关键字:*key*" />&nbsp;&nbsp;
					<button  class="art-button" style=" border-color: #0084ff;background-color: #0084ff;" onclick="search1()">搜索</button>
			</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			
			<span style="position: relative; top: 50%; transform: translateY(-50%);float:right">在线人数:(${online-num}) &nbsp;&nbsp;当前用户:
				${user.username}&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/login.jsp">登录</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/logout">注销</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</span>

		</div>
		<div class="art-sheet">



			<div class="art-sheet-tl"></div>
			<div class="art-sheet-tr"></div>
			<div class="art-sheet-bl"></div>
			<div class="art-sheet-br"></div>
			<div class="art-sheet-tc"></div>
			<div class="art-sheet-bc"></div>
			<div class="art-sheet-cl"></div>
			<div class="art-sheet-cr"></div>
			<div class="art-sheet-cc"></div>
			<div class="art-sheet-body">
				<div class="art-header">
					<div class="art-header-png"></div>
					<div class="art-header-jpeg"></div>
					<div class="art-logo">
						<h1 id="name-text" class="art-logo-name">
							<a href="#">购物商城</a>
						</h1>
						<div id="slogan-text" class="art-logo-text">快乐的购物天堂...</div>
					</div>
				</div>
				<div class="art-nav">
					<div class="l"></div>
					<div class="r"></div>
					<ul class="art-menu">
						<li><a href="#" class="active"><span class="l"></span><span
								class="r"></span><span class="t">主页</span></a></li>
						<li><a href="#"><span class="l"></span><span class="r"></span><span
								class="t">商品分类</span></a>
							<ul>
								<li><a href="#">图书、电子书刊、音像</a></li>
								<li><a href="#">电子数码</a>
									<ul>
										<li><a href="#">家用电器</a></li>
										<li><a href="#">手机数码</a></li>
										<li><a href="#">电脑、办公</a></li>
									</ul></li>
								<li><a href="#">家居、家具、家装、厨具</a></li>
							</ul></li>
						<li><a href="#"><span class="l"></span><span class="r"></span><span
								class="t">关于我们</span></a></li>
					</ul>
				</div>
				<div class="art-content-layout">
					<div class="art-content-layout-row">
						<div class="art-layout-cell art-content">
							<div class="art-post">
								<div class="art-post-tl"></div>
								<div class="art-post-tr"></div>
								<div class="art-post-bl"></div>
								<div class="art-post-br"></div>
								<div class="art-post-tc"></div>
								<div class="art-post-bc"></div>
								<div class="art-post-cl"></div>
								<div class="art-post-cr"></div>
								<div class="art-post-cc"></div>
								<div class="art-post-body">
									<div class="art-post-inner art-article">
										<div class="art-postmetadataheader">
											<h2 class="art-postheader">商品促销信息</h2>
										</div>
										<div class="art-postcontent">
											<!-- article-content 
											<script type="text/javascript" src="mutilpleFlash.js"></script>
											<!-- /article-content -->

											<div style="width: 634px; height: 340;">
												<img id="img" alt="img"
													src="${pageContext.request.contextPath}/css/images/photo1.jpg"
													width="634px" height="340px" style="margin: 0px;" />
											</div>


										</div>
										<div class="cleared"></div>
									</div>

									<div class="cleared"></div>
								</div>
							</div>

							<div class="art-post">
								<div class="art-post-tl"></div>
								<div class="art-post-tr"></div>
								<div class="art-post-bl"></div>
								<div class="art-post-br"></div>
								<div class="art-post-tc"></div>
								<div class="art-post-bc"></div>
								<div class="art-post-cl"></div>
								<div class="art-post-cr"></div>
								<div class="art-post-cc"></div>
								<div class="art-post-body">
									<div class="art-post-inner art-article">
										<div class="art-postmetadataheader">
											<h2 class="art-postheader">热卖商品销售中</h2>
										</div>
										<div class="art-postcontent">
											<!-- article-content -->
											<p>
												<span class="art-button-wrapper"> <span class="l">
												</span> <span class="r"> </span> <a class="art-button"
													href="javascript:void(0)">更多商品...</a>
												</span>
											</p>
											<div class="cleared"></div>
											<div class="art-content-layout overview-table">
												<!-- 行开始-->
												${searchMSG}
												<div class="art-content-layout-row">
													<c:forEach items="${products}" var="p" varStatus="vs">

														<div class="art-layout-cell">
															<div class="overview-table-inner">
																<h4>${p.name}</h4>
																<img
																	src="${pageContext.request.contextPath}${p.imgurl_s}"
																	width="55px" height="55px" alt="an image" class="image"
																	onclick="showProduct(${p.id})" />
																<p>价格: ￥${p.price}</p>
																<p>
																	<a href="/showProduct?id=${p.id}">速速抢购</a>
																</p>
															</div>
														</div>
														<!-- end cell -->

														<c:if test="${vs.count%5==0}">
												</div>
												<!-- 行结束 -->
												<!-- 行开始 -->
												<!-- 判断当前已经有5个商品了，这 一行结束，在重新开启一行 -->
												<div class="art-content-layout-row">
													</c:if>

													</c:forEach>



												</div>
												<!-- end row -->



											</div>
											<!-- end table -->

											<!-- /article-content -->
										</div>
										<div class="cleared"></div>
									</div>

									<div class="cleared"></div>
								</div>
							</div>
						</div>
						<div class="art-layout-cell art-sidebar1">
							<div class="art-vmenublock">
								<div class="art-vmenublock-body">
									<div class="art-vmenublockheader">
										<div class="l"></div>
										<div class="r"></div>
										<div class="t">导航菜单</div>
									</div>
									<div class="art-vmenublockcontent">
										<div class="art-vmenublockcontent-tl"></div>
										<div class="art-vmenublockcontent-tr"></div>
										<div class="art-vmenublockcontent-bl"></div>
										<div class="art-vmenublockcontent-br"></div>
										<div class="art-vmenublockcontent-tc"></div>
										<div class="art-vmenublockcontent-bc"></div>
										<div class="art-vmenublockcontent-cl"></div>
										<div class="art-vmenublockcontent-cr"></div>
										<div class="art-vmenublockcontent-cc"></div>
										<div class="art-vmenublockcontent-body">
											<!-- block-content -->
											<ul class="art-vmenu">
												<li><a href="${pageContext.request.contextPath}/home"><span class="l"></span><span
														class="r"></span><span class="t">主页</span></a></li>
												<li><a
													href="${pageContext.request.contextPath}/addProduct.jsp"><span
														class="l"></span><span class="r"></span><span class="t">添加商品</span></a>
												</li>
												<li><a
													href="${pageContext.request.contextPath}/productList"><span
														class="l"></span><span class="r"></span><span class="t">商品管理</span></a></li>
												<li><a
													href="${pageContext.request.contextPath}/showCart.jsp"><span
														class="l"></span><span class="r"></span><span class="t">查看购物车</span></a></li>
												<li><a
													href="${pageContext.request.contextPath}/getOrder"><span
														class="l"></span><span class="r"></span><span class="t">查看订单</span></a></li>
												<li><a href="#"><span class="l"></span><span
														class="r"></span><span class="t">关于我们</span></a></li>
												<li><a href="#"><span class="l"></span><span
														class="r"></span><span class="t">联系方式</span></a></li>
											</ul>
											<!-- /block-content -->

											<div class="cleared"></div>
										</div>
									</div>
									<div class="cleared"></div>
								</div>
							</div>
							<div class="art-block">
								<div class="art-block-body">
									<div class="art-blockheader">
										<div class="l"></div>
										<div class="r"></div>
										<div class="t">用户登陆</div>
									</div>
									<div class="art-blockcontent">
										<div class="art-blockcontent-tl"></div>
										<div class="art-blockcontent-tr"></div>
										<div class="art-blockcontent-bl"></div>
										<div class="art-blockcontent-br"></div>
										<div class="art-blockcontent-tc"></div>
										<div class="art-blockcontent-bc"></div>
										<div class="art-blockcontent-cl"></div>
										<div class="art-blockcontent-cr"></div>
										<div class="art-blockcontent-cc"></div>
										<div class="art-blockcontent-body">
											<!-- block-content -->
											<div>
											<%-- action='${PageContext.request.getContextPath}/login'总是编译错,  所以这里写死 --%>
												<form method="post" id="loginForm" action="${pageContext.request.contextPath}/login">
													<table>
														<tr>
															<td colspan="2">${LMessage}</td>
														</tr>
														<tr>
															<td>用户名</td>
															<td><input type="text" value="" name="username"
																id="s" /><br /></td>
														</tr>
														<tr>
															<td>密 码</td>
															<td><input type="text" value="" name="password" id="s" />
																</td>
														</tr>
														
														<tr>
															<td>
																<!-- <input type="button" onclick="getImg()" value="换一张" />-->
																 <div onclick="getImg()" style="width:40px; cursor:pointer">换一张</div>
															</td>
															<td>
															<img alt="checkCode" src="${PageContext.request.getContextPath}/checkSimpleCode"
																onclick="getImg()" id="checkCode" width="120px" height="25px"/>
															</td>
														</tr>
														
														<tr>
															<td>验证码</td>
															<td><input type="text" id="check" name="checkCode"></td>
														</tr>
														
														
													</table>
													<table>
														<tr>
															<td colspan="2"><input type="checkbox"
																name="remember" value="on" />记住用户 <input
																type="checkbox" name="autologin" value="on" />自动登陆</td>
														</tr>
														<tr>
															<td colspan="1"><span class="art-button-wrapper">
																	<span class="l"> </span> <span class="r"> </span> <input
																	class="art-button" type="submit" name="loginbtn"
																	value="登陆" />
															</span></td>
															<td colspan="1"><span class="art-button-wrapper">
																	<span class="l"> </span> <span class="r"> </span> <input
																	class="art-button" type="button"
																	onclick="window.location.href='regist.jsp'"
																	name="regist" value="注册" />
															</span></td>
													</table>
												</form>
											</div>
											<!-- /block-content -->

											<div class="cleared"></div>
										</div>
									</div>
									<div class="cleared"></div>
								</div>
							</div>
							<div class="art-block">
								<div class="art-block-body">
									<div class="art-blockheader">
										<div class="l"></div>
										<div class="r"></div>
										<div class="t">查询商品</div>
									</div>
									<div class="art-blockcontent">
										<div class="art-blockcontent-tl"></div>
										<div class="art-blockcontent-tr"></div>
										<div class="art-blockcontent-bl"></div>
										<div class="art-blockcontent-br"></div>
										<div class="art-blockcontent-tc"></div>
										<div class="art-blockcontent-bc"></div>
										<div class="art-blockcontent-cl"></div>
										<div class="art-blockcontent-cr"></div>
										<div class="art-blockcontent-cc"></div>
										<div class="art-blockcontent-body">
											<!-- block-content -->
											<div>
												
													<input type="text" value="" name="search" id="s"
														style="width: 95%;" placeholder="请输入关键字:*key*"/> <span class="art-button-wrapper">
														<span class="l"> </span> <span class="r"> </span> 
														<input class="art-button" type="button" name="search" value="查询"  onclick="search1()"/>
													</span>

									
											</div>
											<!-- /block-content -->

											<div class="cleared"></div>
										</div>
									</div>
									<div class="cleared"></div>
								</div>
							</div>

							<div class="art-block">
								<div class="art-block-body">
									<div class="art-blockheader">
										<div class="l"></div>
										<div class="r"></div>
										<div class="t">联系信息</div>
									</div>
									<div class="art-blockcontent">
										<div class="art-blockcontent-tl"></div>
										<div class="art-blockcontent-tr"></div>
										<div class="art-blockcontent-bl"></div>
										<div class="art-blockcontent-br"></div>
										<div class="art-blockcontent-tc"></div>
										<div class="art-blockcontent-bc"></div>
										<div class="art-blockcontent-cl"></div>
										<div class="art-blockcontent-cr"></div>
										<div class="art-blockcontent-cc"></div>
										<div class="art-blockcontent-body">
											<!-- block-content -->
											<div>
												<img src="${pageContext.request.contextPath}/css/images/contact.jpg" alt="an image"
													style="margin: 0 auto; display: block; width: 95%" /> <br />
												<b>公司信息</b><br /> *****<br /> 电子邮箱: <a
													href="@">****</a><br />
												<br /> 电话: (010)1234567 <br /> 传真: (010)1234567
											</div>
											<!-- /block-content -->

											<div class="cleared"></div>
										</div>
									</div>
									<div class="cleared"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="cleared"></div>
				<div class="art-footer">
					<div class="art-footer-t"></div>
					<div class="art-footer-l"></div>
					<div class="art-footer-b"></div>
					<div class="art-footer-r"></div>
					<div class="art-footer-body">
						<!--
						<div class="art-footer-text">
							<p>
								<a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">人才招聘</a>
								| <a href="#">商家入驻</a><br /> 版权 &#169;
							</p>
						</div>
						-->
						<div class="cleared"></div>
					</div>
				</div>
				<div class="cleared"></div>
			</div>
		</div>
		<div class="cleared"></div>
	</div>
<%@include file="foot.jsp"%>
</body>
</html>