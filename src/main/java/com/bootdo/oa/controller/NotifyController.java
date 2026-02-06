package com.bootdo.oa.controller;

import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.controller.petroleum_engineering.EnterpriseQualityAwardController;
import com.bootdo.cpe.controller.petroleum_engineering.EnterpriseQualityGoldAwardController;
import com.bootdo.cpe.controller.petroleum_engineering.EnterpriseUnitApplyController;
import com.bootdo.cpe.controller.qc.QcProcessController;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.domain.QcGroupApplyInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.controller.OilProApplyInfoController;
import com.bootdo.cpe.petroleum_engineering_award.controller.OilProInstallReviewRecordController;
import com.bootdo.cpe.petroleum_engineering_award.controller.OilProQualityGoldReviewRecordController;
import com.bootdo.cpe.petroleum_engineering_award.controller.OilProQualityReviewRecordController;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProInstallReviewRecordDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProInstallReviewRecordService;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProQualityGoldReviewRecordService;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProQualityReviewRecordService;
import com.bootdo.cpe.service.QcGroupApplyInfoService;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.domain.NotifyRecordDO;
import com.bootdo.oa.service.NotifyRecordService;
import com.bootdo.oa.service.NotifyService;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通知通告
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */

@Controller
@RequestMapping("/oa/notify")
public class NotifyController extends BaseController {
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private NotifyRecordService notifyRecordService;
	@Autowired
	private DictService dictService;
	@Autowired
	private EnterpriseUnitApplyController enterpriseUnitApplyController;
	@Autowired
	private EnterpriseQualityAwardController enterpriseQualityAwardController;
	@Autowired
	private EnterpriseQualityGoldAwardController enterpriseQualityGoldAwardController;
	@Autowired
	private QcProcessController qcProcessController;
	@Autowired
	private QcGroupApplyInfoService qcGroupApplyInfoService;

	@GetMapping()
	@RequiresPermissions("oa:notify:notify")
	String oaNotify() {
		return "oa/notify/notify";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:notify:notify")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<NotifyDO> notifyList = notifyService.list(query);
		int total = notifyService.count(query);
		PageUtils pageUtils = new PageUtils(notifyList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("oa:notify:add")
	String add() {
		return "oa/notify/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:notify:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		NotifyDO notify = notifyService.get(id);
		List<DictDO> dictDOS = dictService.listByType("oa_notify_type");
		String type = notify.getType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("oaNotifyTypes",dictDOS);
		model.addAttribute("notify", notify);
		return "oa/notify/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:notify:add")
	public R save(NotifyDO notify) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		notify.setCreateBy(getUserId());
		if (notifyService.save(notify) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:notify:edit")
	public R update(NotifyDO notify) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		notifyService.update(notify);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("oa:notify:remove")
	public R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (notifyService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:notify:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		notifyService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/message")
	PageUtils message() {
		Map<String, Object> params = new HashMap<>(16);
		params.put("offset", 0);
		params.put("limit", 3);
		Query query = new Query(params);
        query.put("userId", getUserId());
        query.put("isRead",Constant.OA_NOTIFY_READ_NO);
		return notifyService.selfList(query);
	}

	@GetMapping("/selfNotify")
	String selefNotify(Long id, ModelMap model) {
		model.addAttribute("notifyId", id);
		if(id != null && id > 0) {
			read(id, model);
		}
		return "oa/notify/selfNotify";
	}

	@ResponseBody
	@GetMapping("/selfList")
	PageUtils selfList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		query.put("userId", getUserId());

		return notifyService.selfList(query);
	}

	@GetMapping("/read/{id}")
	@RequiresPermissions("oa:notify:edit")
	public String read(@PathVariable("id") Long id, ModelMap model) {
		NotifyDO notify = notifyService.get(id);
		//更改阅读状态
		NotifyRecordDO notifyRecordDO = new NotifyRecordDO();
		notifyRecordDO.setNotifyId(id);
		notifyRecordDO.setUserId(getUserId());
		notifyRecordDO.setReadDate(new Date());
		notifyRecordDO.setIsRead(Constant.OA_NOTIFY_READ_YES);
		notifyRecordService.changeRead(notifyRecordDO);
		model.put("notify", notify);
		return "oa/notify/read";
	}

	/**
	 * 读取项目形式审查结果的消息
	 * @param id
	 * @param proId
	 * @param reviewId
	 * @param proType
	 * @param map
	 * @return
	 */
	@RequestMapping("/readProReview/{id}")
	public String readProReviewMsg(@PathVariable("id") Long id, Integer proId, Integer reviewId, String proType,ModelMap map) {
		//消息设为已读
		read(id, map);
		map.put("readonly", 1);
        //根据项目id 判断展示哪个形式审查的页面
		Map<String,Object> params = new HashMap<>();
		params.put("proId", proId);
		params.put("reviewId", reviewId);
		params.put("readonly", 1);
		if(EnumProjectType.OIL_PRO_INSTALL.getProType().equals(proType)) {
			return enterpriseUnitApplyController.toPetroleumInstallationReview(params, map);
		}else if(EnumProjectType.OIL_PRO_QUALITY.getProType().equals(proType)) {
			return enterpriseQualityAwardController.toPetroleumInstallationReview(params, map);
		}else if(EnumProjectType.OIL_PRO_QUALITY_GOLD.getProType().equals(proType)) {
			return enterpriseQualityGoldAwardController.toPetroleumInstallationReview(params, map);
		}else if(EnumProjectType.QC_PRO_GROUP.getProType().equals(proType)) {
			Map<String, Object> groupInfoParams = new HashMap<>();
			groupInfoParams.put("proId", params.get("proId"));
			List<QcGroupApplyInfoDO> groupApplyInfoDOList = qcGroupApplyInfoService.list(groupInfoParams);
			if(groupApplyInfoDOList.size() > 0) {
				params.put("groupInfoId", groupApplyInfoDOList.get(0).getId());
				return qcProcessController.toReviewPro(params, map);
			}
		}
		return "oa/notify/read";
	}


}
