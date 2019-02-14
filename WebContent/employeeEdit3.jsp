<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="externalLinks.jsp"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">

<script>
window.onload=function selectprocess(){
	var process=document.getElementById("processName").value;
	if(process!=null&&process.length>0){
		$("#process").find("option[value='"+process+"']").attr("selected",true);
		//alert(processName);
		//document.getElementById('process').value=processName;
	}
	
	
}

</script>






</head>
<body>
<form action="addemployeedate" method="post" class="form-horizontal" target="employeeright">
	<div  style="margin:20px" >
	<c:forEach items="${lessonListLog}" var="l" varStatus="vs">
					 <fieldset>
                         <legend>创建时间</legend>
                        <div class="form-group">
                           <label for="disabledSelect"  class="col-sm-2 control-label">表名</label>
                           <div class="input-group date form_datetime col-md-5"
								data-date="1979-09-16T05:25:07Z"
								data-date-format="yyyy-mm-dd hh:mm:ss"
								data-link-field="dtp_input1">
								<input class="form-control" size="16" type="text"
									value="${l.createtime}" readonly name="DateTimePicking">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-th"></span></span>
                           </div>
                           
                        </div>
                    </fieldset>
				
	
                    <fieldset>
                        <legend>精加工日志数据</legend>
                       <div class="form-group">
                          <label class="col-sm-2 control-label" for="ds_host">模具型号</label>
                          <div class="col-sm-4">
                          
                             <div class="controls">
						<!--  <input  type="text" value="${l.mouldidentifier}"/>-->
						
						
								<!--   	<select id="mouldidentifier" name="mouldidentifier" class="input-xlarge" onchange="mouldonchange(this)">-->
								<select id="mouldidentifier" name="mouldidentifier"
									class="input-xlarge">
									<option>-----请选择模具型号-----</option>
									<c:forEach items="${MouldList}" var="ml" varStatus="your">
										<option <c:if test="${l.mouldidentifier == ml.mouldname}">selected</c:if> >${ml.mouldname}</option>
									</c:forEach>
								</select>

							</div>
                          </div>
                          <label class="col-sm-2 control-label" for="ds_name">工件数量</label>
                          <div class="col-sm-4">
                          	
								<input id="textinput-14" name="artifactsNumber"
								class="form-control"	 type="text" value="${l.artifactsNumber}">
							
                          </div>
                       </div>
                       <div class="form-group">
                          <label class="col-sm-2 control-label" for="ds_username">图号/加工件名称</label>
                          <div class="col-sm-4">
                          
                            <select id="DrawingNoMachinedPartName" name="DrawingNoMachinedPartName"
									class="input-xlarge">
									<option>---请先选择模具型号----</option>
									<c:forEach items="${machinedPartNameDrawingnoList}" var="mD" varStatus="your">
										<option <c:if test="${l.drawingNoMachinedPartName == mD.machinedPartNameDrawingno}">selected</c:if> >${mD.machinedPartNameDrawingno}</option>
									</c:forEach>
								</select>
                         
                          </div>
                          <label class="col-sm-2 control-label" for="ds_password">工序</label>
                          <div class="col-sm-4">
                             <select name="process" class="input-xlarge" id="process">
									<option value="1" <c:if test="${l.process == '1'}">selected</c:if>>设计</option>
									<option value="2" <c:if test="${l.process == '2'}">selected</c:if>>工艺</option>
									<option value="3" <c:if test="${l.process == '3'}">selected</c:if>>钳工</option>
									<option value="4" <c:if test="${l.process == '4'}">selected</c:if>>铣床</option>
									<option value="5" <c:if test="${l.process == '5'}">selected</c:if>>磨床</option>
									<option value="6" <c:if test="${l.process == '6'}">selected</c:if>>快走丝</option>
									<option value="7" <c:if test="${l.process == '7'}">selected</c:if>>加工中心</option>
									<option value="8" <c:if test="${l.process == '8'}">selected</c:if>>电脉冲</option>
									<option value="9" <c:if test="${l.process == '9'}">selected</c:if>>慢走丝</option>
									<option value="10"<c:if test="${l.process == '10'}">selected</c:if>>省模抛光</option>
								</select>
                          </div>
                       </div>
                    </fieldset>     
                    <fieldset>
                      <!--     <legend>选择相关表</legend>-->
                        <div class="form-group">
                           <label   class="col-sm-2 control-label">设备编号</label>
                           <div class="col-sm-4">
                             	<input id="textinput-12" name="machineNo"
									placeholder="placeholder" class="input-xlarge" type="text"
									value="${l.machineNo}"> 
                           </div>
                           <label class="col-sm-2 control-label" >工人员id</label>
                          <div class="col-sm-4">
                             <input  name="workid" placeholder="工号录入"
									 type="text" value="${l.workerid}">
                          </div>
                          	</div>
                          <div class="form-group">
                          <label  class="col-sm-2 control-label">预计完成时间</label>
                          	 <div class="col-sm-4">
                             	<input id="textinput-10" name="expectCompleteTime"
									placeholder="placeholder" class="input-xlarge" type="text"
									value="${l.expectCompleteTime}">
                           </div>
                          
                        </div>
                        
                    </fieldset>
                    
                     <fieldset>
                        <legend>时间采集数据</legend>
                        <div class="form-group">
                           <label   class="col-sm-2 control-label">开始时间</label>
                           <div class="col-sm-4">
                             	<input id="startTime" name="startTime" placeholder="请输入开始加工的时间"
									class="input-xlarge" type="text" value="${l.startTime}">
                           </div>
                           <label class="col-sm-2 control-label" >工结束时间</label>
                          <div class="col-sm-4">
                             <input id="endTime" name="endTime" placeholder="请输入结束加工的时间"
									class="input-xlarge" type="text" value="${l.endTime}">
                          </div>
                         </div>
                         <div class="form-group">
                           <label   class="col-sm-2 control-label">总耗时</label>
                           <div class="col-sm-4">
                             	<input id="consumingTime" name="consumingTime"
									class="input-xlarge" type="text"
									value="${l.consumingTime}">
                           </div>
                         </div>
                    </fieldset>
                     <fieldset>
                      <legend >其他数据</legend>
                     	 <div class="form-group">
                           <label   class="col-sm-2 control-label">实际完成数量</label>
                           <div class="col-sm-4">
                             <input id="textinput-7" name="actualCompletedQuantity"
									placeholder="实际完成数量" class="input-xlarge" type="text"
									value="${l.actualCompletedQuantity}">
                           </div>
                         </div>
                      <div class="form-group">
                           <label   class="col-sm-2 control-label">备注</label>
                           <div class="col-sm-4">
                             <textarea name="note" clos=",50" rows="3" warp="virtual">${l.note}</textarea>
                           </div>
                           
                         </div>
                           <div class="form-group">
                           <input id="employeeid" name="employeeid" class="input-xlarge"
									type="text" value="${employeeNumber}">
							<input type="hidden" id="processName" value="${l.process}">
                           </div>
                           </c:forEach>
                           <div class="form-group" style="text-align: center;">
								<button id="singlebutton-0" name="singlebutton-0"
									class="btn btn-primary">提交</button>
								<a href="javascript:history.go(-1);">后退</a>
						
                		  </div>
                     </fieldset>
                 
                  	
		</div>
</form>
</body>
</html>