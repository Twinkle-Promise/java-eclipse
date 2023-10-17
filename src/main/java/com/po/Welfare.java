package com.po;

import java.io.Serializable;

public class Welfare implements Serializable{
	private Integer wid;
	private String welfarename;
	public Welfare() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Welfare(Integer wid, String welfarename) {
		super();
		this.wid = wid;
		this.welfarename = welfarename;
	}
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public String getWelfarename() {
		return welfarename;
	}
	public void setWelfarename(String welfarename) {
		this.welfarename = welfarename;
	}
	@Override
	public String toString() {
		return "Welfare [wid=" + wid + ", welfarename=" + welfarename + "]";
	}

}
