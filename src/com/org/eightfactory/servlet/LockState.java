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

import com.org.eightfactory.entity.Employee;
import com.org.eightfactory.entity.Employee2;
import com.org.eightfactory.entity.Mould;
import com.org.eightfactory.entity.NewPrecisionMachiningLogStatistics;
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class LockState
 */
@WebServlet("/LockState")
public class LockState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LockState() {
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
		synchronized(this){
		
		String SearchCondition = "";	
		String logid=request.getParameter("logid");
		String employeeNumber=request.getParameter("employeeNumber");
		String method=request.getParameter("method");
		int state=Integer.parseInt(request.getParameter("state"));
		double row=10;
		double allsize = 0;
		String msg=null;
		Timestamp now = new Timestamp(System.currentTimeMillis()); 	
		System.out.println(logid);
		System.out.println(employeeNumber);
		System.out.println(state);
		System.out.println(method);
		try {
			switch (method) {
			case "employee":
				if(state!=0&&state==1){
					SqlMethods.UpdateLockLogState(Integer.parseInt(logid),employeeNumber,now,2);
					msg="加锁成功";
					}
					else if(state!=0&&state==2){
						SqlMethods.UpdateLockLogState(Integer.parseInt(logid),employeeNumber,now,1);
					msg="解锁成功";
					}
					else{
						//break;
						msg="解锁/加锁异常";	
					}
					List<NewPrecisionMachiningLogStatistics> lessonListLog= new ArrayList<NewPrecisionMachiningLogStatistics>();
					List<Mould> MouldList = new ArrayList<Mould>();
					List<Mould> DrawingnoList = new ArrayList<Mould>();
					List<Employee2> lessonList = new ArrayList<Employee2>();
					lessonList = SqlMethods.SearchUserInfo(employeeNumber);
					// System.out.println("权限："+lessonList.get(0).getLeader());
					String permission = lessonList.get(0).getLeader();
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
			//		MouldList = SqlMethods.SearchMouldAll();
			//		DrawingnoList = SqlMethods.SearchAllDrawingno();
					//lessonListLog=SqlMethods.SearchlogbyWorkid(Integer.parseInt(employeeNumber));
					//lessonListLog=SqlMethods.SearchlogbyWorkid(Integer.parseInt(employeeNumber),1,(int)row);
			//		lessonListLog = SqlMethods.SearchlogbyLeadergrouptype(Integer.parseInt(employeeNumber), 1, (int) row,"");
			//		allsize = SqlMethods.SearchlogdateCountbyleaderworkid(Integer.parseInt(employeeNumber),"");
					request.setAttribute("lessonListLog", lessonListLog);
					request.getSession().setAttribute("allsize", (int) allsize);
					request.getSession().setAttribute("row", (int) row);
					request.getSession().setAttribute("pageCount", (int) Math.ceil(allsize / row));
					request.getSession().setAttribute("curPage", 1);
					if (MouldList.size() > 0) {
						request.getSession().setAttribute("DrawingnoList", DrawingnoList);
						request.getSession().setAttribute("MouldList", MouldList);
					}
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("employeedate.jsp").forward(request, response);	
		    break;
		    	
			case "mould":
				System.out.println(logid+"---"+employeeNumber+"---"+state+"---"+method+"---");
				if(state!=0&&state==1){
				SqlMethods.UpdateLockMouldState(Integer.parseInt(logid),employeeNumber, now, 2);
				msg="禁用成功";
				}
				else if (state!=0&&state==2){
					SqlMethods.UpdateLockMouldState(Integer.parseInt(logid),employeeNumber, now, 1);	
					msg="启用成功";
				}
				List<Mould> mouldlessonList= new ArrayList<Mould>();
		    	mouldlessonList=SqlMethods.SearchMoulddrawingNoAll();
		    	request.setAttribute("mouldlessonList", mouldlessonList);
		    	request.setAttribute("employeeNumber", employeeNumber);
				request.getRequestDispatcher("mouldShowPage.jsp").forward(request, response);	
				
				break;
			case "loglock":
				System.out.println("开始loglock");
				System.out.println("logid："+logid);
				System.out.println("employeeNumber："+employeeNumber);
				System.out.println("state："+state);
				if(state==1){
					
				}
				else if(state==2){
					
				}
				else{
					
					
				}
				
				break;
			default:
				break;
			}
			//执行更新
			
			
			
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}}
