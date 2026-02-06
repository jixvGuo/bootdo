package com.bootdo.cpe.petroleum_engineering_award.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProEngineeAwardDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProEngineeAwardService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 工程获奖
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-25 06:35:24
 */
 
@Controller
@RequestMapping("/petroleum_engineering_award/oilProEngineeAward")
public class OilProEngineeAwardController extends BaseController {
	@Autowired
	private OilProEngineeAwardService oilProEngineeAwardService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilProEngineeAward:oilProEngineeAward")
	String OilProEngineeAward(){
	    return "petroleum_engineering_award/oilProEngineeAward/oilProEngineeAward";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilProEngineeAward:oilProEngineeAward")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilProEngineeAwardDO> oilProEngineeAwardList = oilProEngineeAwardService.list(query);
		int total = oilProEngineeAwardService.count(query);
		PageUtils pageUtils = new PageUtils(oilProEngineeAwardList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilProEngineeAward:add")
	String add(){
	    return "petroleum_engineering_award/oilProEngineeAward/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilProEngineeAward:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilProEngineeAwardDO oilProEngineeAward = oilProEngineeAwardService.get(id);
		model.addAttribute("oilProEngineeAward", oilProEngineeAward);
	    return "petroleum_engineering_award/oilProEngineeAward/edit";
	}

	@RequestMapping("/get")
    @ResponseBody
	R get(Integer id,Model model){
		OilProEngineeAwardDO oilProEngineeAward = oilProEngineeAwardService.get(id);
		R result = R.ok();
		result.put("data", oilProEngineeAward);
	    return result;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("petroleum_engineering_award:oilProEngineeAward:add")
	public R save( OilProEngineeAwardDO oilProEngineeAward){
		Long optUid = getUserId();
		oilProEngineeAward.setOptUid(optUid == null ? "0" : optUid.toString());
		Integer id = oilProEngineeAward.getId();
		if(id != null && id > 0) {
			R result = update(oilProEngineeAward);
			result.put("id", id);
			return result;
		}
		if(oilProEngineeAwardService.save(oilProEngineeAward)>0){
			R result = R.ok();
			return result;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("petroleum_engineering_award:oilProEngineeAward:edit")
	public R update( OilProEngineeAwardDO oilProEngineeAward){
		oilProEngineeAwardService.update(oilProEngineeAward);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("petroleum_engineering_award:oilProEngineeAward:remove")
	public R remove( Integer id){
		if(oilProEngineeAwardService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilProEngineeAward:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilProEngineeAwardService.batchRemove(ids);
		return R.ok();
	}
	
}
