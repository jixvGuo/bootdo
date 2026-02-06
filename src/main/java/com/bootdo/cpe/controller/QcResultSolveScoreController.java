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

import com.bootdo.cpe.domain.QcResultSolveScoreDO;
import com.bootdo.cpe.service.QcResultSolveScoreService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 问题解决型课题成果评价表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-01-31 16:01:32
 */

@Controller
@RequestMapping("/cpe/qcResultSolveScore")
public class QcResultSolveScoreController extends BaseQcProController {
	@Autowired
	private QcResultSolveScoreService qcResultSolveScoreService;

	@GetMapping()
	@RequiresPermissions("cpe:qcResultSolveScore:qcResultSolveScore")
	String QcResultSolveScore(){
	    return "cpe/qcResultSolveScore/qcResultSolveScore";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:qcResultSolveScore:qcResultSolveScore")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QcResultSolveScoreDO> qcResultSolveScoreList = qcResultSolveScoreService.list(query);
		int total = qcResultSolveScoreService.count(query);
		PageUtils pageUtils = new PageUtils(qcResultSolveScoreList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:qcResultSolveScore:add")
	String add(){
	    return "cpe/qcResultSolveScore/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:qcResultSolveScore:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		QcResultSolveScoreDO qcResultSolveScore = qcResultSolveScoreService.get(id);
		model.addAttribute("qcResultSolveScore", qcResultSolveScore);
	    return "cpe/qcResultSolveScore/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:qcResultSolveScore:add")
	public R save( QcResultSolveScoreDO qcResultSolveScore){
		Long uid = getUserId();
		Date now = new Date();
		qcResultSolveScore.setDeleted(0);
		qcResultSolveScore.setOptUid(uid.intValue());
		qcResultSolveScore.setUpdated(now);
		int rst = 0;
		Integer id = qcResultSolveScore.getId();
		if(id != null) {
			rst = qcResultSolveScoreService.update(qcResultSolveScore);
		}else {
		    qcResultSolveScore.setCreated(now);
			rst = qcResultSolveScoreService.save(qcResultSolveScore);
			id = qcResultSolveScore.getId();
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
	@RequiresPermissions("cpe:qcResultSolveScore:edit")
	public R update( QcResultSolveScoreDO qcResultSolveScore){
		qcResultSolveScoreService.update(qcResultSolveScore);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:qcResultSolveScore:remove")
	public R remove( Integer id){
		if(qcResultSolveScoreService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:qcResultSolveScore:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		qcResultSolveScoreService.batchRemove(ids);
		return R.ok();
	}

}
