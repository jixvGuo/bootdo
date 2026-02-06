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

import com.bootdo.cpe.domain.SurverDesignApplyTableInfoDO;
import com.bootdo.cpe.service.SurverDesignApplyTableInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 *  石油工程建设优秀设计奖项目申报表表格
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:09
 */

@Controller
@RequestMapping("/cpe/surverDesignApplyTableInfo")
public class SurverDesignApplyTableInfoController extends BaseSurverController {
	@Autowired
	private SurverDesignApplyTableInfoService surverDesignApplyTableInfoService;

	@Autowired
	private AwardEnterpriseProjectCommonService awardEnterpriseProjectCommonService;

	@GetMapping()
	@RequiresPermissions("cpe:surverDesignApplyTableInfo:surverDesignApplyTableInfo")
	String SurverDesignApplyTableInfo(){
	    return "cpe/surverDesignApplyTableInfo/surverDesignApplyTableInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverDesignApplyTableInfo:surverDesignApplyTableInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverDesignApplyTableInfoDO> surverDesignApplyTableInfoList = surverDesignApplyTableInfoService.list(query);
		int total = surverDesignApplyTableInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverDesignApplyTableInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverDesignApplyTableInfo:add")
	String add(){
	    return "cpe/surverDesignApplyTableInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverDesignApplyTableInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverDesignApplyTableInfoDO surverDesignApplyTableInfo = surverDesignApplyTableInfoService.get(id);
		model.addAttribute("surverDesignApplyTableInfo", surverDesignApplyTableInfo);
	    return "cpe/surverDesignApplyTableInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverDesignApplyTableInfo:add")
	public R save( SurverDesignApplyTableInfoDO surverDesignApplyTableInfo){

		AwardEnterpriseProjectDO projectDO = new AwardEnterpriseProjectDO();
		projectDO.setId(surverDesignApplyTableInfo.getProId());
		projectDO.setMajor(surverDesignApplyTableInfo.getApplyMajor());
		projectDO.setProSubType(AwardSurverSubTypeEnum.DESITN.getSubType());
		projectDO.setChengguo(surverDesignApplyTableInfo.getProName());
		awardEnterpriseProjectCommonService.update(projectDO);

		Long optUid = getUserId();
		surverDesignApplyTableInfo.setOptUid(optUid.intValue());
		Integer id = surverDesignApplyTableInfo.getId();
		if(id != null && id > 0) {
			int rst = surverDesignApplyTableInfoService.update(surverDesignApplyTableInfo);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverDesignApplyTableInfoService.save(surverDesignApplyTableInfo)>0){
		    R r = R.ok();
		    r.put("id", surverDesignApplyTableInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverDesignApplyTableInfo:edit")
	public R update( SurverDesignApplyTableInfoDO surverDesignApplyTableInfo){
		surverDesignApplyTableInfoService.update(surverDesignApplyTableInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesignApplyTableInfo:remove")
	public R remove( Integer id){
		if(surverDesignApplyTableInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesignApplyTableInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverDesignApplyTableInfoService.batchRemove(ids);
		return R.ok();
	}

}
