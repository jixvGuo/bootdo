package com.bootdo.cpe.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseSurverController;
import com.bootdo.cpe.domain.AwardEnterpriseProjectDO;
import com.bootdo.cpe.domain.SurverConsultApplyProjectProfileDO;
import com.bootdo.cpe.service.SurverConsultApplyProjectProfileService;
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
@RequestMapping("/cpe/surverConsultApplyProjectProfile")
public class SurverConsultApplyProjectProfileController extends BaseSurverController {
	@Autowired
	private SurverConsultApplyProjectProfileService surverConsultApplyProjectProfileService;

	@GetMapping()
	@RequiresPermissions("cpe:surverConsultApplyProjectProfile:surverConsultApplyProjectProfile")
	String SurverConsultApplyProjectProfile(){
	    return "system/surverConsultApplyProjectProfile/surverConsultApplyProjectProfile";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverConsultApplyProjectProfile:surverConsultApplyProjectProfile")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverConsultApplyProjectProfileDO> surverConsultApplyProjectProfileList = surverConsultApplyProjectProfileService.list(query);
		int total = surverConsultApplyProjectProfileService.count(query);
		PageUtils pageUtils = new PageUtils(surverConsultApplyProjectProfileList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverConsultApplyProjectProfile:add")
	String add(){
	    return "system/surverConsultApplyProjectProfile/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverConsultApplyProjectProfile:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverConsultApplyProjectProfileDO surverConsultApplyProjectProfile = surverConsultApplyProjectProfileService.get(id);
		model.addAttribute("surverConsultApplyProjectProfile", surverConsultApplyProjectProfile);
	    return "system/surverConsultApplyProjectProfile/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverConsultApplyProjectProfile:add")
	public R save( SurverConsultApplyProjectProfileDO surverConsultApplyProjectProfile){
		Long optUid = getUserId();
		surverConsultApplyProjectProfile.setOptUid(optUid.intValue());
		Integer id = surverConsultApplyProjectProfile.getId();
		if(id != null && id > 0) {
			int rst = surverConsultApplyProjectProfileService.update(surverConsultApplyProjectProfile);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverConsultApplyProjectProfileService.save(surverConsultApplyProjectProfile)>0){
			R r= R.ok();
			r.put("id", surverConsultApplyProjectProfile.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverConsultApplyProjectProfile:edit")
	public R update( SurverConsultApplyProjectProfileDO surverConsultApplyProjectProfile){
		surverConsultApplyProjectProfileService.update(surverConsultApplyProjectProfile);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverConsultApplyProjectProfile:remove")
	public R remove( Integer id){
		if(surverConsultApplyProjectProfileService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverConsultApplyProjectProfile:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverConsultApplyProjectProfileService.batchRemove(ids);
		return R.ok();
	}

}
