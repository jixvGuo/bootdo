package com.bootdo.cpe.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseQcProController;
//import com.sun.org.apache.bcel.internal.generic.DADD;
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

import com.bootdo.cpe.domain.QcReportInnovateInfoDO;
import com.bootdo.cpe.service.QcReportInnovateInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 石油工程建设优秀质量管理小组报告单（创新型课题）
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-01-31 16:01:32
 */

@Controller
@RequestMapping("/cpe/qcReportInnovateInfo")
public class QcReportInnovateInfoController extends BaseQcProController {
	@Autowired
	private QcReportInnovateInfoService qcReportInnovateInfoService;

	@GetMapping()
	@RequiresPermissions("cpe:qcReportInnovateInfo:qcReportInnovateInfo")
	String QcReportInnovateInfo(){
	    return "cpe/qcReportInnovateInfo/qcReportInnovateInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:qcReportInnovateInfo:qcReportInnovateInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QcReportInnovateInfoDO> qcReportInnovateInfoList = qcReportInnovateInfoService.list(query);
		int total = qcReportInnovateInfoService.count(query);
		PageUtils pageUtils = new PageUtils(qcReportInnovateInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:qcReportInnovateInfo:add")
	String add(){
	    return "cpe/qcReportInnovateInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:qcReportInnovateInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		QcReportInnovateInfoDO qcReportInnovateInfo = qcReportInnovateInfoService.get(id);
		model.addAttribute("qcReportInnovateInfo", qcReportInnovateInfo);
	    return "cpe/qcReportInnovateInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:qcReportInnovateInfo:add")
	public R save( QcReportInnovateInfoDO qcReportInnovateInfo){
		Long uid = getUserId();
		Date now = new Date();
		qcReportInnovateInfo.setDeleted(0);
		qcReportInnovateInfo.setOptUid(uid.intValue());
		qcReportInnovateInfo.setUpdated(now);
	    Integer id = qcReportInnovateInfo.getId();
	    int rst = 0;
	    if(id != null) {
	    	rst = qcReportInnovateInfoService.update(qcReportInnovateInfo);
		}else {
	    	qcReportInnovateInfo.setCreated(now);
			rst = qcReportInnovateInfoService.save(qcReportInnovateInfo);
			id = qcReportInnovateInfo.getId();
		}
	    R r = rst > 0 ? R.ok() : R.error();
	    r.put("id", id);
		return r;
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:qcReportInnovateInfo:edit")
	public R update( QcReportInnovateInfoDO qcReportInnovateInfo){
		qcReportInnovateInfoService.update(qcReportInnovateInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:qcReportInnovateInfo:remove")
	public R remove( Integer id){
		if(qcReportInnovateInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:qcReportInnovateInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		qcReportInnovateInfoService.batchRemove(ids);
		return R.ok();
	}

}
