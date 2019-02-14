package com.org.eightfactory.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class BindCellPhoneNumber
 */
@WebServlet("/BindCellPhoneNumber")
public class BindCellPhoneNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BindCellPhoneNumber() {
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
			
			String employeeNumber= request.getParameter("employeeNumber");
			String employeeid= request.getParameter("employeeid");
			System.out.println("employeeNumber:"+employeeNumber);
			System.out.println("employeeid:"+employeeid);
			
			String tel=SqlMethods.SearchtelNumberByemployeeNumber(employeeNumber);
			if(tel==""||tel==null||tel.length()==0){
				System.out.println("tel:"+tel+"，未找到手机号 可以绑定");
				request.setAttribute("employeeNumber", employeeNumber);
				request.setAttribute("employeeid", employeeid);
				request.getRequestDispatcher("mobilephonebinding.jsp").forward(request, response);
				
			}
			else{
				
				System.out.println("tel:"+tel+"，有手机号可以编辑");
				request.setAttribute("employeetelNumber", tel);
				request.setAttribute("employeeNumber", employeeNumber);
				request.setAttribute("employeeid", employeeid);
			request.getRequestDispatcher("mobilephonebinding.jsp").forward(request, response);
			
			}
			
			
		}catch ( Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
