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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.cpe.domain.SurverSoftApplyTableInfoDO;
import com.bootdo.cpe.service.SurverSoftApplyTableInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 *  石油工程建设优秀勘察设计计算机软件奖申报表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-29 20:51:20
 */

@Controller
@RequestMapping("/cpe/surverSoftApplyTableInfo")
public class SurverSoftApplyTableInfoController extends BaseSurverController {
	@Autowired
	private SurverSoftApplyTableInfoService surverSoftApplyTableInfoService;
	@Autowired
	private AwardEnterpriseProjectCommonService awardEnterpriseProjectCommonService;

	@GetMapping()
	@RequiresPermissions("cpe:surverSoftApplyTableInfo:surverSoftApplyTableInfo")
	String SurverSoftApplyTableInfo(){
	    return "cpe/surverSoftApplyTableInfo/surverSoftApplyTableInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverSoftApplyTableInfo:surverSoftApplyTableInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverSoftApplyTableInfoDO> surverSoftApplyTableInfoList = surverSoftApplyTableInfoService.list(query);
		int total = surverSoftApplyTableInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverSoftApplyTableInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverSoftApplyTableInfo:add")
	String add(){
	    return "cpe/surverSoftApplyTableInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverSoftApplyTableInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverSoftApplyTableInfoDO surverSoftApplyTableInfo = surverSoftApplyTableInfoService.get(id);
		model.addAttribute("surverSoftApplyTableInfo", surverSoftApplyTableInfo);
	    return "cpe/surverSoftApplyTableInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverSoftApplyTableInfo:add")
	public R save( SurverSoftApplyTableInfoDO surverSoftApplyTableInfo){
		AwardEnterpriseProjectDO projectDO = new AwardEnterpriseProjectDO();
		projectDO.setId(surverSoftApplyTableInfo.getProId());
		projectDO.setMajor(surverSoftApplyTableInfo.getApplyMajor());
		projectDO.setProSubType(AwardSurverSubTypeEnum.SOFTWARE.getSubType());
		projectDO.setChengguo(surverSoftApplyTableInfo.getSoftName());
		awardEnterpriseProjectCommonService.update(projectDO);

		Long optUid = getUserId();
		surverSoftApplyTableInfo.setOptUid(optUid.intValue());
		Integer id = surverSoftApplyTableInfo.getId();
		if(id != null && id > 0) {
			int rst = surverSoftApplyTableInfoService.update(surverSoftApplyTableInfo);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverSoftApplyTableInfoService.save(surverSoftApplyTableInfo)>0){
			R r = R.ok();
			r.put("id", surverSoftApplyTableInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverSoftApplyTableInfo:edit")
	public R update( SurverSoftApplyTableInfoDO surverSoftApplyTableInfo){
		surverSoftApplyTableInfoService.update(surverSoftApplyTableInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverSoftApplyTableInfo:remove")
	public R remove( Integer id){
		if(surverSoftApplyTableInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverSoftApplyTableInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverSoftApplyTableInfoService.batchRemove(ids);
		return R.ok();
	}

}
