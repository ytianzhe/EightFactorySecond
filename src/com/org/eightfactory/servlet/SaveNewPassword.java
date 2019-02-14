package com.org.eightfactory.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class SaveNewPassword
 */
@WebServlet("/SaveNewPassword")
public class SaveNewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNewPassword() {
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
		try{
			String employeetelNumber=request.getParameter("employeetelNumber");
			String newPassword=request.getParameter("newPassword");
			int id=SqlMethods.SearchidByemployeetelNumber(employeetelNumber);
			SqlMethods.UpdateemployeepasswordByid(newPassword, id);
			
			request.getSession().removeAttribute("employeeName");
			request.getSession().removeAttribute("EmployeeName");
			request.getSession().removeAttribute("mouldname");
			request.getSession().removeAttribute("drawingno");
			request.getSession().removeAttribute("employeeNumber");
			request.getSession().removeAttribute("curPage");
			request.getSession().removeAttribute("employeelessonList");
			
			request.setAttribute("msg","修改成功");
			response.sendRedirect("login2.jsp");
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
