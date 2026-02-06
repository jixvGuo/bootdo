package com.bootdo.cpe.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.service.SurverExcellentApplyTableInfoService;
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

import com.bootdo.system.domain.SurverExcellentApplyTableInfoDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
 
@Controller
@RequestMapping("/system/surverExcellentApplyTableInfo")
public class SurverExcellentApplyTableInfoController {
	@Autowired
	private SurverExcellentApplyTableInfoService surverExcellentApplyTableInfoService;
	
	@GetMapping()
	@RequiresPermissions("system:surverExcellentApplyTableInfo:surverExcellentApplyTableInfo")
	String SurverExcellentApplyTableInfo(){
	    return "system/surverExcellentApplyTableInfo/surverExcellentApplyTableInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:surverExcellentApplyTableInfo:surverExcellentApplyTableInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverExcellentApplyTableInfoDO> surverExcellentApplyTableInfoList = surverExcellentApplyTableInfoService.list(query);
		int total = surverExcellentApplyTableInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverExcellentApplyTableInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:surverExcellentApplyTableInfo:add")
	String add(){
	    return "system/surverExcellentApplyTableInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:surverExcellentApplyTableInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverExcellentApplyTableInfoDO surverExcellentApplyTableInfo = surverExcellentApplyTableInfoService.get(id);
		model.addAttribute("surverExcellentApplyTableInfo", surverExcellentApplyTableInfo);
	    return "system/surverExcellentApplyTableInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:surverExcellentApplyTableInfo:add")
	public R save( SurverExcellentApplyTableInfoDO surverExcellentApplyTableInfo){
		if(surverExcellentApplyTableInfoService.save(surverExcellentApplyTableInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:surverExcellentApplyTableInfo:edit")
	public R update( SurverExcellentApplyTableInfoDO surverExcellentApplyTableInfo){
		surverExcellentApplyTableInfoService.update(surverExcellentApplyTableInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:surverExcellentApplyTableInfo:remove")
	public R remove( Integer id){
		if(surverExcellentApplyTableInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:surverExcellentApplyTableInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverExcellentApplyTableInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
