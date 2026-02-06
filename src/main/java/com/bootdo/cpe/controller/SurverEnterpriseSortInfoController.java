package com.bootdo.cpe.controller;

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

import com.bootdo.cpe.domain.SurverEnterpriseSortInfoDO;
import com.bootdo.cpe.service.SurverEnterpriseSortInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 上传排序表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-02 21:56:36
 */
 
@Controller
@RequestMapping("/cpe/surverEnterpriseSortInfo")
public class SurverEnterpriseSortInfoController {
	@Autowired
	private SurverEnterpriseSortInfoService surverEnterpriseSortInfoService;
	
	@GetMapping()
	@RequiresPermissions("cpe:surverEnterpriseSortInfo:surverEnterpriseSortInfo")
	String SurverEnterpriseSortInfo(){
	    return "cpe/surverEnterpriseSortInfo/surverEnterpriseSortInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverEnterpriseSortInfo:surverEnterpriseSortInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverEnterpriseSortInfoDO> surverEnterpriseSortInfoList = surverEnterpriseSortInfoService.list(query);
		int total = surverEnterpriseSortInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverEnterpriseSortInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cpe:surverEnterpriseSortInfo:add")
	String add(){
	    return "cpe/surverEnterpriseSortInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverEnterpriseSortInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverEnterpriseSortInfoDO surverEnterpriseSortInfo = surverEnterpriseSortInfoService.get(id);
		model.addAttribute("surverEnterpriseSortInfo", surverEnterpriseSortInfo);
	    return "cpe/surverEnterpriseSortInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverEnterpriseSortInfo:add")
	public R save( SurverEnterpriseSortInfoDO surverEnterpriseSortInfo){
		if(surverEnterpriseSortInfoService.save(surverEnterpriseSortInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverEnterpriseSortInfo:edit")
	public R update( SurverEnterpriseSortInfoDO surverEnterpriseSortInfo){
		surverEnterpriseSortInfoService.update(surverEnterpriseSortInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverEnterpriseSortInfo:remove")
	public R remove( Integer id){
		if(surverEnterpriseSortInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverEnterpriseSortInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverEnterpriseSortInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
