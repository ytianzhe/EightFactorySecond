package com.org.eightfactory.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.eightfactory.entity.Mould;
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class MouldRegister
 */
@WebServlet("/MouldRegister")
public class MouldRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MouldRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//doGet(request, response);
		synchronized(this){
		try {
		request.setCharacterEncoding("utf-8");
		System.out.println("开始/更新注册");
		String mouldname = null;
		String drawingno = null;
		String MachinedPartName = null;
		String process = null;
		String mouldid=null;
		String machinedPartNameDrawingno=null;
		String EmployeeNumber=null;
		String msg=null;
			mouldid=request.getParameter("mouldid");
			mouldname=request.getParameter("mouldname");
			drawingno=request.getParameter("drawingno");
			MachinedPartName=request.getParameter("MachinedPartName");
//			EmployeeNumber=Integer.parseInt(request.getParameter("EmployeeNumber"));
			EmployeeNumber=request.getParameter("EmployeeNumber");
			process=request.getParameter("process");
			int processNumber= Integer.parseInt(process);
			
			machinedPartNameDrawingno=MachinedPartName+"/"+drawingno;
			System.out.println("mouldname: "+mouldname+"--drawingno:"+drawingno+"---MachinedPartName:"+MachinedPartName+"---EmployeeNumber: "+EmployeeNumber+"--process: "+process+" mouldid:"+mouldid);
			//确认是否重复
			
			// 没有重复开始更新
			Timestamp now = new Timestamp(System.currentTimeMillis());
			if(mouldid==null||mouldid==""){
			SqlMethods.insertmould(mouldname, machinedPartNameDrawingno, now, EmployeeNumber,processNumber);
			msg="添加成功";
			}
			else if(mouldid!=null&&mouldid.length()>0){
				//更新
				SqlMethods.UpdateMouldbyid(now, mouldid,mouldname,machinedPartNameDrawingno, process, EmployeeNumber);	
				msg="修改成功";
			}
			else{
				msg="数据异常";
			}
			request.setAttribute("msg", msg);
			
			//request.getRequestDispatcher("mouldRegister.jsp").forward(request, response);
			List<Mould> mouldlessonList= new ArrayList<Mould>();
	    	mouldlessonList=SqlMethods.SearchMoulddrawingNoAll();
	    	request.setAttribute("EmployeeNumber", EmployeeNumber);
	    	request.setAttribute("mouldlessonList", mouldlessonList);
	    	request.getRequestDispatcher("mouldShowPage.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}}
