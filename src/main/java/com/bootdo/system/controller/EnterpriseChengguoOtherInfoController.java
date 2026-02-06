package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseScienceTechnologyController;
import com.bootdo.system.domain.EnterpriseChengguoBaseInfoDO;
import com.bootdo.system.service.EnterpriseChengguoBaseInfoService;
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

import com.bootdo.system.domain.EnterpriseChengguoOtherInfoDO;
import com.bootdo.system.service.EnterpriseChengguoOtherInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 企业成果申报其他资料信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-27 13:37:22
 */
 
@Controller
@RequestMapping("/system/enterpriseChengguoOtherInfo")
public class EnterpriseChengguoOtherInfoController extends BaseScienceTechnologyController {
	@Autowired
	private EnterpriseChengguoOtherInfoService enterpriseChengguoOtherInfoService;
	@Autowired
	private EnterpriseChengguoBaseInfoService enterpriseChengguoBaseInfoService;
	
	@GetMapping()
	@RequiresPermissions("system:enterpriseChengguoOtherInfo:enterpriseChengguoOtherInfo")
	String EnterpriseChengguoOtherInfo(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
	    return "system/enterpriseChengguoOtherInfo/enterpriseChengguoOtherInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:enterpriseChengguoOtherInfo:enterpriseChengguoOtherInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriseChengguoOtherInfoDO> enterpriseChengguoOtherInfoList = enterpriseChengguoOtherInfoService.list(query);
		int total = enterpriseChengguoOtherInfoService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriseChengguoOtherInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:enterpriseChengguoOtherInfo:add")
	String add(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
	    return "system/enterpriseChengguoOtherInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:enterpriseChengguoOtherInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		EnterpriseChengguoOtherInfoDO enterpriseChengguoOtherInfo = enterpriseChengguoOtherInfoService.get(id);
		model.addAttribute("enterpriseChengguoOtherInfo", enterpriseChengguoOtherInfo);
	    return "system/enterpriseChengguoOtherInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:enterpriseChengguoOtherInfo:add")
	public R save( EnterpriseChengguoOtherInfoDO enterpriseChengguoOtherInfo){
		if(enterpriseChengguoOtherInfoService.save(enterpriseChengguoOtherInfo)>0){
			EnterpriseChengguoBaseInfoDO baseInfoDO = new EnterpriseChengguoBaseInfoDO();
			baseInfoDO.setProId(enterpriseChengguoOtherInfo.getProId());
			baseInfoDO.setChengguoName("暂未填写");
			enterpriseChengguoBaseInfoService.addBaseInfo(baseInfoDO);
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:enterpriseChengguoOtherInfo:edit")
	public R update( EnterpriseChengguoOtherInfoDO enterpriseChengguoOtherInfo){
		enterpriseChengguoOtherInfoService.update(enterpriseChengguoOtherInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:enterpriseChengguoOtherInfo:remove")
	public R remove( Integer id){
		if(enterpriseChengguoOtherInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:enterpriseChengguoOtherInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriseChengguoOtherInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
