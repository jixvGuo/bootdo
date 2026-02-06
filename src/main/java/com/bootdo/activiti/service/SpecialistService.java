package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.AwardGroupInfo;
import com.bootdo.activiti.domain.AwardScoreDetailInfo;
import com.bootdo.activiti.domain.ProjectScoreInfo;
import com.bootdo.activiti.domain.SpecialistSelInfo;
import com.bootdo.common.domain.Tree;
import com.bootdo.cpe.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SpecialistService {
    int save(SpecialistSelInfo selInfo);
    List<SpecialistSelInfo> list(Map<String,Object> param);
    List<SpecialistSelInfo> listByProId(String proId);
    int scorePro(ProjectScoreInfo scoreInfo);
    boolean isScoreOverPro(String proId);

    Tree<AwardGroupInfo> getTreeAwardGroupInfo(Map<String,Object> params);

    /**
     * 获取专家各专业的账号信息
     * @param params
     * @return
     */
    List<TreeBootstrap> getSpecialistMajorAccountList(Map<String,Object> params);

    List<AwardScoreDetailInfo> getProScoreDetails(Map<String,Object> params);
    List<ProjectScoreInfo> getProScoreList(String proId);
    List<ProjectScoreInfo> listProScore(Map<String,Object> params);
    List<ExpertGroupDO> listSpecialistInfoList(Map<String, Object> params);

    /**
     * 获取提交打分结束的标记值
     * @param params
     * @return
     */
    int getSubmitScoreOverFlg(Map<String,Object> params);
    ProjectScoreInfo getMineScoreProInfo(Map<String,Object> params);
    //一个项目完成打分
    void proCompleteScoreFlow(String proId);

    /**
     * 专家提交打分结果结束打分
     * @param scoreUid
     */
    void scoreOver(Long scoreUid,String scoreType);

    /**
     * 分数取消
     * @param scoreUid
     * @param scoreType
     */
    void scoreCancel(Long scoreUid,String scoreType);

    /**
     * 获取当前的是否提交了评分
     * @param scoreUid
     * @param scoreType
     * @return
     */
    int getScoreIsOver(Long scoreUid,String scoreType);

    /**
     * 获取已打分完结的项目列表信息
     * @param params
     * @return
     */
    List<SpecialistScoreProOverCountInfo> getScoreOverSpecialistCountProList(Map<String,Object> params);

    /**
     * 获取某专家项目评分明细
      * @param params
     * @return
     */
    List<SpecialistScoreDetailInfo> getSpecialistScoreProDetailList(Map<String,Object> params);

    public void calculateProScore(String proId);

    int isCalculateProScoreOver(String proId);

    public List<SpecialistScoreProInfo> getSpecialistScoreProList(Map<String, Object> params);

}
