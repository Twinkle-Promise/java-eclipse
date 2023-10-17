package com.service.implement;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Department;
import com.service.IDepartmentService;
import com.util.DaoService;
@Service("DepartmentService")
@Transactional
public class DepartmentServiceImplement implements IDepartmentService {
	@Resource(name="DaoService")
	private DaoService isDao;
	
	public DaoService getIsDao() {
		return isDao;
	}

	public void setIsDao(DaoService isDao) {
		this.isDao = isDao;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return isDao.getDepartmentMapper().findAll();
	}

}
