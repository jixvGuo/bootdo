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

import com.bootdo.cpe.domain.SurverDesiginMajorMaterialsUseInfoDO;
import com.bootdo.cpe.service.SurverDesiginMajorMaterialsUseInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 主要原材料消耗定额对比
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:09
 */

@Controller
@RequestMapping("/cpe/surverDesiginMajorMaterialsUseInfo")
public class SurverDesiginMajorMaterialsUseInfoController extends BaseSurverController {
	@Autowired
	private SurverDesiginMajorMaterialsUseInfoService surverDesiginMajorMaterialsUseInfoService;

	@GetMapping()
	@RequiresPermissions("cpe:surverDesiginMajorMaterialsUseInfo:surverDesiginMajorMaterialsUseInfo")
	String SurverDesiginMajorMaterialsUseInfo(){
	    return "cpe/surverDesiginMajorMaterialsUseInfo/surverDesiginMajorMaterialsUseInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverDesiginMajorMaterialsUseInfo:surverDesiginMajorMaterialsUseInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverDesiginMajorMaterialsUseInfoDO> surverDesiginMajorMaterialsUseInfoList = surverDesiginMajorMaterialsUseInfoService.list(query);
		int total = surverDesiginMajorMaterialsUseInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverDesiginMajorMaterialsUseInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverDesiginMajorMaterialsUseInfo:add")
	String add(){
	    return "cpe/surverDesiginMajorMaterialsUseInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverDesiginMajorMaterialsUseInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverDesiginMajorMaterialsUseInfoDO surverDesiginMajorMaterialsUseInfo = surverDesiginMajorMaterialsUseInfoService.get(id);
		model.addAttribute("surverDesiginMajorMaterialsUseInfo", surverDesiginMajorMaterialsUseInfo);
	    return "cpe/surverDesiginMajorMaterialsUseInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverDesiginMajorMaterialsUseInfo:add")
	public R save( SurverDesiginMajorMaterialsUseInfoDO surverDesiginMajorMaterialsUseInfo){
		Long optUid = getUserId();
		surverDesiginMajorMaterialsUseInfo.setOptUid(optUid.intValue());
		Integer id = surverDesiginMajorMaterialsUseInfo.getId();
		if(id != null && id > 0) {
			int rst = surverDesiginMajorMaterialsUseInfoService.update(surverDesiginMajorMaterialsUseInfo);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverDesiginMajorMaterialsUseInfoService.save(surverDesiginMajorMaterialsUseInfo)>0){
			R r = R.ok();
			r.put("id", surverDesiginMajorMaterialsUseInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverDesiginMajorMaterialsUseInfo:edit")
	public R update( SurverDesiginMajorMaterialsUseInfoDO surverDesiginMajorMaterialsUseInfo){
		surverDesiginMajorMaterialsUseInfoService.update(surverDesiginMajorMaterialsUseInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesiginMajorMaterialsUseInfo:remove")
	public R remove( Integer id){
		if(surverDesiginMajorMaterialsUseInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesiginMajorMaterialsUseInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverDesiginMajorMaterialsUseInfoService.batchRemove(ids);
		return R.ok();
	}

}
