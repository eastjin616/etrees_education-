package com.spring.board.vo;

import java.util.List;

public class CereerVo  {
	
	 private Long carSeq;       
    private Long seq;          
    private String compName;     
    private String location;     
    private String startPeriod;  
    private String endPeriod;    
    private String task;   
    
     
	public Long getCarSeq() {
		return carSeq;
	}
	public void setCarSeq(Long carSeq) {
		this.carSeq = carSeq;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartPeriod() {
		return startPeriod;
	}
	public void setStartPeriod(String startPeriod) {
		this.startPeriod = startPeriod;
	}
	public String getEndPeriod() {
		return endPeriod;
	}
	public void setEndPeriod(String endPeriod) {
		this.endPeriod = endPeriod;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}


    
}
