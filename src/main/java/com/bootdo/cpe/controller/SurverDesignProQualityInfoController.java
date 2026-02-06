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

import com.bootdo.cpe.domain.SurverDesignProQualityInfoDO;
import com.bootdo.cpe.service.SurverDesignProQualityInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 产品质量指标对比
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:10
 */

@Controller
@RequestMapping("/cpe/surverDesignProQualityInfo")
public class SurverDesignProQualityInfoController extends BaseSurverController {
	@Autowired
	private SurverDesignProQualityInfoService surverDesignProQualityInfoService;

	@GetMapping()
	@RequiresPermissions("cpe:surverDesignProQualityInfo:surverDesignProQualityInfo")
	String SurverDesignProQualityInfo(){
	    return "cpe/surverDesignProQualityInfo/surverDesignProQualityInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverDesignProQualityInfo:surverDesignProQualityInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverDesignProQualityInfoDO> surverDesignProQualityInfoList = surverDesignProQualityInfoService.list(query);
		int total = surverDesignProQualityInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverDesignProQualityInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverDesignProQualityInfo:add")
	String add(){
	    return "cpe/surverDesignProQualityInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverDesignProQualityInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverDesignProQualityInfoDO surverDesignProQualityInfo = surverDesignProQualityInfoService.get(id);
		model.addAttribute("surverDesignProQualityInfo", surverDesignProQualityInfo);
	    return "cpe/surverDesignProQualityInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverDesignProQualityInfo:add")
	public R save( SurverDesignProQualityInfoDO surverDesignProQualityInfo){
		Long optUid = getUserId();
		surverDesignProQualityInfo.setOptUid(optUid.intValue());
		Integer id = surverDesignProQualityInfo.getId();
		if(id != null && id > 0) {
			int rst = surverDesignProQualityInfoService.update(surverDesignProQualityInfo);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverDesignProQualityInfoService.save(surverDesignProQualityInfo)>0){
			R r = R.ok();
			r.put("id", surverDesignProQualityInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverDesignProQualityInfo:edit")
	public R update( SurverDesignProQualityInfoDO surverDesignProQualityInfo){
		surverDesignProQualityInfoService.update(surverDesignProQualityInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesignProQualityInfo:remove")
	public R remove( Integer id){
		if(surverDesignProQualityInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesignProQualityInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverDesignProQualityInfoService.batchRemove(ids);
		return R.ok();
	}

}
