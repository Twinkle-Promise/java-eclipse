package com.service.implement;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Welfare;
import com.service.IWelfareService;
import com.util.DaoService;
@Service("WelfareService")
@Transactional
public class WelfareServiceImplement implements IWelfareService {
	@Resource(name="DaoService")
	private DaoService isDao;
	
	public DaoService getIsDao() {
		return isDao;
	}

	public void setIsDao(DaoService isDao) {
		this.isDao = isDao;
	}
	@Override
	public List<Welfare> findAll() {
		// TODO Auto-generated method stub
		return isDao.getWelfareMapper().findAll();
	}

}
