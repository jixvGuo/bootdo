package com.bootdo.system.controller;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTechnologyController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.system.domain.EnterpriseChengguoBaseInfoDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.EnterpriseChengguoBaseInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bootdo.common.config.Constant.ROLE_SCIENCE_ASSOCIATION_ID;

/**
 * 成果基本情况
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-23 05:48:20
 */
 
@Controller
@RequestMapping("/system/enterpriseChengguoBaseInfo")
public class EnterpriseChengguoBaseInfoController extends BaseScienceTechnologyController {
	private Logger logger = LoggerFactory.getLogger(EnterpriseChengguoBaseInfoController.class);

	@Autowired
	private BootdoConfig bootdoConfig;

	@Autowired
	private FileService sysFileService;

	@Autowired
	private EnterpriseChengguoBaseInfoService enterpriseChengguoBaseInfoService;
	@Autowired
	private AwardEnterpriseProjectService awardEnterpriseProjectService;
	
	@GetMapping()
	@RequiresPermissions("system:enterpriseChengguoBaseInfo:enterpriseChengguoBaseInfo")
	String EnterpriseChengguoBaseInfo(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
	    return "system/enterpriseChengguoBaseInfo/enterpriseChengguoBaseInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:enterpriseChengguoBaseInfo:enterpriseChengguoBaseInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		UserDO user = getUser();
		List<Long> roleIdList = user.getRoleIds();
		if(roleIdList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
			params.put("associationUserId",user.getUserId());
		}
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriseChengguoBaseInfoDO> enterpriseChengguoBaseInfoList = enterpriseChengguoBaseInfoService.list(query);
		int total = enterpriseChengguoBaseInfoService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriseChengguoBaseInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:enterpriseChengguoBaseInfo:add")
	String add(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
	    return "system/enterpriseChengguoBaseInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:enterpriseChengguoBaseInfo:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
		EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo = enterpriseChengguoBaseInfoService.get(id);
		map.addAttribute("enterpriseChengguoBaseInfo", enterpriseChengguoBaseInfo);
	    return "system/enterpriseChengguoBaseInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:enterpriseChengguoBaseInfo:add")
	public R save(EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo,@RequestParam List<String> applyEnterpriseList,
				  @RequestParam List<String> masterEnterpriseList,
				  @RequestPart(value = "file", required = false) MultipartFile file) {
		Map<String, Object> params = new HashMap<>();
		enterpriseChengguoBaseInfo.setApplyEnterpriseList(applyEnterpriseList);
		enterpriseChengguoBaseInfo.setMasterEnterpriseList(masterEnterpriseList);
		params.put("proId", enterpriseChengguoBaseInfo.getProId());
		List<EnterpriseChengguoBaseInfoDO> baseInfoDOList = enterpriseChengguoBaseInfoService.list(params);
		if (baseInfoDOList.size() > 0) {
			return this.update(enterpriseChengguoBaseInfo, file);
		}

		handlePublishAwardTaskUploadFiles(file, enterpriseChengguoBaseInfo);

		if (enterpriseChengguoBaseInfoService.save(enterpriseChengguoBaseInfo) > 0) {

			EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
			projectInfoDo.setProType(EnumProjectType.SCIENCE_PROGRESS.getProType());
			projectInfoDo.setProSubType(EnumProjectType.SCIENCE_PROGRESS.getProType());
			projectInfoDo.setId(Integer.parseInt(enterpriseChengguoBaseInfo.getProId()));
			projectInfoDo.setMajor(enterpriseChengguoBaseInfo.getMajor());
			awardEnterpriseProjectService.updateProjectInfo(projectInfoDo);

			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:enterpriseChengguoBaseInfo:edit")
	public R update(EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo, @RequestPart(value = "file",required = false) MultipartFile file){
		handlePublishAwardTaskUploadFiles(file, enterpriseChengguoBaseInfo);
		enterpriseChengguoBaseInfoService.update(enterpriseChengguoBaseInfo);

		if(StringUtils.isNotBlank(enterpriseChengguoBaseInfo.getMajor())) {
			EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
			projectInfoDo.setId(Integer.parseInt(enterpriseChengguoBaseInfo.getProId()));
			projectInfoDo.setMajor(enterpriseChengguoBaseInfo.getMajor());
			awardEnterpriseProjectService.updateProjectInfo(projectInfoDo);
		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:enterpriseChengguoBaseInfo:remove")
	public R remove( Integer id){
		if(enterpriseChengguoBaseInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:enterpriseChengguoBaseInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriseChengguoBaseInfoService.batchRemove(ids);
		return R.ok();
	}

	private void handlePublishAwardTaskUploadFiles(MultipartFile file, EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo) {
		if (file != null) {
			String fileName = file.getOriginalFilename();
			fileName = FileUtil.renameToUUID(fileName);
			FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
			try {
				FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
				int rstCount = sysFileService.save(sysFile);
				if (rstCount > 0) {
					enterpriseChengguoBaseInfo.setApplyBookContent(String.valueOf(sysFile.getId()));
				}
			} catch (Exception e) {
				logger.error("upload file error {}", e.getMessage());
			}
		}
	}
}
