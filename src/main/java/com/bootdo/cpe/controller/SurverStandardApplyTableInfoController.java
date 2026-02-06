package com.bootdo.cpe.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseSurverController;
import com.bootdo.cpe.domain.AwardEnterpriseProjectDO;
import com.bootdo.cpe.domain.SurverStandardApplyTableInfoDO;
import com.bootdo.cpe.service.AwardEnterpriseProjectCommonService;
import com.bootdo.cpe.service.SurverStandardApplyTableInfoService;
import com.bootdo.cpe.utils.AwardSurverSubTypeEnum;
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

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 *
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */

@Controller
@RequestMapping("/cpe/surverStandardApplyTableInfo")
public class SurverStandardApplyTableInfoController extends BaseSurverController {
	@Autowired
	private SurverStandardApplyTableInfoService surverStandardApplyTableInfoService;
	@Autowired
	private AwardEnterpriseProjectCommonService awardEnterpriseProjectCommonService;

	@GetMapping()
	@RequiresPermissions("cpe:surverStandardApplyTableInfo:surverStandardApplyTableInfo")
	String SurverStandardApplyTableInfo(){
	    return "system/surverStandardApplyTableInfo/surverStandardApplyTableInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverStandardApplyTableInfo:surverStandardApplyTableInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverStandardApplyTableInfoDO> surverStandardApplyTableInfoList = surverStandardApplyTableInfoService.list(query);
		int total = surverStandardApplyTableInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverStandardApplyTableInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverStandardApplyTableInfo:add")
	String add(){
	    return "system/surverStandardApplyTableInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverStandardApplyTableInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverStandardApplyTableInfoDO surverStandardApplyTableInfo = surverStandardApplyTableInfoService.get(id);
		model.addAttribute("surverStandardApplyTableInfo", surverStandardApplyTableInfo);
	    return "system/surverStandardApplyTableInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverStandardApplyTableInfo:add")
	public R save( SurverStandardApplyTableInfoDO surverStandardApplyTableInfo){
		AwardEnterpriseProjectDO projectDO = new AwardEnterpriseProjectDO();
		projectDO.setId(surverStandardApplyTableInfo.getProId());
		projectDO.setMajor(surverStandardApplyTableInfo.getApplyMajor());
		projectDO.setProSubType(AwardSurverSubTypeEnum.STANDARD.getSubType());
		projectDO.setChengguo(surverStandardApplyTableInfo.getGalleryName());
		awardEnterpriseProjectCommonService.update(projectDO);

		Long optUid = getUserId();
		surverStandardApplyTableInfo.setOptUid(optUid.intValue());
		Integer id = surverStandardApplyTableInfo.getId();
		if(id != null && id > 0) {
			int rst = surverStandardApplyTableInfoService.update(surverStandardApplyTableInfo);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverStandardApplyTableInfoService.save(surverStandardApplyTableInfo)>0){
			R r = R.ok();
			r.put("id", surverStandardApplyTableInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverStandardApplyTableInfo:edit")
	public R update( SurverStandardApplyTableInfoDO surverStandardApplyTableInfo){
		surverStandardApplyTableInfoService.update(surverStandardApplyTableInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverStandardApplyTableInfo:remove")
	public R remove( Integer id){
		if(surverStandardApplyTableInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverStandardApplyTableInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverStandardApplyTableInfoService.batchRemove(ids);
		return R.ok();
	}

}
