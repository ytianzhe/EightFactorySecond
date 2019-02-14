package com.org.eightfactory.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginOut
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
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
	//	doGet(request, response);
		System.out.println("开始执行退出");
//		request.getSession().removeAttribute("username");
//		request.getSession().removeAttribute("password");
//		request.getSession().removeAttribute("msg");
//		request.getSession().removeAttribute("examiner");
//		request.getSession().removeAttribute("registermes");
//		request.getSession().removeAttribute("examinee");
		request.getSession().removeAttribute("employeeName");
		request.getSession().removeAttribute("EmployeeName");
		request.getSession().removeAttribute("mouldname");
		request.getSession().removeAttribute("drawingno");
		request.getSession().removeAttribute("employeeNumber");
		request.getSession().removeAttribute("curPage");
		request.getSession().removeAttribute("employeelessonList");

		
		
		
		
		/*解放session*/
		System.out.println("解放Session 退出");
		response.sendRedirect("login2.jsp");
		
	}

}
