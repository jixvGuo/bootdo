package com.bootdo.cpe.controller;

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

import com.bootdo.cpe.domain.QcGroupApplyInfoDO;
import com.bootdo.cpe.service.QcGroupApplyInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 石油工程建设优秀质量管理小组申报表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-16 01:08:57
 */
 
@Controller
@RequestMapping("/cpe/qcGroupApplyInfo")
public class QcGroupApplyInfoController {
	@Autowired
	private QcGroupApplyInfoService qcGroupApplyInfoService;
	
	@GetMapping()
	@RequiresPermissions("cpe:qcGroupApplyInfo:qcGroupApplyInfo")
	String QcGroupApplyInfo(){
	    return "cpe/qcGroupApplyInfo/qcGroupApplyInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:qcGroupApplyInfo:qcGroupApplyInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QcGroupApplyInfoDO> qcGroupApplyInfoList = qcGroupApplyInfoService.list(query);
		int total = qcGroupApplyInfoService.count(query);
		PageUtils pageUtils = new PageUtils(qcGroupApplyInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cpe:qcGroupApplyInfo:add")
	String add(){
	    return "cpe/qcGroupApplyInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:qcGroupApplyInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		QcGroupApplyInfoDO qcGroupApplyInfo = qcGroupApplyInfoService.get(id);
		model.addAttribute("qcGroupApplyInfo", qcGroupApplyInfo);
	    return "cpe/qcGroupApplyInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:qcGroupApplyInfo:add")
	public R save( QcGroupApplyInfoDO qcGroupApplyInfo){
		if(qcGroupApplyInfoService.save(qcGroupApplyInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:qcGroupApplyInfo:edit")
	public R update( QcGroupApplyInfoDO qcGroupApplyInfo){
		qcGroupApplyInfoService.update(qcGroupApplyInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:qcGroupApplyInfo:remove")
	public R remove( Integer id){
		if(qcGroupApplyInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:qcGroupApplyInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		qcGroupApplyInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
