package com.bootdo.cpe.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseQcProController;
import com.bootdo.cpe.dto.QcBaseProjectInfoDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.cpe.domain.QcReportSolveInfoDO;
import com.bootdo.cpe.service.QcReportSolveInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 石油工程建设优秀质量管理小组报告单（问题解决型课题）
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-06 21:58:24
 */

@Controller
@RequestMapping("/cpe/qcReportSolveInfo")
public class QcReportSolveInfoController extends BaseQcProController {
	@Autowired
	private QcReportSolveInfoService qcReportSolveInfoService;

	@GetMapping()
	@RequiresPermissions("cpe:qcReportSolveInfo:qcReportSolveInfo")
	String QcReportSolveInfo(){
	    return "cpe/qcReportSolveInfo/qcReportSolveInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:qcReportSolveInfo:qcReportSolveInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QcReportSolveInfoDO> qcReportSolveInfoList = qcReportSolveInfoService.list(query);
		int total = qcReportSolveInfoService.count(query);
		PageUtils pageUtils = new PageUtils(qcReportSolveInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:qcReportSolveInfo:add")
	String add(){
	    return "cpe/qcReportSolveInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:qcReportSolveInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		QcReportSolveInfoDO qcReportSolveInfo = qcReportSolveInfoService.get(id);
		model.addAttribute("qcReportSolveInfo", qcReportSolveInfo);
	    return "cpe/qcReportSolveInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:qcReportSolveInfo:add")
	public R save( QcReportSolveInfoDO qcReportSolveInfo){
	    Long uid = getUserId();
	    qcReportSolveInfo.setOptUid(uid.intValue());
		Integer id = qcReportSolveInfo.getId();
		qcReportSolveInfo.setDeleted(0);
		Date now = new Date();
		qcReportSolveInfo.setUpdated(now);
		if(id != null) {
			int rst = qcReportSolveInfoService.update(qcReportSolveInfo);
			R r = rst > 0 ? R.ok() : R.error();
			r.put("id", qcReportSolveInfo.getId());
			return r;
		}
		qcReportSolveInfo.setCreated(now);
		if(qcReportSolveInfoService.save(qcReportSolveInfo)>0){
			R r = R.ok();
			r.put("id", qcReportSolveInfo.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:qcReportSolveInfo:edit")
	public R update( QcReportSolveInfoDO qcReportSolveInfo){
		qcReportSolveInfoService.update(qcReportSolveInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:qcReportSolveInfo:remove")
	public R remove( Integer id){
		if(qcReportSolveInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:qcReportSolveInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		qcReportSolveInfoService.batchRemove(ids);
		return R.ok();
	}

}
