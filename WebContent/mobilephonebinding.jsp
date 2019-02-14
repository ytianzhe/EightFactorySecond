<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="externalLinks.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更换绑定手机号</title>

</head>
<body>
	<form action="SaveNewEmployeetelphone" method="Post" target="_top"
		onsubmit="return check()">
		<div
			style="margin-left: 200px; margin-right: 200px; margin-top: 30px;">
			<!-- <div  style="border:1px solid red;">-->
			<div>
				<c:if test="${not empty employeetelNumber}">
					<div class="form-group">
						<label>手机号</label> <input type="text" class="form-control"
							id="oldemployeetelNumber" value="${employeetelNumber}"
							readonly="true">
					</div>
				</c:if>
				
				<c:if test="${empty employeetelNumber}">
					<div class="form-group">
						<label>手机号</label> <input type="hidden" class="form-control"
							id="oldemployeetelNumber" value=""
							readonly="true">
					</div>
				</c:if>
				
				<div class="form-group" id="employeeNumberDIV">
					<label>新手机号</label> <input type="text" class="form-control"
						id="employeetelNumber" name="employeetelNumber" value=""
						onKeyUp="value=value.replace(/\D/g,'')"
						onafterpaste="value=value.replace(/\D/g,'')">
				</div>

				<div class="form-group">
					<label></label> <input type="hidden" class="form-control"
						id="employeeNumber" name="employeeNumber"
						value="${employeeNumber}"> <input type="hidden"
						class="form-control" id="employeeid" name="employeeid"
						value="${employeeid}">
				</div>
				<div class="form-group">
					<label>验证码</label> <input type="text" class=""
						id="VerificationCode" value=""> <input type="hidden"
						class="form-control" id="codeGen" value="${codeGen}"> <input
						type="button" class="btn btn-default" id="Code" value="验证码">
				</div>
				<button type="submit" class="btn btn-default">提交</button>
			</div>
		</div>
	</form>
	<script>





$("#Code").on("click",function(){
	//alert("发送验证码");
	var oldemployeetelNumber=document.getElementById("oldemployeetelNumber").value;
	var employeetelNumber=document.getElementById("employeetelNumber").value;
	//alert(oldemployeetelNumber+"--"+employeetelNumber);
	var employeetelNumber=document.getElementById("employeetelNumber").value;
	if(employeetelNumber==null||employeetelNumber==""||employeetelNumber.length==0){
		alert("请填写手机号");
	} 
	else{
		if(oldemployeetelNumber==employeetelNumber){
		alert("新手机号不能和原来的一样");
		}
		else{
		 var abc=document.getElementById('Code');
	// abc.disabled=true;
	//document.getElementById("Code").onclick = function() {
   		 time(abc);
   // } 
		var employeetelNumber=document.getElementById("employeetelNumber").value;
		if(employeetelNumber==null||employeetelNumber==""||employeetelNumber.length==0){
		alert("请填写新手机号");
		}else{
		var employeeNumber=document.getElementById("employeeNumber").value;
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
			    		if(data.employeetelNumber==employeetelNumber){
			    			alert("新手机号不能和原来的一样");
			    		}
			    		else{
			    		alert("该手机号:"+data.employeetelNumber+" 已经被绑定");
			    		}
			    		document.getElementById("employeetelNumber").value = "";
			    		document.getElementById("employeeNumberDIV").className = "form-group has-error has-feedback"; 
			    		
			    	}else{
			    		 $.ajax({  
			    	         type:'post',  
			    	         url:'/EightFactory/VerificationCode?employeetelNumber='+employeetelNumber+"&&method=resetTelphone", 
			    	         cache:false,  
			    	         dataType:'json',  
			    	         success:function(data) {
			    				    	if(data.success)
			    				    	{var msg=data.msg;
			    				    	var codeGen=data.codeGen;
			    				    	document.getElementById("codeGen").value=codeGen;
			    				    	alert(msg);
			    				    	}else{
			    				    		var msg=data.msg;
			    					    	alert(msg);
			    				    		// alert("false");
			    				    		}
			    				   		}
			    	    			 });
			    		
			    		//document.getElementById("employeeNumberDIV").className = "form-group has-success has-feedback"; 
			    					}
			   					 }
      						}); 	
	
						}
					}
				}
			}
	);
function VerificationCode(){
	
}
function check(){
	var VerificationCode=document.getElementById("VerificationCode").value;
	var codeGen=document.getElementById("codeGen").value;
	//alert(VerificationCode+"---"+codeGen);
	if(VerificationCode==codeGen&&VerificationCode!=null&&codeGen!=null&&VerificationCode!=""&&codeGen!=""){
		return true;
	}
	else{
		alert("验证码不正确");
		return false;
		
	}
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
</script>
</body>
</html>