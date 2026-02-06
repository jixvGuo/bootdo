package com.bootdo.cpe.petroleum_engineering_award.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.petroleum_engineering_award.service.PetroleumEngineeringService;
import com.bootdo.cpe.service.ProjectCommonService;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.service.NotifyService;
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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProQualityGoldReviewRecordDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProQualityGoldReviewRecordService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 石油优质工程形式审查结果记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-20 09:01:55
 */
 
@Controller
@RequestMapping("/petroleum_engineering_award/oilProQualityGoldReviewRecord")
public class OilProQualityGoldReviewRecordController extends BaseController {
	@Autowired
	private OilProQualityGoldReviewRecordService oilProQualityGoldReviewRecordService;
	@Autowired
	private PetroleumEngineeringService petroleumEngineeringService;
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilProQualityGoldReviewRecord:oilProQualityGoldReviewRecord")
	String OilProQualityGoldReviewRecord(){
	    return "petroleum_engineering_award/oilProQualityGoldReviewRecord/oilProQualityGoldReviewRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilProQualityGoldReviewRecord:oilProQualityGoldReviewRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilProQualityGoldReviewRecordDO> oilProQualityGoldReviewRecordList = oilProQualityGoldReviewRecordService.list(query);
		int total = oilProQualityGoldReviewRecordService.count(query);
		PageUtils pageUtils = new PageUtils(oilProQualityGoldReviewRecordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilProQualityGoldReviewRecord:add")
	String add(){
	    return "petroleum_engineering_award/oilProQualityGoldReviewRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilProQualityGoldReviewRecord:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilProQualityGoldReviewRecordDO oilProQualityGoldReviewRecord = oilProQualityGoldReviewRecordService.get(id);
		model.addAttribute("oilProQualityGoldReviewRecord", oilProQualityGoldReviewRecord);
	    return "petroleum_engineering_award/oilProQualityGoldReviewRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("petroleumEngineering:qualityGoldPro:review")
	public R save( OilProQualityGoldReviewRecordDO oilProQualityGoldReviewRecord){
		//更新项目状态值
		Map<String,Object> proStatParams = new HashMap<>();
		proStatParams.put("proId", oilProQualityGoldReviewRecord.getProId());
		proStatParams.put("reviewResult", oilProQualityGoldReviewRecord.getReviewResult());
		petroleumEngineeringService.updateProStat(proStatParams);

		//添加
		oilProQualityGoldReviewRecord.setOptUid(getUserId());
		if(oilProQualityGoldReviewRecordService.save(oilProQualityGoldReviewRecord)>0){

			//发送系统通知给用户
			long uid = projectCommonService.getProCreateUid(oilProQualityGoldReviewRecord.getProId());
			Long[] uidArr = {uid};
			NotifyDO notifyDO = new NotifyDO();
			notifyDO.setUserIds(uidArr);
			notifyDO.setCreateBy(getUserId());
			String applyAwardName = oilProQualityGoldReviewRecord.getApplyAwardName();
			String title = (applyAwardName == null ? "" : applyAwardName) + "形式审查结果";
			String content = "形式审查结果:"+oilProQualityGoldReviewRecord.getReviewResult()+";";
			if(StringUtils.isNotBlank(oilProQualityGoldReviewRecord.getOpinionDesc())) {
				content += oilProQualityGoldReviewRecord.getOpinionDesc();
			}
			notifyDO.setContent(content);
			notifyDO.setTitle(title);
			notifyService.save(notifyDO);

			long notifyId = notifyDO.getId();
			int reviewId = oilProQualityGoldReviewRecord.getId();
			int proId = oilProQualityGoldReviewRecord.getProId();
			notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.OIL_PRO_QUALITY_GOLD.getProType());

			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("petroleum_engineering_award:oilProQualityGoldReviewRecord:edit")
	public R update( OilProQualityGoldReviewRecordDO oilProQualityGoldReviewRecord){
		oilProQualityGoldReviewRecordService.update(oilProQualityGoldReviewRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilProQualityGoldReviewRecord:remove")
	public R remove( Integer id){
		if(oilProQualityGoldReviewRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilProQualityGoldReviewRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilProQualityGoldReviewRecordService.batchRemove(ids);
		return R.ok();
	}
	
}
