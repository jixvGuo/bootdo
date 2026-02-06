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

import com.bootdo.cpe.domain.SurverDesignCommonProUseInfoDO;
import com.bootdo.cpe.service.SurverDesignCommonProUseInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 主要公用工程消耗定额对比
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:10
 */

@Controller
@RequestMapping("/cpe/surverDesignCommonProUseInfo")
public class SurverDesignCommonProUseInfoController extends BaseSurverController {
	@Autowired
	private SurverDesignCommonProUseInfoService surverDesignCommonProUseInfoService;

	@GetMapping()
	@RequiresPermissions("cpe:surverDesignCommonProUseInfo:surverDesignCommonProUseInfo")
	String SurverDesignCommonProUseInfo(){
	    return "cpe/surverDesignCommonProUseInfo/surverDesignCommonProUseInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverDesignCommonProUseInfo:surverDesignCommonProUseInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverDesignCommonProUseInfoDO> surverDesignCommonProUseInfoList = surverDesignCommonProUseInfoService.list(query);
		int total = surverDesignCommonProUseInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverDesignCommonProUseInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverDesignCommonProUseInfo:add")
	String add(){
	    return "cpe/surverDesignCommonProUseInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverDesignCommonProUseInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverDesignCommonProUseInfoDO surverDesignCommonProUseInfo = surverDesignCommonProUseInfoService.get(id);
		model.addAttribute("surverDesignCommonProUseInfo", surverDesignCommonProUseInfo);
	    return "cpe/surverDesignCommonProUseInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverDesignCommonProUseInfo:add")
	public R save( SurverDesignCommonProUseInfoDO surverDesignCommonProUseInfo){
		Long optUid = getUserId();
		surverDesignCommonProUseInfo.setOptUid(optUid.intValue());
		Integer id = surverDesignCommonProUseInfo.getId();
		if(id != null && id > 0) {
			int rst = surverDesignCommonProUseInfoService.update(surverDesignCommonProUseInfo);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverDesignCommonProUseInfoService.save(surverDesignCommonProUseInfo)>0){
			R r = R.ok();
			r.put("id", surverDesignCommonProUseInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverDesignCommonProUseInfo:edit")
	public R update( SurverDesignCommonProUseInfoDO surverDesignCommonProUseInfo){
		surverDesignCommonProUseInfoService.update(surverDesignCommonProUseInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesignCommonProUseInfo:remove")
	public R remove( Integer id){
		if(surverDesignCommonProUseInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesignCommonProUseInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverDesignCommonProUseInfoService.batchRemove(ids);
		return R.ok();
	}

}
