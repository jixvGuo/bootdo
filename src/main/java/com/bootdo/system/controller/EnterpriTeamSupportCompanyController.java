package com.bootdo.system.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.EnterpriTeamSupportCompanyDO;
import com.bootdo.system.service.EnterpriTeamSupportCompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 支持单位情况
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-05 08:21:09
 */
 
@Controller
@RequestMapping("/system/enterpriTeamSupportCompany")
public class EnterpriTeamSupportCompanyController extends BaseScienceTeamController {
	String prefix = "act/award/chengguo_team";

	@Autowired
	private EnterpriTeamSupportCompanyService enterpriTeamSupportCompanyService;
	
	@GetMapping()
	@RequiresPermissions("system:enterpriTeamSupportCompany:enterpriTeamSupportCompany")
	String EnterpriTeamSupportCompany(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriTeamSupportCompany/enterpriTeamSupportCompany";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriTeamSupportCompanyDO> enterpriTeamSupportCompanyList = enterpriTeamSupportCompanyService.list(query);
		int total = enterpriTeamSupportCompanyService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriTeamSupportCompanyList, total);
		return pageUtils;
	}

	@GetMapping("/toList")
	String toList(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
		packageAwardTaskId(map, params);
		Query query = new Query(params);
		List<EnterpriTeamSupportCompanyDO> enterpriTeamSupportCompanyList = enterpriTeamSupportCompanyService.list(query);
        map.put("list", enterpriTeamSupportCompanyList);
		return prefix + "/support_company_list";
	}
	
	@GetMapping("/add")
	String add(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
		packageAwardTaskId(map, params);
		map.put("enterpriTeamSupportCompany", new EnterpriTeamSupportCompanyDO());
		return prefix + "/support_company";
	}




	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:enterpriTeamSupportCompany:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		EnterpriTeamSupportCompanyDO enterpriTeamSupportCompany = enterpriTeamSupportCompanyService.get(id);
		map.put("enterpriTeamSupportCompany", enterpriTeamSupportCompany);
		return prefix + "/support_company";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:enterpriTeamSupportCompany:add")
	public R save(EnterpriTeamSupportCompanyDO enterpriTeamSupportCompany){
		Integer id = enterpriTeamSupportCompany.getId();
		enterpriTeamSupportCompany.setOptUid(getUserId());
		if (id != null && id > 0) {
			enterpriTeamSupportCompanyService.update(enterpriTeamSupportCompany);
			return R.ok();
		}
		if(enterpriTeamSupportCompanyService.save(enterpriTeamSupportCompany)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:enterpriTeamSupportCompany:edit")
	public R update( EnterpriTeamSupportCompanyDO enterpriTeamSupportCompany){
		enterpriTeamSupportCompanyService.update(enterpriTeamSupportCompany);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:enterpriTeamSupportCompany:remove")
	public R remove( Integer id){
		if(enterpriTeamSupportCompanyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:enterpriTeamSupportCompany:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriTeamSupportCompanyService.batchRemove(ids);
		return R.ok();
	}
	
}
