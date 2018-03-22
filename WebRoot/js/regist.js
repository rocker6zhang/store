/**
 * 轮播图,  
 * 通过setImg 来跟换图片,  Imgindex 表示图片序号(数组下标),  timeCount 计时
 *
-- 坑 
	元素id一定不能重复 
	js的正则表单式不是字符串
*/



//校验数据 
function checkForm(){

	username = checkText("username",1);
	password = checkText("password",6);
	check = checkText("check",4);

	repassword = checkRepassword("password","repassword");
	email = checkMail("email");

	//console.log(username);
	//alert(username);
	//alert(username&password&email&check);
	//注意 & 与 && 不同

	//测试完成后再开启
//	alert(username && password && email && check && repassword);

	return username && password && email && check && repassword;
	//return true;
}

// num : 允许最少几个字符 
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

function checkMail(id){
	var text = document.getElementById(id).value;
	//var reg = new RegExp("^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$");//无法识别qq邮箱,  其他也不行 
	var reg = new RegExp(".+@.+");

	if(reg.test(text)){
		document.getElementById(id+"_message").innerHTML="" ;
		return true;

	}else{
		document.getElementById(id+"_message").innerHTML="<font color='red'>"+"email 格式不正确 "+"</font>" ;
		return false;
	}
}

// 校验两次密码输入是否一致 
function checkRepassword(password,repassword){
	if(document.getElementById(password).value == document.getElementById(repassword).value){
		document.getElementById(repassword+"_message").innerHTML="" ;
		return true;

	}else{
		document.getElementById(repassword+"_message").innerHTML="<font color='red'>"+"密码输入不一致  "+"</font>" ;
		return false;
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
}
