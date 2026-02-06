package com.bootdo.cpe.petroleum_engineering_award.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardPartakeUnitDO;
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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardUnitInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilAwardUnitInfoService;
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
@RequestMapping("/petroleum_engineering_award/oilAwardUnitInfo")
public class OilAwardUnitInfoController extends BaseController {
	@Autowired
	private OilAwardUnitInfoService oilAwardUnitInfoService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilAwardUnitInfo:oilAwardUnitInfo")
	String OilAwardUnitInfo(){
	    return "petroleum_engineering_award/oilAwardUnitInfo/oilAwardUnitInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilAwardUnitInfo:oilAwardUnitInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilAwardUnitInfoDO> oilAwardUnitInfoList = oilAwardUnitInfoService.list(query);
		int total = oilAwardUnitInfoService.count(query);
		PageUtils pageUtils = new PageUtils(oilAwardUnitInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilAwardUnitInfo:add")
	String add(){
	    return "petroleum_engineering_award/oilAwardUnitInfo/add";
	}

	@RequestMapping("/get")
	@ResponseBody
	R get(Integer id, Model model) {
		OilAwardUnitInfoDO oilAwardUnitInfoDO = oilAwardUnitInfoService.get(id);
		R result = R.ok();
		result.put("data", oilAwardUnitInfoDO);
		return result;
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilAwardUnitInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilAwardUnitInfoDO oilAwardUnitInfo = oilAwardUnitInfoService.get(id);
		model.addAttribute("oilAwardUnitInfo", oilAwardUnitInfo);
	    return "petroleum_engineering_award/oilAwardUnitInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardUnitInfo:add")
	public R save( OilAwardUnitInfoDO oilAwardUnitInfo){
		Long optUid = getUserId();
		oilAwardUnitInfo.setOptUid(optUid == null ? "0" : optUid.toString());
		Integer id = oilAwardUnitInfo.getId();
		if(id != null && id > 0) {
			update(oilAwardUnitInfo);
			R result = R.ok();
			result.put("id", id);
			return result;
		}
		if(oilAwardUnitInfoService.save(oilAwardUnitInfo)>0){
			R result = R.ok();
			result.put("id", oilAwardUnitInfo.getId());
			return result;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardUnitInfo:edit")
	public R update( OilAwardUnitInfoDO oilAwardUnitInfo){
		oilAwardUnitInfoService.update(oilAwardUnitInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("petroleum_engineering_award:oilAwardUnitInfo:remove")
	public R remove( Integer id){
		if(oilAwardUnitInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilAwardUnitInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilAwardUnitInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
