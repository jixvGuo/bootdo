package com.bootdo.cpe.controller.gongfa;

import java.util.Date;
import java.util.HashMap;
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

import com.bootdo.cpe.domain.GfMainCompleteInfoDO;
import com.bootdo.cpe.service.GfMainCompleteInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 公奖法主要完成人
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-07-27 08:03:07
 */
 
@Controller
@RequestMapping("/cpe/gfMainCompleteInfo")
public class GfMainCompleteInfoController {
	@Autowired
	private GfMainCompleteInfoService gfMainCompleteInfoService;
	
	@GetMapping()
	@RequiresPermissions("cpe:gfMainCompleteInfo:gfMainCompleteInfo")
	String GfMainCompleteInfo(){
	    return "cpe/gfMainCompleteInfo/gfMainCompleteInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:gfMainCompleteInfo:gfMainCompleteInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GfMainCompleteInfoDO> gfMainCompleteInfoList = gfMainCompleteInfoService.list(query);
		int total = gfMainCompleteInfoService.count(query);
		PageUtils pageUtils = new PageUtils(gfMainCompleteInfoList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/get")
	@RequiresPermissions("cpe:gfMainCompleteInfo:gfMainCompleteInfo")
	public R get(int id){
		//查询列表数据
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
        Query query = new Query(params);
		List<GfMainCompleteInfoDO> gfMainCompleteInfoList = gfMainCompleteInfoService.list(query);
		R result = R.ok();
		result.put("data", gfMainCompleteInfoList.size() > 0 ? gfMainCompleteInfoList.get(0) : null);
		return result;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cpe:gfMainCompleteInfo:add")
	String add(){
	    return "cpe/gfMainCompleteInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:gfMainCompleteInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		GfMainCompleteInfoDO gfMainCompleteInfo = gfMainCompleteInfoService.get(id);
		model.addAttribute("gfMainCompleteInfo", gfMainCompleteInfo);
	    return "cpe/gfMainCompleteInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:gfMainCompleteInfo:add")
	public R save( GfMainCompleteInfoDO gfMainCompleteInfo){
		Integer id = gfMainCompleteInfo.getId();
		if(id != null && id > 0) {
			gfMainCompleteInfo.setUpdated(new Date());
			if(gfMainCompleteInfoService.update(gfMainCompleteInfo) > 0) {
				return R.ok();
			}
			return R.error();
		}
		gfMainCompleteInfo.setCreated(new Date());
		gfMainCompleteInfo.setUpdated(new Date());
		if(gfMainCompleteInfoService.save(gfMainCompleteInfo)>0){
			R r = R.ok();
			r.put("workHistory", gfMainCompleteInfo);
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:gfMainCompleteInfo:edit")
	public R update( GfMainCompleteInfoDO gfMainCompleteInfo){
		gfMainCompleteInfoService.update(gfMainCompleteInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:gfMainCompleteInfo:remove")
	public R remove( Integer id){
		if(gfMainCompleteInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:gfMainCompleteInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		gfMainCompleteInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
