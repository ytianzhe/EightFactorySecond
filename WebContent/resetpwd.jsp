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

<div  style="">
<div style="margin-top:20px;margin-left:30%;margin-right:30%;border:1px solid #888888;background-color:#ddd ">
<div style="margin-top:10px;margin-left:10px;margin-right:10px;margin-bottom:10px;background-color:white;text-align:center;">
<p></p>
<form action="SaveNewPassword" method="Post" target="_top"    onsubmit="return check()">
  <div class="form-group">
    <label >手机号:</label>
    <input type="text" class="" id="employeetelNumber" name="employeetelNumber" placeholder="手机号" onKeyUp="value=value.replace(/\D/g,'')" onafterpaste="value=value.replace(/\D/g,'')">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">验证码:</label>
    <input type="text"  id="VerificationCode" placeholder="验证码">
     <input type="hidden" class="form-control" id="codeGen"  value="${codeGen}">
     <div>
    <input class="btn btn-default" type="button" class="btn btn-primary" id="Code" value="获取验证码">
  </div>
  </div>
  <div class="form-group">
    <label >新密码:</label>
    <input type="text" class="" id="newPassword" name="newPassword" placeholder="新密码">
  </div>
  <div class="form-group">
    <label >新密码确认:</label>
    <input type="text" class="" id="newPasswordagain"  name="newPasswordagain" placeholder="第二次确认新密码">
  </div>
 <div >
  <button type="submit" class="btn btn-default">提交</button>
</div>
</form>
</div>
</div>
</div>
	<script>
	$("#Code").on("click",function(){
		//alert("发送验证码");
		 var abc=document.getElementById('Code');
		// abc.disabled=true;
		// document.getElementById("Code").onclick = function() {
    	time(abc);
    	//} 
		var employeetelNumber=document.getElementById("employeetelNumber").value;
		if(employeetelNumber==null||employeetelNumber==""||employeetelNumber.length==0){
			alert("请填写手机号");
		}
		
		else{
			if(isPoneAvailable(employeetelNumber)==false){
				alert("请输入正确的手机号");
				}
				else{
			
					  $.ajax({  
				      	    type:'post',  
				       	   url:'/EightFactory/RepeatCheck?employeetelNumber='+employeetelNumber+"&&method=telphone",  
				        	 // data:$("#myform").serialize(), 
				        	 //	data: 
				         	 cache:false,  
				         	 dataType:'json',  
				          	success:function(data) {
							    	if(data.success)
							    	{
							    		//alert("该手机号:"+data.employeetelNumber+" 已经被绑定");
							    		//document.getElementById("employeetelNumber").value = "";
							    		//document.getElementById("employeeNumberDIV").className = "form-group has-error has-feedback"; 
							    		 $.ajax({  
							    	         type:'post',  
							    	         url:'/EightFactory/VerificationCode?employeetelNumber='+employeetelNumber+"&&method=resetpwd", 
							    	         cache:false,  
							    	         dataType:'json',  
							    	         success:function(data) {
							    				    	if(data.success)
							    				    	{
							    				    		var msg=data.msg;
							    				    		var codeGen=data.codeGen;
							    				    		document.getElementById("codeGen").value=codeGen;
							    				    		alert(msg);
							    				    	}
							    				    		else
							    				    		{
							    				    		var msg=data.msg;
							    					    	alert(msg);
							    				    		// alert("false");
							    				    		}
							    				    }
							    	     }); 
							    	}
							    		else
							    		{
							    			alert("该手机号:"+data.employeetelNumber+" 没有被绑定到任何账号中 请先去绑定手机号吧");
							    			document.getElementById("employeetelNumber").value = "";
							    			//document.getElementById("employeeNumberDIV").className = "form-group has-success has-feedback"; 
							    		// alert("false");
							    		}
							    }
				      }); 
				}}});
	function check(){
		var VerificationCode=document.getElementById("VerificationCode").value;
		var codeGen=document.getElementById("codeGen").value;
		var newPassword=document.getElementById("newPassword").value;
		var newPasswordagain=document.getElementById("newPasswordagain").value;
		var employeetelNumber=document.getElementById("employeetelNumber").value;
		//alert(VerificationCode+"---"+codeGen);
	//	alert("employeetelNumber:"+employeetelNumber);
		
		if(employeetelNumber==""||employeetelNumber==null)
		{
			alert("请先填写手机号");
			return false;
		}
		else{
			if(employeetelNumber.length!=11){
				alert("请填写11位手机号");
				return false;
			}
			else{
			if(VerificationCode==""||VerificationCode==null){
				alert("请先填写验证码");
				return false;
			}
			
			else{
				if( newPassword==""||newPassword==null){
					alert("请先填写新密码");	
					return false;
				}
				else{
					if( newPasswordagain==""||newPasswordagain==null){
						alert("请先填写新密码再次确认");	
						return false;
					}
					else{
		if(VerificationCode==codeGen){
			//return true;
			if(newPassword==newPasswordagain){
				return true;	
			}
			else{
				alert("两次输入的密码不一样");
				return false;	
			}
		}
		else{
			alert("验证码不正确");
			return false;
			
		}
		}}}}}
	}
	
	
	var wait = 60;
	function time(btn) {
	    if(wait == 0) {
	        btn.removeAttribute("disabled");
	        btn.value = "获取验证码";
	        wait = 60;
	    } else {
	        btn.setAttribute("disabled", true);
	        btn.value = "重新发送(" + wait + ")";
	        wait--;
	        setTimeout(function() {
	                time(btn)
	            },
	            1000)
	    }
	}
	
	
	
	  function isPoneAvailable(tel) {
          var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
          if (!myreg.test(tel)) {
              return false;
          } else {
              return true;
          }
      }
	</script>	
</body>
</html>