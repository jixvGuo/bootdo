package com.bootdo.activiti.dao;

import com.bootdo.activiti.domain.AssignProjectDataDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.cpe.domain.EnterpriseOilProInfo;
import com.bootdo.cpe.domain.science_process.ScienceAssignCountInfo;
import com.bootdo.cpe.domain.science_process.ScienceAssignExternalProInfo;
import com.bootdo.cpe.domain.science_process.ScienceAssignUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AwardEnterpriseProjectDao {
    EnterpriseProjectInfoDo get(String id);

    /**
     * 是否已使用了此项目id
     * @return
     */
    List<EnterpriseOilProInfo> getOilNoOverProIdList();

    List<EnterpriseProjectInfoDo> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriseProjectInfoDo project);

    int update(EnterpriseProjectInfoDo project);

    int updateProcInsIdByTaskId(Map<String, Object> map);

    /**
     * 更新团队的状态
     * @param proId
     * @param teamStat
     * @return
     */
    int updateTeamStat(@Param("proId") String proId,@Param("teamStat") String teamStat);

    /**
     * 更新个人的状态
     * @param proId
     * @param personStat
     * @return
     */
    int updatePersonStat(@Param("proId") String proId,@Param("personStat") String personStat);

    /**
     * 根据任务id更新项目的统一状态信息
     * 不参评的与缓评的状态不再做变更
     *
     * @param map
     * @return
     */
    int updateProStatByTaskId(Map<String, Object> map);
    int updateActRunTaskIddByTaskId(Map<String, Object> map);
    String getMajorLeaderIdByProId(Map<String,Object> params);

    public int updateTeamStatByTaskId(@Param("taskId") String taskId, @Param("teamStat") String teamStat);
    public int updatePersonStatByTaskId(@Param("taskId") String taskId,@Param("personStat") String personStat);

    int remove(String id);

    int removeByExtUid(@Param("workerUidList") List<Long> workerUidList, @Param("taskId") String taskId, @Param("awardType") String awardType);

    int batchRemove(String[] ids);

    int assignPro(List<AssignProjectDataDo> list);
    Long getAssignUserIdByProId(String proId);
    String getProCurActRunTaskIdByProId(String proId);
    int isAssignOverByTaskId(String publishTaskId);
    List<String> getAssignedUserIdsByTaskId(String publishTaskId);
    List<String> getAssignedProIdsByTaskId(String publishTaskId);

    List<AssignProjectDataDo> userAssignedProList(Map<String,Object> params);

    /**
     * 获取专家打分的团队项目列表
     * @param params
     * @return
     */
    List<AssignProjectDataDo> getSpecialistTeamProList(Map<String,Object> params);

    List<Long> taskAssignedProIds(Map<String,Object> params);

    List<EnterpriseProjectInfoDo> getSpecialistReviewPros(Map<String,Object> params);

    List<Integer> getNoUseProIdByUid(long uid);

    /***
     * 根据任务id 获取 企业申请信息
     * @param params
     * @return
     */
    List<EnterpriseProjectInfoDo> getAllProList(Map<String,Object> params);

    /**
     * 获取专业列表
     * @param params
     * @return
     */
    List<String> getProMajorList(Map<String, Object> params);

    public List<String> getProGroupList(Map<String, Object> params);

    public List<ScienceAssignExternalProInfo> getAssignExtProList(Map<String, Object> params);

    public ScienceAssignCountInfo getAssignCountInfo(Map<String, Object> params);

    public List<ScienceAssignUserInfo> getAssignUserProInfo(Map<String, Object> params);

    public String getApplyCompanyByProId(String proId);

    /**
     * 是否申请结束
      * @param proId
     * @return
     */
    int isApplyOver(String proId);

    /**
     * 获取当前项目的类型
     * @param proId
     * @return
     */
    String getProType(String proId);

    int updateProStat(Map<String, Object> params);
}
