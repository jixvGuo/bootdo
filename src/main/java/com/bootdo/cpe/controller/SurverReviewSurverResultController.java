package com.bootdo.cpe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseSurverController;
import com.bootdo.cpe.domain.EnumAwardType;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.domain.SurverReviewStandardResultDO;
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

import com.bootdo.cpe.domain.SurverReviewSurverResultDO;
import com.bootdo.cpe.service.SurverReviewSurverResultService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 勘察类审查表格
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */

@Controller
@RequestMapping("/cpe/surverReviewSurverResult")
public class SurverReviewSurverResultController extends BaseSurverController {
	private String prefix = "cpe/survey/check";
	@Autowired
	private SurverReviewSurverResultService surverReviewSurverResultService;
	@Autowired
	private PetroleumEngineeringService petroleumEngineeringService;
	@Autowired
	private ProjectCommonService projectCommonService;
	@Autowired
	private NotifyService notifyService;

	@GetMapping()
	@RequiresPermissions("cpe:surverApplyInfo:review")
	String SurverReviewSurverResult(@RequestParam Map<String, Object> params, ModelMap map){
	    packageAwardTaskId(map, params);
		List<SurverReviewSurverResultDO> list = surverReviewSurverResultService.list(params);
		map.put("reviewResult", list.size() > 0 ? list.get(0) : new SurverReviewSurverResultDO());
		return prefix + "/review_surver_template";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverApplyInfo:review")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverReviewSurverResultDO> surverReviewSurverResultList = surverReviewSurverResultService.list(query);
		int total = surverReviewSurverResultService.count(query);
		PageUtils pageUtils = new PageUtils(surverReviewSurverResultList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverReviewSurverResult:add")
	String add(){
	    return "cpe/surverReviewSurverResult/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverReviewSurverResult:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverReviewSurverResultDO surverReviewSurverResult = surverReviewSurverResultService.get(id);
		model.addAttribute("surverReviewSurverResult", surverReviewSurverResult);
	    return "cpe/surverReviewSurverResult/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverReview:add")
	public R save( SurverReviewSurverResultDO surverReviewSurverResult){
		Integer proId = surverReviewSurverResult.getProId();
		//更新项目状态值
		Map<String,Object> proStatParams = new HashMap<>();
		proStatParams.put("proId", proId);
		proStatParams.put("reviewResult", surverReviewSurverResult.getReviewResult());
		petroleumEngineeringService.updateProStat(proStatParams);

		//发送系统通知给用户
		long proCreateUid = projectCommonService.getProCreateUid(proId);
		Long[] uidArr = {proCreateUid};
		NotifyDO notifyDO = new NotifyDO();
		notifyDO.setType(EnumAwardType.SURVER.getAwrdType() + "");
		notifyDO.setUserIds(uidArr);
		notifyDO.setCreateBy(getUserId());
		String applyAwardName = "【勘察奖】" + surverReviewSurverResult.getProName();
		String title = (applyAwardName == null ? "" : applyAwardName) + "形式审查结果";
		String content = "形式审查结果:"+surverReviewSurverResult.getReviewResult()+";";
		if(StringUtils.isNotBlank(surverReviewSurverResult.getRemarks())) {
			content += surverReviewSurverResult.getRemarks();
		}
		notifyDO.setContent(content);
		notifyDO.setTitle(title);
		notifyService.save(notifyDO);

		long notifyId = notifyDO.getId();
		int reviewId = 0;

		Long uid = getUserId();
		surverReviewSurverResult.setOptUid(uid.intValue());
		Integer id = surverReviewSurverResult.getId();
		if(id != null && id > 0) {
		    reviewId = id;
			notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.SURVER_PRO.getProType());

			int rst = surverReviewSurverResultService.update(surverReviewSurverResult);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverReviewSurverResultService.save(surverReviewSurverResult)>0){
			reviewId = surverReviewSurverResult.getId();
			notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.SURVER_PRO.getProType());

			R r = R.ok();
			r.put("id", surverReviewSurverResult.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverReviewSurverResult:edit")
	public R update( SurverReviewSurverResultDO surverReviewSurverResult){
		surverReviewSurverResultService.update(surverReviewSurverResult);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverReviewSurverResult:remove")
	public R remove( Integer id){
		if(surverReviewSurverResultService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverReviewSurverResult:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverReviewSurverResultService.batchRemove(ids);
		return R.ok();
	}

}
