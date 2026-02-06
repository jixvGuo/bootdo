package com.bootdo.cpe.petroleum_engineering_award.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProGeneralSituationDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProGeneralSituationService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
 
@Controller
@RequestMapping("/petroleum_engineering_award/oilProGeneralSituation")
public class OilProGeneralSituationController extends BaseController {
	@Autowired
	private OilProGeneralSituationService oilProGeneralSituationService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilProGeneralSituation:oilProGeneralSituation")
	String OilProGeneralSituation(){
	    return "petroleum_engineering_award/oilProGeneralSituation/oilProGeneralSituation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilProGeneralSituation:oilProGeneralSituation")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilProGeneralSituationDO> oilProGeneralSituationList = oilProGeneralSituationService.list(query);
		int total = oilProGeneralSituationService.count(query);
		PageUtils pageUtils = new PageUtils(oilProGeneralSituationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilProGeneralSituation:add")
	String add(){
	    return "petroleum_engineering_award/oilProGeneralSituation/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilProGeneralSituation:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilProGeneralSituationDO oilProGeneralSituation = oilProGeneralSituationService.get(id);
		model.addAttribute("oilProGeneralSituation", oilProGeneralSituation);
	    return "petroleum_engineering_award/oilProGeneralSituation/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("petroleum_engineering_award:oilProGeneralSituation:add")
	public R save( OilProGeneralSituationDO oilProGeneralSituation){
		Long optUid = getUserId();
		oilProGeneralSituation.setOptUid(optUid == null ? "0" : optUid.toString());
		Integer id = oilProGeneralSituation.getId();
		if(id!= null &&  id> 0) {
			//修改
            R result = update(oilProGeneralSituation);
			result.put("id",id);
			return result;
		}
		if(oilProGeneralSituationService.save(oilProGeneralSituation)>0){
			R result = R.ok();
			result.put("id",oilProGeneralSituation.getId());
			return result;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("petroleum_engineering_award:oilProGeneralSituation:edit")
	public R update( OilProGeneralSituationDO oilProGeneralSituation){
		oilProGeneralSituationService.update(oilProGeneralSituation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("petroleum_engineering_award:oilProGeneralSituation:remove")
	public R remove( Integer id){
		if(oilProGeneralSituationService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilProGeneralSituation:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilProGeneralSituationService.batchRemove(ids);
		return R.ok();
	}
	
}
