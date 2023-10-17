package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Employees;
import com.po.PageSelect;

@Service("EmployeesDao")
public interface IEmployeesMapper {
	public int save(Employees em);//添加员工信息
	public int update(Employees em);//修改员工信息
	public int deleteById(Integer eid);//删除员工信息
	public Employees findById(Integer eid);//查询单个员工信息
	public List<Employees> findPageAll(PageSelect ps);//分页查询查询所有员工信息
	public int findMaxRows();//统计所有信息条数
	public int findMaxEid();
}
