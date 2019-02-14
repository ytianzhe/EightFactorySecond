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
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class OpenInsert
 */
@WebServlet("/OpenInsert")
public class OpenInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenInsert() {
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
		String employeeNumber=request.getParameter("employeeNumber");
		System.out.println(employeeNumber);
		try{
		if(employeeNumber!=null){
			
			List<Mould> MouldList= new ArrayList<Mould>();
			MouldList=SqlMethods.SearchMouldAll();
			request.setAttribute("MouldList", MouldList);
			request.setAttribute("employeeNumber", employeeNumber);
			request.getRequestDispatcher("employeeinsertdate.jsp").forward(request, response);
			
		}
		else{
			//login=null 是错误的情况 该用户非法登入系统 提示先登入
			
		}
		}
		catch(Exception e){e.printStackTrace();}
	}

}
