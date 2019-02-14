package com.org.eightfactory.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.eightfactory.entity.NewPrecisionMachiningLogStatistics;
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class addemployeedate
 */
@WebServlet("/addemployeedate")
public class addemployeedate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addemployeedate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		synchronized (this) {
			
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		System.out.println();// new Date()为获取当前系统时间
	//	String createtime=request.getParameter("createtime");
		String logid=request.getParameter("logid");
	//	int employeeid=Integer.parseInt(request.getParameter("employeeid"));
		String employeeid=request.getParameter("employeeid");
		String mouldidentifier=request.getParameter("mouldidentifier");
		String DrawingNoMachinedPartName=request.getParameter("DrawingNoMachinedPartName");
		int artifactsNumber=Integer.parseInt(request.getParameter("artifactsNumber"));
		String process=request.getParameter("process");
		String machineNo=request.getParameter("machineNo");
		//int workid=Integer.parseInt(request.getParameter("workid"));
		String workid=request.getParameter("workid");
		
		String processingReasons=request.getParameter("processingReasons");
		String workContent=request.getParameter("workContent");
		int expectCompleteTime=Integer.parseInt(request.getParameter("expectCompleteTime"));
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		String consumingTime=request.getParameter("consumingTime");
		String actualCompletedQuantity=request.getParameter("actualCompletedQuantity");
		int state=1;
		double row=10;
		String note=request.getParameter("note");
		
		
		
		//System.out.println("创建时间:"+createtime);
		System.out.println("logid:"+logid);
		System.out.println("employeeid:"+employeeid);
		System.out.println("模具编号:"+mouldidentifier);
		System.out.println("图号(加工件名称):"+DrawingNoMachinedPartName);
		System.out.println("工件数量:"+artifactsNumber);
		System.out.println("工序:"+process);
		System.out.println("设备编号:"+machineNo);
		System.out.println("工人人员:"+workid);
		System.out.println("工件加工原因:"+processingReasons);
		System.out.println("工作内容:"+workContent);
		System.out.println("预计完成时间:"+expectCompleteTime);
		System.out.println("开始时间:"+startTime);
		System.out.println("结束时间:"+endTime);
		System.out.println("总耗时:"+consumingTime);
		System.out.println("实际完成数量:"+actualCompletedQuantity);
		System.out.println("状态:"+state);
		System.out.println("备注:"+note);
		
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		try {
			if(logid==null){
				SqlMethods.insertlogdateinfo(now,mouldidentifier,
				DrawingNoMachinedPartName,
				artifactsNumber,
				process,
				machineNo,
				workid,
				processingReasons,
				workContent,
				expectCompleteTime,
				startTime,endTime,consumingTime,1,note,state);
			//	request.getRequestDispatcher("/GridManager/demo/employeedate.jspp").forward(request, response);	
				//插入完成  查询新的数据 返回给页面
				List<NewPrecisionMachiningLogStatistics> lessonListLog= new ArrayList<NewPrecisionMachiningLogStatistics>();
				lessonListLog=SqlMethods.SearchlogbyWorkid(employeeid, 1,(int)row);
				request.setAttribute("lessonListLog", lessonListLog);
				request.getRequestDispatcher("employeedate.jsp").forward(request, response);	
			}
			
			else{
				int count=SqlMethods.Searchlogdate(Integer.parseInt(logid));
				if(count!=0)
					{
					//有此条数据时更新改数据
					SqlMethods.Updatelogbyid(mouldidentifier,
							DrawingNoMachinedPartName,
							artifactsNumber,
							process,
							machineNo,
							workid,
							processingReasons,
							workContent,
							expectCompleteTime,
							startTime,endTime,consumingTime,1,note,state,employeeid,now,Integer.parseInt(logid));
					
				//	request.getRequestDispatcher("/GridManager/demo/employeedate.jsp").forward(request, response);
					List<NewPrecisionMachiningLogStatistics> lessonListLog= new ArrayList<NewPrecisionMachiningLogStatistics>();
					lessonListLog=SqlMethods.SearchlogbyWorkid(employeeid,1,(int)row);
					request.setAttribute("lessonListLog", lessonListLog);
					request.getRequestDispatcher("employeedate.jsp").forward(request, response);
					}
				else	{
					//0 没有可以更新的数据 报错
						}
				}
			//(now,"C91ZXC123","9001-B镶片下型腔",1,"1",1,81205,"新做","a",8,now,now,now,1,"a",1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}}
