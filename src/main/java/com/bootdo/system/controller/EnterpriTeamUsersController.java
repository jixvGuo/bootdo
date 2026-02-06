package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
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

import com.bootdo.system.domain.EnterpriTeamUsersDO;
import com.bootdo.system.service.EnterpriTeamUsersService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

//import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

/**
 * 人员构成
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:15
 */
 
@Controller
@RequestMapping("/system/enterpriTeamUsers")
public class EnterpriTeamUsersController extends BaseScienceTeamController {
	@Autowired
	private EnterpriTeamUsersService enterpriTeamUsersService;
	
	@GetMapping()
	@RequiresPermissions("system:enterpriTeamUsers:enterpriTeamUsers")
	String EnterpriTeamUsers(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriTeamUsers/enterpriTeamUsers";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:enterpriTeamUsers:enterpriTeamUsers")
	public PageUtils list(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriTeamUsersDO> enterpriTeamUsersList = enterpriTeamUsersService.list(query);
		int total = enterpriTeamUsersService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriTeamUsersList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:enterpriTeamUsers:add")
	String add(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriTeamUsers/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:enterpriTeamUsers:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		EnterpriTeamUsersDO enterpriTeamUsers = enterpriTeamUsersService.get(id);
		map.addAttribute("enterpriTeamUsers", enterpriTeamUsers);
	    return "system/enterpriTeamUsers/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:enterpriTeamUsers:add")
	public R save(EnterpriTeamUsersDO enterpriTeamUsers, ModelMap map){
		map.put("proId", enterpriTeamUsers.getProId());
		map.put("taskId", enterpriTeamUsers.getTaskId());
		if(enterpriTeamUsers.getId() !=null && enterpriTeamUsers.getId() > 0) {
			enterpriTeamUsersService.update(enterpriTeamUsers);
			return R.ok();
		}else if(enterpriTeamUsersService.save(enterpriTeamUsers)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:enterpriTeamUsers:edit")
	public R update( EnterpriTeamUsersDO enterpriTeamUsers){
		enterpriTeamUsersService.update(enterpriTeamUsers);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:enterpriTeamUsers:remove")
	public R remove( Integer id){
		if(enterpriTeamUsersService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:enterpriTeamUsers:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriTeamUsersService.batchRemove(ids);
		return R.ok();
	}
	
}
