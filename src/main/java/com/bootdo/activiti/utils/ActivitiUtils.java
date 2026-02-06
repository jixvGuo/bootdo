package com.bootdo.activiti.utils;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**

 */
@Component
public class ActivitiUtils {
    /**
     * 根据taskId查找businessKey
     */
    @Autowired
    TaskService taskService;
    @Autowired
    RuntimeService runtimeService;
    public String getBusinessKeyByTaskId(String taskId){
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        ProcessInstance pi = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        return pi.getBusinessKey();
    }

    public List<Task> getUserTasks(String userId){
        String candidateUser = userId;
        List<Task> list = taskService// 与正在执行的任务管理相关的Service
                .createTaskQuery()// 创建任务查询对象
                /** 查询条件（where部分） */
                .taskAssignee(candidateUser)
                /** 排序 */
                .orderByTaskCreateTime().asc()// 使用创建时间的升序排列
                /** 返回结果集 */
                .list();// 返回列表
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                System.out.println("任务ID:" + task.getId());
                System.out.println("任务名称:" + task.getName());
                System.out.println("任务的创建时间:" + task.getCreateTime());
                System.out.println("任务的办理人:" + task.getAssignee());
                System.out.println("流程实例ID：" + task.getProcessInstanceId());
                System.out.println("执行对象ID:" + task.getExecutionId());
                System.out.println("流程定义ID:" + task.getProcessDefinitionId());
                System.out.println("########################################################");
            }
        }
        return list;
    }

    public Task getTaskByTaskId(String taskId){
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        return task;
    }

    public List<Task> getTaskByProInsId(String proInsId,String taskAssignee){
        List<Task> task = taskService
                .createTaskQuery()
                .processInstanceId(proInsId).taskAssignee(taskAssignee)
                .list();
        return task;
    }

    public List<Task> getTaskByProInsId(String proInsId){
        List<Task> task = taskService
                .createTaskQuery()
                .processInstanceId(proInsId)
                .list();
        return task;
    }

    public void claimTask(String taskId,String userId) {
         taskService.claim(taskId,userId);
    }
}
