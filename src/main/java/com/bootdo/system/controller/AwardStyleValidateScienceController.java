package com.bootdo.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.controller.AwardFlowController;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTechnologyController;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.service.ProjectCommonService;
import com.bootdo.system.domain.AwardStyleValidateTeamDO;
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

import com.bootdo.system.domain.AwardStyleValidateScienceDO;
import com.bootdo.system.service.AwardStyleValidateScienceService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 科技形式审核结果记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-14 03:26:09
 */

@Controller
@RequestMapping("/system/awardStyleValidateScience")
public class AwardStyleValidateScienceController extends BaseScienceTechnologyController {
	@Autowired
	private AwardStyleValidateScienceService awardStyleValidateScienceService;
	@Autowired
	private AwardEnterpriseProjectService awardEnterpriseProjectService;
	@Autowired
	private AwardFlowController awardFlowController;
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@GetMapping()
	@RequiresPermissions("system:awardStyleValidateScience:awardStyleValidateScience")
	String AwardStyleValidateScience(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
	    return "system/awardStyleValidateScience/awardStyleValidateScience";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:awardStyleValidateScience:awardStyleValidateScience")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AwardStyleValidateScienceDO> awardStyleValidateScienceList = awardStyleValidateScienceService.list(query);
		int total = awardStyleValidateScienceService.count(query);
		PageUtils pageUtils = new PageUtils(awardStyleValidateScienceList, total);
		return pageUtils;
	}
	


	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:awardStyleValidateScience:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
		AwardStyleValidateScienceDO awardStyleValidateScience = awardStyleValidateScienceService.get(id);
		map.addAttribute("awardStyleValidateScience", awardStyleValidateScience);
	    return "system/awardStyleValidateScience/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:awardStyleValidateScience:add")
	public R save( AwardStyleValidateScienceDO awardStyleValidateScience){
		if(StringUtils.isNotBlank(awardStyleValidateScience.getProGroupName())) {
			//更新项目分组
			projectCommonService.updateProGroupName(awardStyleValidateScience.getProId(), awardStyleValidateScience.getProGroupName());
		}
		Long validateUid = getUserId();
		awardStyleValidateScience.setValidateUid(validateUid);
		awardStyleValidateScienceService.cleanLastValidate(awardStyleValidateScience.getProId());
		awardStyleValidateScience.setIsLastValidate(1);
		if(awardStyleValidateScienceService.save(awardStyleValidateScience)>0){
		    EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
			projectInfoDo.setId(awardStyleValidateScience.getProId());
			projectInfoDo.setProStat(awardStyleValidateScience.getValidateResult());
			awardEnterpriseProjectService.updateProjectInfo(projectInfoDo);
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:awardStyleValidateScience:edit")
	public R update( AwardStyleValidateScienceDO awardStyleValidateScience){
		awardStyleValidateScienceService.update(awardStyleValidateScience);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:awardStyleValidateScience:remove")
	public R remove( Integer id){
		if(awardStyleValidateScienceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:awardStyleValidateScience:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		awardStyleValidateScienceService.batchRemove(ids);
		return R.ok();
	}
	
}
