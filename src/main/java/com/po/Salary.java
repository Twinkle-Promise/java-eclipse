package com.po;

import java.io.Serializable;

public class Salary implements Serializable{
	private Integer sid;
	private Integer eid;
	private Float empsalary;
	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Salary(Integer sid, Integer eid, Float empsalary) {
		super();
		this.sid = sid;
		this.eid = eid;
		this.empsalary = empsalary;
	}
	//添加
	public Salary(Integer eid, Float empsalary) {
		super();
		this.eid = eid;
		this.empsalary = empsalary;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public Float getEmpsalary() {
		return empsalary;
	}
	public void setEmpsalary(Float empsalary) {
		this.empsalary = empsalary;
	}
	@Override
	public String toString() {
		return "Salary [sid=" + sid + ", eid=" + eid + ", empsalary=" + empsalary + "]";
	}

}
