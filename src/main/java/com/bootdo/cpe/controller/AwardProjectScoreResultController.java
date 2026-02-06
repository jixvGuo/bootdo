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

import com.bootdo.cpe.domain.AwardProjectScoreResultDO;
import com.bootdo.cpe.service.AwardProjectScoreResultService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 项目的打分结果记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-10 02:22:28
 */
 
@Controller
@RequestMapping("/cpe/awardProjectScoreResult")
public class AwardProjectScoreResultController {
	@Autowired
	private AwardProjectScoreResultService awardProjectScoreResultService;
	
	@GetMapping()
	@RequiresPermissions("cpe:awardProjectScoreResult:awardProjectScoreResult")
	String AwardProjectScoreResult(){
	    return "cpe/awardProjectScoreResult/awardProjectScoreResult";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:awardProjectScoreResult:awardProjectScoreResult")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AwardProjectScoreResultDO> awardProjectScoreResultList = awardProjectScoreResultService.list(query);
		int total = awardProjectScoreResultService.count(query);
		PageUtils pageUtils = new PageUtils(awardProjectScoreResultList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cpe:awardProjectScoreResult:add")
	String add(){
	    return "cpe/awardProjectScoreResult/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:awardProjectScoreResult:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AwardProjectScoreResultDO awardProjectScoreResult = awardProjectScoreResultService.get(id);
		model.addAttribute("awardProjectScoreResult", awardProjectScoreResult);
	    return "cpe/awardProjectScoreResult/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:awardProjectScoreResult:add")
	public R save( AwardProjectScoreResultDO awardProjectScoreResult){
		if(awardProjectScoreResultService.save(awardProjectScoreResult)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:awardProjectScoreResult:edit")
	public R update( AwardProjectScoreResultDO awardProjectScoreResult){
		awardProjectScoreResultService.update(awardProjectScoreResult);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:awardProjectScoreResult:remove")
	public R remove( Integer id){
		if(awardProjectScoreResultService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:awardProjectScoreResult:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		awardProjectScoreResultService.batchRemove(ids);
		return R.ok();
	}
	
}
