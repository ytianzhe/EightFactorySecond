<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="externalLinks.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>数据显示面板</title>
<script>
window.onload=function loadSearchdata(){
	var optsm = document.getElementById("mouldname");  
	var EmployeeName=document.getElementById("SearchEmployeeName").value;
	var mouldname=document.getElementById("Searchmouldname").value;
	var drawingno=document.getElementById("Searchdrawingno").value;
	var msg=document.getElementById("msg").value;
	if(msg!=null&&msg.length>0){
		alert(msg);}
//	alert("EmployeeName: "+EmployeeName+" mouldname: "+mouldname+" drawingno: "+drawingno);
//	
	if(EmployeeName!=null&&EmployeeName.length>0){
	document.getElementById("EmployeeNameid").value = EmployeeName;}
	if(mouldname!=null&&mouldname.length>0){
	 document.getElementById("mouldnameid").value = mouldname;}
	if(drawingno!=null&&drawingno.length>0){
	 document.getElementById("drawingnoid").value = drawingno;}
}
function reset2(){
	//alert("清空表格");
	document.getElementById("EmployeeNameid").value = "";
	document.getElementById('mouldnameid').value="-1";
	document.getElementById('drawingnoid').value="-1";	
}
</script>
<style>
	  th{
        text-align:center;/** 设置水平方向居中 */
        vertical-align:middle/** 设置垂直方向居中 */
    }
</style>
</head>

<body >
<c:if test="${not empty employeeleader}">
<!-- 
有返回结果
	 -->
<!-- 查询条件 -->
<div>
 <c:if test="${employeeleader=='组长'||employeeleader=='厂长'}"> 
  <!--  	MouldList-->					
<c:if test="${not empty MouldList}">
<!--  需要查询   -->
 	<form action="Fenye" method="post"  target="employeeright" id="Searchform" name="Searchform">
	<table>
	<tr>
	<th>工作人员:&nbsp </th>
	<th><input class="form-control" type="text" id="EmployeeNameid" name="EmployeeName" value="" /></th>
	<th>模具编号:&nbsp </th>
	<th>
	<select class="form-control" id="mouldnameid" name="mouldname">
				<option value="-1"></option>
		<c:forEach items="${MouldList}" var="m" varStatus="vs" begin="0">
					<option value="${m.mouldname}">${m.mouldname}</option>
		</c:forEach>
	</select>
	</th>
	<th>工件名称/图号:&nbsp</th>
	<th>
	<select class="form-control" id="drawingnoid" name="drawingno">
			<option value="-1"></option>
		<c:forEach items="${DrawingnoList}" var="d" varStatus="vs" begin="0">
					<option value="${d.machinedPartNameDrawingno}">${d.machinedPartNameDrawingno}</option>
		</c:forEach>
	</select>
	</th>
     <th><input class="btn btn-default purple" type="submit" name="button" value="查询"/></th>
    <th><input  class="btn btn-default purple" type="button" onclick="reset2()"  value="清空"/></th>
   	<th><input type="hidden"   name="method"  value="Search" /></th>
    <th><input type="hidden"   name="pageCount"  value="${pageCount}" /></th>
    <th><input type="hidden"   name="curPage"  value="${curPage}" /></th>
 	<th><input type="hidden"  name="employeeNumber" value="${employeeNumber}"  /></th>
    </tr>
</table>
</form>
</c:if>
</c:if>
<!-- 增加新日志 -->
<div>
<form action="OpenInsert" method="Post" target="employeeright">
	<input type="hidden" id="employeeNumber" value="${employeeNumber}" name="employeeNumber" />
	<c:if test="${employeeleader=='组员'||employeeleader=='组长'}"> 
	</br>
      		<input type="submit" class="btn btn-info"  name="button"  value="增加新日志" />						
  	</c:if>
</form>

</div>
</div>
</c:if>
<!-- 查询条件结束 -->
</br>
<c:if test="${not empty lessonListLog}">
<!--  有返回结果-->
<div  style="overflow:scroll;">
	<table class="table-bordered"  border="1" width="2400px" height="120px" align="center">
			<thead>
				<tr>
					<th scope="row" style="width:30px">编号</th>
					<th>状态</th>
					<th style="width:200px">操作</th>
					<th style="width:100px">创建时间</th>
					<th>模具编号</th>
					<th>图号</th>
					<th style="width:60px">工件数量</th>
					<th>工序</th>
					<th style="width:100px">工作人员</th>
		 			<th>加工理由</th>
					<th>加工内容</th>
					<th style="width:120px">预计完成时间(h)</th>
		  			<th>开始时间</th>
					<th>结束时间</th>
					<th>总耗时</th>
					<th style="width:100px">实际完成数量</th>
				    <th>备注</th>     
				</tr>
				<c:forEach items="${lessonListLog}" var="l" varStatus="vs" begin="0">
						<tr>
							<th ><c:out value="${(vs.index + 1+(curPage-1) * row)}"/><br /></th>
							<th>
							 <c:if test="${ l.state=='1' }"> 
      							 <p>未锁定 <p>
  								</c:if>
								 <c:if test="${ l.state=='2' }"> 
      							 <p>已锁定 <p>
  							</c:if>
							</th>
							<th>
							<c:if test="${employeeleader=='组员'||employeeleader=='组长'}"> 
      								<button class="btn btn-default" type="button" id="${l.id}"  value="${l.state}" onclick="edit(this)">编辑</button>
  							</c:if>
							
							<c:if test="${l.state=='1'}"> 
      								<button class="btn btn-default" type="button" id="${l.id}"  value="${l.state}" onclick="lock(this)">锁定</button>
  							</c:if>
						
							<c:if test="${l.state=='2'&&employeeleader=='组长'||employeeleader=='厂长'}"> 
      							<button class="btn btn-default" type="button" id="${l.id}"  value="${l.state}" onclick="unlock(this)">解锁</button>
  							</c:if>
							<br /></th>
							<th>
							<c:if test="${not empty l.createtime}">
								<fmt:formatDate value="${l.createtime}" pattern="yyyy-MM-dd" />
								<br />
							</c:if>
							</th>
							<th><c:out value="${l.mouldidentifier}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.drawingNoMachinedPartName}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.artifactsNumber}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.processName}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.name}" escapeXml="false" /><br /></th>
					  		<th><c:out value="${l.processingReasons}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.workContent}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.expectCompleteTime}" escapeXml="false" /><br /></th>
					 		<th><c:out value="${l.startTime}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.endTime}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.consumingTime}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.actualCompletedQuantity}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.note}" escapeXml="false" /><br /></th> 
						</tr>
				</c:forEach>
	</table>
</div>
</c:if>
<c:if test="${empty lessonListLog}">
<table class="table-bordered"  border="1" width="100%" height="120px" align="center">
			<thead>
				<tr>
					<th scope="row">序号</th>
					<th>操作</th>
					<th>创建时间</th>
					<th>模具编号</th>
					<th>图号</th>
					<th>工件数量</th>
					<th>工序</th>
					<th>工作人员id</th>
					<th>工作人员</th>
					<th>加工理由</th>
					<th>加工内容</th>
					<th>预计完成时间</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>总耗时</th>
					<th>实际完成数量</th>
					<th>备注</th>
					<th>状态</th>
				</tr>
				</thead></table>
</c:if>
<form action="Fenye" method="Post" target="employeeright">
		 <input type="hidden"  id="SearchEmployeeName" name="EmployeeName"  value="${EmployeeName}" />
 		 <input type="hidden"  id="Searchmouldname" name="mouldname" value="${mouldname}"  />
 		 <input type="hidden"  id="Searchdrawingno" name="drawingno" value="${drawingno}"  />
 		  <input type="hidden"  id="msg"  value="${msg}"  />
		 <input type="hidden"   name="lessonListLog"  value="${lessonListLog}" />
		 <!--  
		查询出 <input type="text"   name="allsize"  value="${allsize}" /> 条记录,
		每页 <input type="text"   name="rows"  value="${row}" />条
		共<input type="text"   name="pageCount"  value="${pageCount}" />页
		当前 <input type="text"   name="nowpage"  value="${curPage}" />页
		-->
		<!--  查询出${allsize}, 条记录,每页${row}条 ,共${pageCount}页,当前${curPage}页-->
	<!--	<div style="text-align: center;">	第${curPage}页/共${pageCount}页</div>-->
</form>
<!--新2的翻页-->
<div style="height:60px;position:relative;" >
	<div style="text-align: center;">第${curPage}页/共${pageCount}页       </div>
<!-- 翻页组件 -->

		<div  style="text-align: right;margin-right:10%" >
		<nav aria-label="Page navigation"  >
  		<ul class="pagination" >
  		  <li>
     		 <a  style="border: 1px solid #ddd;" href="Fenye?method=first&&curPage=1&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}"  target="employeeright" aria-label="Previous">
       	 	<span aria-hidden="true" style="color:#38acff" >&laquo;</span>
     	 </a>
   	 	</li>
    		<li><a  style="border: 1px solid #ddd;" href="Fenye?method=up&&curPage=${curPage}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright"><span style="color:#38acff"><</span> </a></li>
   		<c:forEach var="pageIndex" begin="1" end="${pageCount}">
			<c:choose>
		<c:when test="${pageIndex==1}">
			<c:choose>
				<c:when test="${curPage== pageIndex}">
				<li><a  style="border:1px solid #ddd;background-color: #38acff;color:white">${pageIndex}</a></li>
				</c:when>
				<c:otherwise>
				<li><a  style="border:1px solid #ddd;" href="Fenye?curPage=${pageIndex}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright">${pageIndex}</a></li>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${pageIndex==pageCount}">
					<c:choose>
						<c:when test="${curPage== pageIndex}">
							<li><a  style="border:1px solid #ddd;background-color:#38acff;color:white">${pageIndex}</a></li>
						</c:when>
						<c:otherwise>
						<li><a   style="border:1px solid #ddd;" href="Fenye?curPage=${pageIndex}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright">${pageIndex}</a></li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${pageCount<=10 }">
							<c:choose>
								<c:when test="${curPage== pageIndex}">
									<li><a style="border:1px solid #ddd;background-color: #38acff;color:white">${pageIndex}</a></li>
								</c:when>
								<c:otherwise>
								<li>	<a  style="border:1px solid #ddd;" href="Fenye?curPage=${pageIndex}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright">${pageIndex}</a></li>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${curPage-pageIndex<=3&&curPage-pageIndex>=-3 }">
									<c:choose>
										<c:when test="${curPage== pageIndex}">
											<li><a style="border:1px solid #ddd;background-color: #38acff;color:white">${pageIndex}</a></li>
										</c:when>
										<c:otherwise>
										<li>	<a   style="border: 1px solid #ddd;" href="Fenye?curPage=${pageIndex}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright">${pageIndex}</a></li>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${pageIndex==2 }">
											<li><a>...</a></li>
										</c:when>
										<c:when test="${pageIndex==pageCount-1 }">
										</c:when>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</c:forEach>
	<li><a  style="border: 1px solid #ddd;" href="Fenye?method=down&&curPage=${curPage}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright"><span style="color:#38acff"> ></span> </a></li>
    		<li>
     			 <a  style="border: 1px solid #ddd;" href="Fenye?method=last&&curPage=${curPage}&&employeeNumber=${employeeNumber}&&pageCount=${pageCount}&&EmployeeName=${EmployeeName}&&mouldname=${mouldname}&&drawingno=${drawingno}" target="employeeright" aria-label="Next">
       			 <span aria-hidden="true" style="color:#38acff">&raquo;</span>
      			</a>
   		 	</li>
 		</ul>
	</nav>
	</div>
</div>

	
	<div>

<script>
function edit(obj){
	//alert(obj.id);
	var id= obj.id;
	var state= obj.value;
	document.getElementById("msg").value="";
	if(state==1){
		//alert("状态未锁定");
		window.location.href = "Editemployeedate?loginid="+obj.id+"&&employeeid"+${employeeNumber}; 
	}
	else if(state==2){
		alert("请不要编辑已经锁定的项目 如果需要请联系该组组长");
	}
	else{
		alert("数据状态异常 请联系管理远核对数据");
	}
}
function lock(obj){
	
	var id= obj.id;
	var state= obj.value;
	var employeeNumber=document.getElementById("employeeNumber").value;
	if(state==1){
		if(confirm("确认要锁定吗？ 锁定后组员将无法编辑")){  
			window.location.href = "LockState?logid="+obj.id+"&&employeeNumber="+employeeNumber+"&&state="+state+"&&method=employee"; 
	        return true;  
	    }  
	    return false;  
	}
	else if(state==2){
		alert("状态已经锁定 请不要反复锁定 需要解锁请询问组长");
		//window.location.href = "Lock?loginid="+obj.id+"&&employeeid"+${employeeNumber}; 
	}
	else{
		alert("错误数据状态 请联系管理远核对数据");
	}
}
function unlock(obj){
	var id= obj.id;
	var state= obj.value;
	var employeeNumber=document.getElementById("employeeNumber").value;
	if(state==1){
		alert("状态未锁定  无法解锁 ");
	}
	else if(state==2){
		alert("状态已经锁定 你确定解锁么？");		
		window.location.href = "LockState?logid="+obj.id+"&&employeeNumber="+employeeNumber+"&&state="+state+"&&method=employee"
	}
	else{
		alert("错误数据状态 请联系管理远核对数据");
	}
	
}
</script>
</body>
</html>
