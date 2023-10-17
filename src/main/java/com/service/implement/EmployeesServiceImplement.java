	package com.service.implement;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Employees;
import com.po.Empwelfare;
import com.po.PageSelect;
import com.po.Salary;
import com.po.Welfare;
import com.service.IEmployeesService;
import com.util.DaoService;
@Service("EmployeesService")
@Transactional
public class EmployeesServiceImplement implements IEmployeesService {
	@Resource(name="DaoService")
	private DaoService isDao;
	
	public DaoService getIsDao() {
		return isDao;
	}

	public void setIsDao(DaoService isDao) {
		this.isDao = isDao;
	}

	@Override
	public boolean save(Employees em) {
		if(em!=null) {
			//添加数据到员工表
			int code=isDao.getEmployeesMapper().save(em);
			if(code>0) {
				int maxEid=isDao.getEmployeesMapper().findMaxEid();
				//添加员工薪资表
				Salary sa =new Salary(maxEid,em.getEmpsalary());
				isDao.getSalaryMapper().save(sa);
				//添加员工福利
				if(em.getWids().length>0) {
					for(String wid :em.getWids()) {
						Empwelfare ef=new Empwelfare(maxEid,Integer.parseInt(wid));
						isDao.getEmpwelfareMapper().save(ef);
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(Employees em) {
		int code=isDao.getEmployeesMapper().update(em);
		if(code>0) {
			//修改薪资(限制降薪)
			Float empsalary=isDao.getSalaryMapper().findByEid(em.getEid()).getEmpsalary();
			if(empsalary<em.getEmpsalary()) {
				Salary sa=new Salary(em.getEid(),em.getEmpsalary());
				isDao.getSalaryMapper().updateByEid(sa);
				return true;
			}
			//修改员工福利
			isDao.getEmpwelfareMapper().deleteById(em.getEid());
			if(em.getWids().length>0) {
				for(String wid :em.getWids()) {
					Empwelfare ef=new Empwelfare(em.getEid(),Integer.parseInt(wid));
					isDao.getEmpwelfareMapper().save(ef);
				}
			}
			return true;
		}	
		return false;
	}

	@Override
	public boolean deleteById(Integer eid) {

		//1.删除薪资信息
		isDao.getSalaryMapper().deleteByEid(eid);
		//2.删除员工福利信息
		isDao.getEmpwelfareMapper().deleteById(eid);
		//3.删除员工信息
		int code=isDao.getEmployeesMapper().deleteById(eid);
		if(code>0) {
		//4.删除旧有照片
			return true;
		}
		return false;
	}

	@Override
	public Employees findById(Integer eid) {
		//查询员工信息
		Employees oldem=isDao.getEmployeesMapper().findById(eid);
		//查询员工薪资
		oldem.setEmpsalary(isDao.getSalaryMapper().findByEid(eid).getEmpsalary());
		//查询员工福利
		List<Welfare> lwfe=isDao.getEmpwelfareMapper().findByEid(eid);
		oldem.setLwfe(lwfe);
		System.out.println("福利集合:"+lwfe.toString());
		System.out.println("旧集合:"+oldem.toString());
		
		//福利编号数组
		String wids[]=new String[lwfe.size()];
		for(int i=0;i<lwfe.size();i++) {
			wids[i]=lwfe.get(i).getWid()+"";
		}
		System.out.println("数组集合:"+wids);
		oldem.setWids(wids);
		return oldem;
	}

	@Override
	public List<Employees> findPageAll(PageSelect ps) {
		return isDao.getEmployeesMapper().findPageAll(ps);
	}

	@Override
	public int findMaxRows() {
		// TODO Auto-generated method stub
		return isDao.getEmployeesMapper().findMaxRows();
	}

}
