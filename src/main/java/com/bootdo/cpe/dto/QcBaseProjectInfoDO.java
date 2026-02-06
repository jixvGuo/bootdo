package com.bootdo.cpe.dto;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProStatEnum;

/**
 * 科技进步奖基础类
 *
 * @author houzb
 * @version 1.0
 * @date 2021-04-16 7:15
 */
public class QcBaseProjectInfoDO {
    /**
     * 项目当前的状态
     * 项目的状态信息:"to_validate","待审核中，申请尚未结束"\n"validate","审核中,申请结束，进入审核中"\n"score","参评，企业项目可以进入打分节点"\n"reject","完善后参评，企业形式审查被驳回，需企业进行二次提交审核"\n"no_score","不评，不对该申请项目进行评分"\n"defer_score","缓评,此次不对此申请项目进行评分"\n"result","专家打分结束，算出平均分，及生成相关模板数据"
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
     * 专家开始打分时间
     */
    private String expertStartTime;
    /**
     * 专家打分结束时间
     */
    private String expertEndTime;

    /**
     * 专家开始打分时间
     */
    private String expertStartTimeSecond;
    /**
     * 专家打分结束时间
     */
    private String expertEndTimeSecond;

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
    /**
     * 是否可显示打分按钮
     */
    private boolean isScore = false;

    /**
     * 是否可以提交审核 0不可以，1可以
     * 默认放开，因为不能判定用户是否已经提交完，具体由用户自己决定是否提交
     */
    private int isSubCheck = 1;

    /**
     * 申报的账号
     */
    private String applyAccount;


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
        if(StringUtils.isNotBlank(applyStartDate)) {
            applyStartDate = applyStartDate.replaceAll("\\.0", "");
        }
        this.applyStartDate = applyStartDate;
    }

    public String getApplyEndDate() {
        return applyEndDate;
    }

    public void setApplyEndDate(String applyEndDate) {
        if(StringUtils.isNotBlank(applyEndDate)) {
            applyEndDate = applyEndDate.replaceAll("\\.0", "");
        }
        this.applyEndDate = applyEndDate;
    }

    public String getCheckStartTime() {
        return checkStartTime;
    }

    public void setCheckStartTime(String checkStartTime) {
        if(StringUtils.isNotBlank(checkStartTime)) {
            checkStartTime = checkStartTime.replaceAll("\\.0", "");
        }
        this.checkStartTime = checkStartTime;
    }

    public String getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(String checkEndTime) {
        if(StringUtils.isNotBlank(checkEndTime)) {
            checkEndTime = checkEndTime.replaceAll("\\.0", "");
        }
        this.checkEndTime = checkEndTime;
    }

    public String getExpertStartTime() {
        return expertStartTime;
    }

    public void setExpertStartTime(String expertStartTime) {
        if(StringUtils.isNotBlank(expertStartTime)) {
            expertStartTime = expertStartTime.replaceAll("\\.0", "");
        }
        this.expertStartTime = expertStartTime;
    }

    public String getExpertEndTime() {
        return expertEndTime;
    }

    public void setExpertEndTime(String expertEndTime) {
        if(StringUtils.isNotBlank(expertEndTime)) {
            expertEndTime = expertEndTime.replaceAll("\\.0", "");
        }
        this.expertEndTime = expertEndTime;
    }

    public String getExpertStartTimeSecond() {
        return expertStartTimeSecond;
    }

    public void setExpertStartTimeSecond(String expertStartTimeSecond) {
        if(StringUtils.isNotBlank(expertStartTimeSecond)) {
            expertStartTimeSecond = expertStartTimeSecond.replaceAll("\\.0", "");
        }
        this.expertStartTimeSecond = expertStartTimeSecond;
    }

    public String getExpertEndTimeSecond() {
        return expertEndTimeSecond;
    }

    public void setExpertEndTimeSecond(String expertEndTimeSecond) {
        if(StringUtils.isNotBlank(expertEndTimeSecond)) {
            expertEndTimeSecond = expertEndTimeSecond.replaceAll("\\.0", "");
        }
        this.expertEndTimeSecond = expertEndTimeSecond;
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

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean getIsReview() {
        return isReview;
    }

    public void setReview(boolean review) {
        isReview = review;
    }

    public boolean getIsCancelReview() {
        return isCancelReview;
    }

    public void setCancelReview(boolean cancelReview) {
        isCancelReview = cancelReview;
    }

    public boolean getIsReviewResult() {
        return isReviewResult;
    }

    public void setReviewResult(boolean reviewResult) {
        isReviewResult = reviewResult;
    }

    public boolean getIsDownloadProDoc() {
        return isDownloadProDoc;
    }

    public void setDownloadProDoc(boolean downloadProDoc) {
        isDownloadProDoc = downloadProDoc;
    }

    public boolean getIsScore() {
        return isScore;
    }

    public void setScore(boolean score) {
        isScore = score;
    }

    public int getIsSubCheck() {
        return isSubCheck;
    }

    public void setIsSubCheck(int isSubCheck) {
        this.isSubCheck = isSubCheck;
    }

    public String getApplyAccount() {
        return applyAccount;
    }

    public void setApplyAccount(String applyAccount) {
        this.applyAccount = applyAccount;
    }

    public void initApplyStat() {
        OilProStatEnum[] statEnums = OilProStatEnum.values();
        if(this.proStat == null) {
            this.proStat = "";
        }

        //检查申请时间是否已结束
        boolean isTimeoutFlg = StringUtils.isNotBlank(this.applyEndDate) && isTimeout(this.applyEndDate);
        if(isTimeoutFlg) {
            this.isCancelReview = false;
            this.isEdit = false;
        }

        for(OilProStatEnum proStatEnum:statEnums) {
            if(OilProStatEnum.APPLYING.getProStat().equals(this.proStat) && isTimeoutFlg) {
                //如果目前状态为申请中且申报时间结束，则以申报时间结束的判断为准
                continue;
            }
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

        //检查审核时间是否结束
        if(StringUtils.isNotBlank(this.checkEndTime)) {
            boolean isCheckTimeoutFlg = isTimeout(this.checkEndTime);
            if(isCheckTimeoutFlg) {
                this.isEdit = false;
                this.isCancelReview = false;
                this.isReview = false;
            }
        }
        //检查审核时间是否开始
        if(StringUtils.isNotBlank(this.checkStartTime)) {
            boolean isStartFlg = isStart(this.checkStartTime);
            if(isStartFlg) {
                this.isCancelReview = false;
            }else {
                this.isReview = false;
            }
        }
        if(StringUtils.isNotBlank(this.expertStartTime) && StringUtils.isNotBlank(this.checkEndTime)) {
            boolean isCheckEnd = isTimeout(this.checkEndTime);
            boolean isStartScore = isStart(this.expertStartTime);
            if(isCheckEnd && !isStartScore) {
                //分派专家阶段
                String prefixStr = StringUtils.isNotBlank(this.expertStartTimeSecond) ? "第一阶段" : "";
                this.applyStat = prefixStr + "分派专家";
            }
        }
        if(StringUtils.isNotBlank(this.expertStartTimeSecond) && StringUtils.isNotBlank(this.checkEndTime)) {
            boolean isCheckEnd = isTimeout(this.checkEndTime);
            boolean isStartScore = isStart(this.expertStartTime);
            if(isCheckEnd && !isStartScore) {
                //分派专家阶段
                this.applyStat = "第二阶段分派专家";
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
