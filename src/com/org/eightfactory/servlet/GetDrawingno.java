package com.org.eightfactory.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import com.org.eightfactory.entity.Mould;
import com.org.eightfactory.sql.SqlMethods;

/**
 * Servlet implementation class GetDrawingno
 */
@WebServlet("/GetDrawingno")
public class GetDrawingno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDrawingno() {
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
		String mould=null;
		int count=0;
		List<Mould> mouldlessonList= new ArrayList<Mould>();
		 Map<String, Object> map = new LinkedHashMap<String, Object>();
		mould=request.getParameter("mould");
		System.out.println(mould);
		try {
			if(mould!=null&&mould.length()>0){
				//判断数据库正常状态的改模具个数
			count=SqlMethods.Searchlogdatecount(mould);
				if(count!=0){
				//查询该模具对应的所有图号
				mouldlessonList=SqlMethods.SearchMouldbymould(mould);
				
				//返回成功
				
				 map.put("success", true);
				 map.put("mouldlessonList", mouldlessonList);
				 String json=JSON.toJSONString(map);  
				 System.out.println(json);//输出{"a":"aaa","b":"bbb","c":"ccc"}   
				 response.setContentType("text/javascript;charset=utf-8");
				 response.setCharacterEncoding("utf-8");
				 
				 response.getWriter().print(json);			
				}
				
			else{
				//数据库正常状态的改模具个数为0 
				//返回失败 然后要求修改模具数据库的状态
				 map.put("false", true);
				 map.put("msg", "数据库正常状态的该模具型号个数为0");
			  }
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
