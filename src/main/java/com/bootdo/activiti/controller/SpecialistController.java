package com.bootdo.activiti.controller;

import com.bootdo.activiti.domain.*;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.activiti.service.SpecialistService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseScienceTechnologyController;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.controller.qc.QcProcessController;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.service.ExpertGroupService;
import com.bootdo.cpe.service.ScienceProcessService;
import com.bootdo.cpe.utils.EnumUtils;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.cpe.utils.PoiWordOilProUtils;
import com.bootdo.system.config.ConstantCommonData;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.EnterpriPersonalInfoService;
import com.bootdo.system.service.EnterpriTeamInfoService;
import com.bootdo.system.service.EnterpriseChengguoBaseInfoService;
import com.bootdo.system.service.UserService;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import static com.bootdo.common.config.Constant.ROLE_SPECIALIST_ID;

/**
 * 专家相关操作
 */
@Controller
@RequestMapping("/specialist")
public class SpecialistController extends BaseScienceTechnologyController {
    private Logger logger = LoggerFactory.getLogger(SpecialistController.class);
    String prefix = "act/award";
    @Autowired
    private UserService userService;
    @Autowired
    private AwardFlowService awardFlowService;
    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private AwardFlowController awardFlowController;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    private EnterpriPersonalInfoService enterpriPersonalInfoService;
    @Autowired
    private EnterpriseChengguoBaseInfoService chengguoBaseInfoService;
    @Autowired
    private EnterpriTeamInfoService enterpriTeamInfoService;
    @Autowired
    private ExpertGroupService expertGroupService;
    @Autowired
    private ScienceProcessService scienceProcessService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private AwardPublishTaskService awardPublishTaskService;
    @Autowired
    private QcProcessController qcProcessController;

    /**
     * 选择专家确定组长
     *
     * @param taskId
     * @param request
     * @return
     */
    @RequiresPermissions("act:specialist:select")
    @RequestMapping("/to_select")
    public String toSelSpecialistView(@RequestParam Map<String, Object> params, String taskId, ModelMap map, HttpServletRequest request) {
        List<PublishAwardTaskDo> awardTaskDoList = awardPublishTaskService.list(params);
        if(awardTaskDoList.size() > 0) {
           PublishAwardTaskDo awardTaskDo = awardTaskDoList.get(0);
           String awardId = awardTaskDo.getAwardId();
           if(String.valueOf(EnumAwardType.QC.getAwrdType()).equals(awardId)) {
               //QC奖
               return qcProcessController.toAddSpecialistUser(params, map);
           }
        }

        packageAwardTaskId(map, params);
        //获取专家列表
        params.put("roleId", ROLE_SPECIALIST_ID + "");
        List<UserDO> specialistList = userService.list(params);
        map.put("specialistList", specialistList);
        //获取发布的奖项任务的专业信息
        List<MajorInfo> majorInfoList = awardFlowService.getAwardTaskMajorInfosById(taskId);
        map.put("majorInfoList", majorInfoList);

        return prefix + "/association_select_specialist";
    }

    /**
     * 获取已经选择的专业信息
     *
     * @param publishTaskId
     * @return
     */
    @RequestMapping("/get_sel_specialist")
    @ResponseBody
    public List<SpecialistSelInfo> getSelSpecialistInfo(String publishTaskId) {
        //获取已经选择的专家信息
        Map<String, Object> selParams = new HashMap<>();
        selParams.put("publishAwardId", publishTaskId);
        List<SpecialistSelInfo> selInfoList = specialistService.list(selParams);
        return selInfoList;
    }


    /**
     * 协会联系检查分数
     *
     * @param map
     * @return
     */
    @RequestMapping("/associationViewScore")
    @RequiresPermissions("award_flow:specialist_score:check")
    public String expertCheckScore(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/score/expert_check_score";
    }


    /**
     * 确认选择专家及确认组长
     *
     * @param selInfo
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public R selectSpecialist(SpecialistSelInfo selInfo) {
        Long associationUid = getUserId();
        selInfo.setSelUid(associationUid == null ? associationUid : 0);
        int rst = specialistService.save(selInfo);
        if (rst == 0) {
            return R.error(1, "选择保存失败");
        }
        return R.ok();
    }

    /**
     * 专家去打分
     *
     * @param map
     * @return
     */
    @RequestMapping("/to_score_pros")
    @RequiresPermissions("award_flow:specialist_pros:specialist_pros")
    public String toScorePro(ModelMap map) {

        return prefix + "/score/specialist_to_score";
    }

    /**
     * 跳转到打分的项目列表
     *
     * @param scoreType 页面名称
     * @param map
     * @return
     */
    @RequestMapping("/toScoreProList")
    public String toScoreProList(String scoreType, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);

        Long userId = getUserId();
        //根据用户id获取当前分派的打分任务列表
        params.put("uid", userId);
        List<AssignProjectDataDo> list = awardEnterpriseProjectService.userAssignedProList(params);
        List<AssignProjectDataDo> personalList = new ArrayList<>();
        List<AssignProjectDataDo> scienceList = new ArrayList<>();
        List<AssignProjectDataDo> teamList = new ArrayList<>();
        list.stream().forEach(p -> {
            String proType = p.getProType();
            if ("team".equals(proType)) {
                teamList.add(p);
            } else if ("science".equals(proType)) {
                scienceList.add(p);
            } else if ("personal".equals(proType)) {
                personalList.add(p);
            }
        });

        map.put("personalProList", personalList);
        map.put("scienceProList", scienceList);
        map.put("teamProList", teamList);
        return prefix + "/score/pro_list_" + scoreType;
    }


    /**
     * 获取当前用户参与的项目信息
     *
     * @param map
     * @return
     */
    @GetMapping("/getCurLeaderPro")
    @ResponseBody
    public List<EnterpriseProjectInfoDo> getDataByScoreType(String proType, String major, String account, ModelMap map) {
        //根据用户id获取当前分派的打分任务列表
        Map<String, Object> params = new HashMap<>();
        params.put("proType", proType);
        if (StringUtils.isBlank(major)) {
            //默认指定一个不存在的专业
            major = "暂无NULL";
        }
        params.put("scoreMajor", major);
        String[] accArr = account.split("\\(");
        if (accArr.length > 1) {
            account = accArr[accArr.length - 1].replace(")", "");
        }

        params.put("scoreAccount", account);

        Map<String, Object> proParamsMap =new HashMap<String, Object>();
        proParamsMap.putAll(params);
        proParamsMap.remove("proSubType");
        proParamsMap.remove("keyWord");
        proParamsMap.remove("proStatStr");
        proParamsMap.remove("scoreMajor");
        List<EnterpriseProjectInfoDo> list = awardEnterpriseProjectService.list(params);
        return list;
    }

    /**
     * 获取团队列表
     *
     * @param map
     * @return
     */
    @GetMapping("/getTeamPros")
    @ResponseBody
    public List<SpecialistScoreProInfo> getTeamProList(@RequestParam Map<String, Object> params, ModelMap map) {
        //根据用户id获取当前分派的打分任务列表
        Map<String, Object> paramsQuery = new HashMap<>();
        paramsQuery.put("scoreAccount", params.get("account"));
        paramsQuery.put("groupName", params.get("major"));
        paramsQuery.put("taskId", params.get("taskId"));
        List<SpecialistScoreProInfo> scoreProInfoList = specialistService.getSpecialistScoreProList(paramsQuery);
        return scoreProInfoList;
    }

    @GetMapping("/getPersonalPros")
    @ResponseBody
    public List<SpecialistScoreProInfo> getPersonalProList(@RequestParam Map<String, Object> params, ModelMap map) {
                packageAwardTaskId(map, params);
        //根据用户id获取当前分派的打分任务列表
        Map<String, Object> paramsQuery = new HashMap<>();
        paramsQuery.put("scoreAccount", params.get("account"));
        paramsQuery.put("groupName", params.get("major"));
        paramsQuery.put("taskId", params.get("taskId"));
        List<SpecialistScoreProInfo> scoreProInfoList = specialistService.getSpecialistScoreProList(paramsQuery);
        return scoreProInfoList;
    }


    /**
     * 弹出个人打分列表
     */
    @RequestMapping("/toScoreProPersonalList")
    @RequiresPermissions("award_flow:specialist_pros:specialist_personal_list")
    public String toScoreProPersonalList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        String scoreType = EnumScienceScoreType.PERSONAL.getScoreType();
        map.put("scoreType", scoreType);
        int scoreIsOver = specialistService.getScoreIsOver(getUserId(), scoreType);
        map.put("scoreIsOver", scoreIsOver);
        return prefix + "/score/personal/pro_list_personal";
    }


    /**
     * 弹出个人打分列表
     */
    @ResponseBody
    @RequestMapping("/personal/list")
    @RequiresPermissions("award_flow:specialist_pros:specialist_personal_list")
    public PageUtils toScorePersonalList(@RequestParam Map<String, Object> params) {
        params.put("scoreSpecialistUid", getUserId());
        Query query = new Query(params);
        List<EnterpriPersonalInfoDO> list = enterpriPersonalInfoService.list(query);
        if(list.size() > 0) {
            PublishAwardTaskDo taskDo = awardPublishTaskService.getByProId(list.get(0).getProId() + "");
            if(taskDo != null) {
                list.stream().forEach(ec->{
                    ec.setProStatStr(taskDo.getTaskStatStr());
                });
            }
        }
        PageUtils pageUtils = new PageUtils(list, list.size());
        return pageUtils;
    }

    /**
     * 弹出团队打分列表
     */
    @RequestMapping("/toScoreProTeamList")
    @RequiresPermissions("award_flow:specialist_pros:specialist_team_list")
    public String toTeamList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        String scoreType = EnumScienceScoreType.TEAM.getScoreType();
        map.put("scoreType", scoreType);
        int scoreIsOver = specialistService.getScoreIsOver(getUserId(), scoreType);
        map.put("scoreIsOver", scoreIsOver);
        return prefix + "/score/team/pro_list_team";
    }


    /**
     * 弹出团队打分列表
     */
    @ResponseBody
    @RequestMapping("/team/list")
    @RequiresPermissions("award_flow:specialist_pros:specialist_team_list")
    public PageUtils toTeamProList(@RequestParam Map<String, Object> params) {
        params.put("scoreSpecialistUid", getUserId());
        Query query = new Query(params);
        List<EnterpriTeamInfoDO> teamInfoDOList = enterpriTeamInfoService.list(query);
        if(teamInfoDOList.size() > 0) {
            PublishAwardTaskDo taskDo = awardPublishTaskService.getByProId(teamInfoDOList.get(0).getProId() + "");
            if(taskDo != null) {
                teamInfoDOList.stream().forEach(ec->{
                    ec.setProStatStr(taskDo.getTaskStatStr());
                });
            }
        }
        PageUtils pageUtils = new PageUtils(teamInfoDOList, teamInfoDOList.size());

        return pageUtils;
    }


    /**
     * 弹出科技打分列表
     */
    @RequestMapping("/toScoreProScienceList")
    @RequiresPermissions("award_flow:specialist_pros:specialist_science_list")
    public String toScienceList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        String scoreType = EnumScienceScoreType.SCIENCE.getScoreType();
        map.put("scoreType", scoreType);
        int scoreIsOver = specialistService.getScoreIsOver(getUserId(), scoreType);
        map.put("scoreIsOver", scoreIsOver);
        return prefix + "/score/science/pro_list_science";
    }

    /**
     * 弹出团队打分列表
     */
    @ResponseBody
    @RequestMapping("/science/list")
    @RequiresPermissions("award_flow:specialist_pros:specialist_science_list")
    public PageUtils toScienceProList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        params.put("scoreSpecialistUid", getUserId());
        Query query = new Query(params);
        List<EnterpriseChengguoBaseInfoDO> list = chengguoBaseInfoService.list(query);
        if(list.size() > 0) {
            PublishAwardTaskDo taskDo = awardPublishTaskService.getByProId(list.get(0).getProId());
            if(taskDo != null) {
                list.stream().forEach(ec->{
                    ec.setProStatStr(taskDo.getTaskStatStr());
                });
            }
        }
        PageUtils pageUtils = new PageUtils(list, list.size());
        return pageUtils;
    }

    /**
     * 组长查看已打分的项目列表
     *
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/leaderViewScoreProList")
    @ResponseBody
    public PageUtils getGroupProList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        //TODO 查询组长应看到的项目列表
        return null;
    }

    /**
     * 弹出给个人打分的页面
     *
     * @param personId
     * @return
     */
    @RequestMapping("/toScoreProPersonal")
    public String toScoreProPersonal(String personId, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        map.put("itemId", personId);
        params.put("id", personId);

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("uid", getUserId());
        queryParams.put("proId", params.get("proId"));
        List<AwardScoreDetailInfo> scoreList = specialistService.getProScoreDetails(queryParams);
        double totalScore = 0;
        for (AwardScoreDetailInfo scoreInfo : scoreList) {
            if (StringUtils.isBlank(scoreInfo.getScoreTxt())) {
                map.put(scoreInfo.getScoreKey(), scoreInfo.getScoreVal());
            } else {
                map.put(scoreInfo.getScoreKey(), scoreInfo.getScoreTxt());
            }
            totalScore += scoreInfo.getScoreVal();
        }
        map.put("totalScore", totalScore);

        List<EnterpriPersonalInfoDO> list = enterpriPersonalInfoService.list(params);
        EnterpriPersonalInfoDO  personalInfoDO = list.size() > 0 ? list.get(0) : new EnterpriPersonalInfoDO();
        map.put("proInfo", personalInfoDO);
        map.put("itemId", personalInfoDO == null ? 0 : personalInfoDO.getId());

        //是否已提交结束打分
        int scoreOverFlg = specialistService.getSubmitScoreOverFlg(params);
        map.put("readonly", scoreOverFlg);

        return prefix + "/score/score_pro_personal";
    }

    /**
     * 弹出给科技进行打分
     *
     * @return
     */
    @RequestMapping("/toScoreProScience")
    public String toScoreProScience(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        //科技奖只有一个内容信息进行评审，因此子项为0
        params.put("uid", getUserId());

        List<AwardScoreDetailInfo> scoreList = specialistService.getProScoreDetails(params);
        double totalScore = 0;
        for (AwardScoreDetailInfo scoreInfo : scoreList) {
            if (StringUtils.isBlank(scoreInfo.getScoreTxt())) {
                map.put(scoreInfo.getScoreKey(), scoreInfo.getScoreVal());
            } else {
                map.put(scoreInfo.getScoreKey(), scoreInfo.getScoreTxt());
            }
            if(!"totalScore".equalsIgnoreCase(scoreInfo.getScoreKey())){
                totalScore += scoreInfo.getScoreVal();
            }
        }
        map.put("totalScore", totalScore);

        List<EnterpriseChengguoBaseInfoDO> list = chengguoBaseInfoService.list(params);
        EnterpriseChengguoBaseInfoDO baseInfoDO = list.size() > 0 ? list.get(0) : new EnterpriseChengguoBaseInfoDO();
        map.put("proInfo", baseInfoDO);
        map.put("itemId", baseInfoDO == null ? 0 : baseInfoDO.getId());

        //是否已提交结束打分
        int scoreOverFlg = specialistService.getSubmitScoreOverFlg(params);
        map.put("readonly", scoreOverFlg);

        return prefix + "/score/score_pro_science";
    }

    /**
     * 弹出给科技进行打分
     *
     * @param teamId
     * @return
     */
    @RequestMapping("/toScoreProTeam")
    public String toScoreProTeam(String teamId, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        map.put("teamId", teamId);
        params.put("id", teamId);

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("uid", getUserId());
        queryParams.put("proId", params.get("proId"));
        List<AwardScoreDetailInfo> scoreList = specialistService.getProScoreDetails(queryParams);
        double totalScore = 0;
        for (AwardScoreDetailInfo scoreInfo : scoreList) {
            if (StringUtils.isBlank(scoreInfo.getScoreTxt())) {
                map.put(scoreInfo.getScoreKey(), scoreInfo.getScoreVal());
            } else {
                map.put(scoreInfo.getScoreKey(), scoreInfo.getScoreTxt());
            }
            totalScore += scoreInfo.getScoreVal();
        }
        map.put("totalScore", totalScore);

        List<EnterpriTeamInfoDO> list = enterpriTeamInfoService.list(params);
        EnterpriTeamInfoDO teamInfoDO =list.size() > 0 ? list.get(0) : new EnterpriTeamInfoDO();
        map.put("proInfo", teamInfoDO);
        map.put("itemId", teamInfoDO == null ? 0 : teamInfoDO.getId());

        //是否已提交结束打分
        int scoreOverFlg = specialistService.getSubmitScoreOverFlg(params);
        map.put("readonly", scoreOverFlg);

        return prefix + "/score/score_pro_team";
    }

    /**
     * 去评审打分
     *
     * @param proId
     * @return
     */
    @RequiresPermissions("act:award:score")
    @RequestMapping("/to_score")
    public String toScore(String proId, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        String procInsId = projectInfoDo.getProcInsId();
        return awardFlowController.toValidateAssociation(proId, procInsId, Constant.PAGE_SOURCE_ENTERPRISE_VALIDATE_MENU, map, params);
    }

    /**
     * 查看评分规则并进行打分
     *
     * @param proId
     * @param map
     * @return
     */
    @RequiresPermissions("act:award:score")
    @RequestMapping("/score_rule")
    public String toScoreRule(String proId, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        Long uid = getUserId();
        params.put("uid", uid);
        ProjectScoreInfo scoreInfo = specialistService.getMineScoreProInfo(params);
        map.put("scoreInfo", scoreInfo == null ? new ProjectScoreInfo() : scoreInfo);
        List<AwardScoreDetailInfo> detailInfoList = specialistService.getProScoreDetails(params);
        for (AwardScoreDetailInfo detailInfo : detailInfoList) {
            map.put(detailInfo.getScoreKey(), detailInfo.getScoreVal());
            map.put(detailInfo.getScoreType(), true);
        }
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("proInfo", projectInfoDo);
        return prefix + "/specialist_score_rule";
    }

    /**
     * 打分保存
     *
     * @param scoreInfo
     * @return
     */
    @RequestMapping("/score")
    @ResponseBody
    public R score(ProjectScoreInfo scoreInfo, HttpServletRequest request) {
        Long scoreUid = getUserId();
        scoreInfo.setScoreUid(scoreUid);

        //处理分数信息
        scoreInfo.handleParams(request);
        scoreInfo.setScore(scoreInfo.getTotalScore());
        int rst = specialistService.scorePro(scoreInfo);
        if (rst == 0) {
            return R.error(1, "打分失败,请重试");
        }

        return R.ok();
    }

    /**
     * 专家评判分数结束
     *
     * @return
     */
    @RequestMapping("/scoreOver")
    @ResponseBody
    public R scoreOver(String scoreType) {
        Long scoreUid = getUserId();
        specialistService.scoreOver(scoreUid, scoreType);
        return R.ok();
    }

    /**
     * 专家评判分数取消
     *
     * @return
     */
    @RequestMapping("/scoreCancel")
    @ResponseBody
    public R scoreCancel(String scoreType) {
        Long scoreUid = getUserId();
        specialistService.scoreCancel(scoreUid, scoreType);
        return R.ok();
    }

    /**
     * 专业领导审核列表
     *
     * @return
     */
    @RequestMapping("/review_list")
    public String reviewProList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        map.put("role", "specialist_leader");
        map.put("act", "review");
        map.put("pageSource", Constant.PAGE_SOURCE_ENTERPRISE_VALIDATE_MENU);
        return prefix + "/specialist_leader_review";
    }

    /**
     * 奖项的分组信息
     *
     * @return
     */
    @RequestMapping("/awardGroupTree")
    @ResponseBody
    public String awardGroupTree(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        long uid = getUserId();
        params.put("uid", uid);
        //TODO 只查属于自己的专业
        List<TreeBootstrap> tree = specialistService.getSpecialistMajorAccountList(params);
        Gson gson = new Gson();
        return gson.toJson(tree);
    }

    /**
     * 专家领导审核项目分数
     *
     * @return
     */
    @RequestMapping("/review_score")
    public String toReviewProScore(String proId, String sType, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        if ("science".equals(sType)) {
            sType = "科技";
        } else if ("team".equals(sType)) {
            sType = "团队";
        } else if ("personal".equals(sType)) {
            sType = "个人";
        }
        map.put("sType", sType);
        EnterpriseProjectInfoDo proInfo = awardEnterpriseProjectService.get(proId);
        map.put("proInfo", proInfo);
        List<ProjectScoreInfo> scoreInfoList = specialistService.getProScoreList(proId);
        map.put("scoreSize", scoreInfoList.size());
        if (scoreInfoList.size() > 0) {
            map.put("scoreInfo", scoreInfoList.get(0));
            scoreInfoList.remove(0);
        }
        map.put("scoreInfoList", scoreInfoList);
        return prefix + "/specialist_leader_score_review";
    }

    @RequestMapping("/option_form")
    public String toScoreOptionForm(String proId, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        EnterpriseProjectInfoDo proInfo = awardEnterpriseProjectService.get(proId);
        map.put("proInfo", proInfo);
        return prefix + "/specialist_leader_score_option_form";
    }


    /***
     * 个人 评分标准
     */
    @RequestMapping("/standard/personal")
    public String openPersonalStandard(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);

        return prefix + "/score/personal/personal_standard";
    }


    /****
     * 企业评分标准
     */
    @RequestMapping("/standard/team")
    public String openTeamStandard(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/score/team/team_standard";
    }


    /**
     * 科技团队评分标准
     */
    @RequestMapping("/standard/science")
    public String openScienceStandard(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/score/science/science_standard";
    }

/////////////////汇总表/////////////////////////////////

    /**
     * 个人汇总分数
     */
    @RequestMapping("/personal/summery")
    public String scorePersona(String major, String account, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        params.put("proTypes", EnumProjectType.SCIENCE_PERSONAL.getProType() + "," + EnumProjectType.SCIENCE_TEAM.getProType());
        params.put("groupName", major);
        params.put("scoreAccount", account);
        List<ProjectScoreInfo> scoreInfoList = specialistService.listProScore(params);
        Set<String> scoreSpecialistNameSet = new HashSet<>();
        Map<String, ProjectScoreTotalInfo> totalInfoMap = new HashMap<>();
        for (ProjectScoreInfo s : scoreInfoList) {
            scoreSpecialistNameSet.add(s.getScoreUserName());
            String key = s.getProId() + "" + s.getEnterpriseName();
            ProjectScoreTotalInfo totalInfo = totalInfoMap.get(key);
            if (totalInfo == null) {
                totalInfo = new ProjectScoreTotalInfo();
                totalInfo.setEnterpriseName(s.getEnterpriseName());
                totalInfo.setCheckRes(s.getValidateResult());
                String proType = s.getProType();
                if(EnumProjectType.SCIENCE_TEAM.getProType().equals(proType)) {
                    totalInfo.setName(s.getTeamName());
                }else if(EnumProjectType.SCIENCE_PERSONAL.getProType().equals(proType)) {
                    totalInfo.setName(s.getPersonName());
                }
                totalInfoMap.put(key, totalInfo);
            }
            totalInfo.addScoreInfo(s);
        }
        map.put("major", major);
        packageTotalScoreInfo(totalInfoMap, map, scoreSpecialistNameSet);

        return prefix + "/score/personal/personal_scoring_summary";
    }

    @RequestMapping("/science/down/summery")
    public void scoreDownScience(String major, String account, @RequestParam Map<String, Object> params, ModelMap map,HttpServletResponse response) throws IOException {
        packageAwardTaskId(map, params);
        params.put("proType", EnumProjectType.SCIENCE_PROGRESS.getProType());
        params.put("groupName", major);
        params.put("scoreAccount", account);
        List<ProjectScoreInfo> scoreInfoList = specialistService.listProScore(params);
        Map<String, ProjectScoreTotalInfo> totalInfoMap = new HashMap<>();
        Set<String> scoreSpecialistNameSet = new HashSet<>();
        for (ProjectScoreInfo s : scoreInfoList) {
            scoreSpecialistNameSet.add(s.getScoreUserName());
            String key = s.getProId() + "-" + s.getEnterpriseName();
            ProjectScoreTotalInfo totalInfo = totalInfoMap.get(key);
            if (totalInfo == null) {
                totalInfo = new ProjectScoreTotalInfo();
                totalInfo.setEnterpriseName(s.getEnterpriseName());
                totalInfo.setName(s.getChengguo());
                totalInfo.setProCode(s.getProCode());
                totalInfoMap.put(key, totalInfo);
            }
            totalInfo.addScoreInfo(s);
        }
        map.put("major", major);

        map.put("specialistList", scoreSpecialistNameSet);
        List<ProjectScoreTotalInfo> totalInfoList = new ArrayList<>();
        for (ProjectScoreTotalInfo t : totalInfoMap.values()) {
            t.initScoreList(scoreSpecialistNameSet);
            totalInfoList.add(t);
        }
        map.put("specialistCount", scoreSpecialistNameSet.size() + 1);
        totalInfoList.sort((((o1, o2) -> (int) (o2.getAvgScore() * 100 - o1.getAvgScore() * 100))));
        map.put("toatlInfoList", totalInfoList);

        List<String> specialistNameList = scoreSpecialistNameSet.stream().sorted().collect(Collectors.toList());
        PoiWordOilProUtils.downScoreStatistics(major,major,specialistNameList, totalInfoList,response);
    }





    @RequestMapping("/personal/down/summery")
    public void scoreDownPersonal(String major, String account, @RequestParam Map<String, Object> params, ModelMap map,HttpServletResponse response) throws IOException {
        packageAwardTaskId(map, params);
        params.put("proTypes", EnumProjectType.SCIENCE_PERSONAL.getProType() + "," + EnumProjectType.SCIENCE_TEAM.getProType());

        params.put("groupName", major);
        params.put("scoreAccount", account);
        List<ProjectScoreInfo> scoreInfoList = specialistService.listProScore(params);
        Map<String, ProjectScoreTotalInfo> totalInfoMap = new HashMap<>();
        Set<String> scoreSpecialistNameSet = new HashSet<>();
        for (ProjectScoreInfo s : scoreInfoList) {
            scoreSpecialistNameSet.add(s.getScoreUserName());
            String key = s.getProId() + "-" + s.getEnterpriseName();
            ProjectScoreTotalInfo totalInfo = totalInfoMap.get(key);
            if (totalInfo == null) {
                totalInfo = new ProjectScoreTotalInfo();
                totalInfo.setEnterpriseName(s.getEnterpriseName());
                totalInfo.setName(s.getProName());
                totalInfo.setProCode(s.getProCode());
                totalInfoMap.put(key, totalInfo);
            }
            totalInfo.addScoreInfo(s);
        }
        map.put("major", major);

        map.put("specialistList", scoreSpecialistNameSet);
        List<ProjectScoreTotalInfo> totalInfoList = new ArrayList<>();
        for (ProjectScoreTotalInfo t : totalInfoMap.values()) {
            t.initScoreList(scoreSpecialistNameSet);
            totalInfoList.add(t);

        }
        map.put("specialistCount", scoreSpecialistNameSet.size() + 1);
        totalInfoList.sort((((o1, o2) -> (int) (o2.getAvgScore() * 100 - o1.getAvgScore() * 100))));
        map.put("toatlInfoList", totalInfoList);

        List<String> specialistNameList = scoreSpecialistNameSet.stream().sorted().collect(Collectors.toList());
        PoiWordOilProUtils.downScoreStatisticsPersonal(major,major,specialistNameList, totalInfoList,response);


    }



    /**
         * 科技汇总分数
         */
    @RequestMapping("/science/summery")
    public String scoreScience(String major, String account, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        params.put("proType", EnumProjectType.SCIENCE_PROGRESS.getProType());
        params.put("groupName", major);
        params.put("scoreAccount", account);
        List<ProjectScoreInfo> scoreInfoList = specialistService.listProScore(params);
        Set<String> scoreSpecialistNameSet = new HashSet<>();
        Map<String, ProjectScoreTotalInfo> totalInfoMap = new HashMap<>();
        for (ProjectScoreInfo s : scoreInfoList) {
            scoreSpecialistNameSet.add(s.getScoreUserName());
            String key = s.getProId() + "-" + s.getEnterpriseName();
            ProjectScoreTotalInfo totalInfo = totalInfoMap.get(key);
            if (totalInfo == null) {
                totalInfo = new ProjectScoreTotalInfo();
                totalInfo.setEnterpriseName(s.getEnterpriseName());
                totalInfo.setName(s.getChengguo());
                totalInfo.setProCode(s.getProCode());
                totalInfo.setCheckRes(s.getValidateResult());
                totalInfoMap.put(key, totalInfo);
            }
            totalInfo.addScoreInfo(s);
        }
        map.put("major", major);
        packageTotalScoreInfo(totalInfoMap, map, scoreSpecialistNameSet);
        return prefix + "/score/science/science_scoring_summary";
    }

    /**
     * 封装汇总打分的展示数据
     *
     * @param totalInfoMap
     * @param map
     * @param scoreSpecialistNameSet
     */
    private void packageTotalScoreInfo(Map<String, ProjectScoreTotalInfo> totalInfoMap, ModelMap map, Set<String> scoreSpecialistNameSet) {
        List<String> specialNameList = scoreSpecialistNameSet.stream().sorted().collect(Collectors.toList());
        map.put("specialistList", specialNameList);
        List<ProjectScoreTotalInfo> totalInfoList = new ArrayList<>();
        for (ProjectScoreTotalInfo t : totalInfoMap.values()) {
            t.initScoreList(scoreSpecialistNameSet);
            totalInfoList.add(t);
        }
        map.put("specialistCount", specialNameList.size() + 1);
        totalInfoList.sort((((o1, o2) -> (int) (o2.getAvgScore() * 100 - o1.getAvgScore() * 100))));
        map.put("toatlInfoList", totalInfoList);
    }

    /**
     * 团队汇总分数
     */
    @RequestMapping("/team/summery")
    public String scoreTeam(String major, String account, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        params.put("proType", EnumProjectType.SCIENCE_TEAM.getProType());
        params.put("groupName", major);
        params.put("scoreAccount", account);
        List<ProjectScoreInfo> scoreInfoList = specialistService.listProScore(params);
        Set<String> scoreSpecialistNameSet = new HashSet<>();
        Map<String, ProjectScoreTotalInfo> totalInfoMap = new HashMap<>();
        for (ProjectScoreInfo s : scoreInfoList) {
            scoreSpecialistNameSet.add(s.getScoreUserName());
            String key = s.getProId() + "" + s.getEnterpriseName();
            ProjectScoreTotalInfo totalInfo = totalInfoMap.get(key);
            if (totalInfo == null) {
                totalInfo = new ProjectScoreTotalInfo();
                totalInfo.setEnterpriseName(s.getEnterpriseName());
                totalInfo.setName(s.getTeamName());
                totalInfoMap.put(key, totalInfo);
            }
            totalInfo.addScoreInfo(s);
        }
        packageTotalScoreInfo(totalInfoMap, map, scoreSpecialistNameSet);

        return prefix + "/score/team/team_scoring_summary";
    }



    /*，查看打分表*/

    /**
     * 查看个人打分表
     */
    @RequestMapping("/personal/sheet")
    public String scoreSheetPersona(String scoreType, String major, String account, int proId, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        packageSpecialistScoreProList(scoreType, major, account, proId, map);

        return prefix + "/score/personal/group_personal_form";
    }

    /**
     * 查看企业打分表
     */
    @RequestMapping("/science/sheet")
    public String scoreSheetScience(String scoreType, String major, String account, int proId , @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        packageSpecialistScoreProList(scoreType, major, account, proId, map);

        return prefix + "/score/science/group_scicence_form";
    }


    /**
     * 查看团队打分表
     */
    @RequestMapping("/team/sheet")
    public String scoreSheetTeam(String scoreType, String major, int proId, String account, ModelMap map) {

        packageSpecialistScoreProList(scoreType, major, account, proId, map);

        return prefix + "/score/team/group_team_form";
    }

    /**
     * 封装专家某专业下的项目评分明细
     *
     * @param major
     * @param account
     * @param proId
     * @param map
     */
    public void packageSpecialistScoreProList(String scoreType, String major, String account, int proId, ModelMap map) {
        String projectType = EnumUtils.getProTypeByScoreType(scoreType);
        Map<String, Object> params = new HashMap<>();
        params.put("proType", projectType);
        params.put("scoreMajor", major);
        params.put("scoreAccount", account);
        params.put("scoreType", scoreType);
        params.put("proId", proId);
        List<SpecialistScoreDetailInfo> list = specialistService.getSpecialistScoreProDetailList(params);
        Map<String, List<SpecialistScoreDetailInfo>> scoreDetailMap = new HashMap<>();
        list.stream().forEach(s -> {
            String key = s.getProId() + "";
            List<SpecialistScoreDetailInfo> dList = scoreDetailMap.get(key);
            if (dList == null) {
                dList = new ArrayList<>();
                scoreDetailMap.put(key, dList);
            }
            dList.add(s);
        });

        List<SpecialistScoreProDetailInfo> detailInfoList = new ArrayList<>();
        for (String key : scoreDetailMap.keySet()) {
            List<SpecialistScoreDetailInfo> tmpList = scoreDetailMap.get(key);
            detailInfoList.add(new SpecialistScoreProDetailInfo(tmpList));
        }

        map.put("list", detailInfoList);
        map.put("signature", list.size() > 0 ? list.get(0).getSignature() : "");
        map.put("created", list.size() > 0 ? list.get(0).getCreated() : "");
        map.put("major", major);

    }


    /*查看评审意见表*/

    /**
     * 查看个人打分表
     */
    @RequestMapping("/personal/form")
    public String scoreFormPersona(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/score/personal/group_personal_form";
    }

    /**
     * 查看企业打分表
     */
    @RequestMapping("/science/form")
    public String scoreFormScience(int proId, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId + "");
        Map<String, Object> scoreParams = new HashMap<>();
        scoreParams.put("proId", proId);
        Object accountObj = params.get("account");
        if (accountObj == null) {
            accountObj = "-123aL暂无";
        } else {
            String[] accArr = accountObj.toString().split("\\(");
            if (accArr.length > 1) {
                accountObj = accArr[accArr.length - 1].replace(")", "");
            } else {
                accountObj = "-123aL暂无";
            }
        }
        scoreParams.put("account", accountObj.toString());
        scoreParams.put("isAllKey", true);
        List<AwardScoreDetailInfo> scoreDetailInfoList = specialistService.getProScoreDetails(scoreParams);
        Map<String, Object> scoreResultMap = new HashMap<>();
        for (AwardScoreDetailInfo detailInfo : scoreDetailInfoList) {
            String showTxt = StringUtils.isBlank(detailInfo.getScoreTxt()) ? detailInfo.getScoreVal() + "" : detailInfo.getScoreTxt();
            scoreResultMap.put(detailInfo.getScoreKey(), showTxt);
        }
        String signUrl = expertGroupService.getSignUrl(scoreParams);
        map.put("signature", signUrl);
        map.put("proInfo", projectInfoDo);
        map.put("scoreResultMap", scoreResultMap);
        map.put("major", params.get("major"));
        return prefix + "/score/science/group_scicence_form";
    }


    private String mAccount = "";
    private String currentImageUrl = "";
    private String createTime = "";


    /***
     * 下载专家评审打分表
     * @param map
     * @param params
     * @param response
     * @throws IOException
     */
    @RequestMapping("/down/expert_score")
    public void expertToPrintPage(ModelMap map, @RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {

        response.setHeader("content-type", "application/msword");
        response.setContentType("application/octet-stream");
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        File storeFile = null;
        try {
            outputStream = response.getOutputStream();
            Map<String, Object> paramsData = new HashMap<>();
            //根据用户id获取当前分派的打分任务列表
            String account = (String) params.get("account");
            Object proTypes = params.get("proTypes");
            Object taskId = params.get("taskId");
            if(proTypes == null) {
                //兼容处理
                proTypes = params.get("proType");
            }
            String groupName = (String) params.get("major");
            paramsData.put("groupName", groupName);
            paramsData.put("proTypes", proTypes);
            paramsData.put("scoreAccount", account);
            paramsData.put("taskId", taskId);
            List<EnterpriseProjectInfoDo> list = awardEnterpriseProjectService.list(paramsData);
            Map<String, Object> scoreParamsd  = new HashMap<>();
            scoreParamsd.put("scoreAccount", account);
            scoreParamsd.put("groupName", groupName);
            scoreParamsd.put("proTypes", proTypes);
            paramsData.put("taskId", taskId);
            List<ProjectScoreInfo> gsd = specialistService.listProScore(scoreParamsd);
            gsd.stream().forEach(p -> {
                createTime = p.getCreated();
            });

            List<GroupScoreItem> gs = new ArrayList<>();
            list.stream().forEach(p -> {

                GroupScoreItem scoreItem = new GroupScoreItem();
                scoreItem.setShowNum(p.getProCode());
                scoreItem.setChengguo(p.getShowProName());
                scoreItem.setGroupName(p.getProGroupName());

                Map<String, Object> scoreParams = new HashMap<>();
                scoreParams.put("account", account);
                scoreParams.put("isAllKey", true);
                scoreParams.put("proId", p.getId());
                List<AwardScoreDetailInfo> scoreDetailInfoList = specialistService.getProScoreDetails(scoreParams);

                Map<String, Object> scoreResultMap = new HashMap<>();
                for (AwardScoreDetailInfo detailInfo : scoreDetailInfoList) {
                    String showTxt = StringUtils.isBlank(detailInfo.getScoreTxt()) ? detailInfo.getScoreVal() + "" : detailInfo.getScoreTxt();
                    scoreResultMap.put(detailInfo.getScoreKey(), showTxt);
                }
                String signUrl = expertGroupService.getSignUrl(scoreParams);
                currentImageUrl = signUrl != null ? signUrl : currentImageUrl ;
                scoreItem.setTechnologyAdvancement((String) scoreResultMap.get("technology_advancement"));
                scoreItem.setDegreeInnovation((String) scoreResultMap.get("degree_innovation"));
                scoreItem.setDifficultyComplex((String) scoreResultMap.get("difficulty_complex"));
                scoreItem.setLoreProperty((String) scoreResultMap.get("lore_property"));
                scoreItem.setEconomicBenefit((String) scoreResultMap.get("economic_benefit"));
                scoreItem.setSocialBenefit((String) scoreResultMap.get("social_benefit"));
                scoreItem.setPromotionProspect((String) scoreResultMap.get("promotion_prospect"));
                scoreItem.setPreparationExpression((String) scoreResultMap.get("preparation_expression"));
                scoreItem.setTotal((String) scoreResultMap.get("total"));
                scoreItem.setCreate(p.getCreated());
                scoreItem.setLevel((String) scoreResultMap.get("opinion_level"));

                scoreItem.setSinImageUrl(signUrl);

                gs.add(scoreItem);
            });

            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.EXPERT_DOWNLOAD_SCORE_FORM_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.EXPERT_DOWNLOAD_SCORE_FORM_CLASS_PATH);
                templateFile = resource.getFile();
            }

            String fName = templateFile.getName();
            String[] fNameArr = fName.split("\\.");
            String tmpFileName = fNameArr[0] + "_" + System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(5) + "." + fNameArr[1];
            tmpFileName = URLEncoder.encode(tmpFileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + tmpFileName);
            String storePath = bootdoConfig.getUploadPath() + "/print_doc/" + DateUtils.getCurDate();
            storeFile = new File(storePath);
            if (!storeFile.exists()) {
                storeFile.mkdirs();
            }
            storePath = storePath + "/" + tmpFileName;
            storeFile = new File(storePath);
            FileUtils.copyFile(templateFile, storeFile);

            PoiWordOilProUtils.creatExpertPrint(templateFile.getAbsolutePath(), storePath, gs, bootdoConfig.getImgUrlPre() , currentImageUrl,createTime, groupName);
            InputStream inputStream = new FileInputStream(storeFile);
            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(inputStream);
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
            map.addAttribute("result", "下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (storeFile != null) {
                storeFile.deleteOnExit();
            }
        }
        //成功后返回成功信息
        map.addAttribute("result", "下载成功");
    }

    /***
     * 下载专家评审打分表--非科技
     * @param map
     * @param params
     * @param response
     * @throws IOException
     */
    @RequestMapping("/down/expert_score/other")
    public void expertToPrintPageOther(ModelMap map, @RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {

        response.setHeader("content-type", "application/msword");
        response.setContentType("application/octet-stream");
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        File storeFile = null;
        try {
            outputStream = response.getOutputStream();
            Map<String, Object> paramsData = new HashMap<>();
            //根据用户id获取当前分派的打分任务列表
            String account = (String) params.get("account");
            Object proTypes = params.get("proTypes");
            Object taskId = params.get("taskId");
            if(proTypes == null) {
                //兼容处理
                proTypes = params.get("proType");
            }
            String groupName = (String) params.get("major");
            paramsData.put("groupName", groupName);
            paramsData.put("proTypes", proTypes);
            paramsData.put("scoreAccount", account);
            paramsData.put("taskId", taskId);
            List<EnterpriseProjectInfoDo> list = awardEnterpriseProjectService.list(paramsData);
            Map<String, Object> scoreParamsd  = new HashMap<>();
            scoreParamsd.put("scoreAccount", account);
            scoreParamsd.put("groupName", groupName);
            scoreParamsd.put("proTypes", proTypes);
            paramsData.put("taskId", taskId);
            List<ProjectScoreInfo> gsd = specialistService.listProScore(scoreParamsd);
            gsd.stream().forEach(p -> {
                createTime = p.getCreated();
            });

            List<GroupScoreItem> gs = new ArrayList<>();
            list.stream().forEach(p -> {

                GroupScoreItem scoreItem = new GroupScoreItem();
                scoreItem.setShowNum(p.getProCode());
                scoreItem.setChengguo(p.getShowProName());
                scoreItem.setGroupName(p.getProGroupName());

                Map<String, Object> scoreParams = new HashMap<>();
                scoreParams.put("account", account);
                scoreParams.put("isAllKey", true);
                scoreParams.put("proId", p.getId());
                List<AwardScoreDetailInfo> scoreDetailInfoList = specialistService.getProScoreDetails(scoreParams);

                Map<String, Object> scoreResultMap = new HashMap<>();
                for (AwardScoreDetailInfo detailInfo : scoreDetailInfoList) {
                    String showTxt = StringUtils.isBlank(detailInfo.getScoreTxt()) ? detailInfo.getScoreVal() + "" : detailInfo.getScoreTxt();
                    scoreResultMap.put(detailInfo.getScoreKey(), showTxt);
                }
                String signUrl = expertGroupService.getSignUrl(scoreParams);
                currentImageUrl = signUrl != null ? signUrl : currentImageUrl ;
                scoreItem.setTechnologyCreate((String) scoreResultMap.get("technology_create"));
                scoreItem.setGainAwards((String) scoreResultMap.get("gain_awards"));
                scoreItem.setScienceContribution((String) scoreResultMap.get("science_contribution"));
                scoreItem.setIntelliRight((String) scoreResultMap.get("intelli_right"));
                scoreItem.setPubWork((String) scoreResultMap.get("pub_work"));
                scoreItem.setTotal((String) scoreResultMap.get("total"));
                scoreItem.setCreate(p.getCreated());
                scoreItem.setLevel((String) scoreResultMap.get("opinion_level"));
                scoreItem.setSinImageUrl(signUrl);
                gs.add(scoreItem);
            });

            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.EXPERT_DOWNLOAD_SCORE_PERSON_FORM_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.EXPERT_DOWNLOAD_SCORE_PERSON_FORM_CLASS_PATH);
                templateFile = resource.getFile();
            }

            String fName = templateFile.getName();
            String[] fNameArr = fName.split("\\.");
            String tmpFileName = fNameArr[0] + "_" + System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(5) + "." + fNameArr[1];
            tmpFileName = URLEncoder.encode(tmpFileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + tmpFileName);
            String storePath = bootdoConfig.getUploadPath() + "/print_doc/" + DateUtils.getCurDate();
            storeFile = new File(storePath);
            if (!storeFile.exists()) {
                storeFile.mkdirs();
            }
            storePath = storePath + "/" + tmpFileName;
            storeFile = new File(storePath);
            FileUtils.copyFile(templateFile, storeFile);

            PoiWordOilProUtils.creatExpertPersonalPrint(templateFile.getAbsolutePath(), storePath, gs, bootdoConfig.getImgUrlPre() , currentImageUrl,createTime, groupName);
            InputStream inputStream = new FileInputStream(storeFile);
            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(inputStream);
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
            map.addAttribute("result", "下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (storeFile != null) {
                storeFile.deleteOnExit();
            }
        }
        //成功后返回成功信息
        map.addAttribute("result", "下载成功");
    }


    /***
     * 下载专家评审意见表
     * @param map
     * @param params
     * @param response
     * @throws IOException
     */
    @RequestMapping("/expert_advise/toPrint")
    public void expertAdviseToPrintPage(ModelMap map, @RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {
        response.setHeader("content-type", "application/msword");
        response.setContentType("application/octet-stream");
        byte[] buff = new byte[1024];
//创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        File storeFile = null;
        try {
            outputStream = response.getOutputStream();
            String account = (String) params.get("account");
            String[] accArr = account.split("\\(");
            if (accArr.length > 1) {
                account = accArr[accArr.length - 1].replace(")", "");
            }
            Map<String, Object> scoreParams = new HashMap<>();
            scoreParams.put("scoreAccount", account);
            scoreParams.put("groupName", params.get("scoreMajor"));
            scoreParams.put("scoreType", params.get("scoreType"));
            scoreParams.put("proType", params.get("proType"));
            List<ProjectScoreInfo> gs = specialistService.listProScore(scoreParams);

            List<Object> projectScoreInfoList = new ArrayList<>();
            int allSize = gs.size();
            for (int a = 0; a < allSize; a++) {
                ProjectScoreInfo project = gs.get(a);
                ProjectScoreInfo projectScoreInfo = new ProjectScoreInfo();
                projectScoreInfo.setChengguo(project.getChengguo());
                projectScoreInfo.setEnterpriseName(project.getEnterpriseName());
                projectScoreInfo.setMajor(project.getProGroupName());
                projectScoreInfo.setOpinionLevel(project.getOpinionLevel());
                String created = project.getCreated();
                String[] createdArr = StringUtils.isNotBlank(created) ? created.split(" ") : new String[]{""};
                projectScoreInfo.setCreated(createdArr[0]);
                String optionText = project.getOpinionText();
                if(StringUtils.isNotBlank(optionText)) {
                    optionText = optionText.trim();
                }else {
                    optionText = "";
                }
                projectScoreInfo.setOpinionText(optionText);
                String signUrl = project.getSignUrl();
                if(StringUtils.isNotBlank(signUrl)) {
                    String imgPath = bootdoConfig.getImgUrlPre() + (signUrl.startsWith("/") ? signUrl : "/" + signUrl);
                    int lastIndex = imgPath.lastIndexOf("/");
                    imgPath = imgPath.substring(0, lastIndex) + "/" + URLEncoder.encode(imgPath.substring(lastIndex + 1), "utf-8");
                    projectScoreInfo.setImageObj(Pictures.ofUrl(imgPath, PictureType.JPEG).create());
                }
                projectScoreInfoList.add(projectScoreInfo);
            }



            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.EXPERT_ADVISE_DOWNLOAD_SCORE_FORM_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.EXPERT_ADVISE_DOWNLOAD_SCORE_FORM_CLASS_PATH);
                templateFile = resource.getFile();
            }

            String fName = templateFile.getName();
            String[] fNameArr = fName.split("\\.");
            String tmpFileName = fNameArr[0] + "_" + System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(5) + "." + fNameArr[1];
            tmpFileName = URLEncoder.encode(tmpFileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + tmpFileName);
            String storePath = bootdoConfig.getUploadPath() + "/print_doc/" + DateUtils.getCurDate();
            storeFile = new File(storePath);
            if (!storeFile.exists()) {
                storeFile.mkdirs();
            }
            storePath = storePath + "/" + tmpFileName;
            storeFile = new File(storePath);
            FileUtils.copyFile(templateFile, storeFile);


            PoiWordOilProUtils.creatExpertAdvisePrint(templateFile.getAbsolutePath(), storePath, projectScoreInfoList);


            InputStream inputStream = new FileInputStream(storeFile);
            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(inputStream);
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
            map.addAttribute("result", "下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (storeFile != null) {
                storeFile.deleteOnExit();
            }
        }
//成功后返回成功信息
        map.addAttribute("result", "下载成功");
    }


    /**
     * 查看团队打分表
     */
    @RequestMapping("/team/form")
    public String scoreFormTeam(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/score/team/group_team_form";
    }

    /**
     * 科技专家评审意见表
     *
     * @param proId
     * @param itemId
     * @return
     */
    @RequestMapping("/optionScienceTable")
    public String scoreScienceOption(int proId, int itemId, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId + "");
        map.put("proInfo", projectInfoDo);
        params.put("itemId", itemId);
        Object accountObj = params.get("account");
        params.put("scoreAccount", accountObj.toString());
        List<ProjectScoreInfo> projectScoreInfoList = specialistService.listProScore(params);
        map.put("proScoreInfo", projectScoreInfoList.size() > 0 ? projectScoreInfoList.get(0) : new ProjectScoreInfo());
        return prefix + "/score/science/expert_science_review_form";
    }

    /**
     * 团队专家评审意见表
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/optionTeamTable")
    public String scoreTeamOption(int itemId, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        EnterpriTeamInfoDO teamInfoDO = enterpriTeamInfoService.get(itemId);
        map.put("teamInfo", teamInfoDO);

        params.put("itemId", itemId);
        List<ProjectScoreInfo> projectScoreInfoList = specialistService.listProScore(params);
        map.put("proScoreInfo", projectScoreInfoList.size() > 0 ? projectScoreInfoList.get(0) : new ProjectScoreInfo());
        return prefix + "/score/team/expert_team_review_form";
    }

    /**
     * 个人专家评审意见表
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/optionPersonalTable")
    public String scorePersonalOption(int itemId, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        EnterpriPersonalInfoDO personalInfoDO = enterpriPersonalInfoService.get(itemId);
        map.put("psersonlInfo", personalInfoDO);

        params.put("itemId", itemId);
        List<ProjectScoreInfo> projectScoreInfoList = specialistService.listProScore(params);
        map.put("proScoreInfo", projectScoreInfoList.size() > 0 ? projectScoreInfoList.get(0) : new ProjectScoreInfo());
        return prefix + "/score/personal/expert_personal_review_form";
    }

}
