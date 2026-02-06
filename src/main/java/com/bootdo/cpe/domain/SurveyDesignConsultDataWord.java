package com.bootdo.cpe.domain;

import java.util.List;

/***
 * 勘察设计奖 设计奖
 *
 */
public class SurveyDesignConsultDataWord extends DocBaseDataWord{
    private String applyUnite;
    private String applyCOde;

    private  SurverProjectInfo pro;

    private  SurverConsultApplyTableInfoDO info; //   石油工程建设优秀咨询奖项目申报表

    private  List<SurverDesiginContributeUserInfoDO> userInfo  ; //  在本项目中做出贡献的主要人员情况表列表
    private  List<SurverConsultApplyProjectProfileDO> projectProfileDOList;//项目简介表列表

    public String getApplyUnite() {
        return applyUnite;
    }

    public SurverProjectInfo getPro() {
        return pro;
    }

    public void setPro(SurverProjectInfo pro) {
        this.pro = pro;
    }

    public void setApplyUnite(String applyUnite) {
        this.applyUnite = applyUnite;
    }

    public String getApplyCOde() {
        return applyCOde;
    }

    public void setApplyCOde(String applyCOde) {
        this.applyCOde = applyCOde;
    }

    public SurverConsultApplyTableInfoDO getInfo() {
        return info;
    }

    public void setInfo(SurverConsultApplyTableInfoDO info) {
        this.info = info;
    }

    public List<SurverDesiginContributeUserInfoDO> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<SurverDesiginContributeUserInfoDO> userInfo) {
        this.userInfo = userInfo;
    }

    public List<SurverConsultApplyProjectProfileDO> getProjectProfileDOList() {
        return projectProfileDOList;
    }

    public void setProjectProfileDOList(List<SurverConsultApplyProjectProfileDO> projectProfileDOList) {
        this.projectProfileDOList = projectProfileDOList;
    }
}
