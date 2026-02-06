package com.bootdo.cpe.domain;

import java.util.List;

/***
 * 勘察设计奖 设计奖
 *
 */
public class SurveySoftDataWord extends DocBaseDataWord{
    private String applyUnite;
    private String applyCOde;
    private  SurverProjectInfo pro;

    public SurverProjectInfo getPro() {
        return pro;
    }

    public void setPro(SurverProjectInfo pro) {
        this.pro = pro;
    }

    private  SurverSoftApplyTableInfoDO info; //   石油工程建设优秀咨询奖项目申报表

    private  List<SurverDesiginContributeUserInfoDO> userInfo  ; //  在本项目中做出贡献的主要人员情况表列表
    private  List<SurverSoftApplyProjectProfileDO> softApplyProjectProfileDOS  ; //  在本项目中做出贡献的主要人员情况表列表

//    List<SurverDesiginContributeUserInfoDO> contributeUserInfoDOList = surverDesiginContributeUserInfoService.list(query);


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


    public List<SurverDesiginContributeUserInfoDO> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<SurverDesiginContributeUserInfoDO> userInfo) {
        this.userInfo = userInfo;
    }


    public List<SurverSoftApplyProjectProfileDO> getSoftApplyProjectProfileDOS() {
        return softApplyProjectProfileDOS;
    }

    public void setSoftApplyProjectProfileDOS(List<SurverSoftApplyProjectProfileDO> softApplyProjectProfileDOS) {
        this.softApplyProjectProfileDOS = softApplyProjectProfileDOS;
    }

    public SurverSoftApplyTableInfoDO getInfo() {
        return info;
    }

    public void setInfo(SurverSoftApplyTableInfoDO info) {
        this.info = info;
    }
}
