package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.service.*;

@Service("BizService")
public class BizService {
	@Resource(name="DepartmentService")
	private IDepartmentService departmentService;	
	@Resource(name="EmployeesService")
	private IEmployeesService employeesService;	
	@Resource(name="WelfareService")
	private IWelfareService welfareService;
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public IEmployeesService getEmployeesService() {
		return employeesService;
	}
	public void setEmployeesService(IEmployeesService employeesService) {
		this.employeesService = employeesService;
	}
	public IWelfareService getWelfareService() {
		return welfareService;
	}
	public void setWelfareService(IWelfareService welfareService) {
		this.welfareService = welfareService;
	}	
	

}
