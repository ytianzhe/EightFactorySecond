package com.org.eightfactory.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
public class Sqlite {

	public static List<Teamtype> SearchteamName() throws SQLException {
		//String name = null;
		List<Teamtype> lessonList= new ArrayList<Teamtype>();
		Connection conn = ConnectionFactory2.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {

			String sql = "SELECT d.value,d.name FROM employee  e  left join  dictionary d on d.value=e.grouptype where d.type='team' group by d.name   order by e.id";
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
	
	
	
	
	
	
	
	public static void main(String[] args) throws SQLException{
		List<Teamtype> lessonList= new ArrayList<Teamtype>();
		System.out.println(0);
		lessonList=SearchteamName();
		System.out.println(lessonList.size());
		for(int i =0;i<lessonList.size();i++){
			System.out.println(lessonList.get(i).getTeamName());
			
		}
		System.out.println(2);
	}
}
