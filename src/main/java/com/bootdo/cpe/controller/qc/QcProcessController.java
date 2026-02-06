package com.bootdo.cpe.controller.qc;

import com.bootdo.activiti.domain.AssignProjectDataDo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.common.controller.BaseQcProController;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.domain.ExpertGroupDO;
import com.bootdo.cpe.domain.QcReviewResultRecordDO;
import com.bootdo.cpe.domain.science_process.ScienceAssignCountInfo;
import com.bootdo.cpe.domain.science_process.ScienceAssignExternalProInfo;
import com.bootdo.cpe.dto.QcProDataDto;
import com.bootdo.cpe.service.ExpertGroupService;
import com.bootdo.cpe.service.QcAwardService;
import com.bootdo.cpe.service.QcReviewResultRecordService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.bootdo.common.config.Constant.ROLE_QC_EXTERNAL_EMPLOYMENT_ID;

/**
 * QC奖任务
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-07 17:33
 */
@RequestMapping("/qcProcess")
@Controller
public class QcProcessController extends BaseQcProController {
    private String prefix = "cpe/qc";
    @Autowired
    private UserService userService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    private AwardPublishTaskService awardPublishTaskService;
    @Autowired
    private QcAwardService qcAwardService;
    @Autowired
    private QcReviewResultRecordService qcReviewResultRecordService;
    @Autowired
    private ExpertGroupService expertGroupService;
    /**
     * 去分配项目给工作人员
     *
     * @return
     */
    @RequiresPermissions("asso:task:assign")
    @RequestMapping("/toAssign")
    public String toAssignPro(@RequestParam Map<String, Object> params, ModelMap map) {
        long roleId = ROLE_QC_EXTERNAL_EMPLOYMENT_ID;
        packageAwardTaskId(map, params);
        params.put("roleId", roleId);

        ScienceAssignCountInfo countInfo = awardEnterpriseProjectService.getAssignCountInfo(params);
        int validateCount = countInfo.getValidateCount();
        if(validateCount == 0) {
            //已分派完
            map.put("tipMsg", "还没有人提交申请审核！可督促申请提交");
            return "enterprise/tip_msg";
        }
        String taskId = (String) params.get("taskId");
        PublishAwardTaskDo taskDo = awardPublishTaskService.get(taskId);
        taskDo.initStat();
        boolean isAssgin = taskDo.getIsAssign();
        if(!isAssgin) {
            //分派时间已截止
            map.put("tipMsg", "分外外聘人员时间已结束,如需调整联系协会任务发布人员");
            return "enterprise/tip_msg";
        }

        //如果是分派阶段则可查询进行分派
        Map<String, Object> param = new HashMap<>();
        param.put("roleId", roleId);
        List<UserDO> assWorkers = userService.list(param);
        map.put("assWorkers", assWorkers);
        long exUid = 0L;
        for(UserDO aw:assWorkers){
            List<Long> roleIds = aw.getRoleIds();
            if(roleIds.contains(roleId)) {
                map.put("firstWorkerName", aw.getName());
                exUid = aw.getUserId();
                map.put("extUserId", exUid);
                break;
            }
        }
        return prefix + "/check/qc_check_assignment";
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
        if (proId != null && proId > 0 && qcAwardService.updateProCheck(proId) > 0) {
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
    @RequiresPermissions("cpe:qcGroupApplyInfo:cancelReview")
    public R cancelCheckPro(Integer proId) {
        if (proId != null && proId > 0 && qcAwardService.updateProApply(proId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 选择分派外聘人员的项目信息
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/queryAssignPro")
    @ResponseBody
    public R queryAssignProList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        String asWorkerName = (String) params.get("asWorkerName");
        Map<String,Object> userParamMap = new HashMap<>();
        userParamMap.put("username", asWorkerName);
        List<UserDO> userList = userService.list(userParamMap);
        long finalExUid = userList.size() > 0 ? userList.get(0).getUserId() : 0;

        List<ScienceAssignExternalProInfo> list = awardEnterpriseProjectService.getAssignExtProList(params);
        List<ScienceAssignExternalProInfo> noAssignList = new ArrayList<>();
        List<ScienceAssignExternalProInfo> assignList = new ArrayList<>();
        AtomicInteger noAsAuto = new AtomicInteger(1);
        AtomicInteger asAuto = new AtomicInteger(1);
        list.stream().forEach(as->{
            long curExtUid = as.getExtUserId();
            if(curExtUid == 0) {
                as.setSerNum(noAsAuto.getAndIncrement());
                noAssignList.add(as);
            }else if(finalExUid == curExtUid){
                as.setSerNum(asAuto.getAndIncrement());
                assignList.add(as);
            }
        });

        Map<String,Object> result = new HashMap<>();
        result.put("noAssignList", noAssignList);
        result.put("assignList", assignList);
        return R.ok(result);
    }


    @RequestMapping("/assignPro")
    @ResponseBody
    public R assignPro(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        String taskId = (String) params.get("taskId");
        //{"publishTaskId":"359110170ab640269a846230a0ea98b2vno2WA","proIds":"2,6","assWorkers":"10,7","assWorker":"10"}
        long assignUid = getUserId();
        String asWorkerName = params.get("asWorkerName").toString();
        if (StringUtils.isBlank(asWorkerName)) {
            return R.error(1, "请选择工作人员");
        }
        String awardType = params.get("awardType").toString();
        String[] workerNameArr = asWorkerName.split(",");
        List<Long> workerUidList = userService.getUidsByLoginUserNames(workerNameArr);
        String proIds = params.get("proIds").toString();
        if (StringUtils.isBlank(proIds)) {
            awardEnterpriseProjectService.removeByExtUid(workerUidList, taskId, awardType);
            return R.error(2, "分派项目已清空");
        }
        String[] proIdArr = proIds.split(",");
        List<AssignProjectDataDo> assignProjectDataDoList = new ArrayList<>();
        for (long wuid : workerUidList) {
            for (String proId : proIdArr) {
                long pid = Long.parseLong(proId);
                AssignProjectDataDo assignData = new AssignProjectDataDo(assignUid, wuid, pid);
                assignProjectDataDoList.add(assignData);
            }
        }
        awardEnterpriseProjectService.removeByExtUid(workerUidList, taskId, awardType);
        awardEnterpriseProjectService.assignPro(assignProjectDataDoList);

        return R.ok();
    }

    /**
     * 形式审查
     * @return
     */
    @RequestMapping("/toReivew")
    @RequiresPermissions("cpe:qcGroupApplyInfo:review")
    public String toReviewPro(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        Map<String, Object> proInfoParams = new HashMap<>();
        proInfoParams.put("id", params.get("groupInfoId"));
        List<QcProDataDto> qcProDataDtoList = qcAwardService.listProInfo(proInfoParams);
        map.put("qcProData", qcProDataDtoList.size() > 0 ? qcProDataDtoList.get(0) : new QcProDataDto());

        //获取最近一次审核信息
        Map<String, Object> reviewParams = new HashMap<>();
        reviewParams.put("proId", params.get("proId"));
        reviewParams.put("sort", "id");
        reviewParams.put("order", " desc");
        reviewParams.put("offset", 0);
        reviewParams.put("limit", 1);
        List<QcReviewResultRecordDO> reviewResultRecordDOList = qcReviewResultRecordService.list(reviewParams);
        QcReviewResultRecordDO reviewResultRecordDO = reviewResultRecordDOList.size() > 0 ? reviewResultRecordDOList.get(0) : new QcReviewResultRecordDO();
        map.put("reviewResult", reviewResultRecordDO);
        return prefix + "/check/qc_check_template";
    }

    /**
     * 添加专家账号
     * @return
     */
    @RequestMapping("/toAddSpecialist")
    public String toAddSpecialistUser(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        //用于入库标记账号的奖项类型
        String proType = EnumProjectType.QC_PRO_GROUP.getProType();
        map.put("proType", proType);
        Map<String, Object> selParams = new HashMap<>();
        selParams.put("taskId", params.get("taskId"));
        selParams.put("groupName", params.get("major"));
        selParams.put("proType", proType);
        List<ExpertGroupDO> selList = expertGroupService.list(selParams);
        map.put("selInfoList", selList);

        return prefix + "/score/major_group_admin";
    }

}
