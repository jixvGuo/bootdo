package com.bootdo.activiti.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RankMajorProActTask implements JavaDelegate {
    private Logger logger = LoggerFactory.getLogger(RankMajorProActTask.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String proIncId = delegateExecution.getProcessInstanceId();
        logger.debug("开始进行各个专业项目的排名:{}",proIncId);
    }
}
