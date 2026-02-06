package com.bootdo.cpe.controller.science_progress;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseScienceTechnologyController;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.petroleum_engineering_award.domain.*;
import com.bootdo.cpe.service.EnterpriTeamLeaderInfoService;
import com.bootdo.cpe.service.ExpertGroupService;
import com.bootdo.cpe.service.ScienceProcessService;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bootdo.common.config.Constant.*;

/**
 * 科技奖控制处理
 *
 * @author houzb
 * @version 1.0
 * @date 2021-04-16 8:14
 */
@Controller
@RequestMapping("/scienceProgressScience")
public class ScienceController extends BaseScienceTechnologyController {
    @Autowired
    private AwardFlowService awardFlowService;
    @Autowired
    private EnterpriseChengguoBaseInfoService enterpriseChengguoBaseInfoService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    private UserService userService;
    @Autowired
    private ScienceProcessService scienceProcessService;
    @Autowired
    private ExpertGroupService expertGroupService;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private DictService dictService;
    @Autowired
    private EnterpriTeamInfoService enterpriTeamInfoService;
    @Autowired
    private EnterpriPersonalInfoService enterpriPersonalInfoService;
    @Autowired
    private EnterpriTeamLeaderInfoService enterpriTeamLeaderInfoService; //主要成员情况带头人
    @Autowired
    private EnterpriseChengguoOtherInfoService enterpriseChengguoOtherInfoService;

    @RequestMapping("/updateProMajor")
    @ResponseBody
    public R upateProMajor(int id, String major,String proType) {
        EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
        projectInfoDo.setId(id);
        projectInfoDo.setMajor(major);
        int rst = awardEnterpriseProjectService.updateProjectInfo(projectInfoDo);
        if(rst > 0) {
            if("science".equals(proType)) {
                EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo = new EnterpriseChengguoBaseInfoDO();
                enterpriseChengguoBaseInfo.setProId(id + "");
                enterpriseChengguoBaseInfo.setMajor(major);
                enterpriseChengguoBaseInfo.setSubjectType(major);
                enterpriseChengguoBaseInfoService.update(enterpriseChengguoBaseInfo);
            }

            if("team".equals(proType)) {
                EnterpriTeamInfoDO enterpriTeamInfo = new EnterpriTeamInfoDO();
                enterpriTeamInfo.setProId(id);
                enterpriTeamInfo.setSubjectClassificationName(major);
                enterpriTeamInfo.setResearchDirection(major);
                enterpriTeamInfoService.updateMajor(enterpriTeamInfo);
            }

            if("personal".equals(proType)) {
                EnterpriPersonalInfoDO enterpriPersonalInfo = new EnterpriPersonalInfoDO();
                enterpriPersonalInfo.setProId(id);
                enterpriPersonalInfo.setWorkMajor(major);
                enterpriPersonalInfoService.updateMajor(enterpriPersonalInfo);
            }

            return R.ok();
        }
        return R.error("更新失败");
    }

    @RequiresPermissions("technologyaward:to:prolist")
    @RequestMapping("/toProListMain")
    public String toProListMain(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/science/technology_pro_list_main";
    }
    /**
     * 查看项目列表
     * @param map
     * @param params
     * @return
     */
    @RequiresPermissions("technologyaward:to:prolist")
    @RequestMapping("/toProList")
    public String toProList(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("proSubType", params.get("proSubType"));

        return "cpe/science/technology_pro_list";
    }

      /**
     * 获取项目列表
     * @return
     */
    @RequestMapping("/get/proList")
    @ResponseBody
    public PageUtils getSurverProList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        UserDO user = getUser();
        Long uid = getUserId();
        List<Long> roleIdList = user.getRoleIds();

        if (roleIdList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
            //todo 临时使用协会联系人的用户id
            params.put("associationUserId", roleIdList.contains(ROLE_SCIENCE_OFFLINE_VIEW_ID) ? 101 : user.getUserId());
        } else if (roleIdList.contains(ROLE_ENTERPRISE_SCIENCE_ID)) {
            //企业用户查看自己创建项目
            params.put("enterpriseUid", uid);
        } else if(roleIdList.contains(ROLE_SPECIALIST_ID)) {
            //评审专家
            params.put("scoreSpecialistUid", uid);
        }else {
            //分派给自己的项目
            params.put("ass_assign_uid", uid);
        }
        getProListParamsByRole(params);

        params.put("proStatStr", "");
        Object keyWordObj = params.get("keyWord");
        if (keyWordObj != null && StringUtils.isNotBlank(keyWordObj.toString())) {
            List<String> proStatList = QcProStatEnum.getStatValByKey(keyWordObj.toString());
            if (proStatList.size() > 0) {
                String str = new String();
                for (String s : proStatList) {
                    str +=  s + ",";
                }
                params.put("proStatStr", str.substring(0, str.length() - 1));
            }
            params.put("keyWord", "%" + keyWordObj + "%");
        }
        this.newParams = params;
        Query query = new Query(params);

        List<TechnologyProjectInfo> proDataDtoList = scienceProcessService.listProInfo(query);
        int total = scienceProcessService.countProInfo(query);
        //权限校验仅lidan协会领导,协会联系人,外聘人员可以查看编号,其余不可查看
        if(!CommonUtils.isViewProCode(roleIdList)) {
             proDataDtoList.stream().forEach(pd->{
                 pd.setProCode("");
             });
        }
        PageUtils pageUtils = new PageUtils(proDataDtoList, total);
        return pageUtils;
    }

    /**
     * 跳转到要申报的项目列表
     *
     * @param map
     * @return
     */
    @RequestMapping("/toApplyPros")
    @RequiresPermissions("act:award:apply_pros")
    public String toApplyProList(@RequestParam Map<String, Object> params, ModelMap map) {
        UserDO user = getUser();
        List<Long> roleIdList = user.getRoleIds();
        isTaskIsApply(map, params, roleIdList, user);
        packageAwardTaskId(map, params);
        List<PublishAwardTaskDo> taskDoList = awardFlowService.getAwardLastTasksByAwardType(20, EnumAwardType.SCIENCE.getAwrdType());
        if(roleIdList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
            //TODO 临时取消下发专业，需要根据项目状态判断是否下发
            /*List<DictDO> dictDOList = new ArrayList<>();
            DictDO selOptionDo = new DictDO();
            selOptionDo.setName("请选择");
            dictDOList.add(selOptionDo);
            List<DictDO> dictDOS = dictService.listByType("profession_type");
            dictDOList.addAll(dictDOS);
            map.put("specialTypeList", dictDOList);*/
        }
        map.put("taskList", taskDoList);
        map.put("apply_pro_type", "science");
        return "act/award/enterprise_doc_award_pro_list";
    }

    private Map<String, Object> newParams;

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params, ModelMap map) {
        //获取用户的角色不同角色查看不同的信息,超级管理员全部的,发布者全部的,分派的人员查看分派的,企业只能看自己创建的,专家可以查看分派的
        //是否为超级管理员全部的,发布者
        packageAwardTaskId(map, params);
        UserDO user = getUser();
        Long uid = getUserId();
        List<Long> roleIdList = user.getRoleIds();
        if (roleIdList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
            //todo 临时使用协会联系人的用户id
            params.put("associationUserId", roleIdList.contains(ROLE_SCIENCE_OFFLINE_VIEW_ID) ? 101 : user.getUserId());
        } else if (roleIdList.contains(ROLE_ENTERPRISE_SCIENCE_ID)) {
            //企业用户查看自己创建项目
            params.put("enterpriseUid", uid);
        } else {
            //分派给自己的项目
            params.put("ass_assign_uid", uid);
        }
        getProListParamsByRole(params);
        this.newParams = params;

        params.put("proStatStr", "");
        Object keyWordObj = params.get("keyWord");
        if (keyWordObj != null) {
            List<String> proStatList = OilProStatEnum.getStatValByKey(keyWordObj.toString());
            if (proStatList.size() > 0) {
                String str = new String();
                for (String s : proStatList) {
                    str +=  s + ",";
                }
                params.put("proStatStr", str.substring(0, str.length() - 1));
            }
        }

        Query query = new Query(params);
        List<EnterpriseProjectInfoDo> proList = awardEnterpriseProjectService.list(query);
        int total = awardEnterpriseProjectService.count(query);
        PageUtils pageUtils = new PageUtils(proList, total);
        return pageUtils;
    }


    @RequestMapping("/printExcel")
    @ResponseBody
    public String printExcel(HttpServletResponse response, ModelMap map, @RequestParam Map<String, Object> params) {

        this.newParams.put("limit", "100000");
        Query query = new Query(this.newParams);


        List<EnterpriseProjectInfoDo> enterpriTeamInfoList = awardEnterpriseProjectService.list(query);
        System.out.println(enterpriTeamInfoList.size());

        Map<String, Object> proParamsMap =new HashMap<String, Object>();
        proParamsMap.putAll(this.newParams);
        proParamsMap.remove("proSubType");
        proParamsMap.remove("keyWord");
        proParamsMap.remove("proStatStr");
        List<TechnologyProjectInfo> proDataDtoList = scienceProcessService.listProInfo(new Query(proParamsMap));
        Map<Integer, String> proCodeMap = new HashMap<>();
        proDataDtoList.stream().forEach(p->{
            proCodeMap.put(p.getProId(), p.getProCode());
        });


        for (EnterpriseProjectInfoDo   enterpriseProjectInfoDo :enterpriTeamInfoList   ) {
            Map<String, Object> teamParams = new HashMap<>();
            teamParams.put("taskId", this.newParams.get("taskId"));
            teamParams.put("proId", enterpriseProjectInfoDo.getId());


            List<EnterpriseChengguoOtherInfoDO> otherInfoDOList = enterpriseChengguoOtherInfoService.list(teamParams);
            StringBuilder stringBuilder = new StringBuilder();

            for (EnterpriseChengguoOtherInfoDO   user :otherInfoDOList   ) {
                stringBuilder.append(user.getMainEnterpriseCompleter() +",");
            }
            enterpriseProjectInfoDo.setMainCompleteNames(stringBuilder.toString());
            enterpriseProjectInfoDo.setProCode(proCodeMap.get(enterpriseProjectInfoDo.getId()));

            enterpriseProjectInfoDo.initShowProName();
        }

        String[] title = {"编号", "项目编号", "项目类别", "申报单位", "专业", "申报日期", "成果", "状态",  "主要贡献人"};
        try {
            PoiWordUtils.downScienceExcel(title, enterpriTeamInfoList, response);
        } catch (Exception e) {
        }
        map.addAttribute("result", "下载成功");

        return "";
    }


    /**
     * 去编辑成果信息
     * 企业可以在一个任务下创建多个项目
     *
     * @return
     */
    @GetMapping("/toEditChengguo")
    @RequiresPermissions("system:enterpriseChengguoBaseInfo:editView")
    public String toUpateChengguoInfo(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        //获取项目成果相关信息
        List<EnterpriseChengguoBaseInfoDO> baseInfoDOList = enterpriseChengguoBaseInfoService.list(params);
        map.put("enterpriseChengguoBaseInfo", baseInfoDOList.size() > 0 ? baseInfoDOList.get(0) : null);
        return "act/award/chengguo/index";
    }


    /**
     * 科学形式审查的列表 科学技术奖成果审查 Formal review
     *
     * @param map
     * @return
     */
    @RequestMapping("/toReviewSciencePros")
    @RequiresPermissions("act:award:apply_pros")
    public String toListScienceFormalReview(@RequestParam Map<String, Object> params, ModelMap map) {
        map.put("apply_type", "science");
        packageAwardTaskId(map, params);
        List<PublishAwardTaskDo> taskDoList = awardFlowService.getAwardLastTasksByAwardType(20, EnumAwardType.SCIENCE.getAwrdType());
        map.put("taskList", taskDoList);
        return "act/award/formal/enterprise_formal_science_award_pro_list";
    }


    /**
     * 专业组管理 筛选专业
     *
     * @return
     */
    @RequiresPermissions("act:award:project_professional")
    @RequestMapping("/toAssignExperts")
    public String toManageProfession(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        List<String> groupList = awardEnterpriseProjectService.getProGroupList(params);

        List<EnterpriseProjectInfoDo> list = awardEnterpriseProjectService.getAllProList(params);
        boolean isAssign = true;

        map.put("isAssign", isAssign);
        params.put("roleId", "65");
        if (isAssign) {
            //如果是分派阶段则可查询进行分派
            List<UserDO> assWorkers = userService.list(params);
            map.put("assWorkers", assWorkers);
        }
        // 企业申请的数据
        map.put("assPros", list);

        map.put("profession", groupList);

        Map<String, Object> selParams = new HashMap<>();
        selParams.put("taskId", params.get("taskId"));
        selParams.put("groupName", params.get("major"));
        selParams.put("proType", params.get("proType"));
        List<ExpertGroupDO> selList = expertGroupService.list(selParams);
        map.put("selInfoList", selList);
        map.put("proType", params.get("proType"));
        return "act/award/association_profession_manage";
    }

    /**
     * 获取项目分组
     * @param params
     * @param map
     * @return
     */
    @RequiresPermissions("act:award:project_professional")
    @RequestMapping("/getAssignGroups")
    @ResponseBody
    public R getProGroupList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        List<EnterpriseProjectInfoDo> list = awardEnterpriseProjectService.getAllProList(params);
        String selProType = (String) params.get("proType");
        List<String> majoList = new ArrayList<>();
        for (EnterpriseProjectInfoDo workerUid : list) {
            String wMajor = workerUid.getProGroupName();
            String proType = workerUid.getProType();
            if (StringUtils.isNotBlank(wMajor) && proType.equals(selProType))
                if (!majoList.contains(wMajor)) {
                    majoList.add(wMajor);
                }
        }
        R result = R.ok();
        result.put("groupList", majoList);
        return result;
    }
    @RequiresPermissions("act:award:project_professional")
    @RequestMapping("/getAssignMajors")
    @ResponseBody
    public R getProMajorList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        List<EnterpriseProjectInfoDo> list = awardEnterpriseProjectService.getAllProList(params);
        String selProType = (String) params.get("proType");
        List<String> majoList = new ArrayList<>();
        for (EnterpriseProjectInfoDo workerUid : list) {
            String wMajor = workerUid.getMajor();
            String proType = workerUid.getProType();
            if (StringUtils.isNotBlank(wMajor) && proType.equals(selProType))
                if (!majoList.contains(wMajor)) {
                    majoList.add(wMajor);
                }
        }
        R result = R.ok();
        result.put("majorList", majoList);
        return result;
    }

    /**
     * 专业组管理 添加专家
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/expert/add")
    public R toAddExpert(ExpertGroupDO expertGroupDO) {
        String loginAccountObj = expertGroupDO.getLoginAccount();
        if(loginAccountObj != null) {
            String loginAccount = loginAccountObj.trim();
            expertGroupDO.setLoginAccount(loginAccount);
        }

        Integer id = expertGroupDO.getId();
        if(id == null) {
            Map<String, Object> params = new HashMap<>();
            params.put("loginAccount", expertGroupDO.getLoginAccount());
            params.put("taskId", expertGroupDO.getTaskId());
            params.put("proType", expertGroupDO.getProType());
            List<ExpertGroupDO> expertGroupDOList = expertGroupService.list(params);
            if(expertGroupDOList.size() > 0) {
                id = expertGroupDOList.get(0).getId();
                if(id > 0) {
                    expertGroupDO.setId(id);
                }
            }
        }
        int tag = 0;
        if(id != null) {
           tag = expertGroupService.update(expertGroupDO);
        }else {
           tag = expertGroupService.save(expertGroupDO);
        }
        if (tag > 0) {
            R r = R.ok();
            r.put("id", expertGroupDO.getId());
            return r;
        } else {
            if(tag == -100) {
                return R.error("修改的用户已存在,请删除后再操作");
            }
            return R.error("添加数据出错");
        }
    }

    /**
     * 专业组管理 添加专家
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/expert/remove")
    public R toRemoveExpert(String loginAccount) {
        if (StringUtils.isBlank(loginAccount)) {
            return R.error("账号为空，无法移除");
        }
        loginAccount = loginAccount.trim();
        Map<String,Object> params = new HashMap<>();
        params.put("loginAccount",loginAccount);
        int count = expertGroupService.count(params);
        if(count == 0) {
            return R.ok();
        }
        //逻辑删除
        int tag = expertGroupService.delByLoginAccount(loginAccount);
        if (tag > 0) {
            //逻辑删除
            userService.delUserByAccount(loginAccount);
            return R.ok();
        } else {
            return R.error("移除数据出错");
        }
    }

    /**
     * 上传分派的专家签名
     *
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/toUploadExpertSign")
    public String toUploadConfirmFilePage(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        Object loginAccountObj = params.get("loginAccount");
        if (loginAccountObj != null) {
            List<Long> list = userService.getUidByLoginUserName(loginAccountObj.toString());
            if (list.size() > 0) {
                map.put("expertUid", list.get(0));
            }
        }
        map.put("loginAccount", params.get("loginAccount"));
        map.put("trIndex", params.get("trIndex"));
        return "cpe/science/science_expert_sign_upload";
    }

     /**
     * 上传分派的专家签名
     *
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/toUploadHeadImg")
    public String toUploadHeadImgFilePage(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/science/personal_head_upload";
    }

    /**
     * 提交审核项目
     *
     * @param proId
     * @return
     */
    @RequestMapping("/subCheck")
    @ResponseBody
    public R subCheckPro(Integer proId) {
        if (proId != null && proId > 0 && awardEnterpriseProjectService.updateProCheck(proId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 撤回提交审核项目
     *
     * @param proId
     * @return
     */
    @RequestMapping("/cancelCheck")
    @ResponseBody
    @RequiresPermissions("system:enterpriseChengguoBaseInfo:cancelReview")
    public R cancelCheckPro(Integer proId) {
        if (proId != null && proId > 0 && awardEnterpriseProjectService.updateProApply(proId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 撤回提交审核项目
     *
     * @param proId
     * @return
     */
    @RequestMapping("/teamCancelCheck")
    @ResponseBody
    @RequiresPermissions("system:enterpriTeamInfo:cancelReview")
    public R teamCancelCheckPro(Integer proId) {
        return cancelCheckPro(proId);
    }

    /**
     * 撤回提交审核项目
     *
     * @param proId
     * @return
     */
    @RequestMapping("/personalCancelCheck")
    @ResponseBody
    @RequiresPermissions("system:enterpriPersonalInfo:cancelReview")
    public R personalCancelCheckPro(Integer proId) {
        return cancelCheckPro(proId);
    }

    /**
     * 下载项目中的文件列表
     */
    @RequestMapping("/downloadProDocFiles")
    @RequiresPermissions("system:scienceProcess:downloadProDocFiles")
    @ResponseBody
    public R downloadUpDocFiles(Integer proId) {
        if (proId == null) {
            return R.error("选择要下载的项目");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        List<String> fileUrlList = scienceProcessService.getUploadFileUrlList(proId);
        String uploadPath = bootdoConfig.getUploadPath();

        Set<String> filePathList = new HashSet<>();
        fileUrlList.stream().forEach(url -> {
            String filePath = uploadPath + "/" + url.replaceAll("/files/", "");
            filePathList.add(filePath);
        });

        //打包数据下发
        String curDate = DateUtils.getCurDate();
        String[] dateArr = curDate.split("-");
        String userFolderPath = dateArr[0] + "/" + dateArr[1] + "/u_" + getUserId() + "/zip";
        String storeZipFolder = uploadPath + userFolderPath;
        File zipFolder = new File(storeZipFolder);
        if (!zipFolder.exists()) {
            zipFolder.mkdirs();
        }

        String zipFileName = System.currentTimeMillis() + "_" + proId;

        List<String> fList = new ArrayList<>();
        filePathList.stream().forEach(f -> {
            fList.add(f);
        });
        try {
            ZipCompress.compress(storeZipFolder, zipFileName, fList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String zipUrl = "/files/" + userFolderPath + "/" + zipFileName + ".zip";
        R result = R.ok();
        result.put("zipUrl", zipUrl);
        return result;
    }

    @RequestMapping("/removePro")
    @ResponseBody
    @RequiresPermissions("system:enterpriseChengguoBaseInfo:remove")
    public R removeProject(int proId) {
        int rst = awardEnterpriseProjectService.remove(proId + "");
        if (rst == 0) {
            return R.error("删除失败");
        }
        return R.ok();
    }

}
