<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.echart1{
height:400px;
width:240px;
}
</style>
<link rel="stylesheet" href="css/bootstrap.css">
 <script src="js/echarts.js"></script>
 <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div style="padding: 15px;padding-top:40px">
<form id="quanxianform" method="post"  target="employeeright">
<table class="table">
      <caption style="text-align: center;">员工信息</caption>
      <thead>
     
      </thead>
      <tbody>
        <tr>
          <th >姓名</th>
          
          <td>${employeeName}</td>
         
        </tr>
        <tr>
          <th scope="row">工号</th>
          <td>${employeeNumber}</td>
         
        </tr>
        <tr>
          <th scope="row">小组</th>
          <td>${employeeGroupName}</td>
          
        </tr>
        <tr>
          <th scope="row">小组职位</th>
          <td>${employeeleader}</td>
          </tr>
         <c:if test="${not empty employeetelphone}">
         <tr>
          <th scope="row">手机号</th>
          <td>${employeetelphone}</td>
          </tr> 
          </c:if>
        
      </tbody>
    </table>
     <c:if test="${employeeleader=='厂长'}"> 
         	<td><input type="hidden"  name="employeeNumber" value="${employeeNumber}"/></td>
         	<td><input type="hidden"  name="method" value="Search"/></td>
      		 <td><button class="btn btn-default" type="button" onclick="javascript:guanlirenyuan()" >人员管理</button></td>
  	</c:if>
  	<c:if test="${designEngineer=='1'}"> 
         	<tr>
         	<td><input type="hidden"  name="employeeNumber" value="${employeeNumber}"/></td>
      		 <td><button class="btn btn-default" type="button" onclick="javascript:quanxian()">设计工程师</button></td>
      	    </tr>				
  		</c:if>
  		
  	 <c:if test="${employeeleader=='厂长'||employeeleader=='组长'||employeeleader=='组员'}"> 
         	<td><input type="hidden"  name="employeeNumber" value="${employeeNumber}"/></td>
         	<td><input type="hidden"  name="method" value=""/></td>
      		 <td><button class="btn btn-default" type="button" onclick="javascript:yuangongshujun()" >员工数据</button></td>
  	</c:if>	
  		
  		<td><button class="btn btn-default" type="button" onclick="javascript:tubiao()" >图表统计数据</button></td>
        <td><input type="hidden"  name="employeeid" value="${employeeid}"/></td>
        <td><input type="button" value="修改密码" class="btn btn-default" onclick="javascript:resetPassword()"></td>  
        <td><input type="hidden"  id="employeetelphone" name="employeetelphone" value="${employeetelphone}"/></td>
          
  		<input type="hidden" name="employeeName" value="${employeeName}" id="employeeName"/>
    </form>
   </div> 
  
  
  <script>
  function tubiao(){
	  document.getElementById("quanxianform").action="/EightFactory/ChartStatistics"
		  document.getElementById("quanxianform").submit();	
	  
  }
   function quanxian(){
	   document.getElementById("quanxianform").action="/EightFactory/DesignEngineerEdit";
	   document.getElementById("quanxianform").submit();	   
   }
   
   function guanlirenyuan(){
	   document.getElementById("quanxianform").action="/EightFactory/EmployeeInfoEdit";
	   document.getElementById("quanxianform").submit();	   
   }
   function ytz(){
	   
	   alert("1111");
   }
   function yuangongshujun(){
	   document.getElementById("quanxianform").action="/EightFactory/Fenye";
	   document.getElementById("quanxianform").submit();	   
   }
   function BindCellPhoneNumber(){
	   document.getElementById("quanxianform").action="/EightFactory/BindCellPhoneNumber";
	   document.getElementById("quanxianform").submit();
   }
   function resetPassword(){
	  // var telnumber=document.getElementById("employeetelphone").value;
	 //  var employeeid=document.getElementById("employeeid").value;
	   
	//   if(telnumber==""||telnumber==null||telnumber.length==0||telnumber=="null"){
	//	   alert("请先绑定手机号");
	//   }
	//   else{
	   document.getElementById("quanxianform").action="resetpwd2.jsp";
	   document.getElementById("quanxianform").submit();
	//   }
   }
   </script>
   
</body>
</html>
<!-- 
取消手机号的绑定
<c:if test="${ empty employeetelphone}">
           <td><input type="hidden"  name="employeeNumber" value="${employeeNumber}"/></td>
           <td><input type="hidden"  name="employeeid" value="${employeeid}"/></td>
          <td><input type="button" value="绑定手机号" class="btn btn-default" onclick="javascript:BindCellPhoneNumber()"></td>  
       </br>
        </c:if> 
         <c:if test="${not empty employeetelphone}">
           <td><input type="hidden"  name="employeeNumber" value="${employeeNumber}"/></td>
           <td><input type="hidden"  name="employeeid" value="${employeeid}"/></td>
          <td><input type="button" value="跟换手机号" class="btn btn-default" onclick="javascript:BindCellPhoneNumber()"></td>  
        </c:if>  -->