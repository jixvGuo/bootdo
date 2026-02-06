package com.bootdo.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.system.domain.EnterpriseChengguoBaseInfoDO;
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

import com.bootdo.system.domain.EnterpriTeamUsersListDO;
import com.bootdo.system.service.EnterpriTeamUsersListService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 团队人员构成-主要成员
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:15
 */
 
@Controller
@RequestMapping("/system/enterpriTeamUsersList")
public class EnterpriTeamUsersListController extends BaseScienceTeamController {
	@Autowired
	private EnterpriTeamUsersListService enterpriTeamUsersListService;
	
	@GetMapping()
	@RequiresPermissions("system:enterpriTeamUsersList:enterpriTeamUsersList")
	String EnterpriTeamUsersList(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriTeamUsersList/enterpriTeamUsersList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:enterpriTeamUsersList:enterpriTeamUsersList")
	public PageUtils list(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriTeamUsersListDO> enterpriTeamUsersListList = enterpriTeamUsersListService.list(query);
		int total = enterpriTeamUsersListService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriTeamUsersListList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/get")
	@RequiresPermissions("system:enterpriTeamUsersList:enterpriTeamUsersList")
	public R get(Integer id){
	    Map<String,Object> params = new HashMap<>();
	    params.put("id",id);
		//查询列表数据
		List<EnterpriTeamUsersListDO> enterpriTeamUsersListList = enterpriTeamUsersListService.list(params);
		R result = R.ok();
		result.put("userInfo",enterpriTeamUsersListList.size() > 0 ? enterpriTeamUsersListList.get(0) : new EnterpriseChengguoBaseInfoDO());
		return result;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:enterpriTeamUsersList:add")
	String add(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriTeamUsersList/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:enterpriTeamUsersList:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		EnterpriTeamUsersListDO enterpriTeamUsersList = enterpriTeamUsersListService.get(id);
		map.addAttribute("enterpriTeamUsersList", enterpriTeamUsersList);
	    return "system/enterpriTeamUsersList/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:enterpriTeamUsersList:add")
	public R save( EnterpriTeamUsersListDO enterpriTeamUsersList){
		Long uid = getUserId();
		enterpriTeamUsersList.setOptUid(uid);
		if(enterpriTeamUsersList.getId() != null &&enterpriTeamUsersList.getId() > 0) {
			enterpriTeamUsersListService.update(enterpriTeamUsersList);
			return R.ok();
		}else if(enterpriTeamUsersListService.save(enterpriTeamUsersList)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:enterpriTeamUsersList:edit")
	public R update( EnterpriTeamUsersListDO enterpriTeamUsersList){
		enterpriTeamUsersListService.update(enterpriTeamUsersList);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:enterpriTeamUsersList:remove")
	public R remove( Integer id){
		if(enterpriTeamUsersListService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:enterpriTeamUsersList:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriTeamUsersListService.batchRemove(ids);
		return R.ok();
	}
	
}
