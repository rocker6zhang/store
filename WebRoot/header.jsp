<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- head begin -->
<div id="head">
<!-- 左 logo -->
	<span style="position: relative; top: 50%; transform: translateY(-50%); float:left">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;logo -- 本站仅供测试
	</span>
<!-- 中 搜索 -->
	<span style="position: relative; top: 25%; transform: translateY(-50%);float:none ">
			<a href="${pageContext.request.contextPath}/home">首页</a>&nbsp;&nbsp;
			<input type="text" placeholder="请输入关键字:*key*" id="search_input" />&nbsp;&nbsp;
			<button  style="color: #fff; border-color: #0084ff;background-color: #0084ff;"onclick="var key = document.getElementById('search_input').value;if (key == '') {return;} location.href = '${pageContext.request.contextPath}/searchProduct?key='+key;">搜索</button>
	</span>
<!-- 右 用户 -->
	<span style="position: relative; top: 50%; transform: translateY(-50%);float:right">
		在线人数:(${applicationScope.online-num})&nbsp;&nbsp;当前用户:
				
		${user.username}&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/login.jsp">登录</a>&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/logout">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</span>

</div>
<!--  
<script type="text/javascript">
	/*
	 根据浏览器宽度设置head 宽度
	 */
	var w = window.innerWidth || document.documentElement.clientWidth
			|| document.body.clientWidth;

	var head = document.getElementById("head");
	var t = ((w * 0.8) + "px");
	head.style.width=t;
	
	
</script>

-->
<!-- 隔断 -->
<div id="first" class='l'></div>
<!-- head 结束 -->
