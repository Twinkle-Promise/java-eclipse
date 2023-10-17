package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Empwelfare;
import com.po.Welfare;

@Service("EmpwelfareDao")
public interface IEmpwelfareMapper {
	public int save(Empwelfare ef);//增加员工福利
	public int deleteById(Integer eid);//根据员工编号删除员工福利
	public List<Welfare> findByEid(Integer eid);//根据员工编号展示该员工福利
}
