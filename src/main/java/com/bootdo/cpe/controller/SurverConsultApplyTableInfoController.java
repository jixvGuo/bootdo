package com.bootdo.cpe.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseSurverController;
import com.bootdo.cpe.domain.AwardEnterpriseProjectDO;
import com.bootdo.cpe.service.AwardEnterpriseProjectCommonService;
import com.bootdo.cpe.utils.AwardSurverSubTypeEnum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.cpe.domain.SurverConsultApplyTableInfoDO;
import com.bootdo.cpe.service.SurverConsultApplyTableInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 *
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-29 23:15:37
 */

@Controller
@RequestMapping("/cpe/surverConsultApplyTableInfo")
public class SurverConsultApplyTableInfoController extends BaseSurverController {
	@Autowired
	private SurverConsultApplyTableInfoService surverConsultApplyTableInfoService;
	@Autowired
	private AwardEnterpriseProjectCommonService awardEnterpriseProjectCommonService;

	@GetMapping()
	@RequiresPermissions("cpe:surverConsultApplyTableInfo:surverConsultApplyTableInfo")
	String SurverConsultApplyTableInfo(){
	    return "cpe/surverConsultApplyTableInfo/surverConsultApplyTableInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverConsultApplyTableInfo:surverConsultApplyTableInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverConsultApplyTableInfoDO> surverConsultApplyTableInfoList = surverConsultApplyTableInfoService.list(query);
		int total = surverConsultApplyTableInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverConsultApplyTableInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverConsultApplyTableInfo:add")
	String add(){
	    return "cpe/surverConsultApplyTableInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverConsultApplyTableInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverConsultApplyTableInfoDO surverConsultApplyTableInfo = surverConsultApplyTableInfoService.get(id);
		model.addAttribute("surverConsultApplyTableInfo", surverConsultApplyTableInfo);
	    return "cpe/surverConsultApplyTableInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverConsultApplyTableInfo:add")
	public R save( SurverConsultApplyTableInfoDO surverConsultApplyTableInfo){
		AwardEnterpriseProjectDO projectDO = new AwardEnterpriseProjectDO();
		projectDO.setId(surverConsultApplyTableInfo.getProId());
		projectDO.setMajor(surverConsultApplyTableInfo.getApplyMajor());
		projectDO.setProSubType(AwardSurverSubTypeEnum.CONSULTING.getSubType());
		projectDO.setChengguo(surverConsultApplyTableInfo.getProName());
		awardEnterpriseProjectCommonService.update(projectDO);

		Long optUid = getUserId();
		surverConsultApplyTableInfo.setOptUid(optUid.intValue());
		Integer id = surverConsultApplyTableInfo.getId();
		if(id != null && id > 0) {
			int rst = surverConsultApplyTableInfoService.update(surverConsultApplyTableInfo);
			return rst > 0 ? R.ok() : R.error();
		}

		if(surverConsultApplyTableInfoService.save(surverConsultApplyTableInfo)>0){
			R r= R.ok();
			r.put("id", surverConsultApplyTableInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverConsultApplyTableInfo:edit")
	public R update( SurverConsultApplyTableInfoDO surverConsultApplyTableInfo){
		surverConsultApplyTableInfoService.update(surverConsultApplyTableInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverConsultApplyTableInfo:remove")
	public R remove( Integer id){
		if(surverConsultApplyTableInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverConsultApplyTableInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverConsultApplyTableInfoService.batchRemove(ids);
		return R.ok();
	}

}
