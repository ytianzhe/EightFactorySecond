package com.org.eightfactory.servlet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.Feature;
import com.org.eightfactory.entity.NewPrecisionMachiningLogStatistics;
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class baowen
 */
@WebServlet("/baowen")
public class baowen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public baowen() {
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
		response.setContentType("text/html; charset=UTF-8");
		double row=10;
		//response.setCharacterEncoding("UTF-8"); 
		try{
		String id=request.getParameter("employeeid");
			if(id!=null){
		//int employeeid=Integer.parseInt(id);
		String employeeid=id;
		List<NewPrecisionMachiningLogStatistics> lessonListLog= new ArrayList<NewPrecisionMachiningLogStatistics>();
		lessonListLog=SqlMethods.SearchlogbyWorkid(employeeid, 1,(int)row);
		System.out.println(lessonListLog.get(0).getDrawingNoMachinedPartName());
		System.out.println(lessonListLog.get(0).getCreatetime());
		
		
//		JSONArray ja = JSONArray.parseArray(JSON.toJSONString(lessonListLog));
		
//		  String json = JSON.toJSONString(lessonListLog);
//		  PrintWriter out = response.getWriter();
//		  out.print(json);
//		JSONArray jsonArray2 = JSONArray.parseArray(JSON.toJSONString(lessonListLog));
////		JSONArray jsonArray2 = JSONArray.fromObject(lessonListLog);//将集合转换为json格式
//		String jsonString=jsonArray2.toString();//将jisn转换为字符串
//		response.getWriter().print(jsonString);//返回json信息
		 Map<String, Object> map = new LinkedHashMap<String, Object>();
		 
//		 map.put("page",1);
//		 map.put("total", lessonListLog.size());
//		 map.put("records",14);
//		 map.put("rows", lessonListLog);
		 
	
		 
		 
		 map.put("status", "success");
		 map.put("totals", lessonListLog.size());
		 map.put("data", lessonListLog);		
		 String json=JSON.toJSONString(map);  
		 System.out.println(json);//输出{"a":"aaa","b":"bbb","c":"ccc"}   
		 response.getWriter().print(json);
		    }
		}catch(Exception e){e.printStackTrace();}
		
		
		
	}

}
