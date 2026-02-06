package com.bootdo.cpe.petroleum_engineering_award.domain;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;

/**
 * 石油工程信息
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-19 0:38
 */
public abstract class OilProBaseInfoDO {
    /**
     * 项目当前的状态
     */
    private String proStat;
    /**
     * 申请开始时间
     */
    private String applyStartDate;
    /**
     * 申请结束时间
     */
    private String applyEndDate;
    /**
     * 形式审查开始时间
     */
    private String checkStartTime;
    /**
     * 形式审查结束时间
     */
    private String checkEndTime;

    /**
     * 申报状态
     */
    private String applyStat;
    /**
     * 是否可以进行编辑调整，针对审核前，驳回可以修改，其余不可以调整
     * true 可以，false 不可以
     */
    private boolean isEdit = true;
    /**
     * 是否审核，只有协会工作人员才可以进行是否为true的设置
     */
    private boolean isReview = false;
    /**
     * 是否撤回，形式审查，true 可以点击撤回，false不可以点击撤回
     */
    private boolean isCancelReview = false;

    //是否可以查看审核结果 true 可以点击查看,false 不可以点击查看
    private boolean isReviewResult = false;

    //是否展示下载上传文件压缩包的权限按钮
    private boolean isDownloadProDoc = false;

    public String getProStat() {
        return proStat;
    }

    public void setProStat(String proStat) {
        this.proStat = proStat;
    }

    public String getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyStartDate(String applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public String getApplyEndDate() {
        return applyEndDate;
    }

    public void setApplyEndDate(String applyEndDate) {
        this.applyEndDate = applyEndDate;
    }

    public String getCheckStartTime() {
        return checkStartTime;
    }

    public void setCheckStartTime(String checkStartTime) {
        this.checkStartTime = checkStartTime;
    }

    public String getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(String checkEndTime) {
        this.checkEndTime = checkEndTime;
    }

    public String getApplyStat() {
        return applyStat;
    }

    public void setApplyStat(String applyStat) {
        this.applyStat = applyStat;
    }

    public boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean getIsReview() {
        return isReview;
    }

    public void setIsReview(boolean review) {
        isReview = review;
    }

    public boolean getIsCancelReview() {
        return isCancelReview;
    }

    public boolean getIsReviewResult() {
        return isReviewResult;
    }

    public boolean getIsDownloadProDoc() {
        return isDownloadProDoc;
    }

    public void initApplyStat() {
        OilProStatEnum[] statEnums = OilProStatEnum.values();
        if(this.proStat == null) {
            this.proStat = "";
        }
        for(OilProStatEnum proStatEnum:statEnums) {
            if(proStatEnum.getProStat().equals(this.proStat)) {
                this.applyStat = proStatEnum.getStatDesc();
                this.isEdit = proStatEnum.isEdit();
                this.isReview = proStatEnum.isReview();
                this.isCancelReview = proStatEnum.isCancelReview();
                this.isReviewResult = proStatEnum.isReviewResult();
                this.isDownloadProDoc = proStatEnum.isDownloadProDoc();
                break;
            }
        }
        //检查申请时间是否已结束
        if(StringUtils.isNotBlank(this.applyEndDate)) {
            boolean isTimeoutFlg = isTimeout(this.applyEndDate);
            if(isTimeoutFlg) {
                this.isCancelReview = false;
                this.isEdit = false;
            }
        }
        //检查审核时间是否结束
        if(StringUtils.isNotBlank(this.checkEndTime)) {
            boolean isTimeoutFlg = isTimeout(this.checkEndTime);
            if(isTimeoutFlg) {
                this.isEdit = false;
                this.isCancelReview = false;
                this.isReview = false;
            }
        }
        //检查审核时间是否开始
        if(StringUtils.isNotBlank(this.checkStartTime)) {
            boolean isStartFlg = isStart(this.checkStartTime);
            if(!isStartFlg) {
                this.isReview = false;
            }
        }
    }

    /**
     * 是否超期
     * @param endDate
     * @return true 超期，false 未超期
     */
    private boolean isTimeout(String endDate) {
        long diff = DateUtils.diffNow(endDate);
        return diff > 0;
    }

    /**
     * 是否超期
     * @param startDate
     * @return true 开始，false 未开始
     */
    private boolean isStart(String startDate) {
        long diff = DateUtils.diffNow(startDate);
        return diff > 0;
    }
}
