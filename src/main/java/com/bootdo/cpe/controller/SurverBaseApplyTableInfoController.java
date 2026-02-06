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

import com.bootdo.cpe.domain.SurverBaseApplyTableInfoDO;
import com.bootdo.cpe.service.SurverBaseApplyTableInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 *
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-30 23:24:20
 */

@Controller
@RequestMapping("/cpe/surverBaseApplyTableInfo")
public class SurverBaseApplyTableInfoController extends BaseSurverController {
	@Autowired
	private SurverBaseApplyTableInfoService surverBaseApplyTableInfoService;
	@Autowired
	private AwardEnterpriseProjectCommonService awardEnterpriseProjectCommonService;

	@GetMapping()
	@RequiresPermissions("cpe:surverBaseApplyTableInfo:surverBaseApplyTableInfo")
	String SurverBaseApplyTableInfo(){
	    return "cpe/surverBaseApplyTableInfo/surverBaseApplyTableInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverBaseApplyTableInfo:surverBaseApplyTableInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverBaseApplyTableInfoDO> surverBaseApplyTableInfoList = surverBaseApplyTableInfoService.list(query);
		int total = surverBaseApplyTableInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverBaseApplyTableInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverBaseApplyTableInfo:add")
	String add(){
	    return "cpe/surverBaseApplyTableInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverBaseApplyTableInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverBaseApplyTableInfoDO surverBaseApplyTableInfo = surverBaseApplyTableInfoService.get(id);
		model.addAttribute("surverBaseApplyTableInfo", surverBaseApplyTableInfo);
	    return "cpe/surverBaseApplyTableInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverBaseApplyTableInfo:add")
	public R save( SurverBaseApplyTableInfoDO surverBaseApplyTableInfo){
		AwardEnterpriseProjectDO projectDO = new AwardEnterpriseProjectDO();
		projectDO.setId(surverBaseApplyTableInfo.getProId());
		projectDO.setMajor(surverBaseApplyTableInfo.getApplyMajor());
		projectDO.setProSubType(AwardSurverSubTypeEnum.CONTRIBUTION.getSubType());
		projectDO.setChengguo(surverBaseApplyTableInfo.getProName());
		awardEnterpriseProjectCommonService.update(projectDO);

		Long optUid = getUserId();
		surverBaseApplyTableInfo.setOptUid(optUid.intValue());
		Integer id = surverBaseApplyTableInfo.getId();
		if(id != null && id > 0) {
			int rst = surverBaseApplyTableInfoService.update(surverBaseApplyTableInfo);
			return rst > 0 ? R.ok() : R.error();
		}

		if(surverBaseApplyTableInfoService.save(surverBaseApplyTableInfo)>0){
			R r = R.ok();
			r.put("id", surverBaseApplyTableInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverBaseApplyTableInfo:edit")
	public R update( SurverBaseApplyTableInfoDO surverBaseApplyTableInfo){
		surverBaseApplyTableInfoService.update(surverBaseApplyTableInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverBaseApplyTableInfo:remove")
	public R remove( Integer id){
		if(surverBaseApplyTableInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverBaseApplyTableInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverBaseApplyTableInfoService.batchRemove(ids);
		return R.ok();
	}

}
