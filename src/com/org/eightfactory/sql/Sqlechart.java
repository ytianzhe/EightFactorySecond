package com.org.eightfactory.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.org.eightfactory.entity.Teamcountdata;
import com.org.eightfactory.entity.Teamdata;
import com.org.eightfactory.entity.Teamtype;
import com.org.eightfactory.until.ConnectionFactory;
import com.org.eightfactory.until.ConnectionFactory2;

/**
* @author Tony
* @version 创建时间：2018年7月2日 下午4:36:10
* @ClassName 类名称
* @Description 类描述
*/
public class Sqlechart {

	public static List<Teamtype> SearchteamName() throws SQLException {
		//String name = null;
		List<Teamtype> lessonList= new ArrayList<Teamtype>();
		Connection conn = ConnectionFactory2.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {

			String sql = "SELECT d.value,d.name FROM employee  e  left join  dictionary d on d.value=e.grouptype where d.type='team' group by  d.name   order by e.id";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Teamtype tt =new Teamtype();
				tt.setId(rs.getInt(1));
				tt.setTeamName(rs.getString(2));
				lessonList.add(tt);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  lessonList;
		//return name;
	}
	public static List<Teamcountdata>Searchteamcountdatathisweek() throws SQLException {
		//String name = null;
		List<Teamcountdata> lessonList= new ArrayList<Teamcountdata>();
		Connection conn = ConnectionFactory2.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			
		//	String sql = "select count(*) from (SELECT DATE_FORMAT(npml.createtime,'%Y-%m-%d') as weektime , d.name, e.grouptype, npml.* FROM eight_factory.`new_precision_machining_log _statistics`  npml left join employee e on e.employeeNumber =npml.workerid  left join dictionary d on d.value= e.grouptype where d.type='team' and d.value=1 and YEARWEEK(date_format(npml.createtime,'%Y-%m-%d')) = YEARWEEK(now())) as teamdate group by teamdate.weektime";
		//	String sql2="select ifnull(date.TeamC ,0) as count ,d2.name from dictionary d2 left join (select T.grouptype as grouptype,d.name as dateName,count(*) as TeamCfrom (select npmls.* ,e.grouptype from new_precision_machining_log_statistics npmls left join employee e on e.employeeNumber=npmls.workerid where npmls.createtime>=datetime('now','start of day','-7 day','weekday 1') AND npmls.createtime<datetime('now','start of day','+0 day','weekday 1') ) as T   left join dictionary d on d.value=T.grouptype where d.type='team' order by T.grouptype) as date  on d2.name=date.dateName where d2.type='team' ";
			String sql3="select ifnull(date.TeamC ,0) as count ,d2.name from dictionary d2 left join ( select T.grouptype as grouptype,d.name as dateName,count(*) as TeamC from ( select npmls.* ,e.grouptype from new_precision_machining_log_statistics npmls left join employee e on e.employeeNumber=npmls.workerid  where npmls.createtime  between datetime(date(datetime('now',strftime('-%w day','now'))),'+86400 second')  and datetime(date(datetime('now',(6 - strftime('%w day','now'))||' day','1 day')),'+86400 second')) as T left join dictionary d on d.value=T.grouptype where d.type='team' group by T.grouptype) as date  on d2.name=date.dateName where d2.type='team'";
			System.out.println(sql3);
			rs = stmt.executeQuery(sql3);
			while (rs.next()) {
				Teamcountdata tc =new Teamcountdata();
				tc.setCount(rs.getInt(1));
				tc.setName(rs.getString(2));
				lessonList.add(tc);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  lessonList;
		//return name;
	}
	

	
	
	
	
	
	
	
	public static void main(String[] args) throws SQLException{
//		List<Teamtype> lessonList= new ArrayList<Teamtype>();
//		lessonList=SearchteamName();
//		for(int i =0;i<lessonList.size();i++){
//			System.out.println(lessonList.get(i).getTeamName());
//			
//		}
		

	
	}
}
