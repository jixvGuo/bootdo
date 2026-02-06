package com.bootdo.cpe.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.service.SurverSoftContributeUserInfoService;
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

import com.bootdo.system.domain.SurverSoftContributeUserInfoDO;
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
@RequestMapping("/system/surverSoftContributeUserInfo")
public class SurverSoftContributeUserInfoController {
	@Autowired
	private SurverSoftContributeUserInfoService surverSoftContributeUserInfoService;
	
	@GetMapping()
	@RequiresPermissions("system:surverSoftContributeUserInfo:surverSoftContributeUserInfo")
	String SurverSoftContributeUserInfo(){
	    return "system/surverSoftContributeUserInfo/surverSoftContributeUserInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:surverSoftContributeUserInfo:surverSoftContributeUserInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverSoftContributeUserInfoDO> surverSoftContributeUserInfoList = surverSoftContributeUserInfoService.list(query);
		int total = surverSoftContributeUserInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverSoftContributeUserInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:surverSoftContributeUserInfo:add")
	String add(){
	    return "system/surverSoftContributeUserInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:surverSoftContributeUserInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverSoftContributeUserInfoDO surverSoftContributeUserInfo = surverSoftContributeUserInfoService.get(id);
		model.addAttribute("surverSoftContributeUserInfo", surverSoftContributeUserInfo);
	    return "system/surverSoftContributeUserInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:surverSoftContributeUserInfo:add")
	public R save( SurverSoftContributeUserInfoDO surverSoftContributeUserInfo){
		if(surverSoftContributeUserInfoService.save(surverSoftContributeUserInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:surverSoftContributeUserInfo:edit")
	public R update( SurverSoftContributeUserInfoDO surverSoftContributeUserInfo){
		surverSoftContributeUserInfoService.update(surverSoftContributeUserInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:surverSoftContributeUserInfo:remove")
	public R remove( Integer id){
		if(surverSoftContributeUserInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:surverSoftContributeUserInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverSoftContributeUserInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
