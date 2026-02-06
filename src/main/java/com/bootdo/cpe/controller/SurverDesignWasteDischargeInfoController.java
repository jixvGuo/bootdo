package com.bootdo.cpe.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseSurverController;
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

import com.bootdo.cpe.domain.SurverDesignWasteDischargeInfoDO;
import com.bootdo.cpe.service.SurverDesignWasteDischargeInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 废水_液__废气_废渣排放量及排放指标对比
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:10
 */

@Controller
@RequestMapping("/cpe/surverDesignWasteDischargeInfo")
public class SurverDesignWasteDischargeInfoController extends BaseSurverController {
	@Autowired
	private SurverDesignWasteDischargeInfoService surverDesignWasteDischargeInfoService;

	@GetMapping()
	@RequiresPermissions("cpe:surverDesignWasteDischargeInfo:surverDesignWasteDischargeInfo")
	String SurverDesignWasteDischargeInfo(){
	    return "cpe/surverDesignWasteDischargeInfo/surverDesignWasteDischargeInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverDesignWasteDischargeInfo:surverDesignWasteDischargeInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverDesignWasteDischargeInfoDO> surverDesignWasteDischargeInfoList = surverDesignWasteDischargeInfoService.list(query);
		int total = surverDesignWasteDischargeInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverDesignWasteDischargeInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverDesignWasteDischargeInfo:add")
	String add(){
	    return "cpe/surverDesignWasteDischargeInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverDesignWasteDischargeInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverDesignWasteDischargeInfoDO surverDesignWasteDischargeInfo = surverDesignWasteDischargeInfoService.get(id);
		model.addAttribute("surverDesignWasteDischargeInfo", surverDesignWasteDischargeInfo);
	    return "cpe/surverDesignWasteDischargeInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverDesignWasteDischargeInfo:add")
	public R save( SurverDesignWasteDischargeInfoDO surverDesignWasteDischargeInfo){
		Long optUid = getUserId();
		surverDesignWasteDischargeInfo.setOptUid(optUid.intValue());
		Integer id = surverDesignWasteDischargeInfo.getId();
		if(id != null && id > 0) {
			int rst = surverDesignWasteDischargeInfoService.update(surverDesignWasteDischargeInfo);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverDesignWasteDischargeInfoService.save(surverDesignWasteDischargeInfo)>0){
			R r = R.ok();
			r.put("id", surverDesignWasteDischargeInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverDesignWasteDischargeInfo:edit")
	public R update( SurverDesignWasteDischargeInfoDO surverDesignWasteDischargeInfo){
		surverDesignWasteDischargeInfoService.update(surverDesignWasteDischargeInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesignWasteDischargeInfo:remove")
	public R remove( Integer id){
		if(surverDesignWasteDischargeInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesignWasteDischargeInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverDesignWasteDischargeInfoService.batchRemove(ids);
		return R.ok();
	}

}
