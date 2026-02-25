package com.bootdo.system.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.ApplicationContextRegister;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.AssDeptNotifyRecord;
import com.bootdo.cpe.domain.RoleAwardParamData;
import com.bootdo.cpe.utils.CpeException;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.service.NotifyService;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.MailDO;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.*;
import com.bootdo.system.vo.CompanyInfoVO;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.bootdo.common.utils.SpringContextHolder.getBean;
import java.io.File;

@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MenuService menuService;
    @Autowired
    FileService fileService;
    @Autowired
    BootdoConfig bootdoConfig;
    @Autowired
    AwardService awardService;
    @Autowired
    UserService userService;
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private MailService mailService;

    @Autowired
    private DeptService sysDeptService;




    @GetMapping({"/", ""})
    String welcome(Model model) {
//        return "blog/uwp/index.html";
        return "home/index.html";
//将首页改成各个磁块的展示
//        return "redirect:/index";
    }

    @GetMapping("/to_index")
    public String toIndex(String award,String awardId, ModelMap map){
        map.put("award", award);
        map.put("awardId", awardId);
        return "redirect:/index?award="+award + "&awardId=" + awardId;
    }

    @Log("请求访问主页")
    @RequestMapping({"/index"})
    String index(String awardId, Model model) {
        Integer awardIdInt = null;
        ShiroUtils.getSubjct().getSession().setAttribute("current_award_id", awardId);
        System.out.println("DEBUG: 已将 awardId=" + awardId + " 存入 Session");
        try {
            RoleAwardParamData paramData = RoleAwardParamData.getInstance(getUser().getRoleIds(), awardId);
            System.out.println(awardId);
            System.out.println("当前awardid");
            System.out.println(paramData.getAwardId());
            System.out.println(paramData.toString());
            awardIdInt = paramData.getAwardId();

        } catch (CpeException e) {
        }
        model.addAttribute("award", awardId);
        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId(), awardIdInt);
        model.addAttribute("menus", menus);
        model.addAttribute("name", getUser().getName());
        FileDO fileDO = fileService.get(getUser().getPicId());
        if (fileDO != null && fileDO.getUrl() != null) {
            if (fileService.isExist(fileDO.getUrl())) {
                model.addAttribute("picUrl", fileDO.getUrl());
            } else {
                model.addAttribute("picUrl", "/img/photo_s.jpg");
            }
        } else {
            model.addAttribute("picUrl", "/img/photo_s.jpg");
        }
        model.addAttribute("username", getUser().getUsername());
        Map<String, Object> params = new HashMap<>(16);
        params.put("offset", 0);
        params.put("limit", 10);
        Query query = new Query(params);
        query.put("userId", getUserId());
        query.put("isRead", Constant.OA_NOTIFY_READ_NO);
        PageUtils pageUtils = notifyService.selfList(query);
        model.addAttribute("rows", pageUtils.getRows());
        model.addAttribute("total", pageUtils.getTotal());
        return "index_v1";
    }

    @GetMapping("/login")
    String login(Model model) {
        /*List<AwardDo> awards = awardService.list();
        model.addAttribute("awards",awards);*/
        model.addAttribute("username", bootdoConfig.getUsername());
        model.addAttribute("password", bootdoConfig.getPassword());

        return "login";
    }

    @RequestMapping("/toLogin")
    String login(String awardId, ModelMap map, HttpServletRequest request) {
        map.put("awardId", awardId);
        return "login";
    }


    @Log("登录")
    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password,String awardId) {
        System.out.println( "id" + awardId);
        password = MD5Utils.encrypt(username, password);
        System.out.println( "username" + password);
        System.out.println( "密码 " + password);
        ApplicationContextRegister.setCurrentType(awardId);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();

        } catch (UnknownAccountException e) {
            return R.error("账号不存在");

        } catch (DisabledAccountException e) {  // 新增：模块选择错误
            return R.error("请选择正确的模块进入系统");

        }catch (IncorrectCredentialsException e) {
            return R.error("账号或密码错误");  // 真正的密码错误

        }

        catch (ExcessiveAttemptsException e) {
            return R.error("登录失败次数过多，请稍后再试");

        } catch (AuthenticationException e) {
            return R.error("登录失败，请检查账号密码");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

//    @GetMapping("/index_v3")
//    String index_v3() {
//
//        return "index_v3";
//    }
//    @GetMapping("/index_v3")
//    String index_v3(Model model) {
//        // 1. 获取当前登录用户信息
//        UserDO user = getUser();
//
//        // =======================================================
//        // 【配置项】请在此处修改为数据库 sys_role 表中 QC企业 对应的 role_id
//        // 假设 QC企业的角色ID是 2，如果数据库里是 5，请改为 5L
//        Long QC_ROLE_ID = 71L;
//        // =======================================================
//
//        boolean isQcEnterprise = false;
//        String companyPhoto = "";
//        String companyName = "";
//
//        // 2. 判断用户是否拥有 QC企业角色
//        List<Long> roleIds = user.getRoleIds();
//        // 防空判断：如果session中没有角色列表，重新查一遍数据库
//        if (roleIds == null || roleIds.isEmpty()) {
//            UserDO fullUser = userService.get(user.getUserId());
//            if (fullUser != null) {
//                roleIds = fullUser.getRoleIds();
//            }
//        }
//
//        if (roleIds != null && roleIds.contains(QC_ROLE_ID)) {
//            isQcEnterprise = true;
//
//            // 3. 如果是QC企业，查询部门信息获取图片
//            Long deptId = user.getDeptId();
//            if (deptId != null) {
//                DeptDO dept = sysDeptService.get(deptId);
//                if (dept != null) {
//                    // 获取营业执照路径
//                    companyPhoto = dept.getBusinessLicense();
//                    // 获取企业名称
//                    companyName = dept.getName();
//                }
//            }
//        }
//        // 4. 将数据放入 Model 传给前端
//        model.addAttribute("isQcEnterprise", isQcEnterprise); // 是否显示板块
//        model.addAttribute("companyPhoto", companyPhoto);     // 图片路径
//        model.addAttribute("companyName", companyName);       // 企业名称
//
//        return "index_v3";
//    }
    /**
     * 首页 V3 版本
     * 增加 awardId 参数接收
     */
    @GetMapping("/index_v3")
    String index_v3(@RequestParam(required = false)String awardId, Model model) {
        // 1. 获取当前登录用户
        UserDO user = getUser();
        if (awardId == null || awardId.isEmpty()) {
            Object sessionVal = ShiroUtils.getSubjct().getSession().getAttribute("current_award_id");
            if (sessionVal != null) {
                awardId = sessionVal.toString();
            }
        }

        System.out.println("DEBUG: index_v3 获取到的 awardId 为: " + awardId);

        // =======================================================
        // 【配置项】请确认数据库 sys_role 表中 QC企业 对应的 role_id
        Long QC_ROLE_ID = 71L;
        // =======================================================

        // 2. 判断是否拥有 QC企业角色
        boolean isQcRole = false;
        List<Long> roleIds = user.getRoleIds();
        // 防止缓存导致 roleIds 为空，重新查询一次
        if (roleIds == null || roleIds.isEmpty()) {
            UserDO fullUser = userService.get(user.getUserId());
            if (fullUser != null) {
                roleIds = fullUser.getRoleIds();
            }
        }
        if (roleIds != null && roleIds.contains(QC_ROLE_ID)) {
            isQcRole = true;
        }
        System.out.println(awardId);
        System.out.println(isQcRole);
        // 3. 判断 awardId 是否等于 3
        boolean isAwardThree = "3".equals(awardId);
        System.out.println(isAwardThree);
        // 4. 最终决定是否显示营业执照板块 (必须同时满足两个条件)
        boolean showLicense = isQcRole && isAwardThree;

        // 5. 如果需要显示，则查询数据库获取图片路径
        if (showLicense) {
            Long deptId = user.getDeptId();
            if (deptId != null) {
                DeptDO dept = sysDeptService.get(deptId);
                if (dept != null) {
                    model.addAttribute("companyPhoto", dept.getBusinessLicense());
                    model.addAttribute("companyName", dept.getName());
                }
            }
        }

        // 6. 将控制开关放入 Model
        model.addAttribute("showLicense", showLicense);

        return "index_v3";
    }




    @GetMapping("/to_register")
    String to_register() {
        return "register";
    }

    /**
     * TODO 重复公司注册是否过滤
     * @param companyInfoVO
     * @return
     */
    @Log("注册公司信息")
    @PostMapping("/register_company")
    @ResponseBody
    R ajaxRegisterCompany(CompanyInfoVO companyInfoVO, @RequestParam(value = "awardIds", required = false) List<Long> awardIds) {
        if (awardIds != null && !awardIds.isEmpty()) {
            Set<Long> uniqueAwardIds = new LinkedHashSet<>(awardIds);
            awardIds = new ArrayList<>(uniqueAwardIds);
            System.out.println("去重后的角色IDs: " + awardIds.toString());
        }
        // 根据公司名称查询deptId
        String companyName = companyInfoVO.getCompany_name();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", companyName);
        List<DeptDO> existingDepts = sysDeptService.list(queryMap);
        int deptId;


        int rst = userService.registerCompanyInfo(companyInfoVO);
        if(rst > 0) {
            //发送通知消息
            String title = companyInfoVO.getCompany_name() + "提交注册,已自动分配账号密码";
            NotifyService notifyService = getBean("notifyService");
            NotifyDO notifyDO = new NotifyDO();
            //获取协会工作人员用户id
            Map<String,Object> params = new HashMap<>();
            //TODO 需要处理企业注册信息的协会工作人员
            params.put("roleId",64);
            List<UserDO> assWorkers = userService.list(params);
            Long[] uids = new Long[assWorkers.size()];
            //协会工作人员邮件
            String[] emailArr = new String[uids.length];
            final int[] i = {0};
            assWorkers.stream().forEach(w->{
                uids[i[0]] = w.getUserId();
                emailArr[i[0]] = w.getEmail();
                i[0]++;
            });
            notifyDO.setUserIds(uids);
            notifyDO.setTitle(title);
            notifyDO.setType("4");
            notifyDO.setCreateBy(-100L);
            notifyDO.setContent(title);
            notifyService.save(notifyDO);
            Long notifyId = notifyDO.getId();
            int deptId1 = companyInfoVO.getDeptId();
            AssDeptNotifyRecord assDeptNotifyRecord = new AssDeptNotifyRecord();
            assDeptNotifyRecord.setNotifyId(notifyId == null ? 0 : notifyId.longValue());
            assDeptNotifyRecord.setDeptId(deptId1);
            notifyService.saveDeptNotify(assDeptNotifyRecord);
            //邮件通知协会人员
            MailDO enterpriseMailNotify = new MailDO();
            enterpriseMailNotify.setEmail(emailArr);
            enterpriseMailNotify.setContent(title);
            enterpriseMailNotify.setTitle(title);
            mailService.sendTextMail(enterpriseMailNotify);

            //启用企业
            DeptDO dept = new DeptDO();
            dept.setDeptId((long) companyInfoVO.getDeptId());
            dept.setDelFlag(1); // 启用企业
            sysDeptService.update(dept);

            //分配账号密码
            UserDO user = new UserDO();
            // 生成初始密码
            String initPwd = "123456";
            user.setUsername(companyInfoVO.getCompany_concat_phone()); // 用手机号做用户名
            user.setName(companyInfoVO.getCompany_name());
            user.setPassword(MD5Utils.encrypt(user.getUsername(), initPwd));
            if(existingDepts != null && !existingDepts.isEmpty()) {
                // 如果找到同名企业，使用现有企业ID
                deptId = existingDepts.get(0).getDeptId().intValue();
                user.setDeptId((long) deptId);
                System.out.println("找到同名企业: " + companyName + ", 使用deptId: " + deptId);
            }else{
                deptId =companyInfoVO.getDeptId();
                user.setDeptId((long) companyInfoVO.getDeptId()); // long类型4
                System.out.println("未找到同名企业: " + companyName + ", 使用deptId: " + deptId);
            }
            user.setEmail(companyInfoVO.getCompany_concat_email());
            user.setRoleIds(awardIds);
            System.out.println(user.getRoleIds().toString());
            user.setStatus(1); // 启用
            userService.save(user);
            System.out.println("分配账号");

            return R.ok("注册成功<br/>"+ "账号：" + companyInfoVO.getCompany_concat_phone() + "<br/>" +
                    "密码：123456" + "<br/>" +
                    "请登录后尽快修改密码");
        }
        return R.error();
    }



}