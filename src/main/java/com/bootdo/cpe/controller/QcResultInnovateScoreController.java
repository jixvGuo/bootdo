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

import com.bootdo.cpe.domain.QcResultInnovateScoreDO;
import com.bootdo.cpe.service.QcResultInnovateScoreService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 创新型课题成果评价表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-06 22:48:27
 */

@Controller
@RequestMapping("/cpe/qcResultInnovateScore")
public class QcResultInnovateScoreController extends BaseQcProController {
	@Autowired
	private QcResultInnovateScoreService qcResultInnovateScoreService;

	@GetMapping()
	@RequiresPermissions("cpe:qcResultInnovateScore:qcResultInnovateScore")
	String QcResultInnovateScore(){
	    return "cpe/qcResultInnovateScore/qcResultInnovateScore";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:qcResultInnovateScore:qcResultInnovateScore")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QcResultInnovateScoreDO> qcResultInnovateScoreList = qcResultInnovateScoreService.list(query);
		int total = qcResultInnovateScoreService.count(query);
		PageUtils pageUtils = new PageUtils(qcResultInnovateScoreList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:qcResultInnovateScore:add")
	String add(){
	    return "cpe/qcResultInnovateScore/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:qcResultInnovateScore:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		QcResultInnovateScoreDO qcResultInnovateScore = qcResultInnovateScoreService.get(id);
		model.addAttribute("qcResultInnovateScore", qcResultInnovateScore);
	    return "cpe/qcResultInnovateScore/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:qcResultInnovateScore:add")
	public R save( QcResultInnovateScoreDO qcResultInnovateScore){
		Long uid = getUserId();
		Date now = new Date();
		qcResultInnovateScore.setDeleted(0);
		qcResultInnovateScore.setOptUid(uid.intValue());
		qcResultInnovateScore.setUpdated(now);
		int rst = 0;
		Integer id = qcResultInnovateScore.getId();
		if(id != null) {
			rst = qcResultInnovateScoreService.update(qcResultInnovateScore);
		}else {
		    qcResultInnovateScore.setCreated(now);
			rst = qcResultInnovateScoreService.save(qcResultInnovateScore);
			id = qcResultInnovateScore.getId();
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
	@RequiresPermissions("cpe:qcResultInnovateScore:edit")
	public R update( QcResultInnovateScoreDO qcResultInnovateScore){
		qcResultInnovateScoreService.update(qcResultInnovateScore);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:qcResultInnovateScore:remove")
	public R remove( Integer id){
		if(qcResultInnovateScoreService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:qcResultInnovateScore:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		qcResultInnovateScoreService.batchRemove(ids);
		return R.ok();
	}

}
