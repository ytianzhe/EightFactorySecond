package com.org.eightfactory.sql;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.org.eightfactory.entity.Employee;
import com.org.eightfactory.entity.Employee2;
import com.org.eightfactory.entity.Mould;
import com.org.eightfactory.entity.NewPrecisionMachiningLogStatistics;
import com.org.eightfactory.until.ConnectionFactory;
import com.org.eightfactory.until.ConnectionFactory2;




/**
* @author Tony
* @version 创建时间：2018年6月1日 下午3:13:30
* @ClassName 类名称
* @Description 类描述
*/
public class SqlMethods {
   
	public static String SearchName(int employeeNumber) throws SQLException {
		String name = null;
		Connection conn = ConnectionFactory2.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {

			String sql = "select e.name from  employee e where employeeNumber="+employeeNumber;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				name = rs.getString(1);
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

		return name;
	}
	
	
	
	public static int SearchNameCount(String employeeNumber) throws SQLException {
		int Count=0;
	//	String name = null;
		Connection conn = ConnectionFactory2.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {

			String sql = "select count(1) from  employee e where employeeNumber='"+employeeNumber+"'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Count = rs.getInt(1);
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

		return Count;
	}
	
	//判断用户是否存在
	public static int SearchUser(String employeeNumber,String password) throws SQLException{
		Connection conn= ConnectionFactory2.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs= null;
		int count=0;
		try{
			String sql="select count(1) from employee  e where e.employeeNumber= '"+employeeNumber+"' and e.password='"+password+"'";
			//System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				count=rs.getInt(1);
			}
		}catch(Exception e){e.printStackTrace();}
		
		return count; 
	}
	//判断该工作日志是否存在
		public static int Searchlogdate(int loginid) throws SQLException{
			Connection conn= ConnectionFactory2.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs= null;
			int count=0;
			try{
				String sql="select count(1) from employee where id= "+loginid;
				//System.out.println(sql);
				rs=stmt.executeQuery(sql);
				while(rs.next()){
					count=rs.getInt(1);
				}
			}catch(Exception e){e.printStackTrace();}
			
			return count; 
		}
	
	
	//获取员工信息
	//判断用户是否存在
		public static List<Employee2> SearchUserInfo(String employeeNumber) throws SQLException{
			List<Employee2> lessonList= new ArrayList<Employee2>();
			Connection conn= ConnectionFactory2.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs= null;
			//int count=0;
			try{
				String sql="select e.employeeNumber,e.name,dy.name  as grouptypename ,dyl.name as permission,e.designEngineer,e.state,e.telphone,e.id from employee e left join dictionary dy on e.grouptype=dy.value left join dictionary dyl on e.leader=dyl.value where dy.type='team'  and dyl.type='leader' and e.employeeNumber='"+employeeNumber+"'";
				//System.out.println(sql);
				rs=stmt.executeQuery(sql);
				while(rs.next()){
					Employee2 em =new Employee2();
					em.setEmployeeNumber(rs.getString(1));
					em.setName(rs.getString(2));
					em.setGroupTypeName(rs.getString(3));
					em.setLeader(rs.getString(4));
					em.setDesignEngineer(rs.getInt(5));
					em.setState(rs.getString(6));
					em.setTelphone(rs.getString(7));
					em.setId(rs.getInt(8));
					lessonList.add(em);
				}
			}catch(Exception e){e.printStackTrace();}
			
			return lessonList; 
		}
		
		//根据id搜索员工的工作记录
		
		public static List<NewPrecisionMachiningLogStatistics> SearchlogbyWorkid(String employeeNumber,int curPage,int row) throws SQLException{
			List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
			Connection conn= ConnectionFactory2.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs= null;
			
			curPage=(curPage-1)*row;
			try{
				//String sql="SELECT npmls.*,e.name FROM eight_factory.`new_precision_machining_log _statistics` npmls left join employee e on npmls.workerid=e.employeeNumber where workerid="+employeeNumber+" order by npmls.createtime desc limit "+curPage+","+row;
				String sql2="SELECT npmls.*,e.name,d.name FROM `new_precision_machining_log_statistics` npmls left join employee e on npmls.workerid=e.employeeNumber left join dictionary d on d.value=npmls.process where workerid='"+employeeNumber+"'and d.type='process' order by npmls.createtime desc limit "+curPage+","+row;

				
				System.out.println(sql2);
				rs=stmt.executeQuery(sql2);
				while(rs.next()){
					NewPrecisionMachiningLogStatistics npmls = new NewPrecisionMachiningLogStatistics();
					npmls.setId(rs.getInt(1));
					npmls.setCreatetime(rs.getTimestamp(2));
					npmls.setMouldidentifier(rs.getString(3));
					npmls.setDrawingNoMachinedPartName(rs.getString(4));
					npmls.setArtifactsNumber(rs.getInt(5));
					npmls.setProcess(rs.getString(6));
					npmls.setMachineNo(rs.getString(7));
					npmls.setWorkerid(rs.getString(8));
					npmls.setProcessingReasons(rs.getString(9));
					npmls.setWorkContent(rs.getString(10));
					npmls.setExpectCompleteTime(rs.getInt(11));
					npmls.setStartTime(rs.getString(12));
					npmls.setEndTime(rs.getString(13));
					npmls.setConsumingTime(rs.getString(14));
					npmls.setActualCompletedQuantity(rs.getInt(15));
					npmls.setNote(rs.getString(16));
					npmls.setState(rs.getInt(17));
					npmls.setName(rs.getString(20));
					npmls.setProcessName(rs.getString(21));				
					lessonList.add(npmls);
				}
			}catch(Exception e){e.printStackTrace();}
			
			return lessonList; 
		}
		
		public static List<NewPrecisionMachiningLogStatistics> SearchlogbyName(String employeeName) throws SQLException{
			List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
			Connection conn= ConnectionFactory2.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs= null;
			//int count=0;
			try{
				String sql="SELECT npmls.*,e.name FROM `new_precision_machining_log_statistics` npmls left join employee e on npmls.workerid=e.employeeNumber where name='"+employeeName+"'";
				System.out.println(sql);
				rs=stmt.executeQuery(sql);
				while(rs.next()){
					NewPrecisionMachiningLogStatistics npmls = new NewPrecisionMachiningLogStatistics();
					npmls.setId(rs.getInt(1));
					npmls.setCreatetime(rs.getTimestamp(2));
					npmls.setMouldidentifier(rs.getString(3));
					npmls.setDrawingNoMachinedPartName(rs.getString(4));
					npmls.setArtifactsNumber(rs.getInt(5));
					npmls.setProcess(rs.getString(6));
					npmls.setMachineNo(rs.getString(7));
					npmls.setWorkerid(rs.getString(8));
					npmls.setProcessingReasons(rs.getString(9));
					npmls.setWorkContent(rs.getString(10));
					npmls.setExpectCompleteTime(rs.getInt(11));
					npmls.setStartTime(rs.getString(12));
					npmls.setEndTime(rs.getString(13));
					npmls.setConsumingTime(rs.getString(14));
					npmls.setActualCompletedQuantity(rs.getInt(15));
					npmls.setNote(rs.getString(16));
					npmls.setState(rs.getInt(17));
					npmls.setName(rs.getString(19));
					lessonList.add(npmls);
				}
			}catch(Exception e){e.printStackTrace();}
			
			return lessonList; 
		}
		
		
		
	//插入用户数据
		public static void  insertlogdateinfo(Timestamp createtime,String mouldidentifier,String DrawingNoMachinedPartName,int artifactsNumber,String process,String machineNo,String workerid,String processingReasons,String workContent,int expectCompleteTime,String startTime,String endTime,String consumingTime,int actualCompletedQuantity,String note,int state) throws SQLException{
			
			Connection conn= ConnectionFactory2.makeConnection();
			Statement stmt = conn.createStatement();
			//ResultSet rs= null;
			//int count=0;
			try{
				String sql="INSERT INTO `new_precision_machining_log_statistics` (createtime,mouldidentifier,DrawingNoMachinedPartName,artifactsNumber,process,machineNo,workerid,processingReasons,workContent,expectCompleteTime,startTime,endTime,consumingTime,actualCompletedQuantity,note,state)"
			+"VALUES ('"+createtime+"','"+mouldidentifier+"','"+DrawingNoMachinedPartName+"',"+artifactsNumber+",'"+process+"','"+machineNo+"','"+workerid+"','"+processingReasons+"','"+workContent+"',"+expectCompleteTime+",'"+startTime+"','"+endTime+"','"+consumingTime+"',"+actualCompletedQuantity+",'"+note+"',"+state+")";
				System.out.println(sql);
				stmt.execute(sql);
			
			}catch(Exception e){e.printStackTrace();}
			
			
		}
		//读取编辑页的工作信息
		public static List<NewPrecisionMachiningLogStatistics> Searchlogbyid(int id) throws SQLException{
			List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
			Connection conn= ConnectionFactory2.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs= null;
			//int count=0;
			try{
				String sql="SELECT npmls.*,e.name,d.name FROM `new_precision_machining_log_statistics` npmls left join employee e on npmls.workerid=e.employeeNumber left join dictionary d on d.value=npmls.process where npmls.id="+id+" and d.type='process'";
				System.out.println(sql);
				rs=stmt.executeQuery(sql);
				while(rs.next()){
					NewPrecisionMachiningLogStatistics npmls = new NewPrecisionMachiningLogStatistics();
					npmls.setId(rs.getInt(1));
					npmls.setCreatetime(rs.getTimestamp(2));
					npmls.setMouldidentifier(rs.getString(3));
					npmls.setDrawingNoMachinedPartName(rs.getString(4));
					npmls.setArtifactsNumber(rs.getInt(5));
					npmls.setProcess(rs.getString(6));
					npmls.setMachineNo(rs.getString(7));
					npmls.setWorkerid(rs.getString(8));
					npmls.setProcessingReasons(rs.getString(9));
					npmls.setWorkContent(rs.getString(10));
					npmls.setExpectCompleteTime(rs.getInt(11));
					npmls.setStartTime(rs.getString(12));
					npmls.setEndTime(rs.getString(13));
					npmls.setConsumingTime(rs.getString(14));
					npmls.setActualCompletedQuantity(rs.getInt(15));
					npmls.setNote(rs.getString(16));
					npmls.setState(rs.getInt(17));
					npmls.setName(rs.getString(19));
					npmls.setProcessName(rs.getString(21));
					
					lessonList.add(npmls);
				}
			}catch(Exception e){e.printStackTrace();}
			
			return lessonList; 
		}
		
		
		
		//更新编辑页的工作信息
				public static void Updatelogbyid(String mouldidentifier,String DrawingNoMachinedPartName,int artifactsNumber,String process,String machineNo,String workerid,String processingReasons,String workContent,int expectCompleteTime,String startTime,String endTime,String consumingTime,int actualCompletedQuantity,String note,int state,String updateid,Timestamp updatetime,int id) throws SQLException{
					//List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
					Connection conn= ConnectionFactory2.makeConnection();
					Statement stmt = conn.createStatement();
					//ResultSet rs= null;
					//int count=0;
					try{
					//	String sql="SELECT npmls.*,e.name FROM eight_factory.`new_precision_machining_log _statistics` npmls left join employee e on npmls.workerid=e.employeeNumber where npmls.id="+id;
						String sql2="UPDATE `new_precision_machining_log_statistics` SET  mouldidentifier = '"+mouldidentifier+"', DrawingNoMachinedPartName='"+DrawingNoMachinedPartName+"',artifactsNumber='"+artifactsNumber+"' ,process='"+process+"',machineNo='"+machineNo+"',workerid='"+workerid+"',processingReasons='"+processingReasons+"',workContent='"+workContent+"',expectCompleteTime='"+expectCompleteTime+"',startTime='"+startTime+"',endTime='"+endTime+"',consumingTime='"+consumingTime+"',actualCompletedQuantity='"+actualCompletedQuantity+"',note='"+note+"',state='"+state+"',updateid='"+updateid+"',updatetime='"+updatetime+"'  WHERE id = "+id;
						//System.out.println(sql2);
						stmt.executeUpdate(sql2);						
					}catch(Exception e){e.printStackTrace();}
					
					
				}
		
				//查看日志的状态 by 日志id
					public static int  SearchLogState(int logid) throws SQLException{
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						int state=0;
						try{
							String sql="SELECT npmls.state FROM `new_precision_machining_log_statistics` npmls where npmls.id="+logid;
							//System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								state=rs.getInt(1);
							}
						}catch(Exception e){e.printStackTrace();}
						return state; 
					}
				//锁定日志状态  日志id  更新人 和更新时间
					public static void  UpdateLockLogState(int logid,String employeeid,Timestamp updatetime,int state) throws SQLException{
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						
						try{
							String sql="UPDATE `new_precision_machining_log_statistics` SET updateid = '"+employeeid+"', updatetime = '"+updatetime+"',state="+state+" where id="+logid;
							System.out.println(sql);
							stmt.executeUpdate(sql);
						}catch(Exception e){e.printStackTrace();}
						
					}
					
					
					//启用禁用 模具型号  by日志id  更新人 和更新时间
					public static void  UpdateLockMouldState(int logid,String employeeid,Timestamp updatetime,int state) throws SQLException{
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						
						try{
							String sql="UPDATE `mould` SET updateid = '"+employeeid+"', updatetime = '"+updatetime+"',state="+state+" where id="+logid;
							System.out.println(sql);
							stmt.executeUpdate(sql);
						}catch(Exception e){e.printStackTrace();}
						
					}
		
				
	//查询所有的模具 型号 
					
					public static List<Mould> SearchMouldAll() throws SQLException{
						List<Mould> lessonList= new ArrayList<Mould>();
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						//int count=0;
						try{
							String sql="SELECT  DISTINCT m.mouldname  FROM mould  m where m.state=1";
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								Mould m = new Mould();
								m.setMouldname(rs.getString(1));
							//	m.setDrawingno(rs.getString(2));
								lessonList.add(m);
							}
						}catch(Exception e){e.printStackTrace();}
						
						return lessonList; 
					}	
					
					
//查询所有的模具 型号 +图号
					
					public static List<Mould> SearchMoulddrawingNoAll() throws SQLException{
						List<Mould> lessonList= new ArrayList<Mould>();
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						//int count=0;
						try{
							String sql="SELECT m.id, m.mouldname ,m.machinedPartNameDrawingno,m.state,m.createid,m.createtime,e.name ,m.process FROM mould m left join employee e on e.employeeNumber=m.createid   order by m.id ASC";
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								Mould m = new Mould();
								m.setId(rs.getInt(1));
								m.setMouldname(rs.getString(2));
								m.setMachinedPartNameDrawingno(rs.getString(3));
								m.setState(rs.getInt(4));
								m.setCreateid(rs.getInt(5));
								m.setCreatetime(rs.getTimestamp(6));
								m.setCreateUserName(rs.getString(7));
								m.setProcess(rs.getInt(8));
								lessonList.add(m);
							}
						}catch(Exception e){e.printStackTrace();}
						
						return lessonList; 
					}	
					
					
					
					
					//查询所有的图号型号 
									
									public static List<Mould> SearchAllDrawingno() throws SQLException{
										List<Mould> lessonList= new ArrayList<Mould>();
										Connection conn= ConnectionFactory2.makeConnection();
										Statement stmt = conn.createStatement();
										ResultSet rs= null;
										//int count=0;
										try{
											String sql="SELECT  m.machinedPartNameDrawingno  FROM mould  m where m.state=1";
											System.out.println(sql);
											rs=stmt.executeQuery(sql);
											while(rs.next()){
												Mould m = new Mould();
												//m.setMouldname(rs.getString(1));
												m.setMachinedPartNameDrawingno(rs.getString(1));
												lessonList.add(m);
											}
										}catch(Exception e){e.printStackTrace();}
										
										return lessonList; 
									}	
		//根据模型编号查图号				
					public static List<Mould> SearchMouldbymould(String mouldname) throws SQLException{
						List<Mould> lessonList= new ArrayList<Mould>();
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						//int count=0;
						try{
							String sql="SELECT m.mouldname,m.machinedPartNameDrawingno FROM mould  m where m.state=1 and m.mouldname='"+mouldname+"'";
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								Mould m = new Mould();
								//m.setMouldname(rs.getString(1));
								m.setMachinedPartNameDrawingno(rs.getString(2));
								lessonList.add(m);
							}
						}catch(Exception e){e.printStackTrace();}
						
						return lessonList; 
					}	
					
			//查询模具是否存在
					public static int Searchlogdatecount(String logdate) throws SQLException{
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						int count=0;
						try{
							String sql="select count(1) from mould m where m.state=1 and m.mouldname= '"+logdate+"'";
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								count=rs.getInt(1);
							}
						}catch(Exception e){e.printStackTrace();}
						
						return count; 
					}	
					//查询个人数据
					public static int Searchlogdatecountbyworkid(String employeeNumber) throws SQLException{
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						int count=0;
						try{
							String sql="select count(1) from  `new_precision_machining_log_statistics` npmls where  npmls.workerid= '"+employeeNumber+"'";
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								count=rs.getInt(1);
							}
						}catch(Exception e){e.printStackTrace();}
						
						return count; 
					}	
					
					//搜索所有员工的工作记录
					public static List<NewPrecisionMachiningLogStatistics> SearchAllLog(int curPage,int row,String SearchCondition) throws SQLException{
						List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						curPage=(curPage-1)*row;
						try{
							String sql="SELECT npmls.*,e.name,d.name FROM `new_precision_machining_log_statistics` npmls left join employee e on npmls.workerid=e.employeeNumber left join dictionary d on d.value=npmls.process where npmls.state=2 "+SearchCondition+" and d.type='process' order by npmls.createtime asc limit "+curPage+","+row;
							
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								NewPrecisionMachiningLogStatistics npmls = new NewPrecisionMachiningLogStatistics();
								npmls.setId(rs.getInt(1));
								npmls.setCreatetime(rs.getTimestamp(2));
								npmls.setMouldidentifier(rs.getString(3));
								npmls.setDrawingNoMachinedPartName(rs.getString(4));
								npmls.setArtifactsNumber(rs.getInt(5));
								npmls.setProcess(rs.getString(6));
								npmls.setMachineNo(rs.getString(7));
								npmls.setWorkerid(rs.getString(8));
								npmls.setProcessingReasons(rs.getString(9));
								npmls.setWorkContent(rs.getString(10));
								npmls.setExpectCompleteTime(rs.getInt(11));
								npmls.setStartTime(rs.getString(12));
								npmls.setEndTime(rs.getString(13));
								npmls.setConsumingTime(rs.getString(14));
								npmls.setActualCompletedQuantity(rs.getInt(15));
								npmls.setNote(rs.getString(16));
								npmls.setState(rs.getInt(17));
								npmls.setName(rs.getString(20));
								npmls.setProcessName(rs.getString(21));
								lessonList.add(npmls);
							}
						}catch(Exception e){e.printStackTrace();}
						return lessonList; 
					}		
					//根据id搜索该组长对应的员工的工作记录
					public static List<NewPrecisionMachiningLogStatistics> SearchlogbyLeadergrouptype(String employeeNumber,int curPage,int row,String SearchCondition) throws SQLException{
						List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						curPage=(curPage-1)*row;
						
						try{
							String sql="SELECT npmls.*,e1.name,d.name FROM `new_precision_machining_log_statistics` npmls left join employee e1 on npmls.workerid=e1.employeeNumber left join employee e2 on e2.grouptype= e1.grouptype left join employee e on e.employeeNumber=npmls.workerid   left join dictionary d on d.value=npmls.process where (npmls.state=1 and npmls.workerid='"+employeeNumber+"' or npmls.state=2) and d.type='process' "+SearchCondition+" and e2.employeeNumber='"+employeeNumber+"' order by npmls.createtime desc limit "+curPage+","+row;
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								NewPrecisionMachiningLogStatistics npmls = new NewPrecisionMachiningLogStatistics();
								npmls.setId(rs.getInt(1));
								npmls.setCreatetime(rs.getTimestamp(2));
								npmls.setMouldidentifier(rs.getString(3));
								npmls.setDrawingNoMachinedPartName(rs.getString(4));
								npmls.setArtifactsNumber(rs.getInt(5));
								npmls.setProcess(rs.getString(6));
								npmls.setMachineNo(rs.getString(7));
								npmls.setWorkerid(rs.getString(8));
								npmls.setProcessingReasons(rs.getString(9));
								npmls.setWorkContent(rs.getString(10));
								npmls.setExpectCompleteTime(rs.getInt(11));
								npmls.setStartTime(rs.getString(12));
								npmls.setEndTime(rs.getString(13));
								npmls.setConsumingTime(rs.getString(14));
								npmls.setActualCompletedQuantity(rs.getInt(15));
								npmls.setNote(rs.getString(16));
								npmls.setState(rs.getInt(17));
								npmls.setName(rs.getString(20));
								npmls.setProcessName(rs.getString(21));
								lessonList.add(npmls);
							}
						}catch(Exception e){e.printStackTrace();}
						return lessonList; 
					}	
					//查询组长对应的查询日志数量
					public static int SearchlogdateCountbyleaderworkid(String employeeNumber,String SearchCondition) throws SQLException{
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						int count=0;
						try{
							String sql="SELECT count(*) FROM `new_precision_machining_log_statistics` npmls left join employee e on npmls.workerid=e.employeeNumber left join employee e2 on e2.grouptype= e.grouptype where  (npmls.state=1 and npmls.workerid='"+employeeNumber+"' or npmls.state=2) "+SearchCondition+"and e2.employeeNumber='"+employeeNumber+"'";
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								count=rs.getInt(1);
							}
						}catch(Exception e){e.printStackTrace();}
						
						return count; 
					}
					//查询所有日志数量
					public static int SearchlogdateAllCount(String SearchCondition) throws SQLException{
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						int count=0;
						try{
							String sql="SELECT count(*) FROM `new_precision_machining_log_statistics` npmls left join employee e on e.employeeNumber=npmls.workerid where npmls.state=2 "+ SearchCondition;
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								count=rs.getInt(1);
							}
						}catch(Exception e){e.printStackTrace();}
						
						return count; 
					}	
					//获取所有用户的数据
					//查询所有日志数量
					public static List<Employee2> SearchAllEmployeeInfo() throws SQLException{
						List<Employee2> lessonList= new ArrayList<Employee2>();
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						//int count=0;
						try{
							String sql="SELECT e.id,e.employeeNumber,e.name,d1.name as groupName,d2.name as leaderpermission,e.state,e.designEngineer  FROM employee e left join dictionary d1 on d1.value=e.grouptype left join dictionary d2 on d2.value=e.leader where d1.type='team' and d2.type='leader'";
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								Employee2 e = new Employee2();
								e.setId(rs.getInt(1));
								e.setEmployeeNumber(rs.getString(2));
								e.setName(rs.getString(3));
								e.setGroupTypeName(rs.getString(4));
								e.setLeader(rs.getString(5));
								e.setState(rs.getString(6));
								e.setDesignEngineer(rs.getInt(7));
								lessonList.add(e);
								//count=rs.getInt(1);
							}
						}catch(Exception e){e.printStackTrace();}
						
						return lessonList; 
					}
					
					
					//更新员工的设计师傅权限
					
					public static void UpdateDesignEngineerStatebyid(Timestamp updatetime,int designEngineer,int id) throws SQLException{
						//List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						//ResultSet rs= null;
						//int count=0;
						try{
						//	String sql="SELECT npmls.*,e.name FROM eight_factory.`new_precision_machining_log _statistics` npmls left join employee e on npmls.workerid=e.employeeNumber where npmls.id="+id;
							String sql2="UPDATE employee  SET designEngineer = "+designEngineer+" WHERE id = "+id;
							System.out.println(sql2);
							stmt.executeUpdate(sql2);						
						}catch(Exception e){e.printStackTrace();}
						
						
					}
					
					public static void UpdateEmployeeStatebyid(Timestamp updatetime,int state,int id) throws SQLException{
						//List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						//ResultSet rs= null;
						//int count=0;
						try{
						//	String sql="SELECT npmls.*,e.name FROM eight_factory.`new_precision_machining_log _statistics` npmls left join employee e on npmls.workerid=e.employeeNumber where npmls.id="+id;
							String sql2="UPDATE employee  SET state = "+state+" WHERE id = "+id;
							System.out.println(sql2);
							stmt.executeUpdate(sql2);						
						}catch(Exception e){e.printStackTrace();}
						
						
					}
					
					//注册新数据
					public static void  inserEmployee(int EmployeeNumber,String password,String Name,int grouptype,int leader,Timestamp createtime) throws SQLException{
						
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						//ResultSet rs= null;
						//int count=0;
						try{
							String sql="INSERT INTO employee (employeeNumber,password,name,grouptype,leader,state,designEngineer,createtime) VALUES ("+EmployeeNumber+",'"+password+"','"+Name+"',"+grouptype+","+leader+",1,0,'"+createtime+"');";
							System.out.println(sql);
							stmt.execute(sql);
						
						}catch(Exception e){e.printStackTrace();}
						
						
					}	
					
					
					//增加新的图纸模具型号
					public static void insertmould(String mouldname,String drawingno,Timestamp createtime,String EmployeeNumber,int process)throws SQLException {
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						try{
							String sql="INSERT INTO mould (mouldname,machinedPartNameDrawingno,state,createtime,createid,process)VALUES ('"+mouldname+"','"+drawingno+"',1,'"+createtime+"','"+EmployeeNumber+"',"+process+")";
							System.out.println(sql);
							stmt.execute(sql);
							
						}catch (Exception e) {
							// TODO: handle exception
						}
						
					}
		
					// 搜索模具型号by id 和workid
					public static List<Mould> SearchMouldbymouldid(String mouldid) throws SQLException{
						List<Mould> lessonList= new ArrayList<Mould>();
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						//int count=0;
						try{
							String sql="SELECT m.id,m.mouldname,m.machinedPartNameDrawingno,substring_index(m.machinedPartNameDrawingno,'/',1) as drawingno,substring_index(m.machinedPartNameDrawingno,'/',-1) as MachinedPartName,m.process FROM mould  m where  m.id="+mouldid;
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								Mould m = new Mould();
								m.setId(rs.getInt(1));
								m.setMouldname(rs.getString(2));
								m.setDrawingno(rs.getString(4));
								m.setMachinedPartName(rs.getString(5));
								m.setProcess(rs.getInt(6));
								lessonList.add(m);
							}
						}catch(Exception e){e.printStackTrace();}
						
						return lessonList; 
					}	
					
					
					//因为没有sub函数  重写获取mould  在java进行逻辑处理
					// 搜索模具型号by id 和workid
					public static List<Mould> SearchMouldsqlitebymouldid(String mouldid) throws SQLException{
						List<Mould> lessonList= new ArrayList<Mould>();
						Connection conn= ConnectionFactory2.makeConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs= null;
						//int count=0;
						try{
							String sql="SELECT m.id,m.mouldname,m.machinedPartNameDrawingno,m.process FROM mould  m where  m.id="+mouldid;
							System.out.println(sql);
							rs=stmt.executeQuery(sql);
							while(rs.next()){
								Mould m = new Mould();
								m.setId(rs.getInt(1));
								m.setMouldname(rs.getString(2));
								m.setMachinedPartNameDrawingno(rs.getString(3));
								m.setProcess(rs.getInt(4));
								lessonList.add(m);
							}
						}catch(Exception e){e.printStackTrace();}
						
						return lessonList; 
					}	
					
					
					

					//更新模具的模具型号 加工件名称 图号的信息by 模具信息id workid
							public static void UpdateMouldbyid(Timestamp createtime,String id,String mouldname ,String machinedPartNameDrawingno,String process,String employeeNumber) throws SQLException{
								//List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
								Connection conn= ConnectionFactory2.makeConnection();
								Statement stmt = conn.createStatement();
								//ResultSet rs= null;
								//int count=0;
								try{
								//	String sql="SELECT npmls.*,e.name FROM eight_factory.`new_precision_machining_log _statistics` npmls left join employee e on npmls.workerid=e.employeeNumber where npmls.id="+id;
									String sql2="UPDATE `mould` SET mouldname = '"+mouldname+"',machinedPartNameDrawingno='"+machinedPartNameDrawingno+"', process='"+process+"',updateid='"+employeeNumber+"' where id="+id;
									System.out.println(sql2);
									stmt.executeUpdate(sql2);						
								}catch(Exception e){e.printStackTrace();}
								
								
							}
					
							//SELECT e.telphone FROM eight_factory.employee e where e.employeeNumber=81205;
							//查询 个人手机号
							//查询所有日志数量
							public static String SearchtelNumberByemployeeNumber(String employeeNumber) throws SQLException{
								Connection conn= ConnectionFactory2.makeConnection();
								Statement stmt = conn.createStatement();
								ResultSet rs= null;
								String tel=null;
								try{
									String sql="SELECT e.telphone FROM employee e where e.employeeNumber="+ employeeNumber;
									System.out.println(sql);
									rs=stmt.executeQuery(sql);
									while(rs.next()){
										tel=rs.getString(1);
									}
								}catch(Exception e){e.printStackTrace();}
								
								return tel; 
							}	
							
							//更新员工手机号
							public static void UpdateemployeeTelNumberByemployeeNumber(String newtelphone,String employeeid) throws SQLException{
								//List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
								Connection conn= ConnectionFactory2.makeConnection();
								Statement stmt = conn.createStatement();
								try{
									String sql2="UPDATE employee  SET telphone = '"+newtelphone+"'  WHERE id = "+employeeid;
									//System.out.println(sql2);
									stmt.executeUpdate(sql2);						
								}catch(Exception e){e.printStackTrace();}
								
								
							}			
							
				//判断手机号是否被绑定
							public static int SearchTelnumberCount(String employeeTelNumber) throws SQLException {
								int Count=0;
							//	String name = null;
								Connection conn = ConnectionFactory2.makeConnection();
								// System.err.println(conn);
								Statement stmt = conn.createStatement();
								ResultSet rs = null;
								try {

									String sql = "select count(1) from  employee e where telphone="+employeeTelNumber;
									rs = stmt.executeQuery(sql);
									while (rs.next()) {
										Count = rs.getInt(1);
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

								return Count;
							}
					//通过手机号查询id
							
							public static int SearchidByemployeetelNumber(String employeetelNumber) throws SQLException{
								Connection conn= ConnectionFactory2.makeConnection();
								Statement stmt = conn.createStatement();
								ResultSet rs= null;
								int id=0;
								try{
									String sql="SELECT e.id FROM employee e where e.telphone="+ employeetelNumber;
									System.out.println(sql);
									rs=stmt.executeQuery(sql);
									while(rs.next()){
										id=rs.getInt(1);
									}
								}catch(Exception e){e.printStackTrace();}
								
								return id; 
							}	
							//通过工号查询id
							
							public static int SearchidByemployeeNumber(String employeeNumber) throws SQLException{
								Connection conn= ConnectionFactory2.makeConnection();
								Statement stmt = conn.createStatement();
								ResultSet rs= null;
								int id=0;
								try{
									String sql="SELECT e.id FROM employee e where e.employeeNumber='"+ employeeNumber+"'";
									System.out.println(sql);
									rs=stmt.executeQuery(sql);
									while(rs.next()){
										id=rs.getInt(1);
									}
								}catch(Exception e){e.printStackTrace();}
								
								return id; 
							}	
							//更新员工密码
							public static void UpdateemployeepasswordByid(String newpassword,int employeeid) throws SQLException{
								//List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
								Connection conn= ConnectionFactory2.makeConnection();
								Statement stmt = conn.createStatement();
								try{
									String sql2="UPDATE employee  SET password = '"+newpassword+"'  WHERE id = "+employeeid;
									System.out.println(sql2);
									stmt.executeUpdate(sql2);						
								}catch(Exception e){e.printStackTrace();}
								
								
							}
							
							//判断加工件名称/图号是否重复
							
							public static int SearchmachinedPartNameDrawingnoCount(String machinedPartNameDrawingno) throws SQLException {
								int Count=0;
							//	String name = null;
								Connection conn = ConnectionFactory2.makeConnection();
								// System.err.println(conn);
								Statement stmt = conn.createStatement();
								ResultSet rs = null;
								try {

									String sql = "select count(1) from  mould m where machinedPartNameDrawingno='"+machinedPartNameDrawingno+"'";
									System.out.println(sql);
									rs = stmt.executeQuery(sql);
									while (rs.next()) {
										Count = rs.getInt(1);
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

								return Count;
							}
							
	public static void main(String[] args) throws SQLException {
//		String name=null;
//		name=SearchName(81205);
//		System.out.println(name);
//		int count =0;
//		count=SearchNameCount(81205);
//		System.out.println(count);
//		int count=0;
//		count=SearchUser(81205, "123456");
//		System.out.println(count);
//		List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
//		lessonList=SearchlogbyName("黄哲丰");
//		for(int i=0;i<lessonList.size();i++)
//		{
//			System.out.println(lessonList.get(i).getCreatetime());
//			
//		}
		
//		int a=1;
//		int b=0;
//		System.out.println(b/a);
//		System.out.println(a/b);
//		Timestamp now = new Timestamp(System.currentTimeMillis()); 	
//		insertlogdateinfo(now,"C91ZXC123","9001-B镶片下型腔",1,"1",1,81205,"新做","a",8,now,now,now,1,"a",1);
//		List<NewPrecisionMachiningLogStatistics> lessonList= new ArrayList<NewPrecisionMachiningLogStatistics>();
//		lessonList=Searchlogbyid(1);
//		System.out.println(lessonList.size());
//		int count=Searchlogdate(110);
//		System.out.println(count);
//		Updatelogbyid(null, null, null, 0, null, 0, 0, null, null, 0, null, null, null, 0, null, 0, 0);
//		String dateStr = "12:34";  
//        Date date = new Date();  
//        //注意format的格式要与日期String的格式相匹配  
//        DateFormat sdf = new SimpleDateFormat("HH:mm");  
//        try {  
//            date = sdf.parse(dateStr);  
//            System.out.println(date.toString());  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//		
//	

//			
//			 int A=0;
//			 int B=0;
//			 String str="16:17";
//			 if(str!=null){
//				 String[] aa = str.split(":");
//				 for (int i = 0 ; i <aa.length ; i++ ) {
//				     //System.out.println(aa[i]); 
//				     if(i==0){
//				    	A=Integer.parseInt(aa[i]);
//				     }
//				     else if(i==1){
//					    	B=Integer.parseInt(aa[i]);
//					     }
//				   } 
//				System.out.println(60*A+B);
//			  }
//		List<Mould> lessonList= new ArrayList<Mould>();
//		//lessonList=SqlMethods.SearchMouldAll();
//		lessonList=SqlMethods.SearchMouldbymould("C91ZXC123");
//		for(int i=0;i<lessonList.size();i++){
//			System.out.println(lessonList.get(i).getDrawingno());
//			
//		}
//	 int count=0;
//	 count=SqlMethods.Searchlogdatecountbyworkid(81205);
//	System.out.println(count);
		
		
		
	}	
		
}
