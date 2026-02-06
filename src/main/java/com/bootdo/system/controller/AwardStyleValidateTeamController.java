package com.bootdo.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.cpe.service.ProjectCommonService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.AwardStyleValidateTeamDO;
import com.bootdo.system.service.AwardStyleValidateTeamService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 形式审查_团队
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-14 03:26:09
 */
 
@Controller
@RequestMapping("/system/awardStyleValidateTeam")
public class AwardStyleValidateTeamController extends BaseScienceTeamController {
	@Autowired
	private AwardStyleValidateTeamService awardStyleValidateTeamService;
	@Autowired
	private AwardEnterpriseProjectService awardEnterpriseProjectService;
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@GetMapping()
	@RequiresPermissions("system:awardStyleValidateTeam:awardStyleValidateTeam")
	String AwardStyleValidateTeam(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
	    return "system/awardStyleValidateTeam/awardStyleValidateTeam";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:awardStyleValidateTeam:awardStyleValidateTeam")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AwardStyleValidateTeamDO> awardStyleValidateTeamList = awardStyleValidateTeamService.list(query);
		int total = awardStyleValidateTeamService.count(query);
		PageUtils pageUtils = new PageUtils(awardStyleValidateTeamList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:awardStyleValidateTeam:add")
	String add(String proId,int teamId, @RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
		EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
		map.put("projectInfo",projectInfoDo);
		params.put("teamId",teamId);
		List<AwardStyleValidateTeamDO> list = awardStyleValidateTeamService.list(params);
		map.put("awardStyleValidateTeam",list.size() > 0 ? list.get(0) : new AwardStyleValidateTeamDO());
		map.put("proId",proId);
		map.put("teamId",teamId);
	    return "system/awardStyleValidateTeam/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:awardStyleValidateTeam:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
		AwardStyleValidateTeamDO awardStyleValidateTeam = awardStyleValidateTeamService.get(id);
		map.addAttribute("awardStyleValidateTeam", awardStyleValidateTeam);
	    return "system/awardStyleValidateTeam/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:awardStyleValidateTeam:add")
	public R save( AwardStyleValidateTeamDO awardStyleValidateTeam){
		if(StringUtils.isNotBlank(awardStyleValidateTeam.getProGroupName())) {
			//更新项目分组
			projectCommonService.updateProGroupName(awardStyleValidateTeam.getProId(), awardStyleValidateTeam.getProGroupName());
		}
		Long validateUid = getUserId();
		awardStyleValidateTeam.setValidateUid(validateUid);
		awardStyleValidateTeamService.cleanLastValidate(awardStyleValidateTeam.getProId(),awardStyleValidateTeam.getTeamId());
		awardStyleValidateTeam.setIsLastValidate(1);
		awardStyleValidateTeam.setTeamStat(awardStyleValidateTeam.getValidateResult());
		if(awardStyleValidateTeamService.save(awardStyleValidateTeam)>0){
			Map<String,Object> params = new HashMap<>();
			params.put("proStat", awardStyleValidateTeam.getValidateResult());
			params.put("proId", awardStyleValidateTeam.getProId());
			awardEnterpriseProjectService.updateProStat(params);
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:awardStyleValidateTeam:edit")
	public R update( AwardStyleValidateTeamDO awardStyleValidateTeam){
		awardStyleValidateTeamService.update(awardStyleValidateTeam);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:awardStyleValidateTeam:remove")
	public R remove( Integer id){
		if(awardStyleValidateTeamService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:awardStyleValidateTeam:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		awardStyleValidateTeamService.batchRemove(ids);
		return R.ok();
	}
	
}
