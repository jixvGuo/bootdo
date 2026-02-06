package com.bootdo.cpe.petroleum_engineering_award.controller;

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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityProSituationDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilQualityProSituationService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 石油优质工程项目概况
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-14 21:38:26
 */
 
@Controller
@RequestMapping("/cpe/oilQualityProSituation")
public class OilQualityProSituationController {
	@Autowired
	private OilQualityProSituationService oilQualityProSituationService;
	
	@GetMapping()
	@RequiresPermissions("cpe:oilQualityProSituation:oilQualityProSituation")
	String OilQualityProSituation(){
	    return "cpe/oilQualityProSituation/oilQualityProSituation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:oilQualityProSituation:oilQualityProSituation")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilQualityProSituationDO> oilQualityProSituationList = oilQualityProSituationService.list(query);
		int total = oilQualityProSituationService.count(query);
		PageUtils pageUtils = new PageUtils(oilQualityProSituationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cpe:oilQualityProSituation:add")
	String add(){
	    return "cpe/oilQualityProSituation/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:oilQualityProSituation:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilQualityProSituationDO oilQualityProSituation = oilQualityProSituationService.get(id);
		model.addAttribute("oilQualityProSituation", oilQualityProSituation);
	    return "cpe/oilQualityProSituation/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:oilQualityProSituation:add")
	public R save( OilQualityProSituationDO oilQualityProSituation){
		if(oilQualityProSituationService.save(oilQualityProSituation)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:oilQualityProSituation:edit")
	public R update( OilQualityProSituationDO oilQualityProSituation){
		oilQualityProSituationService.update(oilQualityProSituation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:oilQualityProSituation:remove")
	public R remove( Integer id){
		if(oilQualityProSituationService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:oilQualityProSituation:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilQualityProSituationService.batchRemove(ids);
		return R.ok();
	}
	
}
