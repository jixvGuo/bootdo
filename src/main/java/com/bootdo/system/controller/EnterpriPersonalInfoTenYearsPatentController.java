package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseSciencePersonalController;
import com.bootdo.common.utils.JSONUtils;
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

import com.bootdo.system.domain.EnterpriPersonalInfoTenYearsPatentDO;
import com.bootdo.system.service.EnterpriPersonalInfoTenYearsPatentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 近十年内由申报人参与完成并取得授权的专利 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */
 
@Controller
@RequestMapping("/system/enterpriPersonalInfoTenYearsPatent")
public class EnterpriPersonalInfoTenYearsPatentController extends BaseSciencePersonalController {
	@Autowired
	private EnterpriPersonalInfoTenYearsPatentService enterpriPersonalInfoTenYearsPatentService;
	
	@GetMapping()
	@RequiresPermissions("system:enterpriPersonalInfoTenYearsPatent:enterpriPersonalInfoTenYearsPatent")
	String EnterpriPersonalInfoTenYearsPatent(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriPersonalInfoTenYearsPatent/enterpriPersonalInfoTenYearsPatent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:enterpriPersonalInfoTenYearsPatent:list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriPersonalInfoTenYearsPatentDO> enterpriPersonalInfoTenYearsPatentList = enterpriPersonalInfoTenYearsPatentService.list(query);
		int total = enterpriPersonalInfoTenYearsPatentService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriPersonalInfoTenYearsPatentList, total);

		System.out.println("===" + JSONUtils.beanToJson(pageUtils));
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:enterpriPersonalInfoTenYearsPatent:add")
	String add(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriPersonalInfoTenYearsPatent/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,String proId,String taskId,String applyId,@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		EnterpriPersonalInfoTenYearsPatentDO enterpriPersonalInfoTenYearsPatent = enterpriPersonalInfoTenYearsPatentService.get(id);

		map.addAttribute("tenYearsPatent", enterpriPersonalInfoTenYearsPatent);
    	map.addAttribute("isEdit", "0");

		return "act/award/chengguo_personal/personal_ten_years_patent";
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:enterpriPersonalInfoTenYearsPatent:add")
	public R save( EnterpriPersonalInfoTenYearsPatentDO enterpriPersonalInfoTenYearsPatent){
		if(enterpriPersonalInfoTenYearsPatent.getId() != null && enterpriPersonalInfoTenYearsPatent.getId() > 0) {
			enterpriPersonalInfoTenYearsPatentService.update(enterpriPersonalInfoTenYearsPatent);
			return R.ok();
		}
		if(enterpriPersonalInfoTenYearsPatentService.save(enterpriPersonalInfoTenYearsPatent)>0){
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/personal_userId")
	public R personalBusinessList(@RequestParam Map<String, Object> params) {


      return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( EnterpriPersonalInfoTenYearsPatentDO enterpriPersonalInfoTenYearsPatent){
		enterpriPersonalInfoTenYearsPatentService.update(enterpriPersonalInfoTenYearsPatent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:enterpriPersonalInfoTenYearsPatent:remove")
	public R remove( Integer id){
		if(enterpriPersonalInfoTenYearsPatentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:enterpriPersonalInfoTenYearsPatent:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriPersonalInfoTenYearsPatentService.batchRemove(ids);
		return R.ok();
	}
	
}
