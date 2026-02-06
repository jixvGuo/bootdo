package com.bootdo.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.controller.BaseSciencePersonalController;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.service.ProjectCommonService;
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

import com.bootdo.system.domain.AwardStyleValidatePersonDO;
import com.bootdo.system.service.AwardStyleValidatePersonService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 形式审查_个人
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-14 03:26:09
 */
 
@Controller
@RequestMapping("/system/awardStyleValidatePerson")
public class AwardStyleValidatePersonController extends BaseSciencePersonalController {
	@Autowired
	private AwardStyleValidatePersonService awardStyleValidatePersonService;
	@Autowired
	private AwardEnterpriseProjectService awardEnterpriseProjectService;
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@GetMapping()
	@RequiresPermissions("system:awardStyleValidatePerson:awardStyleValidatePerson")
	String AwardStyleValidatePerson(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
	    return "system/awardStyleValidatePerson/awardStyleValidatePerson";
	}

	/**
	 * 查看安装工程审查结果列表
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping("/viewValidateResult")
	public String toViewInstallCheckResultList(@RequestParam Map<String, Object> params, ModelMap map) {
		applyParams(map, params);
		return "/act/award/chengguo_personal/pro_personal_review_result_list";
	}

	@RequestMapping("/getCheckResultList")
	@ResponseBody
	public PageUtils getCheckResultList(@RequestParam Map<String, Object> params){
		params.remove("applyId");
		Query query = new Query(params);
		List<AwardStyleValidatePersonDO> personDOList = awardStyleValidatePersonService.list(query);
		int total = awardStyleValidatePersonService.count(query);
		PageUtils pageUtils = new PageUtils(personDOList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:awardStyleValidatePerson:awardStyleValidatePerson")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AwardStyleValidatePersonDO> awardStyleValidatePersonList = awardStyleValidatePersonService.list(query);
		int total = awardStyleValidatePersonService.count(query);
		PageUtils pageUtils = new PageUtils(awardStyleValidatePersonList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:awardStyleValidatePerson:add")
	String add(ModelMap map, @RequestParam Map<String, Object> params){
		packageAwardTaskId(map, params);
		Object proIdObj = params.get("proId");
		String proId = proIdObj != null ? proIdObj.toString() : "";
		Object personIdObj = params.get("personId");
		int personId = personIdObj != null ? Integer.parseInt(personIdObj.toString()) : 0;
		EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
		List<AwardStyleValidatePersonDO> personDOList = awardStyleValidatePersonService.list(params);
		map.put("projectInfo",projectInfoDo);
		map.put("personId",personId);
		map.put("awardStyleValidatePerson", personDOList.size() > 0 ? personDOList.get(0) : new AwardStyleValidatePersonDO());
	    return "system/awardStyleValidatePerson/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:awardStyleValidatePerson:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
		AwardStyleValidatePersonDO awardStyleValidatePerson = awardStyleValidatePersonService.get(id);
		map.addAttribute("awardStyleValidatePerson", awardStyleValidatePerson);
	    return "system/awardStyleValidatePerson/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:awardStyleValidatePerson:add")
	public R save( AwardStyleValidatePersonDO awardStyleValidatePerson){
		if(StringUtils.isNotBlank(awardStyleValidatePerson.getProGroupName())) {
			//更新项目分组
			projectCommonService.updateProGroupName(awardStyleValidatePerson.getProId(), awardStyleValidatePerson.getProGroupName());
		}
		Long validateUid = getUserId();
		awardStyleValidatePerson.setValidateUid(validateUid);
		awardStyleValidatePersonService.cleanLastValidate(awardStyleValidatePerson.getProId(),awardStyleValidatePerson.getPersonId());
		awardStyleValidatePerson.setIsLastValidate(1);
		awardStyleValidatePerson.setPersonStat(awardStyleValidatePerson.getValidateResult());
		if(awardStyleValidatePersonService.save(awardStyleValidatePerson)>0){
			Map<String,Object> params = new HashMap<>();
			params.put("proStat", awardStyleValidatePerson.getValidateResult());
			params.put("proId", awardStyleValidatePerson.getProId());
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
	@RequiresPermissions("system:awardStyleValidatePerson:edit")
	public R update( AwardStyleValidatePersonDO awardStyleValidatePerson){
		awardStyleValidatePersonService.update(awardStyleValidatePerson);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:awardStyleValidatePerson:remove")
	public R remove( Integer id){
		if(awardStyleValidatePersonService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:awardStyleValidatePerson:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		awardStyleValidatePersonService.batchRemove(ids);
		return R.ok();
	}
	
}
