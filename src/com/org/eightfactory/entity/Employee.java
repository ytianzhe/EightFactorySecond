package com.org.eightfactory.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
* @author Tony
* @version 创建时间：2018年6月2日 上午10:24:52
* @ClassName 类名称
* @Description 类描述
*/
public class Employee {
	
	private int id;
	private int employeeNumber;
	private String password;
	private String name;
	private String groupTypeName;
	private String leader;
	private String state;
	private int designEngineer;
	private String telphone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupTypeName() {
		return groupTypeName;
	}
	public void setGroupTypeName(String groupTypeName) {
		this.groupTypeName = groupTypeName;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getDesignEngineer() {
		return designEngineer;
	}
	public void setDesignEngineer(int designEngineer) {
		this.designEngineer = designEngineer;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
}
