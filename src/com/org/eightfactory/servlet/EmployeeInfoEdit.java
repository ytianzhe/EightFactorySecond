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

import com.org.eightfactory.entity.Employee;
import com.org.eightfactory.entity.Employee2;
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class EmployeeInfoEdit
 */
@WebServlet("/EmployeeInfoEdit")
public class EmployeeInfoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeInfoEdit() {
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
		synchronized(this){
		// TODO Auto-generated method stub
		// doGet(request, response);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String msg = null;
		int designEngineerstate=0;
		int id=0;
		int state=0;
		List<Employee2> employeelessonList = new ArrayList<Employee2>();
		try {
			String method = request.getParameter("method");
			System.out.println(method);
			switch (method) {
			case "Search":
				System.out.println("开始验证权限是否可以进行人员管理");
				//int employeeNumber = Integer.parseInt(request.getParameter("employeeNumber"));
				String employeeNumber = request.getParameter("employeeNumber");
				System.out.println("employeeNumber:" + employeeNumber);

				List<Employee2> lessonList = new ArrayList<Employee2>();
				lessonList = SqlMethods.SearchUserInfo(employeeNumber);
				// System.out.println("权限："+lessonList.get(0).getLeader());
				String permission = lessonList.get(0).getLeader();
				System.out.println("目前该角色的权限：" + permission);
				if (permission.equals("厂长") && employeeNumber != null&&employeeNumber!="") {
					// 有访问权限 转入 编辑页面
					System.out.println("验证权限通过");
				//	List<Employee> employeelessonList = new ArrayList<Employee>();
					employeelessonList = SqlMethods.SearchAllEmployeeInfo();
					request.setAttribute("employeelessonList", employeelessonList);
					request.setAttribute("employeeNumber", employeeNumber);
					request.getRequestDispatcher("/WEB-INF/employeeInfo.jsp").forward(request, response);

				} else {
					System.out.println("验证权限异常,抛出错误信息");
					// 没有访问权限
				}

				break;
			case "ChangeEngineer":


				//employeeNumber = Integer.parseInt(request.getParameter("employeeNumber"));
				employeeNumber = request.getParameter("employeeNumber");
				designEngineerstate = Integer.parseInt(request.getParameter("designEngineerstate"));
			    id = Integer.parseInt(request.getParameter("id"));
				System.out.println(employeeNumber + "----" + designEngineerstate+"----id:"+id);
				
				if (designEngineerstate==1) {

					// 是设计工程师 解除职务
					System.out.println("解除");
					SqlMethods.UpdateDesignEngineerStatebyid(now, 0, id);
					msg = "解除成功";
				} else if (designEngineerstate==0){
					// 不是赋予设计工程师傅职务
					System.out.println("任命");
					SqlMethods.UpdateDesignEngineerStatebyid(now, 1, id);
					msg = "任命成功";
				}
				else{
					msg = "数据异常";
				}
				
				
				employeelessonList = SqlMethods.SearchAllEmployeeInfo();
				request.setAttribute("employeelessonList", employeelessonList);
				request.setAttribute("employeeNumber", employeeNumber);
				request.getRequestDispatcher("/WEB-INF/employeeInfo.jsp").forward(request, response);
				break;
			case "ChangeState":
				//employeeNumber = Integer.parseInt(request.getParameter("employeeNumber"));
				employeeNumber = request.getParameter("employeeNumber");
				state = Integer.parseInt(request.getParameter("state"));
			    id = Integer.parseInt(request.getParameter("id"));
				System.out.println(employeeNumber + "----" + designEngineerstate+"----id:"+id);
				//Timestamp now = new Timestamp(System.currentTimeMillis());
				if (state==1) {
					System.out.println("开始离职");
					SqlMethods.UpdateEmployeeStatebyid(now, 0, id);
					msg = "离职成功";
				} else if(state==0) {
					System.out.println("开始复职");
					SqlMethods.UpdateEmployeeStatebyid(now, 1, id);
					msg = "复职成功";
				}
				else{
					//数据异常 直接跳出
					msg="数据异常";
				}
		//		List<Employee> employeelessonList = new ArrayList<Employee>();
				employeelessonList = SqlMethods.SearchAllEmployeeInfo();
				request.setAttribute("employeelessonList", employeelessonList);
				request.setAttribute("employeeNumber", employeeNumber);
				request.getRequestDispatcher("/WEB-INF/employeeInfo.jsp").forward(request, response);
				break;
			default:
				//其他异常
				break;
			}

		} catch (Exception e) {
			// 异常
			e.printStackTrace();
		}

	}

}
}