package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Welfare;

@Service("WelfareDao")
public interface IWelfareMapper {
	public List<Welfare> findAll();//查询全部福利
}
