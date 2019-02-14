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
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class DesignEngineerEdit
 */
@WebServlet("/DesignEngineerEdit")
public class DesignEngineerEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DesignEngineerEdit() {
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
//		int employeeNumber= Integer.parseInt(request.getParameter("employeeNumber"));
		String employeeNumber= request.getParameter("employeeNumber");
		String msg=null;
		System.out.println("本次请求工号为 ："+employeeNumber);	
	    try{
	    	
	    	List<Employee2> lessonList= new ArrayList<Employee2>();
			lessonList=SqlMethods.SearchUserInfo(employeeNumber);
		//	System.out.println("权限："+lessonList.get(0).getLeader());
		    int designEngineer=lessonList.get(0).getDesignEngineer();
		    System.out.println("目前该角色的权限："+designEngineer);
		    if(designEngineer==1&&employeeNumber!=null&&employeeNumber!=""){
		    	//有访问权限  转入 编辑页面
		    	System.out.println("验证权限通过");
		    	List<Mould> mouldlessonList= new ArrayList<Mould>();
		    	mouldlessonList=SqlMethods.SearchMoulddrawingNoAll();
		    	request.setAttribute("mouldlessonList", mouldlessonList);
		    	request.setAttribute("employeeNumber", employeeNumber);
		    	
		    	//request.getRequestDispatcher("mouldRegister.jsp").forward(request, response); 重新定向志数据展现
		    	request.getRequestDispatcher("mouldShowPage.jsp").forward(request, response); 
		    	
		    	
		    }
		    else{
		    	System.out.println("验证权限异常,抛出错误信息");
		    	//没有访问权限
		    }
	    	
	    }catch(Exception e){
		e.printStackTrace();
	   }
		
		
		
		
	}

}
