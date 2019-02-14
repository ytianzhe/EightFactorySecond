<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/externalLinks.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty employeelessonList}">

<!-- 展示数据区域 -->
		<div>
			<form action="" method="Post" target="employeeright">
				<table class="table-bordered" width="100%">
					<thead>
						<tr>
							<th>编号</th>
							<th>id</th>
							<th>工号</th>
							<th>员工姓名</th>
							<th>分组组名</th>
							<th>组内权限</th>
							<th>状态</th>
							<th>设计工程师</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${employeelessonList}" var="el" varStatus="vs"
							begin="0">
							<tr>
								<!--  <th><c:out value="${(vs.index + 1+(curPage-1) * row)}" /><br /></th>-->
								<th><c:out value="${(vs.index + 1)}" /><br />
								<th><c:out value="${el.id}" escapeXml="false" /><br /></th>
								<th><c:out value="${el.employeeNumber}" escapeXml="false" /><br /></th>
								<th><c:out value="${el.name}" escapeXml="false" /><br /></th>
								<th><c:out value="${el.groupTypeName}" escapeXml="false" /><br /></th>
								<th><c:out value="${el.leader}" escapeXml="false" /><br /></th>
								<c:if test="${el.state=='1'}"> 
      								<th>在职<br /></th>
  								</c:if>
								<c:if test="${el.state=='0'}"> 
      								<th>离职<br /></th>
  								</c:if>
							
								<c:if test="${el.designEngineer=='1'}"> 
      								<th>任命中<br /></th>
  								</c:if>
								<c:if test="${el.designEngineer=='0'}"> 
      								<th>未任命<br /></th>
  								</c:if>
								<th>
								<button type="button" id="${el.id}"  value="${el.designEngineer}"  onclick="EnableRemove(this)">启用/解除设计师</button>
								<button type="button" id="${el.id}"  value="${el.state}"  onclick="ban(this)">复原/离职</button>
								<br /></th>
								
							</tr>
						</c:forEach>
					</thead>
				</table>
				<input type="hidden" id="employeeNumber" value="${employeeNumber}"/>
			</form>

		</div>




	</c:if>
<script>
function EnableRemove(obj){
	var id= obj.id;
	var designEngineerstate= obj.value;
	var statemsg=null;
	var employeeNumber=document.getElementById("employeeNumber").value;
	//alert("id: "+id+"designEngineerstate: "+designEngineerstate+"工号： "+employeeNumber);
	 if (designEngineerstate=="1"){
		 statemsg="解除";
	 }
	 else if (designEngineerstate=="0"){
		 statemsg="启用"; 
	 }
	 if(confirm("确认 "+statemsg+" 吗？")){  
	window.location.href = "EmployeeInfoEdit?method=ChangeEngineer&&employeeNumber="+employeeNumber+"&&designEngineerstate="+designEngineerstate+"&&id="+id;
	 }
}

function ban(obj){
	var id= obj.id;
	var state= obj.value;
	var statemsg=null;
	var employeeNumber=document.getElementById("employeeNumber").value;
	//alert("id: "+id+"state: "+state+"工号： "+employeeNumber);
	 if (state=="1"){
		 statemsg="离职";
	 }
	 else if (state=="0"){
		 statemsg="复原"; 
	 }
	 
	if(confirm("确认 "+statemsg+" 吗？")){  
		 window.location.href = "EmployeeInfoEdit?method=ChangeState&&employeeNumber="+employeeNumber+"&&state="+state+"&&id="+id;
	    }  
	   
	
	
	
}

</script>
</body>
</html>