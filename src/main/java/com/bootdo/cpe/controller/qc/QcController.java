package com.bootdo.cpe.controller.qc;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseQcProController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.dao.QcGroupMemberDao;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.dto.QcProDataDto;
import com.bootdo.cpe.service.*;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.cpe.utils.PoiWordQCProUtils;
import com.bootdo.system.config.ConstantCommonData;
import com.bootdo.system.controller.EnterpriPersonalInfoController;
import com.bootdo.system.domain.*;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import static com.bootdo.common.config.Constant.*;

/**
 * qc奖
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-05 10:12
 */
@RequestMapping("/qcAward")
@Controller
public class QcController extends BaseQcProController {
    private Logger logger = LoggerFactory.getLogger(EnterpriPersonalInfoController.class);

    private String prefix = "cpe/qc";

    @Autowired
    private FileService sysFileService;
    @Autowired
    private QcAwardService qcAwardService;
    @Autowired
    private QcGroupApplyInfoService qcGroupApplyInfoService;
    @Autowired
    private QcReportSolveInfoService qcReportSolveInfoService;
    @Autowired
    private QcReportInnovateInfoService qcReportInnovateInfoService;
    @Autowired
    private QcAppraiseActiveScoreService qcAppraiseActiveScoreService;
    @Autowired
    private QcResultSolveScoreService qcResultSolveScoreService;
    @Autowired
    private QcResultInnovateScoreService qcResultInnovateScoreService;
    @Autowired
    private QcSurveyStatisticInfoService qcSurveyStatisticInfoService;
    @Autowired
    private FileService fileService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private DictService dictService;
    @Autowired
    private QcGroupMemberService qcGroupMemberService;
    @Autowired
    private QcReviewResultRecordService qcReviewResultRecordService;
    @Autowired
    private com.bootdo.activiti.service.AwardPublishTaskService awardPublishTaskService;

    private Map<String, Object> newParams;

    @RequiresPermissions("qcaward:to:prolist")
    @RequestMapping("/toProListMain")
    public String toProListMain(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/qc/qc_pro_list_main";
    }
    /**
     * qc奖项目列表
     * @return
     */
    @RequestMapping("/view/proList")
    public String toQcProjectList(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        boolean isApply = isTaskIsApply(map, params);
        map.put("isApply", isApply);
        UserDO user = getUser();
        List<Long> roleIdList = user.getRoleIds();
        boolean isEnterpriseUser = roleIdList.contains(ROLE_ENTERPRISE_QC_ID);
        boolean isAssociationLeader = roleIdList.contains(ROLE_ASSOCIATION_LEADER);
        boolean isAssociationContact = roleIdList.contains(ROLE_QC_ASSOCIATION_ID);
        map.put("isAssociationContact", isAssociationContact);
        map.put("isAssociationLeader", isAssociationLeader);
        map.put("isEnterpriseUser", isEnterpriseUser);
        boolean isReview = roleIdList.contains(ROLE_QC_ASSOCIATION_ID) ||
            roleIdList.contains(ROLE_QC_EXTERNAL_EMPLOYMENT_ID) ||
            roleIdList.contains(ROLE_SPECIALIST_ID) ||
            roleIdList.contains(ROLE_ASSOCIATION_LEADER);
        map.put("isReview", isReview);
        if(!roleIdList.contains(ROLE_ENTERPRISE_QC_ID)) {
            //非企业角色不显示申请按钮
            map.put("apply_type", null);
        }
        boolean canShowCancelReviewBtn = roleIdList.contains(ROLE_QC_ASSOCIATION_ID)
        || roleIdList.contains(ROLE_ASSOCIATION_LEADER)|| roleIdList.contains(ROLE_QC_EXTERNAL_EMPLOYMENT_ID);
        map.put("canShowCancelReviewBtn", canShowCancelReviewBtn);
        List<DictDO> projectTypes = dictService.listByType("projectType");
        List<DictDO> classifications = dictService.listByType("classification");
        map.put("projectTypes", projectTypes);
        map.put("classifications", classifications);
        // ===== 页面级阶段判定（用于左上角申报按钮、状态筛选显示）=====
        String taskStageCode = "WAIT_APPLY";
        String taskStageName = "等待申请";
        boolean canApplyBtn = false;      // 左上角“申报”按钮
        boolean showRejectStatus = false; // 状态筛选“已驳回”

        Object taskIdObj = params.get("taskId");
        if (taskIdObj != null) {
            com.bootdo.activiti.domain.PublishAwardTaskDo taskDo =
                    awardPublishTaskService.getProTaskByTaskId(taskIdObj.toString());
            if (taskDo != null) {
                String applyStart = taskDo.getApplyStartDate();
                String applyEnd = taskDo.getApplyEndDate();
                String checkStart = taskDo.getCheckStartTime();
                String checkEnd = taskDo.getCheckEndTime();

                boolean applyStarted = org.apache.commons.lang3.StringUtils.isNotBlank(applyStart)
                        && com.bootdo.common.utils.DateUtils.diffNow(applyStart) >= 0;
                boolean applyEnded = org.apache.commons.lang3.StringUtils.isNotBlank(applyEnd)
                        && com.bootdo.common.utils.DateUtils.diffNow(applyEnd) >= 0;
                boolean checkStarted = org.apache.commons.lang3.StringUtils.isNotBlank(checkStart)
                        && com.bootdo.common.utils.DateUtils.diffNow(checkStart) >= 0;
                boolean checkEnded = org.apache.commons.lang3.StringUtils.isNotBlank(checkEnd)
                        && com.bootdo.common.utils.DateUtils.diffNow(checkEnd) >= 0;

                if (!applyStarted && !checkStarted) {
                    taskStageCode = "WAIT_APPLY";
                    taskStageName = "等待申请";
                    canApplyBtn = false;
                } else if (applyStarted && !checkStarted && !applyEnded) {
                    taskStageCode = "APPLYING";
                    taskStageName = "申请中";
                    canApplyBtn = true;
                } else if (checkEnded) {
                    taskStageCode = "CHECK_END";
                    taskStageName = "形审结束";
                    canApplyBtn = false;
                } else if (checkStarted) {
                    taskStageCode = "CHECKING";
                    taskStageName = "形式审查";
                    // 仅“申报进行中 + 形审已开始”可申报
                    canApplyBtn = !applyEnded;
                } else if (applyStarted && applyEnded) {
                    taskStageCode = "CHECKING";
                    taskStageName = "形式审查";
                    canApplyBtn = false;
                }
            }
        }

        // “已驳回”状态在形审相关阶段展示
        showRejectStatus = "CHECKING".equals(taskStageCode) || "CHECK_END".equals(taskStageCode);

        map.put("taskStageCode", taskStageCode);
        map.put("taskStageName", taskStageName);
        map.put("canApplyBtn", canApplyBtn);
        map.put("showRejectStatus", showRejectStatus);

        return prefix + "/qc_pro_list";
    }
    @RequestMapping("/get/latestReviewComment")
    @ResponseBody
    public R getLatestReviewComment(Integer proId) {
        if (proId == null) {
            return R.error("参数错误");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        // 这里调用现有service，查 ass_qc_review_result_record 最新一条
        List<QcReviewResultRecordDO> list = qcReviewResultRecordService.list(params);
        QcReviewResultRecordDO latest = (list != null && !list.isEmpty()) ? list.get(0) : null;
//        QcReviewResultRecordDO latest = qcReviewResultRecordService.getLatestByProId(proId);
        Map<String, Object> data = new HashMap<>();
        if (latest != null) {
            data.put("latestReviewResult", latest.getReviewResult());
            data.put("latestReviewComment", latest.getOpinionDesc());
            data.put("latestReviewTime", DateUtils.format(latest.getCreated(), "yyyy-MM-dd HH:mm:ss"));
        }
        return R.ok().put("data", data);
    }
    /**
     * 获取项目列表
     * @return
     */
    @RequestMapping("/get/proList")
    @ResponseBody
    public PageUtils getQcProList(@RequestParam Map<String, Object> params, ModelMap map) {
        UserDO user = getUser();
        Long uid = getUserId();
        List<Long> roleIdList = user.getRoleIds();
        packageAwardTaskId(map, params);
        getProListParamsByRole(params);
        if (roleIdList.contains(ROLE_QC_ASSOCIATION_ID)) {
            //todo 临时使用协会联系人的用户id
            params.put("associationUserId", roleIdList.contains(ROLE_QC_OFFLINE_VIEW_ID) ? 101 : user.getUserId());
            System.out.println("协会联系人");
        } else if (roleIdList.contains(ROLE_ENTERPRISE_QC_ID)) {
            //企业用户查看自己创建项目
            params.put("enterpriseUid", uid);
            System.out.println("企业用户"+uid);
        } else {
            //分派给自己的项目
            params.put("ass_assign_uid", uid);
            System.out.println("分派项目"+uid);
        }
//        getProListParamsByRole(params);
        this.newParams = params;
        // 第3步修改：QC协会角色项目列表状态可见范围控制（后端强约束）
        if (roleIdList.contains(ROLE_ASSOCIATION_LEADER)) {
            // 协会领导：只看审核中
            params.put("proStatStr", "check");
        } else if (roleIdList.contains(ROLE_QC_ASSOCIATION_ID)) {
            // 协会联系人：未提交 + 审核中 + 已驳回
            // 说明：你们SQL里“未提交”通常用 apply 特殊值映射，请与现有 mapper 约定保持一致
            params.put("proStatStr", "apply,check,reject,improve_partake");
        }

        // 第0步修改：使用独立状态筛选参数 statusFilter，兼容旧 keyWord
        Object statusFilterObj = params.get("statusFilter");
        Object keyWordObj = params.get("keyWord");
        String proStatStr = buildProStatStrByStatusFilter(statusFilterObj, keyWordObj);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(proStatStr)) {
            params.put("proStatStr", proStatStr);
        }
        // params.put("proStatStr", "");
        // Object keyWordObj = params.get("keyWord");
        // if (keyWordObj != null) {
        //     List<String> proStatList = QcProStatEnum.getStatValByKey(keyWordObj.toString());
        //     if (proStatList.size() > 0) {
        //         String str = new String();
        //         for (String s : proStatList) {
        //             str +=  s + ",";
        //         }
        //         params.put("proStatStr", str.substring(0, str.length() - 1));
        //     }
        // }

        Query query = new Query(params);
        //TODO 为了下载使用
        this.newParams = params;

        List<QcProDataDto> proDataDtoList = qcAwardService.listProInfo(query);
        // 第0步修改：统一阶段码来源，列表行必须返回 taskStageCode
        String currentTaskId = params.get("taskId") == null ? null : params.get("taskId").toString();
        String unifiedTaskStageCode = resolveTaskStageCodeByTaskId(currentTaskId);
        if (proDataDtoList != null) {
            for (QcProDataDto dto : proDataDtoList) {
                // 行级阶段统一赋值，避免前端 pageTaskStageCode / row.taskStageCode 混用
                dto.setTaskStageCode(unifiedTaskStageCode);
            }
        }
        int total = qcAwardService.countProInfo(query);
        PageUtils pageUtils = new PageUtils(proDataDtoList, total);
        return pageUtils;
    }

    /**
     * 跳转到申报页面
     * @return
     */
    @RequestMapping("/view/apply")
    public String toQcApply(ModelMap map, @RequestParam Map<String, Object> params) {
        applyParams(map, params);
        return prefix + "/apply/qc_apply";
    }

    /**
     * 跳转到申报小组报表页面
     * @return
     */
    @RequestMapping("/view/applyGroupInfo")
    public String toQcApplyGroupInfo(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Object proIdObj = params.get("proId");
        QcGroupApplyInfoDO applyInfoDO = new QcGroupApplyInfoDO();
        if(proIdObj != null) {
            Map<String, Object> groupParamsMap = new HashMap<>();
            groupParamsMap.put("proId", proIdObj);
            List<QcGroupApplyInfoDO> groupApplyInfoDOList = qcGroupApplyInfoService.list(groupParamsMap);
            applyInfoDO = groupApplyInfoDOList.size() > 0 ? groupApplyInfoDOList.get(0) : applyInfoDO;
        }
        // 回显申报单位
        if(StringUtils.isBlank(applyInfoDO.getUnitName())){
            applyInfoDO.setUnitName(getUser().getName());
        }
        map.put("groupInfo", applyInfoDO);
        //todo: 增加字典值
        List<DictDO> projectTypes = dictService.listByType("projectType");
        List<DictDO> classifications = dictService.listByType("classification");
        map.put("projectTypes", projectTypes);
        map.put("classifications", classifications);
        return prefix + "/apply/qc_apply_group_info";
    }

    /**
     * 跳转到申报小组报告-问题解决型课题
     * @param map
     * @return
     */
    @RequestMapping("/view/applyReportSolve")
    public String toQcApplyReportSolve(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Object proIdObj = params.get("proId");
        QcReportSolveInfoDO reportSolveInfoDO = new QcReportSolveInfoDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcReportSolveInfoDO> list = qcReportSolveInfoService.list(repMap);
            reportSolveInfoDO = list.size() > 0 ? list.get(0) : reportSolveInfoDO;
        }
        map.put("report", reportSolveInfoDO);
        return prefix + "/apply/qc_apply_report_solve";
    }

    /**
     * 跳转到申报小组报告-创新型课题
     * @param map
     * @return
     */
    @RequestMapping("/view/applyReportInnovate")
    public String toQcApplyReportInnovate(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Object proIdObj = params.get("proId");
        QcReportInnovateInfoDO reportInnovateInfoDO = new QcReportInnovateInfoDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcReportInnovateInfoDO> list = qcReportInnovateInfoService.list(repMap);
            reportInnovateInfoDO = list.size() > 0 ? list.get(0) : reportInnovateInfoDO;
        }
        map.put("report", reportInnovateInfoDO);
        return prefix + "/apply/qc_apply_report_innovate";
    }

    /**
     * 跳转到小组现场活动评价表
     * @param map
     * @return
     */
    @RequestMapping("/view/applyScoreActive")
    public String toQcApplyScoreActive(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);

        Object proIdObj = params.get("proId");
        QcAppraiseActiveScoreDO activeScoreDO = new QcAppraiseActiveScoreDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcAppraiseActiveScoreDO> list = qcAppraiseActiveScoreService.list(repMap);
            activeScoreDO = list.size() > 0 ? list.get(0) : activeScoreDO;
        }
        map.put("score", activeScoreDO);

        return prefix + "/apply/qc_apply_active_score";
    }

    /**
     * 跳转到 问题解决型课题成果评价表
     * @param map
     * @return
     */
    @RequestMapping("/view/applyScoreResultSolve")
    public String toQcApplyScoreResultSolve(ModelMap map , @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);

        Object proIdObj = params.get("proId");
        QcResultSolveScoreDO resultSolveScoreDO = new QcResultSolveScoreDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcResultSolveScoreDO> list = qcResultSolveScoreService.list(repMap);
            resultSolveScoreDO = list.size() > 0 ? list.get(0) : resultSolveScoreDO;
        }
        map.put("score", resultSolveScoreDO);

        return prefix + "/apply/qc_apply_result_solve_score";
    }

    /**
     * 跳转到 创新型课题成果评价表
     * @param map
     * @return
     */
    @RequestMapping("/view/applyScoreResultInnovate")
    public String toQcApplyScoreResultInnovate(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);

        Object proIdObj = params.get("proId");
        QcResultInnovateScoreDO resultInnovateScoreDO = new QcResultInnovateScoreDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcResultInnovateScoreDO> list = qcResultInnovateScoreService.list(repMap);
            resultInnovateScoreDO = list.size() > 0 ? list.get(0) : resultInnovateScoreDO;
        }
        map.put("score", resultInnovateScoreDO);

        return prefix + "/apply/qc_apply_result_innovate_score";
    }

    /**
     * 跳转到 质量管理小组概况统计表
     * @param map
     * @return
     */
    @RequestMapping("/view/applySurveyStatistic")
    public String toQcApplySurveyStatistic(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);

        Object proIdObj = params.get("proId");
        QcSurveyStatisticInfoDO surveyStatisticInfoDO = new QcSurveyStatisticInfoDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcSurveyStatisticInfoDO> list = qcSurveyStatisticInfoService.list(repMap);
            surveyStatisticInfoDO = list.size() > 0 ? list.get(0) : surveyStatisticInfoDO;
        }
        map.put("score", surveyStatisticInfoDO);

        return prefix + "/apply/qc_apply_survey_statistic";
    }


    /**
     * 跳转到 人员管理页签
     */
    @RequestMapping("/view/applyMember")
    public String toQcApplyMenmber(ModelMap map, @RequestParam Map<String, Object> params) {

        packageAwardTaskId(map, params);

        String proId = (String) params.get("proId");
        System.out.println("当前 proId = " + proId);

        // 查询成员列表
        List<QcGroupMember> memberList = qcGroupMemberService.getByProid(proId);
        map.put("memberList", memberList);
        packageAwardTaskId(map, params);

        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listUploadEnterpriseDocs(params);
        map.put("docUploadDoList",docUploadDoList);
        map.put("fileSize",docUploadDoList.size());
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo == null ? new EnterpriseProjectInfoDo() : projectInfoDo);

        return prefix + "/apply/qc_apply_member";
    }

    /**
     * 成员列表接口
     */
    @RequestMapping("/member/list")
    @ResponseBody
    public List<QcGroupMember> getMemberList(String proId) {
        return qcGroupMemberService.getByProid(proId);
    }



    /**
     * 保存 / 更新成员
     */
    @RequestMapping("/member/save")
    @ResponseBody
//    @RequiresPermissions("cpe:qcGroupMember:add")
    public R saveMember(QcGroupMember member) {

        if (member == null) {
            return R.error("参数不能为空");
        }

        if (member.getProid() == null) {
            return R.error("缺少项目ID");
        }

        if (member.getIdCardNumber() == null) {
            return R.error("缺少身份证号");
        }

        // 根据 proid + 身份证 判断是否存在
        QcGroupMember exist = qcGroupMemberService.getOne(
                member.getProid(),
                member.getIdCardNumber()
        );

        if (exist == null) {
            qcGroupMemberService.save(member);
        } else {
            qcGroupMemberService.update(member);
        }

        return R.ok();
    }

    /**
     * 删除成员（按 proId + 身份证）
     */
    @RequestMapping("/member/remove")
    @ResponseBody
//    @RequiresPermissions("cpe:qcGroupMember:remove")
    public R removeMember(String proId, String idCardNumber) {

        if (proId == null || idCardNumber == null) {
            return R.error("缺少参数");
        }

        qcGroupMemberService.removeOne(proId, idCardNumber);
        return R.ok();
    }

    /**
     * 查询单个成员（编辑回显）
     */
    @RequestMapping("/member/get")
    @ResponseBody
    public QcGroupMember getMember(String proId, String idCardNumber) {

        if (proId == null || idCardNumber == null) {
            return null;
        }

        return qcGroupMemberService.getOne(proId, idCardNumber);
    }
    /**
     * 跳转成员编辑页（新增 / 编辑）
     */
    @RequestMapping("/memberEdit")
    public String toMemberEdit(ModelMap map,
                               @RequestParam(required = false) String proId,
                               @RequestParam(required = false) String idCardNumber) {

        map.put("proId", proId);
        map.put("idCardNumber", idCardNumber);

        return prefix + "/apply/qc_apply_member_edit";
    }




    /**
     * 跳转到 附件上传页签
     * @param map
     * @return
     */
    @RequestMapping("/view/applyAnnex")
    public String toQcApplyAnnex(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");
        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listUploadEnterpriseDocs(params);
        map.put("docUploadDoList",docUploadDoList);
        map.put("fileSize",docUploadDoList.size());
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo == null ? new EnterpriseProjectInfoDo() : projectInfoDo);
        return prefix + "/apply/qc_apply_annex";
    }



    @Autowired
    SequenceService sequenceService;
    /**
     * 保存申请的组信息
     * @param map
     * @param qcGroupApplyInfoDO
     * @return
     */
    @RequestMapping("/update/groupInfo")
    @ResponseBody
    @RequiresPermissions("cpe:qcGroupApplyInfo:add")
    public R updateGroupInfo(ModelMap map, QcGroupApplyInfoDO qcGroupApplyInfoDO) {
        Long uid = getUserId();
        qcGroupApplyInfoDO.setOptUid(uid.intValue());
        qcGroupApplyInfoDO.setDeleted(0);
        Date now = new Date();
        qcGroupApplyInfoDO.setUpdated(now);
        // 创建流水号 新增修改如果为空则创建qc号
        //qcGroupApplyInfoDO.setApplyId(sequenceService.generateSequence("QC"));
        Integer id = qcGroupApplyInfoDO.getId();
        int rst = 0;

        EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
        projectInfoDo.setId(qcGroupApplyInfoDO.getProId());
        projectInfoDo.setMajor(qcGroupApplyInfoDO.getProfessionalScope());
        awardEnterpriseProjectService.updateProjectInfo(projectInfoDo);
//        //新增修改如果为空则创建qc号
//        if(StringUtils.isBlank(qcGroupApplyInfoDO.getApplyId())) {
//            qcGroupApplyInfoDO.setApplyId(sequenceService.generateSequence("QC"));
//            qcGroupApplyInfoDO.setCreated(now);
//            rst = qcGroupApplyInfoService.save(qcGroupApplyInfoDO);
//            id = qcGroupApplyInfoDO.getId();
//        }else {
//            rst = qcGroupApplyInfoService.update(qcGroupApplyInfoDO);
//        }

        // 1) 先兜底：applyId 为空就生成
        if (StringUtils.isBlank(qcGroupApplyInfoDO.getApplyId())) {
            qcGroupApplyInfoDO.setApplyId(sequenceService.generateSequence("QC"));
        }

        // 2) 再按 id 判断新增/更新
        if (id == null) {
            qcGroupApplyInfoDO.setCreated(now);
            rst = qcGroupApplyInfoService.save(qcGroupApplyInfoDO);
            id = qcGroupApplyInfoDO.getId();
        } else {
            rst = qcGroupApplyInfoService.update(qcGroupApplyInfoDO);
        }
        R r = rst > 0 ? R.ok() : R.error("更新失败");
        r.put("id", id);
        r.put("applyId", qcGroupApplyInfoDO.getApplyId());
        return r;
    }

    @RequestMapping("/remove/groupInfo")
    @ResponseBody
    @RequiresPermissions("cpe:qcGroupApplyInfo:remove")
    public R removeGroupInfo(Integer id, Integer proId) {
        if(id != null) {
            qcGroupApplyInfoService.remove(id);
        }
        return R.ok();
    }

    @RequestMapping("/qc/printExcel")
    @ResponseBody
    public String printExcel(HttpServletResponse response, ModelMap map, @RequestParam Map<String, Object> params) {



        this.newParams.put("limit", "100000");
        Query query = new Query(this.newParams);

        List<QcProDataDto> proDataDtoList = qcAwardService.listProInfo(query);


        String[] title = {"序号", "成果编号", "类别", "课题名称", "小组名称", "单位名称",  "小组类型","专业范围", "申报账号","状态" };
        try {
            PoiWordUtils.downQCExcel(title, proDataDtoList, response);
        } catch (Exception e) {
        }
        map.addAttribute("result", "下载成功");

        return "";
    }

    @ResponseBody
    @PostMapping("/uploadQcAttachment")
    public R uploadQcAttachment(@RequestParam("file") MultipartFile file,@RequestParam("idCardNumber") String idCardNumber) { // type 可用于标识附件类别
        if (file.isEmpty()) {
            return R.error("上传文件不能为空");
        }

        try {
            // 1. 构建存储路径：年/月/u_用户id/
            long uid = getUserId(); // 获取当前QC用户ID
            String curDate = DateUtils.getCurDate(); // yyyy-MM-dd
            String[] dateArr = curDate.split("-");
            String userFolderPath = dateArr[0] + "/" + dateArr[1] + "/qc_" + uid + "/";

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
                fileName = originalFileName.substring(0, index)
                        + "_" + System.currentTimeMillis()
                        + RandomStringUtils.randomAlphanumeric(4)
                        + originalFileName.substring(index);
            }

            // 4. 保存物理文件到磁盘
            FileUtil.uploadFile(file.getBytes(), uploadPath, fileName);

            // 5. 生成访问 URL
            String fileUrl = "/files/" + userFolderPath + fileName;

            // 6. 保存到系统文件表 sys_file（保持一致性）
            FileDO sysFile = new FileDO(FileType.fileType(fileName), fileUrl, new Date());
            sysFileService.save(sysFile);

            // 7. 保存到 QC 用户附件表
            QcGroupMember qgm = new QcGroupMember();
            qgm.setIdCardNumber(idCardNumber);
            qgm.setCertificateNumber(fileUrl);
            qcGroupMemberService.update(qgm);

            // 8. 返回成功及文件 URL
            return R.ok().put("src", fileUrl);

        } catch (Exception e) {
            e.printStackTrace();
            return R.error("上传发生异常：" + e.getMessage());
        }
    }



    /**
     * 打印 记录
     * @return
     */
    @RequestMapping("/print/proinfo")
    public String printProInfo(String  id ,ModelMap map, @RequestParam Map<String, Object> params, HttpServletResponse response)  {
        packageAwardTaskId(map, params);
        Object proIdObj = id;
        //待下载文件名
        //设置为png格式的文件
        response.setHeader("content-type", "application/msword");
        response.setContentType("application/octet-stream");
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        File storeFile = null;
        try {
            outputStream = response.getOutputStream();

            QcGroupApplyInfoDO applyInfoDO = new QcGroupApplyInfoDO();
            if(proIdObj != null) {
                Map<String, Object> groupParamsMap = new HashMap<>();
                groupParamsMap.put("proId", proIdObj);
                List<QcGroupApplyInfoDO> groupApplyInfoDOList = qcGroupApplyInfoService.list(groupParamsMap);
                applyInfoDO = groupApplyInfoDOList.size() > 0 ? groupApplyInfoDOList.get(0) : applyInfoDO;
            }
            //  问题解决型
            QcReportSolveInfoDO reportSolveInfoDO = new QcReportSolveInfoDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcReportSolveInfoDO> list = qcReportSolveInfoService.list(repMap);
                reportSolveInfoDO = list.size() > 0 ? list.get(0) : reportSolveInfoDO;
            }
            // 创新型课题
            QcReportInnovateInfoDO reportInnovateInfoDO = new QcReportInnovateInfoDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcReportInnovateInfoDO> list = qcReportInnovateInfoService.list(repMap);
                reportInnovateInfoDO = list.size() > 0 ? list.get(0) : reportInnovateInfoDO;
            }
            // 活动现场评价
            QcAppraiseActiveScoreDO activeScoreDO = new QcAppraiseActiveScoreDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcAppraiseActiveScoreDO> list = qcAppraiseActiveScoreService.list(repMap);
                activeScoreDO = list.size() > 0 ? list.get(0) : activeScoreDO;
            }
            //解决成果评价
            QcResultSolveScoreDO resultSolveScoreDO = new QcResultSolveScoreDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcResultSolveScoreDO> list = qcResultSolveScoreService.list(repMap);
                resultSolveScoreDO = list.size() > 0 ? list.get(0) : resultSolveScoreDO;
            }
            // 创新型成果评价
            QcResultInnovateScoreDO resultInnovateScoreDO = new QcResultInnovateScoreDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcResultInnovateScoreDO> list = qcResultInnovateScoreService.list(repMap);
                resultInnovateScoreDO = list.size() > 0 ? list.get(0) : resultInnovateScoreDO;
            }
            // 质量管理小组概况统计表
            QcSurveyStatisticInfoDO surveyStatisticInfoDO = new QcSurveyStatisticInfoDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcSurveyStatisticInfoDO> list = qcSurveyStatisticInfoService.list(repMap);
                surveyStatisticInfoDO = list.size() > 0 ? list.get(0) : surveyStatisticInfoDO;
            }

            QCDocBaseDataWord word = new QCDocBaseDataWord();
            word.setApplyInfoDO(applyInfoDO);
            word.setActiveScoreDO(activeScoreDO);
            word.setReportInnovateInfoDO(reportInnovateInfoDO);
            word.setResultInnovateScoreDO(resultInnovateScoreDO);
            word.setSurveyStatisticInfoDO(surveyStatisticInfoDO);
            word.setReportSolveInfoDO(reportSolveInfoDO);
            word.setResultSolveScoreDO(resultSolveScoreDO);





            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
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

            PoiWordQCProUtils.createProQCWord(templateFile.getAbsolutePath(), storePath, word);

//            PoiWordUtils.createScicenceWord(templateFile.getAbsolutePath(),
//                    storePath,
//                    word,list,otherInfoDO);

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
        return "";

//
//        //待下载文件名
//
//          BufferedInputStream bis = null;
//
        //  优质申报表

//
//        OutputStream outputStream = null;
//        File storeFile = null;
//            try {
//                  outputStream = response.getOutputStream();
//                File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
//                if (templateFile == null) {
//                    Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
//                    templateFile = resource.getFile();
//                }
//
//
//                String fName = templateFile.getName();
//                String[] fNameArr = fName.split("\\.");
//                String tmpFileName = fNameArr[0] + "_" + System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(5) + "." + fNameArr[1];
//                tmpFileName = URLEncoder.encode(tmpFileName, "UTF-8");
//                response.setHeader("Content-Disposition", "attachment; filename=" + tmpFileName);
//                String storePath = bootdoConfig.getUploadPath() + "/print_doc/" + DateUtils.getCurDate();
//                storeFile = new File(storePath);
//                if (!storeFile.exists()) {
//                    storeFile.mkdirs();
//                }
//                storePath = storePath + "/" + tmpFileName;
//                storeFile = new File(storePath);
//                FileUtils.copyFile(templateFile, storeFile);
//
//
//                PoiWordQCProUtils.createProQCWord(templateFile.getAbsolutePath(), storePath, word);
//
//                InputStream inputStream = new FileInputStream(storeFile);
//                //这个路径为待下载文件的路径
//                  bis = new BufferedInputStream(inputStream);
//                byte[] buff = new byte[1024];
//                int len;
//                //通过while循环写入到指定了的文件夹中
//                while ((len = bis.read(buff)) != -1) {
//                    outputStream.write(buff, 0, len);
//                    outputStream.flush();
////                    read = bis.read(buff);
//                }
//
//            }catch (Exception ex){
//                ex.printStackTrace();
//                //出现异常返回给页面失败的信息
//                map.addAttribute("result", "下载失败");
//            }finally {
//                if (bis != null) {
//                    try {
//                        bis.close();
//                    } catch (IOException e) {
//                        logger.error("close err {}", e.getMessage());
//                    }
//                }
//
//                if (outputStream != null) {
//                    try {
//                        outputStream.flush();
//                        outputStream.close();
//                    } catch (IOException e) {
//                        logger.error("close out err {}", e.getMessage());
//                    }
//                }
////                if (storeFile != null) {
////                    storeFile.deleteOnExit();
////                }
//            }
//
//        map.addAttribute("result", "下载成功");
//        return "enterprise/apply/print";
    }


//    @Autowired
//    private ScienceProcessService scienceProcessService;
    @PostMapping("/downloadProDocFiles")
    //@RequiresPermissions("system:scienceProcess:downloadProDocFiles")
    @ResponseBody
    public R downloadUpDocFiles(Integer proId) {
        if (proId == null) {
            return R.error("选择要下载的项目");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listUploadEnterpriseDocs(params);
        String uploadPath = bootdoConfig.getUploadPath();

        Set<String> filePathList = new HashSet<>();
        docUploadDoList.stream().forEach(url -> {
            String filePath = uploadPath + "/" + url.getUrl().replaceAll("/files/", "");
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


    /**
     * 下载
     * @return
     */
    @RequestMapping("/download/proinfo")
    public void  downloadProInfo(int id,ModelMap map,  @RequestParam Map<String, Object> params, HttpServletResponse response)throws  Exception {
        packageAwardTaskId(map, params);
        Object proIdObj = id;
        //待下载文件名
        //设置为png格式的文件
        response.setHeader("content-type", "application/msword");
        response.setContentType("application/octet-stream");


        File storeFile = null;

        //  优质申报表
        QcGroupApplyInfoDO applyInfoDO = new QcGroupApplyInfoDO();
        if(proIdObj != null) {
            Map<String, Object> groupParamsMap = new HashMap<>();
            groupParamsMap.put("proId", proIdObj);
            List<QcGroupApplyInfoDO> groupApplyInfoDOList = qcGroupApplyInfoService.list(groupParamsMap);
            applyInfoDO = groupApplyInfoDOList.size() > 0 ? groupApplyInfoDOList.get(0) : applyInfoDO;
        }
        //  问题解决型
        QcReportSolveInfoDO reportSolveInfoDO = new QcReportSolveInfoDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcReportSolveInfoDO> list = qcReportSolveInfoService.list(repMap);
            reportSolveInfoDO = list.size() > 0 ? list.get(0) : reportSolveInfoDO;
        }
        // 创新型课题
        QcReportInnovateInfoDO reportInnovateInfoDO = new QcReportInnovateInfoDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcReportInnovateInfoDO> list = qcReportInnovateInfoService.list(repMap);
            reportInnovateInfoDO = list.size() > 0 ? list.get(0) : reportInnovateInfoDO;
        }
        // 活动现场评价
        QcAppraiseActiveScoreDO activeScoreDO = new QcAppraiseActiveScoreDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcAppraiseActiveScoreDO> list = qcAppraiseActiveScoreService.list(repMap);
            activeScoreDO = list.size() > 0 ? list.get(0) : activeScoreDO;
        }
        //解决成果评价
        QcResultSolveScoreDO resultSolveScoreDO = new QcResultSolveScoreDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcResultSolveScoreDO> list = qcResultSolveScoreService.list(repMap);
            resultSolveScoreDO = list.size() > 0 ? list.get(0) : resultSolveScoreDO;
        }
        // 创新型成果评价
        QcResultInnovateScoreDO resultInnovateScoreDO = new QcResultInnovateScoreDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcResultInnovateScoreDO> list = qcResultInnovateScoreService.list(repMap);
            resultInnovateScoreDO = list.size() > 0 ? list.get(0) : resultInnovateScoreDO;
        }
        // 质量管理小组概况统计表
        QcSurveyStatisticInfoDO surveyStatisticInfoDO = new QcSurveyStatisticInfoDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcSurveyStatisticInfoDO> list = qcSurveyStatisticInfoService.list(repMap);
            surveyStatisticInfoDO = list.size() > 0 ? list.get(0) : surveyStatisticInfoDO;
        }

        QCDocBaseDataWord word = new QCDocBaseDataWord();
        word.setApplyInfoDO(applyInfoDO);
        word.setActiveScoreDO(activeScoreDO);
        word.setReportInnovateInfoDO(reportInnovateInfoDO);
        word.setResultInnovateScoreDO(resultInnovateScoreDO);
        word.setSurveyStatisticInfoDO(surveyStatisticInfoDO);
        word.setReportSolveInfoDO(reportSolveInfoDO);
        word.setResultSolveScoreDO(resultSolveScoreDO);

        File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
        if (templateFile == null) {
            Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
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
        PoiWordQCProUtils.createProQCWord(templateFile.getAbsolutePath(), storePath, word);
        InputStream inputStream = new FileInputStream(storeFile);
        //这个路径为待下载文件的路径
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] buff = new byte[1024];
        int read = bis.read(buff);
        OutputStream outputStream = response.getOutputStream();
        //通过while循环写入到指定了的文件夹中
        while (read != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            read = bis.read(buff);
        }
        bis.close();
    }




//    /**
//     * 下载项目中的文件列表
//     */
//    @RequestMapping("/downloadProDocFiles")
//    @RequiresPermissions("system:scienceProcess:downloadProDocFiles")
//    @ResponseBody
//    public R downloadUpDocFiles(Integer proId) {
//        if (proId == null) {
//            return R.error("选择要下载的项目");
//        }
//        Map<String, Object> params = new HashMap<>();
//        params.put("proId", proId);
//        List<String> fileUrlList = scienceProcessService.getUploadFileUrlList(proId);
//        String uploadPath = bootdoConfig.getUploadPath();
//
//        Set<String> filePathList = new HashSet<>();
//        fileUrlList.stream().forEach(url -> {
//            String filePath = uploadPath + "/" + url.replaceAll("/files/", "");
//            filePathList.add(filePath);
//        });
//
//        //打包数据下发
//        String curDate = DateUtils.getCurDate();
//        String[] dateArr = curDate.split("-");
//        String userFolderPath = dateArr[0] + "/" + dateArr[1] + "/u_" + getUserId() + "/zip";
//        String storeZipFolder = uploadPath + userFolderPath;
//        File zipFolder = new File(storeZipFolder);
//        if (!zipFolder.exists()) {
//            zipFolder.mkdirs();
//        }
//
//        String zipFileName = System.currentTimeMillis() + "_" + proId;
//
//        List<String> fList = new ArrayList<>();
//        filePathList.stream().forEach(f -> {
//            fList.add(f);
//        });
//        try {
//            ZipCompress.compress(storeZipFolder, zipFileName, fList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String zipUrl = "/files/" + userFolderPath + "/" + zipFileName + ".zip";
//        R result = R.ok();
//        result.put("zipUrl", zipUrl);
//        return result;
//    }

    /**
     * 保存成果编号
     * @param proId
     * @param resultCode
     * @return
     */
    @RequestMapping("/saveCode")
    @ResponseBody
    @RequiresPermissions("cpe:qcGroupApplyInfo:saveCode")
    public R updateProResultCode(int proId, String resultCode) {
        if(StringUtils.isBlank(resultCode)) {
            return R.error("编号不能为空");
        }
        int rst = qcAwardService.updateProResultCode(proId, resultCode);
        return  rst > 0 ? R.ok("保存成功") : R.error("保存失败");
    }


    @ResponseBody
    @PostMapping("/member/importExcel")
    public R importExcel(@RequestParam("file") MultipartFile file,
                         @RequestParam("proId") String proId) {
        if (file.isEmpty()) {
            return R.error("Excel 文件不能为空");
        }

        try {
            int success = qcGroupMemberService.importFromExcel(file, proId);
            return R.ok().put("success", success).put("fail", 0);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("导入失败：" + e.getMessage());
        }
    }

        /**
     * 统一计算任务阶段（第0步：统一契约）
     * 返回值：WAIT_APPLY / APPLYING / CHECKING / CHECK_END
     */
    private String resolveTaskStageCodeByTaskId(String taskId) {
        String taskStageCode = "WAIT_APPLY";
        if (org.apache.commons.lang3.StringUtils.isBlank(taskId)) {
            return taskStageCode;
        }

        com.bootdo.activiti.domain.PublishAwardTaskDo taskDo =
                awardPublishTaskService.getProTaskByTaskId(taskId);
        if (taskDo == null) {
            return taskStageCode;
        }

        String applyStart = taskDo.getApplyStartDate();
        String applyEnd = taskDo.getApplyEndDate();
        String checkStart = taskDo.getCheckStartTime();
        String checkEnd = taskDo.getCheckEndTime();

        boolean applyStarted = org.apache.commons.lang3.StringUtils.isNotBlank(applyStart)
                && com.bootdo.common.utils.DateUtils.diffNow(applyStart) >= 0;
        boolean applyEnded = org.apache.commons.lang3.StringUtils.isNotBlank(applyEnd)
                && com.bootdo.common.utils.DateUtils.diffNow(applyEnd) >= 0;
        boolean checkStarted = org.apache.commons.lang3.StringUtils.isNotBlank(checkStart)
                && com.bootdo.common.utils.DateUtils.diffNow(checkStart) >= 0;
        boolean checkEnded = org.apache.commons.lang3.StringUtils.isNotBlank(checkEnd)
                && com.bootdo.common.utils.DateUtils.diffNow(checkEnd) >= 0;

        if (!applyStarted && !checkStarted) {
            taskStageCode = "WAIT_APPLY";
        } else if (applyStarted && !checkStarted && !applyEnded) {
            taskStageCode = "APPLYING";
        } else if (checkEnded) {
            taskStageCode = "CHECK_END";
        } else if (checkStarted) {
            // 允许申报与形审重叠：只要形审开始且未结束，统一 CHECKING
            taskStageCode = "CHECKING";
        } else if (applyStarted && applyEnded) {
            // 申报结束但形审未显式开始时，按你现有业务仍归入形审阶段
            taskStageCode = "CHECKING";
        }

        return taskStageCode;
    }

    /**
     * 第0步：状态筛选参数标准化
     * 优先使用 statusFilter（新参数），兼容旧 keyWord（老参数）
     */
    private String buildProStatStrByStatusFilter(Object statusFilterObj, Object keyWordObj) {
        List<String> proStatList = new ArrayList<>();

        // 1) 新参数优先
        if (statusFilterObj != null && org.apache.commons.lang3.StringUtils.isNotBlank(statusFilterObj.toString())) {
            proStatList = QcProStatEnum.getStatValByKey(statusFilterObj.toString());
        }
        // 2) 兼容旧参数：仅当新参数为空时回退
        else if (keyWordObj != null && org.apache.commons.lang3.StringUtils.isNotBlank(keyWordObj.toString())) {
            proStatList = QcProStatEnum.getStatValByKey(keyWordObj.toString());
        }

        if (proStatList == null || proStatList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : proStatList) {
            sb.append(s).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }


}
