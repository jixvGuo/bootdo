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

import com.bootdo.cpe.domain.SurverDesiginContributeUserInfoDO;
import com.bootdo.cpe.service.SurverDesiginContributeUserInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 在本项目中做出贡献的主要人员情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:09
 */

@Controller
@RequestMapping("/cpe/surverDesiginContributeUserInfo")
public class SurverDesiginContributeUserInfoController extends BaseSurverController {
	@Autowired
	private SurverDesiginContributeUserInfoService surverDesiginContributeUserInfoService;

	@GetMapping()
	@RequiresPermissions("cpe:surverDesiginContributeUserInfo:surverDesiginContributeUserInfo")
	String SurverDesiginContributeUserInfo(){
	    return "cpe/surverDesiginContributeUserInfo/surverDesiginContributeUserInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverDesiginContributeUserInfo:surverDesiginContributeUserInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverDesiginContributeUserInfoDO> surverDesiginContributeUserInfoList = surverDesiginContributeUserInfoService.list(query);
		int total = surverDesiginContributeUserInfoService.count(query);
		PageUtils pageUtils = new PageUtils(surverDesiginContributeUserInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverDesiginContributeUserInfo:add")
	String add(){
	    return "cpe/surverDesiginContributeUserInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverDesiginContributeUserInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverDesiginContributeUserInfoDO surverDesiginContributeUserInfo = surverDesiginContributeUserInfoService.get(id);
		model.addAttribute("surverDesiginContributeUserInfo", surverDesiginContributeUserInfo);
	    return "cpe/surverDesiginContributeUserInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverDesiginContributeUserInfo:add")
	public R save( SurverDesiginContributeUserInfoDO surverDesiginContributeUserInfo){
		Integer id = surverDesiginContributeUserInfo.getId();
		if(id != null && id > 0) {
			int rst = surverDesiginContributeUserInfoService.update(surverDesiginContributeUserInfo);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverDesiginContributeUserInfoService.save(surverDesiginContributeUserInfo)>0){
			R r = R.ok();
			r.put("id", surverDesiginContributeUserInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverDesiginContributeUserInfo:edit")
	public R update( SurverDesiginContributeUserInfoDO surverDesiginContributeUserInfo){
		surverDesiginContributeUserInfoService.update(surverDesiginContributeUserInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesiginContributeUserInfo:remove")
	public R remove( Integer id){
		if(surverDesiginContributeUserInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverDesiginContributeUserInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverDesiginContributeUserInfoService.batchRemove(ids);
		return R.ok();
	}

}
