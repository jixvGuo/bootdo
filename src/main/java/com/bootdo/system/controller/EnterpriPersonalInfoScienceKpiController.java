package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseSciencePersonalController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.EnterpriPersonalInfoScienceKpiDO;
import com.bootdo.system.service.EnterpriPersonalInfoScienceKpiService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 申报人科技创新业绩指标
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-01 01:13:32
 */
 
@Controller
@RequestMapping("/system/enterpriPersonalInfoScienceKpi")
public class EnterpriPersonalInfoScienceKpiController extends BaseSciencePersonalController {
	@Autowired
	private EnterpriPersonalInfoScienceKpiService enterpriPersonalInfoScienceKpiService;
	
	@GetMapping()
	@RequiresPermissions("system:enterpriPersonalInfoScienceKpi:enterpriPersonalInfoScienceKpi")
	String EnterpriPersonalInfoScienceKpi(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriPersonalInfoScienceKpi/enterpriPersonalInfoScienceKpi";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:enterpriPersonalInfoScienceKpi:enterpriPersonalInfoScienceKpi")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriPersonalInfoScienceKpiDO> enterpriPersonalInfoScienceKpiList = enterpriPersonalInfoScienceKpiService.list(query);
		int total = enterpriPersonalInfoScienceKpiService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriPersonalInfoScienceKpiList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:enterpriPersonalInfoScienceKpi:add")
	String add(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriPersonalInfoScienceKpi/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:enterpriPersonalInfoScienceKpi:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		EnterpriPersonalInfoScienceKpiDO enterpriPersonalInfoScienceKpi = enterpriPersonalInfoScienceKpiService.get(id);
		map.addAttribute("enterpriPersonalInfoScienceKpi", enterpriPersonalInfoScienceKpi);
	    return "system/enterpriPersonalInfoScienceKpi/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:enterpriPersonalInfoScienceKpi:add")
	public R save( EnterpriPersonalInfoScienceKpiDO enterpriPersonalInfoScienceKpi){
		enterpriPersonalInfoScienceKpi.setApplyOptUid(getUserId());
		if(enterpriPersonalInfoScienceKpiService.save(enterpriPersonalInfoScienceKpi)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:enterpriPersonalInfoScienceKpi:edit")
	public R update( EnterpriPersonalInfoScienceKpiDO enterpriPersonalInfoScienceKpi){
		enterpriPersonalInfoScienceKpiService.update(enterpriPersonalInfoScienceKpi);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:enterpriPersonalInfoScienceKpi:remove")
	public R remove( Integer id){
		if(enterpriPersonalInfoScienceKpiService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:enterpriPersonalInfoScienceKpi:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriPersonalInfoScienceKpiService.batchRemove(ids);
		return R.ok();
	}
	
}
