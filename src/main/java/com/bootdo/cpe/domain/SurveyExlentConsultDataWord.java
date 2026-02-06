package com.bootdo.cpe.domain;

import java.util.List;

/***
 * 勘察设计奖 设计奖
 *
 */
public class SurveyExlentConsultDataWord extends DocBaseDataWord{
    private String applyUnite;
    private String applyCOde;

    private  SurverProjectInfo pro;

    private  SurverBaseApplyTableInfoDO info; //   石油工程建设优秀咨询奖项目申报表

    private  List<SurverExcellentApplyMainInfoDO> userInfo  ; //  在本项目中做出贡献的主要人员情况表列表
    private  List<SurverConsultApplyProjectProfileDO> projectProfileDOList;//项目简介表列表


    public SurverProjectInfo getPro() {
        return pro;
    }

    public void setPro(SurverProjectInfo pro) {
        this.pro = pro;
    }

    public String getApplyUnite() {
        return applyUnite;
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

    public SurverBaseApplyTableInfoDO getInfo() {
        return info;
    }

    public void setInfo(SurverBaseApplyTableInfoDO info) {
        this.info = info;
    }

    public List<SurverExcellentApplyMainInfoDO> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<SurverExcellentApplyMainInfoDO> userInfo) {
        this.userInfo = userInfo;
    }

    public List<SurverConsultApplyProjectProfileDO> getProjectProfileDOList() {
        return projectProfileDOList;
    }

    public void setProjectProfileDOList(List<SurverConsultApplyProjectProfileDO> projectProfileDOList) {
        this.projectProfileDOList = projectProfileDOList;
    }
}
