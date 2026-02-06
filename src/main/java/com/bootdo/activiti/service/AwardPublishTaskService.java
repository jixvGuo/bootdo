package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.PublishAwardTaskDo;

import java.util.List;
import java.util.Map;

public interface AwardPublishTaskService {
    PublishAwardTaskDo get(String id);
    PublishAwardTaskDo getByProId(String proId);

    List<PublishAwardTaskDo> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    long save(PublishAwardTaskDo publishAwardTaskDo);

    int update(PublishAwardTaskDo publishAwardTaskDo);

    int remove(String id);

    int batchRemove(String[] ids);

    /**
	 * 根据发布的奖项任务id获取对应的工作流的实例id
	 * @param publishTaskId
	 * @return
	 */
	int getPublishTaskActProcInsId(String publishTaskId);

    String getLastTaskId();

    /**
     * 获取最新的优质工程奖
     * @return
     */
    PublishAwardTaskDo getLastProTaskByAwardType(String awardType);
    PublishAwardTaskDo getProTaskByTaskId(String taskId);

    /**
     * 根据项目id获取任务id
     * @param proId
     * @return
     */
    String getTaskIdByProId(int proId);


    boolean isAuthByMenuIds(long uid, List<Integer> menuIds);
}
