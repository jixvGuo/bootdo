package com.bootdo.activiti.service.impl;

import com.bootdo.activiti.dao.AwardEnterpriseProjectDao;
import com.bootdo.activiti.domain.AssignProjectDataDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.cpe.domain.EnterpriseOilProInfo;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.domain.science_process.ScienceAssignCountInfo;
import com.bootdo.cpe.domain.science_process.ScienceAssignExternalProInfo;
import com.bootdo.cpe.domain.science_process.ScienceAssignUserInfo;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProStatEnum;
import org.activiti.engine.HistoryService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service("awardEnterpriseProjectService")
public class AwardEnterpriseProjectServiceImpl implements AwardEnterpriseProjectService {
    @Autowired
    private AwardEnterpriseProjectDao projectDao;
    @Autowired
    private ActTaskService actTaskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    ActivitiUtils activitiUtils;

    @Override
    public EnterpriseProjectInfoDo get(String id) {
        EnterpriseProjectInfoDo projectInfoDo = projectDao.get(id);
        projectInfoDo.initShowProName();
        return projectInfoDo;
    }

    /**
     * 获取石油工程的最近使用的项目id，用于绑定同一次的录入
     * 只要存在有一个填写使用了proId，则其他项必须使用完才可使用新的proId
     *
     * @return
     */
    @Override
    public List<EnterpriseOilProInfo> getOilNoOverProIdList() {
        List<EnterpriseOilProInfo> list = projectDao.getOilNoOverProIdList();
        return list;
    }

    @Override
    public List<EnterpriseProjectInfoDo> list(Map<String, Object> map) {
        if (map.get("isValidate") == null) {
            map.put("isValidate",false);
        }
        Object offsetObj = map.get("offset");
        int startNum = offsetObj == null ? 1 : (Integer.parseInt(offsetObj.toString()) + 1);
        AtomicInteger atomicInteger = new AtomicInteger(startNum);
        List<EnterpriseProjectInfoDo> list = projectDao.list(map);
        list.stream().forEach(p->{
            p.initApplyStat();
            if(p.getShowNum() == 0) {
                p.setShowNum(atomicInteger.getAndIncrement());
            }
            p.initShowProName();
        });
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        return projectDao.count(map);
    }

    @Override
    public int save(EnterpriseProjectInfoDo projectInfo) {
        return projectDao.save(projectInfo);
    }

    /**
     * 初始化一个申报项目的标识记录
     *
     * @param taskId
     * @param uid
     * @param projectType
     * @return
     */
    @Override
    public EnterpriseProjectInfoDo initOneProject(String taskId, long uid, EnumProjectType projectType) {
        EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
        projectInfoDo.setPublishTaskId(taskId);
        projectInfoDo.setCreateUid(uid);
        projectInfoDo.setProType(projectType.getProType());
        projectInfoDo.setChengguo(projectType.getDesc());
        save(projectInfoDo);
        return projectInfoDo;
    }

    @Override
    public EnterpriseProjectInfoDo initOneProjectByProSubType(String taskId, long uid, EnumProjectType projectType, String proSubType) {
        EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
        projectInfoDo.setPublishTaskId(taskId);
        projectInfoDo.setCreateUid(uid);
        projectInfoDo.setProType(projectType.getProType());
        projectInfoDo.setChengguo(projectType.getDesc());
        projectInfoDo.setProSubType(proSubType);
        save(projectInfoDo);
        return projectInfoDo;
    }

    @Override
    public int update(EnterpriseProjectInfoDo projectInfo) {

        String taskId = projectInfo.getActRunTaskId();
        Task task = activitiUtils.getTaskByTaskId(taskId);
        Map<String, Object> vars = new HashMap<>(16);
        if (task != null) {
            String taskKey = task.getTaskDefinitionKey();
            String agree = projectInfo.getTaskAgree();
            if ("ass_validate_pro".equals(taskKey)) {
                //提交形式审查结果
                if ("yes".equals(agree) || "over".equals(agree)) {
                    agree = "yes";
                    //审核通过
                } else {
                    agree = "no";
                    //审核不通过(驳回),流程走到企业去修改
                    EnterpriseProjectInfoDo projectInfoDo = projectDao.get(projectInfo.getId() + "");
                    vars.put("enterpriseUser", projectInfoDo.getCreateUid() + "");
                }
            }
            vars.put("agree", agree);
            vars.put("title", "");
            //完成当前个人流程节点
            actTaskService.complete(taskId, vars);

            if ("no".equals(agree)) {
                //驳回则需要查询完成后企业修改的任务id，更新项目的当前运行任务id
                List<Task> updateTaskList = activitiUtils.getUserTasks(vars.get("enterpriseUser").toString());
                if (updateTaskList.size() > 0) {
                    projectInfo.setActRunTaskId(updateTaskList.get(0).getId());
                }
            }
        }

        return projectDao.update(projectInfo);
    }

    @Override
    public int updateProjectInfo(EnterpriseProjectInfoDo projectInfo) {
        return projectDao.update(projectInfo);
    }

    @Override
    public int updateProcInsIdByTaskId(Map<String, Object> map) {
        return projectDao.updateProcInsIdByTaskId(map);
    }

    @Override
    public int updateActRunTaskIddByTaskId(Map<String, Object> map) {
        return projectDao.updateActRunTaskIddByTaskId(map);
    }

    @Override
    public String getMajorLeaderIdByProId(String proId) {
        EnterpriseProjectInfoDo projectInfoDo = projectDao.get(proId);
        String majorId = projectInfoDo.getMajorId() + "";
        Map<String, Object> params = new HashMap<>();
        params.put("majorId", majorId);
        params.put("proId", proId);
        return projectDao.getMajorLeaderIdByProId(params);
    }

    /**
     * 根据任务id修改团队的状态
     *
     * @param taskId
     * @param teamStat
     * @return
     */
    @Override
    public int updateTeamStatByTaskId(String taskId, String teamStat) {
        return projectDao.updateTeamStatByTaskId(taskId,teamStat);
    }

    /**
     * 根据任务id修改个人的状态
     *
     * @param taskId
     * @param personStat
     * @return
     */
    @Override
    public int updatePersonStatByTaskId(String taskId, String personStat) {
        return projectDao.updatePersonStatByTaskId(taskId,personStat);
    }

    /**
     * 进入协会工作人员校审子流程
     *
     * @param publishTaskId
     * @param procInsId
     * @return
     */
    @Override
    public int toAssociationValidateProFlowNode(String publishTaskId, String procInsId) {
        List<String> workerUidList = projectDao.getAssignedProIdsByTaskId(publishTaskId);
        Map<String, Object> vars = new HashMap<>(16);
        vars.put("proIdList", workerUidList);
        vars.put("title", "形式审查");
        //完成当前个人流程节点
        List<Task> taskList = activitiUtils.getTaskByProInsId(procInsId);
        if (taskList.size() == 0) {
            return -1;
        }
        Task task = taskList.get(0);
        String taskId = task.getId();
        actTaskService.complete(taskId, vars);
        return 0;
    }

    @Override
    public int remove(String id) {
        return projectDao.remove(id);
    }

    @Override
    public int removeByExtUid(List<Long> workerUidList, String taskId, String awardType) {
        return projectDao.removeByExtUid(workerUidList, taskId, awardType);
    }

    @Override
    public int batchRemove(String[] ids) {
        return projectDao.batchRemove(ids);
    }

    @Override
    public Tree<EnterpriseProjectInfoDo> getTree(Map<String, Object> map) {
        //获取已分派的项目
        List<Long> assignedProIds = projectDao.taskAssignedProIds(map);
        if (map.get("isValidate") == null) {
            map.put("isValidate",false);
        }
        List<EnterpriseProjectInfoDo> proList = projectDao.list(map);
        List<Tree<EnterpriseProjectInfoDo>> trees = new ArrayList<Tree<EnterpriseProjectInfoDo>>();
        Map<String, String> taskMap = new HashMap<>();
        Map<String, Integer> taskTotalMap = new HashMap<>();
        Map<String, Integer> taskAssignedMap = new HashMap<>();
        for (EnterpriseProjectInfoDo pro : proList) {
            String taskId = "task" + pro.getPublishTaskId();
            taskMap.put(taskId, pro.getTaskName());
            Integer total = taskTotalMap.get(taskId);
            taskTotalMap.put(taskId, total == null ? 1 : total + 1);

            int proId = pro.getId();
            Tree<EnterpriseProjectInfoDo> tree = new Tree<EnterpriseProjectInfoDo>();
            tree.setId(proId + "");
            tree.setParentId(taskId);
            tree.setText(pro.getChengguo());
            Map<String, Object> state = new HashMap<>(16);
            state.put("selected", false);
            assignedProIds.stream().forEach(pid -> {
                if (pid == proId) {
                    state.put("disabled", true);
                    tree.setText(pro.getChengguo() + "(已分派:" + pro.getAssignedUserName() + ")");
                    Integer assigned = taskAssignedMap.get(taskId);
                    taskAssignedMap.put(taskId, assigned == null ? 1 : assigned + 1);
                }
            });
            tree.setState(state);
            trees.add(tree);
        }

        for (String taskId : taskMap.keySet()) {
            int total = taskTotalMap.get(taskId);
            Integer assigned = taskAssignedMap.get(taskId);

            boolean isAssignedOver = assigned != null && assigned == total;
            Tree<EnterpriseProjectInfoDo> tree = new Tree<EnterpriseProjectInfoDo>();
            tree.setId(taskId);
            tree.setParentId("0");
            tree.setText(taskMap.get(taskId));
            Map<String, Object> state = new HashMap<>(16);
            if (isAssignedOver) {
                state.put("disabled", true);
                tree.setText(taskMap.get(taskId) + "(已派完)");
            }
            state.put("selected", false);
            tree.setState(state);
            trees.add(tree);
        }

        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<EnterpriseProjectInfoDo> t = BuildTree.build(trees);
        return t;
    }

    /**
     * 用户被分派的项目信息
     *
     * @param params
     * @return
     */
    @Override
    public List<AssignProjectDataDo> userAssignedProList(Map<String, Object> params) {
        return projectDao.userAssignedProList(params);
    }

    /**
     * 获取专家打分的团队项目列表
     *
     * @param params
     * @return
     */
    @Override
    public List<AssignProjectDataDo> getSpecialistScoreTeamProList(Map<String, Object> params) {
        return projectDao.getSpecialistTeamProList(params);
    }

    /**
     * 根据奖项任务id获取是否分派完
     *
     * @param publishTaskId
     * @return
     */
    @Override
    public int isAssignOverByTaskId(String publishTaskId) {
        return projectDao.isAssignOverByTaskId(publishTaskId);
    }

    /**
     * 分派项目
     *
     * @param list
     * @return
     */
    @Override
    public int assignPro(List<AssignProjectDataDo> list) {
        return projectDao.assignPro(list);
    }

    @Override
    public Long getAssignUserIdByProId(String proId) {
        return projectDao.getAssignUserIdByProId(proId);
    }

    @Override
    public List<EnterpriseProjectInfoDo> getSpecialistReviewPros(Map<String, Object> params) {
        return projectDao.getSpecialistReviewPros(params);
    }

    /**
     * 根据用户id获取目前尚未创建的项目id
     *
     * @param uid
     * @return
     */
    @Override
    public int getNoUseProIdByUid(long uid) {
        List<Integer> list = projectDao.getNoUseProIdByUid(uid);
        return list.size() > 0 ? list.get(0) : 0;
    }

    @Override
    public List<EnterpriseProjectInfoDo> getAllProList(Map<String, Object> params) {
        return projectDao.getAllProList(params);
    }

    /**
     * 获取项目的专业列表
     *
     * @param params
     * @return
     */
    @Override
    public List<String> getProMajorList(Map<String, Object> params) {
        return projectDao.getProMajorList(params);
    }

    @Override
    public List<String> getProGroupList(Map<String, Object> params) {
        return projectDao.getProGroupList(params);
    }

    /**
     * 获取分派外聘项目列表
     *
     * @param params
     * @return
     */
    @Override
    public List<ScienceAssignExternalProInfo> getAssignExtProList(Map<String, Object> params) {
        return projectDao.getAssignExtProList(params);
    }

    /**
     * 获取分派外聘的数量信息
     *
     * @param params
     * @return
     */
    @Override
    public ScienceAssignCountInfo getAssignCountInfo(Map<String, Object> params) {
        ScienceAssignCountInfo countInfo = projectDao.getAssignCountInfo(params);
        return countInfo;
    }

    /**
     * 分派的外聘用户项目信息
     *
     * @param params
     * @return
     */
    @Override
    public List<ScienceAssignUserInfo> getAssignUserProInfo(Map<String, Object> params) {
        return projectDao.getAssignUserProInfo(params);
    }

    /**
     * 获取项目id获取应用医院信息
     *
     * @param proId
     * @return
     */
    @Override
    public String getApplyCompanyByProId(String proId) {
        return null;
    }

    @Override
    public int updateProApply(Integer proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("proStat", "");
        return updateProStat(params);
    }

    @Override
    public int updateProCheck(Integer proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("proStat", "check");
        return updateProStat(params);
    }

    @Override
    public int updateProStat(Map<String, Object> params) {
        Object reviewResultObj = params.get("reviewResult");
        if(reviewResultObj != null) {
            //传递了审核结果，则根据审核结果获取项目状态结果
            String reviewRst = reviewResultObj.toString();
            String proStat = "";
            if(OilProStatEnum.PARTAKE_AWARD.getStatDesc().equals(reviewRst)) {
                proStat = OilProStatEnum.PARTAKE_AWARD.getProStat();
            }else if(OilProStatEnum.REJECT.getStatDesc().equals(reviewRst)) {
                proStat = OilProStatEnum.REJECT.getProStat();
            }else if(OilProStatEnum.DELAYED_AWARD.getStatDesc().equals(reviewRst)) {
                proStat = OilProStatEnum.DELAYED_AWARD.getProStat();
            }else if(OilProStatEnum.NO_AWARD.getStatDesc().equals(reviewRst)) {
                proStat = OilProStatEnum.NO_AWARD.getProStat();
            }
            params.put("proStat", proStat);
        }
        Object proStatObj = params.get("proStat");
        if(proStatObj != null) {
            return projectDao.updateProStat(params);
        }
        return 0;
    }
}
