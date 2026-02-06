package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTechnologyController;
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

import com.bootdo.system.domain.EnterpriseScienceAwardKnowledgeInfoDO;
import com.bootdo.system.service.EnterpriseScienceAwardKnowledgeInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 企业科技进步奖知识产权信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-08 22:47:30
 */
 
@Controller
@RequestMapping("/system/enterpriseScienceAwardKnowledgeInfo")
public class EnterpriseScienceAwardKnowledgeInfoController extends BaseScienceTechnologyController {
	@Autowired
	private EnterpriseScienceAwardKnowledgeInfoService enterpriseScienceAwardKnowledgeInfoService;
	
	@GetMapping()
	@RequiresPermissions("system:enterpriseScienceAwardKnowledgeInfo:enterpriseScienceAwardKnowledgeInfo")
	String EnterpriseScienceAwardKnowledgeInfo(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
	    return "system/enterpriseScienceAwardKnowledgeInfo/enterpriseScienceAwardKnowledgeInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:enterpriseScienceAwardKnowledgeInfo:enterpriseScienceAwardKnowledgeInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriseScienceAwardKnowledgeInfoDO> enterpriseScienceAwardKnowledgeInfoList = enterpriseScienceAwardKnowledgeInfoService.list(query);
		int total = enterpriseScienceAwardKnowledgeInfoService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriseScienceAwardKnowledgeInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:enterpriseScienceAwardKnowledgeInfo:add")
	String add(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
	    return "system/enterpriseScienceAwardKnowledgeInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:enterpriseScienceAwardKnowledgeInfo:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
		EnterpriseScienceAwardKnowledgeInfoDO enterpriseScienceAwardKnowledgeInfo = enterpriseScienceAwardKnowledgeInfoService.get(id);
		map.addAttribute("enterpriseScienceAwardKnowledgeInfo", enterpriseScienceAwardKnowledgeInfo);
	    return "system/enterpriseScienceAwardKnowledgeInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:enterpriseScienceAwardKnowledgeInfo:add")
	public R save( EnterpriseScienceAwardKnowledgeInfoDO enterpriseScienceAwardKnowledgeInfo){
		if(enterpriseScienceAwardKnowledgeInfoService.save(enterpriseScienceAwardKnowledgeInfo)>0){
			return R.ok();
		}
		return R.error();
	}


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:enterpriseScienceAwardKnowledgeInfo:edit")
	public R update( EnterpriseScienceAwardKnowledgeInfoDO enterpriseScienceAwardKnowledgeInfo){
		if(enterpriseScienceAwardKnowledgeInfo.getId() == 0) {
			enterpriseScienceAwardKnowledgeInfoService.save(enterpriseScienceAwardKnowledgeInfo);
		}else {
			enterpriseScienceAwardKnowledgeInfoService.update(enterpriseScienceAwardKnowledgeInfo);
		}
		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateBatch")
	@RequiresPermissions("system:enterpriseScienceAwardKnowledgeInfo:edit")
	public R updateBatch(@RequestBody EnterpriseScienceAwardKnowledgeInfoDO[] enterpriseScienceAwardKnowledgeInfoArr, HttpServletRequest request){
		Long uid = getUserId();
		for(EnterpriseScienceAwardKnowledgeInfoDO knowledgeInfoDO:enterpriseScienceAwardKnowledgeInfoArr) {
			knowledgeInfoDO.setUid(uid);
			Integer id = knowledgeInfoDO.getId();
			if (id == null || id == 0) {
				enterpriseScienceAwardKnowledgeInfoService.save(knowledgeInfoDO);
			} else {
				enterpriseScienceAwardKnowledgeInfoService.update(knowledgeInfoDO);
			}
		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:enterpriseScienceAwardKnowledgeInfo:remove")
	public R remove( Integer id){
		if(enterpriseScienceAwardKnowledgeInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:enterpriseScienceAwardKnowledgeInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriseScienceAwardKnowledgeInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
