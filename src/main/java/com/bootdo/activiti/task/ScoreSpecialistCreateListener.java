package com.bootdo.activiti.task;

import com.bootdo.activiti.domain.AssignProjectDataDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.SpecialistSelInfo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.SpecialistService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.bootdo.common.utils.SpringContextHolder.getBean;

public class ScoreSpecialistCreateListener implements TaskListener {
    private Logger logger = LoggerFactory.getLogger(ScoreSpecialistCreateListener.class);
    @Override
    public void notify(DelegateTask delegateTask) {
        logger.debug("开始动态分派专家进行打分流程");
        Object proIdObj = delegateTask.getVariable("proId");
        if(proIdObj == null) {
            logger.error("score proId Is null ------------------------------------------------------>");
        }else{
            logger.debug("score要分派的项目id:{},{}",proIdObj,delegateTask.getId());
            //1.根据项目id获取对应专业的专家人员
            SpecialistService specialistService = getBean("specialistService");
            List<SpecialistSelInfo> selInfoList = specialistService.listByProId(proIdObj.toString());
            Set<String> specialistUidList = new HashSet<>();
            selInfoList.parallelStream().forEach(sleInfo->{
               String uids = sleInfo.getUids();
               String[] uidArr = uids.split(",");
               List<String> uidList = Arrays.asList(uidArr);
               specialistUidList.addAll(uidList);
               specialistUidList.add(sleInfo.getSpecialistLeaderUid() + "");
            });
            List<String> uidList = new ArrayList<>(specialistUidList);
            //2.开启单个项目多实例的专家打分审核子流程节点specialistUserIds
            delegateTask.setVariable("specialistUserIds",uidList);
            //3.项目分配给专家入库保存关系(表:ass_award_assign_project)，并记录任务id
            AwardEnterpriseProjectService awardEnterpriseProjectService = getBean("awardEnterpriseProjectService");
            List<AssignProjectDataDo> assignProjectDataDoList = new ArrayList<>();
            for(String specialistUid:uidList){
                long sUid = Long.parseLong(specialistUid);
                long pid = Long.parseLong(proIdObj.toString());
                AssignProjectDataDo assignData = new AssignProjectDataDo(0,sUid,pid);
                assignProjectDataDoList.add(assignData);
            }
            awardEnterpriseProjectService.assignPro(assignProjectDataDoList);

            //4.更新项目当前的工作流的任务Id
            EnterpriseProjectInfoDo projectInfo = new EnterpriseProjectInfoDo();
            projectInfo.setActRunTaskId(delegateTask.getId());
            projectInfo.setId(Integer.parseInt(proIdObj.toString()));
            projectInfo.setProcInsId(delegateTask.getProcessInstanceId());
            awardEnterpriseProjectService.update(projectInfo);
        }
    }
}
