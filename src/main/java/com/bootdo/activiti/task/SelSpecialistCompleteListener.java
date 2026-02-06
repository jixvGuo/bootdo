package com.bootdo.activiti.task;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelSpecialistCompleteListener implements TaskListener {
    private Logger logger = LoggerFactory.getLogger(SelSpecialistCompleteListener.class);
    @Override
    public void notify(DelegateTask delegateTask) {
        String proInsId = delegateTask.getProcessInstanceId();
        logger.debug("专家开始给个项目打分,{}",proInsId);


    }
}
