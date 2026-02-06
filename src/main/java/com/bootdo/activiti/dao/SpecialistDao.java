package com.bootdo.activiti.dao;

import com.bootdo.activiti.domain.AwardGroupInfo;
import com.bootdo.activiti.domain.AwardScoreDetailInfo;
import com.bootdo.activiti.domain.ProjectScoreInfo;
import com.bootdo.activiti.domain.SpecialistSelInfo;
import com.bootdo.cpe.domain.SpecialistMajorAccountInfo;
import com.bootdo.cpe.domain.SpecialistScoreDetailInfo;
import com.bootdo.cpe.domain.SpecialistScoreProInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SpecialistDao {
    int save(SpecialistSelInfo selInfo);
    List<SpecialistSelInfo> list(Map<String,Object> param);
    List<AwardGroupInfo> listAwardGroupInfo(Map<String,Object> params);
    int scorePro(ProjectScoreInfo scoreInfo);
    int removeMineDetailScore(ProjectScoreInfo scoreInfo);
    int scoreDetailPro(ProjectScoreInfo scoreInfo);
    List<AwardScoreDetailInfo> getProScoreDetails(Map<String,Object> params);
    List<ProjectScoreInfo> listProScore(Map<String,Object> params);
    public int getSubmitScoreOverFlg(Map<String, Object> params);
    ProjectScoreInfo getMineScoreProInfo(Map<String, Object> params);
    int isScoreOverPro(String proId,String scoreType);

    /**
     * 获取专家各专业组的专家信息
     * @param params
     * @return
     */
    List<SpecialistMajorAccountInfo> getSpecialistMajorAccountsList(Map<String,Object> params);

    /**
     * 获取专家对某一专业项目的所有评分
     * @param params
     * @return
     */
    List<SpecialistScoreDetailInfo> getSpecialistScoreProDetailList(Map<String,Object> params);

    public List<SpecialistScoreProInfo> getSpecialistScoreProList(Map<String, Object> params);
}
