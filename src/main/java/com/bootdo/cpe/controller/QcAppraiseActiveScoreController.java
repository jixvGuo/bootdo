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

import com.bootdo.cpe.domain.QcAppraiseActiveScoreDO;
import com.bootdo.cpe.service.QcAppraiseActiveScoreService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 质量管理小组活动现场评价表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-16 01:15:51
 */

@Controller
@RequestMapping("/cpe/qcAppraiseActiveScore")
public class QcAppraiseActiveScoreController extends BaseQcProController {
	@Autowired
	private QcAppraiseActiveScoreService qcAppraiseActiveScoreService;

	@GetMapping()
	@RequiresPermissions("cpe:qcAppraiseActiveScore:qcAppraiseActiveScore")
	String QcAppraiseActiveScore(){
	    return "cpe/qcAppraiseActiveScore/qcAppraiseActiveScore";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:qcAppraiseActiveScore:qcAppraiseActiveScore")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QcAppraiseActiveScoreDO> qcAppraiseActiveScoreList = qcAppraiseActiveScoreService.list(query);
		int total = qcAppraiseActiveScoreService.count(query);
		PageUtils pageUtils = new PageUtils(qcAppraiseActiveScoreList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:qcAppraiseActiveScore:add")
	String add(){
	    return "cpe/qcAppraiseActiveScore/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:qcAppraiseActiveScore:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		QcAppraiseActiveScoreDO qcAppraiseActiveScore = qcAppraiseActiveScoreService.get(id);
		model.addAttribute("qcAppraiseActiveScore", qcAppraiseActiveScore);
	    return "cpe/qcAppraiseActiveScore/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:qcAppraiseActiveScore:add")
	public R save( QcAppraiseActiveScoreDO qcAppraiseActiveScore){
		Long uid = getUserId();
		Date now = new Date();
		qcAppraiseActiveScore.setDeleted(0);
		qcAppraiseActiveScore.setOptUid(uid.intValue());
		qcAppraiseActiveScore.setUpdated(now);
		int rst = 0;
		Integer id = qcAppraiseActiveScore.getId();
		if(id != null) {
			rst = qcAppraiseActiveScoreService.update(qcAppraiseActiveScore);
		}else {
			qcAppraiseActiveScore.setCreated(now);
			rst = qcAppraiseActiveScoreService.save(qcAppraiseActiveScore);
			id = qcAppraiseActiveScore.getId();
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
	@RequiresPermissions("cpe:qcAppraiseActiveScore:edit")
	public R update( QcAppraiseActiveScoreDO qcAppraiseActiveScore){
		qcAppraiseActiveScoreService.update(qcAppraiseActiveScore);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:qcAppraiseActiveScore:remove")
	public R remove( Integer id){
		if(qcAppraiseActiveScoreService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:qcAppraiseActiveScore:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		qcAppraiseActiveScoreService.batchRemove(ids);
		return R.ok();
	}

}
