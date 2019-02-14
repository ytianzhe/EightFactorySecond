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
import com.org.eightfactory.entity.NewPrecisionMachiningLogStatistics;
import com.org.eightfactory.sql.SqlMethods;
import com.org.eightfactory.until.AppMethods;

/**
 * Servlet implementation class Fenye
 */
@WebServlet("/Fenye")
public class Fenye extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fenye() {
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
		request.setCharacterEncoding("UTF-8");
		int curPage=1;
		if(request.getParameter("curPage")!=null&&request.getParameter("curPage").length()>0){
		curPage=Integer.parseInt(request.getParameter("curPage"));}
		String employeeNumber=null;
		//int employeeNumber=0;
		if(request.getParameter("employeeNumber")!=null&&request.getParameter("employeeNumber").length()>0){
		employeeNumber=request.getParameter("employeeNumber");}
		int pageCount=0;
		if(request.getParameter("pageCount")!=null&&request.getParameter("pageCount").length()>0){
		pageCount=Integer.parseInt(request.getParameter("pageCount"));}
		String method=null;method= request.getParameter("method");
		
		String EmployeeName=request.getParameter("EmployeeName");
		String mouldname=request.getParameter("mouldname");
		String drawingno=request.getParameter("drawingno");
//		String row=request.getParameter("row");
//		int row=Integer.parseInt(request.getParameter("row"));
		double row=10;
		double allsize=0;
		String SearchCondition="";
		System.out.println("当前第 "+curPage+" 页");
		System.out.println("一共："+pageCount+"页");
		System.out.println("当前人员 id:"+employeeNumber);
		System.out.println("当前获取执行的方法:"+method);
		System.out.println("EmployeeName: "+EmployeeName);
		System.out.println("mouldname: "+mouldname);
		System.out.println("drawingno: "+drawingno);
		
		
			
			if(method!=null&&method.length()>0){
			switch(method){
			case "first":
				System.out.println("执行的动作： "+method);
				curPage=1;
				if(EmployeeName!=null&&EmployeeName.length()>0){
					SearchCondition	=AppMethods.AppendSql("EmployeeName",EmployeeName,SearchCondition);
					}
					if(mouldname!=null&&mouldname.length()>0&&mouldname.equals("-1")==false){
					SearchCondition=AppMethods.AppendSql("mouldname",mouldname,SearchCondition);
					}
					if(drawingno!=null&&drawingno.length()>0&&drawingno.equals("-1")==false){
					SearchCondition=AppMethods.AppendSql("drawingno",drawingno,SearchCondition);
					}
					System.out.println("SearchCondition: "+SearchCondition);
				break;
				
			case "up":
				System.out.println("执行的动作： "+method);
				if(curPage>1&&curPage<=pageCount)
				{
				curPage=curPage-1;
				}
				if(EmployeeName!=null&&EmployeeName.length()>0){
					SearchCondition	=AppMethods.AppendSql("EmployeeName",EmployeeName,SearchCondition);
					}
					if(mouldname!=null&&mouldname.length()>0&&mouldname.equals("-1")==false){
					SearchCondition=AppMethods.AppendSql("mouldname",mouldname,SearchCondition);
					}
					if(drawingno!=null&&drawingno.length()>0&&drawingno.equals("-1")==false){
					SearchCondition=AppMethods.AppendSql("drawingno",drawingno,SearchCondition);
					}
					System.out.println("SearchCondition: "+SearchCondition);
				break;
				
			case "down":
				System.out.println("执行的动作： "+method);
				if(curPage>=1&&curPage<pageCount)
				{
					curPage=curPage+1;
				}
				if(EmployeeName!=null&&EmployeeName.length()>0){
					SearchCondition	=AppMethods.AppendSql("EmployeeName",EmployeeName,SearchCondition);
					}
					if(mouldname!=null&&mouldname.length()>0&&mouldname.equals("-1")==false){
					SearchCondition=AppMethods.AppendSql("mouldname",mouldname,SearchCondition);
					}
					if(drawingno!=null&&drawingno.length()>0&&drawingno.equals("-1")==false){
					SearchCondition=AppMethods.AppendSql("drawingno",drawingno,SearchCondition);
					}
					System.out.println("SearchCondition: "+SearchCondition);
				break;
				
			case "last":
				System.out.println("执行的动作： "+method);
				if(curPage>=1&&curPage<pageCount)
				{
					curPage=pageCount;
				}
				if(EmployeeName!=null&&EmployeeName.length()>0){
					SearchCondition	=AppMethods.AppendSql("EmployeeName",EmployeeName,SearchCondition);
					}
					if(mouldname!=null&&mouldname.length()>0&&mouldname.equals("-1")==false){
					SearchCondition=AppMethods.AppendSql("mouldname",mouldname,SearchCondition);
					}
					if(drawingno!=null&&drawingno.length()>0&&drawingno.equals("-1")==false){
					SearchCondition=AppMethods.AppendSql("drawingno",drawingno,SearchCondition);
					}
					System.out.println("SearchCondition: "+SearchCondition);
				break;
			
			case "Search":
				System.out.println("执行的动作： "+method);
				if(EmployeeName!=null&&EmployeeName.length()>0){
				SearchCondition	=AppMethods.AppendSql("EmployeeName",EmployeeName,SearchCondition);
				}
				if(mouldname!=null&&mouldname.length()>0&&mouldname.equals("-1")==false){
				SearchCondition=AppMethods.AppendSql("mouldname",mouldname,SearchCondition);
				}
				if(drawingno!=null&&drawingno.length()>0&&drawingno.equals("-1")==false){
				SearchCondition=AppMethods.AppendSql("drawingno",drawingno,SearchCondition);
				}
				System.out.println("SearchCondition: "+SearchCondition);
				break;
				default:
					System.out.println("执行的动作： "+"NULL");	
				break;
			  }
			}
			else if(method==null){
				if(EmployeeName!=null&&EmployeeName.length()>0){
					SearchCondition=AppMethods.AppendSql("EmployeeName", EmployeeName, SearchCondition);
				}
				if(mouldname!=null&&mouldname.length()>0&&mouldname.equals("-1")==false){
					SearchCondition=AppMethods.AppendSql("mouldname", mouldname, SearchCondition);
				}
				if(drawingno!=null&&drawingno.length()>0&&drawingno.equals("-1")==false){
					SearchCondition=AppMethods.AppendSql("drawingno", drawingno, SearchCondition);
				}
				System.out.println("SearchCondition: " +SearchCondition);
			}
			
			List<Employee2> lessonList= new ArrayList<Employee2>();
			lessonList=SqlMethods.SearchUserInfo(employeeNumber);
		//	System.out.println("权限："+lessonList.get(0).getLeader());
		    String permission=lessonList.get(0).getLeader();
			
		    List<NewPrecisionMachiningLogStatistics> lessonListLog= new ArrayList<NewPrecisionMachiningLogStatistics>();
		    switch (permission) {
			case "组员":
				lessonListLog=SqlMethods.SearchlogbyWorkid(employeeNumber,curPage,(int)row);
				allsize=SqlMethods.Searchlogdatecountbyworkid(employeeNumber);
				pageCount=(int)Math.ceil(allsize/row);
				break;
			case "组长":
				System.out.println("这tm是"+permission+"用点脑子 人家要看组员和自己");
				//System.out.println("curPage: "+curPage);
				//curPage=0;
				System.out.println("开始输出输入的数据：");
				System.out.println(employeeNumber);
				System.out.println(curPage);
				System.out.println((int)row);
				System.out.println(SearchCondition);
				//SearchCondition="";
				lessonListLog=SqlMethods.SearchlogbyLeadergrouptype(employeeNumber,curPage,(int)row,SearchCondition);
				allsize=SqlMethods.SearchlogdateCountbyleaderworkid(employeeNumber,SearchCondition);
				pageCount=(int)Math.ceil(allsize/row);
				System.out.println(allsize);
				System.out.println(pageCount);
				
				break;
			case "厂长":
				System.out.println("这tm是"+permission+"boss知道么 不用 干活的管理层");
				lessonListLog=SqlMethods.SearchAllLog(curPage,(int)row,SearchCondition);
				allsize=SqlMethods.SearchlogdateAllCount(SearchCondition);
				pageCount=(int)Math.ceil(allsize/row);
				break;
			case "admin":
				System.out.println("这tm是神 放尊重点 代号： "+permission);
				pageCount=(int)Math.ceil(allsize/row);
				break;
			default:
			//返回错误
				pageCount=(int)Math.ceil(allsize/row);
				break;
			}
			
			
			
		
		for(int i=0;i<lessonListLog.size();i++){
    	  System.out.println("id:"+lessonListLog.get(i).getId()+"---------"+"createtime"+lessonListLog.get(i).getCreatetime()); 
       }
		
		if(EmployeeName!=null&&EmployeeName.length()>0){
			
			request.setAttribute("EmployeeName",EmployeeName);
		}
		if(mouldname!=null&&mouldname.length()>0){
			
			request.setAttribute("mouldname",mouldname);
		}
		if(drawingno!=null&&drawingno.length()>0){
			
			request.setAttribute("drawingno",drawingno);
		}
		
	   request.getSession().setAttribute("allsize",(int)allsize);
       request.getSession().setAttribute("lessonListLog",lessonListLog);
       
      // request.getSession().setAttribute("pageCount",(int)Math.ceil(allsize/row));
       request.getSession().setAttribute("pageCount",pageCount);
       
       request.setAttribute("employeeNumber",employeeNumber);
       request.setAttribute("curPage",curPage);
       request.getRequestDispatcher("employeedate.jsp").forward(request, response);
      
       
       
       
//		request.getSession().setAttribute("lessonListLog",lessonListLog);
//		request.getRequestDispatcher("employeedate.jsp").forward(request, response);
//		List<NewPrecisionMachiningLogStatistics> lessonListLog= new ArrayList<NewPrecisionMachiningLogStatistics>();
//		lessonListLog=SqlMethods.SearchlogbyWorkid(Integer.parseInt(employeeNumber), Integer.parseInt(curPage), 10);
//		
//		double allsize=SqlMethods.Searchlogdatecountbyworkid(Integer.parseInt(employeeNumber));
//		System.out.println((int)allsize+"----"+(int)Math.ceil(allsize/row)+"----"+(int)row+"-----"+Integer.parseInt(curPage));
//		request.getSession().setAttribute("allsize",(int)allsize);
//		request.getSession().setAttribute("pageCount",(int)Math.ceil(allsize/row));
//		request.getSession().setAttribute("row",(int)row);
//		request.getSession().setAttribute("curPage", Integer.parseInt(curPage));
//		request.setAttribute("employeeNumber",employeeNumber);
//		request.getSession().removeAttribute("lessonListLog");
//		request.getSession().setAttribute("lessonListLog",lessonListLog);
//										   
//		request.getRequestDispatcher("employeedate.jsp").forward(request, response);
			
		
				
		}catch(Exception e){e.printStackTrace();}
	}

}
