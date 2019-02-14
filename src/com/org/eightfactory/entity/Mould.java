package com.org.eightfactory.entity;

import java.sql.Timestamp;


/**
 * @author Tony
 * @version 创建时间：2018年6月8日 上午8:52:43
 * @ClassName 类名称
 * @Description 类描述
 */
public class Mould {
	private int id;
	private String mouldname;
	private String drawingno;
	private String machinedPartNameDrawingno;
	private int state;
	private Timestamp createtime;
	private int createid;
	private String createUserName;
	private String machinedPartName;
	private int process;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
	public int getCreateid() {
		return createid;
	}
	public void setCreateid(int createid) {
		this.createid = createid;
	}

	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getMouldname() {
		return mouldname;
	}
	public void setMouldname(String mouldname) {
		this.mouldname = mouldname;
	}
	

	public String getMachinedPartNameDrawingno() {
		return machinedPartNameDrawingno;
	}
	public void setMachinedPartNameDrawingno(String machinedPartNameDrawingno) {
		this.machinedPartNameDrawingno = machinedPartNameDrawingno;
	}
	public String getMachinedPartName() {
		return machinedPartName;
	}
	public void setMachinedPartName(String machinedPartName) {
		this.machinedPartName = machinedPartName;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public String getDrawingno() {
		return drawingno;
	}
	public void setDrawingno(String drawingno) {
		this.drawingno = drawingno;
	}

	

}
