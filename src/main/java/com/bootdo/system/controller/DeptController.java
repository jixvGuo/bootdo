package com.bootdo.system.controller;

import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.common.config.BootdoConfig;

import com.bootdo.system.domain.AwardDo;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.service.AwardService;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

import static com.bootdo.common.config.Constant.ROLE_ENTERPRISE_SCIENCE_ID;
import static com.bootdo.cpe.utils.CpeCommonUtils.isCompanyUserRole;

/**
 * 部门管理,修改为企业管理，便于调用保留关键字的使用
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:40:36
 */

@Controller
@RequestMapping("/system/sysDept")
public class DeptController extends BaseController {
	private String prefix = "system/dept";
	@Autowired
	private DeptService sysDeptService;
	@Autowired
	private AwardService awardService;
	@Autowired
	RoleService roleService;

	@Autowired
	BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;

	@GetMapping()
	@RequiresPermissions("system:sysDept:sysDept")
	String dept() {
		return prefix + "/dept";
	}

	@ApiOperation(value="获取企业列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:sysDept:sysDept")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		Object name = query.get("name");
		if(name != null && StringUtils.isNotBlank(name.toString())) {
			query.put("name","%"+name+"%");
		}
		List<DeptDO> sysDeptList = sysDeptService.list(query);
		int total = sysDeptService.count(query);
		PageUtils pageUtil = new PageUtils(sysDeptList, total);

		return pageUtil;
	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("system:sysDept:add")
	String add(@PathVariable("pId") Long pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "总部门");
		} else {
			model.addAttribute("pName", sysDeptService.get(pId).getName());
		}
		return  prefix + "/add";
	}


	/**
	 * 首页快速上传营业执照
	 * 参考 uploadEnterpriseDoc 的存储逻辑
	 */
	@ResponseBody
	@PostMapping("/uploadHomeLicense")
	public R uploadHomeLicense(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return R.error("上传文件不能为空");
		}
		System.out.println("update");
		try {
			// 1. 构建存储路径：年/月/u_用户id/
			long uid = getUserId();
			String curDate = DateUtils.getCurDate(); // 假设格式为 yyyy-MM-dd
			String[] dateArr = curDate.split("-");
			String userFolderPath = dateArr[0] + "/" + dateArr[1] + "/u_" + uid + "/";

			// 处理配置路径 (防止配置中包含 /** 导致路径错误)
			String configUploadPath = bootdoConfig.getUploadPath();
			if (configUploadPath.endsWith("/**")) {
				configUploadPath = configUploadPath.substring(0, configUploadPath.length() - 3);
			}
			String uploadPath = configUploadPath + userFolderPath;

			// 2. 创建目录
			File fileFolder = new File(uploadPath);
			if (!fileFolder.exists()) {
				fileFolder.mkdirs();
			}

			// 3. 处理文件名：原名_时间戳+随机数.后缀
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName;
			if (originalFileName != null && originalFileName.contains(".")) {
				int index = originalFileName.lastIndexOf(".");
				fileName = originalFileName.substring(0, index) + "_" + System.currentTimeMillis()
						+ RandomStringUtils.randomAlphanumeric(4) + originalFileName.substring(index);
			}

			// 4. 保存物理文件到磁盘
			FileUtil.uploadFile(file.getBytes(), uploadPath, fileName);

			// 5. 生成访问 URL
			String fileUrl = "/files/" + userFolderPath + fileName;

			// 6. 保存到 sys_file 表 (保持系统文件记录一致性)
			FileDO sysFile = new FileDO(FileType.fileType(fileName), fileUrl, new Date());
			sysFileService.save(sysFile);

			// 7. 【关键】更新 sys_dept 表的 business_license 字段
			DeptDO sysDept = new DeptDO();
			sysDept.setDeptId(getUser().getDeptId()); // 获取当前登录用户的部门ID
			sysDept.setBusinessLicense(fileUrl); // 设置图片路径

			if (sysDeptService.update(sysDept) > 0) {
				// 返回成功及图片路径，前端用于回显
				return R.ok().put("src", fileUrl);
			}
			return R.error("数据库更新失败");

		} catch (Exception e) {
			e.printStackTrace();
			return R.error("上传发生异常：" + e.getMessage());
		}
	}


	/**
	 * 给企业添加账号
	 * @param pId
	 * @param model
	 * @return
	 */
	@GetMapping("/addAccount/{pId}")
	@RequiresPermissions("system:sysDept:add")
	String addAccount(@PathVariable("pId") Long pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "协会管理");
		} else {
			model.addAttribute("pName", sysDeptService.get(pId).getName());
		}
		List<RoleDO>  roleDOList = roleService.list();
		List<RoleDO> roleList = new ArrayList<>();
		roleDOList.stream().forEach(r->{
			Long rId = r.getRoleId();
			boolean isCompanyRole = isCompanyUserRole(rId);
			if(isCompanyRole) {
				if(ROLE_ENTERPRISE_SCIENCE_ID == rId) {
					r.setRoleSign("true");
				}
				roleList.add(r);
			}
		});
		List<AwardDo> awards = awardService.list();
		model.addAttribute("awards",awards);
		model.addAttribute("companyId",pId);
		model.addAttribute("companyRoleIds",ROLE_ENTERPRISE_SCIENCE_ID);//企业用户角色
		model.addAttribute("roles", roleList);
		return  "system/user/add";
	}

	@GetMapping("/edit/{deptId}")
	@RequiresPermissions("system:sysDept:edit")
	String edit(@PathVariable("deptId") Long deptId, Model model) {
		DeptDO sysDept = sysDeptService.get(deptId);
		model.addAttribute("sysDept", sysDept);
		if(Constant.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
			model.addAttribute("parentDeptName", "无");
		}else {
			DeptDO parDept = sysDeptService.get(sysDept.getParentId());
			model.addAttribute("parentDeptName", parDept.getName());
		}
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:sysDept:add")
	public R save(DeptDO sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (sysDeptService.save(sysDept) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:sysDept:edit")
	public R update(DeptDO sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (sysDeptService.update(sysDept) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:remove")
	public R remove(Long deptId) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", deptId);
		if(sysDeptService.count(map)>0) {
			return R.error(1, "包含下级部门,不允许修改");
		}
		if(sysDeptService.checkDeptHasUser(deptId)) {
			if (sysDeptService.remove(deptId) > 0) {
				return R.ok();
			}
		}else {
			return R.error(1, "部门包含用户,不允许修改");
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] deptIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysDeptService.batchRemove(deptIds);
		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = sysDeptService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}

}
