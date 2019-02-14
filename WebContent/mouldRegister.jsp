<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="externalLinks.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script>
	
</script>
</head>
<body style="overflow: auto">
	<div class="login_box">


		<div class="login_name">
			<p>八分厂管理模具型号注册系统</p>
		</div>

		<c:if test="${not empty mouldlessonList}">
			<!-- 
	有模具型号返回结果
	 -->
			<c:forEach items="${mouldlessonList}" var="m" varStatus="vs">
				<form action="MouldRegister" method="Post" class="form-horizontal"
					target="employeeright" onsubmit="return zijian()">
					<div class="form-group">
						<!--  	<label for="inputEmail3" class="col-sm-2 control-label">Email</label>-->
						<div class="col-sm-10">
							<input type="text" class="form-control" id="mouldname"
								name="mouldname" placeholder="模具型号" value="${m.mouldname}">
						</div>
					</div>
					<div class="form-group" name="employeeNumberDIV">
						<!--	<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
						<div class="col-sm-10">
							<input type="text" class="form-control" id="MachinedPartName"
								name="MachinedPartName" placeholder="加工件名称" value="${m.machinedPartName}">
						</div>
					</div>
					<div class="form-group" name="employeeNumberDIV">
						<!--	<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
						<div class="col-sm-10">
							<input type="text" class="form-control" id="drawingno"
								name="drawingno" placeholder="图号" value="${m.drawingno}">
						</div>
					</div>
					<div class="form-group">
						<div></div>
						<div class="col-sm-10">
							<select class="form-control" id="process" name="process">
								<option value="-1">----请选择工序----</option>
								<option value="1"
									<c:if test="${m.process == '1'}">selected</c:if>>设计</option>
								<option value="2"
									<c:if test="${m.process == '2'}">selected</c:if>>工艺</option>
								<option value="3"
									<c:if test="${m.process == '3'}">selected</c:if>>钳工</option>
								<option value="4"
									<c:if test="${m.process == '4'}">selected</c:if>>铣床</option>
								<option value="5"
									<c:if test="${m.process == '5'}">selected</c:if>>磨床</option>
								<option value="6"
									<c:if test="${m.process == '6'}">selected</c:if>>快走丝</option>
								<option value="7"
									<c:if test="${m.process == '7'}">selected</c:if>>加工中心</option>
								<option value="8"
									<c:if test="${m.process == '8'}">selected</c:if>>电脉冲</option>
								<option value="9"
									<c:if test="${m.process == '9'}">selected</c:if>>慢走丝</option>
								<option value="10"
									<c:if test="${m.process == '10'}">selected</c:if>>省模抛光</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">提交</button>
						</div>
					</div>
					<input type="hidden" name="EmployeeNumber" value="${employeeNumber}" /> 
					<input type="hidden" name="mouldid" value="${m.id}" />
					<input type="hidden" id="msg" name="msg" value="${msg}" />
				</form>
			</c:forEach>
		</c:if>
		<c:if test="${ empty mouldlessonList}">
			<!-- 
		没有模具型号返回结果
		 -->
			<form action="MouldRegister" method="Post" class="form-horizontal"
				target="employeeright" onsubmit="return zijian()">
				<div class="form-group">
					<!--  	<label for="inputEmail3" class="col-sm-2 control-label">Email</label>-->
					<div class="col-sm-10">
						<input type="text" class="form-control" id="mouldname"
							name="mouldname" placeholder="模具型号" value="">
					</div>
				</div>
				<div class="form-group" name="employeeNumberDIV">
					<!--	<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
					<div class="col-sm-10">
						<input type="text" class="form-control" id="MachinedPartName"
							name="MachinedPartName" placeholder="加工件名称" value="">
					</div>
				</div>
				<div class="form-group" name="employeeNumberDIV">
					<!--	<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
					<div class="col-sm-10">
						<input type="text" class="form-control" id="drawingno"
							name="drawingno" placeholder="图号" value="">
					</div>
				</div>
				<div class="form-group">
					<div></div>
					<div class="col-sm-10">
						<select class="form-control" id="process" name="process">
							<option value="-1">----请选择工序----</option>
							<option value="1">设计</option>
							<option value="2">工艺</option>
							<option value="3">钳工</option>
							<option value="4">铣床</option>
							<option value="5">磨床</option>
							<option value="6">快走丝</option>
							<option value="7">加工中心</option>
							<option value="8">电脉冲</option>
							<option value="9">慢走丝</option>
							<option value="10">省模抛光</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">提交</button>
						<button type="button" class="btn btn-primary" onclick="back()">返回</button>
					</div>
				</div>

				<input type="hidden" name="EmployeeNumber" value="${employeeNumber}" />
				<input type="hidden" name="mouldid" value="${m.id}" /> 
				<input type="hidden" id="msg" name="msg" value="${msg}" />
			</form>
		</c:if>
	</div>
	<div style="text-align: center;"></div>
	<script>
		function submit22() {
			alert("1");
			var employeeName = window.frames["employeeleft"].window.ytz();
			alert(employeeName);
		}
		function back() {
			window.history.go(-1);
		}
		function zijian() {
			var mouldname = document.getElementById("mouldname").value;
			var MachinedPartName = document.getElementById("MachinedPartName").value;
			var drawingno = document.getElementById("drawingno").value;
			var process = document.getElementById("process").value;
		//	alert("mouldname:"+mouldname+"jiagongjian: "+jiagongjian+"MachinedPartName: "+MachinedPartName+"process:"+process);
			if (mouldname == "" || mouldname == null || mouldname.length == 0) {
				alert("模具型号不能为空");
				return false;
			} else {
				if (MachinedPartName == "" || MachinedPartName == null
						|| MachinedPartName.length == 0) {
					alert("加工件名称不能为空");
					return false;
				} else {
					if (drawingno == "" || drawingno.length == 0) {
							alert("图号名称不能为空");
						return false;
					} else {
						if (process == "" || process == null
								|| process.length == 0 || process == "-1") {
							alert("请选择正确的工序");
							return false;
						} else {
							if (confirm("该模具图纸型号为：" + mouldname + "加工件名/图号： "
									+ MachinedPartName + "/" + drawingno
									+ "确认提交吗？")) {
								return true;
							}
						return false;
						}

					}
				}
			}

		}
	</script>
	<script>
		$("#drawingno")
				.on(
						"blur",
						function() {
							//alert("写好工号了");

							var MachinedPartName = document.getElementById("MachinedPartName").value;
							var drawingno = document.getElementById("drawingno").value;
							var machinedPartNameDrawingno = MachinedPartName + "/"+ drawingno;
							$.ajax({
										type : 'post',
										url : '/EightFactory/RepeatCheck?machinedPartNameDrawingno='
												+ machinedPartNameDrawingno
												+ "&&method=machinedPartNameDrawingno",
										// data:$("#myform").serialize(), 
										//	data: 
										cache : false,
										dataType : 'json',
										success : function(data) {
											if (data.success) {
												alert("该加工件图纸/图号:"
														+ machinedPartNameDrawingno
														+ " 已经被注册请不要重复注册");
												document
														.getElementById("MachinedPartName").value = "";
												document
														.getElementById("drawingno").value = "";
												//	document.getElementById("employeeNumberDIV").className = "form-group has-error has-feedback"; 
											} else {
												//document.getElementsByName("employeeNumberDIV").className = "form-group has-success has-feedback"; 

											}
										}
									});

						});
	</script>
</body>
</html>