package com.mapper;

import org.springframework.stereotype.Service;

import com.po.Salary;

@Service("SalaryDao")
public interface ISalaryMapper {
	public int save(Salary sa);//添加员工薪资
	public int updateByEid(Salary sa);//根据员工编号修改员工薪资
	public int deleteByEid(Integer eid);//根据员工编号删除员工薪资
	public Salary findByEid(Integer eid);//根据员工编号查询员工薪资
}
