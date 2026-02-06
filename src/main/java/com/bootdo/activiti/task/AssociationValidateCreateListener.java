package com.bootdo.activiti.task;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.service.NotifyService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bootdo.common.utils.SpringContextHolder.getBean;

public class AssociationValidateCreateListener implements TaskListener {
    private Logger logger = LoggerFactory.getLogger(AssociationValidateCreateListener.class);

    @Override
    public void notify(DelegateTask delegateTask) {
        logger.debug("分派协会工作人员项目形式检查-结束");
        Object proIdObj = delegateTask.getVariable("proId");
        if(proIdObj == null) {
            logger.error("proId Is null ------------------------------------------------------>");
        }else{
            logger.debug("要分派的项目id:{}",proIdObj);
            AwardEnterpriseProjectService awardEnterpriseProjectService = getBean("awardEnterpriseProjectService");
            logger.debug(awardEnterpriseProjectService == null ? "null----->" : "------>notnull");
            Long workerUid= awardEnterpriseProjectService.getAssignUserIdByProId(proIdObj.toString());
            if(workerUid != null) {
                delegateTask.setAssignee(workerUid.toString());
                //更新项目当前的工作流的任务Id
                EnterpriseProjectInfoDo projectInfo = new EnterpriseProjectInfoDo();
                projectInfo.setActRunTaskId(delegateTask.getId());
                projectInfo.setId(Integer.parseInt(proIdObj.toString()));
                projectInfo.setProcInsId(delegateTask.getProcessInstanceId());
                awardEnterpriseProjectService.update(projectInfo);

                //发送通知消息
                EnterpriseProjectInfoDo curProInfo = awardEnterpriseProjectService.get(proIdObj.toString());
                String title = "分派“" +curProInfo.getChengguo() + "”形式审查";
                NotifyService notifyService = getBean("notifyService");
                NotifyDO notifyDO = new NotifyDO();
                //获取对应的获取协会工作人员用户id
                Long[] uids = {workerUid};
                notifyDO.setUserIds(uids);
                notifyDO.setTitle(title);
                notifyDO.setType("4");
                notifyDO.setCreateBy(1l);
                notifyDO.setContent(title);
                notifyService.save(notifyDO);

            }else {
                logger.error("此项目未分配协会工作人员{}",proIdObj);
            }
        }
    }
}
