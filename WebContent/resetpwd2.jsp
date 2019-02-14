<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="externalLinks.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码页面</title>
</head>
<body>

	<div style="">
		<div
			style="margin-top: 20px; margin-left: 30%; margin-right: 30%; border: 1px solid #888888; background-color: #ddd">
			<div
				style="margin-top: 10px; margin-left: 10px; margin-right: 10px; margin-bottom: 10px; background-color: white; text-align: center;">
				<p></p>
				<form action="" id="resetpwdform" method="post" target="_top">
					<div class="form-group">
						<label>账号:</label> <input type="text" class=""
							id="employeeNumber" name="employeeNumber" placeholder="工号"
							onKeyUp="value=value.replace(/\D/g,'')"
							onafterpaste="value=value.replace(/\D/g,'')">
					</div>
					<div class="form-group">
						<label>旧密码:</label> <input type="text" class="" id="oldPassword"
							name="oldPassword" placeholder="旧密码">
					</div>
					

					<div class="form-group">
						<label>新密码:</label> <input type="text" class="" id="newPassword"
							name="newPassword" placeholder="新密码">
					</div>
					<div class="form-group">
						<label>新密码确认:</label> <input type="text" class=""
							id="newPasswordagain" name="newPasswordagain"
							placeholder="第二次确认新密码">
					</div>
					<div>
						<button type="button"  id="submitbutton" class="btn btn-default">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
	$("#submitbutton").on("click",function(){
		var newPassword = document.getElementById("newPassword").value;
		var oldPassword = document.getElementById("oldPassword").value;
		var newPasswordagain = document.getElementById("newPasswordagain").value;
		var employeeNumber = document.getElementById("employeeNumber").value;
		if (employeeNumber == "" || employeeNumber == null) {
			alert("请先填写工号");
			return false;
		} else {
			
			if (oldPassword == "" || oldPassword == null) {
				alert("请先填写旧密码");
				return false;
			}
			else{
			
			if (newPassword == "" || newPassword == null) {
				alert("请先填写新密码");
				return false;
			} else {
				if (newPasswordagain == "" || newPasswordagain == null) {
					alert("请先填写新密码再次确认");
					return false;
				} else {
					//return true;
					if (newPassword == newPasswordagain) {
						//验证工号是否存在
					 $.ajax({  
	        		 type:'post',  
	       			  url:'/EightFactory/RepeatCheck?method=workid&&employeeNumber='+employeeNumber+"&&oldPassword="+oldPassword, 
	       			  cache:false,  
	       				  dataType:'json',  
	       				  success:function(data) {
				    	if(data.success)
				    	{
				    		//alert("工号存在");
				    		//return true;
				    		
				    		
				    		  document.getElementById("resetpwdform").action="/EightFactory/SaveNewPassword2";
				    		  document.getElementById("resetpwdform").submit();	  
				    	}
				    		else
				    		{
				    		var msg=data.msg;
					    		alert(msg);
					    	//	return false;
				    		// alert("false");
				    		}
				    }
	     }); 	
						
						
						
						//return true;
					} else {
						alert("两次输入的密码不一样");
						return false;
					}

				}
			}
		}
		}
	});	
		
		
		
	
		
		

	
	
	
	
		
	</script>
</body>
</html>