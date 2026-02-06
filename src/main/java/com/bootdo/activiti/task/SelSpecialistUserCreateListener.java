package com.bootdo.activiti.task;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.service.NotifyService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.bootdo.common.utils.SpringContextHolder.getBean;

public class SelSpecialistUserCreateListener implements TaskListener {
    private Logger logger = LoggerFactory.getLogger(SelSpecialistUserCreateListener.class);

    @Override
    public void notify(DelegateTask delegateTask) {
       logger.debug("开始选择专家确定组长");
       String proInsId = delegateTask.getProcessInstanceId();
       String taskId = delegateTask.getId();

       AwardFlowService awardFlowService = getBean("awardFlowService");
       AwardEnterpriseProjectService awardEnterpriseProjectService = getBean("awardEnterpriseProjectService");
       PublishAwardTaskDo publishAwardTaskDo = awardFlowService.getAwardTaskByProcInsId(proInsId);
       String publishAwardTaskId = publishAwardTaskDo.getId();
       //获取协会工作人员
       String workerUid= publishAwardTaskDo.getAssociationUserId();
       if(workerUid != null) {
            delegateTask.setAssignee(workerUid.toString());
            //更新项目当前的工作流的任务Id
            Map<String, Object> proParams = new HashMap<>();
            proParams.put("taskId", publishAwardTaskId);
            proParams.put("actRunTaskId", taskId);
            awardEnterpriseProjectService.updateActRunTaskIddByTaskId(proParams);

           //发送通知消息
           String title = publishAwardTaskDo.getTaskName() + "开始选择专家确定组长";
           NotifyService notifyService = getBean("notifyService");
           NotifyDO notifyDO = new NotifyDO();
           //获取对应的获取协会工作人员用户id
           Long[] uids = {Long.parseLong(workerUid)};
           notifyDO.setUserIds(uids);
           notifyDO.setTitle(title);
           notifyDO.setType("4");
           notifyDO.setCreateBy(1l);
           notifyDO.setContent(title);
           notifyService.save(notifyDO);

        }else {
            logger.error("此项目未分配协会工作人员{}",publishAwardTaskId);
        }

    }
}
