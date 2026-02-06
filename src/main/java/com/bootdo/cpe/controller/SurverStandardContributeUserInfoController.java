package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.system.domain.SurverStandardContributeUserInfoDO;
import com.bootdo.system.service.SurverStandardContributeUserInfoService;
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
@RequestMapping("/system/surverStandardContributeUserInfo")
public class SurverStandardContributeUserInfoController {
	@Autowired
	private SurverStandardContributeUserInfoService surverStandardContributeUserInfoService;
	
	@GetMapping()
	@RequiresPermissions("system:surverStandardContributeUserInfo:surverStandardContributeUserInfo")
	String SurverStandardContributeUserInfo(){
	    return "system/surverStandardContributeUserInfo/surverStandardContributeUserInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:surverStandardContributeUserInfo:surverStandardContributeUserInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverStandardContributeUserInfoDO> surverStandardContributeUserInfoList = surverStandardContributeUserInfoService.list(query);
		int total = surverStandardContributeUserInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverStandardContributeUserInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:surverStandardContributeUserInfo:add")
	String add(){
	    return "system/surverStandardContributeUserInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:surverStandardContributeUserInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverStandardContributeUserInfoDO surverStandardContributeUserInfo = surverStandardContributeUserInfoService.get(id);
		model.addAttribute("surverStandardContributeUserInfo", surverStandardContributeUserInfo);
	    return "system/surverStandardContributeUserInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:surverStandardContributeUserInfo:add")
	public R save( SurverStandardContributeUserInfoDO surverStandardContributeUserInfo){
		if(surverStandardContributeUserInfoService.save(surverStandardContributeUserInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:surverStandardContributeUserInfo:edit")
	public R update( SurverStandardContributeUserInfoDO surverStandardContributeUserInfo){
		surverStandardContributeUserInfoService.update(surverStandardContributeUserInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:surverStandardContributeUserInfo:remove")
	public R remove( Integer id){
		if(surverStandardContributeUserInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:surverStandardContributeUserInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverStandardContributeUserInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
