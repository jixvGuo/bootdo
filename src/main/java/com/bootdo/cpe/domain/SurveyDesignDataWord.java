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
public class SurveyDesignDataWord extends DocBaseDataWord{
    private String applyUnite;
    private String applyCOde;

    private  SurverProjectInfo pro;

    public SurverProjectInfo getPro() {
        return pro;
    }

    public void setPro(SurverProjectInfo pro) {
        this.pro = pro;
    }

    private  SurverDesignApplyTableInfoDO info; //  石油工程建设优秀设计奖申报表格

    private  List<SurverDesiginMajorMaterialsUseInfoDO>  majorMaterialsUse; //  主要原材料消耗定额对比

    private  List<SurverDesignProQualityInfoDO>  designQualitys; //  产品质量指标列表

    private  List<SurverDesignCommonProUseInfoDO>  designProUseInfoDOS; // 工程消耗定额对比

    private  List<SurverDesiginContributeUserInfoDO>  contributeUsers; // 主要人员情况列表

    private  List<SurverDesignWasteDischargeInfoDO>  designWasteDischargeInfoDOS; // 废水

    private  List<SurverDesignApplyProjectProfileDO>  surverDesignApplyProjectProfileDOS; // 废水


    public List<SurverDesignApplyProjectProfileDO> getSurverDesignApplyProjectProfileDOS() {
        return surverDesignApplyProjectProfileDOS;
    }

    public void setSurverDesignApplyProjectProfileDOS(List<SurverDesignApplyProjectProfileDO> surverDesignApplyProjectProfileDOS) {
        this.surverDesignApplyProjectProfileDOS = surverDesignApplyProjectProfileDOS;
    }

    public SurverDesignApplyTableInfoDO getInfo() {
        return info;
    }

    public void setInfo(SurverDesignApplyTableInfoDO info) {
        this.info = info;
    }

    public List<SurverDesiginMajorMaterialsUseInfoDO> getMajorMaterialsUse() {
        return majorMaterialsUse;
    }

    public void setMajorMaterialsUse(List<SurverDesiginMajorMaterialsUseInfoDO> majorMaterialsUse) {
        this.majorMaterialsUse = majorMaterialsUse;
    }

    public List<SurverDesignProQualityInfoDO> getDesignQualitys() {
        return designQualitys;
    }

    public void setDesignQualitys(List<SurverDesignProQualityInfoDO> designQualitys) {
        this.designQualitys = designQualitys;
    }

    public List<SurverDesignCommonProUseInfoDO> getDesignProUseInfoDOS() {
        return designProUseInfoDOS;
    }

    public void setDesignProUseInfoDOS(List<SurverDesignCommonProUseInfoDO> designProUseInfoDOS) {
        this.designProUseInfoDOS = designProUseInfoDOS;
    }

    public List<SurverDesiginContributeUserInfoDO> getContributeUsers() {
        return contributeUsers;
    }

    public void setContributeUsers(List<SurverDesiginContributeUserInfoDO> contributeUsers) {
        this.contributeUsers = contributeUsers;
    }

    public List<SurverDesignWasteDischargeInfoDO> getDesignWasteDischargeInfoDOS() {
        return designWasteDischargeInfoDOS;
    }

    public void setDesignWasteDischargeInfoDOS(List<SurverDesignWasteDischargeInfoDO> designWasteDischargeInfoDOS) {
        this.designWasteDischargeInfoDOS = designWasteDischargeInfoDOS;
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


}
