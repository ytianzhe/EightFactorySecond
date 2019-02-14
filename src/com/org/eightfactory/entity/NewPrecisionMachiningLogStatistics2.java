package com.org.eightfactory.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Tony
 * @version 创建时间：2018年6月2日 上午11:18:54
 * @ClassName 类名称
 * @Description 类描述
 */
public class NewPrecisionMachiningLogStatistics2 {

	private int id;

	private Timestamp createtime;
	private String mouldidentifier;
	private String DrawingNoMachinedPartName;
	private int artifactsNumber;
	private String process;
	private String machineNo;
	private int workerid;
	private String processingReasons;
	private String workContent;
	private int expectCompleteTime;
	private String startTime;
	private String endTime;
	private String consumingTime;
	private int actualCompletedQuantity;
	private String note;
	private int state;
	private Date updatetime;

	private String name;
	private String processName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getMouldidentifier() {
		return mouldidentifier;
	}

	public void setMouldidentifier(String mouldidentifier) {
		this.mouldidentifier = mouldidentifier;
	}

	public String getDrawingNoMachinedPartName() {
		return DrawingNoMachinedPartName;
	}

	public void setDrawingNoMachinedPartName(String drawingNoMachinedPartName) {
		DrawingNoMachinedPartName = drawingNoMachinedPartName;
	}

	public int getArtifactsNumber() {
		return artifactsNumber;
	}

	public void setArtifactsNumber(int artifactsNumber) {
		this.artifactsNumber = artifactsNumber;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}



	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public int getWorkerid() {
		return workerid;
	}

	public void setWorkerid(int workerid) {
		this.workerid = workerid;
	}

	public String getProcessingReasons() {
		return processingReasons;
	}

	public void setProcessingReasons(String processingReasons) {
		this.processingReasons = processingReasons;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public int getExpectCompleteTime() {
		return expectCompleteTime;
	}

	public void setExpectCompleteTime(int expectCompleteTime) {
		this.expectCompleteTime = expectCompleteTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getConsumingTime() {
		return consumingTime;
	}

	public void setConsumingTime(String consumingTime) {
		this.consumingTime = consumingTime;
	}

	public int getActualCompletedQuantity() {
		return actualCompletedQuantity;
	}

	public void setActualCompletedQuantity(int actualCompletedQuantity) {
		this.actualCompletedQuantity = actualCompletedQuantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	

}
