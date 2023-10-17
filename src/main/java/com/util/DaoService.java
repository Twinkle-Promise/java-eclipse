package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.*;

@Service("DaoService")
public class DaoService {
@Resource(name="DepartmentDao")
private IDepartmentMapper departmentMapper;	
@Resource(name="EmployeesDao")
private IEmployeesMapper employeesMapper;	
@Resource(name="EmpwelfareDao")
private IEmpwelfareMapper empwelfareMapper;	
@Resource(name="SalaryDao")
private ISalaryMapper salaryMapper;	
@Resource(name="WelfareDao")
private IWelfareMapper welfareMapper;
public IDepartmentMapper getDepartmentMapper() {
	return departmentMapper;
}
public void setDepartmentMapper(IDepartmentMapper departmentMapper) {
	this.departmentMapper = departmentMapper;
}
public IEmployeesMapper getEmployeesMapper() {
	return employeesMapper;
}
public void setEmployeesMapper(IEmployeesMapper employeesMapper) {
	this.employeesMapper = employeesMapper;
}
public IEmpwelfareMapper getEmpwelfareMapper() {
	return empwelfareMapper;
}
public void setEmpwelfareMapper(IEmpwelfareMapper empwelfareMapper) {
	this.empwelfareMapper = empwelfareMapper;
}
public ISalaryMapper getSalaryMapper() {
	return salaryMapper;
}
public void setSalaryMapper(ISalaryMapper salaryMapper) {
	this.salaryMapper = salaryMapper;
}
public IWelfareMapper getWelfareMapper() {
	return welfareMapper;
}
public void setWelfareMapper(IWelfareMapper welfareMapper) {
	this.welfareMapper = welfareMapper;
}	

}
