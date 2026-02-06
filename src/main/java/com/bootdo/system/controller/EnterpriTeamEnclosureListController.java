package com.bootdo.system.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.system.domain.EnterpriTeamEnclosureListDO;
import com.bootdo.system.service.EnterpriTeamEnclosureListService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 附件列表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-05 08:21:09
 */
 
@Controller
@RequestMapping("/system/enterpriTeamEnclosureList")
public class EnterpriTeamEnclosureListController extends BaseScienceTeamController {
	private String prefix = "enterprise/apply/team";

	private Logger logger = LoggerFactory.getLogger(EnterpriTeamEnclosureListController.class);

	@Autowired
	private EnterpriTeamEnclosureListService enterpriTeamEnclosureListService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	@GetMapping()
	@RequiresPermissions("system:enterpriTeamEnclosureList:enterpriTeamEnclosureList")
	String EnterpriTeamEnclosureList(@RequestParam Map<String, Object> params, ModelMap map){
		packageAwardTaskId(map, params);
	    return "system/enterpriTeamEnclosureList/enterpriTeamEnclosureList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:enterpriTeamEnclosureList:enterpriTeamEnclosureList")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EnterpriTeamEnclosureListDO> enterpriTeamEnclosureListList = enterpriTeamEnclosureListService.list(query);
		int total = enterpriTeamEnclosureListService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriTeamEnclosureListList, total);
		return pageUtils;
	}

	@GetMapping("/toList")
	@RequiresPermissions("system:enterpriTeamEnclosureList:enterpriTeamEnclosureList")
	public String list(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
		//查询列表数据
		List<EnterpriTeamEnclosureListDO> enterpriTeamEnclosureListList = enterpriTeamEnclosureListService.list(params);
		map.put("list", enterpriTeamEnclosureListList);
		return prefix + "/enclosure_info_list_14";
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:enterpriTeamEnclosureList:add")
	String add(@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
		map.put("enterpriTeamEnclosureList", new EnterpriTeamEnclosureListDO());
		return prefix + "/enclosure_info_add_14";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:enterpriTeamEnclosureList:edit")
	String edit(@PathVariable("id") Integer id,@RequestParam Map<String, Object> params, ModelMap map) {
		packageAwardTaskId(map, params);
		EnterpriTeamEnclosureListDO enterpriTeamEnclosureList = enterpriTeamEnclosureListService.get(id);
		map.addAttribute("enterpriTeamEnclosureList", enterpriTeamEnclosureList);
		return prefix + "/enclosure_info_add_14";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:enterpriTeamEnclosureList:add")
	public R save(@RequestPart("file") MultipartFile file, EnterpriTeamEnclosureListDO enterpriTeamEnclosureList) {
		if (file != null) {
			String fileName = file.getOriginalFilename();
			FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
			try {
				FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			} catch (Exception e) {
				logger.error("publish task upload file error {}", e.getMessage());
			}
			int rstCount = sysFileService.save(sysFile);
			if (rstCount > 0) {
				enterpriTeamEnclosureList.setFileId(sysFile.getId());
			}
		}
		Integer id = enterpriTeamEnclosureList.getId();
		if (id != null && id > 0) {
			enterpriTeamEnclosureListService.update(enterpriTeamEnclosureList);
			return R.ok();
		}
		if(enterpriTeamEnclosureListService.save(enterpriTeamEnclosureList)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:enterpriTeamEnclosureList:edit")
	public R update( EnterpriTeamEnclosureListDO enterpriTeamEnclosureList){
		enterpriTeamEnclosureListService.update(enterpriTeamEnclosureList);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:enterpriTeamEnclosureList:remove")
	public R remove( Integer id){
		if(enterpriTeamEnclosureListService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:enterpriTeamEnclosureList:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		enterpriTeamEnclosureListService.batchRemove(ids);
		return R.ok();
	}
	
}
