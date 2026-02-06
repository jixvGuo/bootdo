package com.bootdo.cpe.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseQcProController;
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

import com.bootdo.cpe.domain.QcSurveyStatisticInfoDO;
import com.bootdo.cpe.service.QcSurveyStatisticInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 质量管理小组概况统计表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-01-31 16:01:32
 */

@Controller
@RequestMapping("/cpe/qcSurveyStatisticInfo")
public class QcSurveyStatisticInfoController extends BaseQcProController {
	@Autowired
	private QcSurveyStatisticInfoService qcSurveyStatisticInfoService;

	@GetMapping()
	@RequiresPermissions("cpe:qcSurveyStatisticInfo:qcSurveyStatisticInfo")
	String QcSurveyStatisticInfo(){
	    return "cpe/qcSurveyStatisticInfo/qcSurveyStatisticInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:qcSurveyStatisticInfo:qcSurveyStatisticInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QcSurveyStatisticInfoDO> qcSurveyStatisticInfoList = qcSurveyStatisticInfoService.list(query);
		int total = qcSurveyStatisticInfoService.count(query);
		PageUtils pageUtils = new PageUtils(qcSurveyStatisticInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:qcSurveyStatisticInfo:add")
	String add(){
	    return "cpe/qcSurveyStatisticInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:qcSurveyStatisticInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		QcSurveyStatisticInfoDO qcSurveyStatisticInfo = qcSurveyStatisticInfoService.get(id);
		model.addAttribute("qcSurveyStatisticInfo", qcSurveyStatisticInfo);
	    return "cpe/qcSurveyStatisticInfo/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:qcSurveyStatisticInfo:add")
	public R save( QcSurveyStatisticInfoDO qcSurveyStatisticInfo){
	    Long uid = getUserId();
	    Date now = new Date();
	    qcSurveyStatisticInfo.setDeleted(0);
	    qcSurveyStatisticInfo.setUpdated(now);
	    qcSurveyStatisticInfo.setOptUid(uid.intValue());
	    int rst = 0;
	    Integer id = qcSurveyStatisticInfo.getId();
	    if(id != null) {
	    	rst = qcSurveyStatisticInfoService.update(qcSurveyStatisticInfo);
		}else {
	        qcSurveyStatisticInfo.setCreated(now);
	    	rst = qcSurveyStatisticInfoService.save(qcSurveyStatisticInfo);
	    	id = qcSurveyStatisticInfo.getId();
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
	@RequiresPermissions("cpe:qcSurveyStatisticInfo:edit")
	public R update( QcSurveyStatisticInfoDO qcSurveyStatisticInfo){
		qcSurveyStatisticInfoService.update(qcSurveyStatisticInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:qcSurveyStatisticInfo:remove")
	public R remove( Integer id){
		if(qcSurveyStatisticInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:qcSurveyStatisticInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		qcSurveyStatisticInfoService.batchRemove(ids);
		return R.ok();
	}

}
