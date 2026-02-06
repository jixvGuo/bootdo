package com.bootdo.cpe.petroleum_engineering_award.domain;

import com.bootdo.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 形式审查结果枚举
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-19 0:20
 */
public enum OilProStatEnum {
    APPLYING("","申请中", true, false, false, false, false),
    CHECK("check","审核中", false, true, true, false, true),
    PARTAKE_AWARD("partake_award", "参评", false, false, false, true, true),
    NO_AWARD("no_award", "不评", true, false, false, true, true),
    DELAYED_AWARD("delayed_award", "缓评", true, false, false, true, true),
    REJECT("reject", "完善后参评", true, false, false, true, true),

    TO_VALIDATE("to_validate","审核中", false, true, true, false, true),
    TO_ASSIGN_EXPERTS("to_assign_experts","分派专家", false, true, false, true, true),
    SCIENCE_EXPERTS_SCORE("experts_score","专家打分", false, true, false, false, true),

    SCIENCE_ASSIGN_EXPERTS("score","专家打分", false, true, false, false, true),
    ;
    private String proStat;
    private String statDesc;
    //是否允许编辑
    private boolean isEdit;
    //是否允许审核
    private boolean isReview;
    //是否允许撤回审核
    private boolean isCancelReview;
    //是否可以查看审核结果
    private boolean isReviewResult;
    //是否下载项目的文档
    private boolean isDownloadProDoc;

    OilProStatEnum(String proStat, String statDesc, boolean isEdit, boolean isReview, boolean isCancelReview, boolean isReviewResult, boolean isDownloadProDoc) {
        this.proStat = proStat;
        this.statDesc = statDesc;
        this.isEdit = isEdit;
        this.isReview = isReview;
        this.isCancelReview = isCancelReview;
        this.isReviewResult = isReviewResult;
        this.isDownloadProDoc = isDownloadProDoc;
    }

    public String getProStat() {
        return proStat;
    }

    public void setProStat(String proStat) {
        this.proStat = proStat;
    }

    public String getStatDesc() {
        return statDesc;
    }

    public void setStatDesc(String statDesc) {
        this.statDesc = statDesc;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isReview() {
        return isReview;
    }

    public void setReview(boolean review) {
        isReview = review;
    }

    public boolean isCancelReview() {
        return isCancelReview;
    }

    public void setCancelReview(boolean cancelReview) {
        isCancelReview = cancelReview;
    }

    public boolean isReviewResult() {
        return isReviewResult;
    }

    public void setReviewResult(boolean reviewResult) {
        isReviewResult = reviewResult;
    }

    public boolean isDownloadProDoc() {
        return isDownloadProDoc;
    }

    public void setDownloadProDoc(boolean downloadProDoc) {
        isDownloadProDoc = downloadProDoc;
    }

    public static void initApplyStat(Object proInfoObj) {
          if(proInfoObj instanceof OilProInstallBaseInfoDO) {
              OilProInstallBaseInfoDO installInfoDO = (OilProInstallBaseInfoDO) proInfoObj;

          }
    }

    /**
     * 根据数据的关键字，查询对应的项目状态值
     */
    public static List<String> getStatValByKey(String key) {
          List<String> resultList = new ArrayList();
          if(StringUtils.isBlank(key)) {
              return resultList;
          }
          for(OilProStatEnum stat:OilProStatEnum.values()) {
            String desc = stat.getStatDesc();   
            String statVal = stat.getProStat();
        
            if(desc.contains(key)) {
                if(StringUtils.isBlank(statVal)) {
                    statVal = "apply";
                }
                resultList.add(statVal);
            }
          }
          return resultList;
    }


}
