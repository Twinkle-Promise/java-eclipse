package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Department;
@Service("DepartmentDao")
public interface IDepartmentMapper {
	public List<Department> findAll();//查询部门全部信息
}
