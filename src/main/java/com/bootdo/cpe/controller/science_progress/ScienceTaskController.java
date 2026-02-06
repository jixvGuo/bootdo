package com.bootdo.cpe.controller.science_progress;

import com.bootdo.activiti.domain.AssignProjectDataDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.common.controller.BaseScienceTechnologyController;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.science_process.ScienceAssignCountInfo;
import com.bootdo.cpe.domain.science_process.ScienceAssignExternalProInfo;
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

import static com.bootdo.common.config.Constant.ROLE_SCIENCE_EXTERNAL_EMPLOYMENT_ID;

/**
 * 科学进步奖任务处理
 *
 * @author houzb
 * @version 1.0
 * @date 2021-04-19 0:49
 */
@Controller
@RequestMapping("/scienceTask")
public class ScienceTaskController extends BaseScienceTechnologyController {
    @Autowired
    private AwardFlowService awardFlowService;
    @Autowired
    private UserService userService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    private AwardPublishTaskService awardPublishTaskService;

    /**
     * 去分配项目给工作人员
     *
     * @return
     */
    @RequiresPermissions("asso:task:assign")
    @RequestMapping("/toAssign")
    public String toAssignPro(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        params.put("roleId", ROLE_SCIENCE_EXTERNAL_EMPLOYMENT_ID);

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
        param.put("roleId", ROLE_SCIENCE_EXTERNAL_EMPLOYMENT_ID);
        List<UserDO> assWorkers = userService.list(param);
        map.put("assWorkers", assWorkers);
        long exUid = 0L;
        for(UserDO aw:assWorkers){
            List<Long> roleIds = aw.getRoleIds();
            if(roleIds.contains(ROLE_SCIENCE_EXTERNAL_EMPLOYMENT_ID)) {
                map.put("firstWorkerName", aw.getName());
                exUid = aw.getUserId();
                map.put("extUserId", exUid);
                break;
            }
        }

        return "act/award/association_assign_pro_new";
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
        /*EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proIdArr[0]);
        String publishTaskId = projectInfoDo.getPublishTaskId();
        int isAssignOverCount = awardEnterpriseProjectService.isAssignOverByTaskId(publishTaskId);
        if (isAssignOverCount == 0) {
            //已分派完成 开启进入协会工作人员形式检查流程
            PublishAwardTaskDo publishAwardTaskDo = new PublishAwardTaskDo();
            publishAwardTaskDo.setId(publishTaskId);
            publishAwardTaskDo.setTaskStat(EnumApplyTaskStat.VALIDATE.getStat());
            awardPublishTaskService.update(publishAwardTaskDo);

            EnterpriseProjectInfoDo proInfo = new EnterpriseProjectInfoDo();
            proInfo.setPublishTaskId(publishTaskId);
            proInfo.setProStat(EnumApplyEnterpriseProStat.VALIDATE.getStat());
            awardEnterpriseProjectService.update(proInfo);
            //更新团队
            awardEnterpriseProjectService.updateTeamStatByTaskId(publishTaskId, EnumApplyEnterpriseProStat.VALIDATE.getStat());
            //更新个人
            awardEnterpriseProjectService.updatePersonStatByTaskId(publishTaskId, EnumApplyEnterpriseProStat.VALIDATE.getStat());

        }*/
        return R.ok();
    }


}
