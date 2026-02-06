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

import com.bootdo.system.domain.SurverConsultContributeUserInfoDO;
import com.bootdo.system.service.SurverConsultContributeUserInfoService;
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
@RequestMapping("/system/surverConsultContributeUserInfo")
public class SurverConsultContributeUserInfoController {
	@Autowired
	private SurverConsultContributeUserInfoService surverConsultContributeUserInfoService;
	
	@GetMapping()
	@RequiresPermissions("system:surverConsultContributeUserInfo:surverConsultContributeUserInfo")
	String SurverConsultContributeUserInfo(){
	    return "system/surverConsultContributeUserInfo/surverConsultContributeUserInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:surverConsultContributeUserInfo:surverConsultContributeUserInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverConsultContributeUserInfoDO> surverConsultContributeUserInfoList = surverConsultContributeUserInfoService.list(query);
		int total = surverConsultContributeUserInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverConsultContributeUserInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:surverConsultContributeUserInfo:add")
	String add(){
	    return "system/surverConsultContributeUserInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:surverConsultContributeUserInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverConsultContributeUserInfoDO surverConsultContributeUserInfo = surverConsultContributeUserInfoService.get(id);
		model.addAttribute("surverConsultContributeUserInfo", surverConsultContributeUserInfo);
	    return "system/surverConsultContributeUserInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:surverConsultContributeUserInfo:add")
	public R save( SurverConsultContributeUserInfoDO surverConsultContributeUserInfo){
		if(surverConsultContributeUserInfoService.save(surverConsultContributeUserInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:surverConsultContributeUserInfo:edit")
	public R update( SurverConsultContributeUserInfoDO surverConsultContributeUserInfo){
		surverConsultContributeUserInfoService.update(surverConsultContributeUserInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:surverConsultContributeUserInfo:remove")
	public R remove( Integer id){
		if(surverConsultContributeUserInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:surverConsultContributeUserInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverConsultContributeUserInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
