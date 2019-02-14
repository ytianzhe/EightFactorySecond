package com.org.eightfactory.until;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.Statement;


public class ConnectionFactory3 {
	/**
	 * 连接数据库zhongkonghe
	 * @return
	 */
	
	public static void main(String[] arg){
	
		 Connection c = null;
		 java.sql.Statement stmt = null;	
		 
		 try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:E:/eightfactory.db");
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		    //  创建员工表
		      String sql = "CREATE TABLE employee1 " +
		                   "(ID  INTEGER PRIMARY KEY AUTOINCREMENT      NOT NULL," +
		                   " employeeNumber           INT   NOT NULL, " + 
		                   " password            CHAR(45)    NOT NULL, " + 
		                   " name        CHAR(45)  NOT NULL, " +
		      				" grouptype        INT NOT NULL, "  +
		      				" leader        INT NOT NULL, "  +
		      				" state        INT NOT NULL, "  +
		      				" designEngineer        INT NOT NULL, "  +
		      				" createtime        DATETIME NOT NULL, "  +
		      				" createid        INT , "  +
		      				" updatetime        DATETIME , "  +
		      				" updateid        INT , "  +
		      				" telphone        CHAR(45)  ) " ; 
		        
//		      String sql="CREATE TABLE mould " +
//	                   "(id  INTEGER PRIMARY KEY AUTOINCREMENT     NOT NULL," +
//	                   " mouldname           char(45)   NOT NULL, " + 
//	                   " machinedPartNameDrawingno            CHAR(45)    NOT NULL, " + 
//	                   " state       INT NOT NULL, " +
//	      				" createtime        DATETIME NOT NULL, "  +
//	      				" createid        INT NOT NULL, "  +
//	      				" updatetime        DATETIME , "  +
//	      				" updateid        INT , "  +
//	      				" process        CHAR(45) ) " ; 
//		      String sql="CREATE TABLE new_precision_machining_log_statistics " +
//		    		  "(ID INTEGER PRIMARY KEY AUTOINCREMENT     NOT NULL," +
//		    		  " createtime          DATETIME  	  NOT NULL, " + 
//		    		  " mouldidentifier       CHAR(45)    NOT NULL, " + 
//		    		  " DrawingNoMachinedPartName        CHAR(45) NOT NULL, " +
//		    		  " artifactsNumber        INT NOT NULL, "  +
//		    		  " process       		 CHAR(45) NOT NULL, "  +
//		    		  " machineNo        CHAR(45) NOT NULL, "  +
//		    		  " workerid        INT NOT NULL, "  +
//		    		  " processingReasons        CHAR(45) NOT NULL, "  +
//		    		  " workContent        CHAR(45)  ) " ; 
//		    		  " workContent        CHAR(45)  "  +
//		    	  " expectCompleteTime        INT NOT NULL, "  +
//		    		  " startTime         CHAR(45)  , "  +
//		    		  " endTime         CHAR(45)  , "  +
//		    		  " consumingTime       CHAR(45) , "  +
//		    		  " actualCompletedQuantity     INT  , "  +
//		    		  " note         CHAR(45) , "  +
//		    		  " state         INT NOT NULL , "  +
//		    		  " updateid         INT  , "  +
//		    		  " updatetime         DATETIME  ) " ; 
		    System.out.println(sql);		
	      			
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Table created successfully");
		 
		}

}
