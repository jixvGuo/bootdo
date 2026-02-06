package com.bootdo.cpe.petroleum_engineering_award.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardGetInfoDO;
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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardPartakeUnitDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilAwardPartakeUnitService;
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
@RequestMapping("/petroleum_engineering_award/oilAwardPartakeUnit")
public class OilAwardPartakeUnitController extends BaseController {
	@Autowired
	private OilAwardPartakeUnitService oilAwardPartakeUnitService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilAwardPartakeUnit:oilAwardPartakeUnit")
	String OilAwardPartakeUnit(){
	    return "petroleum_engineering_award/oilAwardPartakeUnit/oilAwardPartakeUnit";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilAwardPartakeUnit:oilAwardPartakeUnit")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilAwardPartakeUnitDO> oilAwardPartakeUnitList = oilAwardPartakeUnitService.list(query);
		int total = oilAwardPartakeUnitService.count(query);
		PageUtils pageUtils = new PageUtils(oilAwardPartakeUnitList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilAwardPartakeUnit:add")
	String add(){
	    return "petroleum_engineering_award/oilAwardPartakeUnit/add";
	}

	@RequestMapping("/get")
	@ResponseBody
	R get(Integer id, Model model) {
		OilAwardPartakeUnitDO oilAwardPartakeUnitDO = oilAwardPartakeUnitService.get(id);
		R result = R.ok();
		result.put("data", oilAwardPartakeUnitDO);
		return result;
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilAwardPartakeUnit:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilAwardPartakeUnitDO oilAwardPartakeUnit = oilAwardPartakeUnitService.get(id);
		model.addAttribute("oilAwardPartakeUnit", oilAwardPartakeUnit);
	    return "petroleum_engineering_award/oilAwardPartakeUnit/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardPartakeUnit:add")
	public R save( OilAwardPartakeUnitDO oilAwardPartakeUnit){
		Long optUid = getUserId();
		oilAwardPartakeUnit.setOptUid(optUid == null ? "0" : optUid.toString());
		Integer id = oilAwardPartakeUnit.getId();
		if(id != null && id > 0) {
			update(oilAwardPartakeUnit);
			R result = R.ok();
			result.put("id", id);
			return result;
		}
		if(oilAwardPartakeUnitService.save(oilAwardPartakeUnit)>0){
			R result = R.ok();
			result.put("id", oilAwardPartakeUnit.getId());
			return result;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardPartakeUnit:edit")
	public R update( OilAwardPartakeUnitDO oilAwardPartakeUnit){
		oilAwardPartakeUnitService.update(oilAwardPartakeUnit);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("petroleum_engineering_award:oilAwardPartakeUnit:remove")
	public R remove( Integer id){
		if(oilAwardPartakeUnitService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilAwardPartakeUnit:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilAwardPartakeUnitService.batchRemove(ids);
		return R.ok();
	}
	
}
