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
import com.org.eightfactory.entity.NewPrecisionMachiningLogStatistics;
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class getMouldDate
 */
@WebServlet("/getMouldDate")
public class getMouldDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getMouldDate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String msg=null;
		try{
		String mouldid=request.getParameter("mouldid");
		String employeeNumber=request.getParameter("employeeNumber");
		System.out.println(mouldid);
		System.out.println(employeeNumber);
		if(mouldid!=null&&employeeNumber!=null&&mouldid.length()>0&&employeeNumber.length()>0){
			//优先判断这个id 有没有权限 是不是设计工程师傅
			
			
			//验证通过
			List<Mould> mouldlessonList= new ArrayList<Mould>();
//			mouldlessonList=SqlMethods.SearchMouldbymouldid(mouldid);
			mouldlessonList=SqlMethods.SearchMouldsqlitebymouldid(mouldid);
			String machinedPartNameDrawingno=mouldlessonList.get(0).getMachinedPartNameDrawingno();
			System.out.println("machinedPartNameDrawingno:"+machinedPartNameDrawingno);
			System.out.println("proces:"+mouldlessonList.get(0).getProcess());
			String[] aa=machinedPartNameDrawingno.split("/");
			for(int i=0;i<aa.length;i++){
				System.out.println(aa[i]);
			}
			mouldlessonList.get(0).setMachinedPartName(aa[0]);
			mouldlessonList.get(0).setDrawingno(aa[1]);
			
			msg="打开编辑页面成功";
			
			
//			for(int i=0;i<mouldlessonList.size();i++){
//				System.out.println("id： "+mouldlessonList.get(i).getId());
//				System.out.println("drawingno: "+mouldlessonList.get(i).getDrawingno());
//				System.out.println("MachinedPartName: "+mouldlessonList.get(i).getMachinedPartName());
//				System.out.println("process: "+mouldlessonList.get(i).getProcess());
//			}
			request.setAttribute("msg", msg);
			request.setAttribute("employeeNumber", employeeNumber);
			if(mouldlessonList!=null&&mouldlessonList.size()>0)
			{
			request.setAttribute("mouldlessonList", mouldlessonList);
			}
			request.getRequestDispatcher("mouldRegister.jsp").forward(request, response);
			}
			else{
				msg="提交的数据异常";
				request.getRequestDispatcher("mouldRegister.jsp").forward(request, response);
			}
			
		
	
		
		
		
		}catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		
		
	
	}

}
