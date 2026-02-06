package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseScienceTeamController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.EnterpriTeamMainUsersDO;
import com.bootdo.system.service.EnterpriTeamMainUsersService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 团队主要成员
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-05 08:21:09
 */
 
@Controller
@RequestMapping("/system/enterpriTeamMainUsers")
public class EnterpriTeamMainUsersController extends BaseScienceTeamController {
	@Autowired
	private EnterpriTeamMainUsersService enterpriTeamMainUsersService;
	
	@GetMapping()
	@RequiresPermissions("system:enterpriTeamMainUsers:enterpriTeamMainUsers")
	String EnterpriTeamMainUsers(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriTeamMainUsers/enterpriTeamMainUsers";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:enterpriTeamMainUsers:enterpriTeamMainUsers")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriTeamMainUsersDO> enterpriTeamMainUsersList = enterpriTeamMainUsersService.list(query);
		int total = enterpriTeamMainUsersService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriTeamMainUsersList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:enterpriTeamMainUsers:add")
	String add(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriTeamMainUsers/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:enterpriTeamMainUsers:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		EnterpriTeamMainUsersDO enterpriTeamMainUsers = enterpriTeamMainUsersService.get(id);
		map.addAttribute("enterpriTeamMainUsers", enterpriTeamMainUsers);
	    return "system/enterpriTeamMainUsers/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:enterpriTeamMainUsers:add")
	public R save( EnterpriTeamMainUsersDO enterpriTeamMainUsers){
		if(enterpriTeamMainUsersService.save(enterpriTeamMainUsers)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:enterpriTeamMainUsers:edit")
	public R update( EnterpriTeamMainUsersDO enterpriTeamMainUsers){
		enterpriTeamMainUsersService.update(enterpriTeamMainUsers);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:enterpriTeamMainUsers:remove")
	public R remove( Integer id){
		if(enterpriTeamMainUsersService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:enterpriTeamMainUsers:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriTeamMainUsersService.batchRemove(ids);
		return R.ok();
	}
	
}
