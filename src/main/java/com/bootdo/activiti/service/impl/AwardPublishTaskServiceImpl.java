package com.bootdo.activiti.service.impl;

import com.bootdo.activiti.dao.AwardPublishTaskDao;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.cpe.domain.EnumApplyTaskStat;
import com.bootdo.cpe.timer.PublishTaskTimer;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("awardPublishTaskService")
public class AwardPublishTaskServiceImpl implements AwardPublishTaskService {
    @Autowired
    private AwardPublishTaskDao publishTaskDao;
    @Autowired
    private ActTaskServiceImpl actTaskService;

    @Override
    public PublishAwardTaskDo get(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<PublishAwardTaskDo> list = publishTaskDao.list(params);
        PublishAwardTaskDo taskDo = list.size() > 0 ? list.get(0) : null;
        if(taskDo != null) {
            taskDo.initStat();
        }
        return taskDo;
    }

    @Override
    public PublishAwardTaskDo getByProId(String proId) {
        Map<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        List<PublishAwardTaskDo> list = publishTaskDao.list(params);
        PublishAwardTaskDo taskDo = list.size() > 0 ? list.get(0) : null;
        if(taskDo != null) {
            taskDo.initStat();
        }
        return taskDo;
    }

    @Override
    public List<PublishAwardTaskDo> list(Map<String, Object> map) {
        List<PublishAwardTaskDo> list = publishTaskDao.list(map);
        list.stream().forEach(t->{
            t.initStat();
        });
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        return publishTaskDao.count(map);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public long save(PublishAwardTaskDo awardTaskDo) {
        String randomStr = RandomStringUtils.randomAlphanumeric(6);
        awardTaskDo.setId(UUID.randomUUID().toString().replace("-","") + randomStr);
        awardTaskDo.setTaskStat(EnumApplyTaskStat.APPLY.getStat());
        //新增需要检测的任务信息
        PublishTaskTimer.PUBLISH_AWARD_TASK_DO_LIST.add(awardTaskDo);
        return publishTaskDao.save(awardTaskDo);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public int update(PublishAwardTaskDo awardTaskDo) {
        return publishTaskDao.update(awardTaskDo);
    }

    @Override
    public int remove(String id) {
        return publishTaskDao.remove(id);
    }

    @Override
    public int batchRemove(String[] ids) {
        return publishTaskDao.batchRemove(ids);
    }

    /**
     * 根据发布的奖项任务id获取对应的工作流的实例id
     *
     * @param publishTaskId
     * @return
     */
    @Override
    public int getPublishTaskActProcInsId(String publishTaskId) {
        return publishTaskDao.getPublishTaskActProcInsId(publishTaskId);
    }

    @Override
    public String getLastTaskId() {
        return publishTaskDao.getLastTaskId();
    }

    /**
     * 获取最新的优质工程奖
     *
     * @return
     */
    @Override
    public PublishAwardTaskDo getLastProTaskByAwardType(String awardType) {
        return publishTaskDao.getLastProTaskByAwardType(awardType);
    }

    @Override
    public PublishAwardTaskDo getProTaskByTaskId(String taskId) {
        return publishTaskDao.getProTaskByTaskId(taskId);
    }

    /**
     * 根据项目id获取任务id
     *
     * @param proId
     * @return
     */
    @Override
    public String getTaskIdByProId(int proId) {
        return publishTaskDao.getTaskIdByProId(proId);
    }

    @Override
    public boolean isAuthByMenuIds(long uid, List<Integer> menuIds) {
        return publishTaskDao.isMenuAuth(uid, menuIds) > 0;
    }
}
