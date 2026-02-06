package com.bootdo.activiti.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.dao.AwardEnterpriseProjectDao;
import com.bootdo.activiti.dao.AwardFlowDao;
import com.bootdo.activiti.dao.AwardPublishTaskDao;
import com.bootdo.activiti.domain.AwardUpDocDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.MajorInfo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.cpe.domain.EnumApplyEnterpriseProStat;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProStatEnum;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("awardFlowService")
public class AwardFlowServiceImpl implements AwardFlowService {
    @Autowired
    private AwardFlowDao awardFlowDao;
    @Autowired
    private ActTaskServiceImpl actTaskService;
    @Autowired
    private AwardEnterpriseProjectDao enterpriseProjectDao;
    @Autowired
    private AwardPublishTaskDao awardPublishTaskDao;

    @Override
    public AwardUpDocDo get(String id) {
        return awardFlowDao.get(id);
    }

    @Override
    public String getPublishAwardIdByProcInsId(String procInsId) {
        return awardFlowDao.getPublishAwardIdByProcInsId(procInsId);
    }

    @Override
    public List<AwardUpDocDo> list(Map<String, Object> map) {
        return awardFlowDao.list(map);
    }

    @Override
    public List<AwardUpDocDo> listValidateAssociationTask(String userId) {
        List<Task> list = actTaskService.taskService.createTaskQuery().taskAssignee(userId).list();
        List<AwardUpDocDo> allIst = new ArrayList<>();
        for(Task task:list) {
            String inId = task.getProcessInstanceId();
            Map<String,Object> p = new HashMap<>();
            p.put("procInsId",inId);
            List<AwardUpDocDo> tmpList = awardFlowDao.list(p);
            allIst.addAll(tmpList);
        }
        return allIst;
    }

    @Override
    public int count(Map<String, Object> map) {
        return awardFlowDao.count(map);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public int save(AwardUpDocDo awardUpDocDo) {
        String randomStr = RandomStringUtils.randomAlphanumeric(6);
        awardUpDocDo.setId(UUID.randomUUID().toString().replace("-","") + randomStr);
        return awardFlowDao.save(awardUpDocDo);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public int update(AwardUpDocDo awardUpDocDo) {
        Map<String,Object> vars = new HashMap<>(16);
        vars.put("pass",  awardUpDocDo.getTaskPass() );
        vars.put("title","");
        actTaskService.complete(awardUpDocDo.getTaskId(),vars);
        return awardFlowDao.update(awardUpDocDo);
    }

    /**
     * 修改发布的奖项任务信息
     *
     * @param publishAwardTaskDo
     * @return
     */
    @Override
    public int updatePublishAwardTask(PublishAwardTaskDo publishAwardTaskDo) {
        return awardPublishTaskDao.update(publishAwardTaskDo);
    }

    @Override
    public int remove(String id) {
        return awardFlowDao.remove(id);
    }

    @Override
    public int enterpriseScienceCommitToReview(String proId) {
        EnterpriseProjectInfoDo proInfoUpdate = new EnterpriseProjectInfoDo();
        proInfoUpdate.setId(Integer.parseInt(proId));
        //企业用户上传资料并提交审核标致
        proInfoUpdate.setAssociationReviewRst("upload");
        String proStat = OilProStatEnum.TO_VALIDATE.getProStat();
        proInfoUpdate.setProStat(proStat);
        int rst = enterpriseProjectDao.update(proInfoUpdate);
        String proType = enterpriseProjectDao.getProType(proId);
        if(EnumProjectType.SCIENCE_TEAM.getProType().equals(proType)) {
            //团队
            enterpriseProjectDao.updateTeamStat(proId,proStat);
        }else if(EnumProjectType.SCIENCE_PERSONAL.getProType().equals(proType)) {
            //个人
            enterpriseProjectDao.updatePersonStat(proId,proStat);
        }
        return rst;
    }

    /**
     * 一个奖项任务时间截止，开始审核流程
     *
     * @param taskDo
     * @return
     */
    @Override
    public int oneAwardTaskCompleteStartProcess(PublishAwardTaskDo taskDo) {
        String taskId = taskDo.getId();
        HashMap<String,Object> params = new HashMap<>();
        params.put("award_task_publish_uid",taskDo.getAssociationUserId());
        String processId = actTaskService.startProcessByUid("0",ActivitiConstant.ACTIVITI_APPLY_AWARD[0],ActivitiConstant.ACTIVITI_APPLY_AWARD[1],taskId + "",taskDo.getTaskName(),params);
        taskDo.setProcInsId(processId);
        int rst = awardPublishTaskDao.update(taskDo);
        if(rst > 0) {
            Map<String, Object> proParams = new HashMap<>();
            proParams.put("taskId", taskId);
            proParams.put("procInsId", taskDo.getProcInsId());
            //提交审核的状态标记
            proParams.put("associationReviewRst","upload");
            enterpriseProjectDao.updateProcInsIdByTaskId(proParams);
        }
        return rst;
    }

    /**
     * 根据任务id获取奖项信息
     *
     * @param taskId
     * @return
     */
    @Override
    public PublishAwardTaskDo getAwardTaskById(String taskId) {
        return awardPublishTaskDao.get(taskId);
    }

    @Override
    public PublishAwardTaskDo getAwardTaskByProcInsId(String procInsId) {
        return awardPublishTaskDao.getAwardTaskByProcInsId(procInsId);
    }

    @Override
    public List<MajorInfo> getAwardTaskMajorInfosById(String publishTaskId) {
        return awardPublishTaskDao.getAwardTaskMajorInfosById(publishTaskId);
    }

    @Override
    public int batchRemove(String[] ids) {
        return awardFlowDao.batchRemove(ids);
    }

    @Override
    public List<PublishAwardTaskDo> getAwardLastTasks(int count) {
        Map<String,Object> params = new HashMap<>();
        params.put("offset",0);
        params.put("limit",count);
        return awardPublishTaskDao.list(params);
    }

    @Override
    public List<PublishAwardTaskDo> getAwardLastTasksByAwardType(int count, int awardTypeId) {
        Map<String,Object> params = new HashMap<>();
        params.put("offset",0);
        params.put("limit",count);
        params.put("awardTypeId", awardTypeId);
        return awardPublishTaskDao.list(params);
    }
}
