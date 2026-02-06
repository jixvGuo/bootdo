package com.bootdo.cpe.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.cpe.domain.SpecialistScoreOverDO;
import com.bootdo.cpe.service.SpecialistScoreOverService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 专家打分结束记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-10 01:08:19
 */
 
@Controller
@RequestMapping("/cpe/specialistScoreOver")
public class SpecialistScoreOverController {
	@Autowired
	private SpecialistScoreOverService specialistScoreOverService;
	
	@GetMapping()
	@RequiresPermissions("cpe:specialistScoreOver:specialistScoreOver")
	String SpecialistScoreOver(){
	    return "cpe/specialistScoreOver/specialistScoreOver";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:specialistScoreOver:specialistScoreOver")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SpecialistScoreOverDO> specialistScoreOverList = specialistScoreOverService.list(query);
		int total = specialistScoreOverService.count(query);
		PageUtils pageUtils = new PageUtils(specialistScoreOverList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cpe:specialistScoreOver:add")
	String add(){
	    return "cpe/specialistScoreOver/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:specialistScoreOver:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SpecialistScoreOverDO specialistScoreOver = specialistScoreOverService.get(id);
		model.addAttribute("specialistScoreOver", specialistScoreOver);
	    return "cpe/specialistScoreOver/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:specialistScoreOver:add")
	public R save( SpecialistScoreOverDO specialistScoreOver){
		if(specialistScoreOverService.save(specialistScoreOver)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:specialistScoreOver:edit")
	public R update( SpecialistScoreOverDO specialistScoreOver){
		specialistScoreOverService.update(specialistScoreOver);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:specialistScoreOver:remove")
	public R remove( Integer id){
		if(specialistScoreOverService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:specialistScoreOver:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		specialistScoreOverService.batchRemove(ids);
		return R.ok();
	}
	
}
