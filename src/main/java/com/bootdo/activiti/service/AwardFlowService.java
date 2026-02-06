package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.AwardUpDocDo;
import com.bootdo.activiti.domain.MajorInfo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;

import java.util.List;
import java.util.Map;

public interface AwardFlowService {
    AwardUpDocDo get(String id);
    String getPublishAwardIdByProcInsId(String procInsId);

    List<AwardUpDocDo> list(Map<String, Object> map);
    List<AwardUpDocDo> listValidateAssociationTask(String userId);

    int count(Map<String, Object> map);

    int save(AwardUpDocDo awardUpDocDo);

    int update(AwardUpDocDo awardUpDocDo);

    /**
     * 修改发布的奖项任务信息
     * @param publishAwardTaskDo
     * @return
     */
    int updatePublishAwardTask(PublishAwardTaskDo publishAwardTaskDo);

    int remove(String id);

    int batchRemove(String[] ids);

    int enterpriseScienceCommitToReview(String proId);

    /**
     * 一个奖项任务时间截止，开始审核流程
     * @param taskDo
     * @return
     */
    int oneAwardTaskCompleteStartProcess(PublishAwardTaskDo taskDo);

    /**
     * 根据任务id获取奖项信息
     * @param taskId
     * @return
     */
    PublishAwardTaskDo getAwardTaskById(String taskId);
    PublishAwardTaskDo getAwardTaskByProcInsId(String procInsId);
    List<MajorInfo> getAwardTaskMajorInfosById(String publishTaskId);
    List<PublishAwardTaskDo> getAwardLastTasks(int count);
    List<PublishAwardTaskDo> getAwardLastTasksByAwardType(int count,int awardTypeId);
}
