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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProUnitOpinionDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProUnitOpinionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
 
@Controller
@RequestMapping("/petroleum_engineering_award/oilProUnitOpinion")
public class OilProUnitOpinionController extends BaseController {
	@Autowired
	private OilProUnitOpinionService oilProUnitOpinionService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilProUnitOpinion:oilProUnitOpinion")
	String OilProUnitOpinion(){
	    return "petroleum_engineering_award/oilProUnitOpinion/oilProUnitOpinion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilProUnitOpinion:oilProUnitOpinion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilProUnitOpinionDO> oilProUnitOpinionList = oilProUnitOpinionService.list(query);
		int total = oilProUnitOpinionService.count(query);
		PageUtils pageUtils = new PageUtils(oilProUnitOpinionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilProUnitOpinion:add")
	String add(){
	    return "petroleum_engineering_award/oilProUnitOpinion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilProUnitOpinion:edit")
	String edit(@PathVariable("id") String id,Model model){
		OilProUnitOpinionDO oilProUnitOpinion = oilProUnitOpinionService.get(id);
		model.addAttribute("oilProUnitOpinion", oilProUnitOpinion);
	    return "petroleum_engineering_award/oilProUnitOpinion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("petroleum_engineering_award:oilProUnitOpinion:add")
	public R save( OilProUnitOpinionDO oilProUnitOpinion){
		Long optUid = getUserId();
		oilProUnitOpinion.setOptUid(optUid == null ? "0" : optUid.toString());
		if(oilProUnitOpinionService.save(oilProUnitOpinion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("petroleum_engineering_award:oilProUnitOpinion:edit")
	public R update( OilProUnitOpinionDO oilProUnitOpinion){
		oilProUnitOpinionService.update(oilProUnitOpinion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("petroleum_engineering_award:oilProUnitOpinion:remove")
	public R remove( String id){
		if(oilProUnitOpinionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilProUnitOpinion:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		oilProUnitOpinionService.batchRemove(ids);
		return R.ok();
	}
	
}
