package com.service;

import java.util.List;

import com.po.Employees;
import com.po.PageSelect;

public interface IEmployeesService {
	public boolean save(Employees em);//添加员工信息
	public boolean update(Employees em);//修改员工信息
	public boolean deleteById(Integer eid);//删除员工信息
	public Employees findById(Integer eid);//查询单个员工信息
	public List<Employees> findPageAll(PageSelect ps);//分页查询查询所有员工信息
	public int findMaxRows();//统计所有信息条数
}
