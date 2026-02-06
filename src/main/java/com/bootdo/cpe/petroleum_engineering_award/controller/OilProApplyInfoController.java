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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProApplyInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProApplyInfoService;
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
@RequestMapping("/petroleum_engineering_award/oilProApplyInfo")
public class OilProApplyInfoController extends BaseController {
	@Autowired
	private OilProApplyInfoService oilProApplyInfoService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilProApplyInfo:oilProApplyInfo")
	String OilProApplyInfo(){
	    return "petroleum_engineering_award/oilProApplyInfo/oilProApplyInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilProApplyInfo:oilProApplyInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilProApplyInfoDO> oilProApplyInfoList = oilProApplyInfoService.list(query);
		int total = oilProApplyInfoService.count(query);
		PageUtils pageUtils = new PageUtils(oilProApplyInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilProApplyInfo:add")
	String add(){
	    return "petroleum_engineering_award/oilProApplyInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilProApplyInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilProApplyInfoDO oilProApplyInfo = oilProApplyInfoService.get(id);
		model.addAttribute("oilProApplyInfo", oilProApplyInfo);
	    return "petroleum_engineering_award/oilProApplyInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("petroleum_engineering_award:oilProApplyInfo:add") //TODO
	public R save( OilProApplyInfoDO oilProApplyInfo){
		Long optUid = getUserId();
		oilProApplyInfo.setOptUid(optUid == null ? "0" : optUid.toString());
		Integer id = oilProApplyInfo.getId();
		if(id != null && id > 0 ) {
			update(oilProApplyInfo);
			R result = R.ok();
			result.put("id", id);
			return result;
		}
		if(oilProApplyInfoService.save(oilProApplyInfo)>0){
			R result = R.ok();
			result.put("id", oilProApplyInfo.getId());
			return result;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("petroleum_engineering_award:oilProApplyInfo:edit")
	public R update( OilProApplyInfoDO oilProApplyInfo){
		oilProApplyInfoService.update(oilProApplyInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(oilProApplyInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilProApplyInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilProApplyInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
