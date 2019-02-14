package com.org.eightfactory.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class RepeatCheck
 */
@WebServlet("/RepeatCheck")
public class RepeatCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepeatCheck() {
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
			
			 Map<String, Object> map = new LinkedHashMap<String, Object>();
			String method=request.getParameter("method");
			int count=0;
			int count2=0;
			switch(method){
			case "workid":
				String employeeNumber=request.getParameter("employeeNumber");
				String oldPassword=request.getParameter("oldPassword");
				//System.out.println(employeeNumber);
				 count=SqlMethods.SearchNameCount(employeeNumber);
				
				 count2= SqlMethods.SearchUser(employeeNumber, oldPassword);
				 if(count==1){
					 if(count2==1){
						 map.put("success", true);
					 	}
					 else{
						 map.put("msg","请输入正确的账号密码");  
						 map.put("false", true); 
					 }
					 }
				 else if(count==0){
					 map.put("msg","没有该工号");  
					 map.put("false", true); 
				 }
				 else{
					// map.put("msg", "数据异常");  
					 map.put("success", true);
				 }
					
				 break; 
			case "telphone":
				String employeetelNumber=request.getParameter("employeetelNumber");
				System.out.println("employeetelNumber: "+employeetelNumber);
				count=SqlMethods.SearchTelnumberCount(employeetelNumber);
				 if(count==1){
					 map.put("success", true);
					 map.put("employeetelNumber", employeetelNumber);
					 }
					 else if(count==0){
						 map.put("employeetelNumber", employeetelNumber);
						 map.put("false", true); 
					 }
					 else{
						 map.put("msg", "数据异常");  
					 }
				break;
			case "machinedPartNameDrawingno":
				String machinedPartNameDrawingno=request.getParameter("machinedPartNameDrawingno");
				System.out.println("machinedPartNameDrawingno: "+machinedPartNameDrawingno);
				count=SqlMethods.SearchmachinedPartNameDrawingnoCount(machinedPartNameDrawingno);
				System.out.println(count);
				if(count==1){
					 map.put("success", true);
				//	 map.put("employeetelNumber", employeetelNumber);
					 }
					 else if(count==0){
						 map.put("false", true); 
					 }
					 else{
						 map.put("msg", "数据异常");  
					 }
				break;
				 
			}
			 String json=JSON.toJSONString(map);  
			 System.out.println(json);//输出{"a":"aaa","b":"bbb","c":"ccc"}   
			 response.setContentType("text/javascript;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 response.getWriter().print(json);		
			}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
