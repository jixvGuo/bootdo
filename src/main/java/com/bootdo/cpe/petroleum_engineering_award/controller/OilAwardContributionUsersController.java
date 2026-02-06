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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardContributionUsersDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilAwardContributionUsersService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 贡献
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
 
@Controller
@RequestMapping("/petroleum_engineering_award/oilAwardContributionUsers")
public class OilAwardContributionUsersController extends BaseController {
	@Autowired
	private OilAwardContributionUsersService oilAwardContributionUsersService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilAwardContributionUsers:oilAwardContributionUsers")
	String OilAwardContributionUsers(){
	    return "petroleum_engineering_award/oilAwardContributionUsers/oilAwardContributionUsers";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilAwardContributionUsers:oilAwardContributionUsers")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilAwardContributionUsersDO> oilAwardContributionUsersList = oilAwardContributionUsersService.list(query);
		int total = oilAwardContributionUsersService.count(query);
		PageUtils pageUtils = new PageUtils(oilAwardContributionUsersList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardContributionUsers:add")
	String add(){
	    return "petroleum_engineering_award/oilAwardContributionUsers/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilAwardContributionUsers:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilAwardContributionUsersDO oilAwardContributionUsers = oilAwardContributionUsersService.get(id);
		model.addAttribute("oilAwardContributionUsers", oilAwardContributionUsers);
	    return "petroleum_engineering_award/oilAwardContributionUsers/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardContributionUsers:add")
	public R save( OilAwardContributionUsersDO oilAwardContributionUsers){
		Long optUid = getUserId();
		oilAwardContributionUsers.setOptUid(optUid == null ? "0" : optUid.toString());
		if(oilAwardContributionUsersService.save(oilAwardContributionUsers)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("petroleum_engineering_award:oilAwardContributionUsers:edit")
	public R update( OilAwardContributionUsersDO oilAwardContributionUsers){
		oilAwardContributionUsersService.update(oilAwardContributionUsers);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("petroleum_engineering_award:oilAwardContributionUsers:remove")
	public R remove( Integer id){
		if(oilAwardContributionUsersService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilAwardContributionUsers:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilAwardContributionUsersService.batchRemove(ids);
		return R.ok();
	}
	
}
