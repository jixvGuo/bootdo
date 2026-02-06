package com.bootdo.system.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.EnterpriseAccountInitPwdData;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.*;
import com.bootdo.system.vo.UserVO;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

import static com.bootdo.common.config.Constant.ROLE_ADMIN_ID;
import static com.bootdo.common.config.Constant.ROLE_ENTERPRISE_SCIENCE_ID;
import static com.bootdo.cpe.utils.CpeCommonUtils.isCompanyUserRole;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
	private String prefix="system/user"  ;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	DictService dictService;
	@Autowired
	private DeptService sysDeptService;
    @Autowired
	private AwardService awardService;
    @Autowired
    MailService mailService;

	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		String name = query.get("name") == null ? "" : query.get("name").toString();
		if(StringUtils.isNotBlank(name)) {
			query.put("name","%"+name.trim()+"%");
		}

		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@RequiresPermissions("sys:user:add")
	@Log("添加用户")
	@GetMapping("/add")
	String add(Model model) {
		Map<String, Object> departMap = new HashMap<>();
		//已启动的账号
		departMap.put("delFlag", 1);
		List<DeptDO> enterpriseList = sysDeptService.list(departMap);
		List<RoleDO> roles = roleService.list();
		List<AwardDo> awards = awardService.list();
		model.addAttribute("roles", roles);
		model.addAttribute("enterpriseList",enterpriseList);
		model.addAttribute("awards",awards);
		return prefix + "/add";
	}

	@RequiresPermissions("sys:user:edit")
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		UserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		List<RoleDO> roles = roleService.list(id);
		List<Long> userRoleIds = userDO.getRoleIds();
		//如果是超级管理员，所有角色都显示出来便于管理便捷
		boolean isAdmin = getUser().getRoleIds().contains(ROLE_ADMIN_ID);
		boolean isCompanyFlg = isCompanyUserRole(userRoleIds);
		model.addAttribute("isCompany", isCompanyFlg);
		List<RoleDO> roleList = new ArrayList<>();
		roles.stream().forEach(r->{
			Long rId = r.getRoleId();
			if(rId != null && userRoleIds.contains(rId.longValue())) {
				r.setRoleSign("true");
			}
			boolean isCompanyRole = isAdmin || isCompanyUserRole(rId);
			if(isCompanyRole) {
                roleList.add(r);
			}
		});

		Long deptId = userDO.getDeptId();
		long enterpriseId = deptId == null ? 0 : deptId.longValue();
		Map<String, Object> departMap = new HashMap<>();
		//已启动的账号
		departMap.put("delFlag", 1);
        List<DeptDO> enterpriseList = sysDeptService.list(departMap);
        List<AwardDo> awards = awardService.list();
        List<Long> awardIdList = awardService.getAwardIdsByUserId(id);
        awardIdList.stream().forEach(a->{
            awards.stream().forEach(e->{
                if(a.longValue() == e.getId().longValue()) {
                    e.setFlg("true");
                }
            });
        });
        enterpriseList.stream().forEach(e->{
            if(enterpriseId == e.getDeptId().longValue()) {
                e.setFlg("true");
            }
        });
        model.addAttribute("roles", roleList);
        model.addAttribute("enterpriseList",enterpriseList);
        model.addAttribute("awards",awards);
		return prefix+"/edit";
	}

	@RequiresPermissions("sys:user:add")
	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
	R save(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		boolean isCompanyAccount = false;
		Integer companyId = user.getCompanyId();
		if(companyId != null && companyId > 0) {
			isCompanyAccount = true;
			//企业注册账号
			String emial = user.getEmail();
			if(StringUtils.isNotBlank(emial)) {
				String[] emailArr = {emial};
				MailDO mailUserDO = new MailDO();
				mailUserDO.setTitle("分配的账号"+user.getUsername()+"及密码");
				mailUserDO.setContent("账号密码:" + user.getPassword());
				mailUserDO.setEmail(emailArr);
				mailService.sendTextMail(mailUserDO);
			}

		}
		String pwd = user.getPassword();
		user.setPassword(MD5Utils.encrypt(user.getUsername(), pwd));
		if (userService.save(user) > 0) {
			if(isCompanyAccount) {
				EnterpriseAccountInitPwdData initPwdData = new EnterpriseAccountInitPwdData();
				initPwdData.setPwd(pwd);
				initPwdData.setUid(user.getUserId());
				userService.saveEnterpriseInitPwdData(initPwdData);
			}
			return R.ok();
		}
		return R.error();
	}


	/***
	 * 添加专家账户
	 * @param user
	 * @return
	 */
	@PostMapping("/savepro")
	@ResponseBody
	R savepro(UserDO user) {

		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		String username = user.getUsername().trim();
		List<Long> uidList = userService.getUidByLoginUserName(username);
		if(uidList.size() > 0) {
			return R.ok(uidList.get(0) + "");
		}
		user.setPassword(MD5Utils.encrypt(username, user.getPassword().trim()));

		int resUserId =  userService.save(user) ;

		if ( resUserId > 0) {
			return R.ok(user.getUserId() + "");
		}
		return R.error();
	}




	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}

		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	R updatePeronal(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.updatePersonal(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	@RequiresPermissions("sys:user:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:batchRemove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}

	@RequiresPermissions("sys:user:resetPwd")
	@Log("请求更改用户密码")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {

		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}

	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	R resetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			UserDO user = userVO.getUserDO();
			Integer companyId = user.getCompanyId();
			if(companyId != null && companyId > 0) {
				//企业注册账号
				String emial = user.getEmail();
				if(StringUtils.isNotBlank(emial)) {
					String[] emailArr = {emial};
					MailDO mailUserDO = new MailDO();
					mailUserDO.setTitle("分配的账号"+user.getUsername()+"及密码");
					mailUserDO.setContent("账号密码:" + user.getPassword());
					mailUserDO.setEmail(emailArr);
					mailService.sendTextMail(mailUserDO);
				}
			}
			userService.resetPwd(userVO,getUser());
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}
	@RequiresPermissions("sys:user:resetPwd")
	@Log("admin提交更改用户密码")
	@PostMapping("/adminResetPwd")
	@ResponseBody
	R adminResetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.adminResetPwd(userVO);
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}
	@GetMapping("/tree")
	@ResponseBody
	public String tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = userService.getTree();
		return tree.toString();
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}

	@GetMapping("/personal")
	String personal(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		model.addAttribute("hobbyList",dictService.getHobbyList(userDO));
		model.addAttribute("sexList",dictService.getSexList());
		return prefix + "/personal";
	}
	@ResponseBody
	@PostMapping("/uploadImg")
	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.updatePersonalImg(file, avatar_data, getUserId());
		} catch (Exception e) {
			return R.error("更新图像失败！");
		}
		if(result!=null && result.size()>0){
			return R.ok(result);
		}else {
			return R.error("更新图像失败！");
		}
	}
}
