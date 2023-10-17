package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Employees implements Serializable{
	private Integer eid;
	private String empname;
	private String empsex;
	private String empaddress;
	private Date birthday;
	private String emppicture="default.jpg";
	private Integer did;
	//临时属性
	private String employeedate;//生日类型
	private MultipartFile picture;//照片转换
	private String [] wids;//福利ID
	private List<Welfare> lwfe;//福利集合
	private Float empsalary;//员工工资
	private String depart;//员工部门
	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}
	//添加
	public Employees(String empname, String empsex, String empaddress, Date birthday, String emppicture, Integer did,
			String employeedate, MultipartFile picture, String[] wids, List<Welfare> lwfe, Float empsalary,
			String depart) {
		super();
		this.empname = empname;
		this.empsex = empsex;
		this.empaddress = empaddress;
		this.birthday = birthday;
		this.emppicture = emppicture;
		this.did = did;
		this.employeedate = employeedate;
		this.picture = picture;
		this.wids = wids;
		this.lwfe = lwfe;
		this.empsalary = empsalary;
		this.depart = depart;
	}

	public Employees(Integer eid, String empname, String empsex, String empaddress, Date birthday, String emppicture,
			Integer did) {
		super();
		this.eid = eid;
		this.empname = empname;
		this.empsex = empsex;
		this.empaddress = empaddress;
		this.birthday = birthday;
		this.emppicture = emppicture;
		this.did = did;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpsex() {
		return empsex;
	}
	public void setEmpsex(String empsex) {
		this.empsex = empsex;
	}
	public String getEmpaddress() {
		return empaddress;
	}
	public void setEmpaddress(String empaddress) {
		this.empaddress = empaddress;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmppicture() {
		return emppicture;
	}
	public void setEmppicture(String emppicture) {
		this.emppicture = emppicture;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getEmployeedate() {
		if(birthday!=null) {
		employeedate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		}
		return employeedate;
	}
	public void setEmployeedate(String employeedate) {
		try {
			if(employeedate!=null || !employeedate.trim().equals(employeedate)) {
				birthday=new SimpleDateFormat("yyyy-MM-dd").parse(employeedate);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.employeedate = employeedate;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public String[] getWids() {
		return wids;
	}
	public void setWids(String[] wids) {
		this.wids = wids;
	}
	public List<Welfare> getLwfe() {
		return lwfe;
	}
	public void setLwfe(List<Welfare> lwfe) {
		this.lwfe = lwfe;
	}
	public Float getEmpsalary() {
		return empsalary;
	}
	public void setEmpsalary(Float empsalary) {
		this.empsalary = empsalary;
	}

	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	@Override
	public String toString() {
		return "Employees [eid=" + eid + ", empname=" + empname + ", empsex=" + empsex + ", empaddress=" + empaddress
				+ ", birthday=" + birthday + ", emppicture=" + emppicture + ", did=" + did + ", employeedate="
				+ employeedate + ", picture=" + picture + ", wids=" + Arrays.toString(wids) + ", lwfe=" + lwfe
				+ ", empsalary=" + empsalary + ", depart=" + depart + "]";
	}

	
	
}
