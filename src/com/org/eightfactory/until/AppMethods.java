package com.org.eightfactory.until;

import java.util.Random;

/**
* @author Tony
* @version 创建时间：2018年6月13日 下午3:30:57
* @ClassName 类名称
* @Description 类描述
*/
public class AppMethods {

	 public static String AppendSql(String method,String SearchCondition,String oldSearchCondition) throws Exception{
			String Search="";
			System.out.println(method);
			switch (method) {
			case "EmployeeName":
				Search=" "+" and e.name='"+SearchCondition+"'  ";
				break;
			
				case "mouldname":
				Search=" "+" and npmls.mouldidentifier='"+SearchCondition+"' ";
					
				break;
				
				case "drawingno":
				Search=" "+" and npmls.DrawingNoMachinedPartName='"+SearchCondition+"' ";
	           break;
			default:
				break;
			}
			
			
			System.out.println("Search: "+Search);
			return oldSearchCondition+Search;
		    }
	 

		    //生成四位不重复的验证码
		   public static String codeGen(){
		        char [] codeSequence={'A','B','C','D','E','F','G','H','I','J',
		    'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		    '1','2','3','4','5','6','7','8','9'};
		        Random random =new Random();
		        StringBuilder sb=new StringBuilder();//动态字符串，String创建的字符串不能修改
		        int count=0;//计数器确定产生的是四位验证码
		        while(true){
		            //随机产生一个下标，通过下标取出字符数组对应的字符
		            char c=codeSequence[random.nextInt(codeSequence.length)];
		            //假设取出来的字符在动态字符串中不存在，代表没有重复
		            if (sb.indexOf(c+"")==-1) {
		                sb.append(c);//追加到动态字符串中
		                count++;
		                if (count==4) {
		                    break;
		                }
		            }
		        }

		    return sb.toString();

		        }
		   
		   
		   public static String varoption(String str,String str2){
			//   int arrayDemo[] = {1, 2, 3, 4, 5, 6, 7,8,9,10,11,12};
			//   String arrayDemo2[] = {"设计1", "设计2", "设计3", "设计4", "设计5","设计6","设计7","设计8","设计8","设计9","设计11","设计12"};
			   
			   
			   
			  
			   char[] c4 = new char[]{'a','d','s','a','d','s'};
			  String sc4=new String(c4);
			   
			   
			   String option=" var option = {xAxis: { type: 'category',"+"data: "+str+
       	     //   data: ['Sun','Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
       	    "},yAxis: {type: 'value'}, series: [{data: "+str2
       	    + ",type: 'bar'}]};";
			   return option;   
		   }
		    
	 public static String[] teamData(String teamName)throws Exception{
		 
		 
		 
		
		 
		 return null;	 
	 }
	 
	 public static String toString_2(int[] arr) {
			//1,创建缓冲区。
			StringBuffer sb = new StringBuffer();
			
			sb.append("[");
			for (int i = 0; i < arr.length; i++) {
				if(i!=arr.length-1){
					sb.append(arr[i]+",");
				}else{
					sb.append(arr[i]+"]");
				}
			}
			
			
			return sb.toString();
		}
	 
		public static String toString(int[] arr) {
			
			//用字符串连接。
			String str = "[";
			for (int i = 0; i < arr.length; i++) {
				if(i!=arr.length-1){
					str+=arr[i]+",";
				}else{
					str+=arr[i]+"]";
				}
			}
			return str;
		}
		
		
		public static String toString_3(String[] arr) {
			
			//用字符串连接。
			String str = "[";
			for (int i = 0; i < arr.length; i++) {
				if(i!=arr.length-1){
					str+="\""+arr[i]+"\""+",";
				}else{
					str+="\""+arr[i]+"\""+"]";
				}
			}
			return str;
		}

	 
	 
	public static void main(String[] args) {
		//String option=varoption();
		//System.out.println(option);
		int[] arrayDemo = {1, 2, 3, 4, 5, 6, 7,8,9,10,11,12};
		String arrayDemo2[] = {"设计1", "设计2", "设计3", "设计4", "设计5","设计6","设计7","设计8","设计8","设计9","设计11","设计12"};
		int[] arr = {34,12,89,68};
		String str = toString_2(arrayDemo);
		String str2=toString_3(arrayDemo2);
		System.out.println(str2);
	}
}
