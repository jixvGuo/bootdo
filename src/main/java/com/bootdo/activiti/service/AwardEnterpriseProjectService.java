package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.AssignProjectDataDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.common.domain.Tree;
import com.bootdo.cpe.domain.EnterpriseOilProInfo;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.domain.science_process.ScienceAssignCountInfo;
import com.bootdo.cpe.domain.science_process.ScienceAssignExternalProInfo;
import com.bootdo.cpe.domain.science_process.ScienceAssignUserInfo;

import java.util.List;
import java.util.Map;

public interface AwardEnterpriseProjectService {
    EnterpriseProjectInfoDo get(String id);

    /**
     * 获取石油工程的最近使用的项目id，用于绑定同一次的录入
     * 只要存在有一个填写使用了proId，则其他项必须使用完才可使用新的proId
     * @return
     */
    List<EnterpriseOilProInfo> getOilNoOverProIdList();

    List<EnterpriseProjectInfoDo> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriseProjectInfoDo projectInfo);

    /**
     * 初始化一个申报项目的标识记录
     * @param taskId
     * @param uid
     * @param projectType
     * @return
     */
    EnterpriseProjectInfoDo initOneProject(String taskId, long uid, EnumProjectType projectType);
    EnterpriseProjectInfoDo initOneProjectByProSubType(String taskId, long uid, EnumProjectType projectType, String proSubType);

    int update(EnterpriseProjectInfoDo projectInfo);
    int updateProjectInfo(EnterpriseProjectInfoDo projectInfo);
    int updateProcInsIdByTaskId(Map<String, Object> map);
    int updateActRunTaskIddByTaskId(Map<String, Object> map);
    String getMajorLeaderIdByProId(String proId);

    /**
     * 根据任务id修改团队的状态
     * @param taskId
     * @param teamStat
     * @return
     */
    int updateTeamStatByTaskId(String taskId,String teamStat);

    /**
     * 根据任务id修改个人的状态
     * @param taskId
     * @param personStat
     * @return
     */
    int updatePersonStatByTaskId(String taskId,String personStat);


    /**
     * 进入协会工作人员校审子流程
     * @param publishTaskId
     * @param procInsId
     * @return
     */
    int toAssociationValidateProFlowNode(String publishTaskId,String procInsId);

    int remove(String id);
    int removeByExtUid(List<Long> workerUidList, String taskId, String awardType);

    int batchRemove(String[] ids);

  	Tree<EnterpriseProjectInfoDo> getTree(Map<String, Object> map);

    /**
     * 分派项目
     * @param list
     * @return
     */
  	public int assignPro(List<AssignProjectDataDo> list);
  	public Long getAssignUserIdByProId(String proId);

    /**
     * 根据奖项任务id获取是否分派完
     * @param publishTaskId
     * @return
     */
  	public int isAssignOverByTaskId(String publishTaskId);

    /**
     * 用户被分派的项目信息
     * @param params
     * @return
     */
  	public List<AssignProjectDataDo> userAssignedProList(Map<String,Object> params);

    /**
     * 获取专家打分的团队项目列表
     * @param params
     * @return
     */
    public List<AssignProjectDataDo> getSpecialistScoreTeamProList(Map<String,Object> params);

    public List<EnterpriseProjectInfoDo> getSpecialistReviewPros(Map<String,Object> params);

    /**
     * 根据用户id获取目前尚未创建的项目id
     * @param uid
     * @return
     */
    public int getNoUseProIdByUid(long uid);


    /****
     * 获取分派的数据列表
      * @param params
     * @return
     */
    public List<EnterpriseProjectInfoDo> getAllProList(Map<String,Object> params);

    /**
     * 获取项目的专业列表
     * @param params
     * @return
     */
    public List<String> getProMajorList(Map<String,Object> params);

    /**
     * 获取项目分组列表
     * @param params
     * @return
     */
    public List<String> getProGroupList(Map<String,Object> params);

    /**
     * 获取分派外聘项目列表
     * @param params
     * @return
     */
    public List<ScienceAssignExternalProInfo> getAssignExtProList(Map<String,Object> params);

    /**
     * 获取分派外聘的数量信息
     * @param params
     * @return
     */
    public ScienceAssignCountInfo getAssignCountInfo(Map<String,Object> params);

    /**
     * 分派的外聘用户项目信息
     * @param params
     * @return
     */
    public List<ScienceAssignUserInfo> getAssignUserProInfo(Map<String,Object> params);


    /**
     * 获取项目id获取应用医院信息
     * @param proId
     * @return
     */
    public String getApplyCompanyByProId(String proId);

    public int updateProStat(Map<String, Object> params);

    public int updateProApply(Integer proId);

    public int updateProCheck(Integer proId);
}
