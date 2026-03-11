package com.bootdo.cpe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseQcProController;
import com.bootdo.cpe.domain.EnumAwardType;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.petroleum_engineering_award.service.PetroleumEngineeringService;
import com.bootdo.cpe.service.QcAwardService;
import com.bootdo.cpe.service.ProjectCommonService;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.service.NotifyService;
import org.apache.commons.lang3.StringUtils;
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

import com.bootdo.cpe.domain.QcReviewResultRecordDO;
import com.bootdo.cpe.service.QcReviewResultRecordService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * QC形式审查模板
 */
@Controller
@RequestMapping("/cpe/qcReviewResultRecord")
public class QcReviewResultRecordController extends BaseQcProController {
	@Autowired
	private QcReviewResultRecordService qcReviewResultRecordService;
	@Autowired
	private PetroleumEngineeringService petroleumEngineeringService;
	@Autowired
	private ProjectCommonService projectCommonService;
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private QcAwardService qcAwardService;

	@GetMapping()
	@RequiresPermissions("cpe:qcReviewResultRecord:qcReviewResultRecord")
	String QcReviewResultRecord(){
	    return "cpe/qcReviewResultRecord/qcReviewResultRecord";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:qcReviewResultRecord:qcReviewResultRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
		List<QcReviewResultRecordDO> qcReviewResultRecordList = qcReviewResultRecordService.list(query);
		int total = qcReviewResultRecordService.count(query);
		return new PageUtils(qcReviewResultRecordList, total);
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:qcReviewResultRecord:add")
	String add(){
	    return "cpe/qcReviewResultRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:qcReviewResultRecord:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		QcReviewResultRecordDO qcReviewResultRecord = qcReviewResultRecordService.get(id);
		model.addAttribute("qcReviewResultRecord", qcReviewResultRecord);
	    return "cpe/qcReviewResultRecord/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("cpe:qcReviewResultRecord:add")
	public R save(QcReviewResultRecordDO qcReviewResultRecord, String topicName, String groupName){
		Integer proId = qcReviewResultRecord.getProId();
		if (proId == null) {
			return R.error("缺少项目ID");
		}
		if (StringUtils.isBlank(qcReviewResultRecord.getReviewResult())) {
			return R.error("请选择审查结论");
		}

		Map<String,Object> proStatParams = new HashMap<>();
		proStatParams.put("proId", proId);
		proStatParams.put("reviewResult", qcReviewResultRecord.getReviewResult());
		// petroleumEngineeringService.updateProStat(proStatParams);
		qcAwardService.updateProStat(proStatParams);

		Long uid = getUserId();
		qcReviewResultRecord.setOptUid(uid.intValue());

		// int row;
		// if (qcReviewResultRecord.getId() != null) {
		// 	row = qcReviewResultRecordService.update(qcReviewResultRecord);
		// 	if (row <= 0) {
		// 		qcReviewResultRecord.setId(null);
		// 		row = qcReviewResultRecordService.save(qcReviewResultRecord);
		// 	}
		// } else {
		// 	row = qcReviewResultRecordService.save(qcReviewResultRecord);
		// }
		qcReviewResultRecord.setId(null); // 强制每次新增，保留审批历史
		int row = qcReviewResultRecordService.save(qcReviewResultRecord);

		if(row <= 0){
			return R.error("保存失败");
		}

		try {
			long proCreateUid = projectCommonService.getProCreateUid(proId);
			Long[] uidArr = {proCreateUid};
			NotifyDO notifyDO = new NotifyDO();
			notifyDO.setType(EnumAwardType.QC.getAwrdType() + "");
			notifyDO.setUserIds(uidArr);
			notifyDO.setCreateBy(getUserId());

			String applyAwardName;
			if (StringUtils.isNotBlank(topicName) || StringUtils.isNotBlank(groupName)) {
				applyAwardName = "【QC奖】" + (topicName == null ? "" : topicName) + "-" + (groupName == null ? "" : groupName);
			} else {
				applyAwardName = "【QC奖】";
			}
			String title = applyAwardName + "形式审查结果";
			String content = "形式审查结果:" + qcReviewResultRecord.getReviewResult() + ";";
			if(StringUtils.isNotBlank(qcReviewResultRecord.getOpinionDesc())) {
				content += qcReviewResultRecord.getOpinionDesc();
			}
			notifyDO.setContent(content);
			notifyDO.setTitle(title);
			notifyService.save(notifyDO);

			long notifyId = notifyDO.getId();
			int reviewId = qcReviewResultRecord.getId();
			notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.QC_PRO_GROUP.getProType());
		} catch (Exception e) {
			// 通知失败不影响主记录保存
		}

		R r = R.ok();
		r.put("id", qcReviewResultRecord.getId());
		return r;
	}

	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:qcReviewResultRecord:edit")
	public R update(QcReviewResultRecordDO qcReviewResultRecord){
		qcReviewResultRecordService.update(qcReviewResultRecord);
		return R.ok();
	}

	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("cpe:qcReviewResultRecord:remove")
	public R remove(Integer id){
		if(qcReviewResultRecordService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:qcReviewResultRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		qcReviewResultRecordService.batchRemove(ids);
		return R.ok();
	}
}
