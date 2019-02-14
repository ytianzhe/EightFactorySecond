<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="externalLinks.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册界面</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<style>
#Code,#tishi{

    cursor: pointer;
}

</style>
</head>
<body style="overflow:auto">
	<div class="login_box">
		<div class="login_l_img">
			<img src="images/login-img.png" />
		</div>
		<div class="login" style="height: 580px;">
			<div class="login_logo">
				<a href="#"><img src="images/login_logo.png" /></a>
			</div>
			<div class="login_name">
				<p>八分厂管理人工注册系统</p>
			</div>
			<form action="EmployeeRegister" method="post" class="form-horizontal"  onsubmit="return check()" >
				<div class="form-group">
					<!--  	<label for="inputEmail3" class="col-sm-2 control-label">Email</label>-->
					<div class="col-sm-10">
						<input type="text" class="form-control" id="employeeName"
							name="employeeName" placeholder="名字"  onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"  >
					</div>
				</div>
				<div class="form-group" id="employeeNumberDIV">
					<!--	<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
					<div class="col-sm-10">
						<input type="text" class="form-control" id="employeeNumber"
							name="employeeNumber" placeholder="工号"  onKeyUp="value=value.replace(/\D/g,'')" onafterpaste="value=value.replace(/\D/g,'')" >
					</div>
				</div>
				<div class="form-group">
					<!--	<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
					<div class="col-sm-10">
						<select class="form-control" id="grouptype" name="grouptype">
						
							<option value="-1">-----请选择组别-----</option>
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
					<!--  	<label for="inputEmail3" class="col-sm-2 control-label">Email</label>-->
					<div class="col-sm-10">
						<input type="text" class="form-control" id="VerificationCode"
							name="VerificationCode" placeholder="验证码" >
						<div class="form-control" id="Code" onclick="show()" style="display:inline-block;width:70px"><a href="#"></a></div>
						<div id="tishi" onclick="show()" style="display:inline-block;width:70px">看不清</div>
					</div>
					
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-info">提交</button>
						
						<button type="button" class="btn btn-info" onclick="loginpage();">返回登入页面</button>
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








function check(){
	var a=true;
	var employeeName=document.getElementById("employeeName").value;
	var employeeNumber=document.getElementById("employeeNumber").value;
	var grouptype=document.getElementById("grouptype").value;
	var VerificationCode=document.getElementById("VerificationCode").value;
	var Code=document.getElementById("Code").innerText;
	
	//alert("employeeName: "+employeeName+"employeeNumber: "+employeeNumber+"grouptype: "+grouptype);
	if(employeeName==""||employeeName==null){
		alert("请填写你的名字");
		return false;
		}
	else{
		  if(employeeNumber==""||employeeNumber==null||employeeNumber.length>11){
			 alert("请正确填写你的工号 ");
			 return false;
			// break;
		  }
		  else{
			  if(grouptype==""||grouptype==null||grouptype=="-1"){
				  alert("请填写你的分组");
				  return false;
				  
			  }
			  else{
				  if(VerificationCode==""||grouptype==null){
					  alert("请填写你的验证码");
					  return false;
					  
				  }  
				  else{
					  if(VerificationCode!=Code){
						  alert("验证码不对");
						  return false;
						  
					  }  
					  else{
				  //alert("空表格自检完成");
				  // alert("验证码对");
				 	 if(confirm("确认提交吗？")){  
				       	 return true;  
				   	 }  
				    	return false; 
				//  return true;
				  }
				  }
		  } }
		}
		
	}
	
	


function isChinese(employeeName){
	var reg=/^[\u0391-\uFFE5]+$/;
	if(employeeName!=""&&!reg.test(employeeName)){
	alert('必须输入中文');
	return false;
		}
	}


function codes(n){
    var a="azxcvbnmsdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP0123456789";
    var b="";
  for (var i = 0;i<n;i++){
    var index=Math.floor(Math.random()*62);
     b+=a.charAt(index);

  }
  return b;
  };
  function show(){
    document.getElementById("Code").innerHTML=codes(5);
   
}
window.onload=show;

</script>
<script>
$("#employeeNumber").on("blur",function(){
	//alert("写好工号了");
	var employeeNumber=document.getElementById("employeeNumber").value;
	if(employeeNumber.length>11){
		alert("请填写正确的工号");
	}else{
	  $.ajax({  
          type:'post',  
          url:'/EightFactory/RepeatCheck?employeeNumber='+employeeNumber+"&&method=workid",  
         // data:$("#myform").serialize(), 
         //	data: 
          cache:false,  
          dataType:'json',  
          success:function(data) {
			    	if(data.success)
			    	{
			    		alert("该工号:"+employeeNumber+" 已经被注册请不要重复注册");
			    		document.getElementById("employeeNumber").value = "";
			    		document.getElementById("employeeNumberDIV").className = "form-group has-error has-feedback"; 
			    	}
			    		else
			    		{
			    			document.getElementById("employeeNumberDIV").className = "form-group has-success has-feedback"; 
			    		// alert("false");
			    		}
			    }
      }); 	
	
	
	
	
	}});

</script>


</body>
</html>


<!-- 
if(confirm("确认提交吗？")){  
				        return true;  
				    }  
				    return false;  

-->





<!--  alert("div中的内容："+Code);
				  alert(VerificationCode);
				  if(VerificationCode.equals(Code)==false){
					  alert("验证码不对");
					  return false; 
				  } -->


