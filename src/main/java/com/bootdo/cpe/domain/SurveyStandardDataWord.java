package com.bootdo.cpe.domain;

import java.util.List;

/***
 * 勘察设计奖 设计奖
 *       石油工程建设优秀设计奖申报表格 SurverDesignApplyTableInfoDO
 *       主要原材料消耗定额对比 SurverDesiginMajorMaterialsUseInfoDO
 *       产品质量指标对比 SurverDesignProQualityInfoDO
 *       主要公用工程消耗定额对比列表 SurverDesignCommonProUseInfoDO
 *       废水 液 废气 废渣 排放量及排放指标对比 SurverDesignWasteDischargeInfoDO
 *       贡献的主要人员情况列表 SurverDesiginContributeUserInfoDO
 *       项目简介列表 SurverDesignApplyProjectProfileDO
 */
public class SurveyStandardDataWord extends DocBaseDataWord{
    private String applyUnite;
    private String applyCOde;

    private  SurverProjectInfo pro;

    public SurverProjectInfo getPro() {
        return pro;
    }

    public void setPro(SurverProjectInfo pro) {
        this.pro = pro;
    }

    private  SurverStandardApplyTableInfoDO info; //   石油工程建设优秀咨询奖项目申报表

    private  List<SurverDesiginContributeUserInfoDO> userInfo  ; //  在本项目中做出贡献的主要人员情况表列表
    private  List<SurverStandardApplyProjectProfileDO> softApplyProjectProfileDOS  ; //  在本项目中做出贡献的主要人员情况表列表



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

    public List<SurverStandardApplyProjectProfileDO> getSoftApplyProjectProfileDOS() {
        return softApplyProjectProfileDOS;
    }

    public void setSoftApplyProjectProfileDOS(List<SurverStandardApplyProjectProfileDO> softApplyProjectProfileDOS) {
        this.softApplyProjectProfileDOS = softApplyProjectProfileDOS;
    }

    public SurverStandardApplyTableInfoDO getInfo() {
        return info;
    }

    public void setInfo(SurverStandardApplyTableInfoDO info) {
        this.info = info;
    }

}
