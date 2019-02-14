<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="externalLinks.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模具型号图号  加工件名称展示页面</title>
<style>

	  th{
        text-align:center;/** 设置水平方向居中 */
        vertical-align:middle/** 设置垂直方向居中 */
    }

</style>
</head>
<body>

<!-- 增加新日志 -->
<div>
<form action="mouldRegister.jsp" method="Post" target="employeeright">
	<input type="hidden" id="employeeNumber" value="${employeeNumber}" name="employeeNumber" />
      		<input type="submit"   name="button" class="btn btn-default"   value="增加新模具型号加工件名称图号" />	
      							
	</form>

</div>
</br>
		<div>
	<c:if test="${not empty mouldlessonList}">
	<!-- 
	有模具型号返回结果
	 -->
		<div  style="overflow:scroll;">
	<table class="table-bordered"  border="1" width="100%" height="120px" align="center">
			<thead>
				<tr>
					<th scope="row" style="width:30px">编号</th>
					<th>模具编号</th>
					<th>加工件名称/图号</th>
					<th>工序</th>
					<th>创建人id</th>
					<th style="width:100px">创建时间</th>
					<th>创建人</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${mouldlessonList}" var="l" varStatus="vs" begin="0">
						<tr>
							
						<!-- 	<th ><c:out value="${(vs.index + 1+(curPage-1) * row)}"/><br /></th> -->
						<!-- 	<th><c:out value="${l.id}" escapeXml="false" /><br /></th> -->
						
							<th><c:out value="${l.id}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.mouldname}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.machinedPartNameDrawingno}" escapeXml="false" /><br /></th>
							<th>
							<c:if test="${l.process==1}"> 
							 <p>设计 <p>
							</c:if>
							<c:if test="${l.process==2}"> 
							 	<p>工艺 <p>
							</c:if>
							<c:if test="${l.process==3}"> 
							 	<p>钳工 <p>
							</c:if>
							<c:if test="${l.process==4}"> 
							 	<p> 铣床<p>
							</c:if>
							<c:if test="${l.process==5}"> 
							 	<p>磨床 <p>
							</c:if>
							<c:if test="${l.process==6}"> 
							 	<p>快走丝 <p>
							</c:if>
							<c:if test="${l.process==7}"> 
							 	<p>加工中心 <p>
							</c:if>
							<c:if test="${l.process==8}"> 
							 	<p>电脉冲 <p>
							</c:if>
							<c:if test="${l.process==9}"> 
							 	<p>省模抛光 <p>
							</c:if>
							<c:if test="${l.process==10}"> 
							 	<p>禁用中 <p>
							</c:if>
							</th>
							<th><c:out value="${l.createid}" escapeXml="false" /><br /></th>
							<th>
							<c:if test="${not empty l.createtime}">
								<fmt:formatDate value="${l.createtime}" pattern="yyyy-MM-dd" />
							</c:if>
					</th>
							<th><c:out value="${l.createUserName}" escapeXml="false" /><br /></th>
							<th>
							 <c:if test="${ l.state=='1' }"> 
      							 <p>启用中 <p>
  								</c:if>
								 <c:if test="${ l.state=='2' }"> 
      							 <p>禁用中 <p>
  							</c:if>
							</th>
							<th>
      						<button class="btn btn-warning" type="button" id="${l.id}"  value="${l.state}" onclick="edit(this)">编辑</button>
      						<button class="btn btn-danger" type="button" id="${l.id}"  value="${l.state}" onclick="ban(this)">启用/禁用</button>
							<br /></th>
						</tr>
				</c:forEach>
	</table>
</div>
</c:if>
</div>	
	
	<script>
	function ban(obj){
		var id= obj.id;
		var state= obj.value;
		var employeeNumber=document.getElementById("employeeNumber").value;
		var msg=null;
		var statemsg=null;
		if(state=="1"){
			statemsg="启用中";
			msg="禁用";
		}
		//alert("id"+id+"--"+"状态： "+state+ "当前修改人"+employeeNumber);
		else if(state=="2"){
			statemsg="禁用中";
			msg="启用";
		}
		
//		 if(confirm("编号: "+id+" 当前状态为： "+statemsg+ "  确定要"+msg+"么？ 用户id: "+employeeNumber)){  
		 if(confirm(" 确定要"+msg+"么？ ")){  
			 window.location.href = "LockState?logid="+obj.id+"&&employeeNumber="+employeeNumber+"&&state="+state+"&&method=mould"
		    }  
}
function edit(obj){
	var id= obj.id;
	var state= obj.value;
	var employeeNumber=document.getElementById("employeeNumber").value;
	var msg="编辑";
	var statemsg=null;
	if(state =="1"){
	//if(confirm("编号: "+id+"确定要"+msg+"么？ 用户id: "+employeeNumber)){  
	if(confirm("确定要"+msg+"么？")){  
		 window.location.href = "getMouldDate?mouldid="+obj.id+"&&employeeNumber="+employeeNumber;
		
	
	}  
	}else if(state =="2"){
		alert("该图纸型号禁用中 请先解锁！");
	}
}
	</script>	
		
		
</body>
</html>