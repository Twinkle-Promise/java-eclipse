package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.Employees;
import com.po.PageSelect;

public interface IEmployeesAction {
	public String save(HttpServletRequest request,HttpServletResponse response,Employees em);//添加员工信息
	public String update(HttpServletRequest request,HttpServletResponse response,Employees em);//修改员工信息
	public String deleteById(HttpServletRequest request,HttpServletResponse response,Integer eid);//删除员工信息
	public String findById(HttpServletRequest request,HttpServletResponse response,Integer eid);//查询单个员工信息
	public String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows);//分页查询查询所有员工信息
	public String doInIt(HttpServletRequest request,HttpServletResponse response);
}
