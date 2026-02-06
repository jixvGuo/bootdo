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

import com.bootdo.cpe.domain.SurverReviewDesignResultDO;
import com.bootdo.cpe.service.SurverReviewDesignResultService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 设计类审查表格
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */

@Controller
@RequestMapping("/cpe/surverReviewDesignResult")
public class SurverReviewDesignResultController extends BaseSurverController {
	private String prefix = "cpe/survey/check";

	@Autowired
	private SurverReviewDesignResultService surverReviewDesignResultService;
	@Autowired
	private PetroleumEngineeringService petroleumEngineeringService;
	@Autowired
	private ProjectCommonService projectCommonService;
	@Autowired
	private NotifyService notifyService;

	@GetMapping()
	@RequiresPermissions("cpe:surverApplyInfo:review")
	String SurverReviewDesignResult(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
		List<SurverReviewDesignResultDO> list = surverReviewDesignResultService.list(params);
		map.put("reviewResult", list.size() > 0 ? list.get(0) : new SurverReviewDesignResultDO());
	    return prefix + "/review_design_template";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cpe:surverApplyInfo:review")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SurverReviewDesignResultDO> surverReviewDesignResultList = surverReviewDesignResultService.list(query);
		int total = surverReviewDesignResultService.count(query);
		PageUtils pageUtils = new PageUtils(surverReviewDesignResultList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("cpe:surverReviewDesignResult:add")
	String add(){
	    return "cpe/surverReviewDesignResult/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("cpe:surverReviewDesignResult:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SurverReviewDesignResultDO surverReviewDesignResult = surverReviewDesignResultService.get(id);
		model.addAttribute("surverReviewDesignResult", surverReviewDesignResult);
	    return "cpe/surverReviewDesignResult/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cpe:surverReview:add")
	public R save( SurverReviewDesignResultDO surverReviewDesignResult){
		Integer proId = surverReviewDesignResult.getProId();
		//更新项目状态值
		Map<String,Object> proStatParams = new HashMap<>();
		proStatParams.put("proId", proId);
		proStatParams.put("reviewResult", surverReviewDesignResult.getReviewResult());
		petroleumEngineeringService.updateProStat(proStatParams);

		//发送系统通知给用户
		long proCreateUid = projectCommonService.getProCreateUid(proId);
		Long[] uidArr = {proCreateUid};
		NotifyDO notifyDO = new NotifyDO();
		notifyDO.setType(EnumAwardType.SURVER.getAwrdType() + "");
		notifyDO.setUserIds(uidArr);
		notifyDO.setCreateBy(getUserId());
		String applyAwardName = "【勘察奖】" + surverReviewDesignResult.getProName();
		String title = (applyAwardName == null ? "" : applyAwardName) + "形式审查结果";
		String content = "形式审查结果:"+surverReviewDesignResult.getReviewResult()+";";
		if(StringUtils.isNotBlank(surverReviewDesignResult.getRemarks())) {
			content += surverReviewDesignResult.getRemarks();
		}
		notifyDO.setContent(content);
		notifyDO.setTitle(title);
		notifyService.save(notifyDO);

		long notifyId = notifyDO.getId();
		int reviewId = 0;


		Long uid = getUserId();
		surverReviewDesignResult.setOptUid(uid.intValue());
		Integer id = surverReviewDesignResult.getId();
		if(id != null && id > 0) {
		    reviewId = id;
			notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.SURVER_PRO.getProType());
			int rst = surverReviewDesignResultService.update(surverReviewDesignResult);
			return rst > 0 ? R.ok() : R.error();
		}
		if(surverReviewDesignResultService.save(surverReviewDesignResult)>0){
			reviewId = surverReviewDesignResult.getId();
			notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.SURVER_PRO.getProType());
			R r = R.ok();
			r.put("id", surverReviewDesignResult.getId());
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cpe:surverReviewDesignResult:edit")
	public R update( SurverReviewDesignResultDO surverReviewDesignResult){
		surverReviewDesignResultService.update(surverReviewDesignResult);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cpe:surverReviewDesignResult:remove")
	public R remove( Integer id){
		if(surverReviewDesignResultService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cpe:surverReviewDesignResult:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		surverReviewDesignResultService.batchRemove(ids);
		return R.ok();
	}

}
