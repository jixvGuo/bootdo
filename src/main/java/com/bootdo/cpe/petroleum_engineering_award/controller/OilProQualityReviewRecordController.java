package com.bootdo.cpe.petroleum_engineering_award.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.AssNotifyProReviewRecord;
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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProQualityReviewRecordDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProQualityReviewRecordService;
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
@RequestMapping("/petroleum_engineering_award/oilProQualityReviewRecord")
public class OilProQualityReviewRecordController extends BaseController {
	@Autowired
	private OilProQualityReviewRecordService oilProQualityReviewRecordService;
	@Autowired
	private PetroleumEngineeringService petroleumEngineeringService;
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilProQualityReviewRecord:oilProQualityReviewRecord")
	String OilProQualityReviewRecord(){
	    return "petroleum_engineering_award/oilProQualityReviewRecord/oilProQualityReviewRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilProQualityReviewRecord:oilProQualityReviewRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilProQualityReviewRecordDO> oilProQualityReviewRecordList = oilProQualityReviewRecordService.list(query);
		int total = oilProQualityReviewRecordService.count(query);
		PageUtils pageUtils = new PageUtils(oilProQualityReviewRecordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilProQualityReviewRecord:add")
	String add(){
	    return "petroleum_engineering_award/oilProQualityReviewRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilProQualityReviewRecord:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilProQualityReviewRecordDO oilProQualityReviewRecord = oilProQualityReviewRecordService.get(id);
		model.addAttribute("oilProQualityReviewRecord", oilProQualityReviewRecord);
	    return "petroleum_engineering_award/oilProQualityReviewRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("petroleumEngineering:qualityPro:review")
	public R save( OilProQualityReviewRecordDO oilProQualityReviewRecord){
		//更新项目状态值
		Map<String,Object> proStatParams = new HashMap<>();
		proStatParams.put("proId", oilProQualityReviewRecord.getProId());
		proStatParams.put("reviewResult", oilProQualityReviewRecord.getReviewResult());
		petroleumEngineeringService.updateProStat(proStatParams);

		//添加
		oilProQualityReviewRecord.setOptUid(getUserId());
		if(oilProQualityReviewRecordService.save(oilProQualityReviewRecord)>0){

			//发送系统通知给用户
			long uid = projectCommonService.getProCreateUid(oilProQualityReviewRecord.getProId());
			Long[] uidArr = {uid};
			NotifyDO notifyDO = new NotifyDO();
			notifyDO.setUserIds(uidArr);
			notifyDO.setCreateBy(getUserId());
			String applyAwardName = oilProQualityReviewRecord.getApplyAwardName();
			String title = (applyAwardName == null ? "" : applyAwardName) + "形式审查结果";
			String content = "形式审查结果:"+oilProQualityReviewRecord.getReviewResult()+";";
			if(StringUtils.isNotBlank(oilProQualityReviewRecord.getOpinionDesc())) {
				content += oilProQualityReviewRecord.getOpinionDesc();
			}
			notifyDO.setContent(content);
			notifyDO.setTitle(title);
			notifyService.save(notifyDO);

			long notifyId = notifyDO.getId();
			int reviewId = oilProQualityReviewRecord.getId();
			int proId = oilProQualityReviewRecord.getProId();
            notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.OIL_PRO_QUALITY.getProType());

			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("petroleum_engineering_award:oilProQualityReviewRecord:edit")
	public R update( OilProQualityReviewRecordDO oilProQualityReviewRecord){
		oilProQualityReviewRecordService.update(oilProQualityReviewRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilProQualityReviewRecord:remove")
	public R remove( Integer id){
		if(oilProQualityReviewRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilProQualityReviewRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilProQualityReviewRecordService.batchRemove(ids);
		return R.ok();
	}
	
}
