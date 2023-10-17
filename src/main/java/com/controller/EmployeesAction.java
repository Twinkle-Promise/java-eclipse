package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.po.Department;
import com.po.Employees;
import com.po.PageSelect;
import com.po.Welfare;
import com.util.AjaxUtil;
import com.util.BizService;
@Controller
public class EmployeesAction implements IEmployeesAction {
	@Resource(name="BizService")
	private BizService biz;
	
	public BizService getBiz() {
		return biz;
	}

	public void setBiz(BizService biz) {
		this.biz = biz;
	}

	@RequestMapping(value="save_employees.do")
	public String save(HttpServletRequest request, HttpServletResponse response, Employees em) {
		System.out.println("存储正在进行......"+em.toString());
		String realPath =request.getSession().getServletContext().getRealPath("/");
		System.out.println("照片路径:"+realPath);
		MultipartFile multipartFile=em.getPicture();
		if(multipartFile!=null && !multipartFile.isEmpty()){
			String emppicture=multipartFile.getOriginalFilename();//获取文件上传名称
			if(emppicture.lastIndexOf(".")!=-1){//	存在后缀
				String ext=emppicture.substring(emppicture.lastIndexOf("."));//获取文件变量后缀
				if(ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".png")){//判断文件上传格式
					String	newemppicture=new Date().getTime()+ext;//组合成新的文件名
					File newFile=new File(realPath+"/UploadPicture/"+newemppicture);//新的文件存储位置
					try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), newFile);//Spring文件上传
						em.setEmppicture(newemppicture);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	/**文件上传**/
		boolean flag=biz.getEmployeesService().save(em);
		if(flag){
			String jsonStr=JSONObject.toJSONString(1);
			AjaxUtil.printString(response, jsonStr);
		}else{
			String jsonStr=JSONObject.toJSONString(0);
			AjaxUtil.printString(response, jsonStr);
		}
		return null;
	}

	@RequestMapping(value="update_employees.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Employees em) {
		System.out.println("修改正在进行......"+em.toString());
		String realPath =request.getSession().getServletContext().getRealPath("/");
		System.out.println("照片路径:"+realPath);
		//获取原有员工信息
		Employees oldem=biz.getEmployeesService().findById(em.getEid());
		MultipartFile multipartFile=em.getPicture();
		if(multipartFile!=null && !multipartFile.isEmpty()){
			String emppicture=multipartFile.getOriginalFilename();//获取文件上传名称
			if(emppicture.lastIndexOf(".")!=-1){//	存在后缀
				String ext=emppicture.substring(emppicture.lastIndexOf("."));//获取文件变量后缀
				if(ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".png")){//判断文件上传格式
					String	newemppicture=new Date().getTime()+ext;//组合成新的文件名
					File newFile=new File(realPath+"/UploadPicture/"+newemppicture);//新的文件存储位置
					try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), newFile);//Spring文件上传
						em.setEmppicture(newemppicture);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else {
			em.setEmppicture(oldem.getEmppicture());
		}
	/**文件上传**/
		boolean flag=biz.getEmployeesService().update(em);
		if(flag){
			String jsonStr=JSONObject.toJSONString(1);
			AjaxUtil.printString(response, jsonStr);
		}else{
			String jsonStr=JSONObject.toJSONString(0);
			AjaxUtil.printString(response, jsonStr);
		}
		return null;
	}

	@RequestMapping(value="deleteById_employees.do")
	public String deleteById(HttpServletRequest request, HttpServletResponse response, Integer eid) {
		System.out.println("删除正在进行......"+eid);
		String realPath =request.getSession().getServletContext().getRealPath("/");
		//获取原有员工信息
		Employees oldem=biz.getEmployeesService().findById(eid);
		boolean flag=biz.getEmployeesService().deleteById(eid);
		if(flag){
			//删除旧有照片
			File newFile=new File(realPath+"/UploadPicture/"+oldem.getEmppicture());
			if(newFile!=null && oldem.getEmppicture().equals("default.jpg")) {
				newFile.delete();
			}
			String jsonStr=JSONObject.toJSONString(1);
			AjaxUtil.printString(response, jsonStr);
		}else{
			String jsonStr=JSONObject.toJSONString(0);
			AjaxUtil.printString(response, jsonStr);
		}
		return null;
	}

	@RequestMapping(value="findById_employees.do")
	public String findById(HttpServletRequest request, HttpServletResponse response, Integer eid) {
		System.out.println("查询单个正在进行......"+eid);
		Employees oldem=biz.getEmployeesService().findById(eid);
		String jsonStr=JSONObject.toJSONString(oldem);
		AjaxUtil.printString(response, jsonStr);
		return null;
	}

	@RequestMapping(value="findPageAll_employees.do")
	public String findPageAll(HttpServletRequest request, HttpServletResponse response, Integer page,Integer rows) {
		System.out.println("分页查询正在进行......"+page+"/"+rows);
		Map<String, Object> Map=new HashMap<String, Object>();
		PageSelect ps =new PageSelect();
		page=page==null||page<1?ps.getPage():page ;
		rows=rows==null||rows<1?ps.getRows():rows ;
		if(rows>20)rows=10;
		ps.setPage(page);
		ps.setRows(rows);
		List<Employees>lsem=biz.getEmployeesService().findPageAll(ps);
		System.out.println(lsem.toString());
		System.out.println(rows.toString());
		int maxPage=biz.getEmployeesService().findMaxRows();
		Map.put("page", page);
		Map.put("rows", lsem);
		Map.put("total", maxPage);
		String jsonStr=JSONObject.toJSONString(Map);
		AjaxUtil.printString(response, jsonStr);
		return null;
	}
	@RequestMapping(value="doInIt_employees.do")
	public String doInIt(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doinit正在进行......");
		Map<String, Object> Map=new HashMap<String, Object>();
		List<Department> lsde=biz.getDepartmentService().findAll();
		List<Welfare> lswe=biz.getWelfareService().findAll();
		Map.put("lsde", lsde);
		Map.put("lswe", lswe);
		String jsonStr=JSONObject.toJSONString(Map);
		AjaxUtil.printString(response, jsonStr);
		return null;
	}

}
