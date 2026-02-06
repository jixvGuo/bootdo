package com.bootdo.cpe.petroleum_engineering_award.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProStatEnum;
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

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProInstallReviewRecordDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProInstallReviewRecordService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 石油安装工程形式审查结果记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-18 23:28:53
 */
 
@Controller
@RequestMapping("/petroleum_engineering_award/oilProInstallReviewRecord")
public class OilProInstallReviewRecordController extends BaseController {
	@Autowired
	private OilProInstallReviewRecordService oilProInstallReviewRecordService;
	@Autowired
	private PetroleumEngineeringService petroleumEngineeringService;
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@GetMapping()
	@RequiresPermissions("petroleum_engineering_award:oilProInstallReviewRecord:oilProInstallReviewRecord")
	String OilProInstallReviewRecord(){
	    return "petroleum_engineering_award/oilProInstallReviewRecord/oilProInstallReviewRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("petroleum_engineering_award:oilProInstallReviewRecord:oilProInstallReviewRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OilProInstallReviewRecordDO> oilProInstallReviewRecordList = oilProInstallReviewRecordService.list(query);
		int total = oilProInstallReviewRecordService.count(query);
		PageUtils pageUtils = new PageUtils(oilProInstallReviewRecordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("petroleum_engineering_award:oilProInstallReviewRecord:add")
	String add(){
	    return "petroleum_engineering_award/oilProInstallReviewRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("petroleum_engineering_award:oilProInstallReviewRecord:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OilProInstallReviewRecordDO oilProInstallReviewRecord = oilProInstallReviewRecordService.get(id);
		model.addAttribute("oilProInstallReviewRecord", oilProInstallReviewRecord);
	    return "petroleum_engineering_award/oilProInstallReviewRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("petroleumEngineering:enterpriseUnitApply:review")
	public R save( OilProInstallReviewRecordDO oilProInstallReviewRecord){
		//更新项目状态值
		Map<String,Object> proStatParams = new HashMap<>();
		proStatParams.put("proId", oilProInstallReviewRecord.getProId());
		proStatParams.put("reviewResult", oilProInstallReviewRecord.getReviewResult());
		petroleumEngineeringService.updateProStat(proStatParams);

	    //添加
		oilProInstallReviewRecord.setOptUid(getUserId());
		if(oilProInstallReviewRecordService.save(oilProInstallReviewRecord)>0){
			//发送系统通知给用户
            long uid = projectCommonService.getProCreateUid(oilProInstallReviewRecord.getProId());
            Long[] uidArr = {uid};
			NotifyDO notifyDO = new NotifyDO();
			notifyDO.setUserIds(uidArr);
			notifyDO.setCreateBy(getUserId());
			String applyAwardName = oilProInstallReviewRecord.getApplyAwardName();
			String title = (applyAwardName == null ? "" : applyAwardName) + "形式审查结果";
			String content = "形式审查结果:"+oilProInstallReviewRecord.getReviewResult()+";";
			if(StringUtils.isNotBlank(oilProInstallReviewRecord.getOpinionDesc())) {
				content += oilProInstallReviewRecord.getOpinionDesc();
			}
			notifyDO.setContent(content);
			notifyDO.setTitle(title);
			notifyService.save(notifyDO);

			long notifyId = notifyDO.getId();
			int reviewId = oilProInstallReviewRecord.getId();
			int proId = oilProInstallReviewRecord.getProId();
			notifyService.saveProReviewNotifyShip(notifyId, proId, reviewId, EnumProjectType.OIL_PRO_INSTALL.getProType());

			return R.ok();
		}

		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("petroleum_engineering_award:oilProInstallReviewRecord:edit")
	public R update( OilProInstallReviewRecordDO oilProInstallReviewRecord){
		oilProInstallReviewRecordService.update(oilProInstallReviewRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilProInstallReviewRecord:remove")
	public R remove( Integer id){
		if(oilProInstallReviewRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("petroleum_engineering_award:oilProInstallReviewRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oilProInstallReviewRecordService.batchRemove(ids);
		return R.ok();
	}
	
}
