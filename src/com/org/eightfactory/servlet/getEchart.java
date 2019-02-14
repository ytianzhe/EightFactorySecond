package com.org.eightfactory.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.org.eightfactory.entity.Teamcountdata;
import com.org.eightfactory.entity.Teamdata;
import com.org.eightfactory.entity.Teamtype;
import com.org.eightfactory.sql.Sqlechart;
import com.org.eightfactory.until.AppMethods;

/**
 * Servlet implementation class getEchart
 */
@WebServlet("/getEchart")
public class getEchart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getEchart() {
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
	String method = request.getParameter("method");
	System.out.println(method);
	 Map<String, Object> map = new LinkedHashMap<String, Object>();
	 String json=null;
	 List<Teamtype> TeamtypelessonList= new ArrayList<Teamtype>();
	 List<Teamcountdata> TeamcountdatalessonList= new ArrayList<Teamcountdata>();
	
 try{
	switch(method){
	case  "lastweek":
		 map.put("success", true);
		// map.put("mouldlessonList", mouldlessonList);
		  json=JSON.toJSONString(map);  
		 System.out.println(json);//输出{"a":"aaa","b":"bbb","c":"ccc"}   
		 response.setContentType("text/javascript;charset=utf-8");
		 response.setCharacterEncoding("utf-8");
		 response.getWriter().print(json);		
		break;
	case "teamType":
			TeamtypelessonList=Sqlechart.SearchteamName();
			
			TeamcountdatalessonList=Sqlechart.Searchteamcountdatathisweek();
			
			for(int i=0;i<TeamtypelessonList.size();i++){
				System.out.println("getTeamName:"+TeamtypelessonList.get(i).getTeamName());	
			}
			
			for(int i=0;i<TeamcountdatalessonList.size();i++){
				System.out.println("TeamcountdatalessonList:"+TeamcountdatalessonList.get(i).getCount());	
			}
			
			String[] teamgroup =new String[12];
			for(int i=0;i<TeamtypelessonList.size();i++){
				teamgroup[i]=TeamtypelessonList.get(i).getTeamName();
				
			}
			
			int[] teamgroupthisweek =new int[12];
			for(int i=0;i<TeamcountdatalessonList.size();i++){
				teamgroupthisweek[i]=TeamcountdatalessonList.get(i).getCount();	
			}
			
			
		 	 map.put("success", true);
		 	// map.put("option", option);
		 	 map.put("teamgroupthisweek", teamgroupthisweek);
		 	 map.put("TeamcountdatalessonList", TeamcountdatalessonList);
		 	 map.put("data", teamgroup);
		 	// map.put("teamdata", teamdatesuzu);
			 map.put("teamtypeList", TeamtypelessonList);
			 json=JSON.toJSONString(map);  
			 System.out.println(json);
			 //输出{"a":"aaa","b":"bbb","c":"ccc"}   
			 response.setContentType("text/javascript;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 response.getWriter().print(json);		
			break;
	default :
		break;
	}
	 }catch (Exception e) {
			// TODO: handle exception
		 e.printStackTrace();
		}
	
	
	
	}

}
