package com.bootdo.cpe.petroleum_engineering_award.controller;

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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityConfirmFileDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilQualityConfirmFileService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 优质工程证实性文件
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-14 22:54:14
 */
 
@Controller
@RequestMapping("/petroleum_engineering_award/oilQualityConfirmFile")
public class OilQualityConfirmFileController {
	@Autowired
	private OilQualityConfirmFileService oilQualityConfirmFileService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilQualityConfirmFile:oilQualityConfirmFile")
	String OilQualityConfirmFile(){
	    return "petroleum_engineering_award/oilQualityConfirmFile/oilQualityConfirmFile";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilQualityConfirmFile:oilQualityConfirmFile")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilQualityConfirmFileDO> oilQualityConfirmFileList = oilQualityConfirmFileService.list(query);
		int total = oilQualityConfirmFileService.count(query);
		PageUtils pageUtils = new PageUtils(oilQualityConfirmFileList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilQualityConfirmFile:add")
	String add(){
	    return "petroleum_engineering_award/oilQualityConfirmFile/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilQualityConfirmFile:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilQualityConfirmFileDO oilQualityConfirmFile = oilQualityConfirmFileService.get(id);
		model.addAttribute("oilQualityConfirmFile", oilQualityConfirmFile);
	    return "petroleum_engineering_award/oilQualityConfirmFile/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("petroleum_engineering_award:oilQualityConfirmFile:add")
	public R save( OilQualityConfirmFileDO oilQualityConfirmFile){
		if(oilQualityConfirmFileService.save(oilQualityConfirmFile)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("petroleum_engineering_award:oilQualityConfirmFile:edit")
	public R update( OilQualityConfirmFileDO oilQualityConfirmFile){
		oilQualityConfirmFileService.update(oilQualityConfirmFile);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilQualityConfirmFile:remove")
	public R remove( Integer id){
		if(oilQualityConfirmFileService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilQualityConfirmFile:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilQualityConfirmFileService.batchRemove(ids);
		return R.ok();
	}
	
}
