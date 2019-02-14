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








</head>
<body style="overflow: auto">
	<div>


		<form action="addemployeedate" method="post" class="form-horizontal"
			target="employeeright" onsubmit="return check()">
			<div style="margin: 20px">
			<fieldset>
			<legend>当前系统日期</legend>
				<div class="form-group">
						<label for="disabledSelect" class="col-sm-2 control-label">当前时间</label>
						<div class="input-group date  col-md-5">
							<input class="form-control" size="16" type="text"
								id="" value="<%=new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0,10) %>" readonly name="">
							
						</div>

					</div>
			</fieldset>
<!-- 
				<fieldset>
					<legend>创建时间*</legend>
					<div class="form-group">
						<label for="disabledSelect" class="col-sm-2 control-label">创建时间</label>
						<div class="input-group date form_datetime col-md-5"
							data-date="1979-09-16T05:25:07Z"
							data-date-format="yyyy-mm-dd hh:mm:ss"
							data-link-field="dtp_input1">
							<input class="form-control" size="16" type="text"
								id="DateTimePicking" value="" readonly name="DateTimePicking">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon">
							</span>
						</div>

					</div>
				</fieldset>
				
				
				<fieldset>
					<legend>创建时间*</legend>
					<div class="form-group">
						<label for="disabledSelect" class="col-sm-2 control-label">创建时间</label>
						<div class="input-group  col-md-5">
							<input type="text" readonly name="feedDay" id="feedDay" class="form-control tbrq"/>
							
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span>
								</span>
								 <span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>

					</div>
				</fieldset>
			 -->	

				<fieldset>
					<legend>精加工日志数据</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">模具型号*</label>
						<div class="col-sm-4">

							<div class="controls">
								<!--  <input  type="text" value="${l.mouldidentifier}"/>-->


								<!--   	<select id="mouldidentifier" name="mouldidentifier" class="input-xlarge" onchange="mouldonchange(this)">-->
								<select id="mouldidentifier" name="mouldidentifier"
									class="input-xlarge">
									<option value="-1">-----请选择模具型号-----</option>
									<c:forEach items="${MouldList}" var="ml" varStatus="your">
										<option>${ml.mouldname}</option>
									</c:forEach>
								</select>

							</div>
						</div>
						<label class="col-sm-2 control-label" for="ds_name">工件数量*</label>
						<div class="col-sm-4">

							<input id="artifactsNumber" name="artifactsNumber"
								class="input-xlarge" type="text" value=""
								onKeyUp="value=value.replace(/\D/g,'')"
								onafterpaste="value=value.replace(/\D/g,'')">

						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_username">图号/加工件名称*</label>
						<div class="col-sm-4">

							<select id="DrawingNoMachinedPartName"
								name="DrawingNoMachinedPartName" class="input-xlarge">
								<option>----请先选择图号/加工件名称----</option>
								<c:forEach items="${machinedPartNameDrawingnoList}" var="mD"
									varStatus="your">
									<option>${mD.machinedPartNameDrawingno}</option>
								</c:forEach>
							</select>

						</div>
						<label class="col-sm-2 control-label" for="ds_password">工序*</label>
						<div class="col-sm-4">
							<select name="process" class="input-xlarge" id="process">
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
				</fieldset>
				<fieldset>
					<!--     <legend>选择相关表</legend>-->
					<div class="form-group">
						<label class="col-sm-2 control-label">设备编号*</label>
						<div class="col-sm-4">
							<input id="machineNo" name="machineNo" placeholder="设备编号"
								class="input-xlarge" type="text" value=""
								onkeyup="value=value.replace(/[^\w\/]/ig,'')">
						</div>
						<label class="col-sm-2 control-label">工作人员id*</label>
						<div class="col-sm-4">
							<input name="workid" placeholder="工号录入" id="workid" type="text"
								value="" onKeyUp="value=value.replace(/[^\w\.\/]/ig,'')"
								onafterpaste="value=value.replace(/[^\w\.\/]/ig,'')">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">预计完成时间(h)</label>
						<div class="col-sm-4">
							<input id="expectCompleteTime" name="expectCompleteTime"
								placeholder="预计完成时间" class="input-xlarge" type="text" value=""
								onKeyUp="value=value.replace(/\D/g,'')"
								onafterpaste="value=value.replace(/\D/g,'')">
						</div>
						<label class="col-sm-2 control-label">加工理由</label>
						<div class="col-sm-4">
							<input id="textinput-10" name="processingReasons"
								placeholder="加工理由" class="input-xlarge" type="text" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">工作内容</label>
						<div class="col-sm-4">
							<input id="textinput-10" name="workContent" placeholder="工作内容"
								class="input-xlarge" type="text" value="">
						</div>

					</div>


				</fieldset>

				<fieldset>
					<legend>时间采集数据</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label">开始时间</label>
						<div class="col-sm-4">
							<input id="startTime" name="startTime"
								placeholder="请输入开始加工的时间  比如 08:35" class="form-control"
								type="text" value="">
						</div>
						<label class="col-sm-2 control-label">工结束时间</label>
						<div class="col-sm-4">
							<input id="endTime" name="endTime"
								placeholder="请输入结束加工的时间 比如 09:34" class="form-control"
								type="text" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">总耗时</label>
						<div class="col-sm-4">
							<input id="consumingTime" name="consumingTime"
								class="form-control" type="text" value="" readonly="readonly">
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>其他数据</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label">实际完成数量</label>
						<div class="col-sm-4">
							<input id="textinput-7" name="actualCompletedQuantity"
								placeholder="实际完成数量" class="input-xlarge" type="text" value=""
								onKeyUp="value=value.replace(/\D/g,'')"
								onafterpaste="value=value.replace(/\D/g,'')">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">备注</label>
						<div class="col-sm-4">
							<textarea name="note" clos=",50" rows="3" warp="virtual"></textarea>
						</div>

					</div>
					<div class="form-group">
						<input id="employeeid" name="employeeid" class="input-xlarge"
							type="hidden" value="${employeeNumber}"> <input
							type="hidden" id="processName" value="">

					</div>

					<div class="form-group" style="text-align: center;">
						<button id="singlebutton-0" name="singlebutton-0"
							class="btn btn-primary">提交</button>
						<a href="javascript:history.go(-1);">后退</a>

					</div>
				</fieldset>


			</div>
		</form>
	</div>

	<script type="text/javascript">
$('#endTime').blur(function () {  
	var endTime=null;
	var startTime=null;
	
     endTime =$(this).val();
     startTime=document.getElementById("startTime").value;
     if(startTime!=null&&isTime(startTime)==false){
    	 alert("请输入正确格式的开始时间");
     }else{
    	 if(endTime!=null&&isTime(endTime)==false&&endTime!=""){
    		 alert("请输入正确格式的结束时间"); 
    	 }
    	 else{
     if(endTime==null||endTime==""||endTime.length==0){
    	 document.getElementById("consumingTime").value=""; 
     }
     else{
   	 if(endTime.length!=0&&startTime.length!=0){
   
   		 result=timeSubtraction(sqit(endTime),sqit(startTime));
    if(result!="结束时间大于开始时间"){
    document.getElementById('consumingTime').value=result;}
    else{
    	alert("结束时间大于开始时间");
     }
    }
    else{
    	//alert("为空");
    } }}}
});  


    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	$('.form_date').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
	
	function sqit(str){
		 var A=0;
		 var B=0;
		 var result=0;
		 var strs= new Array();
		 if(str!=null){
			 strs = str.split(':');
			 for (i=0;i<strs.length;i++) 
			 
			 {
			   
			     if(i==0){
			    	A=strs[i];
			    //	alert("A: "+A)
			     }
			     else if(i==1){
				    	B=strs[i];
				    //	alert("B: "+B)
				     }
			   } 
			
		  }
		 A=A*60;
		// alert("A:"+A);
		 result=parseInt(A)+parseInt(B);
		 return result;
	}
	
	function timeSubtraction(A,B){
		var result=0;
		result=A-B;
		if (result>0){
		var min=0;
		var hour=0;
		if(result>=60){
			hour=parseInt(result/60) ;
			if(hour<10){
				hour="0"+hour;
			}
			min=result%60;
			if(min==0){
				min="00";
			}
			else if(min<10){
				min="0"+min;
			}
		}
		else{
			hour="00";	
			min=result;
			if(min<10){
				min="0"+min;
			}
		}
	//	alert("hour"+hour);
	//	alert("min"+min);
		
		result=hour+":"+min
			return result;
		}
		else {
			return "结束时间大于开始时间";	
		}
	}
	function mouldonchange(obj){
		alert(obj.value);
		
	}
</script>
	<script type="text/javascript">  
    $(document).ready(function(){
      
        	$("#mouldidentifier").on("change",function(){
        	
        	var mould=document.getElementById("mouldidentifier").value;
        	//alert(mould);
        	 $("select[name=DrawingNoMachinedPartName]").empty(); 
            $.ajax({  
                type:'post',  
                url:'/EightFactory/GetDrawingno?mould='+mould,  
               // data:$("#myform").serialize(), 
               //	data: 
                cache:false,  
                dataType:'json',  
                success:function(data) {
				    	if(data.success)
				    	{
				    		 var mouldlessonList=data.mouldlessonList;
						    //alert("success");
						   for(var i=0; i<mouldlessonList.length; i++){
						  	 var option="<option value=\""+mouldlessonList[i].machinedPartNameDrawingno+"\"";  
						  	option +="  >"+mouldlessonList[i].machinedPartNameDrawingno+"</option>";  //动态添加数据  
						 // 	option +=" <c:if test="${l.DrawingNoMachinedPartName == mouldlessonList[i].machinedPartNameDrawingno}">selected</c:if>  >"+mouldlessonList[i].machinedPartNameDrawingno+"</option>";  //动态添加数据  
						    $("#DrawingNoMachinedPartName").append(option); 
						   }
						  //  if(mouldlessonList && mouldlessonList.length != 0){  
						   // 	 for(var i=0; i<mouldlessonList.length; i++)
						    //	 {  
						    	//	 var option="<option value=\""+modelList[i].drawingno+"\"";  
						    		//	option += ">"+modelList[i].drawingno+"</option>";  //动态添加数据  
					             //  	$("select[name=DrawingNoMachinedPartName]").append(option);  	
						    		 
						       //  }
						   
						   
				    		//}
						 //在控制台输出结果 
				    	 //   for(var i=0;i<mouldlessonList.length;i++){
				         //      console.log(mouldlessonList[i]);
				         //   }
				    	}
				    		else
				    		{
				    		//$("#modal-info .modal-title").text("查询成功！");
				    		//$("#modal-info .modal-body").text("原因:"+data.msg);
						    //$("#modal-info").modal();
				    		 alert("false");
				    		}
				    }
            });  
        });  
    });   
    
    function check(){
    	var DateTimePicking=document.getElementById("DateTimePicking").value;
    	var mouldidentifier=document.getElementById("mouldidentifier").value;
    	var artifactsNumber=document.getElementById("artifactsNumber").value;	
    	var DrawingNoMachinedPartName=document.getElementById("DrawingNoMachinedPartName").value;	
    	var process=document.getElementById("process").value;	
    	var machineNo=document.getElementById("machineNo").value;	
    	var workid=document.getElementById("workid").value;	
    	var expectCompleteTime=document.getElementById("expectCompleteTime").value;	
    	var artifactsNumber=document.getElementById("artifactsNumber").value;	
    	var startTime=document.getElementById("startTime").value;	
    	var endTime=document.getElementById("endTime").value;	
    	//alert("DateTimePicking:"+DateTimePicking+" mouldidentifier:"+mouldidentifier+" artifactsNumber:"+artifactsNumber+" DrawingNoMachinedPartName"+DrawingNoMachinedPartName+" process:"+process+" machineNo: "+machineNo+" workid: "+workid+" expectCompleteTime:"+expectCompleteTime+" artifactsNumber:"+artifactsNumber);
    //	if(DateTimePicking==""||DateTimePicking==null){
   // 		alert("请选择创建时间");
    //		 return false;  
   // 	}
   // 	else{
    		if(mouldidentifier==""||mouldidentifier==null){
        		alert("请选择模具型号");
        		 return false;  
        	}
    		else{
    			if(DrawingNoMachinedPartName==""||DrawingNoMachinedPartName==null||DrawingNoMachinedPartName=="-1"){
            		alert("请选择图号/加工件名称");
            		 return false;  
            	}
    			else{
    				if(artifactsNumber==""||artifactsNumber==null){
                		alert("请输入工件数量");
                		 return false;  
                	}
    				else{
    					if(process==""||process==null||process=="-1"){
                    		alert("请选择工序");
                    		 return false;  
                    	}
    					else{
                    		if(machineNo==""||machineNo==null){
                        		alert("请输入设备编号");
                        		 return false; 
                    		}
                        		 else{
                        			 if(workid==""||workid==null){
                                 		alert("请输入工作人员编号");
                                 		 return false; 
                        		 	}
                        			 else{
                        				 if(startTime==""||startTime==null){
                                      		alert("请输入开始时间");
                                      		 return false; 
                             		 	}
                        				 else{
                        					if(startTime!=null&&isTime(startTime)==false){
                        						alert('输入的开始时间参数不是时间格式'); 
                        						return false; 
                        				}
                        					else{
                                							//alert("自检结束");
                                							if(confirm("确认提交吗？")){  
                                		       				 	return true;  
                                		   						}  
                                		  			  			return false; 
                                						}
                                					}
                        							
    					
	  			  			
                        			 }
                        			}
                        		 }
    					}
    				}
    				
    			}
    //	}
    }
    
   
    function isTime(str)
    {
    //var a = str.match(/^(\d{1,2})(:)?(\d{1,2})$/);
  	//  var str=document.getElementById("Tony").value;
   // alert("str:"+str);
    var a=str.match(/^([01]\d|2[0-3]):[0-5]\d$/);
    	if (a == null) {
    	//	alert('输入的开始时间参数不是时间格式'); 
    	return false;
    	}
     
    return true;
    }
   
    
</script>

</body>
</html>


