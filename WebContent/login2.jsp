<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>八分厂管理系统</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script>
function msg(){
	 var msg = document.getElementById("msg").value;
	 if(msg==""){
		// alert("msg为空");
	 }
	 else if(msg==null){
		 alert("msg为null");
	 }
	 else{
		 alert(msg);
	 }
	
}

</script>
</head>


<body onload="msg()" style="overflow:auto">



	<div class="login_box" >
		<div class="login_l_img">
			<img src="images/login-img.png" />
		</div>
		<div class="login" >
			<div class="login_logo">
				<a href="#"><img src="images/login_logo.png" /></a>
			</div>
			<div class="login_name">
				<p>八分厂管理系统</p>
			</div>
			<form    action="Login" method="post"  onsubmit="return jiance()"     >
			 	<table width="100%">
			 
				<tr><td><input  type="text"  name="username" id="username" value="" placeholder="用户名"/></td></tr>
				<tr><td><input  type="password"  name="password" id="password" value="" placeholder="密码"/></td></tr>
				<tr><td><input value="登入" style="width: 100%;" type="submit" /></td></tr>
				<tr ><td align="right" ><a href="Register.jsp">没有账号?点我注册</a></td></tr>
			<!-- 
				<tr ><td align="right" ><a href="ForgotPassword.jsp">忘记密码</a></td></tr>
				 -->	
				</table>
				<input  type="hidden"  name="msg" id="msg" value="${msg}" />		
			</form>
		</div>

	</div>
	<div style="text-align: center;">
	</div>
</body>


<script>
function jiance(){
	
	 var username = document.getElementById("username").value;
	 var password =document.getElementById("password").value;
	
	 if(username == '') {
        alert('请输入用户名');  
        return  false;
        
    }
	 else if(password=='') {
		 alert("请输入密码");
		 return false;
	 }
	
	
	
}


</script>
</html>


<!--
<body onload="mes()">
 <script>
function mes(){	
var msg ="${registermes}";
//alert(msg);
if(msg!= null && msg=="succeed"){
	
	alert("注册成功 ");
	window.location.href="examtest.jsp";		
    
}
if(msg!= null && msg=="registerd"){
	alert("这个人已经注册过了");
}
if(msg!= null && msg=="fail"){
	alert("注册失败");
}


}
</script> -->