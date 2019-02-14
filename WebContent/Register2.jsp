<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="externalLinks.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册界面</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="login_box">
		<div class="login_l_img">
			<img src="images/login-img.png" />
		</div>
		<div class="login" style="height: 650px;">
			<div class="login_logo">
				<a href="#"><img src="images/login_logo.png" /></a>
			</div>
			<div class="login_name">
				<p>八分厂管理人工注册系统</p>
			</div>
			<form action="EmployeeRegister" method="post" class="form-horizontal"    >
				<div class="form-group">
					<!--  	<label for="inputEmail3" class="col-sm-2 control-label">Email</label>-->
					<div class="col-sm-10">
						<input type="text" class="form-control" id="employeeName"
							name="employeeName" placeholder="名字">
					</div>
				</div>
				<div class="form-group">
					<!--	<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
					<div class="col-sm-10">
						<input type="text" class="form-control" id="employeeNumber"
							name="employeeNumber" placeholder="工号">
					</div>
				</div>
				<div class="form-group">
					<!--	<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
					<div class="col-sm-10">
						<select class="form-control" id="grouptype" name="grouptype">
							<option value="1">设计一组</option>
							<option value="2">设计二组</option>
							<option value="3">检测组</option>
							<option value="4">钳工一组</option>
							<option value="5">钳工二组</option>
							<option value="6">慢走丝组</option>
							<option value="7">精雕组</option>
							<option value="8">加工中心</option>
							<option value="9">电脉冲组</option>
							<option value="10">粗加工组</option>
							<option value="11">工艺组</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label> <input type="checkbox" name="leaderPermission">组长
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-10">
						<input type="text" class="form-control" id="telphoneNumber"
							name="telphoneNumber" placeholder="手机号">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-10">
						<input type="text" class="form-control" id="telphoneNumber"
							name="telphoneNumber" placeholder="验证码">
						<button type="button" class="btn btn-primary"  onclick="yanzhengma();">获取验证码</button>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary" id="submit">提交</button>
						<button type="button" class="btn btn-primary" onclick="loginpage();">登入页面</button>
					</div>
				</div>
					
			</form>
		</div>

	</div>
	<div style="text-align: center;"></div>






<script>




function loginpage(){
	window.location.href="login2.jsp"; 
}
function yanzhengma(){
	alert("获取验证码");
	var tel=document.getElementById("telphoneNumber").value;
	var telrule=/^[1][3,4,5,7,8][0-9]{9}$/;  
	if(tel==""||tel==''||tel==null||tel.length==0){
		alert("请先填写手机号");
	}
	else{
		if(!tel.match(telrule)){
			alert("请输入正确的手机号");
		}
		else{
			alert("获取到的手机号是："+tel);
		}
	}
	
}



</script>



</body>
</html>














