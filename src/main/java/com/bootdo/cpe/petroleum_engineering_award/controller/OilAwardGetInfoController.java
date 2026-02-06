package com.bootdo.cpe.petroleum_engineering_award.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProDesignAwardDO;
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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardGetInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilAwardGetInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
 
@Controller
@RequestMapping("/petroleum_engineering_award/oilAwardGetInfo")
public class OilAwardGetInfoController extends BaseController {
	@Autowired
	private OilAwardGetInfoService oilAwardGetInfoService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilAwardGetInfo:oilAwardGetInfo")
	String OilAwardGetInfo(){
	    return "petroleum_engineering_award/oilAwardGetInfo/oilAwardGetInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilAwardGetInfo:oilAwardGetInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilAwardGetInfoDO> oilAwardGetInfoList = oilAwardGetInfoService.list(query);
		int total = oilAwardGetInfoService.count(query);
		PageUtils pageUtils = new PageUtils(oilAwardGetInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilAwardGetInfo:add")
	String add(){

	    return "petroleum_engineering_award/oilAwardGetInfo/add";
	}

	@RequestMapping("/get")
	@ResponseBody
	R get(Integer id, Model model) {
		OilAwardGetInfoDO oilAwardGetInfoDO = oilAwardGetInfoService.get(id);
		R result = R.ok();
		result.put("data", oilAwardGetInfoDO);
		return result;
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardGetInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilAwardGetInfoDO oilAwardGetInfo = oilAwardGetInfoService.get(id);
		model.addAttribute("oilAwardGetInfo", oilAwardGetInfo);
	    return "petroleum_engineering_award/oilAwardGetInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardGetInfo:add")
	public R save(OilAwardGetInfoDO oilAwardGetInfo){
		Long optUid = getUserId();
		oilAwardGetInfo.setOptUid(optUid == null ? "0" : optUid.toString());
		Integer id = oilAwardGetInfo.getId();
		if(id != null && id > 0) {
			update(oilAwardGetInfo);
			R result = R.ok();
			result.put("id", id);
			return result;
		}
		if(oilAwardGetInfoService.save(oilAwardGetInfo)>0){
			R result = R.ok();
			result.put("id", oilAwardGetInfo.getId());
			return result;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardGetInfo:edit")
	public R update( OilAwardGetInfoDO oilAwardGetInfo){
		oilAwardGetInfoService.update(oilAwardGetInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("petroleum_engineering_award:oilAwardGetInfo:remove")
	public R remove( Integer id){
		if(oilAwardGetInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilAwardGetInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilAwardGetInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
