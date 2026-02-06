package com.bootdo.cpe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseQcProController;
import com.bootdo.cpe.domain.EnumAwardType;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.petroleum_engineering_award.service.PetroleumEngineeringService;
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
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-20 21:57:29
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

	@GetMapping()
	@RequiresPermissions("cpe:qcReviewResultRecord:qcReviewResultRecord")
	String QcReviewResultRecord(){
	    return "cpe/qcReviewResultRecord/qcReviewResultRecord";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:qcReviewResultRecord:qcReviewResultRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QcReviewResultRecordDO> qcReviewResultRecordList = qcReviewResultRecordService.list(query);
		int total = qcReviewResultRecordService.count(query);
		PageUtils pageUtils = new PageUtils(qcReviewResultRecordList, total);
		return pageUtils;
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
	@RequiresPermissions("cpe:qcReviewResultRecord:add")
	public R save(QcReviewResultRecordDO qcReviewResultRecord, String topicName, String groupName){
	    Integer proId = qcReviewResultRecord.getProId();
		//更新项目状态值
		Map<String,Object> proStatParams = new HashMap<>();
		proStatParams.put("proId", proId);
		proStatParams.put("reviewResult", qcReviewResultRecord.getReviewResult());
		petroleumEngineeringService.updateProStat(proStatParams);

		Long uid = getUserId();
		qcReviewResultRecord.setOptUid(uid.intValue());
		if(qcReviewResultRecordService.save(qcReviewResultRecord)>0){
			//发送系统通知给用户
            long proCreateUid = projectCommonService.getProCreateUid(proId);
            Long[] uidArr = {proCreateUid};
			NotifyDO notifyDO = new NotifyDO();
			notifyDO.setType(EnumAwardType.QC.getAwrdType() + "");
			notifyDO.setUserIds(uidArr);
			notifyDO.setCreateBy(getUserId());
			//TODO 获取奖项名称
			String applyAwardName = "【QC奖】" + topicName + "-" + groupName;
			String title = (applyAwardName == null ? "" : applyAwardName) + "形式审查结果";
			String content = "形式审查结果:"+qcReviewResultRecord.getReviewResult()+";";
			if(StringUtils.isNotBlank(qcReviewResultRecord.getOpinionDesc())) {
				content += qcReviewResultRecord.getOpinionDesc();
			}
			notifyDO.setContent(content);
			notifyDO.setTitle(title);
			notifyService.save(notifyDO);

			long notifyId = notifyDO.getId();
			int reviewId = qcReviewResultRecord.getId();
			notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.QC_PRO_GROUP.getProType());

			int id = qcReviewResultRecord.getId();
			R r = R.ok();
			r.put("id", id);
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:qcReviewResultRecord:edit")
	public R update( QcReviewResultRecordDO qcReviewResultRecord){
		qcReviewResultRecordService.update(qcReviewResultRecord);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:qcReviewResultRecord:remove")
	public R remove( Integer id){
		if(qcReviewResultRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:qcReviewResultRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		qcReviewResultRecordService.batchRemove(ids);
		return R.ok();
	}

}
