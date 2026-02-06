package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.ActivitiDO;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 */
public interface ActTaskService {
    List<ActivitiDO> listTodo(ActivitiDO act);

    void complete(String taskId, String procInsId, String comment, String title, Map<String, Object> vars);

    void complete(String taskId,Map<String,Object> vars);

    String startProcess(String procDefKey, String businessTable, String businessId, String title, Map<String, Object> vars);
    String startProcessByUid(String startUid,String procDefKey, String businessTable, String businessId, String title, Map<String, Object> vars);

    String getFormKey(String procDefId, String taskDefKey);

    Task getTaskByExecutionId(String executionId);

    InputStream tracePhoto(String processDefinitionId, String executionId);
}
