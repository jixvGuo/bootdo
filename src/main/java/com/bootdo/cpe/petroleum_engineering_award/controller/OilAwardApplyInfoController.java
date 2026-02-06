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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardApplyInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilAwardApplyInfoService;
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
@RequestMapping("/petroleum_engineering_award/oilAwardApplyInfo")
public class OilAwardApplyInfoController extends BaseController {
	@Autowired
	private OilAwardApplyInfoService oilAwardApplyInfoService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilAwardApplyInfo:oilAwardApplyInfo")
	String OilAwardApplyInfo(){
	    return "petroleum_engineering_award/oilAwardApplyInfo/oilAwardApplyInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilAwardApplyInfo:oilAwardApplyInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilAwardApplyInfoDO> oilAwardApplyInfoList = oilAwardApplyInfoService.list(query);
		int total = oilAwardApplyInfoService.count(query);
		PageUtils pageUtils = new PageUtils(oilAwardApplyInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilAwardApplyInfo:add")
	String add(){
	    return "petroleum_engineering_award/oilAwardApplyInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilAwardApplyInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilAwardApplyInfoDO oilAwardApplyInfo = oilAwardApplyInfoService.get(id);
		model.addAttribute("oilAwardApplyInfo", oilAwardApplyInfo);
	    return "petroleum_engineering_award/oilAwardApplyInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardApplyInfo:add")
	public R save( OilAwardApplyInfoDO oilAwardApplyInfo){
		Long optUid = getUserId();
		oilAwardApplyInfo.setOptUid(optUid == null ? "0" : optUid.toString());
		Integer id = oilAwardApplyInfo.getId();
		if(id != null && id > 0) {
			R result = update(oilAwardApplyInfo);
			result.put("id", id);
			return result;
		}
		if(oilAwardApplyInfoService.save(oilAwardApplyInfo)>0){
			R result = R.ok();
			result.put("id", oilAwardApplyInfo.getId());
			return result;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardApplyInfo:edit")
	public R update( OilAwardApplyInfoDO oilAwardApplyInfo){
		oilAwardApplyInfoService.update(oilAwardApplyInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("petroleum_engineering_award:oilAwardApplyInfo:remove")
	public R remove( Integer id){
		if(oilAwardApplyInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilAwardApplyInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilAwardApplyInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
