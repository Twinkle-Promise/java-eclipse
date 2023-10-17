package com.po;

import java.io.Serializable;

public class Department implements Serializable{
	private Integer did;
	private String depart;
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(Integer did, String depart) {
		super();
		this.did = did;
		this.depart = depart;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	@Override
	public String toString() {
		return "Department [did=" + did + ", depart=" + depart + "]";
	}

}
