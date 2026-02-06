package com.bootdo.cpe.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.domain.SurverExcellentApplyMainInfoDO;
import com.bootdo.cpe.service.SurverExcellentApplyMainInfoService;
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
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
 
@Controller
@RequestMapping("/system/surverExcellentApplyMainInfo")
public class SurverExcellentApplyMainInfoController {
	@Autowired
	private SurverExcellentApplyMainInfoService surverExcellentApplyMainInfoService;
	
	@GetMapping()
	@RequiresPermissions("system:surverExcellentApplyMainInfo:surverExcellentApplyMainInfo")
	String SurverExcellentApplyMainInfo(){
	    return "system/surverExcellentApplyMainInfo/surverExcellentApplyMainInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:surverExcellentApplyMainInfo:surverExcellentApplyMainInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverExcellentApplyMainInfoDO> surverExcellentApplyMainInfoList = surverExcellentApplyMainInfoService.list(query);
		int total = surverExcellentApplyMainInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverExcellentApplyMainInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:surverExcellentApplyMainInfo:add")
	String add(){
	    return "system/surverExcellentApplyMainInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:surverExcellentApplyMainInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverExcellentApplyMainInfoDO surverExcellentApplyMainInfo = surverExcellentApplyMainInfoService.get(id);
		model.addAttribute("surverExcellentApplyMainInfo", surverExcellentApplyMainInfo);
	    return "system/surverExcellentApplyMainInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:surverExcellentApplyMainInfo:add")
	public R save( SurverExcellentApplyMainInfoDO surverExcellentApplyMainInfo){
		if(surverExcellentApplyMainInfoService.save(surverExcellentApplyMainInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:surverExcellentApplyMainInfo:edit")
	public R update( SurverExcellentApplyMainInfoDO surverExcellentApplyMainInfo){
		surverExcellentApplyMainInfoService.update(surverExcellentApplyMainInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:surverExcellentApplyMainInfo:remove")
	public R remove( Integer id){
		if(surverExcellentApplyMainInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:surverExcellentApplyMainInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverExcellentApplyMainInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
