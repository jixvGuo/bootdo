package com.bootdo.activiti.task;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.service.NotifyService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static com.bootdo.common.utils.SpringContextHolder.getApplicationContext;
import static com.bootdo.common.utils.SpringContextHolder.getBean;

@Component
public class PublishAwardTask {
    @Autowired
    private AwardPublishTaskService awardPublishTaskService;
    @Autowired
    private AwardFlowService awardFlowService;

    /**
     * 检查发布的任务是否已经结束
     */
//    @Scheduled(cron = "0 */5 * * * ?")
    public void checkPublishTaskIsOver() {
        System.out.println("start check publish task is over");
        Map<String,Object> params = new HashMap<>();
        params.put("overDate", DateUtils.getCurDate());
        List<PublishAwardTaskDo> taskList = awardPublishTaskService.list(params);
        if(taskList.size() > 0) {
            for(PublishAwardTaskDo taskDo:taskList) {
                String procInsId = taskDo.getProcInsId();
                if(StringUtils.isNotBlank(procInsId) && !"0".equals(procInsId)) {
                    continue;
                }
                awardFlowService.oneAwardTaskCompleteStartProcess(taskDo);

                //发送通知消息
                String title = taskDo.getTaskName() + "资料提交结束";
                NotifyService notifyService = getBean("notifyService");
                NotifyDO notifyDO = new NotifyDO();
                //获取协会工作人员用户id
                Map<String,Object> paramsWorker = new HashMap<>();
                paramsWorker.put("roleId",60);
                UserService userService = getBean("userService");
                List<UserDO> assWorkers = userService.list(paramsWorker);
                Long[] uids = new Long[assWorkers.size()];
                final int[] i = {0};
                assWorkers.stream().forEach(w->{
                    uids[i[0]] = w.getUserId();
                    i[0]++;
                });
                notifyDO.setUserIds(uids);
                notifyDO.setTitle(title);
                notifyDO.setType("4");
                notifyDO.setCreateBy(1l);
                notifyDO.setContent(title);
                notifyService.save(notifyDO);
            }
        }
    }
}
