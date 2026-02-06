package com.bootdo.cpe.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseScienceTeamController;
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

import com.bootdo.cpe.domain.EnterpriTeamIntrductionDO;
import com.bootdo.cpe.service.EnterpriTeamIntrductionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 团队简介信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-15 23:37:19
 */
 
@Controller
@RequestMapping("/cpe/enterpriTeamIntrduction")
public class EnterpriTeamIntrductionController extends BaseScienceTeamController {
	@Autowired
	private EnterpriTeamIntrductionService enterpriTeamIntrductionService;
	
	@GetMapping()
	@RequiresPermissions("cpe:enterpriTeamIntrduction:enterpriTeamIntrduction")
	String EnterpriTeamIntrduction(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "cpe/enterpriTeamIntrduction/enterpriTeamIntrduction";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:enterpriTeamIntrduction:enterpriTeamIntrduction")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriTeamIntrductionDO> enterpriTeamIntrductionList = enterpriTeamIntrductionService.list(query);
		int total = enterpriTeamIntrductionService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriTeamIntrductionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cpe:enterpriTeamIntrduction:add")
	String add(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "cpe/enterpriTeamIntrduction/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:enterpriTeamIntrduction:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		EnterpriTeamIntrductionDO enterpriTeamIntrduction = enterpriTeamIntrductionService.get(id);
		map.addAttribute("enterpriTeamIntrduction", enterpriTeamIntrduction);
	    return "cpe/enterpriTeamIntrduction/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:enterpriTeamIntrduction:add")
	public R save( EnterpriTeamIntrductionDO enterpriTeamIntrduction){
		if(enterpriTeamIntrduction.getId() != null &&enterpriTeamIntrduction.getId() > 0) {
			enterpriTeamIntrductionService.update(enterpriTeamIntrduction);
			return R.ok();
		}else if(enterpriTeamIntrductionService.save(enterpriTeamIntrduction)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:enterpriTeamIntrduction:edit")
	public R update( EnterpriTeamIntrductionDO enterpriTeamIntrduction){
		enterpriTeamIntrductionService.update(enterpriTeamIntrduction);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:enterpriTeamIntrduction:remove")
	public R remove( Integer id){
		if(enterpriTeamIntrductionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:enterpriTeamIntrduction:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriTeamIntrductionService.batchRemove(ids);
		return R.ok();
	}
	
}
