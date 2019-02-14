package com.org.eightfactory.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.eightfactory.entity.Mould;
import com.org.eightfactory.entity.NewPrecisionMachiningLogStatistics;
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class Editemployeedate
 */
@WebServlet("/Editemployeedate")
public class Editemployeedate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Editemployeedate() {
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
		//doGet(request, response);
		String  loginid=request.getParameter("loginid");
		System.out.println("loginid:"+loginid);
		try{
			if(loginid!=null){
		int dateid=Integer.parseInt(loginid);
		List<NewPrecisionMachiningLogStatistics> lessonListLog= new ArrayList<NewPrecisionMachiningLogStatistics>();
		lessonListLog=SqlMethods.Searchlogbyid(dateid);
			
		List<Mould> MouldList= new ArrayList<Mould>();
		MouldList=SqlMethods.SearchMouldAll();
		List<Mould> machinedPartNameDrawingnoList= new ArrayList<Mould>();
		machinedPartNameDrawingnoList=SqlMethods.SearchAllDrawingno();
		request.setAttribute("lessonListLog", lessonListLog);
		request.setAttribute("MouldList", MouldList);
		request.setAttribute("machinedPartNameDrawingnoList", machinedPartNameDrawingnoList);
//		request.getRequestDispatcher("/GridManager/demo/employeeEdit2.jsp").forward(request, response);
		request.getRequestDispatcher("employeeEdit2.jsp").forward(request, response);
		
		}
		else{
		//request.getRequestDispatcher("/GridManager/demo/employeeEdit2.jsp").forward(request, response);
		//	loginid为空
			
		}}catch(Exception e){
			e.printStackTrace();
		}
	}

}
