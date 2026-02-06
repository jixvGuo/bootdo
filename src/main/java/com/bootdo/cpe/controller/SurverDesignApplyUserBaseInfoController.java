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

import com.bootdo.cpe.domain.SurverDesignApplyUserBaseInfoDO;
import com.bootdo.cpe.service.SurverDesignApplyUserBaseInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 申报人基本信息
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:10
 */

@Controller
@RequestMapping("/cpe/surverDesignApplyUserBaseInfo")
public class SurverDesignApplyUserBaseInfoController  extends BaseSurverController {
	@Autowired
	private SurverDesignApplyUserBaseInfoService surverDesignApplyUserBaseInfoService;

	@GetMapping()
	@RequiresPermissions("cpe:surverDesignApplyUserBaseInfo:surverDesignApplyUserBaseInfo")
	String SurverDesignApplyUserBaseInfo(){
	    return "cpe/surverDesignApplyUserBaseInfo/surverDesignApplyUserBaseInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverDesignApplyUserBaseInfo:surverDesignApplyUserBaseInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverDesignApplyUserBaseInfoDO> surverDesignApplyUserBaseInfoList = surverDesignApplyUserBaseInfoService.list(query);
		int total = surverDesignApplyUserBaseInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverDesignApplyUserBaseInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverDesignApplyUserBaseInfo:add")
	String add(){
	    return "cpe/surverDesignApplyUserBaseInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverDesignApplyUserBaseInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverDesignApplyUserBaseInfoDO surverDesignApplyUserBaseInfo = surverDesignApplyUserBaseInfoService.get(id);
		model.addAttribute("surverDesignApplyUserBaseInfo", surverDesignApplyUserBaseInfo);
	    return "cpe/surverDesignApplyUserBaseInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverDesignApplyUserBaseInfo:add")
	public R save( SurverDesignApplyUserBaseInfoDO surverDesignApplyUserBaseInfo){
		if(surverDesignApplyUserBaseInfoService.save(surverDesignApplyUserBaseInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverDesignApplyUserBaseInfo:edit")
	public R update( SurverDesignApplyUserBaseInfoDO surverDesignApplyUserBaseInfo){
		surverDesignApplyUserBaseInfoService.update(surverDesignApplyUserBaseInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesignApplyUserBaseInfo:remove")
	public R remove( Integer id){
		if(surverDesignApplyUserBaseInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesignApplyUserBaseInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverDesignApplyUserBaseInfoService.batchRemove(ids);
		return R.ok();
	}

}
