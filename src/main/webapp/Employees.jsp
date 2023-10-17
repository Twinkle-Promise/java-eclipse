<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理页面</title>
<!-- 引入easyUI支持 -->
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">   
<script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>   
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript">
//页面添加触发下拉列表功能
$(function(){
	$('#win').window('close');  // close a window  
	  $.getJSON('doInIt_employees.do',function(map){
			var lsde=map.lsde;
			var lswe=map.lswe;
			/* alert(lswe); */
			//部门下拉列表
			$('#did').combobox({    
   			 data:lsde,    
    			valueField:'did',    
    				textField:'depart',
    				value:1,
    				height:25,
    				panelHeight:150
});  
			//福利复选框
		for(var i=0;i<lswe.length;i++){
			var we=lswe[i];
			 $("#we").append("<input type='checkbox' id='wids' name='wids' value='"+we.wid+"'/>"+we.welfarename);
		}  
	  }); 
 });
 /* 分页查询 */
 	$(function(){
 		$('#dg').datagrid({    
 		    url:'findPageAll_employees.do', 
 		   pagination:true,
 		  singleSelect:true,
 		 sortName:'eid',
 		sortOrder:'asc',
 		pageList:[2,4,6,8,10],
 		    columns:[[    
 		        {field:'eid',title:'员工ID',width:100,align:'center'},    
 		        {field:'empname',title:'员工名称',width:100,align:'center'},    
 		        {field:'empsex',title:'员工性别',width:100,align:'center'},   
 		       {field:'empaddress',title:'员工地址',width:100,align:'center'}, 
 		      {field:'employeedate',title:'员工生日',width:130,align:'center'}, 
 		      {field:'emppicture',title:'员工照片',width:150,align:'center',
 		   	  formatter: function(value,row,index){
		    		  return"<img src=UploadPicture/"+row.emppicture+" width=50/>"
		    	  }
 		      },  	
 		     {field:'depart',title:'员工部门',width:100,align:'center'}, 
 		    {field:'option',title:'福利薪资详情',width:150,align:'center',
 		    	formatter: function(value,row,index){
 					var bt1="<input type='button' id='deleteById' name='deleteById' value='删除' onclick=doDeleteById("+row.eid+")>";
 					var bt2="<input type='button' id='editById' name='editById' value='修改' onclick=doEditById("+row.eid+")>";
 					var bt3="<input type='button' id='findById' name='findById' value='详情' onclick=doFindById("+row.eid+")>";
 					
 					return bt1+bt2+bt3;
 		    	}
 		    } 
 		    ]]    
 		});  
 	});
 /* 删除与编辑与详情  */
function doDeleteById(eid){   
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	$.getJSON('deleteById_employees.do?eid='+eid,function(code){
	    		 if(code==1){
	    			$.messager.alert('提示','删除成功');   
	    			$('#dg').datagrid('reload');//重新载入页面数据
	    			}else{
	    				$.messager.alert('提示','删除失败');
	    			}
	    	});
	    }   
	});  

 }
function doEditById(eid){
	$.getJSON('findById_employees.do?eid='+eid,function(employees){
		$(":checkbox[name='wids']").each(function(){
			$(this).prop("checked",false);
		});
		//表单赋值
		$('#fform').form('load',{
			'eid':employees.eid,
			'empname':employees.empname,
			'empsex':employees.empsex,
			'empaddress':employees.empaddress,
			'employeedate':employees.employeedate,
			'did':employees.did,
			'empsalary':employees.empsalary,
		});
		$("#mypicture").attr('src','UploadPicture/'+employees.emppicture);
		var wids=employees.wids;
		$(":checkbox[name='wids']").each(function(){
			for(var i=0;i<wids.length;i++){		
			if($(this).val()==wids[i]){
				$(this).prop("checked",true);
			}
			}
		});	
	});
}
function doFindById(eid){
	$.getJSON('findById_employees.do?eid='+eid,function(employees){
		$("#eidText").html(employees.eid);
		$("#empnameText").html(employees.empname);
		$("#empsexText").html(employees.empsex);
		$("#empaddressText").html(employees.empaddress);
		$("#employeedateText").html(employees.employeedate);
		$("#departText").html(employees.depart);
		$("#empsalaryText").html(employees.empsalary);
		var lwfe=employees.lwfe;
		var warnames=[];
		for(var i=0;i<lwfe.length;i++){
			var we=lwfe[i];
			warnames.push(we.welfarename);
		}
		alert(warnames);
		var strname=warnames.join(",");
		$("#weText").html(strname);
		$("#mypicture1").attr('src','UploadPicture/'+employees.emppicture);
		
		alert(employees.emppicture);
		$('#win').window('open');  // open a window	
	});
}
/* 保存与修改 */
	$(function(){
		$("#btup").click(function(){
			$.messager.progress();	// 显示进度条
			$('#fform').form('submit', {
				url:'save_employees.do',
			onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				success: function(code){
					if(code==1){
						$.messager.alert('提示','添加成功');   
						$('#dg').datagrid('reload');//重新载入页面数据
					}else{
						$.messager.alert('提示','添加失败');
					} 
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			}); 
		});
		//修改员工数据
		$("#btedit").click(function(){
			$.messager.progress();	// 显示进度条
			$('#fform').form('submit', {
				url:'update_employees.do',
			onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				success: function(code){
					if(code==1){
						$.messager.alert('提示','修改成功');   
						$('#dg').datagrid('reload');//重新载入页面数据
					}else{
						$.messager.alert('提示','修改失败--(薪水不能低于当前工资)');
					} 
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			}); 
		});
	});
</script>
</head>
<body>
<p align="center">员工列表</p>
<!-- 分页组件 -->
<div align="center" style="width:100%"><table id="dg"></table></div>
<hr>

<form action="" method="post" enctype="multipart/form-data" name="fform" id="fform">
<table border="1px" width="100%" align="center">
<tr align="center" bgcolor="PINK">
<td colspan="3">员工管理系统</td>
</tr>
<tr>
<td align="center">员工姓名</td>
<td>
<input type="text" id="empname" name="empname" class="easyui-validatebox" data-options="required:true">
</td>
<td rowspan="7">
<img id="mypicture" align="middle" width="180px" height="170px" alt="图片不存在" src="UploadPicture/default.jpg" style="margin-left: 30%">
</td>
</tr>

<tr>
<td align="center">员工性别</td>
<td>
<input type="radio" id="empsex" name="empsex" value="男" checked="checked">男
<input type="radio" id="empsex" name="empsex" value="女">女
</td>
</tr>

<tr>
<td align="center">员工住址</td>
<td>
<input type="text" id="empaddress" name="empaddress" class="easyui-validatebox" data-options="required:true">
</td>
</tr>

<tr>
<td align="center">员工出生日期</td>
<td>
<input type="date" id="employeedate" name="employeedate" value="1999-01-01">
</td>
</tr>

<tr>
<td align="center">员工证件照</td>
<td>
<input type="file" id="picture" name="picture">
</td>
</tr>

<tr>
<td align="center">员工部门</td>
<td>
<input type="text" id="did" name="did">
</td>
</tr>

<tr>
<td align="center">员工薪资</td>
<td>
<input type="text" id="empsalary" name="empsalary" value="5000">元
</td>
</tr>

<tr>
<td align="center">员工福利</td>
<td colspan="2">
<span id="we"></span>
</td>
</tr>

<tr align="center" bgcolor="PINK">
<td colspan="3">
<input type="hidden" id="eid" name="eid">
<input type="button" id="btup" name="btup" value="保存">
<input type="button" id="btedit" name="btedit" value="修改">
<input type="button" id="btreset" name="btreset" value="重置">
</td>
</tr>
</table>
</form>
<!-- 员工详情弹窗 -->
<div id="win" class="easyui-window" title="My Window" style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true">   
    <table border="1px" width="100%" align="center">
<tr align="center" bgcolor="PINK">
<td colspan="3">员工管理系统</td>
</tr>

<tr>
<td align="center">员工编号</td>
<td>
<span id="eidText"></span>
</td>
<td rowspan="7">
<img id="mypicture1" align="middle" width="180px" height="170px" alt="图片不存在" src="UploadPicture/default.jpg" style="margin-bottom: 10% ">
</td>
</tr>

<tr>
<td align="center">员工姓名</td>
<td>
<span id="empnameText"></span>
</td>
</tr>

<tr>
<td align="center">员工性别</td>
<td>
<span id="empsexText"></span>
</td>
</tr>

<tr>
<td align="center">员工住址</td>
<td>
<span id="empaddressText"></span>
</td>
</tr>

<tr>
<td align="center">员工出生日期</td>
<td>
<span id="employeedateText"></span>
</td>
</tr>

<tr>
<td align="center">员工部门</td>
<td>
<span id="departText"></span>
</td>
</tr>

<tr>
<td align="center">员工薪资</td>
<td>
<span id="empsalaryText"></span>元
</td>
</tr>

<tr>
<td align="center">员工福利</td>
<td colspan="2">
<span id="weText"></span>
</td>
</tr>
</table>
</div>  
</body>
</html>