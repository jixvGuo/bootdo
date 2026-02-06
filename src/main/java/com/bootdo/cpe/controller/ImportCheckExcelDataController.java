package com.bootdo.cpe.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.domain.ImportCheckExcelDataDO;
import com.bootdo.cpe.service.ImportCheckExcelDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 导入的excel的
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-09-04 07:21:32
 */
 
@Controller
@RequestMapping("/system/importCheckExcelData")
public class ImportCheckExcelDataController {
	@Autowired
	private ImportCheckExcelDataService importCheckExcelDataService;
	
	@GetMapping()
	@RequiresPermissions("system:importCheckExcelData:importCheckExcelData")
	String ImportCheckExcelData(){
	    return "system/importCheckExcelData/importCheckExcelData";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:importCheckExcelData:importCheckExcelData")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ImportCheckExcelDataDO> importCheckExcelDataList = importCheckExcelDataService.list(query);
		int total = importCheckExcelDataService.count(query);
		PageUtils pageUtils = new PageUtils(importCheckExcelDataList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:importCheckExcelData:add")
	String add(){
	    return "system/importCheckExcelData/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:importCheckExcelData:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ImportCheckExcelDataDO importCheckExcelData = importCheckExcelDataService.get(id);
		model.addAttribute("importCheckExcelData", importCheckExcelData);
	    return "system/importCheckExcelData/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:importCheckExcelData:add")
	public R save( ImportCheckExcelDataDO importCheckExcelData){
		if(importCheckExcelDataService.save(importCheckExcelData)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:importCheckExcelData:edit")
	public R update( ImportCheckExcelDataDO importCheckExcelData){
		importCheckExcelDataService.update(importCheckExcelData);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:importCheckExcelData:remove")
	public R remove( Integer id){
		if(importCheckExcelDataService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:importCheckExcelData:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		importCheckExcelDataService.batchRemove(ids);
		return R.ok();
	}
	
}
