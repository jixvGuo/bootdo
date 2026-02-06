package com.bootdo.activiti.listeners;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class SpecialistScoreAproEndListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("专家打分结束进行分数统计及交由组长协会领导进行复核"+delegateTask.getAssignee());
    }
}
