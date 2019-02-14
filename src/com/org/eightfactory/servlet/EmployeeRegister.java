package com.org.eightfactory.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class EmployeeRegister
 */
@WebServlet("/EmployeeRegister")
public class EmployeeRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("utf-8");

		System.out.println("开始注册");
		String employeeName = null;
		String Number = null;
		String grouptype = null;
		String leaderPermission = null;
		int leader = 0;
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			employeeName = request.getParameter("employeeName");
			Number = request.getParameter("employeeNumber");
			grouptype = request.getParameter("grouptype");
			leaderPermission = request.getParameter("leaderPermission");
			System.out.println("employeeName:" + employeeName);
			System.out.println("employeeNumber: " + Number);
			System.out.println("grouptype: " + grouptype);
			System.out.println("leaderPermission: " + leaderPermission);

			int EmployeeNumber = Integer.parseInt(Number);
			int grouptypeNumber = Integer.parseInt(grouptype);
			if (leaderPermission != null && leaderPermission.length() > 0) {
				if (leaderPermission.equals("on")) {
					leader = 1;
				}
			}
			// 查询新的工号是否重复

			// 没有重复的话就进行注册
			SqlMethods.inserEmployee(EmployeeNumber, "123456", employeeName, grouptypeNumber, leader, now);
			String msg = "注册成功";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("login2.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
