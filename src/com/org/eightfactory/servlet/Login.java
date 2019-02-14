package com.org.eightfactory.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.eightfactory.entity.Employee;
import com.org.eightfactory.entity.Employee2;
import com.org.eightfactory.entity.Mould;
import com.org.eightfactory.entity.NewPrecisionMachiningLogStatistics;
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + "------" + password);

		//int employeeNumber = 0;
		String employeeNumber=null;
		int count = 0;
		double allsize = 0;
		double row = 10; // 分页的行数
		String SearchCondition = "";
		// String name = null;
		try {
			//用户名字使用string类型
			//employeeNumber = Integer.parseInt(username);
			employeeNumber=username;
			
			if (username != null && password != null) {

				// count=SqlMethods.SearchNameCount(employeeNumber);
				count = SqlMethods.SearchUser(employeeNumber, password);
				System.out.println("count: "+count);
				if (count == 0) {
					// 没有对应账号密码的账号 请输入正常的账号和密码
					request.setAttribute("msg", "请输入正确的账号密码");
					request.getRequestDispatcher("login2.jsp").forward(request, response);
				}
				else if(count>1){
					request.setAttribute("msg", "账号异常 该账号不唯一 不允许登入");
					request.getRequestDispatcher("login2.jsp").forward(request, response);
				}
				
				else if (count == 1) {
					// 比对 账号和密码 是否和数据库相同
					// name = SqlMethods.SearchName(employeeNumber);

					//List<Employee> lessonList = new ArrayList<Employee>();
					List<Employee2> lessonList = new ArrayList<Employee2>();
					lessonList = SqlMethods.SearchUserInfo(employeeNumber);
					// System.out.println("权限："+lessonList.get(0).getLeader());
					String permission = lessonList.get(0).getLeader();
					int designEngineer = lessonList.get(0).getDesignEngineer();
					int state = Integer.parseInt(lessonList.get(0).getState());
			//		String employeeName=lessonList.get(0).getName();
					if (state == 0) {
						request.setAttribute("msg", "对不起  你没有登入的权限.");
						request.getRequestDispatcher("login2.jsp").forward(request, response);
					} else {
						System.out.println("设计工程师 输入权限： " + designEngineer);
						List<Mould> MouldList = new ArrayList<Mould>();
						List<Mould> DrawingnoList = new ArrayList<Mould>();

						List<NewPrecisionMachiningLogStatistics> lessonListLog = new ArrayList<NewPrecisionMachiningLogStatistics>();
						System.out.println("permission: "+permission);
						switch (permission) {
						case "组员":
							lessonListLog = SqlMethods.SearchlogbyWorkid(employeeNumber, 1, (int) row);
							allsize = SqlMethods.Searchlogdatecountbyworkid(employeeNumber);
							break;
						case "组长":
							System.out.println("这tm是" + permission + "用点脑子 人家要看组员和自己");
							lessonListLog = SqlMethods.SearchlogbyLeadergrouptype(employeeNumber, 1, (int) row,SearchCondition);
							allsize = SqlMethods.SearchlogdateCountbyleaderworkid(employeeNumber,SearchCondition);
							MouldList = SqlMethods.SearchMouldAll();
							DrawingnoList = SqlMethods.SearchAllDrawingno();
							System.out.println(allsize);
							break;
						case "厂长":
							System.out.println("这tm是" + permission + "boss知道么 不用 干活的管理层");
							lessonListLog = SqlMethods.SearchAllLog(1, (int) row, "");
							allsize = SqlMethods.SearchlogdateAllCount(SearchCondition);
							MouldList = SqlMethods.SearchMouldAll();
							DrawingnoList = SqlMethods.SearchAllDrawingno();
							for (int i = 0; i < MouldList.size(); i++) {
								System.out.println(MouldList.get(i).getMouldname());
							}
							break;
						case "admin":
							System.out.println("这tm是神 放尊重点 代号： " + permission);
							break;
						default:
							// 返回错误
							break;
						}

						// List<NewPrecisionMachiningLogStatistics>
						// lessonListLog= new
						// ArrayList<NewPrecisionMachiningLogStatistics>();
						// lessonListLog=SqlMethods.SearchlogbyWorkid(employeeNumber,1,(int)row);

						// double
						// allsize=SqlMethods.Searchlogdatecountbyworkid(employeeNumber);

						// for(int i=0;i<lessonList.size();i++){
						// System.out.println(lessonList.get(0).getName());
						// }
						// System.out.println(name);
						if (MouldList.size() > 0) {
							request.getSession().setAttribute("DrawingnoList", DrawingnoList);
							request.getSession().setAttribute("MouldList", MouldList);
						}
						request.getSession().setAttribute("allsize", (int) allsize);
						request.getSession().setAttribute("row", (int) row);
						request.getSession().setAttribute("pageCount", (int) Math.ceil(allsize / row));
						request.getSession().setAttribute("curPage", 1);
						System.out.println((int) allsize);
						System.out.println((int) row);
						System.out.println((int) Math.ceil(allsize / row));

						request.getSession().setAttribute("employeeName", lessonList.get(0).getName());
						request.getSession().setAttribute("employeeNumber", employeeNumber);
						request.getSession().setAttribute("designEngineer", designEngineer);
						request.getSession().setAttribute("employeeGroupName", lessonList.get(0).getGroupTypeName());
						request.getSession().setAttribute("employeeleader", lessonList.get(0).getLeader());
						request.getSession().setAttribute("employeetelphone", lessonList.get(0).getTelphone());
						request.getSession().setAttribute("employeeid", lessonList.get(0).getId());

						request.getSession().setAttribute("lessonListLog", lessonListLog);
						request.getRequestDispatcher("/WEB-INF/employee.jsp").forward(request, response);
					}
				} else {

				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean canParseInt(String str) {

		if (str == null) {
			// 验证是否为空

			return false;

		}

		return str.matches("\\d+");
		// 使用正则表达式判断该字符串是否为数字，第一个\是转义符，\d+表示匹配1个或 //多个连续数字，"+"和"*"类似，"*"表示0个或多个

	}

}
