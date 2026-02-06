package com.bootdo.cpe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseSurverController;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.cpe.domain.SurverReviewConsultResultDO;
import com.bootdo.cpe.service.SurverReviewConsultResultService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 咨询类形式审查模板
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */

@Controller
@RequestMapping("/cpe/surverReviewConsultResult")
public class SurverReviewConsultResultController extends BaseSurverController {
	private String prefix = "cpe/survey/check";
	@Autowired
	private SurverReviewConsultResultService surverReviewConsultResultService;
	@Autowired
	private PetroleumEngineeringService petroleumEngineeringService;
	@Autowired
	private ProjectCommonService projectCommonService;
	@Autowired
	private NotifyService notifyService;

	@GetMapping()
	@RequiresPermissions("cpe:surverApplyInfo:review")
	String SurverReviewConsultResult(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		List<SurverReviewConsultResultDO> surveyConsultList = surverReviewConsultResultService.list(params);
		map.put("reviewResult", surveyConsultList.size() > 0 ? surveyConsultList.get(0) : new SurverReviewConsultResultDO());
	    return prefix + "/review_consulting_template";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverApplyInfo:review")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverReviewConsultResultDO> surverReviewConsultResultList = surverReviewConsultResultService.list(query);
		int total = surverReviewConsultResultService.count(query);
		PageUtils pageUtils = new PageUtils(surverReviewConsultResultList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverReviewConsultResult:add")
	String add(){
	    return "cpe/surverReviewConsultResult/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverReviewConsultResult:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverReviewConsultResultDO surverReviewConsultResult = surverReviewConsultResultService.get(id);
		model.addAttribute("surverReviewConsultResult", surverReviewConsultResult);
	    return "cpe/surverReviewConsultResult/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverReview:add")
	public R save( SurverReviewConsultResultDO surverReviewConsultResult){
		Integer proId = surverReviewConsultResult.getProId();
		//更新项目状态值
		Map<String,Object> proStatParams = new HashMap<>();
		proStatParams.put("proId", proId);
		proStatParams.put("reviewResult", surverReviewConsultResult.getReviewResult());
		petroleumEngineeringService.updateProStat(proStatParams);

		//发送系统通知给用户
		long proCreateUid = projectCommonService.getProCreateUid(proId);
		Long[] uidArr = {proCreateUid};
		NotifyDO notifyDO = new NotifyDO();
		notifyDO.setType(EnumAwardType.SURVER.getAwrdType() + "");
		notifyDO.setUserIds(uidArr);
		notifyDO.setCreateBy(getUserId());
		String applyAwardName = "【勘察奖】" + surverReviewConsultResult.getProName();
		String title = (applyAwardName == null ? "" : applyAwardName) + "形式审查结果";
		String content = "形式审查结果:"+surverReviewConsultResult.getReviewResult()+";";
		if(StringUtils.isNotBlank(surverReviewConsultResult.getRemarks())) {
			content += surverReviewConsultResult.getRemarks();
		}
		notifyDO.setContent(content);
		notifyDO.setTitle(title);
		notifyService.save(notifyDO);

		long notifyId = notifyDO.getId();
		int reviewId = 0;

		Long uid = getUserId();
		surverReviewConsultResult.setOptUid(uid.intValue());
		Integer id = surverReviewConsultResult.getId();
		if(id != null && id > 0) {
		    reviewId = id;
			notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.SURVER_PRO.getProType());
			int rst = surverReviewConsultResultService.update(surverReviewConsultResult);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverReviewConsultResultService.save(surverReviewConsultResult)>0){
			reviewId = surverReviewConsultResult.getId();
			notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.SURVER_PRO.getProType());

			R r = R.ok();
			r.put("id", surverReviewConsultResult.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverReviewConsultResult:edit")
	public R update( SurverReviewConsultResultDO surverReviewConsultResult){
		surverReviewConsultResultService.update(surverReviewConsultResult);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverReviewConsultResult:remove")
	public R remove( Integer id){
		if(surverReviewConsultResultService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverReviewConsultResult:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverReviewConsultResultService.batchRemove(ids);
		return R.ok();
	}

}
